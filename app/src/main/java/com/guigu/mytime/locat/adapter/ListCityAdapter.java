package com.guigu.mytime.locat.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.TextView;

import com.guigu.mytime.MainActivity;
import com.guigu.mytime.R;
import com.guigu.mytime.locat.LocationActivity;
import com.guigu.mytime.locat.sortlist.SortModel;
import com.guigu.mytime.utils.CacheUtils;
import com.guigu.mytime.utils.SpUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Steve on 2016/1/15.
 */
public class ListCityAdapter extends BaseAdapter {
    private static final String TAG = ListCityAdapter.class.getSimpleName();
    private Context mContext;
    private List<SortModel> gSourceDateList;
    private List<SortModel> list;
    private ArrayList<Integer> posRec;

    public ListCityAdapter(Context context, List<SortModel> gSourceDateList1, ArrayList<Integer> posRec1) {
        mContext = context;
        this.gSourceDateList = gSourceDateList1;
        this.posRec = posRec1;
    }

    @Override
    public int getCount() {
        return posRec.size();
    }

    @Override
    public Object getItem(int position) {
        return gSourceDateList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = View.inflate(mContext, R.layout.lv_city_default_item, null);
            holder.tv_letter = (TextView) convertView.findViewById(R.id.tv_letter);
            holder.gv_city_name = (GridView) convertView.findViewById(R.id.gv_city_default);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }


        holder.tv_letter.setText(gSourceDateList.get(posRec.get(position)).getSortLetters());
        //listView嵌套gridview时在最后一个的前一个，将所有的城市信息获取到之后显示
        list = new ArrayList<>();

        int start, end;

        start = posRec.get(position) + 1;
        if (position < 25) {
            end = posRec.get(position + 1);
        } else {
            end = gSourceDateList.size();
        }

        for (int i = start; i < end; i++) {
            list.add(gSourceDateList.get(i));
        }
        ListGridCityAdaper gridCityAdaper = new ListGridCityAdaper(mContext, list);
        holder.gv_city_name.setAdapter(gridCityAdaper);

        return convertView;
    }

    static class ViewHolder {
        TextView tv_letter;
        GridView gv_city_name;
    }
}

class ListGridCityAdaper extends BaseAdapter {
    private List<SortModel> list = null;
    private Context mContext;

    public ListGridCityAdaper(Context mContext, List<SortModel> list) {
        this.list = list;
        this.mContext = mContext;
    }

    public int getCount() {
        return list.size();
    }

    public Object getItem(int position) {
        return this.list.get(position);
    }

    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        final SortModel mContent = list.get(position);
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = View.inflate(mContext, R.layout.gv_order_city_item, null);
            holder.textView = (TextView) convertView.findViewById(R.id.tv_city_name);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        final String cityName = list.get(position).getName();
        final int cityId = list.get(position).getId();
        holder.textView.setText(cityName);
        holder.textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SpUtils.putString(mContext, LocationActivity.CITY_NAME, cityName);
                SpUtils.putInt(mContext, LocationActivity.CITY_ID, cityId);

                Intent intent = new Intent();
                intent.setAction(LocationActivity.LOCATION_ACTION);
                intent.putExtra(LocationActivity.CITY_NAME, cityName);
                intent.putExtra(LocationActivity.CITY_ID, cityId);

                mContext.sendBroadcast(intent);
                boolean isLocation = CacheUtils.getBoolean(mContext, "isLocation");
                if(!isLocation) {
                    mContext.startActivity(new Intent(mContext, MainActivity.class));
                }
                LocationActivity.locationActivity.finish();
            }
        });
        return convertView;
    }

    static class ViewHolder {
        TextView textView;
    }
}

