package com.guigu.mytime.discover.bean;

/**
 * Created by Administrator on 2016/1/13.
 */
public class Discover_head_imgs_bean {

    /**
     * newsID : 1551094
     * title : "星球大战:原力觉醒"细节玄机大起底
     * type : 0
     * imageUrl : http://img31.mtime.cn/mg/2016/01/13/082700.95669835.jpg
     */

    private NewsEntity news;
    /**
     * trailerID : 58394
     * title : 《金钱怪兽》预告片
     * imageUrl : http://img31.mtime.cn/mg/2016/01/13/172424.68604356.jpg
     * mp4Url : http://vfx.mtime.cn/Video/2016/01/13/mp4/160113114149705784_480.mp4
     * hightUrl : http://vfx.mtime.cn/Video/2016/01/13/mp4/160113114149705784.mp4
     * movieId : 221578
     */

    private TrailerEntity trailer;
    /**
     * reviewID : 7938132
     * title : 更接近老三部曲的星战片
     * posterUrl : http://img31.mtime.cn/mt/2015/12/02/103436.31724563_1280X720X2.jpg
     * movieName : 星球大战：原力觉醒
     * imageUrl : http://img31.mtime.cn/mg/2016/01/11/133423.59361028.jpg
     */

    private ReviewEntity review;
    /**
     * id : 10792
     * imageUrl : http://img31.mtime.cn/mg/2015/03/31/100230.43767720.jpg
     */

    private ViewingGuideEntity viewingGuide;
    /**
     * id : 1323
     * title : Time Out评50部最佳LGBT题材影片
     * imageUrl : http://img31.mtime.cn/mg/2015/12/01/100921.69825932.jpg
     * type : 0
     */

    private TopListEntity topList;

    public void setNews(NewsEntity news) {
        this.news = news;
    }

    public void setTrailer(TrailerEntity trailer) {
        this.trailer = trailer;
    }

    public void setReview(ReviewEntity review) {
        this.review = review;
    }

    public void setViewingGuide(ViewingGuideEntity viewingGuide) {
        this.viewingGuide = viewingGuide;
    }

    public void setTopList(TopListEntity topList) {
        this.topList = topList;
    }

    public NewsEntity getNews() {
        return news;
    }

    public TrailerEntity getTrailer() {
        return trailer;
    }

    public ReviewEntity getReview() {
        return review;
    }

    public ViewingGuideEntity getViewingGuide() {
        return viewingGuide;
    }

    public TopListEntity getTopList() {
        return topList;
    }

    public static class NewsEntity {
        private int newsID;
        private String title;
        private int type;
        private String imageUrl;

        public void setNewsID(int newsID) {
            this.newsID = newsID;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public void setType(int type) {
            this.type = type;
        }

        public void setImageUrl(String imageUrl) {
            this.imageUrl = imageUrl;
        }

        public int getNewsID() {
            return newsID;
        }

        public String getTitle() {
            return title;
        }

        public int getType() {
            return type;
        }

        public String getImageUrl() {
            return imageUrl;
        }
    }

    public static class TrailerEntity {
        private int trailerID;
        private String title;
        private String imageUrl;
        private String mp4Url;
        private String hightUrl;
        private int movieId;

        public void setTrailerID(int trailerID) {
            this.trailerID = trailerID;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public void setImageUrl(String imageUrl) {
            this.imageUrl = imageUrl;
        }

        public void setMp4Url(String mp4Url) {
            this.mp4Url = mp4Url;
        }

        public void setHightUrl(String hightUrl) {
            this.hightUrl = hightUrl;
        }

        public void setMovieId(int movieId) {
            this.movieId = movieId;
        }

        public int getTrailerID() {
            return trailerID;
        }

        public String getTitle() {
            return title;
        }

        public String getImageUrl() {
            return imageUrl;
        }

        public String getMp4Url() {
            return mp4Url;
        }

        public String getHightUrl() {
            return hightUrl;
        }

        public int getMovieId() {
            return movieId;
        }
    }

    public static class ReviewEntity {
        private int reviewID;
        private String title;
        private String posterUrl;
        private String movieName;
        private String imageUrl;

        public void setReviewID(int reviewID) {
            this.reviewID = reviewID;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public void setPosterUrl(String posterUrl) {
            this.posterUrl = posterUrl;
        }

        public void setMovieName(String movieName) {
            this.movieName = movieName;
        }

        public void setImageUrl(String imageUrl) {
            this.imageUrl = imageUrl;
        }

        public int getReviewID() {
            return reviewID;
        }

        public String getTitle() {
            return title;
        }

        public String getPosterUrl() {
            return posterUrl;
        }

        public String getMovieName() {
            return movieName;
        }

        public String getImageUrl() {
            return imageUrl;
        }
    }

    public static class ViewingGuideEntity {
        private String id;
        private String imageUrl;

        public void setId(String id) {
            this.id = id;
        }

        public void setImageUrl(String imageUrl) {
            this.imageUrl = imageUrl;
        }

        public String getId() {
            return id;
        }

        public String getImageUrl() {
            return imageUrl;
        }
    }

    public static class TopListEntity {
        private int id;
        private String title;
        private String imageUrl;
        private int type;

        public void setId(int id) {
            this.id = id;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public void setImageUrl(String imageUrl) {
            this.imageUrl = imageUrl;
        }

        public void setType(int type) {
            this.type = type;
        }

        public int getId() {
            return id;
        }

        public String getTitle() {
            return title;
        }

        public String getImageUrl() {
            return imageUrl;
        }

        public int getType() {
            return type;
        }
    }
}
