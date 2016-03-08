package com.guigu.mytime.discover.bean;

import java.util.List;

/**
 * Created by chu on 2016/3/2.
 */
public class Prevue_bean {


    /**
     * id : 59172
     * movieName : 《叶问3》终极预告
     * coverImg : http://img31.mtime.cn/mg/2016/03/02/112845.30407626.jpg
     * url :
     * summary : 咏春拳挑战拳王泰森
     */

    private List<TrailersEntity> trailers;

    public void setTrailers(List<TrailersEntity> trailers) {
        this.trailers = trailers;
    }

    public  List<TrailersEntity> getTrailers() {
        return trailers;
    }

    public static class TrailersEntity {
        private int id;
        private String movieName;
        private String coverImg;
        private String url;
        private String summary;

        public void setId(int id) {
            this.id = id;
        }

        public void setMovieName(String movieName) {
            this.movieName = movieName;
        }

        public void setCoverImg(String coverImg) {
            this.coverImg = coverImg;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public void setSummary(String summary) {
            this.summary = summary;
        }

        public int getId() {
            return id;
        }

        public String getMovieName() {
            return movieName;
        }

        public String getCoverImg() {
            return coverImg;
        }

        public String getUrl() {
            return url;
        }

        public String getSummary() {
            return summary;
        }
    }
}
