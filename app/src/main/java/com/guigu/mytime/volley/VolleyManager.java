package com.guigu.mytime.volley;

import android.content.Context;
import android.view.WindowManager;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;


/**
 * Volley 框架的封装
 * Created by weisong on 2016/1/11.
 */
public class VolleyManager {

    /**
     * Volley框架的请求队列
     */
    private static RequestQueue mQueue;

    /**
     * 图片加载器
     */
    private static ImageLoader mImageLoader;

    /**
     * 屏幕的宽度
     */
    public static int screenWidth;

    /**
     * 初始化VolleyManager
     * @param context 上下文
     */
    public static void init(Context context) {
        screenWidth = ((WindowManager) context.getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay()
                .getWidth();
        if (mQueue == null) {
            mQueue = Volley.newRequestQueue(context);
        }
        if (mImageLoader == null) {
            //图片缓存为app最大内存的1/8
            int maxSize = (int) (Runtime.getRuntime().maxMemory() / 1024) / 8;
            mImageLoader = new ImageLoader(mQueue, new LruImageCache(maxSize));
        }
    }

    /**
     * 获取Volley请求队列
     * @return
     */
    public static RequestQueue getRequestQueue() {
        if (mQueue == null) {
            throw new RuntimeException("VolleyManager 没有被初始化，请在Application中进行初始化");
        }
        return mQueue;
    }

    /**
     * 获取Volley中的ImageLoader
     * @return
     */
    public static ImageLoader getImageLoader(){
        if (mQueue == null) {
            throw new RuntimeException("VolleyManager 没有被初始化，请在Application中进行初始化");
        }
        return mImageLoader;
    }

    /**
     * 将请求添加到队列，如果VolleyManager 没有被初始化，会抛出异常
     * @param request 您的请求
     */
    public static void add(Request<?> request){
        if (mQueue == null) {
            throw new RuntimeException("VolleyManager 没有被初始化，请在Application中进行初始化");
        }
        mQueue.add(request);
    }
}
