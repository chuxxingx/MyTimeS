package com.guigu.mytime.discover.pager;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.android.volley.toolbox.StringRequest;
import com.google.gson.Gson;
import com.guigu.mytime.R;
import com.guigu.mytime.base.BaseDiscoverPager;
import com.guigu.mytime.discover.bean.DiscoverRefreshListView;
import com.guigu.mytime.discover.bean.Discover_head_imgs_bean;
import com.guigu.mytime.discover.bean.Prevue_bean;
import com.guigu.mytime.discover.utils.Singletonutils;
import com.guigu.mytime.utils.ConstantUtils;
import com.guigu.mytime.vido.VitamioVideoPlayerActivity;

import java.io.UnsupportedEncodingException;
import java.util.List;


/**
 * Created by chu on 2016/2/29.
 */
public class Prevue extends BaseDiscoverPager {
    private static final String TAG = Prevue.class.getSimpleName();

    public Prevue(Activity activity) {
        super(activity);

    }

    private Handler handler = new Handler() {
        public void handleMessage(Message msg) {
            if (msg.what == 1) {
                discover_listview.setAdapter(mDiscoverperAdapter);
                discover_listview.setOnRefreshListener(new mOnRefreshListener());
            }
        }
    };
    class mOnRefreshListener implements DiscoverRefreshListView.OnRefreshListener{

        @Override
        public void onPullDownRefresh() {
            getDataFromNet();
        }

        @Override
        public void onLoadMore() {

        }
    }

    private List<Prevue_bean.TrailersEntity> prevueItem;
    private DiscoverperAdapter mDiscoverperAdapter = new DiscoverperAdapter();

    class DiscoverperAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return prevueItem.size();
        }

        @Override
        public Object getItem(int position) {
            return prevueItem.get(position);
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            PreViewHoldler preViewHoldler;

            if(convertView == null) {
                preViewHoldler = new PreViewHoldler();

                convertView = View.inflate(activity, R.layout.discover_list_item_prevue,null);

                preViewHoldler.tv_dis_prevue_title1 = (TextView) convertView.findViewById(R.id.tv_dis_prevue_title1);
                preViewHoldler.tv_dis_prevue_title2= (TextView) convertView.findViewById(R.id.tv_dis_prevue_title2);
                preViewHoldler.iv_dis_newsitem = (NetworkImageView) convertView.findViewById(R.id.iv_dis_newsitem);
                convertView.setTag(preViewHoldler);

            }else {
                preViewHoldler= (PreViewHoldler) convertView.getTag();
            }
            Prevue_bean.TrailersEntity trailersEntity=prevueItem.get(position);
            preViewHoldler.tv_dis_prevue_title1.setText(trailersEntity.getMovieName());
            preViewHoldler.tv_dis_prevue_title2.setText(trailersEntity.getSummary());


            ImageLoader imageLoader = Singletonutils.getInstance(activity).getImageLoader();
            preViewHoldler.iv_dis_newsitem.setImageUrl(trailersEntity.getCoverImg(),imageLoader);
            return convertView;
        }
    }

    static class  PreViewHoldler{

        NetworkImageView iv_dis_newsitem;
        TextView tv_dis_prevue_title1;
        TextView tv_dis_prevue_title2;
    }


    private LinearLayout rl_discover;
    private DiscoverRefreshListView discover_listview;
    private View head_discover1;
    private TextView tv_head_dis_news;
    private ImageView iv_discover_head_news;
    private ImageView iv_head_dis_prevue;

    @Override
    public View initView() {
        rl_discover = (LinearLayout) View.inflate(activity, R.layout.discover_listview, null);

        discover_listview = (DiscoverRefreshListView) rl_discover.findViewById(R.id.discover_listview);
        head_discover1 = View.inflate(activity, R.layout.head_discover1, null);
        tv_head_dis_news = (TextView) head_discover1.findViewById(R.id.tv_head_dis_news);
        iv_discover_head_news = (ImageView) head_discover1.findViewById(R.id.iv_discover_head_news);
        iv_head_dis_prevue = (ImageView) head_discover1.findViewById(R.id.iv_head_dis_prevue);
        iv_head_dis_prevue.setVisibility(View.VISIBLE);

        discover_listview.addHeaderView(head_discover1);
        discover_listview.setOnItemClickListener(new MyOnItemClickListener());
        return discover_listview;
    }

    class MyOnItemClickListener implements AdapterView.OnItemClickListener{

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            if (position == 1){
                return;
            }else {
                position = position-2;
            }
            Prevue_bean.TrailersEntity data = prevueItem.get(position);
            Log.e(TAG, "qwertyuio"+prevueItem.get(position).getMovieName());
            Intent intent = new Intent(activity,VitamioVideoPlayerActivity.class);
            //传递点击的位置
//            intent.putExtra("url",data.getUrl());
            intent.setData(Uri.parse(data.getUrl()));
            // intent.setData(Uri.parse("http://vfx.mtime.cn/Video/2016/01/22/mp4/160122092000361861_480.mp4"));
            activity.startActivity(intent);
        }
    }

    @Override
    public void initData() {
        super.initData();

        getDataFromNet();
    }

    private void getDataFromNet() {
        RequestQueue requestQueue = Singletonutils.getInstance(activity).getRequestQueue();

        StringRequest request = new StringRequest(Request.Method.GET, ConstantUtils.discoverItem_prevue_baseUrl, new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {
                Log.e(TAG, "恭喜联网成功 s 《《《《《《《《《《《== " + s);
                discover_listview.onRefreshFinish(true);
                processData(s);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Log.e(TAG, "联网失败 《《《《《《《《《《《《《《《《《《");
            }
        }) {
            @Override
            protected Response<String> parseNetworkResponse(NetworkResponse response) {
                try {
                    String jsonData = new String(response.data, "UTF-8");
                    return Response.success(jsonData, HttpHeaderParser.parseCacheHeaders(response));
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                return super.parseNetworkResponse(response);
            }
        };

        StringRequest request1 = new StringRequest(Request.Method.GET, ConstantUtils.discoverAll_head_baseUrl, new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {
                Log.e(TAG, "请求成功head data =----------------------------- " + s);
                processHeadData(s);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Log.e(TAG, "请求失败----------------------------------------------");
            }
        }) {
            @Override
            protected Response<String> parseNetworkResponse(NetworkResponse response) {
                try {
                    String jsonData = new String(response.data, "UTF-8");
                    return Response.success(jsonData, HttpHeaderParser.parseCacheHeaders(response));
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                return super.parseNetworkResponse(response);
            }
        };
        requestQueue.add(request1);

        requestQueue.add(request);

    }

    private void processData(String s) {
        Gson gson=new Gson();
        Prevue_bean prevue_bean=gson.fromJson(s,Prevue_bean.class);
        prevueItem = prevue_bean.getTrailers();
        handler.sendEmptyMessage(1);
    }

    private void processHeadData(String s) {
        Gson gson=new Gson();
        Discover_head_imgs_bean discover_head_imgs_bean=gson.fromJson(s,Discover_head_imgs_bean.class);

        Log.e(TAG, "头数据请求成功 =========================================");
        ImageLoader imageLoader = Singletonutils.getInstance(activity).getImageLoader();
        imageLoader.get(discover_head_imgs_bean.getTrailer().getImageUrl(), ImageLoader.getImageListener(iv_discover_head_news,
                R.drawable.img_default, R.drawable.img_default));
        tv_head_dis_news.setText(discover_head_imgs_bean.getNews().getTitle());
    }
}

