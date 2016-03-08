package com.guigu.mytime.pager;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.guigu.mytime.R;
import com.guigu.mytime.base.BasePager;
import com.guigu.mytime.me.LoginActivity;
import com.guigu.mytime.view.ObservableScrollView;

import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import zxing.CaptureActivity;

/**
 * Created by Administrator on 2016/2/29.
 */
public class My extends BasePager implements View.OnClickListener, ObservableScrollView.Callbacks {

    @ViewInject(R.id.scrollView_obser)
    private ObservableScrollView scrollView;

    @ViewInject(R.id.bt_login_user)
    private Button bt_login_user;

    @ViewInject(R.id.bt_regist_user)
    private Button bt_regist_user;

    @ViewInject(R.id.rl_user_scntwo)
    private RelativeLayout rl_user_scntwo;
    @ViewInject(R.id.rl_top)
    private RelativeLayout rl_top;


    public My(Activity activity) {
        super(activity);
    }

    @Override
    public View initView() {
        View view = View.inflate(activity, R.layout.my_fragment_view,null);
        x.view().inject(this,view);
        rl_user_scntwo.setOnClickListener(this);
        bt_login_user.setOnClickListener(this);
        bt_regist_user.setOnClickListener(this);
        scrollView.setCallbacks(this);
        scrollView.getViewTreeObserver().addOnGlobalLayoutListener(
                new ViewTreeObserver.OnGlobalLayoutListener() {
                    @Override
                    public void onGlobalLayout() {
                        onScrollChanged(scrollView.getScrollY());
                    }
                });
        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_login_user :
                activity.startActivity(new Intent(activity,LoginActivity.class));
                break;
            case R.id.bt_regist_user:

                break;
            case R.id.rl_user_scntwo:
                activity.startActivity(new Intent(activity, CaptureActivity.class));
                break;
        }
    }

    @Override
    public void onScrollChanged(int scrollY) {
        if(scrollY<0) {
            rl_top.getBackground().setAlpha(0);
        }else {
            if(scrollY<=255) {
                rl_top.getBackground().setAlpha(scrollY);
            }else {
                rl_top.getBackground().setAlpha(255);
            }
        }
    }

    @Override
    public void onDownMotionEvent() {

    }

    @Override
    public void onUpOrCancelMotionEvent() {

    }
}
