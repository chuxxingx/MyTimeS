package com.guigu.mytime.buyticket.adapter;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.guigu.mytime.R;
import com.guigu.mytime.buyticket.bean.PersonInMovieBean;

import org.xutils.common.util.DensityUtil;
import org.xutils.image.ImageOptions;
import org.xutils.x;

import java.util.List;

/**
 * Created by Administrator on 2016/3/4.
 */
public class MyMovieRcyclerViewAdapter extends RecyclerView.Adapter<ViewHolder> {
    private Activity activity;
    private List<PersonInMovieBean.TypesEntity> types;
    private ImageOptions options1;
    private ImageOptions options2;
    private ImageOptions options3;

    public MyMovieRcyclerViewAdapter(Activity activity,List<PersonInMovieBean.TypesEntity> types){
        this.activity = activity;
        this.types = types;
        options1 = new ImageOptions.Builder()
                .setSize(DensityUtil.dip2px(110), DensityUtil.dip2px(155))
                .setRadius(DensityUtil.dip2px(5))
                        // 如果ImageView的大小不是定义为wrap_content, 不要crop.
                .setCrop(false)
                        // 加载中或错误图片的ScaleType
                        //.setPlaceholderScaleType(ImageView.ScaleType.MATRIX)
                .setImageScaleType(ImageView.ScaleType.CENTER_CROP)
                .build();
        options2 = new ImageOptions.Builder()
                .setSize(DensityUtil.dip2px(110), DensityUtil.dip2px(110))
                .setRadius(DensityUtil.dip2px(5))
                        // 如果ImageView的大小不是定义为wrap_content, 不要crop.
                .setCrop(false)
                        // 加载中或错误图片的ScaleType
                        //.setPlaceholderScaleType(ImageView.ScaleType.MATRIX)
                .setImageScaleType(ImageView.ScaleType.CENTER_CROP)
                .build();
        options3 = new ImageOptions.Builder()
                .setSize(DensityUtil.dip2px(40), DensityUtil.dip2px(40))
                .setRadius(DensityUtil.dip2px(5))
                        // 如果ImageView的大小不是定义为wrap_content, 不要crop.
                .setCrop(false)
                        // 加载中或错误图片的ScaleType
                        //.setPlaceholderScaleType(ImageView.ScaleType.MATRIX)
                .setImageScaleType(ImageView.ScaleType.CENTER_CROP)
                .build();
    }

    @Override
    public int getItemViewType(int position) {
        if(position == 0){
            return 1;
        }
        return 2;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        if(viewType == 1){
            view = View.inflate(activity, R.layout.recy_movie_info_director_item, null);

        }else{
            view = View.inflate(activity, R.layout.recy_movie_info_actor_item,null);

        }
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        if(position == 0){
            List<PersonInMovieBean.TypesEntity.PersonsEntity> persons = types.get(position).getPersons();
            PersonInMovieBean.TypesEntity.PersonsEntity personsEntity = persons.get(0);
            x.image().bind(holder.icon_personmovie_add_,personsEntity.getImage(),options1);
//                 ImageUtils.getBitmap(personsEntity.getImage(), new ImageUtils.OnBitmapLoadlistener() {
//                     @Override
//                     public void onBitmapLoad(Bitmap bitmap) {
//                         holder.icon_personmovie_add_.setImageBitmap(bitmap);
//                     }
//                 },DensityUtil.dip2px(110),DensityUtil.dip2px(155));
            holder.tv_hz_person_.setText(personsEntity.getName() + "");
            holder.tv_py_person_.setText(personsEntity.getNameEn()  +"");
        }else{
            List<PersonInMovieBean.TypesEntity.PersonsEntity> persons = types.get(1).getPersons();
            if(position == 1){
                holder.abcd.setVisibility(View.VISIBLE);
            }else{
                holder.abcd.setVisibility(View.INVISIBLE);
            }
            PersonInMovieBean.TypesEntity.PersonsEntity personsEntity = persons.get(position - 1);
            x.image().bind(holder.icon_personmovieactor_add_,personsEntity.getImage(),options2);
            x.image().bind(holder.icon_in_personmovieactor_add_,personsEntity.getRoleCover(),options3);
//                 ImageUtils.getBitmap(personsEntity.getImage(), new ImageUtils.OnBitmapLoadlistener() {
//                     @Override
//                     public void onBitmapLoad(Bitmap bitmap) {
//                         holder.icon_personmovie_add_.setImageBitmap(bitmap);
//                     }
//                 },DensityUtil.dip2px(110),DensityUtil.dip2px(110));
//                 ImageUtils.getBitmap(personsEntity.getRoleCover(), new ImageUtils.OnBitmapLoadlistener() {
//                     @Override
//                     public void onBitmapLoad(Bitmap bitmap) {
//                         holder.icon_in_personmovieactor_add_.setImageBitmap(bitmap);
//                     }
//                 },DensityUtil.dip2px(40),DensityUtil.dip2px(40));

            holder.tv_hz_personactor_.setText(personsEntity.getName() + "");
            holder.tv_py_personactor_.setText(personsEntity.getNameEn() + "");
            holder.tv_in_personmovieactor_add_.setText(personsEntity.getPersonate() + "");
        }
    }

    @Override
    public int getItemCount() {
        int size = types.get(1).getPersons().size();
        if( size>=19){
            return  19;
        }
        return size;
    }
}

class ViewHolder extends RecyclerView.ViewHolder{
    //导演
    ImageView icon_personmovie_add_;
    TextView tv_hz_person_;
    TextView tv_py_person_;
    //演员
    TextView abcd;
    ImageView icon_personmovieactor_add_;
    TextView tv_hz_personactor_;
    TextView tv_py_personactor_;
    ImageView icon_in_personmovieactor_add_;
    TextView tv_in_personmovieactor_add_;

    public ViewHolder(View itemView) {
        super(itemView);
        tv_py_person_ = (TextView) itemView.findViewById(R.id.tv_py_person_);
        tv_hz_person_ = (TextView) itemView.findViewById(R.id.tv_hz_person_);
        icon_personmovie_add_ = (ImageView) itemView.findViewById(R.id.icon_personmovie_add_);
        icon_personmovieactor_add_ = (ImageView) itemView.findViewById(R.id.icon_personmovieactor_add_);
        tv_hz_personactor_ = (TextView) itemView.findViewById(R.id.tv_hz_personactor_);
        tv_py_personactor_ = (TextView) itemView.findViewById(R.id.tv_py_personactor_);
        icon_in_personmovieactor_add_ = (ImageView) itemView.findViewById(R.id.icon_in_personmovieactor_add_);
        tv_in_personmovieactor_add_ = (TextView) itemView.findViewById(R.id.tv_in_personmovieactor_add_);
        abcd = (TextView) itemView.findViewById(R.id.abcd);
    }
}

