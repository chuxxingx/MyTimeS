package com.guigu.mytime.buyticket.adapter;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListAdapter;

import com.guigu.mytime.buyticket.bean.CinemaBean;

/**
 * Created by Administrator on 2016/3/1.
 */
public class MyCinemaAdapter extends BaseAdapter {
    private Activity activity;
    private CinemaBean cinemaBean;
    public MyCinemaAdapter(Activity activity, CinemaBean cinemaBean) {
        this.activity = activity;
        this.cinemaBean = cinemaBean;
    }

    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return null;
    }
}
