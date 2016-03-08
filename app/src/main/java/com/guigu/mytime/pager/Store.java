package com.guigu.mytime.pager;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AbsListView;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.google.gson.Gson;
import com.guigu.mytime.R;
import com.guigu.mytime.Store.StoreListFooter;
import com.guigu.mytime.Store.StoreListHeader;
import com.guigu.mytime.Store.StoreReflashListView;
import com.guigu.mytime.Store.adapter.StoreListAdapter;
import com.guigu.mytime.Store.bean.StoreBean;
import com.guigu.mytime.Store.bean.StoreGoodsBean;
import com.guigu.mytime.base.BasePager;
import com.guigu.mytime.utils.ConstantUtils;
import com.guigu.mytime.volley.VolleyManager;

import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import zxing.CaptureActivity;

/**
 * Created by Administrator on 2016/2/29.
 */
public class Store extends BasePager implements View.OnClickListener{
    /**
     * mStoreBeanJson
     */
    private String mStoreBeanJson;
    /**
     * 数据
     */
    private StoreBean mStoreBean;
    /**
     * 底部商品实体类
     */
    private StoreGoodsBean mGoodsBean;
    /**
     * 头部视图
     */
    private StoreListHeader mListHead;
    /**
     * 底部视图
     */
    private StoreListFooter mListFoot;
    /**
     * Store商场的ListAdapter
     */
    private StoreListAdapter mStoreListAdapter;
    /**
     * ListView
     */
    @ViewInject(R.id.lv_store)
    private StoreReflashListView mListView;
    //布局加载器
    private LayoutInflater mInflater;
    @ViewInject(R.id.ll_store_title_bg)
    private LinearLayout mTitleBg;
    /**
     * 保证showUI()方法只调用一次
     */
    private boolean mDataPrepare;
    /**
     * 帧动画，加载数据的动画
     */
    private AnimationDrawable animationDrawable;
    /**
     * 动画的背景
     */
    @ViewInject(R.id.fl_store_loading_animation)
    private FrameLayout fl_store_loading_animation;
    @ViewInject(R.id.fl_store_loading_refresh)
    private FrameLayout fl_store_loading_refresh;
    

    /**
     * 点击进入扫描的图片
     */
    @ViewInject(R.id.iv_scan_title_store)
    private ImageView mScan;

    /**
     * 购物车的图片
     */
    @ViewInject(R.id.iv_car_title_shop)
    private ImageView mShopCar;

    /**
     * 搜索
     */
    @ViewInject(R.id.tv_search_title_store)
    private TextView mTitle;
    private ViewPager mScrollViewPager;

    public Store(Activity activity) {
        super(activity);
    }


    @Override
    public View initView() {
        mInflater = LayoutInflater.from(activity);
        View view = mInflater.inflate(R.layout.store_fragment_view, null);
        x.view().inject(this, view);
        mTitleBg.getBackground().setAlpha(0);

        // 初始化加载动画
        ImageView iv_main_loading = (ImageView) view
                .findViewById(R.id.iv_store_loading_loading);
        iv_main_loading.setImageResource(R.drawable.loading_animation_list);
        animationDrawable = (AnimationDrawable) iv_main_loading.getDrawable();
        return view;
    }

    @Override
    public void initData() {
        super.initData();

        fl_store_loading_animation.setVisibility(View.VISIBLE);
        fl_store_loading_refresh.setVisibility(View.GONE);
        animationDrawable.start();
        getData();//初始化数据

        setListener();//设置监听

    }



    /**
     * 获取网络数据
     */
    private void getData() {

        mDataPrepare = false;//两个数据得到界面
        StringRequest request = new StringRequest(Request.Method.GET, ConstantUtils.STORE_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String json) {

                        mStoreBeanJson = json;//网络请求到数据存储
                        mStoreBean = parseJson(json);//解析json\

                        if (mStoreBean != null) {
                            if (mDataPrepare) {
                                showUI();//显示界面
                            } else {
                                mDataPrepare = true;//保证只能调用一句
                            }
                        }

                        animationDrawable.stop();
                        fl_store_loading_animation.setVisibility(View.GONE);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                animationDrawable.stop();
                fl_store_loading_animation.setVisibility(View.GONE);
                fl_store_loading_refresh.setVisibility(View.VISIBLE);
                Toast.makeText(activity, "亲！检查下网络喔！", Toast.LENGTH_SHORT).show();
            }
        });
        VolleyManager.add(request);

        StringRequest request1 = new StringRequest(Request.Method.GET, ConstantUtils.STORE_GOODS_URL
                , new Response.Listener<String>() {
            @Override
            public void onResponse(String json) {
                String mGoodsBeanJson = json;
                mGoodsBean = parseGoodsJson(json);//解析json
                if (mGoodsBean != null) {
                    if (mDataPrepare) {
                        //显示界面
                        showUI();
                    } else {
                        mDataPrepare = true;
                    }
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Toast.makeText(activity, "亲！检查下网络喔！", Toast.LENGTH_SHORT).show();
            }
        });
        VolleyManager.add(request1);
    }

    private boolean isFirst = true;
    /**
     * ui界面
     */
    private void showUI() {
        if(isFirst) {
            initHeadAndFoot();
            isFirst = false;
        }
        mStoreListAdapter = new StoreListAdapter(activity, mStoreBean.getCategory());
        mListView.setAdapter(mStoreListAdapter);

        //设置ListView的滚动监听监听
        mListView.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                int[] location = new int[2];
                if (mScrollViewPager == null) {
                    mScrollViewPager = (ViewPager) view.findViewById(R.id.vp_scroll_image);
                }
                mScrollViewPager.getLocationOnScreen(location);
                int height = location[1];
                if (height > 0) {
                    mTitleBg.getBackground().setAlpha(0);
                } else {
                    if (Math.abs(height) <= 255) {
                        mTitleBg.getBackground().setAlpha(Math.abs(height));
                    } else {
                        mTitleBg.getBackground().setAlpha(255);
                    }
                }
            }
        });

        //设置ListView的下拉加载更多
        mListView.setmOnPullToReflash(new StoreReflashListView.OnPullToReflash() {
            @Override
            public void onReflash() {
                int pageCount = mGoodsBean.getPageCount();
                if (pageCount > currGoodsPage) {
                    String url = ConstantUtils.STORE_GOODS_URL + currGoodsPage;
                    StringRequest request = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String json) {
                            StoreGoodsBean bean = parseGoodsJson(json);
                            mListFoot.addNewPager(bean);
                            mListView.finishLoadMore(false);
                            currGoodsPage += 1;
                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError volleyError) {
                            Toast.makeText(activity, "请您检查网络", Toast.LENGTH_SHORT).show();
                            mListView.finishLoadMore(false);
                        }
                    });
                    VolleyManager.add(request);
                } else {
                    Toast.makeText(activity, "亲！已经没有更多了哦~", Toast.LENGTH_SHORT).show();
                    mListView.finishLoadMore(true);
                }
            }
        });
    }


    private int currGoodsPage = 2;

    /**
     * 初始化头部和底部视图，需要在联网获取到数据之后调用
     */
    private void initHeadAndFoot() {
        mListHead = new StoreListHeader(activity,mStoreBean,mInflater);
        mListHead.initData();
        mListView.addHeaderView(mListHead.getRootView());


        mListFoot = new StoreListFooter(activity, mGoodsBean, mInflater);
        mListView.addSecondaryFootView(mListFoot.getRootView(),mListFoot);
        mListFoot.initData();


    }
    /**
     * 设置监听
     */
    private void setListener() {
        mScan.setOnClickListener(this);
        mShopCar.setOnClickListener(this);
        mTitle.setOnClickListener(this);
        fl_store_loading_refresh.setOnClickListener(this);

    }

    private StoreGoodsBean parseGoodsJson(String json) {
        return new Gson().fromJson(json, StoreGoodsBean.class);
    }


    /**
     * 解析数据
     * @param json
     * @return
     */
    private StoreBean parseJson(String json) {
        return new Gson().fromJson(json, StoreBean.class);
    }

    @Override
    public void destoryData() {
        super.destoryData();
    }

    @Override
    public void onDestroy() {
        Log.e("TAG", "onDestroy()");
        currGoodsPage = 2;
//        //移除listview中的头部和底部视图，在创建的时候会重建创建
//        mListView.removeHeaderView(mListHead.getRootView());
//        mListView.removeFooterView(mListFoot.getRootView());
        super.onDestroy();
    }

    @Override
    public void onClick(View v) {
        if (v == mScan) {
//            Toast.makeText(activity, "扫描", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(activity, CaptureActivity.class);
            activity.startActivity(intent);
        } else if (v == mShopCar) {
            Toast.makeText(activity, "购物车", Toast.LENGTH_SHORT).show();
        } else if (v == mTitle) {
            Toast.makeText(activity, "搜索", Toast.LENGTH_SHORT).show();
        }
    }
}
