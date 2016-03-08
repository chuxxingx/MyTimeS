package com.guigu.mytime.Store.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.guigu.mytime.Store.bean.MyViewPager;
import com.guigu.mytime.Store.bean.StoreBean;
import com.guigu.mytime.Store.encapsulation;
import com.guigu.mytime.webview.MovieDetailActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/3/1.
 * 顶部轮播图适配器
 */
public class StoreScrollImgAdapter extends PagerAdapter {

    /**
     * 数据
     */
    private List<StoreBean.ScrollImgEntity> mScrollImgs;

    /**
     * 布局加载器
     */
    private LayoutInflater mInflater;

    /**
     * 该适配器的ViewPager
     */
    private ViewPager mViewPager;

    /**
     * 轮播图的数据集合
     */
    private ArrayList<ImageView> mImageViews;

    /**
     * 上下文
     */
    private Context mContext;

    private int initPosition;

    private com.guigu.mytime.Store.encapsulation encapsulation;


    public StoreScrollImgAdapter(Context context, List<StoreBean.ScrollImgEntity> scrollImgs) {
        mContext = context;
        mScrollImgs = scrollImgs;
        mInflater = LayoutInflater.from(context);

        mImageViews = getImageViews();//初始化轮播图的页数

        encapsulation = new encapsulation();
    }

    /**
     * 获取轮播图的页数
     *
     * @return 集合
     */
    private ArrayList<ImageView> getImageViews() {
        ArrayList<ImageView> list = new ArrayList<>();
        int count = mScrollImgs.size();
        for (int i = 0; i < count; i++) {
            ImageView imageView = new ImageView(mContext);
            list.add(imageView);
        }
        return list;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        final int realPosition = position % mScrollImgs.size();

        MyViewPager vp = (MyViewPager) container;

        ImageView imageView = mImageViews.get(realPosition);
        imageView.setScaleType(ImageView.ScaleType.FIT_XY);//该方法能让图片充满ViewPager
//        ImageUtils.loadImage(imageView, mScrollImgs.get(realPosition).getImage());
        encapsulation.bind(imageView, mScrollImgs.get(realPosition).getImage());

        if (mScrollImgs.size() < 4 && vp.isToRight) {
            container.removeView(imageView);
        }
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = mScrollImgs.get(realPosition).getUrl();
                Toast.makeText(mContext, url, Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(mContext, MovieDetailActivity.class);
                intent.putExtra("urlDetail", url);
                mContext.startActivity(intent);
            }
        });
        container.addView(imageView);
        return imageView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {

        MyViewPager vp = (MyViewPager) container;
        if (mScrollImgs.size() < 4 && vp.isToRight) {
            return;
        }
        ImageView imageView = (ImageView) object;
        container.removeView(imageView);
    }

    @Override
    public int getCount() {
        return Integer.MAX_VALUE;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }
}
