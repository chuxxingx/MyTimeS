package com.guigu.mytime.buyticket.bean;

import java.util.List;

/**
 * Created by Administrator on 2016/3/1.
 */
public class CinemaBean {

    /**
     * cinemaId : 2486
     * cinameName : 耀莱国际五棵松店
     * address : 北京市海淀区复兴路69号卓展购物中心5层东侧
     * districtID : 1368
     * isETicket : false
     * isTicket : true
     * ratingFinal : 7.546121
     * longitude : 116.2797
     * latitude : 39.91318
     * baiduLongitude : 116.2856
     * baiduLatitude : 39.91967
     * movieCount : 17
     * showtimeCount : 117
     * minPrice : 4300
     * feature : {"has3D":1,"hasIMAX":0,"hasVIP":1,"hasPark":1,"hasServiceTicket":1,"hasWifi":1,"hasLoveseat":0,"hasFeature4K":0,"hasFeatureDolby":0,"hasFeatureHuge":0,"hasFeature4D":0}
     * couponActivityList : []
     */

    private int cinemaId;
    private String cinameName;
    private String address;
    private int districtID;
    private boolean isETicket;
    private boolean isTicket;
    private double ratingFinal;
    private double longitude;
    private double latitude;
    private double baiduLongitude;
    private double baiduLatitude;
    private int movieCount;
    private int showtimeCount;
    private int minPrice;
    /**
     * has3D : 1
     * hasIMAX : 0
     * hasVIP : 1
     * hasPark : 1
     * hasServiceTicket : 1
     * hasWifi : 1
     * hasLoveseat : 0
     * hasFeature4K : 0
     * hasFeatureDolby : 0
     * hasFeatureHuge : 0
     * hasFeature4D : 0
     */

    private FeatureEntity feature;
    private List<?> couponActivityList;

    public void setCinemaId(int cinemaId) {
        this.cinemaId = cinemaId;
    }

    public void setCinameName(String cinameName) {
        this.cinameName = cinameName;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setDistrictID(int districtID) {
        this.districtID = districtID;
    }

    public void setIsETicket(boolean isETicket) {
        this.isETicket = isETicket;
    }

    public void setIsTicket(boolean isTicket) {
        this.isTicket = isTicket;
    }

    public void setRatingFinal(double ratingFinal) {
        this.ratingFinal = ratingFinal;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public void setBaiduLongitude(double baiduLongitude) {
        this.baiduLongitude = baiduLongitude;
    }

    public void setBaiduLatitude(double baiduLatitude) {
        this.baiduLatitude = baiduLatitude;
    }

    public void setMovieCount(int movieCount) {
        this.movieCount = movieCount;
    }

    public void setShowtimeCount(int showtimeCount) {
        this.showtimeCount = showtimeCount;
    }

    public void setMinPrice(int minPrice) {
        this.minPrice = minPrice;
    }

    public void setFeature(FeatureEntity feature) {
        this.feature = feature;
    }

    public void setCouponActivityList(List<?> couponActivityList) {
        this.couponActivityList = couponActivityList;
    }

    public int getCinemaId() {
        return cinemaId;
    }

    public String getCinameName() {
        return cinameName;
    }

    public String getAddress() {
        return address;
    }

    public int getDistrictID() {
        return districtID;
    }

    public boolean isIsETicket() {
        return isETicket;
    }

    public boolean isIsTicket() {
        return isTicket;
    }

    public double getRatingFinal() {
        return ratingFinal;
    }

    public double getLongitude() {
        return longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getBaiduLongitude() {
        return baiduLongitude;
    }

    public double getBaiduLatitude() {
        return baiduLatitude;
    }

    public int getMovieCount() {
        return movieCount;
    }

    public int getShowtimeCount() {
        return showtimeCount;
    }

    public int getMinPrice() {
        return minPrice;
    }

    public FeatureEntity getFeature() {
        return feature;
    }

    public List<?> getCouponActivityList() {
        return couponActivityList;
    }

    public static class FeatureEntity {
        private int has3D;
        private int hasIMAX;
        private int hasVIP;
        private int hasPark;
        private int hasServiceTicket;
        private int hasWifi;
        private int hasLoveseat;
        private int hasFeature4K;
        private int hasFeatureDolby;
        private int hasFeatureHuge;
        private int hasFeature4D;

        public void setHas3D(int has3D) {
            this.has3D = has3D;
        }

        public void setHasIMAX(int hasIMAX) {
            this.hasIMAX = hasIMAX;
        }

        public void setHasVIP(int hasVIP) {
            this.hasVIP = hasVIP;
        }

        public void setHasPark(int hasPark) {
            this.hasPark = hasPark;
        }

        public void setHasServiceTicket(int hasServiceTicket) {
            this.hasServiceTicket = hasServiceTicket;
        }

        public void setHasWifi(int hasWifi) {
            this.hasWifi = hasWifi;
        }

        public void setHasLoveseat(int hasLoveseat) {
            this.hasLoveseat = hasLoveseat;
        }

        public void setHasFeature4K(int hasFeature4K) {
            this.hasFeature4K = hasFeature4K;
        }

        public void setHasFeatureDolby(int hasFeatureDolby) {
            this.hasFeatureDolby = hasFeatureDolby;
        }

        public void setHasFeatureHuge(int hasFeatureHuge) {
            this.hasFeatureHuge = hasFeatureHuge;
        }

        public void setHasFeature4D(int hasFeature4D) {
            this.hasFeature4D = hasFeature4D;
        }

        public int getHas3D() {
            return has3D;
        }

        public int getHasIMAX() {
            return hasIMAX;
        }

        public int getHasVIP() {
            return hasVIP;
        }

        public int getHasPark() {
            return hasPark;
        }

        public int getHasServiceTicket() {
            return hasServiceTicket;
        }

        public int getHasWifi() {
            return hasWifi;
        }

        public int getHasLoveseat() {
            return hasLoveseat;
        }

        public int getHasFeature4K() {
            return hasFeature4K;
        }

        public int getHasFeatureDolby() {
            return hasFeatureDolby;
        }

        public int getHasFeatureHuge() {
            return hasFeatureHuge;
        }

        public int getHasFeature4D() {
            return hasFeature4D;
        }
    }
}
