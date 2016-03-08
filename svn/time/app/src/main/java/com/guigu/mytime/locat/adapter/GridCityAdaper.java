package com.guigu.mytime.locat.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.guigu.mytime.R;
import com.guigu.mytime.locat.sortlist.SortModel;

import java.util.List;

/**
 * Created by Steve on 2016/1/15.
 */
public class GridCityAdaper extends BaseAdapter {
    private List<SortModel> list = null;
    private Context mContext;

    public GridCityAdaper(Context mContext, List<SortModel> list) {
        this.mContext = mContext;
        this.list = list;
    }

    public int getCount() {
        return this.list.size();
    }

    public Object getItem(int position) {
        return list.get(position);
    }

    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        final SortModel mContent = list.get(position);
        if(convertView == null){
            holder = new ViewHolder();
            convertView = View.inflate(mContext, R.layout.gv_order_city_item, null);
            holder.textView = (TextView) convertView.findViewById(R.id.tv_city_name);
            convertView.setTag(holder);
        }else{
            holder = (ViewHolder) convertView.getTag();
        }

        holder.textView.setText(this.list.get(position).getName());

        return convertView;
    }
    static class ViewHolder{
        TextView textView;
    }
}
