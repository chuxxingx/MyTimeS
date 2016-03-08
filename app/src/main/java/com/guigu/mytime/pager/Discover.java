package com.guigu.mytime.pager;

import android.app.Activity;
import android.support.design.widget.TabLayout;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;

import com.guigu.mytime.R;
import com.guigu.mytime.base.BaseDiscoverPager;
import com.guigu.mytime.base.BasePager;
import com.guigu.mytime.discover.pager.Cinecism;
import com.guigu.mytime.discover.pager.Rank;
import com.guigu.mytime.discover.pager.NewsPager;
import com.guigu.mytime.discover.pager.Prevue;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chu on 2016/2/29.
 */
public class Discover extends BasePager{
    private TabLayout tabpageIndicator;
    private ViewPager viewpager_discover;



    private List<BaseDiscoverPager> data; //pager列表
    private List<String> title; //tab名的列表
    private MyPagerAdapter myAdapter;

    public Discover(Activity activity) {
        super(activity);
    }


    @Override
    public View initView() {
        View view=View.inflate(activity, R.layout.activity_discover,null);
        tabpageIndicator = (TabLayout) view.findViewById(R.id.tabpageIndicator);
        viewpager_discover = (ViewPager) view.findViewById(R.id.viewpager_discover);
        return view;
    }

    @Override
    public void initData() {
        super.initData();
        //初始化集合
        data= new ArrayList<>();

        data.add(new NewsPager(activity));
        data.add(new Prevue(activity));
        data.add(new Rank(activity));
        data.add(new Cinecism(activity));

        myAdapter =new MyPagerAdapter();
        viewpager_discover.setAdapter(myAdapter);
        viewpager_discover.setOnPageChangeListener(new DiscoverOnPageChangeListener());
        title = new ArrayList<>();
        title.add("新闻");
        title.add("预告片");
        title.add("排行榜");
        title.add("影评");
        //绑定VP


        tabpageIndicator.setTabMode(TabLayout.MODE_FIXED);

        //为TabLayout添加tab名称
//        tabpageIndicator.addTab(tabpageIndicator.newTab().setText(title.get(0)));
//        tabpageIndicator.addTab(tabpageIndicator.newTab().setText(title.get(1)));
//        tabpageIndicator.addTab(tabpageIndicator.newTab().setText(title.get(2)));
//        tabpageIndicator.addTab(tabpageIndicator.newTab().setText(title.get(3)));
//
        tabpageIndicator.setupWithViewPager(viewpager_discover);
        //tabpageIndicator.setTabsFromPagerAdapter(myAdapter);

        //myAdapter= new Find_tab_Adapter(getActivity().getSupportFragmentManager(),data,title);
        //首页初始化数据

        data.get(0).initData();
    }

    /**
     * 防止预加载
     */

    class DiscoverOnPageChangeListener implements ViewPager.OnPageChangeListener{

        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            data.get(position).initData();
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    }
    /**
     * 适配器
     */
    class MyPagerAdapter extends PagerAdapter {

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            BaseDiscoverPager baseDiscoverPager = data.get(position);
            View rootView = baseDiscoverPager.rootView;
            ViewGroup parent = (ViewGroup) rootView.getParent();
            if(parent != null){
                parent.removeView(rootView);
            }
            container.addView(baseDiscoverPager.rootView);
            return baseDiscoverPager.rootView;

        }

        @Override
        public CharSequence getPageTitle(int position) {
            return title.get(position);
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public int getCount() {
            return data.size();

        }
    }
}
