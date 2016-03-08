package com.guigu.mytime.buyticket.bean;

/**
 * Created by Administrator on 2016/3/4.
 */
public class BeanContainer {
    private static MovieListBean moviePreBean;

    public static MovieListBean getMoviePreBean() {
        return moviePreBean;
    }

    public static void setMoviePreBean(MovieListBean bean) {
        moviePreBean = bean;
    }
}

