package com.guigu.mytime.locat.bean;

import java.util.List;

/**
 * Created by Steve on 2016/1/14.
 */
public class LoactionCityBean {

    /**
     * id : 292
     * n : 上海
     * count : 183
     * pinyinShort : sh
     * pinyinFull : Shanghai
     */

    private List<City> p;

    public void setP(List<City> p) {
        this.p = p;
    }

    public List<City> getP() {
        return p;
    }

    public static class City {
        private int id;
        private String n;
        private int count;
        private String pinyinShort;
        private String pinyinFull;

        public void setId(int id) {
            this.id = id;
        }

        public void setN(String n) {
            this.n = n;
        }

        public void setCount(int count) {
            this.count = count;
        }

        public void setPinyinShort(String pinyinShort) {
            this.pinyinShort = pinyinShort;
        }

        public void setPinyinFull(String pinyinFull) {
            this.pinyinFull = pinyinFull;
        }

        public int getId() {
            return id;
        }

        public String getN() {
            return n;
        }

        public int getCount() {
            return count;
        }

        public String getPinyinShort() {
            return pinyinShort;
        }

        public String getPinyinFull() {
            return pinyinFull;
        }

        @Override
        public String toString() {
            return "City{" +
                    "id=" + id +
                    ", n='" + n + '\'' +
                    ", count=" + count +
                    ", pinyinShort='" + pinyinShort + '\'' +
                    ", pinyinFull='" + pinyinFull + '\'' +
                    '}';
        }
    }

}
