package com.guigu.mytime.buyticket.adapter;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.guigu.mytime.R;
import com.guigu.mytime.buyticket.activity.VidoListActivity;
import com.guigu.mytime.buyticket.bean.PersonInMovieBean;
import com.guigu.mytime.buyticket.bean.PlayingMovieBean;

/**
 * Created by Administrator on 2016/3/3.
 */
public class PlayMovieItemListViewAdapter extends BaseAdapter implements View.OnClickListener {
    private PlayingMovieBean.MsEntity msEntity;
    private int movieId;//电影的id
    private Activity activity;
    private PersonInMovieBean personInMovieBean;
    private boolean isShowText = false;
    public PlayMovieItemListViewAdapter(Activity activity, PlayingMovieBean.MsEntity msEntity, PersonInMovieBean personInMovieBean) {
        this.movieId = msEntity.getId();
        Log.e("TAG", "movieId =="+movieId);
        this.activity = activity;
        this.msEntity = msEntity;
        this.personInMovieBean = personInMovieBean;
    }

    @Override
    public int getCount() {
        return 4;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    //获取item的类型
    @Override
    public int getItemViewType(int position) {
        int itemType = -1;
        if(position == 0) {
            itemType = 1;//第一种类型，纯文本
        }else if(position == 1) {
            itemType = 2;//第二种类型：视频剧照的
        }else if(position == 2) {
            itemType = 3;//演职人员
        }else if(position == 3) {
            itemType = 4;//影评
        }
        return itemType;
    }

    //item类型的总共数量
    @Override
    public int getViewTypeCount() {
        return 4;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHoler holder = new ViewHoler();
        switch (getItemViewType(position)) {
            case 1 :
                convertView = View.inflate(activity, R.layout.movie_info_adapter_text_type,null);
                holder.tv_descmovie_add_detials = (TextView) convertView.findViewById(R.id.tv_descmovie_add_detials);
                holder.tv_desallcmovie_add_detials = (TextView) convertView.findViewById(R.id.tv_desallcmovie_add_detials);
                holder.ib_extend_textdesc_add_detials = (ImageButton) convertView.findViewById(R.id.ib_extend_textdesc_add_detials);
                final ViewHoler finalHolder = holder;
                holder.ib_extend_textdesc_add_detials.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(isShowText) {
                            isShowText = true;
                            finalHolder.tv_descmovie_add_detials.setVisibility(View.GONE);
                            finalHolder.tv_desallcmovie_add_detials.setVisibility(View.VISIBLE);
                            finalHolder.ib_extend_textdesc_add_detials.setBackgroundResource(R.drawable.ic_collapse_small_holo_light);
                        }else {
                            isShowText = false;
                            finalHolder.tv_descmovie_add_detials.setVisibility(View.VISIBLE);
                            finalHolder.tv_desallcmovie_add_detials.setVisibility(View.GONE);
                            finalHolder.ib_extend_textdesc_add_detials.setBackgroundResource(R.drawable.ic_expand_small_holo_light);
                        }
                    }
                });
                break;
            case 2 :
                convertView = View.inflate(activity, R.layout.movie_info_adapter_vido_type,null);
                holder.iv_movie_add_ = (ImageView) convertView.findViewById(R.id.iv_movie_add_);
                holder.iv_movie_add_.setOnClickListener(this);
                break;
            case 3 :
                convertView = View.inflate(activity, R.layout.movie_info_adapter_actor_type,null);
                holder.tv_actorcount_add_ = (TextView) convertView.findViewById(R.id.tv_actorcount_add_);
                holder.rcv_actor_add_detials = (RecyclerView) convertView.findViewById(R.id.rcv_actor_add_detials);
                boolean t = (personInMovieBean == null);
                Log.e("TAG", "personInMovieBean =="+t);
                if(personInMovieBean != null) {
                    int size = personInMovieBean.getTypes().get(1).getPersons().size()+1;
                    holder.tv_actorcount_add_.setText(size);
                    holder.rcv_actor_add_detials.setHasFixedSize(true);
                    LinearLayoutManager layout = new LinearLayoutManager(activity);
                    layout.setOrientation(LinearLayoutManager.HORIZONTAL);
                    holder.rcv_actor_add_detials.setLayoutManager(layout);
                    holder.rcv_actor_add_detials.setAdapter(new MyAdapter());
                }
                break;
            case 4 :
                convertView = View.inflate(activity,R.layout.movie_info_adapter_review,null);
                break;
        }

        return convertView;
    }


    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.iv_movie_add_) {
            Intent intent = new Intent(activity, VidoListActivity.class);
            activity.startActivity(intent);
        }
    }

    class ViewHoler{
        public TextView tv_descmovie_add_detials;
        public TextView tv_desallcmovie_add_detials;
        public ImageButton ib_extend_textdesc_add_detials;
        public ImageView iv_movie_add_;
        public TextView tv_actorcount_add_;
        public RecyclerView rcv_actor_add_detials;
    }

    private class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            MyViewHolder holder = null;
            if(viewType == 1) {
                holder = new MyViewHolder(LayoutInflater.from(
                        activity).inflate(R.layout.recy_movie_info_director_item, parent,
                        false));
            }else {
                holder = new MyViewHolder(LayoutInflater.from(
                        activity).inflate(R.layout.recy_movie_info_actor_item, parent,
                        false));
            }
            return holder;
        }

        @Override
        public void onBindViewHolder(MyViewHolder holder, int position) {
            //加载holder
            if(position == 0) {
                PersonInMovieBean.TypesEntity typesEntity = personInMovieBean.getTypes().get(0);
                Glide.with(activity).load(typesEntity.getPersons().get(0).getImage()).into(holder.icon_personmovie_add_);
                String name = typesEntity.getPersons().get(0).getName();
                String nameEn = typesEntity.getPersons().get(0).getNameEn();
                holder.tv_hz_person_.setText(name);
                holder.tv_py_person_.setText(nameEn);
            }
            PersonInMovieBean.TypesEntity typesEntity = personInMovieBean.getTypes().get(1);
            PersonInMovieBean.TypesEntity.PersonsEntity personsEntity = typesEntity.getPersons().get(position - 1);
            String image = personsEntity.getImage();
            String name = personsEntity.getName();
            String nameEn = personsEntity.getNameEn();
            String personate = personsEntity.getPersonate();
            String roleCover = personsEntity.getRoleCover();
            Glide.with(activity).load(image).into(holder.icon_personmovieactor_add_);
            Glide.with(activity).load(roleCover).into(holder.icon_in_personmovieactor_add_);
            holder.tv_hz_personactor_.setText(name);
            holder.tv_py_person_.setText(nameEn);
            holder.tv_in_personmovieactor_add_.setText(personate);
        }

        @Override
        public int getItemViewType(int position) {
            if(position == 0){
                return 1;
            }
            return 2;
        }

        @Override
        public int getItemCount() {
            return personInMovieBean.getTypes().get(1).getPersons().size()+1;
        }

        class MyViewHolder extends RecyclerView.ViewHolder{
            private ImageView icon_personmovie_add_;
            private TextView tv_hz_person_;
            private TextView tv_py_person_;
            private ImageView icon_personmovieactor_add_;
            private TextView tv_hz_personactor_;
            private TextView tv_py_personactor_;
            private ImageView icon_in_personmovieactor_add_;
            private TextView tv_in_personmovieactor_add_;
            public MyViewHolder(View itemView) {
                super(itemView);
                icon_personmovie_add_ = (ImageView) itemView.findViewById(R.id.icon_personmovie_add_);
                icon_personmovieactor_add_ = (ImageView) itemView.findViewById(R.id.icon_personmovieactor_add_);
                icon_in_personmovieactor_add_ = (ImageView) itemView.findViewById(R.id.icon_in_personmovieactor_add_);
                tv_hz_person_ = (TextView) itemView.findViewById(R.id.tv_hz_person_);
                tv_py_person_ = (TextView) itemView.findViewById(R.id.tv_py_person_);
                tv_hz_personactor_ = (TextView) itemView.findViewById(R.id.tv_hz_personactor_);
                tv_py_personactor_ = (TextView) itemView.findViewById(R.id.tv_py_personactor_);
                tv_in_personmovieactor_add_ = (TextView) itemView.findViewById(R.id.tv_in_personmovieactor_add_);

            }
        }
    }
}
