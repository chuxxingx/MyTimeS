package com.guigu.mytime.buyticket.adapter;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import com.guigu.mytime.R;
import com.guigu.mytime.buyticket.activity.BuyTicketActivity;
import com.guigu.mytime.buyticket.activity.PlayMovieItemActivity;
import com.guigu.mytime.buyticket.activity.VidoListActivity;
import com.guigu.mytime.buyticket.bean.WillShowMovieBean;

import org.xutils.x;

/**
 * Created by Administrator on 2016/3/1.
 */
public class MyRcyclerViewAdapter  extends RecyclerView.Adapter<MyRcyclerViewAdapter.MyViewHolder> {
    private Activity activity;
    private WillShowMovieBean willShowMovieBean;

    public MyRcyclerViewAdapter(Activity activity, WillShowMovieBean willShowMovieBean) {
        this.activity = activity;
        this.willShowMovieBean = willShowMovieBean;
    }

    @Override
    public MyRcyclerViewAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        MyViewHolder viewHolder = new MyViewHolder(LayoutInflater.from(activity).
                inflate(R.layout.item_will_show_movies, parent, false));
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(MyRcyclerViewAdapter.MyViewHolder holder, final int position) {
        final WillShowMovieBean.AttentionEntity attentionEntity = willShowMovieBean.getAttention().get(position);
        initHolder(holder,attentionEntity);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activity, PlayMovieItemActivity.class);
                intent.putExtra(PlayMovieItemActivity.MOVIELISTPOSITION, position);
                intent.putExtra(PlayMovieItemActivity.MOVIEID, attentionEntity.getId());
                activity.startActivity(intent);
            }
        });
    }


    private void initHolder(MyViewHolder holder, final WillShowMovieBean.AttentionEntity attentionEntity) {
        //装配数据
        boolean isTicket = attentionEntity.isIsTicket();
        if(isTicket) {//如果有票显示超前预售button
            holder.btn_prebuy_rcy_willshow_item.setVisibility(View.VISIBLE);
        }else {
            holder.btn_prebuy_rcy_willshow_item.setVisibility(View.GONE);
        }
        //获取时间，rMoth,rDay
        int rMonth = attentionEntity.getRMonth();
        int rDay = attentionEntity.getRDay();
        holder.tv_day_rcywillshow_item.setText(rMonth+"月"+rDay+"日");

        //获取标题
        String title = attentionEntity.getTitle();
        holder.tv_name_rcywillshow_item.setText(title);

        //多少人想看和其类型
        int wantedCount = attentionEntity.getWantedCount();//多少人想看
        String type = attentionEntity.getType();//类型
        String locationName = attentionEntity.getLocationName();//产地
        holder.tv_type_count_rcywillshow_item.setText(""+wantedCount);
        holder.tv_type_rcywillshow_item.setText(type+"/"+locationName);

        //导演
        String director = attentionEntity.getDirector();
        holder.tv_director_rcywillshow_item.setText(director);

        //演员
        String actor1 = attentionEntity.getActor1();
        String actor2 = attentionEntity.getActor2();
        holder.tv_actor_rcywillshow_item.setText(actor1+","+actor2);

        //图片
        String image = attentionEntity.getImage();
        x.image().bind(holder.iv_icon_rcywillshow_item,image);

        holder.btn_movie_rcy_willshow_item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //播放列表页面
                Intent intent = new Intent(activity, VidoListActivity.class);
                intent.putExtra("id", attentionEntity.getId());
                activity.startActivity(intent);
            }
        });
        holder.btn_prebuy_rcy_willshow_item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //影院页面
                Intent intent = new Intent(activity, BuyTicketActivity.class);
                intent.putExtra("name", attentionEntity.getTitle());
                activity.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return willShowMovieBean.getAttention().size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{
        private ImageView iv_icon_rcywillshow_item;
        private TextView tv_day_rcywillshow_item;
        private TextView tv_name_rcywillshow_item;
        private TextView tv_type_rcywillshow_item;
        private TextView tv_type_count_rcywillshow_item;
        private TextView tv_director_rcywillshow_item;
        private TextView tv_actor_rcywillshow_item;
        private Button btn_prebuy_rcy_willshow_item;
        private Button btn_movie_rcy_willshow_item;
        public MyViewHolder(View itemView) {
            super(itemView);
            iv_icon_rcywillshow_item = (ImageView) itemView.findViewById(R.id.iv_icon_rcywillshow_item);
            tv_day_rcywillshow_item = (TextView) itemView.findViewById(R.id.tv_day_rcywillshow_item);
            tv_name_rcywillshow_item = (TextView) itemView.findViewById(R.id.tv_name_rcywillshow_item);
            tv_type_rcywillshow_item = (TextView) itemView.findViewById(R.id.tv_type_rcywillshow_item);
            tv_type_count_rcywillshow_item = (TextView) itemView.findViewById(R.id.tv_type_count_rcywillshow_item);
            tv_director_rcywillshow_item = (TextView) itemView.findViewById(R.id.tv_director_rcywillshow_item);
            tv_actor_rcywillshow_item = (TextView) itemView.findViewById(R.id.tv_actor_rcywillshow_item);
            btn_prebuy_rcy_willshow_item = (Button) itemView.findViewById(R.id.btn_prebuy_rcy_willshow_item);
            btn_movie_rcy_willshow_item = (Button) itemView.findViewById(R.id.btn_movie_rcy_willshow_item);

        }
    }
}
