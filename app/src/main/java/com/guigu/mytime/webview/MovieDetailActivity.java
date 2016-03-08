package com.guigu.mytime.webview;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.guigu.mytime.R;

import java.lang.reflect.InvocationTargetException;

import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.onekeyshare.OnekeyShare;

public class MovieDetailActivity extends Activity implements View.OnClickListener {

    private LinearLayout ll_movie_detail_back;
    private Button btn_movie_detail_share;
    private WebView webview;
    private ImageView iv_loading;
    private AnimationDrawable drawable;
    private WebSettings settings;
    private String url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);

        initView();

        url = getIntent().getStringExtra("urlDetail");
        Log.e("TAG", "webview url=="+url);
        settings = webview.getSettings();
        settings.setTextSize(WebSettings.TextSize.NORMAL);
        settings.setJavaScriptEnabled(true); //设置支持javaScript
        settings.setBuiltInZoomControls(true); //设置缩放按钮
        settings.setUseWideViewPort(true); //设置双击变大变小
        settings.setPluginState(WebSettings.PluginState.ON);
        webview.loadUrl(url);

        webview.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                drawable.stop();
                iv_loading.setVisibility(View.GONE);
            }

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });

        webview.setWebChromeClient(new WebChromeClient(){
            @Override
            public void onShowCustomView(View view, int requestedOrientation, CustomViewCallback callback) {
                super.onShowCustomView(view, requestedOrientation, callback);

            }

            @Override
            public void onShowCustomView(View view, CustomViewCallback callback) {
                super.onShowCustomView(view, callback);
            }
        });

    }

    @Override
    protected void onPause() {
        super.onPause();
        //停止播放：在页面的onPause方法中使用：
        try {
            webview.getClass().getMethod("onPause").invoke(webview, (Object[]) null);//（低版本测试正常）
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(webview!=null){
            webview.destroy();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        //获取获取焦点，就是点击home键后再启动onstart--->onResume
        try {
            webview.getClass().getMethod("onResume").invoke(webview, (Object[]) null);//（低版本测试正常）
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK) && webview.canGoBack()) {
            webview.goBack();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
    private void initView() {
        ll_movie_detail_back = (LinearLayout) findViewById(R.id.ll_movie_detail_back);
        btn_movie_detail_share = (Button) findViewById(R.id.btn_movie_detail_share);
        webview = (WebView) findViewById(R.id.webview);
        iv_loading = (ImageView) findViewById(R.id.iv_loading);
        drawable = (AnimationDrawable) iv_loading.getBackground();
        drawable.start();
        ll_movie_detail_back.setOnClickListener(this);
        btn_movie_detail_share.setOnClickListener(this);
        webview.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                showTextSizeDialog();
                return true;
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ll_movie_detail_back:
                finish();
                break;
            case R.id.btn_movie_detail_share:
                showShare();
                break;
        }
    }

    private void showShare() {
        ShareSDK.initSDK(this);
        OnekeyShare oks = new OnekeyShare();
        //关闭sso授权
        oks.disableSSOWhenAuthorize();

// 分享时Notification的图标和文字  2.5.9以后的版本不调用此方法
        //oks.setNotification(R.drawable.ic_launcher, getString(R.string.app_name));
        // title标题，印象笔记、邮箱、信息、微信、人人网和QQ空间使用
        oks.setTitle(getString(R.string.share));
        // titleUrl是标题的网络链接，仅在人人网和QQ空间使用
        oks.setTitleUrl("http://sharesdk.cn");
        // text是分享文本，所有平台都需要这个字段
        oks.setText("我是分享文本");
        // imagePath是图片的本地路径，Linked-In以外的平台都支持此参数
        //oks.setImagePath("/sdcard/test.jpg");//确保SDcard下面存在此张图片
        // url仅在微信（包括好友和朋友圈）中使用
        oks.setUrl("http://sharesdk.cn");
        // comment是我对这条分享的评论，仅在人人网和QQ空间使用
        oks.setComment("我是测试评论文本");
        // site是分享此内容的网站名称，仅在QQ空间使用
        oks.setSite(getString(R.string.app_name));
        // siteUrl是分享此内容的网站地址，仅在QQ空间使用
        oks.setSiteUrl("http://sharesdk.cn");

// 启动分享GUI
        oks.show(this);
    }

    private int realSize = 2;
    private int size = 2;

    private void showTextSizeDialog() {
        String[] ds = {"超大字体", "大号字体", "正常字体", "小号字体", "超小字体"};
        new AlertDialog.Builder(this)
                .setTitle("设置字体大小")
                .setSingleChoiceItems(ds, realSize, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        size = which;
                    }
                })
                .setNegativeButton("取消", null)
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        realSize = size;
                        changeTextSize(realSize);
                    }
                })
                .show();
    }

    private void changeTextSize(int position) {
        switch (position) {
            case 0:
                settings.setTextSize(WebSettings.TextSize.LARGEST);
                break;
            case 1:
                settings.setTextSize(WebSettings.TextSize.LARGER);
                break;
            case 2:
                settings.setTextSize(WebSettings.TextSize.NORMAL);
                break;
            case 3:
                settings.setTextSize(WebSettings.TextSize.SMALLER);
                break;
            case 4:
                settings.setTextSize(WebSettings.TextSize.SMALLEST);
                break;
        }
    }
}
