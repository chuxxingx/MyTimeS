package com.guigu.mytime.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Administrator on 2016/2/29.
 */
public class CacheUtils {

    public static String CURRENT_LOCATION_LONGITUDE = "current_location_longitude";
    public static String CURRENT_LOCATION_LATITUDE = "current_location_latitude";

    /**
     * 缓存
     */
    private static SharedPreferences sp;

    public static void putBoolean(Context context,String key,boolean value){
        if(sp == null) {
            sp = context.getSharedPreferences("atguigu",Context.MODE_PRIVATE);
        }
        sp.edit().putBoolean(key,value).commit();
    }

    public static boolean getBoolean(Context context,String key){
        if(sp == null) {
            sp = context.getSharedPreferences("atguigu",Context.MODE_PRIVATE);
        }
        return sp.getBoolean(key,false);
    }

    /**
     * 设置String类型的软件参数
     * @param context
     * @param key
     * @param value
     * @author Wangnan
     */
    public static void setString(Context context, String key, String value){
        if(sp==null){
            sp = context.getSharedPreferences("config", Context.MODE_PRIVATE);
        }
        sp.edit().putString(key, value).commit();
    }

    public static void putInt(Context context,String key,int value){
        if(sp == null) {
            sp = context.getSharedPreferences("atguigu",Context.MODE_PRIVATE);
        }
        sp.edit().putInt(key,value).commit();
    }

    public static int getInt(Context context,String key){
        if(sp == null) {
            sp = context.getSharedPreferences("atguigu",Context.MODE_PRIVATE);
        }
        return sp.getInt(key, 0);
    }

    /**
     * 数据缓存方法
     * @param context
     * @param key
     * @param value
     */
    public static  void putString(Context context,String key,String value){
        if(sp == null) {
            SharedPreferences sp = context.getSharedPreferences("atguigu",Context.MODE_PRIVATE);
        }
        sp.edit().putString(key, value).commit();
    }

    /**
     * 得到软件缓存的数据
     * @param context
     * @param key
     * @return
     */
    public static  String getString(Context context,String key){
        if(sp == null) {
            SharedPreferences sp = context.getSharedPreferences("atguigu",Context.MODE_PRIVATE);
        }
        return  sp.getString(key, "");
    }

    public static String getString1(Context context, String key, String defValue) {
        if(sp == null) {
            SharedPreferences sp = context.getSharedPreferences("atguigu",Context.MODE_PRIVATE);
        }
        return  sp.getString(key, defValue);
    }
}
