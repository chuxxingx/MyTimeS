package com.guigu.mytime.discover.bean;

import java.util.List;

/**
 * Created by chu on 2016/3/5.
 */
public class NewsItemBean {

    /**
     type: 0,
     id: 1550016,
     title: "2015波士顿在线影评人奖公布",
     title2: ""疯狂的麦克斯4"斩获最佳影片等五项大奖",
     content: "<div><b><div><div><div><div><img src="http://img31.mtime.cn/CMS/News/2015/12/06/144531.97841501_620X620.jpg" width="310"><em></em></div></div><p>自颁奖季开始，《疯狂的麦克斯：狂暴之路》的表现堪称所向披靡</p></div></div>　　时光网讯</b> 12月4日，2015年度的波士顿在线影评人奖名单公布。颁奖季一开始就所向披靡的《疯狂的麦克斯：狂暴之路》此次同样不负众望，一举拿下包括最佳影片、最佳导演在内的五项大奖，并在年度十佳影片中名列第一。</div><div><br></div><div>　　正在北美热映的拳击题材电影《奎迪》叫好又叫座，主演迈克尔·B·乔丹与《洛奇》系列的核心人物西尔维斯特·史泰龙的两个表演奖也是分量十足。最佳女演员则由《布鲁克林》主演西尔莎·罗南获得，克里斯汀·斯图尔特则凭借《锡尔斯玛利亚》中的突破性演出收获最佳女配。</div><div><br></div><div>　　托马斯·麦卡锡执导，迈克尔·基顿、马克·鲁法洛、瑞秋·麦克亚当斯主演的《聚焦》获颁最佳群戏，该片同样也获得了最佳剧本。最佳外语电影则颁给了匈牙利电影《索尔之子》；讲述已故歌手艾米·怀恩豪斯生平的《艾米》与皮克斯的《头脑大作战》分别将最佳纪录片与最佳动画收入囊中。最佳摄影、最佳剪辑、最佳原创音乐三大技术奖项则全都颁给了《疯狂的麦克斯4》。</div><div><br></div><div><b>2015波斯顿在线影评人奖获奖名单</b></div><div><br></div><div>最佳影片：《疯狂的麦克斯：狂暴之路》</div><div>最佳导演：乔治·米勒-《疯狂的麦克斯：狂暴之路》</div><div>最佳男演员：迈克尔·B·乔丹-《奎迪》</div><div>最佳女演员：西尔莎·罗南-《布鲁克林》</div><div>最佳男配角：西尔维斯特·史泰龙-《奎迪》</div><div>最佳女配角：克里斯汀·斯图尔特-《锡尔斯玛利亚》</div><div>最佳群戏：《聚焦》</div><div>最佳剧本：托马斯·麦卡锡、Josh Singer-《聚焦》</div><div>最佳外语电影：《索尔之子》</div><div>最佳纪录片：《艾米》</div><div>最佳动画片：《头脑大作战》</div><div>最佳摄影：《疯狂的麦克斯：狂暴之路》</div><div>最佳剪辑：《疯狂的麦克斯：狂暴之路》</div><div>最佳原创音乐：《疯狂的麦克斯：狂暴之路》</div><div><br></div><div><b>2015年度十佳影片</b></div><div><b><br></b></div><div>1、《疯狂的麦克斯：狂暴之路》</div><div>2、《奎迪》</div><div>3、《布鲁克林》</div><div>4、《卡萝尔》</div><div>5、《聚焦》</div><div>6、《锡尔斯玛利亚》</div><div>7、《间谍之桥》</div><div>8、《火星救援》</div><div>9、《失常》</div><div>10、《橘色》</div>",
     time: "2015-12-6 14:50:44",
     source: "Mtime时光网",
     author: "",
     editor: "渐渐不见",
     commentCount: 55,
     url: "http://news.mtime.com/2015/12/06/1550016.html",
     wapUrl: "http://news.wap.mtime.com/2015/12/06/1550016.html",
     previousNewsID: 0,
     nextNewsID: 1553121
     */

    private int type;
    private int id;
    private String title;
    private String title2;
    private String content;
    private String time;
    private String source;
    private String author;
    private String editor;
    private int commentCount;
    private String url;
    private String wapUrl;
    private int previousNewsID;
    private int nextNewsID;
    private List<RelationsEntity> relations;
    /**
     type: 1,
     id: 24138,
     name: "疯狂的麦克斯：狂暴之路",
     image: "http://img31.mtime.cn/mt/2015/05/10/103443.83432757_1280X720X2.jpg",
     year: "2015",
     rating: 8.1,
     scoreCount: 4420,
     releaseDate: "2015年5月15日",
     relaseLocation: "美国"
     }
     */



    public void setType(int type) {
        this.type = type;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setTitle2(String title2) {
        this.title2 = title2;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setEditor(String editor) {
        this.editor = editor;
    }

    public void setCommentCount(int commentCount) {
        this.commentCount = commentCount;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setWapUrl(String wapUrl) {
        this.wapUrl = wapUrl;
    }

    public void setPreviousNewsID(int previousNewsID) {
        this.previousNewsID = previousNewsID;
    }

    public void setNextNewsID(int nextNewsID) {
        this.nextNewsID = nextNewsID;
    }

    public void setRelations(List<RelationsEntity> relations) {
        this.relations = relations;
    }

    public int getType() {
        return type;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getTitle2() {
        return title2;
    }

    public String getContent() {
        return content;
    }

    public String getTime() {
        return time;
    }

    public String getSource() {
        return source;
    }

    public String getAuthor() {
        return author;
    }

    public String getEditor() {
        return editor;
    }

    public int getCommentCount() {
        return commentCount;
    }

    public String getUrl() {
        return url;
    }

    public String getWapUrl() {
        return wapUrl;
    }

    public int getPreviousNewsID() {
        return previousNewsID;
    }

    public int getNextNewsID() {
        return nextNewsID;
    }

    public List<RelationsEntity> getRelations() {
        return relations;
    }

    public static class RelationsEntity {
        private int type;
        private int id;
        private String name;
        private String image;
        private String year;
        private double rating;
        private int scoreCount;
        private String releaseDate;
        private String relaseLocation;

        public void setType(int type) {
            this.type = type;
        }

        public void setId(int id) {
            this.id = id;
        }

        public void setName(String name) {
            this.name = name;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public void setYear(String year) {
            this.year = year;
        }

        public void setRating(double rating) {
            this.rating = rating;
        }

        public void setScoreCount(int scoreCount) {
            this.scoreCount = scoreCount;
        }

        public void setReleaseDate(String releaseDate) {
            this.releaseDate = releaseDate;
        }

        public void setRelaseLocation(String relaseLocation) {
            this.relaseLocation = relaseLocation;
        }

        public int getType() {
            return type;
        }

        public int getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public String getImage() {
            return image;
        }

        public String getYear() {
            return year;
        }

        public double getRating() {
            return rating;
        }

        public int getScoreCount() {
            return scoreCount;
        }

        public String getReleaseDate() {
            return releaseDate;
        }

        public String getRelaseLocation() {
            return relaseLocation;
        }
    }

}

