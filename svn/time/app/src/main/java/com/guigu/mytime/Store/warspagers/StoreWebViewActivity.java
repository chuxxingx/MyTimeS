package com.guigu.mytime.Store.warspagers;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;

import com.guigu.mytime.R;

import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

/**
 * Created by Administrator on 2016/3/4.
 */
public class StoreWebViewActivity extends Activity implements View.OnClickListener {
    @ViewInject(R.id.iv_store_title_back)
    private ImageView iv_store_title_back;

    @ViewInject(R.id.iv_logo_title_store)
    private ImageView iv_logo_title_store;

    @ViewInject(R.id.wv_store)
    private WebView wv_store;

    @ViewInject(R.id.store_webview_title)
    private View store_webview_title;

    /**
     * WebView的设置
     */
    private WebSettings mSettings;
    private String mUrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webview_store);
        x.view().inject(this);

        Intent intent = getIntent();
        mUrl = intent.getStringExtra("");
        String more = intent.getStringExtra("more");
        if (more != null && more.equals("more")) {
            Log.e("StoreWebViewActivity", "more = " + more);
            store_webview_title.setVisibility(View.GONE);
        } else {
            store_webview_title.setVisibility(View.VISIBLE);
        }
        mSettings = wv_store.getSettings();
        wv_store.setWebViewClient(new MyWebViewClient());

        mSettings.setJavaScriptEnabled(true);
        wv_store.loadUrl(mUrl);

        setListener();
    }

    /**
     * 设置监听
     */
    private void setListener() {
        iv_store_title_back.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v == iv_store_title_back) {
            finish();
        }
    }

    class MyWebViewClient extends WebViewClient {
        @Override
        public void onPageFinished(WebView view, String url) {
            if (!mUrl.equals(url)) {
                store_webview_title.setVisibility(View.GONE);
            } else {
                store_webview_title.setVisibility(View.VISIBLE);
            }
        }
    }
}
