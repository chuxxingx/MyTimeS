package com.guigu.mytime.locat.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.guigu.mytime.R;
import com.guigu.mytime.locat.bean.LoactionCityBean;

import java.util.List;

/**
 * Created by Steve on 2016/1/15.
 */
public class GridItemAdapter extends BaseAdapter {
    private Context mContext;
    private List<LoactionCityBean.City> hotCitys;
    private LayoutInflater mInflater;
    public GridItemAdapter(Context context, List<LoactionCityBean.City> citys) {
        mContext = context;
        hotCitys = citys;
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return hotCitys.size();
    }

    @Override
    public Object getItem(int position) {
        return hotCitys.get(position);
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
            convertView = View.inflate(mContext, R.layout.gv_hot_city_item, null);
            holder.textView = (TextView) convertView.findViewById(R.id.tv_city_name);

            convertView.setTag(holder);
        }else{
            holder = (ViewHolder) convertView.getTag();
        }
        String cityName = hotCitys.get(position).getN();
        holder.textView.setText(hotCitys.get(position).getN());

        return convertView;
    }
    static class ViewHolder{
        TextView textView;
    }
}
