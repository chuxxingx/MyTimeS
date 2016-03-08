package com.guigu.mytime.Store.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.guigu.mytime.R;
import com.guigu.mytime.Store.bean.StoreBean;

import org.xutils.image.ImageOptions;
import org.xutils.x;

import java.util.List;

/**
 * Created by Administrator on 2016/3/4.
 */
public class StoreIndicatorAdapter extends BaseAdapter {
    private final ImageOptions mOptions;
    private Context mContext;

    private LayoutInflater mInflater;

    private List<StoreBean.TopicEntity> mTopic;

    /**
     * 当前选中的位置
     */
    private int selectorPosition = 0;

    private int prePosition;

    public void setSelectorPosition(int newPosition) {
        if (this.selectorPosition != newPosition) {
            prePosition = this.selectorPosition;
            this.selectorPosition = newPosition;
            notifyDataSetChanged();
        }
    }


    public StoreIndicatorAdapter(Context mContext, LayoutInflater mInflater, List<StoreBean.TopicEntity> topic) {
        this.mContext = mContext;
        this.mInflater = mInflater;
        mTopic = topic;

        mOptions = new ImageOptions.Builder()
                .setImageScaleType(ImageView.ScaleType.CENTER_CROP)
                .build();

    }

    @Override
    public int getCount() {
        if (mTopic != null)
            return mTopic.size();
        return 0;
    }

    @Override
    public Object getItem(int position) {
        if (mTopic != null)
            return mTopic.get(position);
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
            convertView = mInflater.inflate(R.layout.store_list_indicator_item, parent, false);
            holder.imageView = (ImageView) convertView.findViewById(R.id.iv_store_indicator_icon);
            holder.imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        StoreBean.TopicEntity entity = mTopic.get(position);

        x.image().bind(holder.imageView, entity.getCheckedImage());

        return convertView;
    }
    static class ViewHolder {
        ImageView imageView;
    }
}
