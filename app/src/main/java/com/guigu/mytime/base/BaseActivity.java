package com.guigu.mytime.base;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.guigu.mytime.R;

/**
 * Created by Administrator on 2016/1/22.
 */
public abstract class BaseActivity extends Activity implements View.OnClickListener {
    private LinearLayout ll_child_content;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);
        init();
        getContentView();
    }
    //加载视图
    private void init() {
        ll_child_content = (LinearLayout)findViewById(R.id.ll_child_content);
        View childView = getContentView();
        if( childView != null){
            LinearLayout.LayoutParams params=new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,-1);
            ll_child_content.addView(childView,params);
        }
    }

    public abstract View getContentView();
}
