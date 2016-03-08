package com.guigu.mytime.Store.bean;

import java.util.List;

/**
 * Created by Administrator on 2016/3/1.
 * 商品的Bean
 */
public class StoreGoodsBean {
    /**
     * iconTitle1 : 家居
     * iconTitle2 :
     * img1 : http://img31.mtime.cn/mg/2015/05/14/172023.40406228.jpg
     * img2 :
     * url : #!/commerce/list/?c1=43
     */

    private NavigatorFirthIconEntity navigatorFirthIcon;
    /**
     * url : http://mall.wv.mtime.cn/#!/commerce/item/102173/
     * img : http://img31.mtime.cn/mg/2016/02/29/120103.26540700.jpg
     * goodsId : 0
     * startTime : 0
     * longTime : 0
     * warmup : 0
     * title :
     * subTitle :
     * titleColor :
     */

    private CellAEntity cellA;
    /**
     * url : http://feature.mtime.cn/mobile/item/2015/preironman/
     * img : http://img31.mtime.cn/mg/2016/02/19/111535.54404855.jpg
     * goodsId : 0
     * startTime : 0
     * longTime : 0
     * warmup : 0
     * title :
     * subTitle :
     * titleColor :
     */

    private CellBEntity cellB;
    private CellCEntity cellC;
    private AdvHeadImgEntity advHeadImg;
    /**
     * isNewAdd : false
     * msg :
     */

    private GoodsCouponEntity goodsCoupon;
    /**
     * url : http://feature.mtime.com/mobile/item/2016/j_comics/
     * image : http://img31.mtime.cn/mg/2016/02/26/160923.63499830.jpg
     */

    private List<ScrollImgEntity> scrollImg;
    /**
     * iconTitle : 玩具
     * url : #!/commerce/list/?c1=25
     * image : http://img31.mtime.cn/mg/2015/05/14/171948.98975374.jpg
     */

    private List<NavigatorIconEntity> navigatorIcon;
    /**
     * titleCn : 功夫熊猫
     * titleEn : Kung Fu Panda
     * url : http://mall.wv.mtime.cn/#!/commerce/list/?q=功夫熊猫
     * goodsId : 0
     * checkedImage : http://img31.mtime.cn/mg/2016/01/29/163425.87972160.jpg
     * uncheckImage : http://img31.mtime.cn/mg/2016/01/29/163427.96666490.jpg
     * backgroupImage : http://img31.mtime.cn/mg/2016/01/29/163552.48354218.jpg
     * subList : [{"title":"欢乐双宝i6手机壳","goodsId":101784,"image":"http://img31.mtime.cn/goods/2015/12/09/220039.16414544_600X600X1.jpg","url":""},{"title":"阿宝节节高自拍杆","goodsId":101837,"image":"http://img31.mtime.cn/goods/2015/12/09/140707.70901578_600X600X1.jpg","url":""},{"title":"功夫熊猫亲肤靠垫","goodsId":101744,"image":"http://img31.mtime.cn/goods/2015/12/09/220556.70801777_600X600X1.jpg","url":""},{"title":"阿宝练功多功能雕像","goodsId":101838,"image":"http://img31.mtime.cn/goods/2016/01/07/181644.21735698_600X600X1.jpg","url":""},{"title":"翻滚阿宝陶瓷杯","goodsId":101835,"image":"http://img31.mtime.cn/goods/2015/12/24/105839.33267889_600X600X1.jpg","url":""},{"title":"电影版轻便吸管杯","goodsId":101788,"image":"http://img31.mtime.cn/goods/2015/12/09/184412.27018606_600X600X1.jpg","url":""}]
     */

    private List<TopicEntity> topic;
    /**
     * goodsId : 0
     * moreUrl : http://mall.wv.mtime.cn/#!/commerce/list/?c1=25
     * imageUrl : http://mall.wv.mtime.cn/#!/commerce/list/?q=cosbaby
     * image : http://img31.mtime.cn/mg/2016/02/25/180949.51210304.jpg
     * name : 玩具模型
     * colorValue : #FFB90F
     * subList : [{"title":"GSC巡音流歌泳装","goodsId":102397,"image":"http://img31.mtime.cn/mg/2016/02/25/181019.73506921.jpg","url":""},{"title":"万万没想到pop公仔","goodsId":102036,"image":"http://img31.mtime.cn/mg/2016/02/25/181146.14040202.jpg","url":""},{"title":"魔兽1:1国王莱恩之剑","goodsId":102086,"image":"http://img31.mtime.cn/mg/2016/02/25/181216.73990722.jpg","url":""}]
     */

    private List<CategoryEntity> category;
    private List<GoodsListEntity> goodsList;
    private int pageCount;

    public void setNavigatorFirthIcon(NavigatorFirthIconEntity navigatorFirthIcon) {
        this.navigatorFirthIcon = navigatorFirthIcon;
    }

    public void setCellA(CellAEntity cellA) {
        this.cellA = cellA;
    }

    public void setCellB(CellBEntity cellB) {
        this.cellB = cellB;
    }

    public void setCellC(CellCEntity cellC) {
        this.cellC = cellC;
    }

    public void setAdvHeadImg(AdvHeadImgEntity advHeadImg) {
        this.advHeadImg = advHeadImg;
    }

    public void setGoodsCoupon(GoodsCouponEntity goodsCoupon) {
        this.goodsCoupon = goodsCoupon;
    }

    public void setScrollImg(List<ScrollImgEntity> scrollImg) {
        this.scrollImg = scrollImg;
    }

    public void setNavigatorIcon(List<NavigatorIconEntity> navigatorIcon) {
        this.navigatorIcon = navigatorIcon;
    }

    public void setTopic(List<TopicEntity> topic) {
        this.topic = topic;
    }

    public void setCategory(List<CategoryEntity> category) {
        this.category = category;
    }

    public NavigatorFirthIconEntity getNavigatorFirthIcon() {
        return navigatorFirthIcon;
    }

    public CellAEntity getCellA() {
        return cellA;
    }

    public CellBEntity getCellB() {
        return cellB;
    }

    public CellCEntity getCellC() {
        return cellC;
    }

    public AdvHeadImgEntity getAdvHeadImg() {
        return advHeadImg;
    }

    public GoodsCouponEntity getGoodsCoupon() {
        return goodsCoupon;
    }

    public List<ScrollImgEntity> getScrollImg() {
        return scrollImg;
    }

    public List<NavigatorIconEntity> getNavigatorIcon() {
        return navigatorIcon;
    }

    public List<TopicEntity> getTopic() {
        return topic;
    }

    public List<CategoryEntity> getCategory() {
        return category;
    }

    public List<GoodsListEntity> getGoodsList() {
        return goodsList;
    }

    public int getPageCount() {
        return pageCount;
    }

    public static class NavigatorFirthIconEntity {
        private String iconTitle1;
        private String iconTitle2;
        private String img1;
        private String img2;
        private String url;

        public void setIconTitle1(String iconTitle1) {
            this.iconTitle1 = iconTitle1;
        }

        public void setIconTitle2(String iconTitle2) {
            this.iconTitle2 = iconTitle2;
        }

        public void setImg1(String img1) {
            this.img1 = img1;
        }

        public void setImg2(String img2) {
            this.img2 = img2;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getIconTitle1() {
            return iconTitle1;
        }

        public String getIconTitle2() {
            return iconTitle2;
        }

        public String getImg1() {
            return img1;
        }

        public String getImg2() {
            return img2;
        }

        public String getUrl() {
            return url;
        }
    }

    public static class CellAEntity {
        private String url;
        private String img;
        private int goodsId;
        private int startTime;
        private int longTime;
        private int warmup;
        private String title;
        private String subTitle;
        private String titleColor;

        public void setUrl(String url) {
            this.url = url;
        }

        public void setImg(String img) {
            this.img = img;
        }

        public void setGoodsId(int goodsId) {
            this.goodsId = goodsId;
        }

        public void setStartTime(int startTime) {
            this.startTime = startTime;
        }

        public void setLongTime(int longTime) {
            this.longTime = longTime;
        }

        public void setWarmup(int warmup) {
            this.warmup = warmup;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public void setSubTitle(String subTitle) {
            this.subTitle = subTitle;
        }

        public void setTitleColor(String titleColor) {
            this.titleColor = titleColor;
        }

        public String getUrl() {
            return url;
        }

        public String getImg() {
            return img;
        }

        public int getGoodsId() {
            return goodsId;
        }

        public int getStartTime() {
            return startTime;
        }

        public int getLongTime() {
            return longTime;
        }

        public int getWarmup() {
            return warmup;
        }

        public String getTitle() {
            return title;
        }

        public String getSubTitle() {
            return subTitle;
        }

        public String getTitleColor() {
            return titleColor;
        }
    }

    public static class CellBEntity {
        private String url;
        private String img;
        private int goodsId;
        private int startTime;
        private int longTime;
        private int warmup;
        private String title;
        private String subTitle;
        private String titleColor;

        public void setUrl(String url) {
            this.url = url;
        }

        public void setImg(String img) {
            this.img = img;
        }

        public void setGoodsId(int goodsId) {
            this.goodsId = goodsId;
        }

        public void setStartTime(int startTime) {
            this.startTime = startTime;
        }

        public void setLongTime(int longTime) {
            this.longTime = longTime;
        }

        public void setWarmup(int warmup) {
            this.warmup = warmup;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public void setSubTitle(String subTitle) {
            this.subTitle = subTitle;
        }

        public void setTitleColor(String titleColor) {
            this.titleColor = titleColor;
        }

        public String getUrl() {
            return url;
        }

        public String getImg() {
            return img;
        }

        public int getGoodsId() {
            return goodsId;
        }

        public int getStartTime() {
            return startTime;
        }

        public int getLongTime() {
            return longTime;
        }

        public int getWarmup() {
            return warmup;
        }

        public String getTitle() {
            return title;
        }

        public String getSubTitle() {
            return subTitle;
        }

        public String getTitleColor() {
            return titleColor;
        }
    }

    public static class CellCEntity {
        /**
         * url : http://mall.wv.mtime.cn/#!/commerce/item/100944/
         * image : http://img31.mtime.cn/mg/2016/02/29/120126.44675465.jpg
         * title :
         * subTitle :
         * titleColor :
         */

        private List<ListEntity> list;

        public void setList(List<ListEntity> list) {
            this.list = list;
        }

        public List<ListEntity> getList() {
            return list;
        }

        public static class ListEntity {
        }
    }

    public static class AdvHeadImgEntity {
    }

    public static class GoodsCouponEntity {
        private boolean isNewAdd;
        private String msg;

        public void setIsNewAdd(boolean isNewAdd) {
            this.isNewAdd = isNewAdd;
        }

        public void setMsg(String msg) {
            this.msg = msg;
        }

        public boolean isIsNewAdd() {
            return isNewAdd;
        }

        public String getMsg() {
            return msg;
        }
    }

    public static class ScrollImgEntity {
        private String url;
        private String image;

        public void setUrl(String url) {
            this.url = url;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public String getUrl() {
            return url;
        }

        public String getImage() {
            return image;
        }
    }

    public static class NavigatorIconEntity {
        private String iconTitle;
        private String url;
        private String image;

        public void setIconTitle(String iconTitle) {
            this.iconTitle = iconTitle;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public String getIconTitle() {
            return iconTitle;
        }

        public String getUrl() {
            return url;
        }

        public String getImage() {
            return image;
        }
    }

    public static class TopicEntity {
        private String titleCn;
        private String titleEn;
        private String url;
        private int goodsId;
        private String checkedImage;
        private String uncheckImage;
        private String backgroupImage;
        /**
         * title : 欢乐双宝i6手机壳
         * goodsId : 101784
         * image : http://img31.mtime.cn/goods/2015/12/09/220039.16414544_600X600X1.jpg
         * url :
         */

        private List<SubListEntity> subList;

        public void setTitleCn(String titleCn) {
            this.titleCn = titleCn;
        }

        public void setTitleEn(String titleEn) {
            this.titleEn = titleEn;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public void setGoodsId(int goodsId) {
            this.goodsId = goodsId;
        }

        public void setCheckedImage(String checkedImage) {
            this.checkedImage = checkedImage;
        }

        public void setUncheckImage(String uncheckImage) {
            this.uncheckImage = uncheckImage;
        }

        public void setBackgroupImage(String backgroupImage) {
            this.backgroupImage = backgroupImage;
        }

        public void setSubList(List<SubListEntity> subList) {
            this.subList = subList;
        }

        public String getTitleCn() {
            return titleCn;
        }

        public String getTitleEn() {
            return titleEn;
        }

        public String getUrl() {
            return url;
        }

        public int getGoodsId() {
            return goodsId;
        }

        public String getCheckedImage() {
            return checkedImage;
        }

        public String getUncheckImage() {
            return uncheckImage;
        }

        public String getBackgroupImage() {
            return backgroupImage;
        }

        public List<SubListEntity> getSubList() {
            return subList;
        }

        public static class SubListEntity {
            private String title;
            private int goodsId;
            private String image;
            private String url;

            public void setTitle(String title) {
                this.title = title;
            }

            public void setGoodsId(int goodsId) {
                this.goodsId = goodsId;
            }

            public void setImage(String image) {
                this.image = image;
            }

            public void setUrl(String url) {
                this.url = url;
            }

            public String getTitle() {
                return title;
            }

            public int getGoodsId() {
                return goodsId;
            }

            public String getImage() {
                return image;
            }

            public String getUrl() {
                return url;
            }
        }
    }

    public static class CategoryEntity {
        private int goodsId;
        private String moreUrl;
        private String imageUrl;
        private String image;
        private String name;
        private String colorValue;
        /**
         * title : GSC巡音流歌泳装
         * goodsId : 102397
         * image : http://img31.mtime.cn/mg/2016/02/25/181019.73506921.jpg
         * url :
         */

        private List<SubListEntity> subList;

        public void setGoodsId(int goodsId) {
            this.goodsId = goodsId;
        }

        public void setMoreUrl(String moreUrl) {
            this.moreUrl = moreUrl;
        }

        public void setImageUrl(String imageUrl) {
            this.imageUrl = imageUrl;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public void setName(String name) {
            this.name = name;
        }

        public void setColorValue(String colorValue) {
            this.colorValue = colorValue;
        }

        public void setSubList(List<SubListEntity> subList) {
            this.subList = subList;
        }

        public int getGoodsId() {
            return goodsId;
        }

        public String getMoreUrl() {
            return moreUrl;
        }

        public String getImageUrl() {
            return imageUrl;
        }

        public String getImage() {
            return image;
        }

        public String getName() {
            return name;
        }

        public String getColorValue() {
            return colorValue;
        }

        public List<SubListEntity> getSubList() {
            return subList;
        }


        public static class SubListEntity {
            private String title;
            private int goodsId;
            private String image;
            private String url;

            public void setTitle(String title) {
                this.title = title;
            }

            public void setGoodsId(int goodsId) {
                this.goodsId = goodsId;
            }

            public void setImage(String image) {
                this.image = image;
            }

            public void setUrl(String url) {
                this.url = url;
            }

            public String getTitle() {
                return title;
            }

            public int getGoodsId() {
                return goodsId;
            }

            public String getImage() {
                return image;
            }

            public String getUrl() {
                return url;
            }
        }
    }

    public class GoodsListEntity {
        private int goodsId;
        private String iconText;
        private String background;
        private String name;
        private String longName;
        private String image;
        private int marketPrice;
        private int minSalePrice;
        private String url;

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

        public void setUrl(String url) {
            this.url = url;
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

        public String getUrl() {
            return url;
        }

    }
}
