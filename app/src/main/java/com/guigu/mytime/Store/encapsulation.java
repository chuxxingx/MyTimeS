package com.guigu.mytime.Store;

import android.widget.ImageView;


import com.guigu.mytime.R;

import org.xutils.common.util.DensityUtil;
import org.xutils.image.ImageOptions;
import org.xutils.x;

/**
 * Created by Administrator on 2016/2/29.
 * 封装
 */
public class encapsulation {

    private ImageOptions mOptions;



    /**
     * @param width  你希望图片的宽度  dp
     * @param height 你希望图片的高度  dp
     * @param radius 图片圆角        dp
     * @param corp   如果ImageView的大小不是定义为wrap_content, crop=false.
     */
    public encapsulation(int width, int height, int radius, boolean corp) {
        mOptions = new ImageOptions.Builder()
                .setSize(DensityUtil.dip2px(width), DensityUtil.dip2px(height))
                .setRadius(DensityUtil.dip2px(radius))
                        // 如果ImageView的大小不是定义为wrap_content, 不要crop.
                .setCrop(corp)
                        // 加载中或错误图片的ScaleType
                .setFailureDrawableId(R.drawable.img_default_90x90)
                .setLoadingDrawableId(R.drawable.img_default_90x90)
                        //.setPlaceholderScaleType(ImageView.ScaleType.MATRIX)
                .setImageScaleType(ImageView.ScaleType.CENTER_CROP)
                .build();
    }

    public encapsulation(int width, int height) {
        this(width, height, 0, false);
    }


    public encapsulation() {
        this(0, 0, 0, false);
    }

    public encapsulation(boolean corp) {
        this(0, 0, 0, corp);
    }

    /**
     * 请求图片
     *
     * @param imageView ImageView
     * @param url       请求的地址
     */
    public void bind(ImageView imageView, String url) {
        x.image().bind(imageView, url, mOptions);
    }

}
