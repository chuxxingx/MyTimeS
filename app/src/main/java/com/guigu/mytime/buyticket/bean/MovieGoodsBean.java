package com.guigu.mytime.buyticket.bean;

import java.util.List;

/**
 * Created by Administrator on 2016/3/4.
 */
public class MovieGoodsBean {

    /**
     * relatedId : 206216
     * relatedType : 1
     * goodsCount : 3
     * relatedUrl : http://mall.wv.mtime.cn/?mid=206216&relatedNativeType=1&utm_source=app_movie#!/commerce/list/
     * goodsList : [{"goodsId":101603,"iconText":"新品","background":"#28C8DC","name":"星战原力觉醒i6炫酷手机壳","longName":"吸收储存外部光能 黑暗中发出炫酷荧光","image":"http://img31.mtime.cn/mg/2015/11/06/171401.17276912.jpg","marketPrice":7500,"minSalePrice":4500,"goodsUrl":"http://mall.wv.mtime.cn/?utm_source=app_movie#!/commerce/item/101603/"},{"goodsId":101654,"iconText":"新品","background":"#28C8DC","name":"星战BB-8智能球型机器人","longName":"真实动作 培养个性 自主特性 全息投影 app端操控","image":"http://img31.mtime.cn/mg/2015/11/04/105257.40818907.jpg","marketPrice":179700,"minSalePrice":119800,"goodsUrl":"http://mall.wv.mtime.cn/?utm_source=app_movie#!/commerce/item/101654/"},{"goodsId":102049,"iconText":"新品","background":"#28C8DC","name":"5CM 星战光剑黑色卫衣","longName":"简约设计 经典元素 细致工艺 舒适亲肤","image":"http://img31.mtime.cn/mg/2015/12/09/105729.52352938.jpg","marketPrice":52900,"minSalePrice":31900,"goodsUrl":"http://mall.wv.mtime.cn/?utm_source=app_movie#!/commerce/item/102049/"}]
     * goodsIds : 101603,101654,102049,
     */

    private int relatedId;
    private int relatedType;
    private int goodsCount;
    private String relatedUrl;
    private String goodsIds;
    /**
     * goodsId : 101603
     * iconText : 新品
     * background : #28C8DC
     * name : 星战原力觉醒i6炫酷手机壳
     * longName : 吸收储存外部光能 黑暗中发出炫酷荧光
     * image : http://img31.mtime.cn/mg/2015/11/06/171401.17276912.jpg
     * marketPrice : 7500
     * minSalePrice : 4500
     * goodsUrl : http://mall.wv.mtime.cn/?utm_source=app_movie#!/commerce/item/101603/
     */

    private List<GoodsListEntity> goodsList;

    public void setRelatedId(int relatedId) {
        this.relatedId = relatedId;
    }

    public void setRelatedType(int relatedType) {
        this.relatedType = relatedType;
    }

    public void setGoodsCount(int goodsCount) {
        this.goodsCount = goodsCount;
    }

    public void setRelatedUrl(String relatedUrl) {
        this.relatedUrl = relatedUrl;
    }

    public void setGoodsIds(String goodsIds) {
        this.goodsIds = goodsIds;
    }

    public void setGoodsList(List<GoodsListEntity> goodsList) {
        this.goodsList = goodsList;
    }

    public int getRelatedId() {
        return relatedId;
    }

    public int getRelatedType() {
        return relatedType;
    }

    public int getGoodsCount() {
        return goodsCount;
    }

    public String getRelatedUrl() {
        return relatedUrl;
    }

    public String getGoodsIds() {
        return goodsIds;
    }

    public List<GoodsListEntity> getGoodsList() {
        return goodsList;
    }

    public static class GoodsListEntity {
        private int goodsId;
        private String iconText;
        private String background;
        private String name;
        private String longName;
        private String image;
        private int marketPrice;
        private int minSalePrice;
        private String goodsUrl;

        public void setGoodsId(int goodsId) {
            this.goodsId = goodsId;
        }

        public void setIconText(String iconText) {
            this.iconText = iconText;
        }

        public void setBackground(String background) {
            this.background = background;
        }

        public void setName(String name) {
            this.name = name;
        }

        public void setLongName(String longName) {
            this.longName = longName;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public void setMarketPrice(int marketPrice) {
            this.marketPrice = marketPrice;
        }

        public void setMinSalePrice(int minSalePrice) {
            this.minSalePrice = minSalePrice;
        }

        public void setGoodsUrl(String goodsUrl) {
            this.goodsUrl = goodsUrl;
        }

        public int getGoodsId() {
            return goodsId;
        }

        public String getIconText() {
            return iconText;
        }

        public String getBackground() {
            return background;
        }

        public String getName() {
            return name;
        }

        public String getLongName() {
            return longName;
        }

        public String getImage() {
            return image;
        }

        public int getMarketPrice() {
            return marketPrice;
        }

        public int getMinSalePrice() {
            return minSalePrice;
        }

        public String getGoodsUrl() {
            return goodsUrl;
        }
    }
}

