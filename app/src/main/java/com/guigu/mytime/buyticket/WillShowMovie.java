package com.guigu.mytime.buyticket;

import android.app.Activity;
import android.graphics.drawable.AnimationDrawable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.guigu.mytime.R;
import com.guigu.mytime.base.BaseDiscoverPager;
import com.guigu.mytime.buyticket.adapter.MyRcyclerViewAdapter;
import com.guigu.mytime.buyticket.adapter.MyWillShowMovieListAdapter;
import com.guigu.mytime.buyticket.bean.WillShowMovieBean;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/2/29.
 */
public class WillShowMovie extends BaseDiscoverPager implements AdapterView.OnItemClickListener {

    private ListView lv_willshowmovie;
    private TextView tv_willshowmovie;
    private RecyclerView rcy_willshowmovie;
    //动画
    private AnimationDrawable background;
    private ImageView iv_loading_animation;
    private ChildData childData;
    /**
     * listview在屏幕中位置
     */
    private int listViewOnScreenY = -1;

    public WillShowMovie(Activity activity) {
        super(activity);
    }

    @Override
    public View initView() {
        View view = View.inflate(activity, R.layout.will_show_movie_view,null);
        iv_loading_animation = (ImageView) view.findViewById(R.id.iv_loading_animation);
        lv_willshowmovie = (ListView) view.findViewById(R.id.lv_willshowmovie);
        View headerView = View.inflate(activity,R.layout.will_show_movie_top_view,null);
        rcy_willshowmovie = (RecyclerView) headerView.findViewById(R.id.rcy_willshowmovie);
        tv_willshowmovie = (TextView) headerView.findViewById(R.id.tv_willshowmovie);
        lv_willshowmovie.addHeaderView(headerView);//添加头部

        background = (AnimationDrawable) iv_loading_animation.getBackground();
        background.start();
        return view;
    }

    @Override
    public void initData() {
        getDataFromeNet(290);
    }

    private void getDataFromeNet(int location) {
        String url = "http://api.m.mtime.cn/Movie/MovieComingNew.api?locationId=" + location;
        RequestParams params = new RequestParams(url);
        x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                processData(result);
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
        WillShowMovieBean willShowMovieBean = parseData(result);
        int size = willShowMovieBean.getMoviecomings().size();
        tv_willshowmovie.setText("即将上映("+size+"部)");

        LinearLayoutManager manager = new LinearLayoutManager(activity);
        manager.setOrientation(LinearLayoutManager.HORIZONTAL);
        rcy_willshowmovie.setLayoutManager(manager);
        //如果确定每个item的高度是一定的这个方法能够提供其性能
        rcy_willshowmovie.setHasFixedSize(true);
        rcy_willshowmovie.setAdapter(new MyRcyclerViewAdapter(activity, willShowMovieBean));
        //listview设置适配器
       // lv_willshowmovie.setHeaderView(View.inflate(activity, R.layout.month_bar, null));
//        String[] group = {"3月","4月","5月"};
        getChild(willShowMovieBean);
//        PinnedHeaderExpandableAdapter adapter = new PinnedHeaderExpandableAdapter(childData, group, activity,lv_willshowmovie);
//        lv_willshowmovie.setAdapter(adapter);
        lv_willshowmovie.setAdapter(new MyWillShowMovieListAdapter(activity, willShowMovieBean));
        lv_willshowmovie.setOnItemClickListener(this);
        //停止动画
        background.stop();
        iv_loading_animation.setVisibility(View.GONE);
    }


    private void getChild(WillShowMovieBean willShowMovieBean) {
        List<WillShowMovieBean.MoviecomingsEntity> moviecomings = willShowMovieBean.getMoviecomings();
        List list3= new ArrayList();
        List list4= new ArrayList();
        List list5= new ArrayList();
        for(int i = 0; i < moviecomings.size(); i++) {
            WillShowMovieBean.MoviecomingsEntity moviecomingsEntity = moviecomings.get(i);
            int rMonth = moviecomingsEntity.getRMonth();
            if(rMonth == 3) {
               list3.add(moviecomingsEntity);
            }else if(rMonth == 4) {
                list4.add(moviecomingsEntity);
            }else if (rMonth == 5){
                list5.add(moviecomingsEntity);
            }
        }
        childData = new ChildData();
        childData.list3 = list3;
        childData.list4 = list4;
        childData.list5 = list5;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }

    public class ChildData{
        public   List list3;
        public  List list4;
        public  List list5;
    }

    private WillShowMovieBean parseData(String result) {
        return new Gson().fromJson(result,WillShowMovieBean.class);
    }
}
