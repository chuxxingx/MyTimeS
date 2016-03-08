package com.guigu.mytime.buyticket.utils;

import android.app.Activity;
import android.util.Log;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.guigu.mytime.TimeNetApplication;

/**
 * Created by Administrator on 2016/3/2.
 */
public class LocationUtil {
    private static final String TAG = LocationUtil.class.getSimpleName();
    private Activity activity;
    private LocationClientOption option;
    public LocationClient mLocationClient;
    private MyLocationListener locationlistener;
    private String city = "";

    public LocationUtil(Activity activity) {
        this.activity = activity;
        initLocation();
    }


    /**
     * 初始化定位功能
     */
    private void initLocation() {
//        mLocationClient=new LocationClient(this);
        mLocationClient = ((TimeNetApplication) activity.getApplication()).mLocationClient;

        locationlistener = new MyLocationListener();
        mLocationClient.registerLocationListener(locationlistener);

        option = new LocationClientOption();
        option.setLocationMode(LocationClientOption.LocationMode.Hight_Accuracy
        );//可选，默认高精度，设置定位模式，高精度，低功耗，仅设备
//        option.setCoorType("bd09ll");//可选，默认gcj02，设置返回的定位结果坐标系
        int span = 10;
        option.setScanSpan(span);//可选，默认0，即仅定位一次，设置发起定位请求的间隔需要大于等于1000ms才是有效的
        option.setIsNeedAddress(true);//可选，设置是否需要地址信息，默认不需要
        option.setOpenGps(true);//可选，默认false,设置是否使用gps
        option.setLocationNotify(true);//可选，默认false，设置是否当gps有效时按照1S1次频率输出GPS结果
        option.setIsNeedLocationDescribe(true);//可选，默认false，设置是否需要位置语义化结果，可以在BDLocation.getLocationDescribe里得到，结果类似于“在北京天安门附近”
        option.setIsNeedLocationPoiList(true);//可选，默认false，设置是否需要POI结果，可以在BDLocation.getPoiList里得到
        option.setIgnoreKillProcess(false);//可选，默认false，定位SDK内部是一个SERVICE，并放到了独立进程，设置是否在stop的时候杀死这个进程，默认杀死
        option.SetIgnoreCacheException(false);//可选，默认false，设置是否收集CRASH信息，默认收集
        option.setEnableSimulateGps(false);//可选，默认false，设置是否需要过滤gps仿真结果，默认需要
        mLocationClient.setLocOption(option);

        mLocationClient.start();

    }


    public class MyLocationListener implements BDLocationListener {

        @Override
        public void onReceiveLocation(BDLocation bdLocation) {
//            Log.e("TAG", "bdLocation.getLocType()=****" + bdLocation.getLocType());//
            if (bdLocation.getLocType() == BDLocation.TypeGpsLocation) {// GPS定位结果
                String addrStr = bdLocation.getAddrStr();
                city = bdLocation.getCity();
                double latitudeD = bdLocation.getLatitude();
                double longitudeD = bdLocation.getLongitude();
                int latitude = (int) (latitudeD * 1000000);
                int longitude = (int) (longitudeD * 1000000);

//                Toast.makeText(activity, "GPS定位成功", Toast.LENGTH_SHORT).show();
            } else if (bdLocation.getLocType() == BDLocation.TypeNetWorkLocation) {// 网络定位结果
                String addrStr = bdLocation.getAddrStr();
                city = bdLocation.getCity();
                double latitudeD = bdLocation.getLatitude();
                double longitudeD = bdLocation.getLongitude();
                int latitude = (int) (latitudeD * 1000000);
                int longitude = (int) (longitudeD * 1000000);
//                Toast.makeText(activity, "网络定位成功", Toast.LENGTH_SHORT).show();
            } else if (bdLocation.getLocType() == BDLocation.TypeOffLineLocation) {// 离线定位结果
//                Toast.makeText(activity, "离线定位成功", Toast.LENGTH_SHORT).show();
            }
            Log.e(TAG, "纬度:" + bdLocation.getLatitude() + ",经度:" + bdLocation.getLongitude());
            onReceiveCityNameListener.OnReceiveCityName(bdLocation);
            int locType1 = bdLocation.getLocType();

        }


    }

    private OnReceiveCityNameListener onReceiveCityNameListener;

    public void setOnReceiveCityNameListener(OnReceiveCityNameListener onReceiveCityNameListener) {
        this.onReceiveCityNameListener = onReceiveCityNameListener;
    }

    public interface OnReceiveCityNameListener {
        public abstract void OnReceiveCityName(BDLocation bDLocation);
    }


    private static final  double EARTH_RADIUS = 6378137;//赤道半径(单位m)
    //根据经纬度计算两点距离,单位米
    public static double getDistance(double lon1, double lat1, double lon2, double lat2) {
        double radLat1 = rad(lat1);
        double radLat2 = rad(lat2);
        double a = radLat1 - radLat2;
        double b = rad(lon1) - rad(lon2);
        double s = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(a / 2), 2) + Math.cos(radLat1) * Math.cos(radLat2) * Math.pow(Math.sin(b / 2), 2)));
        s = s * EARTH_RADIUS;
        return s;
    }

    private static double rad(double d)
    {
        return d * Math.PI / 180.0;
    }


}
