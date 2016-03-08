package com.guigu.mytime.home.bean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Steve on 2016/1/13.
 */
public class FirstPageHomeFeedBean {
    public int count;
    public List<Data> data;

    public static class Data {
        /**
         * 以下为所有公共部分
         */
        public int id;
        public int type;
        public String tag;

        public String title;//简讯、头条、图集、电影榜单、影人榜单 、影评

        public String isShow;//简讯、头条、图集、电影榜单、影人榜单、佳片有约
        //简讯、头条、图集、佳片有约
        public String content;
        /**
         * 简讯、头条、图集
         */

        public int commentCount;
        public int dataType;
        public String img1;
        public String img2;
        public String img3;

        public String publishTime;
        public ArrayList<Relation> relations;//相关的影片或者人物
        public int status;//佳片有约也有
        public String time;
        public String titlesmall;
        /**
         * 图集独有
         */
        public ArrayList<Image> images;
        /**
         * 以下为 《佳片有约》与《佳片推荐》独有部分
         */
        public String image;
        public String rating;//佳片有约、影评
        public String titleCn;
        public String titleEn;

        /**
         * 以下为电影榜单、影人榜单独有
         */
        public String Memo;
        public String Time;
        /**
         * 电影榜单独有
         */
        public ArrayList<Movie> movies;
        /**
         * 影人榜单独有
         */
        public ArrayList<Person> persons;
        /**
         * 以下为影评独有
         */
        public String nickname;
        public RelatedObj relatedObj;
        public String summaryInfo;
        public String userImage;

        @Override
        public String toString() {
            return "Data{" +
                    "id=" + id +
                    ", type=" + type +
                    ", tag='" + tag + '\'' +
                    ", title='" + title + '\'' +
                    ", isShow='" + isShow + '\'' +
                    ", content='" + content + '\'' +
                    ", commentCount=" + commentCount +
                    ", dataType=" + dataType +
                    ", img1='" + img1 + '\'' +
                    ", img2='" + img2 + '\'' +
                    ", img3='" + img3 + '\'' +
                    ", publishTime='" + publishTime + '\'' +
                    ", relations=" + relations +
                    ", status=" + status +
                    ", time='" + time + '\'' +
                    ", titlesmall='" + titlesmall + '\'' +
                    ", images=" + images +
                    ", image='" + image + '\'' +
                    ", rating='" + rating + '\'' +
                    ", titleCn='" + titleCn + '\'' +
                    ", titleEn='" + titleEn + '\'' +
                    ", Memo='" + Memo + '\'' +
                    ", Time='" + Time + '\'' +
                    ", movies=" + movies +
                    ", persons=" + persons +
                    ", nickname='" + nickname + '\'' +
                    ", relatedObj=" + relatedObj +
                    ", summaryInfo='" + summaryInfo + '\'' +
                    ", userImage='" + userImage + '\'' +
                    '}';
        }

        /**
         * relation对应的对象
         */
        public static class Relation {
            /**
             * 共有
             */
            public int id;
            public String image;
            public String name;
            public String rating;
            public int type;//影片 =1,人物 =2
            public int year;

            /**
             * 影片独有
             */
            public String relaseLocation;
            public String releaseDate;
            public int scoreCount;

            public String nameEn;//人物独有

            @Override
            public String toString() {
                return "Relation{" +
                        "id=" + id +
                        ", image='" + image + '\'' +
                        ", name='" + name + '\'' +
                        ", rating='" + rating + '\'' +
                        ", type=" + type +
                        ", year=" + year +
                        ", relaseLocation='" + relaseLocation + '\'' +
                        ", releaseDate='" + releaseDate + '\'' +
                        ", scoreCount=" + scoreCount +
                        ", nameEn='" + nameEn + '\'' +
                        '}';
            }
        }

        /**
         * 图集对应的图片
         */
        public static class Image {
            public String desc;
            public int gId;
            public String title;
            public String url1;
            public String url2;

            @Override
            public String toString() {
                return "Image{" +
                        "desc='" + desc + '\'' +
                        ", gId=" + gId +
                        ", title='" + title + '\'' +
                        ", url1='" + url1 + '\'' +
                        ", url2='" + url2 + '\'' +
                        '}';
            }
        }

        /**
         * 电影榜电影类
         */
        public static class Movie {
            public String actor;
            public String actor2;
            public int decade;
            public String director;
            public int id;
            public String name;
            public String nameEn;
            public String posterUrl;
            public String rating;
            public String releaseLocation;

            @Override
            public String toString() {
                return "Movie{" +
                        "actor='" + actor + '\'' +
                        ", actor2='" + actor2 + '\'' +
                        ", decade=" + decade +
                        ", director='" + director + '\'' +
                        ", id=" + id +
                        ", name='" + name + '\'' +
                        ", nameEn='" + nameEn + '\'' +
                        ", posterUrl='" + posterUrl + '\'' +
                        ", rating='" + rating + '\'' +
                        ", releaseLocation='" + releaseLocation + '\'' +
                        '}';
            }
        }

        /**
         * 影人榜的影人类
         */
        public static class Person {
            public String birthLocation;
            public String birthYear;
            public String constellation;
            public int id;
            public String nameCn;
            public String nameEn;
            public String posterUrl;
            public String rating;
            public String sex;

            @Override
            public String toString() {
                return "Person{" +
                        "birthLocation='" + birthLocation + '\'' +
                        ", birthYear='" + birthYear + '\'' +
                        ", constellation='" + constellation + '\'' +
                        ", id=" + id +
                        ", nameCn='" + nameCn + '\'' +
                        ", nameEn='" + nameEn + '\'' +
                        ", posterUrl='" + posterUrl + '\'' +
                        ", rating='" + rating + '\'' +
                        ", sex='" + sex + '\'' +
                        '}';
            }
        }

        /**
         * 影评的相关对象
         */
        public static class RelatedObj {
            public int id;
            public String image;
            public String name;
            public String rating;
            public String releaseLocation;
            public String runtime;
            public String title;
            public String titleCn;
            public String titleEn;
            public List<String> type;
            public String url;
            public String wapUrl;

            @Override
            public String toString() {
                return "RelatedObj{" +
                        "id=" + id +
                        ", image='" + image + '\'' +
                        ", name='" + name + '\'' +
                        ", rating='" + rating + '\'' +
                        ", releaseLocation='" + releaseLocation + '\'' +
                        ", runtime='" + runtime + '\'' +
                        ", title='" + title + '\'' +
                        ", titleCn='" + titleCn + '\'' +
                        ", titleEn='" + titleEn + '\'' +
                        ", type=" + type +
                        ", url='" + url + '\'' +
                        ", wapUrl='" + wapUrl + '\'' +
                        '}';
            }
        }
    }
}
