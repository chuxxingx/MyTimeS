package com.guigu.mytime.buyticket;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;

import com.google.gson.Gson;
import com.guigu.mytime.R;
import com.guigu.mytime.base.BaseDiscoverPager;
import com.guigu.mytime.buyticket.activity.PlayMovieItemActivity;
import com.guigu.mytime.buyticket.adapter.PMListViewAdapte;
import com.guigu.mytime.buyticket.bean.PlayingMovieBean;
import com.guigu.mytime.utils.CacheUtils;
import com.guigu.mytime.utils.ConstantUtils;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.List;

/**
 * Created by Administrator on 2016/2/29.
 */
public class PlayingMovie extends BaseDiscoverPager{
    @ViewInject(R.id.lv_playing_movie)
    private ListView lv_playing_movie;

    @ViewInject(R.id.iv_progressbar_buyticket)
    private ImageView iv_progressbar_buyticket;

    /**
     * 数据集合
     */
    private List<PlayingMovieBean.MsEntity> ms;
    /**
     * 动画
     */
    private AnimationDrawable background;
    private PlayingMovieBean movieBean;

    public PlayingMovie(Activity activity) {
        super(activity);
    }

    @Override
    public View initView() {
        View view = View.inflate(activity, R.layout.playing_movie_view,null);
        x.view().inject(this, view);
        background = (AnimationDrawable) iv_progressbar_buyticket.getBackground();
        return view;
    }

    @Override
    public void initData() {
        background.start();//启动动画
        //从内存中获取数据
        String result = CacheUtils.getString(activity, ConstantUtils.MOVIE_LIST+"?locationId=290");
        if(!TextUtils.isEmpty(result)) {
            processData(result);//处理数据
        }
        getDataFromNet();

    }

    private void getDataFromNet() {
        //联网加载数据
        RequestParams params = new RequestParams(ConstantUtils.MOVIE_LIST+"?locationId=290");//北京地址对应的数据
        x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Log.e("TAG", "result == " + result);
                //保存数据
                CacheUtils.putString(activity, ConstantUtils.MOVIE_LIST+"?locationId=290", result);
                processData(result);//处理数据
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

    private void processData(String result) {
        //动画停止
        background.stop();
        iv_progressbar_buyticket.setVisibility(View.GONE);//让动画视图消失

        movieBean = parseData(result);
        //给listview设置适配器
        lv_playing_movie.setAdapter(new PMListViewAdapte(activity,movieBean));
        lv_playing_movie.setOnItemClickListener(new MyOnItemClickListener());
    }

    private PlayingMovieBean parseData(String result) {
        return new Gson().fromJson(result,PlayingMovieBean.class);
    }

    private class MyOnItemClickListener implements android.widget.AdapterView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            PlayingMovieBean.MsEntity msEntity = movieBean.getMs().get(position);
            //Intent intent = new Intent(activity, PlayMovieItemActivity1.class);
            Intent intent = new Intent(activity, PlayMovieItemActivity.class);
//            Bundle bundle=new Bundle();
//            bundle.putSerializable(PlayMovieItemActivity.MOVIELISTPOSITION, msEntity);
//            intent.putExtras(bundle);
            intent.putExtra(PlayMovieItemActivity.MOVIELISTPOSITION,position);
            intent.putExtra(PlayMovieItemActivity.MOVIEID,msEntity.getId());
            activity.startActivity(intent);
        }
    }
}
