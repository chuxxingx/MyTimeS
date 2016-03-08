package com.guigu.mytime.me;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.guigu.mytime.R;

import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

public class LoginActivity extends Activity implements View.OnClickListener {
    @ViewInject(R.id.iv_store_title_back)
    private ImageView iv_store_title_back;
    @ViewInject(R.id.et_number)
    private EditText et_number;
    @ViewInject(R.id.et_password)
    private EditText et_password;
    @ViewInject(R.id.ib_qq_login)
    private ImageView ib_qq_login;
    @ViewInject(R.id.ib_weibo_login)
    private ImageView login_weibo_icon;
    @ViewInject(R.id.btn_login)
    private Button btn_login;
    //友盟分享
//    private UMShareAPI mShareAPI = null;
//    private SHARE_MEDIA platform = null;
//    private UMAuthListener umAuthListener = new UMAuthListener() {
//        @Override
//        public void onComplete(SHARE_MEDIA platform, int action, Map<String, String> data) {
//            Toast.makeText(getApplicationContext(), "Authorize succeed", Toast.LENGTH_SHORT).show();
//
//        }
//
//        @Override
//        public void onError(SHARE_MEDIA platform, int action, Throwable t) {
//            Toast.makeText( getApplicationContext(), "Authorize fail", Toast.LENGTH_SHORT).show();
//        }
//
//        @Override
//        public void onCancel(SHARE_MEDIA platform, int action) {
//            Toast.makeText( getApplicationContext(), "Authorize cancel", Toast.LENGTH_SHORT).show();
//        }
//    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        x.view().inject(this);
        iv_store_title_back.setOnClickListener(this);
        ib_qq_login.setOnClickListener(this);
        login_weibo_icon.setOnClickListener(this);
        btn_login.setOnClickListener(this);
       // mShareAPI = UMShareAPI.get( this );
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_store_title_back :
                finish();
                break;
            case R.id.ib_qq_login ://qq第三方登录
//                platform = SHARE_MEDIA.QQ;
//                mShareAPI.doOauthVerify(LoginActivity.this, platform, umAuthListener);
                break;
            case R.id.btn_login ://登陆
                String password = et_password.getText().toString();
                String number = et_number.getText().toString();
                Toast.makeText(LoginActivity.this,"账号="+number+",password="+password,Toast.LENGTH_SHORT).show();
                break;
            case R.id.ib_weibo_login://微博第三方登录
//                platform = SHARE_MEDIA.SINA;
//                mShareAPI.doOauthVerify(LoginActivity.this, platform, umAuthListener);
                break;
        }
    }
}
