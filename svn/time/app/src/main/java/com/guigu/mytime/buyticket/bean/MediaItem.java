package com.guigu.mytime.buyticket.bean;

import java.io.Serializable;

/**
 * Created by Administrator on 2016/3/4.
 */
public class MediaItem implements Serializable{
    private String name;//标题
    private String duration;//时间
    private String data;//地址
    private String heightUrl;

    public MediaItem() {
    }

    public MediaItem(String name, String duration, String data, String heightUrl) {
        this.name = name;
        this.duration = duration;
        this.data = data;
        this.heightUrl = heightUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getHeightUrl() {
        return heightUrl;
    }

    public void setHeightUrl(String heightUrl) {
        this.heightUrl = heightUrl;
    }
}
