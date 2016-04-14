package com.riyue.aiyuke.bean;

import java.io.Serializable;

/**
 * @author mtf
 * @date 2016/4/9
 */
public class LoginInfo implements Serializable{

    /**
     * type : succ
     * msg : {"username":"Wonderful","mobile":"18672391226","email":"","uid":"276692","birthday":"0000-00-00","face":"0","province":"","city":"","sex":"0","qq":"","regdate":"1459927723","regip":"113.57.170.146","lastlogintime":"1460166865","lastloginip":"113.57.170.146","account_state":"1","money":null,"freezemoney":null,"nothasqq":null,"pay_password":null,"realname":null,"personid":null,"hasgesturepwd":"0","gourl":"http://www.tiyushe.com/","token":"1c982a3a9041487b11ad4c97050d3654a0aa871a5e55f4bd4bc3","avatar":"http://passport.tiyushe.com/avatar/000/27/66/92_avatar_middle.jpg?_avatar_fixnotfound"}
     * url : ref
     * version : 3
     */

    private String type;
    /**
     * username : Wonderful
     * mobile : 18672391226
     * email :
     * uid : 276692
     * birthday : 0000-00-00
     * face : 0
     * province :
     * city :
     * sex : 0
     * qq :
     * regdate : 1459927723
     * regip : 113.57.170.146
     * lastlogintime : 1460166865
     * lastloginip : 113.57.170.146
     * account_state : 1
     * money : null
     * freezemoney : null
     * nothasqq : null
     * pay_password : null
     * realname : null
     * personid : null
     * hasgesturepwd : 0
     * gourl : http://www.tiyushe.com/
     * token : 1c982a3a9041487b11ad4c97050d3654a0aa871a5e55f4bd4bc3
     * avatar : http://passport.tiyushe.com/avatar/000/27/66/92_avatar_middle.jpg?_avatar_fixnotfound
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

    public static class MsgEntity implements Serializable {
        private String username;
        private String mobile;
        private String email;
        private String uid;
        private String birthday;
        private String face;
        private String province;
        private String city;
        private String sex;
        private String qq;
        private String regdate;
        private String regip;
        private String lastlogintime;
        private String lastloginip;
        private String account_state;
        private Object money;
        private Object freezemoney;
        private Object nothasqq;
        private Object pay_password;
        private Object realname;
        private Object personid;
        private String hasgesturepwd;
        private String gourl;
        private String token;
        private String avatar;

        public void setUsername(String username) {
            this.username = username;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public void setUid(String uid) {
            this.uid = uid;
        }

        public void setBirthday(String birthday) {
            this.birthday = birthday;
        }

        public void setFace(String face) {
            this.face = face;
        }

        public void setProvince(String province) {
            this.province = province;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public void setSex(String sex) {
            this.sex = sex;
        }

        public void setQq(String qq) {
            this.qq = qq;
        }

        public void setRegdate(String regdate) {
            this.regdate = regdate;
        }

        public void setRegip(String regip) {
            this.regip = regip;
        }

        public void setLastlogintime(String lastlogintime) {
            this.lastlogintime = lastlogintime;
        }

        public void setLastloginip(String lastloginip) {
            this.lastloginip = lastloginip;
        }

        public void setAccount_state(String account_state) {
            this.account_state = account_state;
        }

        public void setMoney(Object money) {
            this.money = money;
        }

        public void setFreezemoney(Object freezemoney) {
            this.freezemoney = freezemoney;
        }

        public void setNothasqq(Object nothasqq) {
            this.nothasqq = nothasqq;
        }

        public void setPay_password(Object pay_password) {
            this.pay_password = pay_password;
        }

        public void setRealname(Object realname) {
            this.realname = realname;
        }

        public void setPersonid(Object personid) {
            this.personid = personid;
        }

        public void setHasgesturepwd(String hasgesturepwd) {
            this.hasgesturepwd = hasgesturepwd;
        }

        public void setGourl(String gourl) {
            this.gourl = gourl;
        }

        public void setToken(String token) {
            this.token = token;
        }

        public void setAvatar(String avatar) {
            this.avatar = avatar;
        }

        public String getUsername() {
            return username;
        }

        public String getMobile() {
            return mobile;
        }

        public String getEmail() {
            return email;
        }

        public String getUid() {
            return uid;
        }

        public String getBirthday() {
            return birthday;
        }

        public String getFace() {
            return face;
        }

        public String getProvince() {
            return province;
        }

        public String getCity() {
            return city;
        }

        public String getSex() {
            return sex;
        }

        public String getQq() {
            return qq;
        }

        public String getRegdate() {
            return regdate;
        }

        public String getRegip() {
            return regip;
        }

        public String getLastlogintime() {
            return lastlogintime;
        }

        public String getLastloginip() {
            return lastloginip;
        }

        public String getAccount_state() {
            return account_state;
        }

        public Object getMoney() {
            return money;
        }

        public Object getFreezemoney() {
            return freezemoney;
        }

        public Object getNothasqq() {
            return nothasqq;
        }

        public Object getPay_password() {
            return pay_password;
        }

        public Object getRealname() {
            return realname;
        }

        public Object getPersonid() {
            return personid;
        }

        public String getHasgesturepwd() {
            return hasgesturepwd;
        }

        public String getGourl() {
            return gourl;
        }

        public String getToken() {
            return token;
        }

        public String getAvatar() {
            return avatar;
        }
    }
}
