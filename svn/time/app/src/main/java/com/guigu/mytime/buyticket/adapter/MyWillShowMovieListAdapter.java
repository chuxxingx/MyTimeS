package com.guigu.mytime.buyticket.adapter;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.guigu.mytime.R;
import com.guigu.mytime.buyticket.activity.BuyMovieActivity;
import com.guigu.mytime.buyticket.activity.PlayMovieItemActivity;
import com.guigu.mytime.buyticket.activity.VidoListActivity;
import com.guigu.mytime.buyticket.bean.WillShowMovieBean;

import org.xutils.x;

import java.util.List;

/**
 * Created by Administrator on 2016/3/1.
 */
public class MyWillShowMovieListAdapter extends BaseAdapter {

    private Activity activity;
    private  WillShowMovieBean willShowMovieBean;

    private final List<WillShowMovieBean.MoviecomingsEntity> moviecomings;

    public MyWillShowMovieListAdapter(Activity activity, WillShowMovieBean willShowMovieBean) {
        this.activity = activity;
        this.willShowMovieBean = willShowMovieBean;
        moviecomings = willShowMovieBean.getMoviecomings();
    }


    @Override
    public int getCount() {
        return moviecomings.size();
    }

    @Override
    public Object getItem(int position) {
        return moviecomings.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = initHolder(position, convertView);
        return convertView;
    }

    @NonNull
    private View initHolder(final int position, View convertView) {
        ViewHolder viewHolder = null;
        if(convertView == null) {
            convertView = View.inflate(activity, R.layout.willshowmovie_listview_item,null);
            viewHolder = new ViewHolder();
            viewHolder.month_view = (LinearLayout) convertView.findViewById(R.id.month_view);
            viewHolder.iv_icon_lvwillshow_item = (ImageView) convertView.findViewById(R.id.iv_icon_lvwillshow_item);
            viewHolder.tv_name_lvwillshow_item = (TextView) convertView.findViewById(R.id.tv_name_lvwillshow_item);
            viewHolder.tv_month_willshow_list_item = (TextView) convertView.findViewById(R.id.tv_month_willshow_list_item);
            viewHolder.tv_day_willshow_list_item = (TextView) convertView.findViewById(R.id.tv_day_willshow_list_item);
            viewHolder.tv_type_lvwillshow_item = (TextView) convertView.findViewById(R.id.tv_type_lvwillshow_item);
            viewHolder.tv_director_lvwillshow_item = (TextView) convertView.findViewById(R.id.tv_director_lvwillshow_item);
            viewHolder.btn_prebuy_lv_willshow_item = (Button) convertView.findViewById(R.id.btn_prebuy_lv_willshow_item);
            viewHolder.btn_movie_lv_willshow_item = (Button) convertView.findViewById(R.id.btn_movie_lv_willshow_item);
            convertView.setTag(viewHolder);
        }  else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        final WillShowMovieBean.MoviecomingsEntity moviecomingsEntity = moviecomings.get(position);
        //获取时间月
        int rMonth = moviecomingsEntity.getRMonth();
        viewHolder.tv_month_willshow_list_item.setText(rMonth+"月");
        //获取时间日
        int rDay = moviecomingsEntity.getRDay();
        viewHolder.tv_day_willshow_list_item.setText(rDay+"日");
        //获取标签
        String title = moviecomingsEntity.getTitle();
        viewHolder.tv_name_lvwillshow_item.setText(title);
        //获取多少人想看+类型和产地
        int wantedCount = moviecomingsEntity.getWantedCount();
        String type = moviecomingsEntity.getType();
        String locationName = moviecomingsEntity.getLocationName();
        viewHolder.tv_type_lvwillshow_item.setText(wantedCount+"人想看-"+type+"/"+locationName);
        //导演
        String director = moviecomingsEntity.getDirector();
        viewHolder.tv_director_lvwillshow_item.setText(director);

        //是否有门票
        boolean isTicket = moviecomingsEntity.isIsTicket();

        if(isTicket) {
            viewHolder.btn_prebuy_lv_willshow_item.setVisibility(View.VISIBLE);
            viewHolder.btn_prebuy_lv_willshow_item.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(activity, BuyMovieActivity.class);
                    intent.putExtra("name", moviecomingsEntity.getTitle());
                    activity.startActivity(intent);
                }
            });
        }else {
            viewHolder.btn_prebuy_lv_willshow_item.setText("上映提醒");
        }

        //是否有预告片
        boolean isVideo = moviecomingsEntity.isIsVideo();
        if(isVideo) {//有票也有预告片
            viewHolder.btn_movie_lv_willshow_item.setVisibility(View.VISIBLE);
            viewHolder.btn_movie_lv_willshow_item.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(activity, VidoListActivity.class);
                    intent.putExtra("id", moviecomingsEntity.getId());
                    activity.startActivity(intent);
                }
            });
        }else{
            viewHolder.btn_movie_lv_willshow_item.setVisibility(View.GONE);
        }
        //获取图片的照片
        String image = moviecomingsEntity.getImage();
        x.image().bind(viewHolder.iv_icon_lvwillshow_item,image);

        //判断是否显示月份
        if(position != 0) {
            WillShowMovieBean.MoviecomingsEntity moviecomingsEntity1 = moviecomings.get(position - 1);
            int rMonth1 = moviecomingsEntity1.getRMonth();
            if(rMonth == rMonth1) {//和上一个相同则隐藏
                viewHolder.month_view.setVisibility(View.GONE);
            }else {
                viewHolder.month_view.setVisibility(View.VISIBLE);
            }
        }else {
            viewHolder.month_view.setVisibility(View.VISIBLE);
        }

        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int id = moviecomingsEntity.getId();
                Intent intent = new Intent(activity, PlayMovieItemActivity.class);
                intent.putExtra(PlayMovieItemActivity.MOVIELISTPOSITION,position);
                intent.putExtra(PlayMovieItemActivity.MOVIEID,moviecomingsEntity.getId());
                activity.startActivity(intent);
            }
        });


        return convertView;
    }

    private class ViewHolder{
        private LinearLayout month_view;
        private ImageView iv_icon_lvwillshow_item;
        private TextView tv_month_willshow_list_item;
        private TextView tv_day_willshow_list_item;
        private TextView tv_name_lvwillshow_item;
        private TextView tv_type_lvwillshow_item;
        private TextView tv_director_lvwillshow_item;
        private Button btn_prebuy_lv_willshow_item;
        private Button btn_movie_lv_willshow_item;
    }
}
