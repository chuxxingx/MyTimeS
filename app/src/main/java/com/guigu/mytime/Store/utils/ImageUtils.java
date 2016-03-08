package com.guigu.mytime.Store.utils;

import android.graphics.Bitmap;

import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.guigu.mytime.volley.VolleyManager;

import org.xutils.common.util.DensityUtil;

/**
 * Created by Administrator on 2016/3/3.
 * 图片加载
 */
public class ImageUtils {
    private static String preurl;
    public static void getBitmap(String url, final OnBitmapLoadlistener listener, int with,int height) {
        ImageLoader imageLoader = VolleyManager.getImageLoader();
        ImageLoader.ImageListener imageListener = new ImageLoader.ImageListener() {
            @Override
            public void onResponse(ImageLoader.ImageContainer imageContainer, boolean b) {
                Bitmap bitmap = imageContainer.getBitmap();
                if (bitmap != null && listener != null) {
                    listener.onBitmapLoad(bitmap);
                }
            }
            @Override
            public void onErrorResponse(VolleyError volleyError) {
            }
        };
        imageLoader.get(url, imageListener, with, DensityUtil.dip2px(height));
    }
    public static void getBitmap(String url, final OnBitmapLoadlistener listener){
        getBitmap(url,listener,0,0);
    }

    public interface OnBitmapLoadlistener {

        void onBitmapLoad(Bitmap bitmap);
    }
}
