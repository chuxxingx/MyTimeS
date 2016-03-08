package com.guigu.mytime.discover.bean;

/**
 * Created by chu on 2016/3/3.
 */
public class CinecismBean {


    /**
     * id : 7915250
     * nickname : 方聿南
     * userImage : http://img22.mtime.cn/up/2010/07/08/092035.65057346_128X128.jpg
     * rating : 0.0
     * title : 别惹蚂蚁
     * summary : 　　别让这故事的煞有其事给骗了，跨国大企业，前沿科研领域，天才科学家，军方武器合同什么的，看上去是另一部《钢铁侠》，不到五分钟就漏了陷。原来还是个古老的、高度政治正确的、人民群众喜闻乐见的“街头痞子带着损友逆袭位高权重的大恶人顺便拯救家人修复家庭关系”的好莱坞传统英雄故事。从价值观来看，在爱刷节操下限的漫威电影宇宙里，是最不离经叛道的那一类。 　　 　　虽然埃德加•赖特因理念不合退出了导演工作，但...
     * relatedObj : {"type":1,"id":57903,"title":"蚁人","rating":"7.6","image":"http://img31.mtime.cn/mt/2015/09/15/163509.99636592_1280X720X2.jpg"}
     */

    private int id;
    private String nickname;
    private String userImage;
    private String rating;
    private String title;
    private String summary;
    /**
     * type : 1
     * id : 57903
     * title : 蚁人
     * rating : 7.6
     * image : http://img31.mtime.cn/mt/2015/09/15/163509.99636592_1280X720X2.jpg
     */

    private RelatedObjEntity relatedObj;

    public void setId(int id) {
        this.id = id;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public void setUserImage(String userImage) {
        this.userImage = userImage;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public void setRelatedObj(RelatedObjEntity relatedObj) {
        this.relatedObj = relatedObj;
    }

    public int getId() {
        return id;
    }

    public String getNickname() {
        return nickname;
    }

    public String getUserImage() {
        return userImage;
    }

    public String getRating() {
        return rating;
    }

    public String getTitle() {
        return title;
    }

    public String getSummary() {
        return summary;
    }

    public RelatedObjEntity getRelatedObj() {
        return relatedObj;
    }

    public static class RelatedObjEntity {
        private int type;
        private int id;
        private String title;
        private String rating;
        private String image;

        public void setType(int type) {
            this.type = type;
        }

        public void setId(int id) {
            this.id = id;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public void setRating(String rating) {
            this.rating = rating;
        }

        public void setImage(String image) {
            this.image = image;
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

        public String getRating() {
            return rating;
        }

        public String getImage() {
            return image;
        }
    }
}
