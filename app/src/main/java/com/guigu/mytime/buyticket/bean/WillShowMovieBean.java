package com.guigu.mytime.buyticket.bean;

import java.util.List;

/**
 * Created by Administrator on 2016/3/1.
 */
public class WillShowMovieBean {

    private List<AttentionEntity> attention;
    /**
     * id : 230667
     * title : 别开门
     * image : http://img31.mtime.cn/mt/2015/12/23/141225.78004234_1280X720X2.jpg
     * releaseDate : 3月3日上映
     * rYear : 2016
     * rMonth : 3
     * rDay : 3
     * type : 惊悚 | 恐怖 | 悬疑
     * director : 周阁
     * actor1 : 徐立
     * actor2 : 曲少石
     * locationName : 中国
     * isTicket : false
     * wantedCount : 32
     * isFilter : true
     * videoCount : 0
     * videos : []
     * isVideo : false
     */

    private List<MoviecomingsEntity> moviecomings;

    public void setAttention(List<AttentionEntity> attention) {
        this.attention = attention;
    }

    public void setMoviecomings(List<MoviecomingsEntity> moviecomings) {
        this.moviecomings = moviecomings;
    }

    public List<AttentionEntity> getAttention() {
        return attention;
    }

    public List<MoviecomingsEntity> getMoviecomings() {
        return moviecomings;
    }

    public static class AttentionEntity {
        private int id;
        private String title;
        private String image;
        private String releaseDate;
        private int rYear;
        private int rMonth;
        private int rDay;
        private String type;
        private String director;
        private String actor1;
        private String actor2;
        private String locationName;
        private boolean isTicket;
        private int wantedCount;
        private boolean isFilter;
        private int videoCount;
        private boolean isVideo;
        /**
         * videoId : 58753
         * url : http://vfx.mtime.cn/Video/2016/02/03/mp4/160203105748542664_480.mp4
         * hightUrl : http://vfx.mtime.cn/Video/2016/02/03/mp4/160203105748542664.mp4
         * image : http://img31.mtime.cn/mg/2016/02/03/105555.28024776.jpg
         * length : 204
         * title : 疯狂动物城 中文配音版预告片
         */

        private List<VideosEntity> videos;

        public void setId(int id) {
            this.id = id;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public void setReleaseDate(String releaseDate) {
            this.releaseDate = releaseDate;
        }

        public void setRYear(int rYear) {
            this.rYear = rYear;
        }

        public void setRMonth(int rMonth) {
            this.rMonth = rMonth;
        }

        public void setRDay(int rDay) {
            this.rDay = rDay;
        }

        public void setType(String type) {
            this.type = type;
        }

        public void setDirector(String director) {
            this.director = director;
        }

        public void setActor1(String actor1) {
            this.actor1 = actor1;
        }

        public void setActor2(String actor2) {
            this.actor2 = actor2;
        }

        public void setLocationName(String locationName) {
            this.locationName = locationName;
        }

        public void setIsTicket(boolean isTicket) {
            this.isTicket = isTicket;
        }

        public void setWantedCount(int wantedCount) {
            this.wantedCount = wantedCount;
        }

        public void setIsFilter(boolean isFilter) {
            this.isFilter = isFilter;
        }

        public void setVideoCount(int videoCount) {
            this.videoCount = videoCount;
        }

        public void setIsVideo(boolean isVideo) {
            this.isVideo = isVideo;
        }

        public void setVideos(List<VideosEntity> videos) {
            this.videos = videos;
        }

        public int getId() {
            return id;
        }

        public String getTitle() {
            return title;
        }

        public String getImage() {
            return image;
        }

        public String getReleaseDate() {
            return releaseDate;
        }

        public int getRYear() {
            return rYear;
        }

        public int getRMonth() {
            return rMonth;
        }

        public int getRDay() {
            return rDay;
        }

        public String getType() {
            return type;
        }

        public String getDirector() {
            return director;
        }

        public String getActor1() {
            return actor1;
        }

        public String getActor2() {
            return actor2;
        }

        public String getLocationName() {
            return locationName;
        }

        public boolean isIsTicket() {
            return isTicket;
        }

        public int getWantedCount() {
            return wantedCount;
        }

        public boolean isIsFilter() {
            return isFilter;
        }

        public int getVideoCount() {
            return videoCount;
        }

        public boolean isIsVideo() {
            return isVideo;
        }

        public List<VideosEntity> getVideos() {
            return videos;
        }

        public static class VideosEntity {
            private int videoId;
            private String url;
            private String hightUrl;
            private String image;
            private int length;
            private String title;

            public void setVideoId(int videoId) {
                this.videoId = videoId;
            }

            public void setUrl(String url) {
                this.url = url;
            }

            public void setHightUrl(String hightUrl) {
                this.hightUrl = hightUrl;
            }

            public void setImage(String image) {
                this.image = image;
            }

            public void setLength(int length) {
                this.length = length;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public int getVideoId() {
                return videoId;
            }

            public String getUrl() {
                return url;
            }

            public String getHightUrl() {
                return hightUrl;
            }

            public String getImage() {
                return image;
            }

            public int getLength() {
                return length;
            }

            public String getTitle() {
                return title;
            }
        }
    }

    public static class MoviecomingsEntity {
        private int id;
        private String title;
        private String image;
        private String releaseDate;
        private int rYear;
        private int rMonth;
        private int rDay;
        private String type;
        private String director;
        private String actor1;
        private String actor2;
        private String locationName;
        private boolean isTicket;
        private int wantedCount;
        private boolean isFilter;
        private int videoCount;
        private boolean isVideo;
        private List<?> videos;

        public void setId(int id) {
            this.id = id;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public void setReleaseDate(String releaseDate) {
            this.releaseDate = releaseDate;
        }

        public void setRYear(int rYear) {
            this.rYear = rYear;
        }

        public void setRMonth(int rMonth) {
            this.rMonth = rMonth;
        }

        public void setRDay(int rDay) {
            this.rDay = rDay;
        }

        public void setType(String type) {
            this.type = type;
        }

        public void setDirector(String director) {
            this.director = director;
        }

        public void setActor1(String actor1) {
            this.actor1 = actor1;
        }

        public void setActor2(String actor2) {
            this.actor2 = actor2;
        }

        public void setLocationName(String locationName) {
            this.locationName = locationName;
        }

        public void setIsTicket(boolean isTicket) {
            this.isTicket = isTicket;
        }

        public void setWantedCount(int wantedCount) {
            this.wantedCount = wantedCount;
        }

        public void setIsFilter(boolean isFilter) {
            this.isFilter = isFilter;
        }

        public void setVideoCount(int videoCount) {
            this.videoCount = videoCount;
        }

        public void setIsVideo(boolean isVideo) {
            this.isVideo = isVideo;
        }

        public void setVideos(List<?> videos) {
            this.videos = videos;
        }

        public int getId() {
            return id;
        }

        public String getTitle() {
            return title;
        }

        public String getImage() {
            return image;
        }

        public String getReleaseDate() {
            return releaseDate;
        }

        public int getRYear() {
            return rYear;
        }

        public int getRMonth() {
            return rMonth;
        }

        public int getRDay() {
            return rDay;
        }

        public String getType() {
            return type;
        }

        public String getDirector() {
            return director;
        }

        public String getActor1() {
            return actor1;
        }

        public String getActor2() {
            return actor2;
        }

        public String getLocationName() {
            return locationName;
        }

        public boolean isIsTicket() {
            return isTicket;
        }

        public int getWantedCount() {
            return wantedCount;
        }

        public boolean isIsFilter() {
            return isFilter;
        }

        public int getVideoCount() {
            return videoCount;
        }

        public boolean isIsVideo() {
            return isVideo;
        }

        public List<?> getVideos() {
            return videos;
        }
    }
}
