package com.riyue.aiyuke.bean;

import java.io.Serializable;
import java.util.List;

/**
 * @author mtf
 * @date 2016/4/4
 */
public class ClubDateInfo  implements Serializable {

    /**
     * type : succ
     * msg : [{"show_time":"04月04日","submit":"20160404","cn":"今天","flag":1},{"show_time":"04月05日","submit":"20160405","cn":"明天","flag":0},{"show_time":"04月06日","submit":"20160406","cn":"后天","flag":0},{"show_time":"04月07日","submit":"20160407","cn":"两天后","flag":0}]
     * url : none
     * version : 3
     */

    private String type;
    private String url;
    private int version;
    /**
     * show_time : 04月04日
     * submit : 20160404
     * cn : 今天
     * flag : 1
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

    public static class MsgEntity  implements Serializable{
        private String show_time;
        private String submit;
        private String cn;
        private int flag;

        public void setShow_time(String show_time) {
            this.show_time = show_time;
        }

        public void setSubmit(String submit) {
            this.submit = submit;
        }

        public void setCn(String cn) {
            this.cn = cn;
        }

        public void setFlag(int flag) {
            this.flag = flag;
        }

        public String getShow_time() {
            return show_time;
        }

        public String getSubmit() {
            return submit;
        }

        public String getCn() {
            return cn;
        }

        public int getFlag() {
            return flag;
        }
    }
}
