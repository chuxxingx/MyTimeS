package com.guigu.mytime.buyticket.adapter;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.guigu.mytime.R;
import com.guigu.mytime.buyticket.bean.MovieListBean;

/**
 * Created by Administrator on 2016/3/4.
 */
public class MyVidoListBaseAdapter extends BaseAdapter {
    private Activity activity;
    private MovieListBean movieListBean;
    public MyVidoListBaseAdapter(Activity activity, MovieListBean movieListBean) {
        this.activity =activity;
        this.movieListBean = movieListBean;
    }

    @Override
    public int getCount() {
        return movieListBean.getVideoList().size();
    }

    @Override
    public Object getItem(int position) {
        return movieListBean.getVideoList().get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder =null;
        if(convertView == null) {
            holder = new ViewHolder();
            convertView = View.inflate(activity, R.layout.vido_list_item,null);
            holder.iv_vido_list_item = (ImageView) convertView.findViewById(R.id.iv_vido_list_item);
            holder.tv_name_vido_list_item = (TextView) convertView.findViewById(R.id.tv_time_vido_list_item);
            holder.tv_time_vido_list_item = (TextView) convertView.findViewById(R.id.tv_time_vido_list_item);
            convertView.setTag(holder);
        }else {
            holder = (ViewHolder) convertView.getTag();
        }
        MovieListBean.VideoListEntity videoListEntity = movieListBean.getVideoList().get(position);
        String image = videoListEntity.getImage();
        String title = videoListEntity.getTitle();
        int length = videoListEntity.getLength();
        holder.tv_name_vido_list_item.setText(title);
        holder.tv_time_vido_list_item.setText("片长："+length+"秒");
        Glide.with(activity).load(image).into(holder.iv_vido_list_item);
        return convertView;
    }
    static class ViewHolder{
        private ImageView iv_vido_list_item;
        private TextView tv_name_vido_list_item;
        private TextView tv_time_vido_list_item;
    }
}
