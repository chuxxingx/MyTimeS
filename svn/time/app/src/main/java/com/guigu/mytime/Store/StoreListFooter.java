package com.guigu.mytime.Store;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.guigu.mytime.R;
import com.guigu.mytime.Store.bean.StoreGoodsBean;
import com.guigu.mytime.volley.VolleyManager;

import org.xutils.common.util.DensityUtil;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.List;


/**
 * Created by Administrator on 2016/2/29.
 * 底部视图
 */
public class StoreListFooter {

    private Context mContext;
    private StoreGoodsBean mGoodsBean;
    private LayoutInflater mInflater;

    private List<StoreGoodsBean.GoodsListEntity> mList;

    private encapsulation fz;
    private GoodsAdapter mFootAdapter;
    private View rootView;

    @ViewInject(R.id.showAll_gridView)
    private ShowAllGridView showAll_gridView;

    public View getRootView() {
        return rootView;
    }

    public StoreListFooter(Context context, StoreGoodsBean mGoodsBean, LayoutInflater mInflater) {
        mContext = context;
        this.mGoodsBean = mGoodsBean;
        mList = mGoodsBean.getGoodsList();//数据的集合
        this.mInflater = mInflater;

        rootView = initView();

        int width = DensityUtil.px2dip(VolleyManager.screenWidth / 2);
        fz = new encapsulation(90, 90, 0, true);

    }

    private View initView() {
        View view = mInflater.inflate(R.layout.store_list_footview, null);
        x.view().inject(this, view);
        return view;
    }

    /**
     * 获取底部视图的adapter
     *
     * @return
     */
    public GoodsAdapter getFootAdapter() {
        return mFootAdapter;
    }

    public void initData() {
        mFootAdapter = new GoodsAdapter();
        showAll_gridView.setAdapter(mFootAdapter);
    }

    //新页面加载到集合中
    public void addNewPager(StoreGoodsBean bean) {
        if(bean != null) {

        } List<StoreGoodsBean.GoodsListEntity> list = bean.getGoodsList();
        if (list != null && !list.isEmpty()) {
            mList.addAll(list);
            mFootAdapter.notifyDataSetChanged();
        }
    }


    class GoodsAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return mList.size();
        }

        @Override
        public Object getItem(int position) {
            return mList.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder;
            if (convertView == null) {
                holder = new ViewHolder();
                convertView = mInflater.inflate(R.layout.store_footview_item, parent, false);
                holder.iconText = (TextView) convertView.findViewById(R.id.tv_store_icontext);
                holder.icon = (ImageView) convertView.findViewById(R.id.iv_store_goods_icon);
                holder.nameTV = (TextView) convertView.findViewById(R.id.tv_store_goods_name);
                holder.priceTV = (TextView) convertView.findViewById(R.id.tv_store_goods_price);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }
            StoreGoodsBean.GoodsListEntity entity = (StoreGoodsBean.GoodsListEntity) getItem(position);

            holder.nameTV.setText(entity.getName());
            String iconText = entity.getIconText();
            if (iconText != null && !"".equals(iconText)) {
                String color = entity.getBackground();
                holder.iconText.setBackgroundColor(Color.parseColor(color));
                holder.iconText.setText(iconText);
            }
            fz.bind(holder.icon, entity.getImage());
            String price = "￥" + (float) entity.getMinSalePrice() / 100;
            holder.priceTV.setText(price);
            return convertView;
        }
    }

    static class ViewHolder {
        TextView iconText;
        ImageView icon;
        TextView nameTV;
        TextView priceTV;
    }

}
