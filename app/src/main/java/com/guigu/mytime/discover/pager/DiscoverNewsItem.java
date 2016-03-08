package com.guigu.mytime.discover.pager;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.guigu.mytime.R;
import com.guigu.mytime.discover.bean.NewsItemBean;

/**
 *
 */

public class DiscoverNewsItem extends Activity {

    private TextView discover_item_title;
    private TextView discover_item_time;
    private TextView discover_item_intent;
    private TextView discove_item_editor;
    private TextView discove_item_comment;
    private TextView discove_item_related;
    private WebView discover_item_wv;

    private ProgressBar discover_item_progressbar;
    private String url2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_discover_news_item);
        discover_item_title = (TextView) findViewById(R.id.discover_item_title);
        discover_item_time = (TextView) findViewById(R.id.discover_item_time);
        discover_item_intent = (TextView) findViewById(R.id.discover_item_intent);
        discove_item_editor = (TextView) findViewById(R.id.discove_item_editor);
        discove_item_comment = (TextView) findViewById(R.id.discove_item_comment);
        discove_item_related = (TextView) findViewById(R.id.discove_item_related);
        discover_item_progressbar = (ProgressBar) findViewById(R.id.discover_item_progressbar);
        discover_item_wv = (WebView) findViewById(R.id.discover_item_wv);

        url2 = getIntent().getData().toString();

        getDataFromNet();


        discover_item_wv.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                discover_item_progressbar.setVisibility(View.GONE);
            }
        });
    }


        /**
         * 联网请求数据
         */
        private void getDataFromNet() {
            RequestQueue queue = Volley.newRequestQueue(this);
            StringRequest request = new StringRequest(Request.Method.GET,
                    url2, new Response.Listener<String>() {

                // 获取数据成功后会调
                @Override
                public void onResponse(String s) {
                    // 隐藏Loading动画
                    discover_item_progressbar.setVisibility(View.GONE);
                    Log.e("TAG", "获取新闻DiscoverNewsItem中的数据成功了==================++++++++++++======================");
                    // 解析数据
                    processdata(s);
                }
            }, new Response.ErrorListener() {
                // 获取数据失败后会调
                @Override
                public void onErrorResponse(VolleyError arg0) {
                    Log.e("TAG", "获取新闻DiscoverNewsItem中的数据失败了————————————————————————————————————————————————");
                }
            });
            queue.add(request);
        }


    private void processdata(String s) {
        Gson gson=new Gson();
        NewsItemBean newsItemBean=gson.fromJson(s,NewsItemBean.class);
        discover_item_title.setText(newsItemBean.getTitle());
        discover_item_time.setText(newsItemBean.getTime());
        discove_item_editor.setText(newsItemBean.getEditor());
        discove_item_comment.setText(newsItemBean.getCommentCount() + "");
        String content = newsItemBean.getContent();
        discover_item_wv.loadDataWithBaseURL(null, content, "text/html", "UTF-8", null);
        WebSettings settings = discover_item_wv.getSettings();
        settings.setJavaScriptEnabled(true);
    }
}