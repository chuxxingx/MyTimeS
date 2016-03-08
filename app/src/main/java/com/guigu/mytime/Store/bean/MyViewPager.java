package com.guigu.mytime.Store.bean;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 *
 * 判断向左还是向右
 * Created by weisong on 2016/1/13.
 */
public class MyViewPager extends ViewPager {
    public MyViewPager(Context context) {
        super(context);
    }

    public MyViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
//        mWidth = getMeasuredWidth();
    }

    /**
     * 内容向右滑动
     */
    public boolean isToRight;

//    private int mWidth;
//    private int mDownX = -1;
//    private int totalScroll;
    @Override
    public boolean onTouchEvent(MotionEvent ev) {
//        int enentX = (int) ev.getRawX();//屏幕
//        switch (ev.getAction()) {
//            case MotionEvent.ACTION_DOWN:
//                mDownX = enentX;
//                break;
//            case MotionEvent.ACTION_MOVE:
//                if (mDownX == -1) {
//                    mDownX = enentX;
//                }
//                int dx = enentX - mDownX;
//                totalScroll  = dx;  //total > mWidth / 2 ,内容向右移动
//                                    //total < -mWidth/2 ,内容向左移动
//                break;
//            case MotionEvent.ACTION_UP:
//                if (totalScroll > mWidth / 2) {
//                    isToRight = true;
//                } else {
//                    isToRight = false;
//                }
//                totalScroll = 0;
//                break;
//            default:
//                break;
//        }
        return super.onTouchEvent(ev);
    }
}
