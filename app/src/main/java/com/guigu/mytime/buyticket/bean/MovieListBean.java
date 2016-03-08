package com.guigu.mytime.buyticket.bean;

import java.util.List;

/**
 * Created by Administrator on 2016/3/4.
 */
public class MovieListBean {

    /**
     * totalCount : 11
     * totalPageCount : 1
     * videoList : [{"hightUrl":"http://vfx.mtime.cn/Video/2016/03/01/mp4/160301190336712029.mp4","id":59172,"image":"http://img31.mtime.cn/mg/2016/03/01/190109.22747109_235X132X4.jpg","length":88,"title":"叶问3  终极预告","type":0,"url":"http://vfx.mtime.cn/Video/2016/03/01/mp4/160301190336712029_480.mp4"},{"hightUrl":"http://vfx.mtime.cn/Video/2015/10/02/mp4/151002112936187592.mp4","id":56713,"image":"http://img31.mtime.cn/mg/2015/10/02/113500.62102617_235X132X4.jpg","length":40,"title":"叶问3 先行版预告片","type":0,"url":"http://vfx.mtime.cn/Video/2015/10/02/mp4/151002112936187592.mp4"},{"hightUrl":"http://vfx.mtime.cn/Video/2015/11/12/mp4/151112164535188663.mp4","id":57442,"image":"http://img31.mtime.cn/mg/2015/11/12/164251.79510727_235X132X4.jpg","length":73,"title":"叶问3 美国版预告片","type":0,"url":"http://vfx.mtime.cn/Video/2015/11/12/mp4/151112164535188663.mp4"},{"hightUrl":"http://vfx.mtime.cn/Video/2016/02/23/mp4/160223104333934337.mp4","id":59030,"image":"http://img31.mtime.cn/mg/2016/02/23/104355.17039299_235X132X4.jpg","length":59,"title":"叶问3 先行版预告片2","type":0,"url":"http://vfx.mtime.cn/Video/2016/02/23/mp4/160223104333934337_480.mp4"},{"hightUrl":"http://vfx.mtime.cn/Video/2016/03/01/mp4/160301100311295476.mp4","id":59138,"image":"http://img31.mtime.cn/mg/2016/03/01/100154.69366803_235X132X4.jpg","length":118,"title":"叶问3 情深似海版预告片","type":0,"url":"http://vfx.mtime.cn/Video/2016/03/01/mp4/160301100311295476_480.mp4"},{"hightUrl":"http://vfx.mtime.cn/Video/2016/03/01/mp4/160301104019780671.mp4","id":59149,"image":"http://img31.mtime.cn/mg/2016/03/01/103347.99411353_235X132X4.jpg","length":63,"title":"叶问3 狭路相逢版预告片","type":0,"url":"http://vfx.mtime.cn/Video/2016/03/01/mp4/160301104019780671_480.mp4"},{"hightUrl":"http://vfx.mtime.cn/Video/2015/12/29/mp4/151229093744753606.mp4","id":58163,"image":"http://img31.mtime.cn/mg/2015/12/29/093653.45606963_235X132X4.jpg","length":115,"title":"叶问3 英文片段","type":1,"url":"http://vfx.mtime.cn/Video/2015/12/29/mp4/151229093744753606_480.mp4"},{"hightUrl":"http://vfx.mtime.cn/Video/2015/11/12/mp4/151112170142705829.mp4","id":57443,"image":"http://img31.mtime.cn/mg/2015/11/12/170026.42620905_235X132X4.jpg","length":168,"title":"叶问3 制作特辑之武","type":2,"url":"http://vfx.mtime.cn/Video/2015/11/12/mp4/151112170142705829.mp4"},{"hightUrl":"http://vfx.mtime.cn/Video/2016/03/03/mp4/160303180909742079.mp4","id":59211,"image":"http://img31.mtime.cn/mg/2016/03/03/180922.43231844_235X132X4.jpg","length":167,"title":"《叶问3》美国专访","type":3,"url":"http://vfx.mtime.cn/Video/2016/03/03/mp4/160303180909742079_480.mp4"},{"hightUrl":"http://vfx.mtime.cn/Video/2015/03/26/mp4/150326123406480990.mp4","id":53366,"image":"http://img31.mtime.cn/mg/2015/03/26/123453.20809865_235X132X4.jpg","length":156,"title":"叶问3 开机发布会","type":4,"url":"http://vfx.mtime.cn/Video/2015/03/26/mp4/150326123406480990_480.mp4"},{"hightUrl":"http://vfx.mtime.cn/Video/2016/03/01/mp4/160301225748894698.mp4","id":59177,"image":"http://img31.mtime.cn/mg/2016/03/01/225741.31212045_235X132X4.jpg","length":158,"title":"叶问3 北京首映式","type":4,"url":"http://vfx.mtime.cn/Video/2016/03/01/mp4/160301225748894698_480.mp4"}]
     */

    private int totalCount;
    private int totalPageCount;
    /**
     * hightUrl : http://vfx.mtime.cn/Video/2016/03/01/mp4/160301190336712029.mp4
     * id : 59172
     * image : http://img31.mtime.cn/mg/2016/03/01/190109.22747109_235X132X4.jpg
     * length : 88
     * title : 叶问3  终极预告
     * type : 0
     * url : http://vfx.mtime.cn/Video/2016/03/01/mp4/160301190336712029_480.mp4
     */

    private List<VideoListEntity> videoList;

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public void setTotalPageCount(int totalPageCount) {
        this.totalPageCount = totalPageCount;
    }

    public void setVideoList(List<VideoListEntity> videoList) {
        this.videoList = videoList;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public int getTotalPageCount() {
        return totalPageCount;
    }

    public List<VideoListEntity> getVideoList() {
        return videoList;
    }

    public static class VideoListEntity {
        private String hightUrl;
        private int id;
        private String image;
        private int length;
        private String title;
        private int type;
        private String url;

        public void setHightUrl(String hightUrl) {
            this.hightUrl = hightUrl;
        }

        public void setId(int id) {
            this.id = id;
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

        public void setType(int type) {
            this.type = type;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getHightUrl() {
            return hightUrl;
        }

        public int getId() {
            return id;
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

        public int getType() {
            return type;
        }

        public String getUrl() {
            return url;
        }
    }
}
