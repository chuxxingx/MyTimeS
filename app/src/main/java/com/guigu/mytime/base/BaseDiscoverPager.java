package com.guigu.mytime.base;

import android.app.Activity;
import android.view.View;

/**
 * Created by Administrator on 2016/2/29.
 */
public abstract class BaseDiscoverPager {
    public Activity activity;
    public View rootView;

    public BaseDiscoverPager(Activity activity) {
        this.activity = activity;
        this.rootView = initView();
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String url ;

    /**
     * 强制子类实现的方法
     * @return
     */
    public abstract View initView();

    /**
     * 子类加载数据的方法
     */
    public void initData(){}
}
