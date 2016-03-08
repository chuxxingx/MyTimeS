package com.guigu.mytime.pager;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.drawable.AnimationDrawable;
import android.os.Handler;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.guigu.mytime.HomeSearchActivity;
import com.guigu.mytime.R;
import com.guigu.mytime.base.BasePager;
import com.guigu.mytime.home.HomeListViewPart;
import com.guigu.mytime.home.HomeTopViewPager;
import com.guigu.mytime.home.bean.FirstPageHomeFeedBean;
import com.guigu.mytime.home.bean.HomeDownDetailBean;
import com.guigu.mytime.home.bean.HomeUpDetailBean;
import com.guigu.mytime.locat.LocationActivity;
import com.guigu.mytime.utils.CacheUtils;
import com.guigu.mytime.utils.ConstantUtils;
import com.guigu.mytime.utils.SpUtils;

import org.xutils.common.Callback;
import org.xutils.common.util.DensityUtil;
import org.xutils.http.RequestParams;
import org.xutils.image.ImageOptions;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/2/29.
 */
public class Home extends BasePager implements View.OnClickListener {

    private ListView lv_home;
    private ImageView iv_loading;

    private HomeTopViewPager vp_tabdetail_pager;

    private LinearLayout ll_tabdetail_point_group;

    private LinearLayout ll_title_search;

    private LinearLayout ll_home_scan;

    private TextView tv_location;

    private TextView tv_total_hotmovie;

    private TextView tv_home_going_to_show;

    private TextView tv_samecity_number;

    private RecyclerView rv_hotplay_movie;

    private TextView tv_all_news;

    private FrameLayout fl_search_bg;

    private String url1;
    private String url2;
    private String url3;

    private List<HomeUpDetailBean.MoviesEntity> movies = new ArrayList<>();
    private static final String TAG = Home.class.getSimpleName();
    private LinearLayoutManager mLayoutManager;

    private List<HomeDownDetailBean.TopPostersEntity> topPosters;

    private HomeDownDetailBean.AreaSecondEntity areaSecond;

    private int preSelectPosition;

    private List<FirstPageHomeFeedBean.Data> data;
    private HomeListViewPart homeListViewPart;
    private ImageOptions imageOptions;
    private View tonews_view;
    private View footView;
    private int footer_view_height;
    private int index = 2;
    private AnimationDrawable background;
    private boolean isFirstStart;
    private Handler handler;
    private int TO_BUY = 0;
    private int TO_SHOW_WILL = 1;
    private int TO_CINEMA = 2;
    private int TO_DISCOVER = 3;
    private String MOVIEDETAIL;

    public Home(Activity activity, Handler handler) {
        super(activity);
        this.handler = handler;
        imageOptions = new ImageOptions.Builder()
                //.setSize(DensityUtil.dip2px(140), DensityUtil.dip2px(211))
                .setRadius(DensityUtil.dip2px(5))
                        // 如果ImageView的大小不是定义为wrap_content, 不要crop.
                .setCrop(false)
                        // 加载中或错误图片的ScaleType
                        //.setPlaceholderScaleType(ImageView.ScaleType.MATRIX)
                .setImageScaleType(ImageView.ScaleType.FIT_XY)
                .build();
    }

    /**
     * 初始化view
     *
     * @return
     */
    public View initView() {
        View view = View.inflate(activity, R.layout.home_fragment_view, null);
        x.view().inject(this, view);
        lv_home = (ListView) view.findViewById(R.id.lv_home);
        iv_loading = (ImageView) view.findViewById(R.id.iv_loading);
        background = (AnimationDrawable) iv_loading.getBackground();
        background.start();
        tonews_view = View.inflate(activity, R.layout.home_top_part, null);

        vp_tabdetail_pager = (HomeTopViewPager) tonews_view.findViewById(R.id.vp_tabdetail_pager);
        ll_tabdetail_point_group = (LinearLayout) tonews_view.findViewById(R.id.ll_tabdetail_point_group);
        ll_title_search = (LinearLayout) tonews_view.findViewById(R.id.ll_title_search);
        ll_home_scan = (LinearLayout) tonews_view.findViewById(R.id.ll_home_scan);
        tv_location = (TextView) tonews_view.findViewById(R.id.tv_location);
        tv_total_hotmovie = (TextView) tonews_view.findViewById(R.id.tv_total_hotmovie);
        tv_home_going_to_show = (TextView) tonews_view.findViewById(R.id.tv_home_going_to_show);
        tv_all_news = (TextView) tonews_view.findViewById(R.id.tv_all_news);
        tv_samecity_number = (TextView) tonews_view.findViewById(R.id.tv_samecity_number);
        rv_hotplay_movie = (RecyclerView) tonews_view.findViewById(R.id.rv_hotplay_movie);
        fl_search_bg = (FrameLayout) tonews_view.findViewById(R.id.fl_search_bg);

        lv_home.addHeaderView(tonews_view);

        footView = View.inflate(activity, R.layout.footer_view, null);
        footView.measure(0, 0);//测量
        footer_view_height = footView.getMeasuredHeight();

        // footView.setPadding(8,8,8,8);//完全显示
        //默认是隐藏的
        footView.setPadding(0, -footer_view_height, 0, 0);//隐藏
        lv_home.addFooterView(footView);
        return view;
    }

    /**
     * 联网请求数据
     *
     * @param url
     */
    private void getNet(String url) {
        RequestParams params = new RequestParams(url);
        getDataFromNet(params, url);
    }

    private void getNet1(String url) {
        RequestParams params = new RequestParams(url);
        getDataFromNet1(params, url);
    }

    private void getDataFromNet1(RequestParams params, final String url) {
        x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Log.e(TAG, "onSuccess==联网成功=" + result);
                CacheUtils.putString(activity, url, result);
                processData1(result);
                footView.setPadding(0, -footer_view_height, 0, 0);//隐藏
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                Log.e(TAG, "onError==" + ex.toString());
                footView.setPadding(0, -footer_view_height, 0, 0);//隐藏
            }

            @Override
            public void onCancelled(CancelledException cex) {
                Log.e(TAG, "onCancelled==" + cex.toString());
                footView.setPadding(0, -footer_view_height, 0, 0);//隐藏
            }

            @Override
            public void onFinished() {
                Log.e(TAG, "onFinished==");
            }
        });
    }

    private void processData1(String json) {
        if (json != null) {
            FirstPageHomeFeedBean firstPageHomeFeedBean = parseJson3(json);
            data.addAll(firstPageHomeFeedBean.data);
            homeListViewPart.notifyDataSetChanged();
        } else {
            Toast.makeText(activity, "没有数据了", Toast.LENGTH_SHORT).show();
            footView.setPadding(0, -footer_view_height, 0, 0);//隐藏
        }
    }


    /**
     * 初始化数据
     */
    public void initData() {
        url1 = ConstantUtils.HOME_UP_URL;
        url2 = ConstantUtils.HOME_DOWN_URL;
        url3 = ConstantUtils.MOVIE_LIST_NEWS_URL;

        //缓存json数据
        String json1 = CacheUtils.getString(activity, url1);
        if (!TextUtils.isEmpty(json1)) {
            processData(json1);
        }
        getNet(url1);
        String json2 = CacheUtils.getString(activity, url2);
        if (!TextUtils.isEmpty(json2)) {
            processData2(json2);
        }
        getNet(url2);

        String json3 = CacheUtils.getString(activity, url3);
        if (!TextUtils.isEmpty(json3)) {
            processData3(json3);
        }
        index = 2;
        getNet(url3);

        setCityLocation();

        tv_location.setOnClickListener(this);
        tv_total_hotmovie.setOnClickListener(this);
        tv_home_going_to_show.setOnClickListener(this);
        tv_samecity_number.setOnClickListener(this);
        tv_all_news.setOnClickListener(this);
        ll_title_search.setOnClickListener(this);
//        vp_tabdetail_pager.setOnClickListener(this);

//        vp_tabdetail_pager.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                int i = vp_tabdetail_pager.getCurrentItem() % topPosters.size();
//                String urlDetail = topPosters.get(i).getUrl();
//                //跳转到新闻详情页面
//                Intent intent = new Intent(activity, MovieDetailActivity.class);
//                intent.putExtra("urlDetail", urlDetail);
//                activity.startActivity(intent);
//            }
//        });
    }

    private void getDataFromNet(RequestParams params, final String url) {
        x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Log.e(TAG, "onSuccess==联网成功=" + result);
                if (url.equals(url1)) {
                    CacheUtils.putString(activity, url, result);
                    processData(result);
                } else if (url.equals(url2)) {
                    CacheUtils.putString(activity, url, result);
                    processData2(result);
                } else {
                    CacheUtils.putString(activity, url, result);
                    processData3(result);
                }
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                Log.e(TAG, "onError==" + ex.toString());
            }

            @Override
            public void onCancelled(CancelledException cex) {
                Log.e(TAG, "onCancelled==" + cex.toString());
            }

            @Override
            public void onFinished() {
                Log.e(TAG, "onFinished==");
            }
        });
    }

    private void processData3(String json) {


        FirstPageHomeFeedBean firstPageHomeFeedBean = parseJson3(json);
        data = firstPageHomeFeedBean.data;
        homeListViewPart = new HomeListViewPart(activity, data, firstPageHomeFeedBean);
        lv_home.setAdapter(homeListViewPart);
        lv_home.setOnScrollListener(new MyOnScrollListener());
    }

    private FirstPageHomeFeedBean parseJson3(String json) {
        return new Gson().fromJson(json, FirstPageHomeFeedBean.class);
    }

    private void processData2(String json) {
        HomeDownDetailBean homeDownDetailBean = parseJson2(json);
        topPosters = homeDownDetailBean.getTopPosters();

        new Thread(){
            public void run(){
                CacheUtils.putInt(activity, "topPosters.size()", topPosters.size());
                for (int i = 0; i < topPosters.size(); i++) {
                    String urltopPosters = topPosters.get(i).getUrl();
                    MOVIEDETAIL = i + "a";
                    Log.e("TAG", "urltopPosters =="+urltopPosters);
                    CacheUtils.putString(activity, MOVIEDETAIL, urltopPosters);
                }
            }
        }.start();

        areaSecond = homeDownDetailBean.getAreaSecond();

        ll_tabdetail_point_group.removeAllViews();//移除所有的点
        for (int i = 0; i < topPosters.size(); i++) {

            ImageView iv_point = new ImageView(activity);
            iv_point.setBackgroundResource(R.drawable.point_selector);

            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(DensityUtil.dip2px(5), DensityUtil.dip2px(5));
            if (i != 0) {
                params.leftMargin = DensityUtil.dip2px(10);
            }
            iv_point.setLayoutParams(params);
            if (i == 0) {
                iv_point.setEnabled(true);
            } else {
                iv_point.setEnabled(false);
            }

            //添加到线性布局
            ll_tabdetail_point_group.addView(iv_point);
        }

        ImageView iv_adv_1 = (ImageView) tonews_view.findViewById(R.id.iv_adv_1);
        ImageView iv_adv_2 = (ImageView) tonews_view.findViewById(R.id.iv_adv_2);
        ImageView iv_adv_3 = (ImageView) tonews_view.findViewById(R.id.iv_adv_3);
        ImageView iv_adv_4 = (ImageView) tonews_view.findViewById(R.id.iv_adv_4);
        ImageView iv_adv_5 = (ImageView) tonews_view.findViewById(R.id.iv_adv_5);

        vp_tabdetail_pager.setAdapter(new MyTopNewsAdapter());
        x.image().bind(iv_adv_1, areaSecond.getSubFirst().getImage(), imageOptions);
        x.image().bind(iv_adv_2, areaSecond.getSubSecond().getImage(), imageOptions);
        x.image().bind(iv_adv_3, areaSecond.getSubThird().getImage(), imageOptions);
        x.image().bind(iv_adv_4, areaSecond.getSubFourth().getImage(), imageOptions);
        x.image().bind(iv_adv_5, areaSecond.getSubFifth().getImage(), imageOptions);
        vp_tabdetail_pager.setOnPageChangeListener(new TopPageChangeListener());

        vp_tabdetail_pager.setCurrentItem(2000);
    }

    private HomeDownDetailBean parseJson2(String json) {
        return new Gson().fromJson(json, HomeDownDetailBean.class);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_location:
                activity.startActivity(new Intent(activity, LocationActivity.class));
                break;
            case R.id.tv_total_hotmovie:
                handler.sendEmptyMessage(TO_BUY);
                break;
            case R.id.tv_home_going_to_show:
                handler.sendEmptyMessage(TO_SHOW_WILL);
                break;
            case R.id.tv_samecity_number:
                handler.sendEmptyMessage(TO_CINEMA);
                break;
            case R.id.tv_all_news:
                handler.sendEmptyMessage(TO_DISCOVER);
                break;
            case R.id.ll_title_search:
                activity.startActivity(new Intent(activity, HomeSearchActivity.class));
                break;
        }
    }

    private class MyTopNewsAdapter extends PagerAdapter {
        @Override
        public int getCount() {
            return Integer.MAX_VALUE;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            ImageView imageView = new ImageView(activity);
            container.addView(imageView);

            //联网请求网络图片
            position = position % topPosters.size();
            x.image().bind(imageView, topPosters.get(position).getImg());
            return imageView;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
//            super.destroyItem(container, position, object);
            container.removeView((View) object);
        }
    }

    /**
     * 处理json数据
     *
     * @param json
     */
    private void processData(String json) {
        background.stop();
        iv_loading.setVisibility(View.GONE);
        HomeUpDetailBean homeUpDetailBean = parseJson(json);

        //1.得的数据
        movies = homeUpDetailBean.getMovies();
        int totalHotMovie = homeUpDetailBean.getTotalHotMovie();
        tv_total_hotmovie.setText("共" + totalHotMovie + "部 ");
        int totalComingMovie = homeUpDetailBean.getTotalComingMovie();
        tv_home_going_to_show.setText(totalComingMovie + "部 ");
        int totalCinemaCount = homeUpDetailBean.getTotalCinemaCount();
        tv_samecity_number.setText(totalCinemaCount + " ");

        mLayoutManager = new LinearLayoutManager(activity);
        mLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        //2.适配器的item
        rv_hotplay_movie.setLayoutManager(mLayoutManager);
        //如果可以确定每个item的高度是固定的，设置这个选项可以提高性能
        rv_hotplay_movie.setHasFixedSize(true);
        rv_hotplay_movie.setAdapter(new MoviesAdapter());
    }

    /**
     * 解析json数据
     *
     * @param json
     * @return
     */
    private HomeUpDetailBean parseJson(String json) {
        return new Gson().fromJson(json, HomeUpDetailBean.class);
    }

    class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.MyViewholder> {
        @Override
        public MoviesAdapter.MyViewholder onCreateViewHolder(ViewGroup parent, int viewType) {
            MyViewholder holder = new MyViewholder(LayoutInflater.from(
                    activity).inflate(R.layout.item_home_movies, parent,
                    false));
            return holder;
        }

        @Override
        public void onBindViewHolder(MoviesAdapter.MyViewholder holder, int position) {
            HomeUpDetailBean.MoviesEntity moviesEntity = movies.get(position);
            x.image().bind(holder.iv_item_home_movies, moviesEntity.getImg());

            if (moviesEntity.isIs3D() && !moviesEntity.isIsIMAX3D()) {
                holder.tv_item_home_movies_cha.setText("3D");
                holder.tv_item_home_movies_cha.setVisibility(View.VISIBLE);
            } else if (moviesEntity.isIsDMAX() && !moviesEntity.isIsIMAX3D()) {
                holder.tv_item_home_movies_cha.setText("DMAX");
                holder.tv_item_home_movies_cha.setVisibility(View.VISIBLE);
            } else if (moviesEntity.isIsIMAX() && !moviesEntity.isIsIMAX3D()) {
                holder.tv_item_home_movies_cha.setText("IMAX");
                holder.tv_item_home_movies_cha.setVisibility(View.VISIBLE);
            } else if (moviesEntity.isIsIMAX3D()) {
                holder.tv_item_home_movies_cha.setText("IMAX 3D");
                holder.tv_item_home_movies_cha.setVisibility(View.VISIBLE);
            } else {
                holder.tv_item_home_movies_cha.setVisibility(View.GONE);
            }

            holder.tv_item_home_movies_score.setText(moviesEntity.getRatingFinal());

            holder.tv_item_home_movies_name.setText(moviesEntity.getTitleCn());
        }

        @Override
        public int getItemCount() {
            return movies.size();
        }

        class MyViewholder extends RecyclerView.ViewHolder {
            public ImageView iv_item_home_movies;
            public TextView tv_item_home_movies_cha;
            public TextView tv_item_home_movies_score;
            public TextView tv_item_home_movies_name;

            public MyViewholder(View itemView) {
                super(itemView);
                iv_item_home_movies = (ImageView) itemView.findViewById(R.id.iv_item_home_movies);
                tv_item_home_movies_cha = (TextView) itemView.findViewById(R.id.tv_item_home_movies_cha);
                tv_item_home_movies_score = (TextView) itemView.findViewById(R.id.tv_item_home_movies_score);
                tv_item_home_movies_name = (TextView) itemView.findViewById(R.id.tv_item_home_movies_name);

            }
        }
    }

    private class TopPageChangeListener implements ViewPager.OnPageChangeListener {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            //把上一次的变暗
            position = position % topPosters.size();
            ll_tabdetail_point_group.getChildAt(preSelectPosition).setEnabled(false);
            //把当前的设置高亮显示
            ll_tabdetail_point_group.getChildAt(position).setEnabled(true);

            preSelectPosition = position;
        }

        @Override
        public void onPageSelected(int position) {

        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    }

    private class MyOnScrollListener implements AbsListView.OnScrollListener {
        @Override
        public void onScrollStateChanged(AbsListView view, int scrollState) {
            // 滑动静止或者惯性滑动，并且是最后一条的时候
            if (scrollState == SCROLL_STATE_IDLE || scrollState == SCROLL_STATE_FLING) {
                if (lv_home.getLastVisiblePosition() == (lv_home.getCount() - 1)) {

                    // 显示加载更多控件
                    footView.setPadding(8, 8, 8, 8);//完全显示
                    // 联网请求数据-回调接口
                    onLoadMore();
                }
            }
        }

        @Override
        public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {

        }
    }

    private void onLoadMore() {
        String url = ConstantUtils.MOVIE_LIST_NEWS_URL_PAGE + index;
        index++;
        getNet1(url);
    }

    /**
     * 设置城市位置
     */
    public static final String LOCATION_ACTION = "com.atguigu.location";
    public static final String CITY_NAME = "city_name";
    public static final String CITY_ID = "city_id";
    private int cityId;
    private String cityName;
    private BroadcastReceiver receiver;

    private void setCityLocation() {
        isFirstStart = true;

        if (isFirstStart) {
            isFirstStart = false;
            cityId = SpUtils.getInt(activity, CITY_ID);
            cityName = SpUtils.getString(activity, CITY_NAME);
            tv_location.setText(cityName);
        }
        receiver = new MyReciever();
        IntentFilter filter = new IntentFilter();
        filter.addAction(LOCATION_ACTION);
        activity.registerReceiver(receiver, filter);
    }

    class MyReciever extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            cityId = intent.getIntExtra(CITY_ID, 0);
            cityName = intent.getStringExtra(CITY_NAME);
            tv_location.setText(cityName);

        }
    }
}
