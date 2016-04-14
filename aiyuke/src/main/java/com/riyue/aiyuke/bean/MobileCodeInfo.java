package com.riyue.aiyuke.bean;

import java.io.Serializable;

/**
 * @author mtf
 * @date 2016/4/6
 */
public class MobileCodeInfo implements Serializable{

    /**
     * type : err
     * msg : 手机号码 格式有误，必须是 中国大陆手机号码
     * url : none
     * version : 3
     */

    private String type;
    private String msg;
    private String url;
    private int version;

    public void setType(String type) {
        this.type = type;
    }

    public void setMsg(String msg) {
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

    public String getMsg() {
        return msg;
    }

    public String getUrl() {
        return url;
    }

    public int getVersion() {
        return version;
    }
}
