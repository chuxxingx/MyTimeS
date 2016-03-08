package com.guigu.mytime.pager;

import android.app.Activity;
import android.graphics.Color;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.guigu.mytime.R;
import com.guigu.mytime.base.BasePager;
import com.guigu.mytime.buyticket.Cinema;
import com.guigu.mytime.buyticket.Movie;
import com.guigu.mytime.buyticket.view.MyViewPager;

import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/2/29.
 */
public class Buyticket extends BasePager implements View.OnClickListener {
    @ViewInject(R.id.tv_city_buyticket)
    private TextView tv_city_buyticket;

    @ViewInject(R.id.btn_title_movie_buyticket)
    private Button btn_title_movie_buyticket;

    @ViewInject(R.id.btn_title_cinema_buyticket)
    public Button btn_title_cinema_buyticket;

    @ViewInject(R.id.ib_rearch_buyticket)
    private ImageButton ib_rearch_buyticket;

    @ViewInject(R.id.vp_content_buyticket)
    public MyViewPager vp_content_buyticket;

    private List<BasePager> data;
    private boolean ismovie = true;
    private boolean isCinema = false;

    public Buyticket(Activity activity) {
        super(activity);
    }

    @Override
    public View initView() {
        View view = View.inflate(activity, R.layout.buyticket_fragment_view,null);
        x.view().inject(this,view);
        return view;
    }

    @Override
    public void initData() {
        Log.e("TAG", "buyticket initData()");
        data = new ArrayList<>();
        data.add(new Movie(activity));
        data.add(new Cinema(activity));
        setlistener();
        vp_content_buyticket.setAdapter(new MyPagerAdapter());
//        data.get(0).initData();
        //  vp_content_buyticket.setCurrentItem(0);
    }

    private void setlistener() {
        tv_city_buyticket.setOnClickListener(this);
        btn_title_movie_buyticket.setOnClickListener(this);
        btn_title_cinema_buyticket.setOnClickListener(this);
        ib_rearch_buyticket.setOnClickListener(this);
        vp_content_buyticket.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                data.get(position).initData();
                if (position == 0) {
                    btn_title_movie_buyticket.performClick();
                } else {
                    btn_title_cinema_buyticket.performClick();
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_city_buyticket :
                //启动定位页面

                break;
            case R.id.btn_title_movie_buyticket :
                //电影页面
                vp_content_buyticket.setCurrentItem(0);
                btn_title_cinema_buyticket.setTextColor(Color.WHITE);
                btn_title_movie_buyticket.setTextColor(Color.BLUE);
                btn_title_cinema_buyticket.setEnabled(true);
                btn_title_cinema_buyticket.setBackgroundResource(android.R.color.transparent);
                btn_title_movie_buyticket.setEnabled(false);
                btn_title_movie_buyticket.setBackgroundResource(R.drawable.title_bar_movie_selected);
                break;
            case R.id.btn_title_cinema_buyticket :
                //影院页面
                vp_content_buyticket.setCurrentItem(1);
                btn_title_cinema_buyticket.setEnabled(false);
                btn_title_movie_buyticket.setEnabled(true);
                btn_title_movie_buyticket.setBackgroundResource(android.R.color.transparent);
                btn_title_cinema_buyticket.setBackgroundResource(R.drawable.title_bar_cinema_selected);
                btn_title_cinema_buyticket.setTextColor(Color.BLUE);
                btn_title_movie_buyticket.setTextColor(Color.WHITE);
                break;
            case R.id.ib_rearch_buyticket :
                //搜索页面

                break;
        }
    }

    private class MyPagerAdapter extends PagerAdapter {
        @Override
        public int getCount() {
            return data.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            data.get(position).initData();
            View rootView = data.get(position).rootView;
            container.addView(rootView);
            return rootView;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }
    }
}
