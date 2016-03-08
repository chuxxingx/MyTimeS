package com.guigu.mytime.discover.pager;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
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
import android.widget.Toast;

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
import com.guigu.mytime.discover.InlandDiscoverActivity;
import com.guigu.mytime.discover.bean.DiscoverRefreshListView;
import com.guigu.mytime.discover.bean.DiscoverTopBean;
import com.guigu.mytime.discover.bean.Discover_head_imgs_bean;
import com.guigu.mytime.discover.bean.NewsBean;
import com.guigu.mytime.discover.utils.Singletonutils;
import com.guigu.mytime.utils.CacheUtils;
import com.guigu.mytime.utils.ConstantUtils;

import java.io.UnsupportedEncodingException;
import java.util.List;


/**
 * Created by chu on 2016/2/29.
 */
public class NewsPager extends BaseDiscoverPager {


    // 保存点击过item数据的key值
    private String IS_READ_NEWS = "is_read_news";
    private List<NewsBean.NewsListEntity> disnewsList;
    private DiscoverTopBean.Top_News topnews;
    private static final String TAG = NewsPager.class.getSimpleName();
    // 每个item基本的url，item的url=基本的url+item的id;
    private String baseitemurl = "http://api.m.mtime.cn/News/Detail.api?newsId=";


    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            if (msg.what == 1) {
                discover_listview.setAdapter(mDiscoverNewsAdapter);
                discover_listview.setOnRefreshListener(mOnRefreshListener);
                discover_listview.setOnItemClickListener(new MyOnItemClickListener());
            }
        }
    };

    private DiscoverNewsAdapter mDiscoverNewsAdapter = new DiscoverNewsAdapter();


    class DiscoverNewsAdapter  extends BaseAdapter {

        @Override
        public int getCount() {
            return disnewsList.size();
        }

        @Override
        public Object getItem(int position) {
            return disnewsList.get(position);
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }



        @Override
        public int getItemViewType(int position) {
            return disnewsList.get(position).getType();

        }

        @Override
        public int getViewTypeCount() {
            return 3;

        }
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHoldler viewHoldler1 = null;
            ViewHoldler1 viewHoldler2 = null;
            ViewHoldler2 viewHoldler3 = null;
            int disNewsType = disnewsList.get(position).getType();
            if (convertView == null) {
                if (disNewsType == 0) {
                    convertView = View.inflate(activity, R.layout.discover_list_item_img, null);
                    viewHoldler1 = new ViewHoldler();
                    viewHoldler1.title = (TextView) convertView.findViewById(R.id.tv_dis_newsitem_title1);
                    viewHoldler1.title2 = (TextView) convertView.findViewById(R.id.tv_dis_newsitem_title2);
                    viewHoldler1.timeBefore = (TextView) convertView.findViewById(R.id.tv_long_dis_listitem);
                    viewHoldler1.commentCount = (TextView) convertView.findViewById(R.id.tv_discuss_dis_listitem);
                    viewHoldler1.iv_dis_newsitem = (NetworkImageView) convertView.findViewById(R.id.iv_dis_newsitem);
                    convertView.setTag(viewHoldler1);
                } else if (disNewsType == 2) {
                    convertView = View.inflate(activity, R.layout.discover_list_item_video, null);
                    viewHoldler2 = new ViewHoldler1();
                    viewHoldler2.title = (TextView) convertView.findViewById(R.id.tv_dis_newsitem_title1);
                    viewHoldler2.title2 = (TextView) convertView.findViewById(R.id.tv_dis_newsitem_title2);
                    viewHoldler2.timeBefore = (TextView) convertView.findViewById(R.id.tv_long_dis_listitem);
                    viewHoldler2.commentCount = (TextView) convertView.findViewById(R.id.tv_discuss_dis_listitem);
                    viewHoldler2.iv_dis_newsitem = (NetworkImageView) convertView.findViewById(R.id.iv_dis_newsitem);
                    convertView.setTag(viewHoldler2);

                } else if (disNewsType == 1) {
                    convertView = View.inflate(activity, R.layout.discover_list_item_3img, null);
                    viewHoldler3 = new ViewHoldler2();
                    viewHoldler3.tv_dis_news_3img_title = (TextView) convertView.findViewById(R.id.tv_dis_news_3img_title);
                    viewHoldler3.tv_dis_news_3img = (TextView) convertView.findViewById(R.id.tv_dis_news_3img);
                    viewHoldler3.tv_dis_news_3img_discuss = (TextView) convertView.findViewById(R.id.tv_dis_news_3img_discuss);
                    viewHoldler3.iv1_dis_item3 = (NetworkImageView) convertView.findViewById(R.id.iv1_dis_item3);
                    viewHoldler3.iv2_dis_item3 = (NetworkImageView) convertView.findViewById(R.id.iv2_dis_item3);
                    viewHoldler3.iv3_dis_item3 = (NetworkImageView) convertView.findViewById(R.id.iv3_dis_item3);
                    convertView.setTag(viewHoldler3);

                }
            } else {
                switch (disNewsType) {
                    case 0:
                        viewHoldler1 = (ViewHoldler) convertView.getTag();
                        break;
                    case 2:
                        viewHoldler2 = (ViewHoldler1) convertView.getTag();
                        break;
                    case 1:
                        viewHoldler3 = (ViewHoldler2) convertView.getTag();
                        break;

                }
            }



        NewsBean.NewsListEntity discoverNewsListItem = disnewsList.get(position);
        switch (disNewsType) {
            case 0:
                viewHoldler1.title.setText(discoverNewsListItem.getTitle());
                viewHoldler1.title2.setText(discoverNewsListItem.getTitle2());
                viewHoldler1.timeBefore.setText("x小时前");
                viewHoldler1.commentCount.setText("评论:" + discoverNewsListItem.getCommentCount());
                ImageLoader imageLoader = Singletonutils.getInstance(activity).getImageLoader();
//                    imageLoader.get(discoverNewsListItem.getImage(), ImageLoader.getImageListener(viewHoldler1.iv_dis_newsitem,
//                            R.drawable.girl, R.drawable.girl));
                viewHoldler1.iv_dis_newsitem.setImageUrl(discoverNewsListItem.getImage(), imageLoader);
                viewHoldler1.iv_dis_newsitem.setDefaultImageResId(R.drawable.img_default);//设置默认图片
                viewHoldler1.iv_dis_newsitem.setErrorImageResId(R.drawable.img_default);//设置错误图片
                break;
            case 2:
                viewHoldler2.title.setText(discoverNewsListItem.getTitle());
                viewHoldler2.title2.setText(discoverNewsListItem.getTitle2());
                viewHoldler2.timeBefore.setText("x小时前");
                viewHoldler2.commentCount.setText("评论:" + discoverNewsListItem.getCommentCount());
                ImageLoader imageLoader1 = Singletonutils.getInstance(activity).getImageLoader();
//                    imageLoader1.get(discoverNewsListItem.getImage(), ImageLoader.getImageListener(viewHoldler2.iv_dis_newsitem,
//                            R.drawable.girl, R.drawable.girl));
                viewHoldler2.iv_dis_newsitem.setImageUrl(discoverNewsListItem.getImage(), imageLoader1);
                viewHoldler2.iv_dis_newsitem.setDefaultImageResId(R.drawable.img_default);//设置默认图片
                viewHoldler2.iv_dis_newsitem.setErrorImageResId(R.drawable.img_default);//设置错误图片

                break;
            case 1:
                viewHoldler3.tv_dis_news_3img_title.setText(discoverNewsListItem.getTitle());
                viewHoldler3.tv_dis_news_3img.setText("x小时前");
                viewHoldler3.tv_dis_news_3img_discuss.setText("评论:" + discoverNewsListItem.getCommentCount());
                ImageLoader imageLoader2 = Singletonutils.getInstance(activity).getImageLoader();

                viewHoldler3.iv1_dis_item3.setImageUrl(discoverNewsListItem.getImages().get(0).getUrl1(), imageLoader2);
                viewHoldler3.iv1_dis_item3.setDefaultImageResId(R.drawable.img_default);//设置默认图片
                viewHoldler3.iv1_dis_item3.setErrorImageResId(R.drawable.img_default);//设置错误图片

                viewHoldler3.iv2_dis_item3.setImageUrl(discoverNewsListItem.getImages().get(1).getUrl1(), imageLoader2);
                viewHoldler3.iv2_dis_item3.setDefaultImageResId(R.drawable.img_default);//设置默认图片
                viewHoldler3.iv2_dis_item3.setErrorImageResId(R.drawable.img_default);//设置错误图片
                if (discoverNewsListItem.getImages().size() >=3){
                    Log.e(TAG, "dwdewfewfwefewfewfewf----"+discoverNewsListItem.getImages().size() );
                    viewHoldler3.iv3_dis_item3.setImageUrl(discoverNewsListItem.getImages().get(2).getUrl1(), imageLoader2);
                    viewHoldler3.iv3_dis_item3.setDefaultImageResId(R.drawable.img_default);//设置默认图片
                    viewHoldler3.iv3_dis_item3.setErrorImageResId(R.drawable.img_default);//设置错误图片
                }
                break;
        }
        return convertView;
    }
}

    private DiscoverRefreshListView.OnRefreshListener mOnRefreshListener = new DiscoverRefreshListView.OnRefreshListener() {
        @Override
        public void onPullDownRefresh() {
            getDataFromNet();
        }

        @Override
        public void onLoadMore() {
            getMoreDataFromNet();
        }
    };

    private int position = 1;

    private void getMoreDataFromNet() {
        RequestQueue requestQueue = Singletonutils.getInstance(activity).getRequestQueue();

        StringRequest request = new StringRequest(Request.Method.GET, ConstantUtils.discoverItem_news_baseUrl + (position), new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {
                Log.e(TAG, "加载更多              恭喜联网成功 s 《《《《《《《《《《《== " + s);
                discover_listview.onRefreshFinish(false);
                if ("{}".equals(s)) {
                    Toast.makeText(activity, "没有更多数据￥￥￥￥￥￥￥￥￥￥￥￥￥￥￥￥￥￥￥￥￥￥￥￥￥￥￥￥￥￥￥", Toast.LENGTH_SHORT).show();
                } else {
                    processData(s);
                }

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
        position++;
        requestQueue.add(request);
    }
    static  class ViewHoldler {
            int id;
            int type;
            NetworkImageView iv_dis_newsitem;

            TextView title;
            TextView title2;
            TextView timeBefore;
            TextView commentCount;

            TextView tv_dis_news_3img_title;
            TextView tv_dis_news_3img;
            TextView tv_dis_news_3img_discuss;

            ImageView iv1_dis_item3;
            ImageView iv2_dis_item3;
            ImageView iv3_dis_item3;


            String summary;
            String summaryInfo;
            String tag;
            int publishTime;
        }

    static  class ViewHoldler1 {
            int id;
            int type;
            NetworkImageView iv_dis_newsitem;

            TextView title;
            TextView title2;
            TextView timeBefore;
            TextView commentCount;

            TextView tv_dis_news_3img_title;
            TextView tv_dis_news_3img;
            TextView tv_dis_news_3img_discuss;

            ImageView iv1_dis_item3;
            ImageView iv2_dis_item3;
            ImageView iv3_dis_item3;


            String summary;
            String summaryInfo;
            String tag;
            int publishTime;
        }

    static class ViewHoldler2 {
            int id;
            int type;
            ImageView iv_dis_newsitem;

            TextView title;
            TextView title2;
            TextView timeBefore;
            TextView commentCount;

            TextView tv_dis_news_3img_title;
            TextView tv_dis_news_3img;
            TextView tv_dis_news_3img_discuss;

            NetworkImageView iv1_dis_item3;
            NetworkImageView iv2_dis_item3;
            NetworkImageView iv3_dis_item3;


            String summary;
            String summaryInfo;
            String tag;
            int publishTime;
        }

    public NewsPager(Activity activity) {
        super(activity);
    }


    private LinearLayout rl_discover;
    private DiscoverRefreshListView discover_listview;
    private View discover_list_header;
    private ImageView iv_discover_head_news;
    private TextView  tv_head_dis_news;
    private LinearLayout ll_dis_item_inland;
    private LinearLayout  ll_dis__item_global;
//
    @Override
    public View initView() {

        rl_discover = (LinearLayout) View.inflate(activity, R.layout.discover_listview, null);

        discover_listview = (DiscoverRefreshListView) rl_discover.findViewById(R.id.discover_listview);
        discover_list_header= View.inflate(activity, R.layout.discover_list_header,null);
        iv_discover_head_news= (ImageView) discover_list_header.findViewById(R.id.iv_discover_head_news);
        tv_head_dis_news= (TextView) discover_list_header.findViewById(R.id.tv_head_dis_news);
        ll_dis_item_inland= (LinearLayout) discover_list_header.findViewById(R.id.ll_dis_item_inland);
        ll_dis__item_global= (LinearLayout) discover_list_header.findViewById(R.id.ll_dis__item_global);


        ll_dis_item_inland.setOnClickListener(new InlandOnClickListener());
        ll_dis__item_global.setOnClickListener(new InlandOnClickListener());

        discover_listview.addHeaderView(discover_list_header);


        return rl_discover;
    }

    class InlandOnClickListener implements View.OnClickListener{

        @Override
        public void onClick(View v) {
            activity.startActivity(new Intent(activity, InlandDiscoverActivity.class));
        }
    }

    class MyOnItemClickListener implements AdapterView.OnItemClickListener{

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            String readed_news = CacheUtils.getString(activity, IS_READ_NEWS);
            if (!readed_news.contains(disnewsList.get(position - 1).getId() + ",")) {
                readed_news += disnewsList.get(position - 1).getId() + ",";
                CacheUtils.setString(activity, IS_READ_NEWS, readed_news);
                mDiscoverNewsAdapter.notifyDataSetChanged();
            }

            // 进入item的详情界面
            NewsBean.NewsListEntity list = disnewsList.get(position - 1);
            String itemurl = baseitemurl + list.getId();


            Intent intent = new Intent(activity, DiscoverNewsItem.class);
            intent.setData(Uri.parse(itemurl));
            activity.startActivity(intent);
        }
    }

    @Override
    public void initData() {
        super.initData();



        getDataFromNet();
    }
//
    private void getDataFromNet() {
        RequestQueue requestQueue = Singletonutils.getInstance(activity).getRequestQueue();

        StringRequest request = new StringRequest(Request.Method.GET, ConstantUtils.discoverItem_news_baseUrl + 1, new Response.Listener<String>() {
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
        }; StringRequest request1 = new StringRequest(Request.Method.GET, ConstantUtils.discoverAll_head_baseUrl, new Response.Listener<String>() {
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
        Discover_head_imgs_bean discoverHeadImgsBean = gson.fromJson(s, Discover_head_imgs_bean.class);
        Log.e(TAG, "头数据请求成功 =========================================");
        ImageLoader imageLoader = Singletonutils.getInstance(activity).getImageLoader();
        imageLoader.get(discoverHeadImgsBean.getNews().getImageUrl(), ImageLoader.getImageListener(iv_discover_head_news,
                R.drawable.img_default, R.drawable.img_default));
        tv_head_dis_news.setText(discoverHeadImgsBean.getNews().getTitle());


    }

    private void processData(String s) {
        Gson gson=new Gson();
        NewsBean newsbean=gson.fromJson(s,NewsBean.class);
        if(position == 1) {
            Log.e(TAG, "解析成功!!!!!!!!!!!!!!!!!!!!!! ");
            disnewsList=newsbean.getNewsList();

            handler.sendEmptyMessage(1);
        }else{
            List<NewsBean.NewsListEntity>newslist=newsbean.getNewsList();
            if(newsbean!=null&&newslist!=null)
                disnewsList.addAll(newslist);
                mDiscoverNewsAdapter.notifyDataSetChanged();
            }

        }

}
