package com.guigu.mytime.discover.pager;

import android.app.Activity;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.StringRequest;
import com.google.gson.Gson;
import com.guigu.mytime.R;
import com.guigu.mytime.base.BaseDiscoverPager;
import com.guigu.mytime.discover.bean.DiscoverRefreshListView;
import com.guigu.mytime.discover.bean.Discover_head_imgs_bean;
import com.guigu.mytime.discover.bean.Rank_bean;
import com.guigu.mytime.discover.utils.Singletonutils;
import com.guigu.mytime.utils.ConstantUtils;

import java.io.UnsupportedEncodingException;
import java.util.List;


/**
 * Created by chu on 2016/2/29.
 */
public class Rank extends BaseDiscoverPager {
    private static final String TAG = Rank.class.getSimpleName();
    private int position=1;
    private String url = "http://api.m.mtime.cn/TopList/TopListOfAll.api?pageIndex=";
    private String item_url = "http://api.m.mtime.cn/News/Detail.api?&topListId=";

    public Rank(Activity activity) {
        super(activity);

    }

    private TextView tv_head_dis_news;
    private ImageView iv_head_dis_prevue;
    private ImageView iv_discover_head_news;
    private LinearLayout rl_discover;
    private View head_discover2;
    private DiscoverRefreshListView discover_listview;

    private List<Rank_bean.TopListsEntity> RankBeanList;
    RankAdapter rankAdapter = new RankAdapter();

    private android.os.Handler handler = new android.os.Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 1:
                    discover_listview.setAdapter(rankAdapter);
                    discover_listview.setOnRefreshListener(new mOnRefreshListener());
                    // 设置item的点击事件
                    discover_listview.setOnItemClickListener(new MyOnItemClickListener());
            }

        }
    };

    class MyOnItemClickListener implements AdapterView.OnItemClickListener{

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//            Rank_bean.TopListsEntity topListEntity=RankBeanList.get(position-1);
//            Intent intent = new Intent(activity, DiscoverNewsItem.class);
//            String listing_item_url = item_url +topListEntity.getId();
//            intent.putExtra("urlDetail",listing_item_url);
//            activity.startActivity(intent);
        }
    }


    class RankAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return RankBeanList.size();
        }

        @Override
        public Object getItem(int position) {
            return RankBeanList.get(position);
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        PaihanViewHoldler viewHoldler;

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                viewHoldler = new PaihanViewHoldler();
                convertView = View.inflate(activity, R.layout.rank_item, null);
                viewHoldler.tv1_rank_item = (TextView) convertView.findViewById(R.id.tv1_rank_item);
                viewHoldler.tv2_rank_item = (TextView) convertView.findViewById(R.id.tv2_rank_item);
                convertView.setTag(viewHoldler);
            } else {
                viewHoldler = (PaihanViewHoldler) convertView.getTag();
            }

            Rank_bean.TopListsEntity paihanItem1 = RankBeanList.get(position);
            viewHoldler.tv1_rank_item.setText(paihanItem1.getTopListNameCn());
            viewHoldler.tv2_rank_item.setText(paihanItem1.getSummary());


            return convertView;
        }
    }


    class  mOnRefreshListener  implements DiscoverRefreshListView.OnRefreshListener{

        @Override
        public void onPullDownRefresh() {
            getDataFromNet();
        }

        @Override
        public void onLoadMore() {
            getDataFromNet();
        }
    }

    static class PaihanViewHoldler {
        TextView tv1_rank_item;
        TextView tv2_rank_item;

    }

    @Override
    public View initView() {
        rl_discover = (LinearLayout) View.inflate(activity, R.layout.discover_listview, null);
        discover_listview = (DiscoverRefreshListView) rl_discover.findViewById(R.id.discover_listview);
        head_discover2 = View.inflate(activity, R.layout.head_discover2, null);
        iv_discover_head_news = (ImageView) head_discover2.findViewById(R.id.iv_discover_head_news);
        iv_head_dis_prevue = (ImageView) head_discover2.findViewById(R.id.iv_head_dis_prevue);
        tv_head_dis_news = (TextView) head_discover2.findViewById(R.id.tv_head_dis_news);
        discover_listview.addHeaderView(head_discover2);
        iv_head_dis_prevue.setVisibility(View.GONE);
        return discover_listview;
    }

    @Override
    public void initData() {
        super.initData();
        getDataFromNet();
    }

    private void getDataFromNet() {

        RequestQueue requestQueue = Singletonutils.getInstance(activity).getRequestQueue();

        StringRequest request = new StringRequest(Request.Method.GET,
                ConstantUtils.discoverItem_paihan_baseUrl + 1, new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {
                Log.e(TAG, "恭喜联网成功 s 《《《《《《《《《《《== " + s);
                discover_listview.onRefreshFinish(true);
                processData(s);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Log.e(TAG, "联网失败 《《《《《《《《《《《《《《《《《《");
            }
        }) {
            @Override
            protected Response<String> parseNetworkResponse(NetworkResponse response) {
                try {
                    String jsonData = new String(response.data, "UTF-8");
                    return Response.success(jsonData, HttpHeaderParser.parseCacheHeaders(response));
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                return super.parseNetworkResponse(response);
            }
        };

        StringRequest request1 = new StringRequest(Request.Method.GET, ConstantUtils.discoverAll_head_baseUrl, new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {
                Log.e(TAG, "请求成功head data =----------------------------- " + s);
                processHeadData(s);
            }


        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Log.e(TAG, "请求失败----------------------------------------------");
            }
        }) {
            @Override
            protected Response<String> parseNetworkResponse(NetworkResponse response) {
                try {
                    String jsonData = new String(response.data, "UTF-8");
                    return Response.success(jsonData, HttpHeaderParser.parseCacheHeaders(response));
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                return super.parseNetworkResponse(response);
            }
        };
        requestQueue.add(request1);

        requestQueue.add(request);
    }

    private void processHeadData(String s) {
        Gson gson = new Gson();
        Discover_head_imgs_bean discover_head_imgs_bean = gson.fromJson(s, Discover_head_imgs_bean.class);


        ImageLoader imageLoader = Singletonutils.getInstance(activity).getImageLoader();
        imageLoader.get(discover_head_imgs_bean.getTopList().getImageUrl(), ImageLoader.getImageListener(iv_discover_head_news,
                R.drawable.img_default, R.drawable.img_default));
        tv_head_dis_news.setText(discover_head_imgs_bean.getTopList().getTitle());


    }

    private void processData(String s) {
        Gson gson = new Gson();
        Rank_bean rank_bean = gson.fromJson(s, Rank_bean.class);


        if (position == 1) {
            RankBeanList=rank_bean.getTopLists();
            handler.sendEmptyMessage(1);
        }else {

        }

    }

}