package com.guigu.mytime.Store.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.guigu.mytime.R;
import com.guigu.mytime.Store.bean.StoreBean;
import com.guigu.mytime.Store.encapsulation;
import com.guigu.mytime.volley.VolleyManager;

import java.util.List;

/**
 * Created by Administrator on 2016/3/2.
 * 商城的ListView的适配器
 */
public class StoreListAdapter extends BaseAdapter{
    private Context mContext;
    private LayoutInflater mInflater;
    private List<StoreBean.CategoryEntity> mData;
    private com.guigu.mytime.Store.encapsulation encapsulation;

    public StoreListAdapter(Context context, List<StoreBean.CategoryEntity> category) {
        mContext = context;
        mData = category;
        mInflater = LayoutInflater.from(context);
        encapsulation = new encapsulation(VolleyManager.screenWidth, 200);
    }


    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public Object getItem(int position) {
        return mData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if(convertView == null) {
            viewHolder = new ViewHolder();
            convertView = mInflater.inflate(R.layout.store_list_item, parent, false);
            viewHolder.iv_color = (ImageView) convertView.findViewById(R.id.iv_color);
            viewHolder.iv_catagory_top = (ImageView) convertView.findViewById(R.id.iv_catagory_top);
            viewHolder.iv_catagory_sublist0 = (ImageView) convertView.findViewById(R.id.iv_catagory_sublist0);
            viewHolder.iv_catagory_sublist1 = (ImageView) convertView.findViewById(R.id.iv_catagory_sublist1);
            viewHolder.iv_catagory_sublist2 = (ImageView) convertView.findViewById(R.id.iv_catagory_sublist2);
            viewHolder.tv_catagory_more = (TextView) convertView.findViewById(R.id.tv_catagory_more);

            viewHolder.tv_catagory_name = (TextView) convertView.findViewById(R.id.tv_catagory_name);
            viewHolder.tv_catagory_sublist0_title = (TextView) convertView.findViewById(R.id.tv_catagory_sublist0_title);
            viewHolder.tv_catagory_sublist1_title = (TextView) convertView.findViewById(R.id.tv_catagory_sublist1_title);
            viewHolder.tv_catagory_sublist2_title = (TextView) convertView.findViewById(R.id.tv_catagory_sublist2_title);
            viewHolder.tv_catagory_sublist0_price = (TextView) convertView.findViewById(R.id.tv_catagory_sublist0_price);
            viewHolder.tv_catagory_sublist1_price = (TextView) convertView.findViewById(R.id.tv_catagory_sublist1_price);
            viewHolder.tv_catagory_sublist2_price = (TextView) convertView.findViewById(R.id.tv_catagory_sublist2_price);

            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) convertView.getTag();

        }

        StoreBean.CategoryEntity data = mData.get(position);
        List<StoreBean.CategoryEntity.SubListEntity> subList = data.getSubList();


        encapsulation.bind(viewHolder.iv_catagory_top,data.getImage());
        encapsulation.bind(viewHolder.iv_catagory_sublist0, subList.get(0).getImage());
        encapsulation.bind(viewHolder.iv_catagory_sublist1,subList.get(1).getImage());
        encapsulation.bind(viewHolder.iv_catagory_sublist2, subList.get(2).getImage());


        return convertView;
    }

    class ViewHolder{

        ImageView iv_color;//颜色
        ImageView iv_catagory_top;//上边大图
        ImageView iv_catagory_sublist0;//下面小图0
        ImageView iv_catagory_sublist1;//下面小图1
        ImageView iv_catagory_sublist2;//下面小图2

        TextView tv_catagory_name;//item类型，标题
        TextView tv_catagory_more;//更多
        TextView tv_catagory_sublist0_title;//sublist0_title
        TextView tv_catagory_sublist1_title;//sublist1_title
        TextView tv_catagory_sublist2_title;//sublist2_title
        TextView tv_catagory_sublist0_price;//sublist0_price
        TextView tv_catagory_sublist1_price;//sublist1_price
        TextView tv_catagory_sublist2_price;//sublist2_price


    }
}
