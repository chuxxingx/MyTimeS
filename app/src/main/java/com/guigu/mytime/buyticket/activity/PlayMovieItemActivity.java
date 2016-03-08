package com.guigu.mytime.buyticket.activity;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.guigu.mytime.R;
import com.guigu.mytime.buyticket.bean.PMIAOnClickListener;
import com.guigu.mytime.buyticket.bean.PlayingMovieBean;
import com.guigu.mytime.buyticket.view.AddPlayMovieItemView;
import com.guigu.mytime.utils.ConstantUtils;
import com.guigu.mytime.view.ObservableScrollView;

import org.xutils.common.Callback;
import org.xutils.common.util.DensityUtil;
import org.xutils.http.RequestParams;
import org.xutils.image.ImageOptions;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/3/3.
 */
public class PlayMovieItemActivity extends Activity implements ObservableScrollView.Callbacks {
    public static final String MOVIEID = "movidId";
    public static final String MOVIELISTPOSITION = "movieListPosition";
    //单个item对应的详情信息集合
    private  List<PlayingMovieBean.MsEntity> ms;
    //表示电影类型的图标集合类
    private LinearLayout ll_movietype_play_movie_detials;
    //电影名称，值只显示7个字符
    private TextView tv__namepaly_movie_details_buyticket;
    //返回按钮
    private ImageButton ib_back_movie_details_buyticket;
    //分享
    private ImageButton share_paly_movie_details_buyticket;
    //最爱
    private ImageButton favorite_paly_movie_details_buyticket;
    //顶部购买按钮
    private Button btn_buyticket_play_movie_item;
    //电影图片
    private ImageView icon_paly_movie_detials;

    //电影名称
    private TextView tv_ll_movie_name_detials_buyticket;
    //电影名
    private TextView tv_ll_movie_us_detials_buyticket;
    //评分
    private TextView tv_score_detials_buyticket;
    //电影时长
    private TextView tv_timemovie_detials_buyticket;
    //电影类型
    private TextView tv_typemovie_detials_buyticket;
    //上映时间
    private TextView tv_showTime_detials_buyticket;
    //想看按钮
    private Button btn_wantsee_detials_buyticket;
    //要评分按钮
    private Button btn_wantscore_detials_buyticket;

    //描述一句话
    private TextView tv_commonSpecial_play_movie_detial_buyticket;
    //滚动中的购买查看按钮
    private Button btn_scroll_buyticket_detials;
    //title
    private RelativeLayout rl_title_play_movie_detials;

    private ImageOptions options;

    private ObservableScrollView scr_play_movie_detials;
    //容器
    private LinearLayout ll_content_container_detials;

    private int px ;

    private int martop;

    private boolean isTicket;
    private FrameLayout.LayoutParams params;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_movie_detials);
        final int position_ = getIntent().getIntExtra(MOVIELISTPOSITION, -1);
        int movieId= getIntent().getIntExtra(MOVIEID, -1);
        initOptions();
        findView();
        getDataFromeNet(position_, movieId);
        scr_play_movie_detials.setCallbacks(this);
        scr_play_movie_detials.getViewTreeObserver().addOnGlobalLayoutListener(
                new ViewTreeObserver.OnGlobalLayoutListener() {
                    @Override
                    public void onGlobalLayout() {
                        onScrollChanged(scr_play_movie_detials.getScrollY());
                    }
                });

    }

    private void getDataFromeNet(final int position_, int movieId) {
        if(position_ != -1){
            String url = ConstantUtils.MOVIE_LIST + "?locationId=290";
            x.http().get(new RequestParams(url), new Callback.CommonCallback<String>() {
                @Override
                public void onSuccess(String result) {
                    PlayingMovieBean playingMovieBean = new Gson().fromJson(result, PlayingMovieBean.class);
                    PlayingMovieBean.MsEntity ms = playingMovieBean.getMs().get(position_);
                    initData(ms);
                }

                @Override
                public void onError(Throwable ex, boolean isOnCallback) {

                }

                @Override
                public void onCancelled(CancelledException cex) {

                }

                @Override
                public void onFinished() {

                }
            });
        }else if(movieId != -1){
            //http://api.m.mtime.cn/Showtime/MovieComments.api?movieId=209410&pageIndex=1
            String url = "http://api.m.mtime.cn/Showtime/MovieComments.api?movieId=" + movieId + "&pageIndex=1";

            x.http().get(new RequestParams(url), new Callback.CommonCallback<String>() {
                @Override
                public void onSuccess(String result) {
                    PlayingMovieBean.MsEntity msEntity = new Gson().fromJson(result, PlayingMovieBean.class).getMs().get(position_);
                    initData(msEntity);
                }

                @Override
                public void onError(Throwable ex, boolean isOnCallback) {

                }

                @Override
                public void onCancelled(CancelledException cex) {

                }

                @Override
                public void onFinished() {

                }
            });
        }
    }

    private void initOptions() {
        options = new ImageOptions.Builder()
                .setSize(DensityUtil.dip2px(120), DensityUtil.dip2px(155))
                .setRadius(DensityUtil.dip2px(5))
                        // 如果ImageView的大小不是定义为wrap_content, 不要crop.
                .setCrop(false)
                        // 加载中或错误图片的ScaleType
                        //.setPlaceholderScaleType(ImageView.ScaleType.MATRIX)
                .setImageScaleType(ImageView.ScaleType.CENTER_CROP)
                .build();
    }

    private void initData(PlayingMovieBean.MsEntity entity) {

        //添加尾部
        ll_content_container_detials.addView(new AddPlayMovieItemView(this, entity.getId()).view);

        //顶部购买按钮内容
        isTicket = entity.isIsTicket();
        List<View> list = new ArrayList<>();

        list.add(btn_wantsee_detials_buyticket);
        list.add(btn_wantscore_detials_buyticket);
        list.add(btn_scroll_buyticket_detials);
        list.add(btn_buyticket_play_movie_item);
        list.add(ib_back_movie_details_buyticket);
        list.add(share_paly_movie_details_buyticket);
        list.add(favorite_paly_movie_details_buyticket);

        PMIAOnClickListener pmiaOnClickListener = new PMIAOnClickListener(list,this,isTicket);
        px = DensityUtil.dip2px(23);
        martop = DensityUtil.dip2px(3);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(px,px);
        params.topMargin = martop;


        //顶部title的内容初始化
        ib_back_movie_details_buyticket.setOnClickListener(pmiaOnClickListener.setPosition(4));
        share_paly_movie_details_buyticket.setOnClickListener(pmiaOnClickListener.setPosition(5));
        favorite_paly_movie_details_buyticket.setOnClickListener(pmiaOnClickListener.setPosition(6));

        btn_buyticket_play_movie_item.setOnClickListener(pmiaOnClickListener.setPosition(3));
        //添加类型图片
        /**
         * 动态的添加
         *  <ImageView
         android:layout_marginTop="3dp"
         android:background="@drawable/badge_cimax"
         android:layout_width="23dp"
         *  android:layout_height="23dp" />
         */
        List<PlayingMovieBean.MsEntity.VersionsEntity> versions = entity.getVersions();
        for(int i = 0;i<versions.size();i++){
            ImageView ima = new ImageView(this);
            ima.setLayoutParams(params);
            PlayingMovieBean.MsEntity.VersionsEntity versionsEntity = versions.get(i);
            int enumX = versionsEntity.getEnumX();
            switch(enumX) {
                case 2:
                    ima.setBackgroundResource(R.drawable.badge_3d);
                    break;
                case 4:
                    ima.setBackgroundResource(R.drawable.badge_imax3d);
                    break;
                case 6:
                    ima.setBackgroundResource(R.drawable.badge_cimax);
                    break;
            }
            ll_movietype_play_movie_detials.addView(ima);
        }


        //初始化图片
        x.image().bind(icon_paly_movie_detials, entity.getImg(), options);
        //名称
        String tCn = entity.getTCn();
        tv_ll_movie_name_detials_buyticket.setText(tCn);
        int le = tCn.length()>6? 5:tCn.length();
        String substring = tCn.substring(0,le);
        tv__namepaly_movie_details_buyticket.setText(substring);
        //英文名
        tv_ll_movie_us_detials_buyticket.setText(entity.getTEn());
        //得分
        boolean isWill = entity.getR()<=0;
        if(isWill){
            tv_score_detials_buyticket.setVisibility(View.INVISIBLE);
        }else{
            tv_score_detials_buyticket.setVisibility(View.VISIBLE);
            tv_score_detials_buyticket.setText(entity.getR() + "");
        }
        //分钟
        String length = entity.getD();
        if(length != null){
            tv_timemovie_detials_buyticket.setText(length);
        }else{
            tv_timemovie_detials_buyticket.setText("--分钟");
        }

        //类型
        tv_typemovie_detials_buyticket.setText(entity.getMovieType());

        //上映时间
        tv_showTime_detials_buyticket.setText("上映时间:" + entity.getRd());

        //我想看btn
        btn_wantsee_detials_buyticket.setOnClickListener(pmiaOnClickListener.setPosition(0));
        //评论btn
        btn_wantscore_detials_buyticket.setOnClickListener(pmiaOnClickListener.setPosition(1));
        //一句话描述
        String commonSpecial = entity.getCommonSpecial();
        if(!commonSpecial.equals("")){
            tv_commonSpecial_play_movie_detial_buyticket.setVisibility(View.VISIBLE);
            tv_commonSpecial_play_movie_detial_buyticket.setText("“"+commonSpecial);
        }else{
            tv_commonSpecial_play_movie_detial_buyticket.setVisibility(View.INVISIBLE);
        }
        btn_scroll_buyticket_detials.setOnClickListener(pmiaOnClickListener.setPosition(2));
    }

    private void findView() {
        ll_content_container_detials = (LinearLayout) findViewById(R.id.ll_content_container_detials);
        ib_back_movie_details_buyticket = (ImageButton) findViewById(R.id.ib_back_movie_details_buyticket);
        tv__namepaly_movie_details_buyticket = (TextView) findViewById(R.id.tv__namepaly_movie_details_buyticket);
        share_paly_movie_details_buyticket = (ImageButton) findViewById(R.id.share_paly_movie_details_buyticket);
        favorite_paly_movie_details_buyticket = (ImageButton) findViewById(R.id.favorite_paly_movie_details_buyticket);

        ll_movietype_play_movie_detials = (LinearLayout) findViewById(R.id.ll_movietype_play_movie_detials);
        favorite_paly_movie_details_buyticket = (ImageButton) findViewById(R.id.favorite_paly_movie_details_buyticket);
        btn_buyticket_play_movie_item = (Button) findViewById(R.id.btn_buyticket_play_movie_item);
        icon_paly_movie_detials = (ImageView) findViewById(R.id.icon_paly_movie_detials);
        tv_commonSpecial_play_movie_detial_buyticket = (TextView) findViewById(R.id.tv_commonSpecial_play_movie_detial_buyticket);
        btn_scroll_buyticket_detials = (Button) findViewById(R.id.btn_scroll_buyticket_detials);
        rl_title_play_movie_detials = (RelativeLayout) findViewById(R.id.rl_title_play_movie_detials);
        scr_play_movie_detials = (ObservableScrollView) findViewById(R.id.scr_play_movie_detials);
        tv_ll_movie_name_detials_buyticket = (TextView) findViewById(R.id.tv_ll_movie_name_detials_buyticket);
        tv_ll_movie_us_detials_buyticket = (TextView) findViewById(R.id.tv_ll_movie_us_detials_buyticket);
        tv_score_detials_buyticket = (TextView) findViewById(R.id.tv_score_detials_buyticket);
        tv_timemovie_detials_buyticket = (TextView) findViewById(R.id.tv_timemovie_detials_buyticket);
        tv_typemovie_detials_buyticket = (TextView) findViewById(R.id.tv_typemovie_detials_buyticket);
        tv_showTime_detials_buyticket = (TextView) findViewById(R.id.tv_showTime_detials_buyticket);
        btn_wantsee_detials_buyticket = (Button) findViewById(R.id.btn_wantsee_detials_buyticket);
        btn_wantscore_detials_buyticket = (Button) findViewById(R.id.btn_wantscore_detials_buyticket);
        rl_title_play_movie_detials.getBackground().setAlpha(0);
       // params = new FrameLayout.LayoutParams(DensityUtils.dip2px(this, 360),DensityUtils.dip2px(this,40));
       // params.leftMargin = DensityUtils.dip2px(this,10);
    }

    @Override
    public void onScrollChanged(int scrollY) {
        Log.e("TAG", "scrollY==" + scrollY);
        int mBuyLayout2ParentTop = Math.max(scrollY, btn_scroll_buyticket_detials.getTop());
       // btn_buyticket_play_movie_item.setLayoutParams(params);
        btn_buyticket_play_movie_item.layout(0, mBuyLayout2ParentTop, btn_buyticket_play_movie_item.getWidth(), mBuyLayout2ParentTop + btn_buyticket_play_movie_item.getHeight());
        if(scrollY>=0) {
            if(scrollY<255) {
                rl_title_play_movie_detials.getBackground().setAlpha(scrollY);
            }else {
                rl_title_play_movie_detials.getBackground().setAlpha(255);
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

