package com.guigu.mytime.discover;

import android.app.Activity;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.guigu.mytime.R;
import com.guigu.mytime.base.BaseDiscoverPager;

import java.util.ArrayList;


/**
 * Created by chu on 2016/3/3.
 */
public class InlandDiscoverActivity extends Activity implements View.OnClickListener {


    private TabLayout dis_tabLayout;
    private ViewPager viewpager_discover_inland;

    private MyPagerAdapter myAdapter;

    private ImageButton iv_inland_piaofang;
    private TextView tv_inland_piaofang;
    private static String[] title = new String[]{"北美", "内地", "香港", "台湾", "日本", "韩国"};
    private ArrayList<BaseDiscoverPager> inlandViewpagerBases;
    private String[] urls = {
            "http://api.m.mtime.cn/TopList/TopListDetailsByRecommend.api?pageIndex=1&pageSubAreaID=2015&locationId=292",
            "http://api.m.mtime.cn/TopList/TopListDetailsByRecommend.api?pageIndex=1&pageSubAreaID=2020&locationId=292",
            "http://api.m.mtime.cn/TopList/TopListDetailsByRecommend.api?pageIndex=1&pageSubAreaID=2016&locationId=292",
            "http://api.m.mtime.cn/TopList/TopListDetailsByRecommend.api?pageIndex=1&pageSubAreaID=2019&locationId=292",
            "http://api.m.mtime.cn/TopList/TopListDetailsByRecommend.api?pageIndex=1&pageSubAreaID=2017&locationId=292",
            "http://api.m.mtime.cn/TopList/TopListDetailsByRecommend.api?pageIndex=1&pageSubAreaID=2018&locationId=292"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inland_discover);

        dis_tabLayout = (TabLayout) findViewById(R.id.dis_tabLayout);

        iv_inland_piaofang = (ImageButton) findViewById(R.id.iv_inland_piaofang);
        tv_inland_piaofang = (TextView) findViewById(R.id.tv_inland_piaofang);

        viewpager_discover_inland = (ViewPager) findViewById(R.id.viewpager_discover_inland);



        inlandViewpagerBases = new ArrayList<>();

        inlandViewpagerBases.add(new Beimei(this));
        inlandViewpagerBases.add(new Beimei(this));
        inlandViewpagerBases.add(new Beimei(this));
        inlandViewpagerBases.add(new Beimei(this));
        inlandViewpagerBases.add(new Beimei(this));
        inlandViewpagerBases.add(new Beimei(this));
        dis_tabLayout.setTabMode(TabLayout.MODE_FIXED);
        viewpager_discover_inland.setAdapter( new MyPagerAdapter());
        dis_tabLayout.setupWithViewPager(viewpager_discover_inland);

        viewpager_discover_inland.setOnPageChangeListener(new InlandOnPageChangeListener());

        inlandViewpagerBases.get(0).initData();
    }

    @Override
    public void onClick(View v) {
            finish();
    }

    class InlandOnPageChangeListener implements ViewPager.OnPageChangeListener {

        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            Beimei beimei = (Beimei) inlandViewpagerBases.get(position);
            beimei.setUrl(urls[position]);
            beimei.initData();
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    }

    class MyPagerAdapter extends PagerAdapter {

        @Override
        public int getCount() {
            return inlandViewpagerBases.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            Beimei beimei = (Beimei) inlandViewpagerBases.get(position);
            beimei.setUrl(urls[position]);
            beimei.initData();
            View disRootview = beimei.rootView;
            ViewGroup parent = (ViewGroup) disRootview.getParent();
            if (parent != null) {
                parent.removeView(disRootview);
            }
            container.addView(disRootview);
            return disRootview;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return title[position];
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
//            super.destroyItem(container, position, object);
            container.removeView((View) object);
        }
    }
}
