package com.guigu.mytime.Store.warspagers;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.guigu.mytime.R;
import com.guigu.mytime.Store.ShowAllGridView;
import com.guigu.mytime.Store.bean.StoreBean;
import com.guigu.mytime.Store.encapsulation;

import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.List;

/**
 * Created by Administrator on 2016/3/4.
 * StarWarsPage的页面
 */
public class StarWarsPage extends BaseStarWarsPage {

    @ViewInject(R.id.iv_startwars_bgimg)
    private ImageView iv_startwars_bgimg;

    @ViewInject(R.id.tv_starwars_titleEn)
    private TextView tv_starwars_titleEn;

    @ViewInject(R.id.tv_starwars_titleCn)
    private TextView tv_starwars_titleCn;

    @ViewInject(R.id.gv_star_wars)
    private ShowAllGridView gv_star_wars;

    /**
     * 当前页面的数据
     */
    private StoreBean.TopicEntity mTopicEntity;

    private com.guigu.mytime.Store.encapsulation encapsulation;

    public StarWarsPage(Context context ,StoreBean.TopicEntity topicEntity) {

        super(context);
        mTopicEntity = topicEntity;
        setContentView(inflateView());

        encapsulation = new encapsulation();
    }

    private View inflateView() {
        View view = View.inflate(mContext, R.layout.store_page_star_wars, null);
        x.view().inject(this,view);
        return view;
    }

    @Override
    public void initData() {
        //1.背景
        encapsulation.bind(iv_startwars_bgimg, mTopicEntity.getBackgroupImage());
        //2.标题
        tv_starwars_titleEn.setText(mTopicEntity.getTitleEn());
        tv_starwars_titleCn.setText(mTopicEntity.getTitleCn());
        //3.GridView设置adapter
        gv_star_wars.setAdapter(new GridViewAdapter(mTopicEntity.getSubList()));

        //4.设置监听 : URL 为空
        gv_star_wars.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(mContext, mTopicEntity.getSubList().get(position).getUrl()
                        , Toast.LENGTH_SHORT).show();

            }
        });

    }

    class GridViewAdapter extends BaseAdapter{

        private List<StoreBean.TopicEntity.SubListEntity> mSubList;
        private LayoutInflater mInflater;


        public GridViewAdapter(List<StoreBean.TopicEntity.SubListEntity> subList) {
            mSubList = subList;
            mInflater = LayoutInflater.from(mContext);
        }

        @Override
        public int getCount() {
            if (mSubList != null)
                return mSubList.size();
            return 0;
        }

        @Override
        public Object getItem(int position) {
            if (mSubList != null) {
                return mSubList.get(position);
            }
            return null;
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
                convertView = mInflater.inflate(R.layout.store_starwar_grid_view_item, parent, false);
                holder.icon = (ImageView) convertView.findViewById(R.id.iv_starwars_item_icon);
                holder.titleTV = (TextView) convertView.findViewById(R.id.tv_startwars_item_title);
                holder.priceTV = (TextView) convertView.findViewById(R.id.tv_startwars_item_price);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }
            StoreBean.TopicEntity.SubListEntity entity = mSubList.get(position);
            holder.titleTV.setText(entity.getTitle());
            encapsulation.bind(holder.icon,entity.getImage());
            return convertView;
        }
    }

    static class ViewHolder {
        ImageView icon;
        TextView titleTV;
        TextView priceTV;
    }

}
