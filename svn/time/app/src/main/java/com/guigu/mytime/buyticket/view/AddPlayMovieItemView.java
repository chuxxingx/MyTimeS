package com.guigu.mytime.buyticket.view;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.guigu.mytime.R;
import com.guigu.mytime.buyticket.activity.VidoListActivity;
import com.guigu.mytime.buyticket.adapter.MyMovieRcyclerViewAdapter;
import com.guigu.mytime.buyticket.bean.BeanContainer;
import com.guigu.mytime.buyticket.bean.MovieGoodsBean;
import com.guigu.mytime.buyticket.bean.MovieListBean;
import com.guigu.mytime.buyticket.bean.PersonInMovieBean;
import com.guigu.mytime.utils.ConstantUtils;

import org.xutils.common.Callback;
import org.xutils.common.util.DensityUtil;
import org.xutils.http.RequestParams;
import org.xutils.image.ImageOptions;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.List;

/**
 * Created by Administrator on 2016/3/4.
 */
public class AddPlayMovieItemView implements View.OnClickListener{
    private Activity activity;
    public View view;
    private Object dataFromNet;
    //伸缩描述信息
    @ViewInject(R.id.ib_extend_textdesc_add_detials)
    private ImageButton ib_extend_textdesc_add_detials;
    //描述信息，2行
    @ViewInject(R.id.tv_descmovie_add_detials)
    private TextView tv_descmovie_add_detials;
    //所有剧情
    @ViewInject(R.id.tv_desallcmovie_add_detials)
    private TextView tv_desallcmovie_add_detials;
    //电影周边
    @ViewInject(R.id.tv_moviesomes_add_detials)
    private TextView tv_moviesomes_add_detials;
    //商品图片
    @ViewInject(R.id.iv_1_)
    private ImageView iv_1_;
    @ViewInject(R.id.iv_2_)
    private ImageView iv_2_;
    @ViewInject(R.id.iv_3_)
    private ImageView iv_3_;

    //商品名称
    @ViewInject(R.id.tv_name_1_)
    private TextView tv_name_1_;
    @ViewInject(R.id.tv_name_2_)
    private TextView tv_name_2_;
    @ViewInject(R.id.tv_name_3_)
    private TextView tv_name_3_;

    //商品价格
    @ViewInject(R.id.tv_price_1_)
    private TextView tv_price_1_;
    @ViewInject(R.id.tv_price_2_)
    private TextView tv_price_2_;
    @ViewInject(R.id.tv_price_3_)
    private TextView tv_price_3_;
    //演员表
    @ViewInject(R.id.rcv_actor_add_detials)
    private RecyclerView rcv_actor_add_detials;
    @ViewInject(R.id.tv_actorcount_add_)
    private TextView tv_actorcount_add_;

    //视频
    @ViewInject(R.id.iv_movie_add_)
    private ImageView iv_movie_add_;
    //图片
    @ViewInject(R.id.iv_image_add_)
    private ImageView iv_image_add_;

    private ImageOptions options;

    private int movieid;

    public AddPlayMovieItemView(Activity activity,int movieid){
        options = new ImageOptions.Builder()
                .setSize(DensityUtil.dip2px(95), DensityUtil.dip2px(120))
                .setRadius(DensityUtil.dip2px(5))
                        // 如果ImageView的大小不是定义为wrap_content, 不要crop.
                .setCrop(false)
                        // 加载中或错误图片的ScaleType
                        //.setPlaceholderScaleType(ImageView.ScaleType.MATRIX)
                .setImageScaleType(ImageView.ScaleType.CENTER_CROP)
                .build();
        this.activity = activity;
        this.movieid = movieid;
        view = initView();
        setView();
        getDataFromNet();
    }

    private void setView() {
        iv_image_add_.setOnClickListener(this);
        iv_movie_add_.setOnClickListener(this);

        ib_extend_textdesc_add_detials.setOnClickListener(this);
    }

    private View initView() {
        View view = View.inflate(activity, R.layout.add_layout, null);
        x.view().inject(this, view);
        //设置为水平
        rcv_actor_add_detials.setLayoutManager(new LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false));
        return view;
    }

    /**
     *联网请求
     */
    public void getDataFromNet() {
        String urlgoods = ConstantUtils.MOVIE_RIM + "?relatedId="+movieid+"10190&relatedObjType=1";
//        new HttpRequest(urlgoods, MovieGoodsBean.class, new HttpRequest.HttpRequestListener<MovieGoodsBean>() {
//
//            @Override
//            public void getBean(MovieGoodsBean movieGoodsBean) {
//                processData(movieGoodsBean);
//            }
//
//            @Override
//            public void onError(Throwable ex, boolean isOnCallback) {
//
//            }
//        });
        x.http().get(new RequestParams(urlgoods), new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                processData(new Gson().fromJson(result, MovieGoodsBean.class));
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
        //演职人员
        String urlactor = ConstantUtils.PERSONINMOVIE + "?movieId=" + movieid;
//
        x.http().get(new RequestParams(urlactor), new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                processDataPerson(new Gson().fromJson(result, PersonInMovieBean.class).getTypes());
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
        //相关视频.
        String urlvideo= ConstantUtils.MOVIE_MOVIE + "?movieId="+movieid+"&pageIndex=1";
//
        x.http().get(new RequestParams(urlvideo), new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                BeanContainer.setMoviePreBean(new Gson().fromJson(result, MovieListBean.class));
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


    /**
     * 解析演员表数据
     * @param
     */
    private void processDataPerson(List<PersonInMovieBean.TypesEntity> types) {

        int count = 0;

        for(int i = 0;i<types.size();i++){
            PersonInMovieBean.TypesEntity typesEntity = types.get(i);
            List<PersonInMovieBean.TypesEntity.PersonsEntity> persons = typesEntity.getPersons();
            count+=persons.size();
        }
        tv_actorcount_add_.setText(count + "位演职人员");

        rcv_actor_add_detials.setHasFixedSize(true);
        rcv_actor_add_detials.setItemAnimator(new DefaultItemAnimator());
        rcv_actor_add_detials.setLayoutManager(new LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false));
        rcv_actor_add_detials.setAdapter(new MyMovieRcyclerViewAdapter(activity,types));

    }

    private int[] oneId ;
    private void processData(MovieGoodsBean movieGoodsBean) {
        oneId = new int[3];

        //商品个数
        String goodscount = "电影周边("+movieGoodsBean.getGoodsCount()+")";
        tv_moviesomes_add_detials.setText(goodscount);
        List<MovieGoodsBean.GoodsListEntity> goodsList = movieGoodsBean.getGoodsList();

        for(int i = 0;i<goodsList.size();i++){
            MovieGoodsBean.GoodsListEntity goodsListEntity = goodsList.get(i);
            oneId[i] = goodsListEntity.getGoodsId();
            int minSalePrice = goodsListEntity.getMinSalePrice();
            double price = minSalePrice/100.0;
            switch(i) {
                case 0:
                    x.image().bind(iv_1_, goodsListEntity.getImage(), options);

                    tv_name_1_.setText(goodsListEntity.getName());
                    tv_price_1_.setText("￥" + price);
                    break;
                case 1:
                    x.image().bind(iv_2_, goodsListEntity.getImage(), options);

                    tv_name_2_.setText(goodsListEntity.getName());
                    tv_price_2_.setText("￥" + price);
                    break;
                case 2:
                    x.image().bind(iv_3_, goodsListEntity.getImage(), options);
                    tv_name_3_.setText(goodsListEntity.getName());
                    tv_price_3_.setText("￥" + price);
                    break;
            }
        }
        iv_1_.setOnClickListener(this);
        iv_2_.setOnClickListener(this);
        iv_3_.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
      //  Intent intent = new Intent(activity, StoreWebViewActivity.class);
        if(v == ib_extend_textdesc_add_detials){
            tv_desallcmovie_add_detials.isShown();
            if(tv_desallcmovie_add_detials.isShown()){
                tv_descmovie_add_detials.setVisibility(View.VISIBLE);
                tv_desallcmovie_add_detials.setVisibility(View.GONE);
                ib_extend_textdesc_add_detials.setImageResource(R.drawable.ic_expand_small_holo_light);
            }else{
                tv_descmovie_add_detials.setVisibility(View.GONE);
                tv_desallcmovie_add_detials.setVisibility(View.VISIBLE);
                ib_extend_textdesc_add_detials.setImageResource(R.drawable.ic_collapse_small_holo_light);
            }
        }else if(v == iv_image_add_){//剧照
            //  activity.startActivity(new Intent(activity,JZIMAGES.class));
        }else if(v == iv_movie_add_){//视频
            Log.e("TAG", "addplaymovieItem  movieId==" + movieid);
            Intent intent = new Intent(activity,VidoListActivity.class);
            intent.putExtra("id",movieid);
            activity.startActivity(intent);
        }else if(v == iv_1_){
            String str1 = "http://mall.wv.mtime.cn/?utm_source=app_movie#!/commerce/item/"+ oneId[0] +"/";
//            intent.putExtra("webview_url",str1);
//            activity.startActivity(intent);
        }else if(v == iv_2_){
            String str2 = "http://mall.wv.mtime.cn/?utm_source=app_movie#!/commerce/item/"+ oneId[1]+"/";
//            intent.putExtra("webview_url",str2);
//            activity.startActivity(intent);
        }else if(v == iv_3_){
            String str3 = "http://mall.wv.mtime.cn/?utm_source=app_movie#!/commerce/item/" +oneId[2]+"/";
//            intent.putExtra("webview_url",str3);
//            activity.startActivity(intent);
        }
    }
}
