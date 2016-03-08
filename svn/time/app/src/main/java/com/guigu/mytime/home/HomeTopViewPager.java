package com.guigu.mytime.home;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

import com.guigu.mytime.webview.MovieDetailActivity;
import com.guigu.mytime.utils.CacheUtils;

/**
 * Created by wuyapeng on 2016/3/1.
 */
public class HomeTopViewPager extends ViewPager {

    /**
     * 起始位置
     */
    private float startX;
    private float startY;
    private MyHandler myHandler;
    private Activity activity;
    private String MOVIEDETAIL;

    public HomeTopViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.activity = (Activity) context;
        myHandler = new MyHandler();
        myHandler.removeCallbacksAndMessages(null);
        myHandler.postDelayed(new MyRunnable(),3000);
    }

    /**
     * 事件的分发处理
     * @param ev
     * @return
     *
     * 通过判断scrollx与scrolly的大小来判定是上下还是左右滑动
     * 当左右滑动的时候viewpager响应，阻止父视图的拦截
     * getParent().requestDisallowInterceptTouchEvent(true);
     *
     * 当上下滑动的时候viewpager不响应，允许俯视图拦截
     * getParent().requestDisallowInterceptTouchEvent(false);
     *
     * 当向左滑动到最后一页的时候，允许父视图进行拦截
     * getParent().requestDisallowInterceptTouchEvent(false);
     *
     * 当向右滑动到第一页的时候，允许父视图进行拦截
     * getParent().requestDisallowInterceptTouchEvent(false);
     *
     *
     */
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN :
                startX = ev.getX();
                startY = ev.getY();
                //getParent().requestDisallowInterceptTouchEvent(true);
                if(myHandler!=null) {
                    myHandler.removeCallbacksAndMessages(null);
                }
                break;
            case MotionEvent.ACTION_MOVE :
                float endX = ev.getX();
                float endY = ev.getY();
                float distanceX = endX - startX;
                float distanceY = endY - startY;
                if(Math.abs(distanceX) > Math.abs(distanceY)) {
                    if(myHandler !=null) {
                        myHandler.removeCallbacksAndMessages(null);
                    }
                }else {
                    //下拉
                    getParent().requestDisallowInterceptTouchEvent(false);
                    if(myHandler !=null) {
                        myHandler.removeCallbacksAndMessages(null);
                        myHandler.postDelayed(new MyRunnable(),3000);
                    }
                }
                break;
            case MotionEvent.ACTION_UP :
                if(myHandler !=null) {
                    myHandler.postDelayed(new MyRunnable(),3000);
                }
                float endXi = ev.getX();
                float endYi = ev.getY();
                float distanceXi = endXi - startX;
                float distanceYi = endYi - startY;
                if(Math.abs(distanceXi) < 3 && Math.abs(distanceYi) < 3) {
                    int anInt = CacheUtils.getInt(activity, "topPosters.size()");
                    int currentItem = getCurrentItem()%anInt;
                    MOVIEDETAIL = currentItem + "a";
                    String string = CacheUtils.getString(activity, MOVIEDETAIL);
                    //跳转到新闻详情页面
                    Intent intent = new Intent(activity, MovieDetailActivity.class);
                    intent.putExtra("urlDetail", string);
                    activity.startActivity(intent);
                }
                break;
            default:
                break;
        }
        return super.dispatchTouchEvent(ev);
    }

    private class MyHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            if(HomeTopViewPager.this.getAdapter() != null) {
                int i = (HomeTopViewPager.this.getCurrentItem() + 1) % (HomeTopViewPager.this.getAdapter().getCount());
                HomeTopViewPager.this.setCurrentItem(i);
                if(myHandler!=null) {
                    myHandler.removeCallbacksAndMessages(null);
                    myHandler.postDelayed(new MyRunnable(),3000);
                }
            }
        }
    }

    private class MyRunnable implements Runnable {
        @Override
        public void run() {
            myHandler.sendEmptyMessage(0);
        }
    }
}
