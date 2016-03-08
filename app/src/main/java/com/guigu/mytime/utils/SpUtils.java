package com.guigu.mytime.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by zpj on 2016/1/9.
 */
public class SpUtils {

    public static final String CHOOSED_CITY_NAME = "choosed_city_name";
    public static final String CHOOSED_CITY_ID = "choosed_city_id";
    public static final String CURRENT_LOCATION_CITYNAME = "CURRENT_LOCATION_CITYNAME";
    public static String CURRENT_LOCATION_LONGITUDE = "current_location_longitude";
    public static String CURRENT_LOCATION_LATITUDE = "current_location_latitude";

    /**
     * 保存软件参数
     * @param context
     * @param key
     * @param value
     */
    public static  void putBoolean(Context context,String key,boolean value){
        SharedPreferences sp = context.getSharedPreferences("atguigu",Context.MODE_PRIVATE);
        sp.edit().putBoolean(key,value).commit();
    }

    /**
     * 得到软件保存的参数
     * @param context
     * @param key
     * @return
     */
    public static  boolean getBoolean(Context context,String key){
        SharedPreferences sp = context.getSharedPreferences("atguigu",Context.MODE_PRIVATE);
        return  sp.getBoolean(key,false);
    }

    /**
     * 保存软件的参数
     * @param context
     * @param key
     * @param values
     * @return
     */
    public static void putInt(Context context,String key,int values){
        SharedPreferences sp = context.getSharedPreferences("atguigu",context.MODE_PRIVATE);
        sp.edit().putInt(key,values).commit();
    }

    /**
     * int保存
     * @param context
     * @param key
     * @return
     */
    public static  int getInt(Context context,String key){
        SharedPreferences sp = context.getSharedPreferences("atguigu",Context.MODE_PRIVATE);
        return  sp.getInt(key,-1);
    }
    //读取int型数据
    public static int getInt(Context context, String key, int defValue) {
        SharedPreferences sp = context.getSharedPreferences("config", Context.MODE_PRIVATE);
        return sp.getInt(key, defValue);
    }

    //存入boolean数据
    public static void saveBoolean(Context context, String key, boolean value) {
        SharedPreferences sp = context.getSharedPreferences("config", Context.MODE_PRIVATE);
        sp.edit().putBoolean(key, value).commit();
    }

    //读取boolean型数据
    public static boolean getBoolean(Context context, String key, boolean defValue) {
        SharedPreferences sp = context.getSharedPreferences("config", Context.MODE_PRIVATE);
        return sp.getBoolean(key, defValue);
    }

    //存入String数据
    public static void saveString(Context context, String key, String value) {
        SharedPreferences sp = context.getSharedPreferences("config", Context.MODE_PRIVATE);
        sp.edit().putString(key, value).commit();
    }

    //读取String型数据
    public static String getString(Context context, String key, String defValue) {
        SharedPreferences sp = context.getSharedPreferences("config", Context.MODE_PRIVATE);
        return sp.getString(key, defValue);
    }

    /**
     * 数据缓存方法
     * @param context
     * @param key
     * @param value
     */
    public static  void putString(Context context,String key,String value){
        SharedPreferences sp = context.getSharedPreferences("atguigu",Context.MODE_PRIVATE);
        sp.edit().putString(key,value).commit();
    }

    /**
     * 得到软件缓存的数据
     * @param context
     * @param key
     * @return
     */
    public static  String getString(Context context,String key){
        SharedPreferences sp = context.getSharedPreferences("atguigu",Context.MODE_PRIVATE);
        return  sp.getString(key,"");
    }
}
