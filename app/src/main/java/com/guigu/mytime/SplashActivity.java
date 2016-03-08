package com.guigu.mytime;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;

import com.guigu.mytime.utils.CacheUtils;

import cn.jpush.android.api.JPushInterface;

/**
 * 欢迎页面，获取当前的版本号
 */
public class SplashActivity extends Activity {
    private Handler handler = new Handler();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                //从sp中获取是否经历过引导的页面
                boolean isGuide = CacheUtils.getBoolean(SplashActivity.this, "isGuide");
                if(isGuide) {
                    startActivity(new Intent(SplashActivity.this, MainActivity.class));
                }else {
                    startActivity(new Intent(SplashActivity.this, GuideActivity.class));
                }
                finish();
            }
        },3000);
    }

    @Override
    protected void onResume() {
        super.onResume();
        JPushInterface.onResume(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        JPushInterface.onPause(this);
    }
}
