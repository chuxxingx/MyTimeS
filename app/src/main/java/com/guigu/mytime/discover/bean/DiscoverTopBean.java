package com.guigu.mytime.discover.bean;

/**
 * Created by chu on 2016/3/1.
 */
public class DiscoverTopBean {

    public Top_News news;// 新闻


    public class Top_News {
        public String imageUrl;
        public int newsID;
        public String title;
        public int type;


        @Override
        public String toString() {
            return "Top_News [imageUrl=" + imageUrl + ", newsID=" + newsID
                    + ", title=" + title + ", type=" + type + "]";
        }

    }
}
