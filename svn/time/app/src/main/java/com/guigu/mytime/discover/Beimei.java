package com.guigu.mytime.discover;

import android.app.Activity;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
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
import com.guigu.mytime.R;
import com.guigu.mytime.base.BaseDiscoverPager;
import com.guigu.mytime.discover.bean.BeimeiListBean;
import com.guigu.mytime.discover.bean.DiscoverRefreshListView;
import com.guigu.mytime.discover.utils.Singletonutils;

import java.io.UnsupportedEncodingException;
import java.util.List;

/**
 * Created by chu on 2016/3/4.
 */
public class Beimei extends BaseDiscoverPager implements AdapterView.OnItemClickListener {
    private static final String TAG = Beimei.class.getSimpleName();
    private InlandDiscoverActivity inlandActivity;
    private List<BeimeiListBean.MoviesEntity> movies;

    private Handler handler = new Handler() {
        public void handleMessage(Message msg) {
            if (msg.what == 1) {
                discver_listview.setAdapter(new BeimeiAdapter());
                discver_listview.setOnRefreshListener(new mOnRefreshListener());

            }
        }
    };

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        BeimeiListBean.MoviesEntity moviesEntity = movies.get(position);
        int id1 = moviesEntity.getId();

    }

    class BeimeiAdapter extends BaseAdapter{

        @Override
        public int getCount() {
            return movies.size();
        }

        @Override
        public Object getItem(int position) {
            return movies.get(position);
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }
        BeimeiViewHoldler viewHoldler;
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            viewHoldler = new BeimeiViewHoldler();
            if (convertView == null){

                convertView = View.inflate(activity, R.layout.global_piaofang_item,null);

                viewHoldler.tv_global_item_num = (TextView) convertView.findViewById(R.id.tv_global_item_num);
                viewHoldler.tv_global_item_name = (TextView) convertView.findViewById(R.id.tv_global_item_name);
                viewHoldler.tv_global_item_rating = (TextView) convertView.findViewById(R.id.tv_global_item_rating);
                viewHoldler.tv_global_item_nameen = (TextView) convertView.findViewById(R.id.tv_global_item_nameen);
                viewHoldler.tv_global_item_week = (TextView) convertView.findViewById(R.id.tv_global_item_week);
                viewHoldler.tv_global_item_all = (TextView) convertView.findViewById(R.id.tv_global_item_all);
                viewHoldler.btn_global_item_ticket = (Button) convertView.findViewById(R.id.btn_global_item_ticket);
                viewHoldler.neiv_global_item = (NetworkImageView) convertView.findViewById(R.id.neiv_global_item);

                convertView.setTag(viewHoldler);
            }else {
                viewHoldler = (BeimeiViewHoldler) convertView.getTag();
            }

            BeimeiListBean.MoviesEntity moviesItem = movies.get(position);

            if (moviesItem.getRankNum() == 1){
                viewHoldler.tv_global_item_num.setBackgroundResource(R.drawable.icon_no_first);
            }else if (moviesItem.getRankNum() == 2){
                viewHoldler.tv_global_item_num.setBackgroundResource(R.drawable.icon_no_second);
            }else if (moviesItem.getRankNum() == 3){
                viewHoldler.tv_global_item_num.setBackgroundResource(R.drawable.icon_no_third);
            }else {
                viewHoldler.tv_global_item_num.setBackgroundResource(R.drawable.left_ball);
                viewHoldler.tv_global_item_num.setText(position+1+"");
            }

            if (moviesItem.isIsTicket()){
                viewHoldler.btn_global_item_ticket.setVisibility(View.VISIBLE);
            }else if ( !moviesItem.isIsTicket()){
                viewHoldler.btn_global_item_ticket.setVisibility(View.GONE);
            }


            viewHoldler.tv_global_item_name.setText(moviesItem.getName());
            viewHoldler.tv_global_item_nameen.setText(moviesItem.getNameEn());
            viewHoldler.tv_global_item_rating.setText(moviesItem.getRating()+"");
            viewHoldler.tv_global_item_all.setText(moviesItem.getTotalBoxOffice());
            viewHoldler.tv_global_item_week.setText(moviesItem.getWeekBoxOffice());

            ImageLoader imageLoader = Singletonutils.getInstance(activity).getImageLoader();
            viewHoldler.neiv_global_item.setImageUrl(moviesItem.getPosterUrl(), imageLoader);



            return convertView;
        }
    }

    static class BeimeiViewHoldler {


        TextView tv_global_item_num;
        NetworkImageView neiv_global_item;

        TextView tv_global_item_name;
        TextView tv_global_item_rating;
        TextView tv_global_item_nameen;
        TextView tv_global_item_week;
        TextView tv_global_item_all;
        Button btn_global_item_ticket;

    }

    class mOnRefreshListener implements DiscoverRefreshListView.OnRefreshListener{

        @Override
        public void onPullDownRefresh() {
            getDataFromNet();
        }

        @Override
        public void onLoadMore() {
            getDataFromNet();
        }
    }

    public Beimei(Activity activity) {
        super(activity);
        inlandActivity = (InlandDiscoverActivity) activity;

    }
    private LinearLayout rl_discover;
    private DiscoverRefreshListView discver_listview;

    @Override
    public View initView() {
        rl_discover = (LinearLayout) View.inflate(activity, R.layout.discover_listview, null);
        discver_listview = (DiscoverRefreshListView) rl_discover.findViewById(R.id.discover_listview);
        discver_listview.setOnItemClickListener(this);
        discver_listview.deleteFootView();

        return rl_discover;
    }

    @Override
    public void initData() {
        super.initData();
        getDataFromNet();
    }

    private void getDataFromNet() {
        RequestQueue requestQueue = Singletonutils.getInstance(activity).getRequestQueue();
        StringRequest request = new StringRequest(Request.Method.GET,
                url, new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {
                Log.e(TAG, "恭喜联网成功 s 《《《《《《《《《《《== " + s);
                discver_listview.onRefreshFinish(true);

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
        requestQueue.add(request);
    }

    private void processData(String s) {
        Gson gson=new Gson();
        BeimeiListBean beimeiListBean=gson.fromJson(s,BeimeiListBean.class);
        movies=beimeiListBean.getMovies();
        handler.sendEmptyMessage(1);
    }
}
