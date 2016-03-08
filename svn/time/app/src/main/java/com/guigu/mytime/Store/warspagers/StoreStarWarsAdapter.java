package com.guigu.mytime.Store.warspagers;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/3/4.
 */
public class StoreStarWarsAdapter extends PagerAdapter {
    private Context mContext;
    private ArrayList<BaseStarWarsPage> mPages;

    public StoreStarWarsAdapter(Context context, ArrayList<BaseStarWarsPage> pages) {
        mContext = context;
        mPages = pages;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        BaseStarWarsPage page = mPages.get(position);
        View rootView = page.rootView;
        page.initData();
        container.addView(rootView);
        return rootView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return super.getPageTitle(position);
    }



    @Override
    public int getCount() {
        if (mPages == null) {
            return 0;
        }
        return mPages.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }
}
