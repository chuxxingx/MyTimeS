package com.guigu.mytime.buyticket.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.guigu.mytime.R;
import com.guigu.mytime.buyticket.bean.MediaItem;
import com.guigu.mytime.buyticket.bean.MovieListBean;
import com.guigu.mytime.utils.CacheUtils;
import com.guigu.mytime.utils.ConstantUtils;
import com.guigu.mytime.vido.VitamioVideoPlayerActivity;

import org.xutils.common.Callback;
import org.xutils.common.util.DensityUtil;
import org.xutils.http.RequestParams;
import org.xutils.image.ImageOptions;
import org.xutils.x;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class VidoListActivity extends Activity implements AdapterView.OnItemClickListener {
    private ListView listview;
    private List<MovieListBean.VideoListEntity> videoList;
    private ImageOptions imageOptions;
    private ImageButton ib_location_back;
    private ImageView iv_loading;
    private AnimationDrawable background;
    private List<MediaItem> data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vidolist);
        initView();
        Intent intent = getIntent();
        int movieId = intent.getIntExtra("id", 0);
        Log.e("TAG", "movieId=="+movieId);
        if(movieId == 0) {
            Toast.makeText(VidoListActivity.this, "请求数据错误", Toast.LENGTH_SHORT).show();
        }else {
            final String urlvideo= ConstantUtils.MOVIE_MOVIE + "?movieId="+movieId+"&pageIndex=1";
            String string = CacheUtils.getString(VidoListActivity.this, urlvideo);
            if(!TextUtils.isEmpty(string)) {
                parseData(string);
            }
            getDataFromeNet(urlvideo);
        }
        imageOptions = new ImageOptions.Builder()
                .setSize(DensityUtil.dip2px(120), DensityUtil.dip2px(120))
                .setRadius(DensityUtil.dip2px(5))
                        // 如果ImageView的大小不是定义为wrap_content, 不要crop.
                .setCrop(false)
                        // 加载中或错误图片的ScaleType
                        //.setPlaceholderScaleType(ImageView.ScaleType.MATRIX)
                .setImageScaleType(ImageView.ScaleType.CENTER_CROP)
                .setLoadingDrawableId(R.drawable.img_default_300x200)
                .setFailureDrawableId(R.drawable.img_default_300x200)
                .build();
    }

    private void initView() {
        ib_location_back = (ImageButton) findViewById(R.id.ib_location_back);
        iv_loading = (ImageView) this.findViewById(R.id.iv_loading);
        background = (AnimationDrawable) iv_loading.getBackground();
        background.start();
        listview = (ListView) findViewById(R.id.vido_listview);
        ib_location_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void getDataFromeNet(final String urlvideo) {
        x.http().get(new RequestParams(urlvideo), new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                CacheUtils.putString(VidoListActivity.this, urlvideo, result);
                parseData(result);
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {

            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });
    }

    private void parseData(String result) {
        MovieListBean movieListBean = new Gson().fromJson(result, MovieListBean.class);
        videoList = movieListBean.getVideoList();
        data = new ArrayList<MediaItem>();
        for(int i = 0; i < videoList.size(); i++) {
            String hightUrl = videoList.get(i).getHightUrl();
            int length = videoList.get(i).getLength();
            String title = videoList.get(i).getTitle();
            String url = videoList.get(i).getUrl();
            MediaItem mediaItem = new MediaItem(title,length+"",url,hightUrl);
            data.add(mediaItem);
        }
        Log.e("TAG", "data.size()=="+data.size());
        background.stop();
        iv_loading.setVisibility(View.GONE);
        listview.setAdapter(new MyAdapter());
        listview.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent(VidoListActivity.this,VitamioVideoPlayerActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("videolist", (Serializable) data);//传递集合
        intent.putExtras(bundle);
        //传递点击的位置
        intent.putExtra("position",position);
        // intent.setData(Uri.parse("http://vfx.mtime.cn/Video/2016/01/22/mp4/160122092000361861_480.mp4"));
        startActivity(intent);
    }

    private class MyAdapter extends BaseAdapter {
        @Override
        public int getCount() {
            return videoList.size();
        }

        @Override
        public Object getItem(int position) {
            return videoList.get(position);
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder =null;
            if(convertView == null) {
                holder = new ViewHolder();
                convertView = View.inflate(VidoListActivity.this, R.layout.vido_list_item,null);
                holder.iv_vido_list_item = (ImageView) convertView.findViewById(R.id.iv_vido_list_item);
                holder.tv_name_vido_list_item = (TextView) convertView.findViewById(R.id.tv_name_vido_list_item);
                holder.tv_time_vido_list_item = (TextView) convertView.findViewById(R.id.tv_time_vido_list_item);
                convertView.setTag(holder);
            }else {
                holder = (ViewHolder) convertView.getTag();
            }
            MovieListBean.VideoListEntity videoListEntity = videoList.get(position);
            int length = videoListEntity.getLength();
            String image = videoListEntity.getImage();
            String title = videoListEntity.getTitle();
            holder.tv_name_vido_list_item.setText(title);
            holder.tv_time_vido_list_item.setText("片长：" + length + "秒");
//            Glide.with(VidoListActivity1.this).load(image).into(holder.iv_vido_list_item);
            x.image().bind(holder.iv_vido_list_item,image,imageOptions);
            return convertView;
        }
    }
     class ViewHolder{
         ImageView iv_vido_list_item;
         TextView tv_name_vido_list_item;
         TextView tv_time_vido_list_item;
    }
}
