package com.guigu.mytime;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.RadioGroup;

import com.guigu.mytime.base.BasePager;
import com.guigu.mytime.pager.Buyticket;
import com.guigu.mytime.pager.Discover;
import com.guigu.mytime.pager.Home;
import com.guigu.mytime.pager.My;
import com.guigu.mytime.pager.Store;
import com.guigu.mytime.utils.CacheUtils;
import com.guigu.mytime.view.MyMainViewPager;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/2/29.
 */
public class MainActivity extends FragmentActivity {

    private RadioGroup rg_main;
    private MyMainViewPager viewpager_main;

    /**
     * 首页，购票，商城，发现，我的页面集合
     */
    private ArrayList<BasePager> list;

    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 0 :
                    initShow(1);
                    break;
                case 1 :
                    initShow(2);
                    break;
                case 2 :
                    initShow(3);
                    break;
                case 3 :
                    viewpager_main.setCurrentItem(3);
                    break;
            }
        }
    };
    private Buyticket buyticket;
    private Discover discover;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        CacheUtils.putBoolean(this, "isLocation", true);

        rg_main = (RadioGroup) findViewById(R.id.rg_main);
        viewpager_main = (MyMainViewPager) findViewById(R.id.viewpager_main);

        initData();
        //显示首页
        initShow(0);
        rg_main.setOnCheckedChangeListener(new MyOnCheckedChangeListener());
    }

    private void initShow(int i) {
        viewpager_main.setAdapter(new MyPagerAdapter());
        rg_main.check(R.id.rb_home);
        switch (i) {
            case 0:
                viewpager_main.setCurrentItem(0);
                break;
            case 1 :
                viewpager_main.setCurrentItem(1);//默认选中第一页
                buyticket.vp_content_buyticket.setCurrentItem(0);
                break;
            case 2:
                CacheUtils.putBoolean(MainActivity.this, "willshow", true);
                viewpager_main.setCurrentItem(1);//默认选中第一页
                buyticket.vp_content_buyticket.setCurrentItem(0);
                break;
            case 3:
                viewpager_main.setCurrentItem(1);//默认选中第一页
                buyticket.btn_title_cinema_buyticket.performClick();
                break;
        }
        viewpager_main.setOnPageChangeListener(new MyOnPageChangeListener());
    }

    private void initData() {
        list = new ArrayList();
        buyticket = new Buyticket(MainActivity.this);
        discover = new Discover(MainActivity.this);
        list.add(new Home(MainActivity.this, handler));
        list.add(buyticket);
        list.add(new Store(MainActivity.this));
        list.add(discover);
        list.add(new My(MainActivity.this));
    }


    private class MyOnCheckedChangeListener implements RadioGroup.OnCheckedChangeListener {
        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            switch (checkedId) {
                case R.id.rb_home:
                    viewpager_main.setCurrentItem(0);//默认选中第一页
                    break;
                case R.id.rb_buyticket:
                    viewpager_main.setCurrentItem(1);//选中第二页
                    break;
                case R.id.rb_store:
                    viewpager_main.setCurrentItem(2);//选中第三页
                    break;
                case R.id.rb_discover:
                    viewpager_main.setCurrentItem(3);//选中第四页
                    break;
                case R.id.rb_my:
                    viewpager_main.setCurrentItem(4);//选中第五页
                    break;
            }
        }
    }

    private class MyPagerAdapter extends PagerAdapter {
        @Override
        public int getCount() {
            return list.size();
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
            BasePager basePager = list.get(position);
            basePager.initData();
            View rootView = basePager.rootView;
            container.addView(rootView);
            return rootView;
        }
    }

    private class MyOnPageChangeListener implements ViewPager.OnPageChangeListener {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
          //  list.get(position).initData();
            switch (position) {
                case  0:
                    rg_main.check(R.id.rb_home);
                    break;
                case  1:
                    rg_main.check(R.id.rb_buyticket);
                    break;
                case  2:
                    rg_main.check(R.id.rb_store);
                    break;
                case  3:
                    rg_main.check(R.id.rb_discover);
                    break;
                case  4:
                    rg_main.check(R.id.rb_my);
                    break;
            }
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    }

    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        if(keyCode == KeyEvent.KEYCODE_BACK) {
            AlertDialog dialog = new AlertDialog.Builder(this)
                    .setTitle("亲不在看看嘛")
                    .setNegativeButton("退出", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            finish();
                        }
                    })
                    .setPositiveButton("再看看",null)
                    .show();
        }
        return super.onKeyUp(keyCode, event);
    }
}
