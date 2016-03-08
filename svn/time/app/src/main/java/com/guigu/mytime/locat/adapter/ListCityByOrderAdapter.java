package com.guigu.mytime.locat.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.guigu.mytime.R;
import com.guigu.mytime.locat.bean.LoactionCityBean;

import java.util.List;

/**
 * Created by Steve on 2016/1/14.
 */
public class ListCityByOrderAdapter extends BaseAdapter {
    private  Context context;
    private List<LoactionCityBean.City> orderdCitys;


    public ListCityByOrderAdapter(Context context, List<LoactionCityBean.City> orderdCitys) {
        this.context = context;
        this.orderdCitys = orderdCitys;
    }

    @Override
    public int getCount() {
        return orderdCitys.size();
    }

    @Override
    public Object getItem(int position) {
        return orderdCitys.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if(convertView == null){
            holder = new ViewHolder();
            convertView = View.inflate(context, R.layout.city_order_item, null);
            holder.textView = (TextView) convertView.findViewById(R.id.tv_city_item);
            convertView.setTag(holder);
        }else{
            holder = (ViewHolder) convertView.getTag();
        }

        holder.textView.setText(orderdCitys.get(position).getN());

        return convertView;
    }
    static class ViewHolder{
        TextView textView;
    }
}
