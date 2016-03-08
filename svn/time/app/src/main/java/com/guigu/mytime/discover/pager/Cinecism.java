package com.guigu.mytime.discover.pager;

import android.app.Activity;
import android.os.Handler;
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
import com.android.volley.toolbox.NetworkImageView;
import com.android.volley.toolbox.StringRequest;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.guigu.mytime.R;
import com.guigu.mytime.base.BaseDiscoverPager;
import com.guigu.mytime.discover.bean.CinecismBean;
import com.guigu.mytime.discover.bean.DiscoverRefreshListView;
import com.guigu.mytime.discover.bean.Discover_head_imgs_bean;
import com.guigu.mytime.discover.utils.Singletonutils;
import com.guigu.mytime.utils.ConstantUtils;

import java.io.UnsupportedEncodingException;
import java.util.List;


/**
 * Created by chu on 2016/2/29.
 */
public class Cinecism extends BaseDiscoverPager implements AdapterView.OnItemClickListener {
    private static final String TAG =Cinecism.class.getSimpleName() ;

    public Cinecism(Activity activity) {
        super(activity);

    }

    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            discover_listview.setAdapter(new cinecismAdapter());
            discover_listview.setOnRefreshListener(new mOnRefreshListener());

        }
    };

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        CinecismBean cinecismBean = CsListBean.get(position);
        int id1 = cinecismBean.getId();


    }

    class mOnRefreshListener implements DiscoverRefreshListView.OnRefreshListener{

        @Override
        public void onPullDownRefresh() {
            getDataFromNet();
        }

        @Override
        public void onLoadMore() {

        }
    }

    class cinecismAdapter extends BaseAdapter{

        @Override
        public int getCount() {
            return CsListBean.size();
        }

        @Override
        public Object getItem(int position) {
            return CsListBean.get(position);
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        CinecismViewHolder viewHoldler;
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null){
                viewHoldler = new CinecismViewHolder();
                convertView = View.inflate(activity, R.layout.cinecism_item,null);
                viewHoldler.iv_discuss_cine = (NetworkImageView) convertView.findViewById(R.id.iv_discuss_cine);
                viewHoldler.iv_discuss_userimage = (NetworkImageView) convertView.findViewById(R.id.iv_discuss_userimage);
                viewHoldler.tv_discuss_title = (TextView) convertView.findViewById(R.id.tv_discuss_title);
                viewHoldler.tv_discuss_content = (TextView) convertView.findViewById(R.id.tv_discuss_content);
                viewHoldler.tv_discuss_nickname = (TextView) convertView.findViewById(R.id.tv_discuss_nickname);
                viewHoldler.tv_discuss_cinename = (TextView) convertView.findViewById(R.id.tv_discuss_cinename);
                viewHoldler.tv_dis_dis_item_score = (TextView) convertView.findViewById(R.id.tv_dis_dis_item_score);

                convertView.setTag(viewHoldler);
            }else {
                viewHoldler = (CinecismViewHolder) convertView.getTag();
            }

            CinecismBean disDisItem = CsListBean.get(position);
            String rating = disDisItem.getRating();
            if (rating.equals("0.0")){
                viewHoldler.tv_dis_dis_item_score.setVisibility(View.GONE);
            }else {
                viewHoldler.tv_dis_dis_item_score.setText(rating);
            }
//
            viewHoldler.tv_discuss_nickname.setText(disDisItem.getNickname()+"-评");
            viewHoldler.tv_discuss_cinename.setText(disDisItem.getRelatedObj().getTitle());
            viewHoldler.tv_discuss_title.setText( disDisItem.getTitle());
            viewHoldler.tv_discuss_content.setText(disDisItem.getSummary());

            ImageLoader imageLoader = Singletonutils.getInstance(activity).getImageLoader();
            viewHoldler.iv_discuss_cine.setImageUrl(disDisItem.getRelatedObj().getImage(), imageLoader);
            viewHoldler.iv_discuss_userimage.setImageUrl(disDisItem.getUserImage(), imageLoader);

            return convertView;
        }
    }

    static class CinecismViewHolder{
        TextView tv_discuss_title;
        TextView tv_discuss_content;
        TextView tv_discuss_nickname;
        TextView tv_discuss_cinename;
        NetworkImageView iv_discuss_cine;
        NetworkImageView iv_discuss_userimage;

        TextView tv_dis_dis_item_score;

    }

    private List<CinecismBean> CsListBean;

    private LinearLayout rl_discover;
    private DiscoverRefreshListView discover_listview;
    private View head_cinecism;
    private TextView tv_head_dis_dis1;
    private TextView tv_head_dis_dis2;
    private ImageView iv_discover_head_news;
    private ImageView iv_dis_dis_small;


    @Override
    public View initView() {

        rl_discover = (LinearLayout) View.inflate(activity, R.layout.discover_listview, null);
        discover_listview = (DiscoverRefreshListView) rl_discover.findViewById(R.id.discover_listview);
        head_cinecism=View.inflate(activity, R.layout.head_cinecism,null);
        tv_head_dis_dis1 = (TextView) head_cinecism.findViewById(R.id.tv_head_dis_dis1);
        tv_head_dis_dis2 = (TextView) head_cinecism.findViewById(R.id.tv_head_dis_dis2);
        iv_discover_head_news = (ImageView) head_cinecism.findViewById(R.id.iv_discover_head_news);
        iv_dis_dis_small = (ImageView) head_cinecism.findViewById(R.id.iv_dis_dis_small);

        discover_listview.setOnItemClickListener(this);
        discover_listview.addHeaderView(head_cinecism);
        return discover_listview;
    }

    @Override
    public void initData() {
        super.initData();
        getDataFromNet();
    }


    private void getDataFromNet() {
        RequestQueue requestQueue = Singletonutils.getInstance(activity).getRequestQueue();
        StringRequest request = new StringRequest(Request.Method.GET, ConstantUtils.discoverItem_discuss_baseUrl, new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {
                Log.e(TAG, "影评恭喜联网成功 s 《《《《《《《《《《《== " + s);
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
                Log.e(TAG, "影评请求成功head data =----------------------------- " + s);
                discover_listview.onRefreshFinish(true);
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
        Discover_head_imgs_bean discoverHeadImgsBean = gson.fromJson(s, Discover_head_imgs_bean.class);

        Log.e(TAG, "头数据请求成功 =========================================");

        ImageLoader imageLoader = Singletonutils.getInstance(activity).getImageLoader();
        imageLoader.get(discoverHeadImgsBean.getReview().getImageUrl(), ImageLoader.getImageListener(iv_discover_head_news,
                R.drawable.img_default, R.drawable.img_default));
        imageLoader.get(discoverHeadImgsBean.getReview().getPosterUrl(), ImageLoader.getImageListener(iv_dis_dis_small,
                R.drawable.img_default, R.drawable.img_default));
        tv_head_dis_dis1.setText(discoverHeadImgsBean.getReview().getMovieName());
        tv_head_dis_dis2.setText(discoverHeadImgsBean.getReview().getTitle());
    }

    private void processData(String s) {


        Gson gson = new Gson();
        CsListBean = gson.fromJson(s, new TypeToken<List<CinecismBean>>() {
        }.getType());
//        Log.e(TAG, "影评discoverDiscusslist: ==============="+discoverDiscusslist.get(1).getNickname() );
        handler.sendEmptyMessage(1);
    }
}
