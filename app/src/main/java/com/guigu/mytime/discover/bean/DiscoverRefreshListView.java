package com.guigu.mytime.discover.bean;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.AbsListView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.guigu.mytime.R;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by chu on 2016/1/13.
 */
public class DiscoverRefreshListView extends ListView {


    public DiscoverRefreshListView(Context context) {
        super(context);
        initHeaderView(context);
        initFooterView(context);
        initAnimation(context);
    }

    public DiscoverRefreshListView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initHeaderView(context);
        initFooterView(context);
        initAnimation(context);
    }
    private LinearLayout headerview;

    /**
     * 记录在Y轴上的起始坐标
     */
    private  float startY;
    /**
     * 下拉刷新控件
     */
    private View ll_pull_donw_refresh;

    /**
     * 下拉刷新控件的高
     */
    private  int pull_donw_refresh_height;
    /**
     * 顶部图
     */
    private View topnews_view;
    /**
     * 在屏幕上ListView y 轴的坐标
     */
    private  float listViewOnScreenY = -1;

    //下拉刷新状态
    public static final int PULL_DOWN_REFRESH = 1;

    //手松刷新状态
    public static final int RELEASE_REFRESH = 2;

    //正在刷新
    public static final int REFRESHING = 3;

    /**
     * 当前的状态
     */
    private  int currentState = PULL_DOWN_REFRESH;

    private ImageView iv_red_arrow_refresh_header;
    private ProgressBar pb_refresh_header;
    private TextView tv_state_refresh_header;
    private TextView tv_time_refresh_header;

    /**
     * 加载更多的视图
     */
    private  View loadview;
    /**
     * 加载更多控件的高
     */
    private int loadviewHeight;

    /**
     * 是否已经加载更多
     */
    private boolean isLoadMore;

    /**
     * 当刷新完成后，状态复原
     * @param isPullDownRefresh
     */
    public void onRefreshFinish(boolean isPullDownRefresh) {
        if(isLoadMore){
            loadview.setPadding(0,-loadviewHeight,0,0);//隐藏加载更多
            isLoadMore = false;
        }else{
            ll_pull_donw_refresh.setPadding(0,-pull_donw_refresh_height,0,0);
            currentState = PULL_DOWN_REFRESH;
            iv_red_arrow_refresh_header.setVisibility(VISIBLE);
            pb_refresh_header.setVisibility(GONE);
            iv_red_arrow_refresh_header.clearAnimation();

//            if(isPullDownRefresh){
//                //更新时间
//                tv_time_refresh_header.setText(getSystemTime());
//            }
        }


    }

    /**
     * 得到系统时间
     * @return
     */
    private CharSequence getSystemTime() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return format.format(new Date());
    }




    /**
     * 监听刷新
     */
    public interface OnRefreshListener{

        /**
         * 当下拉刷新的时候回调这个方法
         * 下拉刷新的监听
         */

        public void onPullDownRefresh();

        /**
         * 加载更多，当滑动底部的时候回调这个方法，请在该方法中请求网络
         */
        public  void onLoadMore();
    }

    private  OnRefreshListener onRefreshListener;

    /**
     * 设置监听下拉刷新
     * ygf
     * @param onRefreshListener
     */
    public void setOnRefreshListener(OnRefreshListener onRefreshListener) {
        this.onRefreshListener = onRefreshListener;
    }

    /**
     * 两个动画
     */
    private Animation upAnimation;
    private Animation downAnimation;

    public void  deleteFootView(){
        this.removeFooterView(loadview);
    }



    private void initFooterView(Context context) {


        loadview = View.inflate(context, R.layout.loadview,null);
        loadview.measure(0,0);
        loadviewHeight = loadview.getMeasuredHeight();
        //隐藏和显示
        // view.setPading(0,-控件的高，0,0）；//完全隐
//         view.setPading(0,0，0,0）；//完全显示
        loadview.setPadding(0,0,0,0);
//        loadview.setPadding(10,10,10,10);

        this.addFooterView(loadview);


        //设置滚动监听
        setOnScrollListener(new MyOnScrollListener());
    }

    public void hideFootView(){
        loadview.setPadding(0,-loadviewHeight,0,0);//隐藏加载更多
    }

    class MyOnScrollListener implements  OnScrollListener{

        /**
         * 静止->滚动
         * 惯性滚动->静止
         * 滚动->惯性滚动
         * @param view
         * @param scrollState
         */
        @Override
        public void onScrollStateChanged(AbsListView view, int scrollState) {
            if(scrollState == SCROLL_STATE_FLING || scrollState ==SCROLL_STATE_IDLE){
                if(getLastVisiblePosition() ==getCount()-1){
                    //1.显示加载更多的控件
//                    if (footview instanceof NewsMenuDetailPager){
//
//                    }
                    loadview.setPadding(10,10,10,10);

                    //2.设置状态 isLoadMore
                    isLoadMore = true;
                    //3.选择滑动到的最后一条
//                    setSelection(getCount());//此方法可以省略,此情该方法无用
                    //4.回调接口
                    if(onRefreshListener != null){
                        onRefreshListener.onLoadMore();
                    }

                }

            }

        }

        /**
         * 滚动过程中回调的方法
         * @param view
         * @param firstVisibleItem
         * @param visibleItemCount
         * @param totalItemCount
         */
        @Override
        public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {

        }
    }


    private void initAnimation(Context context) {
        upAnimation = new RotateAnimation(0,-180,Animation.RELATIVE_TO_SELF,0.5f,Animation.RELATIVE_TO_SELF,0.5f);
        upAnimation.setDuration(50);
        upAnimation.setFillAfter(true);
        downAnimation =new RotateAnimation(-180,-360,Animation.RELATIVE_TO_SELF,0.5f,Animation.RELATIVE_TO_SELF,0.5f);
        downAnimation.setDuration(50);
        downAnimation.setFillAfter(true);

    }


    private void initHeaderView(Context context) {
        headerview = (LinearLayout) View.inflate(context, R.layout.refresh_header, null);
        iv_red_arrow_refresh_header = (ImageView) headerview.findViewById(R.id.iv_red_arrow_refresh_header);
        pb_refresh_header = (ProgressBar) headerview.findViewById(R.id.pb_refresh_header);
        tv_state_refresh_header = (TextView) headerview.findViewById(R.id.tv_state_refresh_header);
        tv_time_refresh_header = (TextView) headerview.findViewById(R.id.tv_time_refresh_header);
        ll_pull_donw_refresh = headerview.findViewById(R.id.ll_pull_donw_refresh);
//       topnews_view = View.inflate(context, R.layout.discover_list_header,null);
        //topnews_view = (RelativeLayout) headerview.findViewById(R.id.rl_head_dis_news);
//        headerview.addView(topnews_view);
        ll_pull_donw_refresh.measure(0,0);//测量
        pull_donw_refresh_height = ll_pull_donw_refresh.getMeasuredHeight();//测量后才有值

        ll_pull_donw_refresh.setPadding(0,-pull_donw_refresh_height,0,0);//隐藏
//        ll_pull_donw_refresh.setPadding(0,-0,0,0);//隐藏

        //添加头
        this.addHeaderView(headerview);

    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        switch (ev.getAction()){
            case MotionEvent.ACTION_DOWN://手指按下
                //1.记录起始坐标
                startY = ev.getY();
                break;
            case MotionEvent.ACTION_MOVE://手指在屏幕上移动
                //2.来到新的坐标
                float endY = ev.getY();
                //3.计算偏移量
                float distanceY = endY - startY;

                //如果正在刷新-联网，不用刷新
                if(currentState ==REFRESHING){
                    break;
                }

//                //判断顶部轮播图是否完全显示
//                boolean isDisplayTopNewsView = isDisplayTopNewsView();
//                if(!isDisplayTopNewsView ){
//                    break;
//                }

                if(distanceY >0 ){
                    //float topPading =   -控件的高 + (endY - startY)
                    float topPading = -pull_donw_refresh_height + distanceY;

                    if(topPading >0 && currentState != RELEASE_REFRESH){
                        currentState = RELEASE_REFRESH;
                        refreshHeaderViewState();
                        System.out.println("手松刷新....");
                    }else if(topPading <0 && currentState != PULL_DOWN_REFRESH){
                        currentState = PULL_DOWN_REFRESH;
                        refreshHeaderViewState();
                        System.out.println("下拉刷新....");
                    }

                    ll_pull_donw_refresh.setPadding(0, (int) topPading,0,0);
                }


                break;
            case MotionEvent.ACTION_UP://手指离开
                if(currentState ==PULL_DOWN_REFRESH){
                    ll_pull_donw_refresh.setPadding(0,-pull_donw_refresh_height,0,0);//直接隐藏
                }else if(currentState ==RELEASE_REFRESH){
                    currentState = REFRESHING;
                    ll_pull_donw_refresh.setPadding(0,0,0,0);//完全显示
                    refreshHeaderViewState();

                    //联网请求
                    if(onRefreshListener != null){
                        onRefreshListener.onPullDownRefresh();
                    }

                }
                break;
            default:
                break;
        }
        return super.onTouchEvent(ev);
    }

    private void refreshHeaderViewState() {
        switch (currentState){
            case PULL_DOWN_REFRESH://下拉刷新状态
                tv_state_refresh_header.setText("下拉刷新...");
                iv_red_arrow_refresh_header.startAnimation(downAnimation);
                break;
            case RELEASE_REFRESH://手松刷新
                tv_state_refresh_header.setText("手松刷新...");
                iv_red_arrow_refresh_header.startAnimation(upAnimation);
                break;
            case REFRESHING://正在刷新

                tv_state_refresh_header.setText("正在刷新...");
                iv_red_arrow_refresh_header.clearAnimation();
                iv_red_arrow_refresh_header.setVisibility(View.GONE);
                pb_refresh_header.setVisibility(VISIBLE);
                break;
            default:
                break;
        }
    }

    /**
     * 判断顶部轮播图是否完全显示
     * 当listview在屏幕上的Y轴坐标小于或者等于顶部轮播图在屏幕上的Y轴坐标的时候，完全显示顶部轮播图了
     * @return
     */
    private boolean isDisplayTopNewsView() {

        //计算listView在屏幕上的坐标
        int[] location = new int[2];
        if(listViewOnScreenY ==-1){
            this.getLocationOnScreen(location);
            listViewOnScreenY = location[1];
        }

        //计算顶部轮播图在屏幕上的坐标
        topnews_view.getLocationOnScreen(location);
        float topNewsViewOnScreenY = location[1];

        return listViewOnScreenY <= topNewsViewOnScreenY;
    }

    public void addTopNewsView(View topnews_view) {
        this.topnews_view = topnews_view;
        headerview.addView(topnews_view);
    }
}
