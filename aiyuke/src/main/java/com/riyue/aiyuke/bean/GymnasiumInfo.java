package com.riyue.aiyuke.bean;

import java.io.Serializable;
import java.util.List;

/**
 * @author mtf
 * @date 2016/4/8
 */
public class GymnasiumInfo implements Serializable{

    /**
     * type : succ
     * msg : [{"id":"11607","lng":"114.426532","lat":"30.471749","title":"航海羽毛球馆","address":"洪山区光谷大道108号"},{"id":"11570","lng":"114.423095","lat":"30.48165","title":"博格羽毛球馆","address":"洪山区关南二路1号"},{"id":"11223","lng":"114.393976","lat":"30.478098","title":"财大艺体中心球场","address":"洪山区南湖大道182号财大艺体中心球场"},{"id":"11571","lng":"114.400627","lat":"30.489869","title":"中南民族大学体育馆","address":"洪山区好学路"},{"id":"11762","lng":"114.411812","lat":"30.503262","title":"阳光在线球馆","address":"洪山区光谷商圈雄楚大道1000号阳光在线小区内"},{"id":"11221","lng":"114.390218","lat":"30.504231","title":"欣盛球馆","address":"洪山区楚平路"},{"id":"11218","lng":"114.367421","lat":"30.47921","title":"华农体育馆","address":"洪山区南湖大道"},{"id":"11222","lng":"114.34467","lat":"30.491099","title":"澳新球馆","address":"洪山区珞狮路511号"},{"id":"12456","lng":"114.344706","lat":"30.491114","title":"奥新学院羽毛球馆","address":"洪山区珞狮路322号"},{"id":"11220","lng":"114.352652","lat":"30.510757","title":"羽健球馆","address":"洪山区珞狮路286号"}]
     * url : none
     * version : 3
     */

    private String type;
    private String url;
    private int version;
    /**
     * id : 11607
     * lng : 114.426532
     * lat : 30.471749
     * title : 航海羽毛球馆
     * address : 洪山区光谷大道108号
     */

    private List<MsgEntity> msg;

    public void setType(String type) {
        this.type = type;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public void setMsg(List<MsgEntity> msg) {
        this.msg = msg;
    }

    public String getType() {
        return type;
    }

    public String getUrl() {
        return url;
    }

    public int getVersion() {
        return version;
    }

    public List<MsgEntity> getMsg() {
        return msg;
    }

    public static class MsgEntity {
        private String id;
        private String lng;
        private String lat;
        private String title;
        private String address;

        public void setId(String id) {
            this.id = id;
        }

        public void setLng(String lng) {
            this.lng = lng;
        }

        public void setLat(String lat) {
            this.lat = lat;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getId() {
            return id;
        }

        public String getLng() {
            return lng;
        }

        public String getLat() {
            return lat;
        }

        public String getTitle() {
            return title;
        }

        public String getAddress() {
            return address;
        }
    }
}
