package com.guigu.mytime.buyticket.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.NetworkResponse;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.StringRequest;
import com.google.gson.Gson;
import com.guigu.mytime.R;
import com.guigu.mytime.buyticket.adapter.MyMovieRcyclerViewAdapter1;
import com.guigu.mytime.buyticket.bean.PersonInMovieBean;
import com.guigu.mytime.buyticket.bean.PlayingMovieBean;
import com.guigu.mytime.utils.ConstantUtils;
import com.guigu.mytime.volley.VolleyManager;

import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.io.UnsupportedEncodingException;

public class PlayMovieItemActivity1 extends AppCompatActivity implements View.OnClickListener {

    public static final String PLAYMOVIEITEM = "playmovieitem";//电影对应的集合中的位置

    @ViewInject(R.id.ib_back_movie_details_buyticket)
    private ImageButton ib_back_movie_details_buyticket;
    @ViewInject(R.id.favorite_paly_movie_details_buyticket)
    private ImageButton favorite_paly_movie_details_buyticket;
    @ViewInject(R.id.share_paly_movie_details_buyticket)
    private ImageButton share_paly_movie_details_buyticket;
    @ViewInject(R.id.tv_namepaly_movie_details_buyticket)
    private TextView tv_namepaly_movie_details_buyticket;
    @ViewInject(R.id.rcy_play_movie_item)
    private RecyclerView rcy_play_movie_item;
    private PersonInMovieBean personInMovieBean;
    private PlayingMovieBean.MsEntity msEntity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_movie_item1);
        x.view().inject(this);
        setListener();
        initData();
        initView();
    }

    /**
     * 加载布局
     */
    private void initView() {
        getDataFromeNet();
    }

    private void getDataFromeNet() {
        StringRequest request = new StringRequest(StringRequest.Method.GET, ConstantUtils.PERSONINMOVIE + "?movieId=" + msEntity.getId()
                , new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {
                Log.e("TAG", "s ==" + s);
                personInMovieBean = parseData(s);
                LinearLayoutManager layout = new LinearLayoutManager(PlayMovieItemActivity1.this);
                layout.setOrientation(LinearLayoutManager.VERTICAL);
                rcy_play_movie_item.setHasFixedSize(true);
                rcy_play_movie_item.setLayoutManager(layout);
                rcy_play_movie_item.setAdapter(new MyMovieRcyclerViewAdapter1(PlayMovieItemActivity1.this,msEntity,personInMovieBean));
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Toast.makeText(PlayMovieItemActivity1.this, "获取演员失败", Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            protected Response<String> parseNetworkResponse(NetworkResponse response) {
                try {
                    String s = new String(response.data,"utf-8");
                    return Response.success(s, HttpHeaderParser.parseCacheHeaders(response));
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                return super.parseNetworkResponse(response);
            }
        };
        VolleyManager.add(request);
    }
    /**
     * 解析数据
     */
    private PersonInMovieBean parseData(String json) {
        return new Gson().fromJson(json,PersonInMovieBean.class);
    }

    private void initData() {
        msEntity = (PlayingMovieBean.MsEntity) getIntent().getExtras().get(PLAYMOVIEITEM);
        assert msEntity != null;
        String t = msEntity.getT();
        tv_namepaly_movie_details_buyticket.setText(t);
    }

    private void setListener() {
        ib_back_movie_details_buyticket.setOnClickListener(this);
        share_paly_movie_details_buyticket.setOnClickListener(this);
        favorite_paly_movie_details_buyticket.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ib_back_movie_details_buyticket :
                finish();
                break;
        }
    }
}
