package com.guigu.mytime.base;

import android.app.Activity;
import android.view.View;

/**
 * Created by Administrator on 2016/3/2.
 */
public abstract class BasePager {
    public Activity activity;
    public View rootView;

    public BasePager(Activity activity) {
        this.activity = activity;
        rootView = initView();
    }

    public abstract View initView();

    public void initData(){}

    public void destoryData() {
    }

    public void onDestroy(){}
}
