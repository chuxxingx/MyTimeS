package com.guigu.mytime.buyticket;

import android.app.Activity;
import android.app.AlertDialog;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.guigu.mytime.R;
import com.guigu.mytime.base.BasePager;
import com.guigu.mytime.buyticket.bean.CinemaBean;
import com.guigu.mytime.buyticket.utils.LocationUtil;
import com.guigu.mytime.utils.CacheUtils;
import com.guigu.mytime.utils.ConstantUtils;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by Administrator on 2016/2/29.
 */
public class Cinema extends BasePager {

    @ViewInject(R.id.rg_cinema_choose)
    private RadioGroup rg_cinema_choose;

    @ViewInject(R.id.rb_cinema_total)
    private RadioButton rb_cinema_total;

    @ViewInject(R.id.rb_cinema_near)
    private RadioButton rb_cinema_near;

    @ViewInject(R.id.rb_cinema_price)
    private RadioButton rb_cinema_price;

    @ViewInject(R.id.rb_cinema_screen)
    private RadioButton rb_cinema_screen;


    @ViewInject(R.id.iv_loading)
    private ImageView iv_loading;

    @ViewInject(R.id.lv_cinema_list)
    private ListView lv_cinema_list;

    private static String CINEMA_LIST_URI = "http://api.m.mtime.cn/OnlineLocationCinema/OnlineCinemasByCity.api?locationId=290&deviceToken={1}";
    private List<CinemaBean> rawCinemaBeans;
    private AnimationDrawable background;
    private List cinemaBeans;

    private int nearestCinemaPosition;
    private double currentLatitude;
    private double currentLongitude;
    private List imaxList;
    private List loveList;
    private List park;
    private List vipList;
    private List wifiList;
    private String json;
    private String[] list;

    public Cinema(Activity activity) {
        super(activity);
    }

    @Override
    public View initView() {
        View view = View.inflate(activity, R.layout.cinema_pager_view, null);
        x.view().inject(this, view);
        rg_cinema_choose.check(R.id.rb_cinema_total);//默认选中全部
        rg_cinema_choose.setOnCheckedChangeListener(new MyOnCheckedChangeListener());
        background = (AnimationDrawable) iv_loading.getBackground();
        background.start();
        return view;
    }

    @Override
    public void initData() {
        String string = CacheUtils.getString(activity, CINEMA_LIST_URI);
        if(!TextUtils.isEmpty(string)) {
            processData(string);
        }
        getDataFromNet();
    }

    private void getDataFromNet() {
        RequestParams params = new RequestParams(CINEMA_LIST_URI);
        x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                CacheUtils.putString(activity, CINEMA_LIST_URI, result);
                processData(result);
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {

            }

            @Override
            public void onCancelled(CancelledException cex) {
            }

            @Override
            public void onFinished() {

            }
        });
    }

    private void processData(String result) {
        json = result;
        cinemaBeans = getList(0);
        //动画结束
        background.stop();
        iv_loading.setVisibility(View.GONE);
        //获取所有的数据集合
        rawCinemaBeans = parseData(result);

        nearestCinemaPosition = 0;
        if (currentLatitude == 0) {
            currentLatitude = Double.parseDouble(CacheUtils.getString1(activity, CacheUtils.CURRENT_LOCATION_LATITUDE, "0"));
            currentLongitude = Double.parseDouble(CacheUtils.getString1(activity, CacheUtils.CURRENT_LOCATION_LONGITUDE, "0"));
        }
        for(int i = 0; i < rawCinemaBeans.size(); i++) {
            CinemaBean cinemaBean = rawCinemaBeans.get(i);
            CinemaBean nearCinemaBean = rawCinemaBeans.get(nearestCinemaPosition);
            double distance = LocationUtil.getDistance(currentLongitude, currentLatitude, cinemaBean.getLongitude(), cinemaBean.getLatitude());
            double nearDistance = LocationUtil.getDistance(currentLongitude, currentLatitude, nearCinemaBean.getLongitude(), nearCinemaBean.getLatitude());
            if (nearDistance > distance) {
                nearestCinemaPosition = i;
            }
            cinemaBeans.add(cinemaBean);
        }
        CinemaBean remove = (CinemaBean) cinemaBeans.remove(nearestCinemaPosition);
        cinemaBeans.add(0, remove);
        lv_cinema_list.setAdapter(mAdapter);

        //lv_cinema_list.setAdapter(new MyCinemaAdapter(activity,cinemaBean));
    }

    private List getList(int l) {
        if(l == 0) {
            cinemaBeans = new ArrayList();
            return cinemaBeans;
        }else if(l == 1) {
            imaxList = new ArrayList();
            return imaxList;
        }else if(l == 2) {
            loveList = new ArrayList();
            return loveList;
        }else if(l == 3) {
            park = new ArrayList();
            return park;
        }else if(l == 4) {
            vipList = new ArrayList();
            return vipList;
        }else if(l == 5) {
            wifiList = new ArrayList();
            return wifiList;
        }
        return null;
    }

    private List<CinemaBean> parseData(String result) {
        return new Gson().fromJson(result, new TypeToken<List<CinemaBean>>(){}.getType());
    }

    private BaseAdapter mAdapter = new BaseAdapter(){

        @Override
        public int getCount() {
            return cinemaBeans.size();
        }

        @Override
        public Object getItem(int position) {
            return cinemaBeans.get(position);
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder hodler = null;
            if(convertView == null) {
                convertView = View.inflate(activity,R.layout.item_cinema_list,null);
                hodler = new ViewHolder();
                hodler.iv_cinima_item_nearst_icon = (ImageView) convertView.findViewById(R.id.iv_cinima_item_nearest_icon);
                hodler.tv_cinema_name = (TextView) convertView.findViewById(R.id.tv_cinema_name);
                hodler.tv_cinema_address = (TextView) convertView.findViewById(R.id.tv_cinema_address);
                hodler.tv_cinema_distance = (TextView) convertView.findViewById(R.id.tv_cinema_distance);
                hodler.tv_cinemalist_price = (TextView) convertView.findViewById(R.id.tv_cinemalist_price);
                hodler.item_cinemalist_icon_3d = (ImageView) convertView.findViewById(R.id.item_cinemalist_icon_3d);
                hodler.item_cinemalist_icon_imax = (ImageView) convertView.findViewById(R.id.item_cinemalist_icon_imax);
                hodler.item_cinemalist_icon_kiosk = (ImageView) convertView.findViewById(R.id.item_cinemalist_icon_kiosk);
                hodler.item_cinemalist_icon_parking = (ImageView) convertView.findViewById(R.id.item_cinemalist_icon_parking);
                hodler.item_cinemalist_icon_vip = (ImageView) convertView.findViewById(R.id.item_cinemalist_icon_vip);
                hodler.item_cinemalist_icon_wififree = (ImageView) convertView.findViewById(R.id.item_cinemalist_icon_wififree);
                hodler.line = convertView.findViewById(R.id.line);
                convertView.setTag(hodler);
            }else {
                hodler = (ViewHolder) convertView.getTag();
            }

            if (position == 0 && rb_cinema_total.isChecked()) {
                hodler.iv_cinima_item_nearst_icon.setVisibility(View.VISIBLE);
            } else {
                hodler.iv_cinima_item_nearst_icon.setVisibility(View.GONE);
                hodler.line.setVisibility(View.GONE);
            }
            CinemaBean cinemaBean = (CinemaBean) cinemaBeans.get(position);
            //获取标题
            String cinameName = cinemaBean.getCinameName();
            hodler.tv_cinema_name.setText(cinameName);
            //获取价格
            int minPrice = cinemaBean.getMinPrice()/100;
            hodler.tv_cinemalist_price.setText(String.valueOf(minPrice));
            //获取地址
            String address = cinemaBean.getAddress();
            hodler.tv_cinema_address.setText(address);
            //获取距离，获取坐标
            String distance = getDistance(cinemaBean);
            hodler.tv_cinema_distance.setText(distance);

            //判断是否显示有wifi，停车场，vip,IMAX
            CinemaBean.FeatureEntity feature = cinemaBean.getFeature();
            int hasIMAX = feature.getHasIMAX();
            if(hasIMAX == 1) {
                hodler.item_cinemalist_icon_imax.setVisibility(View.VISIBLE);
                getList(1).add(cinemaBean);
            }
            int hasLoveseat = feature.getHasLoveseat();
            if(hasLoveseat == 1) {
                //hodler.item_cinemalist_icon_l.setVisibility(View.VISIBLE);
                getList(2).add(cinemaBean);
            }
            int hasPark = feature.getHasPark();
            if(hasPark == 1) {
                hodler.item_cinemalist_icon_parking.setVisibility(View.VISIBLE);
                getList(3).add(cinemaBean);
            }
            int hasVIP = feature.getHasVIP();
            if(hasVIP == 1) {
                hodler.item_cinemalist_icon_vip.setVisibility(View.VISIBLE);
                getList(4).add(cinemaBean);
            }
            int hasWifi = feature.getHasWifi();
            if(hasWifi == 1) {
                hodler.item_cinemalist_icon_wififree.setVisibility(View.VISIBLE);
                getList(5).add(cinemaBean);
            }
            int hasServiceTicket = feature.getHasServiceTicket();//是否有自动取票机
            if(hasServiceTicket == 1) {
                hodler.item_cinemalist_icon_kiosk.setVisibility(View.VISIBLE);
            }


            return convertView;
        }

        class ViewHolder {
            private   ImageView iv_cinima_item_nearst_icon;
            private   TextView tv_cinema_name;
            private  TextView tv_cinema_address;
            private  TextView tv_cinema_distance;
            private  TextView tv_cinemalist_price;
            private  ImageView item_cinemalist_icon_3d;
            private  ImageView item_cinemalist_icon_imax;
            private   ImageView item_cinemalist_icon_kiosk;
            private   ImageView item_cinemalist_icon_parking;
            private   ImageView item_cinemalist_icon_vip;
            private   ImageView item_cinemalist_icon_wififree;
            private View line;
        }
    };


    private class MyOnCheckedChangeListener implements RadioGroup.OnCheckedChangeListener {
        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            switch (checkedId) {
                case R.id.rb_cinema_total :
                    processData(json);
                    break;
                case R.id.rb_cinema_price :
                    sortPrice();//价格排序
                    break;
                case R.id.rb_cinema_near :
                    sortNear();
                    break;
                case R.id.rb_cinema_screen :
                    swtichScreen();
                    break;

            }
        }
    }

    /**
     * 条件的筛选
     */
    private String[] tab = {"特色","商圈","地区","地铁"};
    private void swtichScreen() {
        rg_cinema_choose.clearCheck();
        View view =View.inflate(activity,R.layout.cinema_alert,null);
        TabLayout tablayout_alert = (TabLayout) view.findViewById(R.id.tablayout_alert);
        ViewPager vp_alert = (ViewPager) view.findViewById(R.id.vp_alert);

        list = ConstantUtils.tese;
        vp_alert.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                switch (position) {
                    case 0 :
                        list = ConstantUtils.tese;
                        baseAdapter.notifyDataSetChanged();
                        break;
                    default :
                        list = ConstantUtils.shagnquan;
                        baseAdapter.notifyDataSetChanged();
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        vp_alert.setAdapter(new MyAdapter());
        tablayout_alert.setupWithViewPager(vp_alert);

        final AlertDialog dialog = new AlertDialog.Builder(activity)
                .setView(view).create();
        Window window = dialog.getWindow();
        window.setGravity(Gravity.BOTTOM);  //此处可以设置dialog显示的位置
        window.setWindowAnimations(R.style.mystyle);  //添加动画
        dialog.show();
        //dialog的消失
        view.findViewById(R.id.tv_alert_tc).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
    }

    /**
     * 位置转换的方法
     *
     * @param cinemaBean
     * @return
     */
    private String getDistance(CinemaBean cinemaBean) {
        String distanceStr = "距离未知";
        if (currentLatitude == 0) {
            currentLatitude = Double.parseDouble(CacheUtils.getString1(activity, CacheUtils.CURRENT_LOCATION_LATITUDE, "0"));
            currentLongitude = Double.parseDouble(CacheUtils.getString1(activity, CacheUtils.CURRENT_LOCATION_LONGITUDE, "0"));
        }

        double distance = LocationUtil.getDistance(currentLongitude, currentLatitude, cinemaBean.getLongitude(), cinemaBean.getLatitude()) / 1000;
        if (distance > 20) {
            distanceStr = ">20km";
        } else {
            distanceStr = ((int) (distance * 10)) * 1.0 / 10 + "km";
        }
        return distanceStr;
    }
    /**
     * 距离的排序
     */
    private void sortNear() {
        if(rawCinemaBeans == null) {
            return;
        }
        List<CinemaBean> near = new ArrayList<>(rawCinemaBeans.size());
        for (int i = 0; i < rawCinemaBeans.size(); i++) {
            CinemaBean cinemaBean = rawCinemaBeans.get(i);
            if (cinemaBean.getMinPrice() != 0) {
                near.add(cinemaBean);
            }
        }
        cinemaBeans = near;
        Collections.sort(cinemaBeans, new MyNearComparator());
        mAdapter.notifyDataSetChanged();//刷新适配器
    }

    private int NOSORT = 1;//无序
    private int INCREMENT = 2;//升序
    private int DESC = 3;//降序
    private int SORT = NOSORT;//排序状态，默认无序

    /**
     * 价格的排序
     */
    private void sortPrice() {
        if(rawCinemaBeans == null) {
            return;
        }
        List<CinemaBean> price = new ArrayList<>(rawCinemaBeans.size());

        for (int i = 0; i < rawCinemaBeans.size(); i++) {
            CinemaBean cinemaBean = rawCinemaBeans.get(i);
            if (cinemaBean.getMinPrice() != 0) {
                price.add(cinemaBean);
            }
        }
        cinemaBeans = price;
        Collections.sort(cinemaBeans, new MyPriceComparator());
        if(SORT == NOSORT) {
            rg_cinema_choose.clearCheck();
            SORT = INCREMENT;//改为升序的状态
            Drawable nav_up=activity.getResources().getDrawable(R.drawable.price_sort_up);
            nav_up.setBounds(0, 0, nav_up.getMinimumWidth(), nav_up.getMinimumHeight());
            rb_cinema_price.setCompoundDrawables(null, null, nav_up, null);
            mAdapter.notifyDataSetChanged();
        }else if(SORT == INCREMENT) {
            rg_cinema_choose.clearCheck();
            Collections.reverse(cinemaBeans);//反转
            Drawable nav_up=activity.getResources().getDrawable(R.drawable.price_sort_down);
            nav_up.setBounds(0, 0, nav_up.getMinimumWidth(), nav_up.getMinimumHeight());
            rb_cinema_price.setCompoundDrawables(null, null, nav_up, null);
            mAdapter.notifyDataSetChanged();
            SORT = DESC;
        }else {
            rg_cinema_choose.clearCheck();
            Collections.sort(cinemaBeans, new MyPriceComparator());
            SORT = INCREMENT;
            Drawable nav_up=activity.getResources().getDrawable(R.drawable.price_sort_up);
            nav_up.setBounds(0, 0, nav_up.getMinimumWidth(), nav_up.getMinimumHeight());
            rb_cinema_price.setCompoundDrawables(null, null, nav_up, null);
            mAdapter.notifyDataSetChanged();
        }
    }

    private class MyPriceComparator implements Comparator<CinemaBean>{
        @Override
        public int compare(CinemaBean lhs, CinemaBean rhs) {
            int minPrice = lhs.getMinPrice();
            int minPrice1 = rhs.getMinPrice();
            return minPrice-minPrice1;
        }
    }

    private class MyNearComparator implements Comparator<CinemaBean> {
        @Override
        public int compare(CinemaBean lhs, CinemaBean rhs) {
            double lhsDistance = LocationUtil.getDistance(currentLongitude, currentLatitude, lhs.getLongitude(), lhs.getLatitude());
            double rhsDistance = LocationUtil.getDistance(currentLongitude, currentLatitude, rhs.getLongitude(), rhs.getLatitude());
            return (int) (lhsDistance-rhsDistance);
        }
    }
    private class MyAdapter extends PagerAdapter {
        @Override
        public int getCount() {
            return 4;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return tab[position];
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            GridView view = (GridView) View.inflate(activity,R.layout.grid_alert,null);
            view.setAdapter(baseAdapter);
            container.addView(view);
            return view;
        }
    }


    private MyBaseAdapter baseAdapter = new MyBaseAdapter();
    private class MyBaseAdapter extends BaseAdapter {
        @Override
        public int getCount() {
            return list.length;
        }

        @Override
        public Object getItem(int position) {
            return list[position];
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View view = View.inflate(activity,R.layout.alert_grid_textview,null);
            TextView textView = (TextView) view.findViewById(R.id.text);
            textView.setText(list[position]);
            return view;
        }
    }
}
