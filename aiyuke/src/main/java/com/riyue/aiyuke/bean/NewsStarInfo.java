package com.riyue.aiyuke.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2016/4/6.
 * ceshi
 */
public class NewsStarInfo implements Serializable{

    /**
     * type : succ
     * msg : {"list":[{"id":"10001","appurl":"http://cache.app.aiyuke.com/sport_player/10001-.html","name":"李宗伟","image":"http://img2.aiyuke.com/upload/2013/07/11/1222131862.png"},{"id":"10002","appurl":"http://cache.app.aiyuke.com/sport_player/10002-.html","name":"林丹","image":"http://img2.aiyuke.com/upload/2015/12/24/1155354930.jpg"},{"id":"10522","appurl":"http://cache.app.aiyuke.com/sport_player/10522-.html","name":"薛松","image":"http://img2.aiyuke.com/upload/2013/05/29/2245231647.jpg"},{"id":"10018","appurl":"http://cache.app.aiyuke.com/sport_player/10018-.html","name":"约根森","image":"http://img2.aiyuke.com/upload/2013/03/18/1144088580.jpg"},{"id":"10213","appurl":"http://cache.app.aiyuke.com/sport_player/10213-.html","name":"安赛龙","image":"http://img2.aiyuke.com/upload/2015/11/25/0925326525.jpg"},{"id":"10004","appurl":"http://cache.app.aiyuke.com/sport_player/10004-.html","name":"蔡赟","image":"http://img2.aiyuke.com/upload/2013/07/14/1920289140.png"},{"id":"10050","appurl":"http://cache.app.aiyuke.com/sport_player/10050-.html","name":"李龙大","image":"http://img2.aiyuke.com/upload/2013/07/14/1934063944.png"},{"id":"10323","appurl":"http://cache.app.aiyuke.com/sport_player/10323-.html","name":"马琳","image":"http://img2.aiyuke.com/upload/2015/12/24/1722046749.jpg"},{"id":"10021","appurl":"http://cache.app.aiyuke.com/sport_player/10021-.html","name":"李雪芮","image":"http://img2.aiyuke.com/upload/2013/03/25/1622132445.jpg"},{"id":"10156","appurl":"http://cache.app.aiyuke.com/sport_player/10156-.html","name":"奥原希望","image":"http://img2.aiyuke.com/upload/2015/12/24/1108045571.jpg"},{"id":"10925","appurl":"http://cache.app.aiyuke.com/sport_player/10925-.html","name":"唐渊渟","image":"http://img2.aiyuke.com/upload/2014/04/07/2045298871.png"},{"id":"10651","appurl":"http://cache.app.aiyuke.com/sport_player/10651-.html","name":"栗原文音","image":"http://img2.aiyuke.com/upload/2015/05/20/1109365989.jpg"}]}
     * url : none
     * version : 3
     */

    private String type;
    private MsgEntity msg;
    private String url;
    private int version;

    public void setType(String type) {
        this.type = type;
    }

    public void setMsg(MsgEntity msg) {
        this.msg = msg;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public String getType() {
        return type;
    }

    public MsgEntity getMsg() {
        return msg;
    }

    public String getUrl() {
        return url;
    }

    public int getVersion() {
        return version;
    }

    public static class MsgEntity implements Serializable{
        /**
         * id : 10001
         * appurl : http://cache.app.aiyuke.com/sport_player/10001-.html
         * name : 李宗伟
         * image : http://img2.aiyuke.com/upload/2013/07/11/1222131862.png
         */

        private List<ListEntity> list;

        public void setList(List<ListEntity> list) {
            this.list = list;
        }

        public List<ListEntity> getList() {
            return list;
        }

        public static class ListEntity implements Serializable{
            private String id;
            private String appurl;
            private String name;
            private String image;

            public void setId(String id) {
                this.id = id;
            }

            public void setAppurl(String appurl) {
                this.appurl = appurl;
            }

            public void setName(String name) {
                this.name = name;
            }

            public void setImage(String image) {
                this.image = image;
            }

            public String getId() {
                return id;
            }

            public String getAppurl() {
                return appurl;
            }

            public String getName() {
                return name;
            }

            public String getImage() {
                return image;
            }
        }
    }
}
