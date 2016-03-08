package com.guigu.mytime.buyticket.adapter;

import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import com.guigu.mytime.R;
import com.guigu.mytime.buyticket.activity.BuyMovieActivity;
import com.guigu.mytime.buyticket.bean.PlayingMovieBean;

import org.xutils.common.util.DensityUtil;
import org.xutils.image.ImageOptions;
import org.xutils.x;

import java.util.List;

/**
 * Created by Administrator on 2016/2/29.
 */
public class PMListViewAdapte extends BaseAdapter {
    private Activity activity;
    private PlayingMovieBean listBean;
    private ImageOptions options;
    private final List<PlayingMovieBean.MsEntity> ms;

    public PMListViewAdapte(Activity activity, PlayingMovieBean listBean) {
        this.activity = activity;
        this.listBean = listBean;
        options = new ImageOptions.Builder()
                .setSize(DensityUtil.dip2px(90), DensityUtil.dip2px(140))
                .setRadius(DensityUtil.dip2px(5))
                        // 如果ImageView的大小不是定义为wrap_content, 不要crop.
                .setCrop(false)
                        // 加载中或错误图片的ScaleType
                        //.setPlaceholderScaleType(ImageView.ScaleType.MATRIX)
                .setImageScaleType(ImageView.ScaleType.CENTER_CROP)
                .build();
        ms = listBean.getMs();
    }

    @Override
    public int getCount() {
        return ms.size();
    }

    @Override
    public Object getItem(int position) {
        return ms.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (convertView == null) {
            convertView = View.inflate(activity, R.layout.playing_movie_listview_item, null);
            viewHolder = new ViewHolder();
//            viewHolder.tv_score_play_movie_item = (TextView) convertView.findViewById(R.id.tv_score_play_movie_item);
//            viewHolder.tv_score2_play_movie_item = (TextView) convertView.findViewById(R.id.tv_score2_play_movie_item);
            viewHolder.movie_icon = (ImageView) convertView.findViewById(R.id.iv_play_movie_item);
            viewHolder.iv_3dmovie_type_buyticket = (ImageView) convertView.findViewById(R.id.iv_3dmovie_type_buyticket);
            viewHolder.iv_bigmovie_type_buyticket = (ImageView) convertView.findViewById(R.id.iv_bigmovie_type_buyticket);
            viewHolder.iv_imaxmovie_type_buyticket = (ImageView) convertView.findViewById(R.id.iv_imaxmovie_type_buyticket);
            viewHolder.tv_commonSpecial_play_movie_item = (TextView) convertView.findViewById(R.id.tv_commonSpecial_play_movie_item);
            viewHolder.tv_palycount_play_movie_item = (TextView) convertView.findViewById(R.id.tv_palycount_play_movie_item);
            viewHolder.btn_buymovie_buyticket = (Button) convertView.findViewById(R.id.btn_buymovie_buyticket);
            viewHolder.tv_tcn_play_movie_item = (TextView) convertView.findViewById(R.id.tv_tcn_play_movie_item);
            viewHolder.btn_messagemovie_buyticket = (Button) convertView.findViewById(R.id.btn_messagemovie_buyticket);
            viewHolder.btn_willbuymovie_buyticket = (Button) convertView.findViewById(R.id.btn_willbuymovie_buyticket);
            viewHolder.tv_typeorwant_play_movie_item = (TextView) convertView.findViewById(R.id.tv_typeorwant_play_movie_item);
            viewHolder.tv_palytime_play_movie_item = (TextView) convertView.findViewById(R.id.tv_palytime_play_movie_item);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        init(viewHolder,position);

        return convertView;
    }

    private void init(ViewHolder viewHolder, int position) {
        final  PlayingMovieBean.MsEntity msEntity = ms.get(position);
        //设置可见性
        initVisibilable(viewHolder);
        List<PlayingMovieBean.MsEntity.VersionsEntity> versions = msEntity.getVersions();
        for(int i = 0;i<versions.size();i++){
            int enumX = versions.get(i).getEnumX();
            switch(enumX) {
                case 2:
                    Log.e("TAG", "2");
                    viewHolder.iv_3dmovie_type_buyticket.setVisibility(View.VISIBLE);
                    break;
                case 4:
                    Log.e("TAG", "4");
                    viewHolder.iv_imaxmovie_type_buyticket.setVisibility(View.VISIBLE);
                    break;
                case 6:
                    Log.e("TAG", "6");
                    viewHolder.iv_bigmovie_type_buyticket.setVisibility(View.VISIBLE);
                    break;
            }
        }

        //电影类型
        List<String> p = msEntity.getP();

        //评分
        double r = msEntity.getR();

        //是否为预售
        boolean isWillMovie = false;

        //得分
        String score = "";

        if(r < 0){
            isWillMovie = true;
        }else{
            score = r + "";
        }
//        String[] split = score.split(".");
//        String s1 = split[0];
//        String s2 = split[1];
//
//        viewHolder.tv_score_play_movie_item.setText(s1);
//        viewHolder.tv_score2_play_movie_item.setText(s2);
        viewHolder.tv_tcn_play_movie_item.setText(msEntity.getTCn()+" "+score);
        //类型
        String movieType = msEntity.getMovieType();
        if(r <0||!msEntity.isIsTicket()&&!movieType.equals("")){
            int count = msEntity.getWantedCount();
            String type = "" + count +"人想看-" + movieType;
            viewHolder.tv_typeorwant_play_movie_item.setVisibility(View.VISIBLE);
            viewHolder.tv_typeorwant_play_movie_item.setText(type);
        }
        //描述
        String commonSpecial = msEntity.getCommonSpecial();
        if(!commonSpecial.equals("")){
            viewHolder.tv_commonSpecial_play_movie_item.setVisibility(View.VISIBLE);
            viewHolder.tv_commonSpecial_play_movie_item.setText("“" + commonSpecial);
        }

        //

        //上映时间
        String showTime = msEntity.getRd();
        if(msEntity.isIsNew()){
            viewHolder.tv_palytime_play_movie_item.setVisibility(View.VISIBLE);
            viewHolder.tv_palytime_play_movie_item.setText("上映时间" + showTime);
        }

        //多少家上映
        int nearestShowtimeCount = msEntity.getNearestShowtimeCount();
        if(nearestShowtimeCount >=1){
            int nearestCinemaCount = msEntity.getNearestCinemaCount();
            String count = "今日" + nearestCinemaCount + "影院上映" + nearestShowtimeCount + "场";
            viewHolder.tv_palycount_play_movie_item.setVisibility(View.VISIBLE);
            viewHolder.tv_palycount_play_movie_item.setText(count);
        }
        final String date = listBean.getDate();


        //设置按钮
        if(isWillMovie){

            viewHolder.btn_willbuymovie_buyticket.setVisibility(View.VISIBLE);
            viewHolder.btn_willbuymovie_buyticket.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
//                    Intent intent = new Intent(activity, WillBuyMovieActivity.class);
//                    intent.putExtra("date",date);
//                    intent.putExtra("id",msEntity.getId());
//                    activity.startActivity(intent);
                }
            });
        }else

        if(msEntity.isIsTicket()){
            //进入购票页面
            viewHolder.btn_buymovie_buyticket.setVisibility(View.VISIBLE);
            viewHolder.btn_buymovie_buyticket.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(activity, BuyMovieActivity.class);
                    intent.putExtra("date",date);
                    intent.putExtra("id",msEntity.getId());
                    activity.startActivity(intent);
                }
            });
        }else{
            //进入票讯页面
            viewHolder.btn_messagemovie_buyticket.setVisibility(View.VISIBLE);
            viewHolder.btn_messagemovie_buyticket.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
//                    Intent intent = new Intent(activity,MessageMovieActivity.class);
//                    intent.putExtra("date",date);
//                    intent.putExtra("id",msEntity.getId());
//                    activity.startActivity(intent);
                }
            });
        }
        //   viewHolder.tv_commonSpecial_play_movie_item;
        //联网请求图片
//
        String url = msEntity.getImg();
        x.image().bind(viewHolder.movie_icon, url, options);
    }

    private void initVisibilable(ViewHolder viewHolder) {
        viewHolder.iv_3dmovie_type_buyticket.setVisibility(View.GONE);
        viewHolder.iv_imaxmovie_type_buyticket.setVisibility(View.GONE);
        viewHolder.iv_bigmovie_type_buyticket.setVisibility(View.GONE);
        viewHolder.tv_typeorwant_play_movie_item.setVisibility(View.GONE);
        viewHolder.tv_commonSpecial_play_movie_item.setVisibility(View.GONE);
        viewHolder.tv_palytime_play_movie_item.setVisibility(View.GONE);
        viewHolder.tv_palycount_play_movie_item.setVisibility(View.GONE);
        viewHolder.btn_willbuymovie_buyticket.setVisibility(View.GONE);
        viewHolder.btn_buymovie_buyticket.setVisibility(View.GONE);
        viewHolder.btn_messagemovie_buyticket.setVisibility(View.GONE);
    }

    class ViewHolder{
        ImageView movie_icon,iv_3dmovie_type_buyticket,iv_bigmovie_type_buyticket,iv_imaxmovie_type_buyticket;
        //简单描述
        TextView tv_commonSpecial_play_movie_item;
        //上映时间
        TextView tv_palytime_play_movie_item;
        //类型及多少人想看
        TextView tv_typeorwant_play_movie_item;
        //多少家播放多少次
        TextView tv_palycount_play_movie_item;
        //电影名称
        TextView tv_tcn_play_movie_item;
        //得分
//        TextView tv_score_play_movie_item;
//        TextView tv_score2_play_movie_item;
        //购票
        Button btn_buymovie_buyticket;
        //查票讯
        Button btn_messagemovie_buyticket;
        //预售
        Button btn_willbuymovie_buyticket;
    }}
