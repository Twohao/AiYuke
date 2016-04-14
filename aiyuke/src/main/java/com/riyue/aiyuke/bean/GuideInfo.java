package com.riyue.aiyuke.bean;

import java.io.Serializable;

/**
 * @author mtf
 * @date 2016/4/2
 */
public class GuideInfo implements Serializable{

    /**
     * type : succ
     * msg : {"link":"http://app.aiyuke.com/news/2016/02/4084.html?1455605840.html","title":"大家一起找BUG-爱羽客","version":"1.0048","thefile":"http://app.aiyuke.com/upload/2016/02/16/18100376785.png?1459588852&apptag=@aykAPP_othersite"}
     * url : none
     * version : 3
     */

    private String type;
    /**
     * link : http://app.aiyuke.com/news/2016/02/4084.html?1455605840.html
     * title : 大家一起找BUG-爱羽客
     * version : 1.0048
     * thefile : http://app.aiyuke.com/upload/2016/02/16/18100376785.png?1459588852&apptag=@aykAPP_othersite
     */

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
        private String link;
        private String title;
        private String version;
        private String thefile;

        public void setLink(String link) {
            this.link = link;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public void setVersion(String version) {
            this.version = version;
        }

        public void setThefile(String thefile) {
            this.thefile = thefile;
        }

        public String getLink() {
            return link;
        }

        public String getTitle() {
            return title;
        }

        public String getVersion() {
            return version;
        }

        public String getThefile() {
            return thefile;
        }
    }
}
