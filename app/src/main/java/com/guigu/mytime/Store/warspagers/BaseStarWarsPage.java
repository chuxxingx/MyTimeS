package com.guigu.mytime.Store.warspagers;

import android.content.Context;
import android.view.View;
import android.widget.FrameLayout;

import com.guigu.mytime.R;

/**
 * Created by Administrator on 2016/3/4.
 * StarWarsPage的基类
 */
public class BaseStarWarsPage {

    public View rootView;
    protected Context mContext;

    private FrameLayout fl_store_rootView_container;


    public BaseStarWarsPage(Context context) {
        mContext = context;
        rootView = initView();
    }

    //初始化rootview
    private View initView() {
        View view = View.inflate(mContext, R.layout.store_starwars_base_page, null);
        fl_store_rootView_container = (FrameLayout) view.findViewById(R.id.fl_store_rootView_container);
        return view;
    }

    public void setContentView(View view){
        fl_store_rootView_container.addView(view);
    }

    public void initData() {

    }
}
