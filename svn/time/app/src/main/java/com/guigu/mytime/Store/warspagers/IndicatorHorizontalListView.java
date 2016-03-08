package com.guigu.mytime.Store.warspagers;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * Created by Administrator on 2016/3/4.
 *拦截
 */
public class IndicatorHorizontalListView extends HorizontalListView{
    public IndicatorHorizontalListView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }
    private int downX = -1;
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int eventX = (int) event.getX();
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN :
                downX = eventX;
                break;
            case MotionEvent.ACTION_MOVE :
                if(downX == -1) {
                    downX = eventX;
                }
                int tottalDX = Math.abs(eventX - downX);
                if (tottalDX > 10) {
                    getParent().requestDisallowInterceptTouchEvent(true);
                }
                break;
            case MotionEvent.ACTION_UP:
                downX = -1;
                break;
            default:
                break;
        }
        return super.onTouchEvent(event);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return super.onInterceptTouchEvent(ev);
    }
}
