package com.guigu.mytime.Store;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.guigu.mytime.R;
import com.guigu.mytime.Store.adapter.StoreIndicatorAdapter;
import com.guigu.mytime.Store.adapter.StoreScrollImgAdapter;
import com.guigu.mytime.Store.bean.MyViewPager;
import com.guigu.mytime.Store.bean.StoreBean;
import com.guigu.mytime.Store.utils.ImageUtils;
import com.guigu.mytime.Store.warspagers.BaseStarWarsPage;
import com.guigu.mytime.Store.warspagers.IndicatorHorizontalListView;
import com.guigu.mytime.Store.warspagers.StarWarsPage;
import com.guigu.mytime.Store.warspagers.StarWarsViewPager;
import com.guigu.mytime.Store.warspagers.StoreStarWarsAdapter;
import com.guigu.mytime.Store.warspagers.StoreWebViewActivity;
import com.guigu.mytime.utils.ConstantUtils;

import org.xutils.common.util.DensityUtil;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Administrator on 2016/3/1.
 * 商城头部
 */
public class StoreListHeader implements View.OnClickListener {

    /**
     * 上下文
     */
    private Context mContext;

    /**
     * 数据
     */
    private StoreBean mStoreBean;

    /**
     * 布局加载器
     */
    private LayoutInflater mInflater;
    /**
     * 最顶部的ViewPager
     */
    @ViewInject(R.id.vp_scroll_image)
    private MyViewPager vp_scroll_image;

    /**
     * ViewPager上的点
     */
    @ViewInject(R.id.ll_point_container)
    private LinearLayout ll_point_container;
    /**
     * Navigator
     */
    @ViewInject(R.id.tv_navigator0)
    private TextView tv_navigator0;
    @ViewInject(R.id.tv_navigator1)
    private TextView tv_navigator1;
    @ViewInject(R.id.tv_navigator2)
    private TextView tv_navigator2;
    @ViewInject(R.id.tv_navigator3)
    private TextView tv_navigator3;

    /**
     * Cell
     */
    @ViewInject(R.id.iv_cellA)
    private ImageView iv_cellA;
    @ViewInject(R.id.iv_cellB)
    private ImageView iv_cellB;
    @ViewInject(R.id.iv_cellC0)
    private ImageView iv_cellC0;
    @ViewInject(R.id.iv_cellC1)
    private ImageView iv_cellC1;

    /**
     * 水平ListView
     */
    @ViewInject(R.id.hlv_store_starwars_indicator)
    private IndicatorHorizontalListView hlv_store_starwars_indicator;

    /**
     * star_wars 界面的布局
     */
    @ViewInject(R.id.vp_star_wars)
    private StarWarsViewPager vp_star_wars;

    @ViewInject(R.id.tv_more_product)
    private TextView tv_more_product;

    private com.guigu.mytime.Store.encapsulation encapsulation;
    private ArrayList<ImageView> mPoints;

    private View rootView;
    private int mCurrScrollImgPage;
    //viewPage的界面
    private ArrayList<BaseStarWarsPage> mStarwarsPages;

    public View getRootView() {
        return rootView;
    }
        public StoreListHeader(Context context, StoreBean storeBean, LayoutInflater inflater) {
            mContext = context;
            mStoreBean = storeBean;
            mInflater = inflater;
            encapsulation = new encapsulation();
            rootView = initView();

        }

    /**
     * 加载视图
     * @return
     */
    private View initView() {
        View view = mInflater.inflate(R.layout.store_list_head, null);
        vp_scroll_image = (MyViewPager) view.findViewById(R.id.vp_scroll_image);
        x.view().inject(this,view);
        return view;
    }




    //添加头部视图数据
    public void initData() {

        //1.顶部轮播图
        vp_scroll_image.setAdapter(new StoreScrollImgAdapter(mContext, mStoreBean.getScrollImg()));
        mCurrScrollImgPage = mStoreBean.getScrollImg().size() * 100000;
        vp_scroll_image.setCurrentItem(mCurrScrollImgPage);
        //设置标示点
        setPointIndicator();

        initNavigator();
        initCells();
        initStarwarsPagesData();
        initStarWars();//ViewPager
        hlv_store_starwars_indicator.setAdapter(new StoreIndicatorAdapter(mContext, mInflater, mStoreBean.getTopic()));


        //设置监听
        setListener();
    }

    private void initStarWars() {
        vp_star_wars.setAdapter(new StoreStarWarsAdapter(mContext, mStarwarsPages));
    }

    /**
     * 初始化WarsPage页面的数据
     */
    private void initStarwarsPagesData() {
        int size = mStoreBean.getTopic().size();
        mStarwarsPages = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            mStarwarsPages.add(new StarWarsPage(mContext, mStoreBean.getTopic().get(i)));
        }

    }

    private void initCells() {
        encapsulation.bind(iv_cellA,mStoreBean.getCellA().getImg());
        encapsulation.bind(iv_cellB, mStoreBean.getCellB().getImg());
        encapsulation.bind(iv_cellC0, mStoreBean.getCellC().getList().get(0).getImage());
        encapsulation.bind(iv_cellC1, mStoreBean.getCellC().getList().get(1).getImage());
    }

    /**
     * 初始化
     */
    private void initNavigator() {
        List<StoreBean.NavigatorIconEntity> list = mStoreBean.getNavigatorIcon();
        tv_navigator0.setText(list.get(0).getIconTitle());

        ImageUtils.getBitmap(list.get(0).getImage(), new ImageUtils.OnBitmapLoadlistener() {
            @Override
            public void onBitmapLoad(Bitmap bitmap) {
                Drawable drawable = new BitmapDrawable(bitmap);
                drawable.setBounds(0, 0, bitmap.getWidth(), bitmap.getHeight());
                tv_navigator0.setCompoundDrawables(null, drawable, null, null);
            }
        });

        tv_navigator1.setText(list.get(1).getIconTitle());
        ImageUtils.getBitmap(list.get(1).getImage(), new ImageUtils.OnBitmapLoadlistener() {
            @Override
            public void onBitmapLoad(Bitmap bitmap) {
                Drawable drawable = new BitmapDrawable(bitmap);
                drawable.setBounds(0, 0, bitmap.getWidth(), bitmap.getHeight());
                tv_navigator1.setCompoundDrawables(null, drawable, null, null);
            }
        });

        tv_navigator2.setText(list.get(2).getIconTitle());
        ImageUtils.getBitmap(list.get(2).getImage(), new ImageUtils.OnBitmapLoadlistener() {
            @Override
            public void onBitmapLoad(Bitmap bitmap) {
                Drawable drawable = new BitmapDrawable(bitmap);
                drawable.setBounds(0, 0, bitmap.getWidth(), bitmap.getHeight());
                tv_navigator2.setCompoundDrawables(null, drawable, null, null);
            }
        });

        tv_navigator3.setText(mStoreBean.getNavigatorFirthIcon().getIconTitle1());
        ImageUtils.getBitmap(mStoreBean.getNavigatorFirthIcon().getImg1(), new ImageUtils.OnBitmapLoadlistener() {
            @Override
            public void onBitmapLoad(Bitmap bitmap) {
                Drawable drawable = new BitmapDrawable(bitmap);
                //导致图片变小  drawable 和 bitmap装换 图片变小问题
//                drawable.setBounds(0,0,drawable.getMinimumWidth(),drawable.getMinimumHeight());
                drawable.setBounds(0, 0, bitmap.getWidth(), bitmap.getHeight());
                tv_navigator3.setCompoundDrawables(null, drawable, null, null);
            }
        });
    }


    private void setListener() {
        vp_scroll_image.addOnPageChangeListener(new ScrollImageOnPageChangeListener());

        vp_scroll_image.addOnPageChangeListener(new ScrollImageOnPageChangeListener());


        hlv_store_starwars_indicator.setOnItemClickListener(new IndicatorOnItemClickListener());

        //玩具数码之类设置监听
        tv_navigator0.setOnClickListener(this);
        tv_navigator1.setOnClickListener(this);
        tv_navigator2.setOnClickListener(this);
        tv_navigator3.setOnClickListener(this);


        iv_cellA.setOnClickListener(this);
        iv_cellB.setOnClickListener(this);
        iv_cellC0.setOnClickListener(this);
        iv_cellC1.setOnClickListener(this);

        tv_more_product.setOnClickListener(this);//更多商品
        vp_scroll_image.setOnClickListener(this);//顶部轮播图
    }
    private boolean isFistEnter = true;

    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            vp_scroll_image.setCurrentItem(vp_scroll_image.getCurrentItem() + 1);
        }
    };

    @Override
    public void onClick(View v) {
        String url = "";
        if (v == tv_navigator0) { // 玩具
            url = mStoreBean.getNavigatorIcon().get(0).getUrl();
            startWebViewActivity(url);
        } else if (v == tv_navigator1) {// 数码
            url = mStoreBean.getNavigatorIcon().get(1).getUrl();
            startWebViewActivity(url);
        } else if (v == tv_navigator2) {// 服饰
            url = mStoreBean.getNavigatorIcon().get(2).getUrl();
            startWebViewActivity(url);
        } else if (v == tv_navigator3) {// 家居
            url = mStoreBean.getNavigatorFirthIcon().getUrl();
            startWebViewActivity(url);
        }else if (v == iv_cellA) { //CellA
            url = mStoreBean.getCellA().getUrl();
            startWebViewActivity(url);
        } else if (v == iv_cellB) {//CellB
            url = mStoreBean.getCellB().getUrl();
            startWebViewActivity(url);
        } else if (v == iv_cellC0) {//CellC1
            url = mStoreBean.getCellC().getList().get(0).getUrl();
            startWebViewActivity(url);
        } else if (v == iv_cellC1) {//CellC2
            url = mStoreBean.getCellC().getList().get(1).getUrl();
            startWebViewActivity(url);
        }else if (v == tv_more_product) {//更多
            url = mStoreBean.getCategory().get(currStarWarPosition).getMoreUrl();
            startWebViewActivity(url);
        }
    }

    /**
     * 启动WebView
     * @param url
     */
    private void startWebViewActivity(String url) {
        String substring = url.substring(0, 4);
        if (!"http".equals(substring)) {
            url = ConstantUtils.BASE_URL +"/"+ url;
        }

        Intent intent = new Intent(mContext, StoreWebViewActivity.class);
        intent.putExtra("webview_url", url);
        mContext.startActivity(intent);
    }

    class ScrollImageOnPageChangeListener implements ViewPager.OnPageChangeListener{

        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            if (isFistEnter) {
                isFistEnter = false;
                handler.sendEmptyMessageDelayed(1, 3000);
            }
        }

        @Override
        public void onPageSelected(int position) {
            if (position >= mCurrScrollImgPage) {
                vp_scroll_image.isToRight = false;
            } else {
                vp_scroll_image.isToRight = true;
            }
            int realPosition = mCurrScrollImgPage % mPoints.size();
            mPoints.get(realPosition).setEnabled(true);
            mCurrScrollImgPage = position;
            realPosition = mCurrScrollImgPage % mPoints.size();
            mPoints.get(realPosition).setEnabled(false);
        }

        @Override
        public void onPageScrollStateChanged(int state) {

            switch(state){
                case ViewPager.SCROLL_STATE_IDLE:
                    break;
                case ViewPager.SCROLL_STATE_DRAGGING:
                    handler.removeMessages(1);
                    break;
                case ViewPager.SCROLL_STATE_SETTLING:
                    handler.sendEmptyMessageDelayed(1, 3000);
                    break;
                default:
                    break;
            }
        }
    }

    /**
     * Viewpage的标示点
     */
    private void setPointIndicator() {
        //1.获取数据，表示点的集合并且添加到点容器中
        getPoint();
        //2.默认第一个是显示
        mPoints.get(0).setEnabled(false);

    }

    //标识点初始化
    private void getPoint() {
        List<StoreBean.ScrollImgEntity> scrollImg = mStoreBean.getScrollImg();
        mPoints = new ArrayList<>();
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                DensityUtil.dip2px(8), DensityUtil.dip2px(8));
        params.leftMargin = DensityUtil.dip2px(5);
        params.rightMargin = DensityUtil.dip2px(5);
        for (int i = 0; i < scrollImg.size(); i++) {
            ImageView point = new ImageView(mContext);
            point.setImageResource(R.drawable.store_point_indicator);
            mPoints.add(point);

            point.setLayoutParams(params);

            ll_point_container.addView(point);//添加到容器中
        }
    }

    private int currStarWarPosition = 0;

    private class IndicatorOnItemClickListener implements AdapterView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            currStarWarPosition = position;
            vp_star_wars.setCurrentItem(position,false);
        }
    }
}
