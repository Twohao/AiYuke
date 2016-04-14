package com.riyue.aiyuke.bean;

import java.io.Serializable;
import java.util.List;

/**
 * @author mtf
 * @date 2016/4/4
 */
public class NewsTabInfo  implements Serializable {

    /**
     * type : succ
     * msg : {"list":[{"id":1,"name":"头条","layout":"newsList","apiUrl":"AppData/Info/News"},{"id":2,"name":"视频","layout":"videoList","apiUrl":"AppData/Video/List"},{"id":3,"name":"赛事","layout":"eventList","apiUrl":"AppData/Event/Lists"},{"id":4,"name":"教学","layout":"techList","apiUrl":"AppData/Tech/List"},{"id":5,"name":"球星","layout":"starList","apiUrl":"AppData/SportPlayer/hotList"},{"id":6,"name":"装备","layout":"newsList","apiUrl":"AppData/Equip/List"},{"id":7,"name":"保健","layout":"newsList","apiUrl":"AppData/Health/List"}],"version":"10151202112"}
     * url : none
     * version : 3
     */

    private String type;
    /**
     * list : [{"id":1,"name":"头条","layout":"newsList","apiUrl":"AppData/Info/News"},{"id":2,"name":"视频","layout":"videoList","apiUrl":"AppData/Video/List"},{"id":3,"name":"赛事","layout":"eventList","apiUrl":"AppData/Event/Lists"},{"id":4,"name":"教学","layout":"techList","apiUrl":"AppData/Tech/List"},{"id":5,"name":"球星","layout":"starList","apiUrl":"AppData/SportPlayer/hotList"},{"id":6,"name":"装备","layout":"newsList","apiUrl":"AppData/Equip/List"},{"id":7,"name":"保健","layout":"newsList","apiUrl":"AppData/Health/List"}]
     * version : 10151202112
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

    public static class MsgEntity  implements Serializable{
        private String version;
        /**
         * id : 1
         * name : 头条
         * layout : newsList
         * apiUrl : AppData/Info/News
         */

        private List<ListEntity> list;

        public void setVersion(String version) {
            this.version = version;
        }

        public void setList(List<ListEntity> list) {
            this.list = list;
        }

        public String getVersion() {
            return version;
        }

        public List<ListEntity> getList() {
            return list;
        }

        public static class ListEntity  implements Serializable{
            private int id;
            private String name;
            private String layout;
            private String apiUrl;

            public void setId(int id) {
                this.id = id;
            }

            public void setName(String name) {
                this.name = name;
            }

            public void setLayout(String layout) {
                this.layout = layout;
            }

            public void setApiUrl(String apiUrl) {
                this.apiUrl = apiUrl;
            }

            public int getId() {
                return id;
            }

            public String getName() {
                return name;
            }

            public String getLayout() {
                return layout;
            }

            public String getApiUrl() {
                return apiUrl;
            }
        }
    }
}
