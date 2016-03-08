package com.guigu.mytime.Store;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.GridView;

/**
 * Created by Administrator on 2016/2/29.
 * 会显示的gridview
 */
public class ShowAllGridView extends GridView{
    public ShowAllGridView(Context context) {
        super(context);
    }

    public ShowAllGridView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ShowAllGridView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int mExpandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2,
                MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, mExpandSpec);
    }
}
