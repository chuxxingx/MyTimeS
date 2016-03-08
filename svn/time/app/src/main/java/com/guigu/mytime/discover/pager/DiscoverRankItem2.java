package com.guigu.mytime.discover.pager;


import android.app.Activity;
import android.graphics.Color;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.StringRequest;
import com.google.gson.Gson;
import com.guigu.mytime.R;
import com.guigu.mytime.discover.bean.DiscoverRefreshListView;
import com.guigu.mytime.discover.bean.RankItemBean;
import com.guigu.mytime.discover.utils.Singletonutils;
import com.guigu.mytime.utils.ConstantUtils;

import org.xutils.image.ImageOptions;
import org.xutils.x;

import java.io.UnsupportedEncodingException;
import java.util.List;

public class DiscoverRankItem2 extends Activity {
    private static final String TAG = DiscoverRankItem2.class.getSimpleName();
    private Boolean loadMore = false;
    private String item_url = "http://api.m.mtime.cn/TopList/TopListDetails.api?pageIndex=";
    int page;
    private String url;
    private String total_url;
    private DiscoverRefreshListView discover_listing_listview;
    private TextView listing_item_headname;
    private TextView listing_item_headsummary;

    private FrameLayout fl__main_animation;
    private ImageView iv_main_loading;
    private FrameLayout discover_refresh_failed;
    private ImageView discover_refresh_failed_image;
    private AnimationDrawable animationDrawable;

    private List<RankItemBean.MoviesEntity> mListingList;
    private MyAdapter adapter;
    private ImageOptions imageOptions;

    private RankItemBean.TopListEntity topList;
    private View topView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_discover_rank_item);

        initView();
        initData();
        initHeadView();

    }

    private void initView() {
        discover_listing_listview = (DiscoverRefreshListView) findViewById(R.id.discover_listing_listview);
    }

    private void initData() {
        url = getIntent().getData().toString();//&topListId+listing这个类传来的id

        getDataFromNet();
    }

    private void initHeadView() {

        if (topView == null) {
            topView = View.inflate(this, R.layout.discover_listview_header_refresh2, null);
            listing_item_headname = (TextView) topView.findViewById(R.id.listing_item_headname);
            listing_item_headsummary = (TextView) topView.findViewById(R.id.listing_item_headsummary);
            discover_listing_listview.addHeaderView(topView);
        }

    }

    private void getDataFromNet() {
        RequestQueue requestQueue = Singletonutils.getInstance(this).getRequestQueue();

        StringRequest request = new StringRequest(Request.Method.GET,
                ConstantUtils.discoverItem_paihan_baseUrl + 1, new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {
                Log.e(TAG, "恭喜联网成功 s 《《《《《《《《《《《== " + s);
                discover_listing_listview.onRefreshFinish(true);
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
        RankItemBean rankItemBean = gson.fromJson(s, RankItemBean.class);


        listing_item_headsummary.setText(rankItemBean.getTopList().getSummary());
    }

    /**
     * 解析数据
     */
    private void processData(String s) {
        Gson gson = new Gson();
        RankItemBean bean2 = gson.fromJson(s, RankItemBean.class);

        topList = bean2.getTopList();
        mListingList = bean2.getMovies();
        adapter = new MyAdapter();
        discover_listing_listview.setAdapter(adapter);

        discover_listing_listview.setOnRefreshListener(new MyOnRefreshListener());
        discover_listing_listview.setOnItemClickListener(new MyOnItemClickListene());

        listing_item_headname.setText(topList.getName());
        listing_item_headsummary.setText(topList.getSummary());

    }

    class MyAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return mListingList.size();
        }

        @Override
        public Object getItem(int position) {
            return mListingList.get(position);
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder = null;
            if (convertView == null) {
                holder = new ViewHolder();
                Log.e(TAG, "排行榜的item数据加载了++++++++++++++++++++++++++++++++++++++++++++++++");
                convertView = View.inflate(DiscoverRankItem2.this, R.layout.discover_listing_item, null);
                holder.listing_item_actor = (TextView) convertView.findViewById(R.id.listing_item_actor);
                holder.listing_item_rankNum = (TextView) convertView.findViewById(R.id.listing_item_rankNum);
                holder.listing_item_name = (TextView) convertView.findViewById(R.id.listing_item_name);
                holder.listing_item_rating = (TextView) convertView.findViewById(R.id.listing_item_rating);
                holder.listing_item_nameEn = (TextView) convertView.findViewById(R.id.listing_item_nameEn);
                holder.listing_item_director = (TextView) convertView.findViewById(R.id.listing_item_director);
                holder.listing_item_releaseDater = (TextView) convertView.findViewById(R.id.listing_item_releaseDater);
                holder.listing_item_remark = (TextView) convertView.findViewById(R.id.listing_item_remark);
                holder.listing_item_posterUrl = (ImageView) convertView.findViewById(R.id.listing_item_posterUrl);

                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }
            RankItemBean.MoviesEntity bean = mListingList.get(position);

            if (bean.getRating() == -1.0 || bean.getRating() == 0.0) {
                holder.listing_item_rating.setTextColor(Color.TRANSPARENT);
                holder.listing_item_rating.setBackground(null);
            }
            if (bean.getRankNum() == 1) {
                holder.listing_item_rankNum.setBackgroundResource(R.drawable.topmovie_list_num1);
            } else if (bean.getRankNum() == 2) {
                holder.listing_item_rankNum.setBackgroundResource(R.drawable.topmovie_list_num2);
            } else if (bean.getRankNum() == 3) {
                holder.listing_item_rankNum.setBackgroundResource(R.drawable.topmovie_list_num3);
            } else {

                holder.listing_item_rankNum.setBackgroundResource(R.drawable.topmovie_list_num_normal);
            }
            holder.listing_item_actor.setText(bean.getActor());
            holder.listing_item_rankNum.setText(bean.getRankNum() + "");
            holder.listing_item_name.setText("主演：" + bean.getActor());
            holder.listing_item_rating.setText(bean.getRating() + "");
            holder.listing_item_nameEn.setText(bean.getName());
            holder.listing_item_releaseDater.setText("上映时间：" + bean.getReleaseDate());
            holder.listing_item_remark.setText(bean.getRemark());

            x.image().bind(holder.listing_item_posterUrl, bean.getPosterUrl(), imageOptions);
            return convertView;
        }

        class ViewHolder {
            TextView listing_item_rankNum;
            TextView listing_item_name;
            TextView listing_item_rating;
            TextView listing_item_nameEn;
            TextView listing_item_director;
            TextView listing_item_actor;
            TextView listing_item_releaseDater;
            TextView listing_item_remark;
            ImageView listing_item_posterUrl;

        }
    }


        class MyOnRefreshListener implements DiscoverRefreshListView.OnRefreshListener {

            @Override
            public void onPullDownRefresh() {
                getDataFromNet();
            }

            @Override
            public void onLoadMore() {
                getDataFromNet();
            }
        }

        class MyOnItemClickListene implements AdapterView.OnItemClickListener {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(DiscoverRankItem2.this, "被点击了", Toast.LENGTH_SHORT).show();
            }
        }



}
