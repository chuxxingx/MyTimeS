package com.guigu.mytime.view;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * Created by Administrator on 2016/3/1.
 */
public class MyMainViewPager extends ViewPager {
    public boolean isToRight;

    public MyMainViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    /**
     * 取消viewpager的滑动翻页的功能
     * @param ev
     * @return
     */
    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        return true;
    }

    /**
     * 拦截触摸事件
     * 返回false为不拦截
     * 返回true为拦截
     * 让其向里面分发事件
     * @param ev
     * @return
     */
    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return false;
    }
}
