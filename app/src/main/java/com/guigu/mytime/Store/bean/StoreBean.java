package com.guigu.mytime.Store.bean;

import java.util.List;

/**
 * Store ： 商城的Json数据实体类
 * Created by weisong on 2016/1/11.
 */
public class StoreBean {

    private AdvHeadImgEntity advHeadImg;
    /**
     * goodsId : 0
     * img : http://img31.mtime.cn/mg/2016/01/11/151725.69834678.jpg
     * longTime : 0
     * startTime : 0
     * subTitle :
     * title :
     * titleColor :
     * url : #!/commerce/item/101832/
     * warmup : 0
     */

    private CellAEntity cellA;
    /**
     * goodsId : 0
     * img : http://img31.mtime.cn/mg/2016/01/11/151733.85310767.jpg
     * longTime : 0
     * startTime : 0
     * subTitle :
     * title :
     * titleColor :
     * url : http://feature.mtime.cn/mobile/item/2015/preironman/
     * warmup : 0
     */

    private CellBEntity cellB;
    private CellCEntity cellC;
    /**
     * isNewAdd : false
     * msg :
     */

    private GoodsCouponEntity goodsCoupon;
    /**
     * iconTitle1 : 家居
     * iconTitle2 :
     * img1 : http://img31.mtime.cn/mg/2015/05/14/172023.40406228.jpg
     * img2 :
     * url : #!/commerce/list/?c1=43
     */

    private NavigatorFirthIconEntity navigatorFirthIcon;
    /**
     * colorValue : #FFB90F
     * goodsId : 0
     * image : http://img31.mtime.cn/mg/2016/01/08/184128.58497929.jpg
     * imageUrl : http://mall.wv.mtime.cn/#!/commerce/list/?q=weta
     * moreUrl : http://mall.wv.mtime.cn/#!/commerce/list/?c1=25
     * name : 玩具模型
     * subList : [{"goodsId":102078,"image":"http://img31.mtime.cn/mg/2016/01/08/184442.51949290.jpg","title":"反派领袖电能面具","url":""},{"goodsId":102005,"image":"http://img31.mtime.cn/mg/2016/01/08/184500.52186896.jpg","title":"星战豪华光剑","url":""},{"goodsId":102122,"image":"http://img31.mtime.cn/mg/2015/12/27/151816.90759731.jpg","title":"EB叶问蜡像级人偶 ","url":""}]
     */

    private List<CategoryEntity> category;
    /**
     * iconTitle : 玩具
     * image : http://img31.mtime.cn/mg/2015/05/14/171948.98975374.jpg
     * url : #!/commerce/list/?c1=25
     */

    private List<NavigatorIconEntity> navigatorIcon;
    /**
     * image : http://img31.mtime.cn/mg/2016/01/08/143051.45591452.jpg
     * url : http://feature.mtime.cn/mobile/item/2015/1231_star_war/
     */

    private List<ScrollImgEntity> scrollImg;
    /**
     * backgroupImage : http://img31.mtime.cn/mg/2015/12/24/120101.12081200.jpg
     * checkedImage : http://img31.mtime.cn/mg/2015/12/21/154423.70957595.jpg
     * goodsId : 0
     * subList : [{"goodsId":102055,"image":"http://img31.mtime.cn/goods/2015/12/16/222807.99729125_600X600X1.jpg","title":":CHOCOOLATE连帽卫衣","url":""},{"goodsId":102048,"image":"http://img31.mtime.cn/goods/2015/12/09/105101.76612397_600X600X1.jpg","title":"5CM星战白兵卫衣","url":""},{"goodsId":102050,"image":"http://img31.mtime.cn/goods/2015/12/09/105907.94496623_600X600X1.jpg","title":"5CM 星球大战白兵卫衣","url":""},{"goodsId":102045,"image":"http://img31.mtime.cn/goods/2015/12/09/104606.12257890_600X600X1.jpg","title":"5CM星战logo卫衣","url":""},{"goodsId":102055,"image":"http://img31.mtime.cn/goods/2015/12/16/222807.99729125_600X600X1.jpg","title":":CHOCOOLATE连帽卫衣","url":""},{"goodsId":102056,"image":"http://img31.mtime.cn/goods/2015/12/16/223713.87038473_600X600X1.jpg","title":":CHOCOOLATE星战连衣裙","url":""}]
     * titleCn : i.t
     * titleEn : Star Wars
     * uncheckImage : http://img31.mtime.cn/mg/2015/12/21/154425.21082475.jpg
     * url : http://mall.wv.mtime.cn/#!/commerce/list/?q=星球大战&bid=84
     */

    private List<TopicEntity> topic;

    public void setAdvHeadImg(AdvHeadImgEntity advHeadImg) {
        this.advHeadImg = advHeadImg;
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

    public void setGoodsCoupon(GoodsCouponEntity goodsCoupon) {
        this.goodsCoupon = goodsCoupon;
    }

    public void setNavigatorFirthIcon(NavigatorFirthIconEntity navigatorFirthIcon) {
        this.navigatorFirthIcon = navigatorFirthIcon;
    }

    public void setCategory(List<CategoryEntity> category) {
        this.category = category;
    }

    public void setNavigatorIcon(List<NavigatorIconEntity> navigatorIcon) {
        this.navigatorIcon = navigatorIcon;
    }

    public void setScrollImg(List<ScrollImgEntity> scrollImg) {
        this.scrollImg = scrollImg;
    }

    public void setTopic(List<TopicEntity> topic) {
        this.topic = topic;
    }

    public AdvHeadImgEntity getAdvHeadImg() {
        return advHeadImg;
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

    public GoodsCouponEntity getGoodsCoupon() {
        return goodsCoupon;
    }

    public NavigatorFirthIconEntity getNavigatorFirthIcon() {
        return navigatorFirthIcon;
    }

    public List<CategoryEntity> getCategory() {
        return category;
    }

    public List<NavigatorIconEntity> getNavigatorIcon() {
        return navigatorIcon;
    }

    public List<ScrollImgEntity> getScrollImg() {
        return scrollImg;
    }

    public List<TopicEntity> getTopic() {
        return topic;
    }

    public static class AdvHeadImgEntity {
    }

    public static class CellAEntity {
        private int goodsId;
        private String img;
        private int longTime;
        private int startTime;
        private String subTitle;
        private String title;
        private String titleColor;
        private String url;
        private int warmup;

        public void setGoodsId(int goodsId) {
            this.goodsId = goodsId;
        }

        public void setImg(String img) {
            this.img = img;
        }

        public void setLongTime(int longTime) {
            this.longTime = longTime;
        }

        public void setStartTime(int startTime) {
            this.startTime = startTime;
        }

        public void setSubTitle(String subTitle) {
            this.subTitle = subTitle;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public void setTitleColor(String titleColor) {
            this.titleColor = titleColor;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public void setWarmup(int warmup) {
            this.warmup = warmup;
        }

        public int getGoodsId() {
            return goodsId;
        }

        public String getImg() {
            return img;
        }

        public int getLongTime() {
            return longTime;
        }

        public int getStartTime() {
            return startTime;
        }

        public String getSubTitle() {
            return subTitle;
        }

        public String getTitle() {
            return title;
        }

        public String getTitleColor() {
            return titleColor;
        }

        public String getUrl() {
            return url;
        }

        public int getWarmup() {
            return warmup;
        }
    }

    public static class CellBEntity {
        private int goodsId;
        private String img;
        private int longTime;
        private int startTime;
        private String subTitle;
        private String title;
        private String titleColor;
        private String url;
        private int warmup;

        public void setGoodsId(int goodsId) {
            this.goodsId = goodsId;
        }

        public void setImg(String img) {
            this.img = img;
        }

        public void setLongTime(int longTime) {
            this.longTime = longTime;
        }

        public void setStartTime(int startTime) {
            this.startTime = startTime;
        }

        public void setSubTitle(String subTitle) {
            this.subTitle = subTitle;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public void setTitleColor(String titleColor) {
            this.titleColor = titleColor;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public void setWarmup(int warmup) {
            this.warmup = warmup;
        }

        public int getGoodsId() {
            return goodsId;
        }

        public String getImg() {
            return img;
        }

        public int getLongTime() {
            return longTime;
        }

        public int getStartTime() {
            return startTime;
        }

        public String getSubTitle() {
            return subTitle;
        }

        public String getTitle() {
            return title;
        }

        public String getTitleColor() {
            return titleColor;
        }

        public String getUrl() {
            return url;
        }

        public int getWarmup() {
            return warmup;
        }
    }

    public static class CellCEntity {
        /**
         * image : http://img31.mtime.cn/mg/2016/01/11/151743.18418841.jpg
         * subTitle :
         * title :
         * titleColor :
         * url : http://mall.wv.mtime.cn/#!/commerce/list/?q=孩之宝&tid=26&bid=14
         */

        private List<ListEntity> list;

        public void setList(List<ListEntity> list) {
            this.list = list;
        }

        public List<ListEntity> getList() {
            return list;
        }

        public static class ListEntity {
            private String image;
            private String subTitle;
            private String title;
            private String titleColor;
            private String url;

            public void setImage(String image) {
                this.image = image;
            }

            public void setSubTitle(String subTitle) {
                this.subTitle = subTitle;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public void setTitleColor(String titleColor) {
                this.titleColor = titleColor;
            }

            public void setUrl(String url) {
                this.url = url;
            }

            public String getImage() {
                return image;
            }

            public String getSubTitle() {
                return subTitle;
            }

            public String getTitle() {
                return title;
            }

            public String getTitleColor() {
                return titleColor;
            }

            public String getUrl() {
                return url;
            }
        }
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

    public static class CategoryEntity {
        private String colorValue;
        private int goodsId;
        private String image;
        private String imageUrl;
        private String moreUrl;
        private String name;
        /**
         * goodsId : 102078
         * image : http://img31.mtime.cn/mg/2016/01/08/184442.51949290.jpg
         * title : 反派领袖电能面具
         * url :
         */

        private List<SubListEntity> subList;

        public void setColorValue(String colorValue) {
            this.colorValue = colorValue;
        }

        public void setGoodsId(int goodsId) {
            this.goodsId = goodsId;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public void setImageUrl(String imageUrl) {
            this.imageUrl = imageUrl;
        }

        public void setMoreUrl(String moreUrl) {
            this.moreUrl = moreUrl;
        }

        public void setName(String name) {
            this.name = name;
        }

        public void setSubList(List<SubListEntity> subList) {
            this.subList = subList;
        }

        public String getColorValue() {
            return colorValue;
        }

        public int getGoodsId() {
            return goodsId;
        }

        public String getImage() {
            return image;
        }

        public String getImageUrl() {
            return imageUrl;
        }

        public String getMoreUrl() {
            return moreUrl;
        }

        public String getName() {
            return name;
        }

        public List<SubListEntity> getSubList() {
            return subList;
        }

        public static class SubListEntity {
            private int goodsId;
            private String image;
            private String title;
            private String url;

            public void setGoodsId(int goodsId) {
                this.goodsId = goodsId;
            }

            public void setImage(String image) {
                this.image = image;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public void setUrl(String url) {
                this.url = url;
            }

            public int getGoodsId() {
                return goodsId;
            }

            public String getImage() {
                return image;
            }

            public String getTitle() {
                return title;
            }

            public String getUrl() {
                return url;
            }
        }
    }

    public static class NavigatorIconEntity {
        private String iconTitle;
        private String image;
        private String url;

        public void setIconTitle(String iconTitle) {
            this.iconTitle = iconTitle;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getIconTitle() {
            return iconTitle;
        }

        public String getImage() {
            return image;
        }

        public String getUrl() {
            return url;
        }
    }

    public static class ScrollImgEntity {
        private String image;
        private String url;

        public void setImage(String image) {
            this.image = image;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getImage() {
            return image;
        }

        public String getUrl() {
            return url;
        }
    }

    public static class TopicEntity {
        private String backgroupImage;
        private String checkedImage;
        private int goodsId;
        private String titleCn;
        private String titleEn;
        private String uncheckImage;
        private String url;
        /**
         * goodsId : 102055
         * image : http://img31.mtime.cn/goods/2015/12/16/222807.99729125_600X600X1.jpg
         * title : :CHOCOOLATE连帽卫衣
         * url :
         */

        private List<SubListEntity> subList;

        public void setBackgroupImage(String backgroupImage) {
            this.backgroupImage = backgroupImage;
        }

        public void setCheckedImage(String checkedImage) {
            this.checkedImage = checkedImage;
        }

        public void setGoodsId(int goodsId) {
            this.goodsId = goodsId;
        }

        public void setTitleCn(String titleCn) {
            this.titleCn = titleCn;
        }

        public void setTitleEn(String titleEn) {
            this.titleEn = titleEn;
        }

        public void setUncheckImage(String uncheckImage) {
            this.uncheckImage = uncheckImage;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public void setSubList(List<SubListEntity> subList) {
            this.subList = subList;
        }

        public String getBackgroupImage() {
            return backgroupImage;
        }

        public String getCheckedImage() {
            return checkedImage;
        }

        public int getGoodsId() {
            return goodsId;
        }

        public String getTitleCn() {
            return titleCn;
        }

        public String getTitleEn() {
            return titleEn;
        }

        public String getUncheckImage() {
            return uncheckImage;
        }

        public String getUrl() {
            return url;
        }

        public List<SubListEntity> getSubList() {
            return subList;
        }

        public static class SubListEntity {
            private int goodsId;
            private String image;
            private String title;
            private String url;

            public void setGoodsId(int goodsId) {
                this.goodsId = goodsId;
            }

            public void setImage(String image) {
                this.image = image;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public void setUrl(String url) {
                this.url = url;
            }

            public int getGoodsId() {
                return goodsId;
            }

            public String getImage() {
                return image;
            }

            public String getTitle() {
                return title;
            }

            public String getUrl() {
                return url;
            }
        }
    }
}
