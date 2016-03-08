package com.guigu.mytime.base;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Administrator on 2016/2/29.
 */
public abstract class BaseFragment extends Fragment {
    public Activity activity;
    public View rootView;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activity = getActivity();
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = initView();
        return rootView;
    }

    /**
     * 强制子类实现的方法
     * @return
     */
    public abstract View initView();

    /**
     * 子类加载数据的方法
     */
    public void initData(){}

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initData();//当Activity被关联加载后
    }

    /**
     * 实现此方法，销毁特定
     */
    protected  void destoryData(){};

}
