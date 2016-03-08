package com.guigu.mytime.buyticket.bean;

import android.app.Activity;
import android.view.View;
import android.widget.Toast;

import java.util.List;

/**
 * Created by Administrator on 2016/3/4.
 */
public class PMIAOnClickListener implements View.OnClickListener {

    private List<View> mViewLists;
    private Activity mActivity;
    private boolean isTicket;
    private int position;

    public PMIAOnClickListener(List<View> viewLists ,Activity activity,boolean isTicket){
        mViewLists = viewLists;
        mActivity = activity;
        this.isTicket = isTicket;
    }

    public PMIAOnClickListener setPosition(int position) {
        this.position = position;
        return this;
    }

    @Override
    public void onClick(View v) {

        for(int i = 0;i<mViewLists.size();i++){
            View view = mViewLists.get(i);
            if(v == view){
                switch(i) {
                    case 0:
                        Toast.makeText(mActivity, "想看", Toast.LENGTH_SHORT).show();
                        return;
                    case 1:
                        Toast.makeText(mActivity, "评价", Toast.LENGTH_SHORT).show();
                        return;
                    case 2:
                    case 3:
                        if(isTicket){
                            Toast.makeText(mActivity, "买票", Toast.LENGTH_SHORT).show();

                        }else{
                            Toast.makeText(mActivity, "查票讯", Toast.LENGTH_SHORT).show();
                        }
                        return;
                    case 4:
                        mActivity.finish();
                        return;
                    case 5:
                        Toast.makeText(mActivity, "分享", Toast.LENGTH_SHORT).show();
                        return;
                    case 6:
                        Toast.makeText(mActivity, "收藏", Toast.LENGTH_SHORT).show();
                        return;
                    default:

                        return;

                }
            }
        }

        //}
    }
}
