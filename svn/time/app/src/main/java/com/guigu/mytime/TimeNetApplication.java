package com.guigu.mytime;

import android.app.Application;

import com.baidu.location.LocationClient;
import com.guigu.mytime.volley.VolleyManager;

import org.xutils.x;

import cn.jpush.android.api.JPushInterface;

/**
 * Created by Administrator on 2016/2/29.
 */
public class TimeNetApplication extends Application {
    public LocationClient mLocationClient;
    @Override
    public void onCreate() {
        super.onCreate();
        //VolleyManager工具类的初始化

        JPushInterface.setDebugMode(true); 	// 设置开启日志,发布时请关闭日志
        JPushInterface.init(this);     		// 初始化 JPush

        VolleyManager.init(this);
        x.Ext.init(this);
        x.Ext.setDebug(true);

        mLocationClient = new LocationClient(this.getApplicationContext());
    }
}
