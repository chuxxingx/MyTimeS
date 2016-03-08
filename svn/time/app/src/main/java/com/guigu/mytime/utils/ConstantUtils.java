package com.guigu.mytime.utils;

/**
 * Created by Administrator on 2016/2/29.
 */
public class ConstantUtils {
    public static final String[] tese={"全部","IMAX厅","中国巨幕","4K放映厅","4D厅","杜比全景声厅","情侣座","停车场","wifi"};
    public static final String[] shagnquan={"全部","管庄","望京","酒仙桥","三里屯","双井","安贞","大望路","亚运村"
            ,"朝外大街","三元桥","北苑家园","国贸","左家庄","十里堡","劲松","五元桥","西单","西直门"};
    public static final String discoverItem_news_baseUrl ="http://api.m.mtime.cn/News/NewsList.api?pageIndex=";
    public static final String discoverAll_head_baseUrl = "http://api.m.mtime.cn/PageSubArea/GetRecommendationIndexInfo.api";
    public static final String discoverItem_prevue_baseUrl = "http://api.m.mtime.cn/PageSubArea/TrailerList.api";
    public static final String discoverItem_paihan_baseUrl = "http://api.m.mtime.cn/TopList/TopListOfAll.api?pageIndex=";
    public static final String discoverItem_discuss_baseUrl = "http://api.m.mtime.cn/MobileMovie/Review.api?needTop=false";

    public static final String HOME_UP_URL = "http://api.m.mtime.cn/PageSubArea/HotPlayMovies.api?locationId=290";
    public static final String HOME_DOWN_URL = "http://api.m.mtime.cn/PageSubArea/GetFirstPageAdvAndNews.api";

    public static final String BASE_URL = "http://api.m.mtime.cn";
    /////////////////////////////定位界面的url////////////////////////
    public static final String CITY_LOCATION_URL = "http://api.m.mtime.cn/Showtime/HotCitiesByCinema.api";
    /////////////////////////////定位界面的url////////////////////////
    /////////////////////////以下为首页的url///////////////////////////////////
    /**
     * 首页第二部分热播电影
     */
    public static final String FIRSTPAGE_HOTPLAYMOVIES_URL = BASE_URL + "/PageSubArea/HotPlayMovies.api?locationId=290";
    /**
     * 首页第一部分的顶部轮播图及广告
     */
    public static final String FIRSTPAGE_ADVANDNEWS_URL = BASE_URL + "/PageSubArea/GetFirstPageAdvAndNews.api";

    public static final String MOVIE_LIST_NEWS_URL = BASE_URL + "/PageSubArea/GetHomeFeed.api?pageIndex=1";
    public static final String MOVIE_LIST_NEWS_URL_PAGE = "http://api.m.mtime.cn/PageSubArea/GetHomeFeed.api?pageIndex=";


    /////////////////////////以上为首页的url///////////////////////////////////

    /////////////////////////以下为购票的url///////////////////////////////////
    /**
     * 正在热映的list数据
     * 当地的id,例如北京
     * ?locationId=290
     */
    public static final String MOVIE_LIST = "http://api.m.mtime.cn/Showtime/LocationMovies.api";
    /**
     * 电影周边的数据
     * http://api.m.mtime.cn/Search/RelatedGoodsById.api?relatedId=206216&relatedObjType=1
     * <p/>
     * ?relatedId=206216&relatedObjType=1
     */
    public static final String MOVIE_RIM = "http://api.m.mtime.cn/Search/RelatedGoodsById.api";

    /**
     * 电影评论，修改id，获得对应的请求数据
     * ?movieId=192895&pageIndex=1 ,追加请求信息
     */
    public static final String MOVIE_DISCUSS = "http://api.m.mtime.cn/Showtime/MovieComments.api";

    /**
     * 关联电影
     * ?movieId=192895&pageIndex=1
     */
    public static final String MOVIE_TO_MOVIE = "http://api.m.mtime.cn/Movie/Video.api";

    public static final String CINEMA_URL = "http://api.m.mtime.cn/OnlineLocationCinema/OnlineCinemasByCity.api?locationId=290&deviceToken={1}";
    /**
     * 应该知道的事
     * ?MovieId=192895
     */
    public static final String MOVIE_SOMETHING = "http://api.m.mtime.cn/Movie/extendMovieDetail.api";

    /**
     * 相关新闻
     * ?movieId=216495&pageIndex=1
     */
    public static final String MOVIE_NEWS = "http://api.m.mtime.cn/Movie/News.api";
    /////////////////////////以上为购票的url///////////////////////////////////
    /////////////////////////以下为商城的url///////////////////////////////////

    /**
     * 商城的页面的base_url
     */
    public static final String STORE_URL = "http://api.m.mtime.cn/PageSubArea/MarketFirstPageNew.api?lastTime={0}";
    public static final String STORE_GOODS_URL = "http://api.m.mtime.cn/ECommerce/RecommendProducts.api?pageIndex=";

    /////////////////////////以上为商城的url///////////////////////////////////
    /**购票电影：正在热映的item点击进入后
     * 演职人员
     * ?movieId=192895
     */
    public  static  final String PERSONINMOVIE = "http://api.m.mtime.cn/Movie/MovieCreditsWithTypes.api";
    /**
     * 电影预告片
     */
    public static final String MOVIE_MOVIE = "http://api.m.mtime.cn/Movie/Video.api";
}