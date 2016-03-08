package com.guigu.mytime;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.guigu.mytime.locat.LocationActivity;
import com.guigu.mytime.utils.CacheUtils;
import com.guigu.mytime.utils.DensityUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/2/29.
 */
public class GuideActivity extends Activity{

    //引导页面的viewpager
    private ViewPager guide_viewpager;
    //进入主页的按钮
    private Button btn_guide;
    //引导页面的点的集合
    private LinearLayout ll_guide_point_group;
    /**
     * 图片集合
     */
    private List icon;

    /**
     * 图片资源集合
     */
    private int[] iamge = {R.drawable.lead_bg1,R.drawable.lead_bg2,R.drawable.lead_bg3,R.drawable.lead_bg4};
    //点集合布局的参数
    private int widthDpi;
    //当前点的位置
    private int current;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide);

        widthDpi = DensityUtils.dip2px(this, 10f);
        initView();

        initData();
        //viewpager设置适配器
        guide_viewpager.setAdapter(new MyViewPagerAdapter());
        //viewpager设置页面改变的监听
        guide_viewpager.setOnPageChangeListener(new MyOnPageChangeListener());
        //btn_guide的点击监听
        btn_guide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //保存是否引导的标识值
                CacheUtils.putBoolean(GuideActivity.this, "isGuide", true);
                //启动到主页面
                boolean isLocation = CacheUtils.getBoolean(GuideActivity.this, "isLocation");
                if(!isLocation) {
                    startActivity(new Intent(GuideActivity.this, LocationActivity.class));
                } else {
                    startActivity(new Intent(GuideActivity.this, MainActivity.class));
                }
                finish();
            }
        });
    }

    private void initView() {
        guide_viewpager = (ViewPager)findViewById(R.id.guide_viewpager);
        btn_guide = (Button) findViewById(R.id.btn_guide);
        ll_guide_point_group = (LinearLayout)findViewById(R.id.ll_guide_point_group);
    }

    private void initData() {
        icon = new ArrayList();
        for(int i = 0; i < iamge.length; i++) {
            ImageView imageView = new ImageView(this);
            imageView.setBackgroundResource(iamge[i]);
            icon.add(imageView);

            //添加默认点
            ImageView normal_point = new ImageView(this);
            normal_point.setBackgroundResource(R.drawable.point_group);
            //把它当成是dip
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(widthDpi,widthDpi);
            if(i != 0){
                params.leftMargin = widthDpi;
                normal_point.setEnabled(false);
            }
            normal_point.setLayoutParams(params);
            //添加到线性布局里面去
            ll_guide_point_group.addView(normal_point);
        }
        ll_guide_point_group.getChildAt(current).setEnabled(true);//默认选中第一页对应的点
    }

    private class MyViewPagerAdapter extends PagerAdapter {
        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            ImageView imageview = (ImageView) icon.get(position);
            container.addView(imageview);
            return imageview;
        }

        @Override
        public int getCount() {
            return icon.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }
    }

    private class MyOnPageChangeListener implements ViewPager.OnPageChangeListener {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            if(position == 3) {
                btn_guide.setVisibility(View.VISIBLE);
                ll_guide_point_group.setVisibility(View.GONE);
            }else {
                btn_guide.setVisibility(View.GONE);
                ll_guide_point_group.setVisibility(View.VISIBLE);
            }

            ll_guide_point_group.getChildAt(current).setEnabled(false);
            ll_guide_point_group.getChildAt(position).setEnabled(true);
            current = position;
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    }
}
