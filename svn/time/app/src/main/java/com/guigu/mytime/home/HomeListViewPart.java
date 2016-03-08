package com.guigu.mytime.home;


import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.guigu.mytime.R;
import com.guigu.mytime.home.bean.FirstPageHomeFeedBean;

import org.xutils.common.util.DensityUtil;
import org.xutils.image.ImageOptions;
import org.xutils.x;

import java.util.List;

/**
 * Created by wuyapeng on 2016/3/1.
 */
public class HomeListViewPart extends BaseAdapter {

    private static final int ITEM_RECOMMEND_MOVIE_TYPE1 = 1;
    private static final int ITEM_FILM_COMMENT_TYPE2 = 2;
    private static final int ITEM_TOP_NEWS_TYPE3 = 3;
    private static final int ITEM_PIC_GROUP_TYPE4 = 4;
    private static final int ITEM_SHORT_NEWS_TYPE5 = 5;
    private static final int ITEM_MOVIE_ACTOR_LIST_TYPE6 = 6;
    private static final String TAG = HomeListViewPart.class.getSimpleName();

    private Context mContext;
    private LayoutInflater mInflater;
    private FirstPageHomeFeedBean homeFeedBean;
    private List<FirstPageHomeFeedBean.Data> homeFeedListData;
    private FirstPageHomeFeedBean.Data homeFeedData;
    private ImageOptions imageOptions1;
    private ImageOptions userImageOptions;

    public HomeListViewPart(Context context, List<FirstPageHomeFeedBean.Data> homeFeedListData, FirstPageHomeFeedBean homeFeedBean) {
        this.mContext = context;
        this.homeFeedBean = homeFeedBean;
        this.homeFeedListData = homeFeedListData;
        this.mInflater = LayoutInflater.from(context);
        homeFeedData = new FirstPageHomeFeedBean.Data();
        imageOptions1 = new ImageOptions.Builder()//item的图片设置
                .setSize(DensityUtil.dip2px(300), DensityUtil.dip2px(200))
                .setRadius(DensityUtil.dip2px(5))
                        // 如果ImageView的大小不是定义为wrap_content, 不要crop.
                .setCrop(false)
                        // 加载中或错误图片的ScaleType
                        //.setPlaceholderScaleType(ImageView.ScaleType.MATRIX)
                .setImageScaleType(ImageView.ScaleType.FIT_XY)
                .setLoadingDrawableId(R.drawable.img_default_300x200)
                .setFailureDrawableId(R.drawable.img_default_300x200)
                .build();
        userImageOptions = new ImageOptions.Builder()//item的图片设置
                .setSize(DensityUtil.dip2px(20), DensityUtil.dip2px(20))
                .setRadius(DensityUtil.dip2px(5))
                        // 如果ImageView的大小不是定义为wrap_content, 不要crop.
                .setCrop(false)
                        // 加载中或错误图片的ScaleType
                        //.setPlaceholderScaleType(ImageView.ScaleType.MATRIX)
                .setCircular(true)
                .setImageScaleType(ImageView.ScaleType.CENTER_CROP)
                .setLoadingDrawableId(R.drawable.img_default_45x45)
                .setFailureDrawableId(R.drawable.img_default_45x45)
                .build();
    }

    /**
     * 佳片有约 佳片推荐  datatype 无 type -1
     * <p/>
     * 影评 datatype 无 type 336
     * <p/>
     * 头条 datatype 0 type 51
     * 图集 datatype 1 type 51
     * 简讯 datatype 2  type 51
     * <p/>
     * 电影榜单 影人榜单 datatype 无 type 90
     *
     * @return
     */
    @Override
    public int getViewTypeCount() {
        return homeFeedBean.count + 1;
    }//1--6刚好6种类型

    @Override
    public int getItemViewType(int position) {

        homeFeedData = homeFeedListData.get(position);
        String tag = homeFeedData.tag;

        int itemType = -1;
        switch (tag) {
            case "佳片有约":
                itemType = ITEM_RECOMMEND_MOVIE_TYPE1;
                break;
            case "佳片推荐":
                itemType = ITEM_RECOMMEND_MOVIE_TYPE1;
                break;
            case "影评":
                itemType = ITEM_FILM_COMMENT_TYPE2;
                break;
            case "头条":
                itemType = ITEM_TOP_NEWS_TYPE3;
                break;
            case "简讯":
                itemType = ITEM_SHORT_NEWS_TYPE5;
                break;
            case "图集":
                itemType = ITEM_PIC_GROUP_TYPE4;
                break;
            case "电影榜单":
                itemType = ITEM_MOVIE_ACTOR_LIST_TYPE6;
                break;
            case "影人榜单":
                itemType = ITEM_MOVIE_ACTOR_LIST_TYPE6;
                break;
            case "电视榜单":
                itemType = ITEM_MOVIE_ACTOR_LIST_TYPE6;
                break;
        }
        return itemType;
    }

    @Override
    public int getCount() {
        return homeFeedListData.size();
    }

    @Override
    public Object getItem(int position) {
        return homeFeedListData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        int type = getItemViewType(position);
        Log.e(TAG, "getView: " + type);
        ViewHoler holder = null;
        if (convertView == null) {
            holder = new ViewHoler();

            Log.e(TAG, "getView: " + type);
            switch (type) {
                case ITEM_RECOMMEND_MOVIE_TYPE1:
                    convertView = mInflater.inflate(R.layout.item_recommend_movie_type1, parent, false);
                    holder.tv_item_tag = (TextView) convertView.findViewById(R.id.tv_item_recommend_tag);
                    holder.tv_item_recommend_ch_name = (TextView) convertView.findViewById(R.id.tv_item_recommend_ch_name);
                    holder.tv_item_recommend_en_name = (TextView) convertView.findViewById(R.id.tv_item_recommend_en_name);
                    holder.tv_item_recommend_content = (TextView) convertView.findViewById(R.id.tv_item_recommend_content);
                    holder.iv_item_recommend_icon = (ImageView) convertView.findViewById(R.id.iv_item_recommend_icon);

                    break;
                case ITEM_FILM_COMMENT_TYPE2:
                    convertView = mInflater.inflate(R.layout.item_film_comment_type2, parent, false);
                    holder.tv_item_film_comment_title = (TextView) convertView.findViewById(R.id.tv_item_film_comment_title);
                    holder.tv_item_film_comment_summaryInfo = (TextView) convertView.findViewById(R.id.tv_item_film_comment_summaryInfo);
                    holder.tv_item_film_comment_nickname = (TextView) convertView.findViewById(R.id.tv_item_film_comment_nickname);
                    holder.tv_item_film_comment_rating = (TextView) convertView.findViewById(R.id.tv_item_film_comment_rating);
                    holder.iv_item_film_comment_icon = (ImageView) convertView.findViewById(R.id.iv_item_film_comment_icon);
                    holder.iv_item_film_comment_usericon = (ImageView) convertView.findViewById(R.id.tv_item_film_comment_user_img);
                    break;
                case ITEM_TOP_NEWS_TYPE3:
                    convertView = mInflater.inflate(R.layout.item_top_news_type3, parent, false);

                    holder.iv_item_top_news_img1 = (ImageView) convertView.findViewById(R.id.iv_item_top_news_img1);
                    holder.tv_item_top_news_title = (TextView) convertView.findViewById(R.id.tv_item_top_news_title);
                    holder.tv_item_top_news_time = (TextView) convertView.findViewById(R.id.tv_item_top_news_time);
                    holder.tv_item_top_news_comment_count = (TextView) convertView.findViewById(R.id.tv_item_top_news_comment_count);
                    break;
                case ITEM_PIC_GROUP_TYPE4:
                    convertView = mInflater.inflate(R.layout.item_pic_group_type4, parent, false);
                    holder.tv_item_pic_group_title = (TextView) convertView.findViewById(R.id.tv_item_pic_group_title);
                    holder.tv_item_pic_group_time = (TextView) convertView.findViewById(R.id.tv_item_pic_group_time);
                    holder.tv_item_pic_group_comment_count = (TextView) convertView.findViewById(R.id.tv_item_pic_group_comment_count);
                    holder.iv_item_pic_group_img1 = (ImageView) convertView.findViewById(R.id.iv_item_pic_group_img1);
                    holder.iv_item_pic_group_img2 = (ImageView) convertView.findViewById(R.id.iv_item_pic_group_img2);
                    holder.iv_item_pic_group_img3 = (ImageView) convertView.findViewById(R.id.iv_item_pic_group_img3);
                    break;
                case ITEM_SHORT_NEWS_TYPE5:
                    convertView = mInflater.inflate(R.layout.item_short_news_type5, parent, false);
                    holder.tv_item_short_news_content = (TextView) convertView.findViewById(R.id.tv_item_short_news_content);
                    holder.iv_item_short_news_img1 = (ImageView) convertView.findViewById(R.id.iv_item_short_news_img1);
                    holder.tv_item_short_news_title = (TextView) convertView.findViewById(R.id.tv_item_short_news_title);
                    holder.tv_item_short_news_time = (TextView) convertView.findViewById(R.id.tv_item_short_news_time);
                    holder.tv_item_short_news_comment_count = (TextView) convertView.findViewById(R.id.tv_item_short_news_comment_count);
                    break;
                case ITEM_MOVIE_ACTOR_LIST_TYPE6:
                    convertView = mInflater.inflate(R.layout.item_movie_actor_list_type6, parent, false);

                    holder.tv_item_tag = (TextView) convertView.findViewById(R.id.tv_item_actor_list_tag);
                    holder.tv_item_actor_list_title = (TextView) convertView.findViewById(R.id.tv_item_actor_list_title);
                    holder.tv_item_actor_list_Memo = (TextView) convertView.findViewById(R.id.tv_item_actor_list_Memo);

                    holder.iv_item_actor_list_posterUrl1 = (ImageView) convertView.findViewById(R.id.iv_item_actor_list_posterUrl1);
                    holder.iv_item_actor_list_actorName1 = (TextView) convertView.findViewById(R.id.iv_item_actor_list_actorName1);
                    holder.iv_item_actor_list_RatingOrTime1 = (TextView) convertView.findViewById(R.id.iv_item_actor_list_RatingOrTime1);

                    holder.iv_item_actor_list_posterUrl2 = (ImageView) convertView.findViewById(R.id.iv_item_actor_list_posterUrl2);
                    holder.iv_item_actor_list_actorName2 = (TextView) convertView.findViewById(R.id.iv_item_actor_list_actorName2);
                    holder.iv_item_actor_list_RatingOrTime2 = (TextView) convertView.findViewById(R.id.iv_item_actor_list_RatingOrTime2);

                    holder.iv_item_actor_list_posterUrl3 = (ImageView) convertView.findViewById(R.id.iv_item_actor_list_posterUrl3);
                    holder.iv_item_actor_list_actorName3 = (TextView) convertView.findViewById(R.id.iv_item_actor_list_actorName3);
                    holder.iv_item_actor_list_RatingOrTime3 = (TextView) convertView.findViewById(R.id.iv_item_actor_list_RatingOrTime3);
                    break;
                default:
                    convertView = mInflater.inflate(R.layout.item_other_type, parent, false);
                    break;
            }

            convertView.setTag(holder);
        } else {
            holder = (ViewHoler) convertView.getTag();
        }
        FirstPageHomeFeedBean.Data itemData = homeFeedListData.get(position);

        switch (type) {
            case ITEM_RECOMMEND_MOVIE_TYPE1:
                holder.tv_item_recommend_ch_name.setText(itemData.titleCn);
                holder.tv_item_recommend_en_name.setText(itemData.titleEn);
                holder.tv_item_recommend_content.setText(itemData.content);
                x.image().bind(holder.iv_item_recommend_icon, itemData.image, imageOptions1);
                break;
            case ITEM_FILM_COMMENT_TYPE2:
                holder.tv_item_film_comment_title.setText(itemData.title);
                holder.tv_item_film_comment_summaryInfo.setText(itemData.summaryInfo);
                holder.tv_item_film_comment_nickname.setText(itemData.nickname + "-评《" + itemData.relatedObj.titleCn + "》");
                holder.tv_item_film_comment_rating.setText(itemData.rating);
                x.image().bind(holder.iv_item_film_comment_usericon, itemData.userImage, userImageOptions);
                x.image().bind(holder.iv_item_film_comment_icon, itemData.relatedObj.image, imageOptions1);

                break;
            case ITEM_TOP_NEWS_TYPE3:
                holder.tv_item_top_news_title.setText(itemData.title);
                holder.tv_item_top_news_time.setText(itemData.time);
                holder.tv_item_top_news_comment_count.setText("  评论 " + itemData.commentCount);
                x.image().bind(holder.iv_item_top_news_img1, itemData.img1, imageOptions1);
                break;
            case ITEM_PIC_GROUP_TYPE4:
                holder.tv_item_pic_group_title.setText(itemData.title);
                holder.tv_item_pic_group_time.setText(itemData.time);
                holder.tv_item_pic_group_comment_count.setText("  评论 " + itemData.commentCount);
                x.image().bind(holder.iv_item_pic_group_img1, itemData.img1, imageOptions1);
                x.image().bind(holder.iv_item_pic_group_img2, itemData.img2, imageOptions1);
                x.image().bind(holder.iv_item_pic_group_img3, itemData.img3, imageOptions1);

                break;
            case ITEM_SHORT_NEWS_TYPE5:
                holder.tv_item_short_news_title.setText(itemData.title);
                holder.tv_item_short_news_content.setText(itemData.content);
                holder.tv_item_short_news_time.setText(itemData.time);
                holder.tv_item_short_news_comment_count.setText("  评论 " + itemData.commentCount);
                x.image().bind(holder.iv_item_short_news_img1, itemData.img1, imageOptions1);
                break;
            case ITEM_MOVIE_ACTOR_LIST_TYPE6:
                holder.tv_item_tag.setText(itemData.tag);
                holder.tv_item_actor_list_title.setText(itemData.title);
                holder.tv_item_actor_list_Memo.setText(itemData.Memo);
                if ("影人榜单".equals(holder.tv_item_tag.getText().toString())) {//影人榜单
                    x.image().bind(holder.iv_item_actor_list_posterUrl1, itemData.persons.get(0).posterUrl, imageOptions1);
                    holder.iv_item_actor_list_actorName1.setText(itemData.persons.get(0).nameCn);
                    holder.iv_item_actor_list_RatingOrTime1.setText(itemData.persons.get(0).rating);

                    x.image().bind(holder.iv_item_actor_list_posterUrl2, itemData.persons.get(1).posterUrl, imageOptions1);
                    holder.iv_item_actor_list_actorName2.setText(itemData.persons.get(1).nameCn);
                    holder.iv_item_actor_list_RatingOrTime2.setText(itemData.persons.get(1).rating);

                    x.image().bind(holder.iv_item_actor_list_posterUrl3, itemData.persons.get(2).posterUrl, imageOptions1);
                    holder.iv_item_actor_list_actorName3.setText(itemData.persons.get(2).nameCn);
                    holder.iv_item_actor_list_RatingOrTime3.setText(itemData.persons.get(2).rating);

                } else {//电视榜单/电影榜单
                }
                break;
            default:
                Log.e(TAG, "getView: 出错了");
                break;
        }

        return convertView;
    }

    static class ViewHoler {
        int id;
        TextView tv_item_tag;
        //佳片推荐
        TextView tv_item_recommend_ch_name;
        TextView tv_item_recommend_en_name;
        TextView tv_item_recommend_content;
        ImageView iv_item_recommend_icon;
        //影评
        TextView tv_item_film_comment_title;
        TextView tv_item_film_comment_summaryInfo;
        TextView tv_item_film_comment_nickname;
        TextView tv_item_film_comment_rating;
        ImageView iv_item_film_comment_icon;
        ImageView iv_item_film_comment_usericon;
        //头条
        TextView tv_item_top_news_title;
        ImageView iv_item_top_news_img1;
        TextView tv_item_top_news_time;
        TextView tv_item_top_news_comment_count;

        //图集
        TextView tv_item_pic_group_title;
        TextView tv_item_pic_group_time;
        TextView tv_item_pic_group_comment_count;
        ImageView iv_item_pic_group_img1;
        ImageView iv_item_pic_group_img2;
        ImageView iv_item_pic_group_img3;
        //简讯
        TextView tv_item_short_news_title;
        TextView tv_item_short_news_content;
        TextView tv_item_short_news_time;
        TextView tv_item_short_news_comment_count;
        ImageView iv_item_short_news_img1;

        //影人榜单
        TextView tv_item_actor_list_title;
        TextView tv_item_actor_list_Memo;

        ImageView iv_item_actor_list_posterUrl1;
        TextView iv_item_actor_list_actorName1;
        TextView iv_item_actor_list_RatingOrTime1;

        ImageView iv_item_actor_list_posterUrl2;
        TextView iv_item_actor_list_actorName2;
        TextView iv_item_actor_list_RatingOrTime2;

        ImageView iv_item_actor_list_posterUrl3;
        TextView iv_item_actor_list_actorName3;
        TextView iv_item_actor_list_RatingOrTime3;
    }
}