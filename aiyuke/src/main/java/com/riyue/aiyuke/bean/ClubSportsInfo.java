package com.riyue.aiyuke.bean;

import java.io.Serializable;
import java.util.List;

/**
 * @author mtf
 * @date 2016/4/4
 */
public class ClubSportsInfo implements Serializable{

    /**
     * type : succ
     * msg : {"auth":[],"authflag":0,"nearflag":0,"page":"1","nearby":[{"activity_id":86718,"title":"周一下午中科院打球！","desc":"下","arenaid":11266,"fromtime":"2016-04-04 17:00:00","show_day":"周一","show_fromtime":"17:00","arenaname":"中科院羽毛球馆","cid":1137,"clubname":"湖北武汉羽我同行羽毛球俱乐部","logo":"http://www.tiyushe.com/bzdata/globalapi/themes/admin/noimage.gif","distance":12.1,"subdomain":"whywtx","curmembers":5,"max_members":"16人","state":"报名中","statecode":1,"url":"http://m.tiyushe.com/act/","memberInfo":[{"uid":197288,"avatar":"http://passport.tiyushe.com/avatar/000/19/72/88_avatar_middle.jpg?_avatar_fixnotfound","waiting":"0"},{"uid":112990,"avatar":"http://passport.tiyushe.com/avatar/000/11/29/90_avatar_middle.jpg?_avatar_fixnotfound","waiting":"0"},{"uid":112995,"avatar":"http://passport.tiyushe.com/avatar/000/11/29/95_avatar_middle.jpg?_avatar_fixnotfound","waiting":"0"},{"uid":139989,"avatar":"http://passport.tiyushe.com/avatar/000/13/99/89_avatar_middle.jpg?_avatar_fixnotfound","waiting":"0"},{"uid":112994,"avatar":"http://passport.tiyushe.com/avatar/000/11/29/94_avatar_middle.jpg?_avatar_fixnotfound","waiting":"0"}]},{"activity_id":86084,"title":"羽毛球","desc":"娱乐","arenaid":11750,"fromtime":"2016-04-04 17:30:00","show_day":"周一","show_fromtime":"17:30","arenaname":"湖医球馆","cid":1676,"clubname":"湖医快乐羽毛球俱乐部","logo":"http://www.tiyushe.com/upload/2015/12/21/1027278308.jpg","distance":13.2,"subdomain":"hykl","curmembers":1,"max_members":"不限","state":"报名中","statecode":1,"url":"http://m.tiyushe.com/act/","memberInfo":[{"uid":174349,"avatar":"http://passport.tiyushe.com/avatar/000/17/43/49_avatar_middle.jpg?_avatar_fixnotfound","waiting":"0"}]}],"advertise":{"hasAd":1,"title":"","src":"http://www.tiyushe.com/upload/2016/02/02/16212074764.jpg","url":"http://www.tiyushe.com/index.php?c=ad&a=go&url=http%3A%2F%2Fwww.tiyushe.com%2Frt%2FSignClub%2F&fid=1002&cid=0&adid=0&apptag=@aykAPP_othersite","width":750,"height":130}}
     * url : none
     * version : 3
     */

    private String type;
    /**
     * auth : []
     * authflag : 0
     * nearflag : 0
     * page : 1
     * nearby : [{"activity_id":86718,"title":"周一下午中科院打球！","desc":"下","arenaid":11266,"fromtime":"2016-04-04 17:00:00","show_day":"周一","show_fromtime":"17:00","arenaname":"中科院羽毛球馆","cid":1137,"clubname":"湖北武汉羽我同行羽毛球俱乐部","logo":"http://www.tiyushe.com/bzdata/globalapi/themes/admin/noimage.gif","distance":12.1,"subdomain":"whywtx","curmembers":5,"max_members":"16人","state":"报名中","statecode":1,"url":"http://m.tiyushe.com/act/","memberInfo":[{"uid":197288,"avatar":"http://passport.tiyushe.com/avatar/000/19/72/88_avatar_middle.jpg?_avatar_fixnotfound","waiting":"0"},{"uid":112990,"avatar":"http://passport.tiyushe.com/avatar/000/11/29/90_avatar_middle.jpg?_avatar_fixnotfound","waiting":"0"},{"uid":112995,"avatar":"http://passport.tiyushe.com/avatar/000/11/29/95_avatar_middle.jpg?_avatar_fixnotfound","waiting":"0"},{"uid":139989,"avatar":"http://passport.tiyushe.com/avatar/000/13/99/89_avatar_middle.jpg?_avatar_fixnotfound","waiting":"0"},{"uid":112994,"avatar":"http://passport.tiyushe.com/avatar/000/11/29/94_avatar_middle.jpg?_avatar_fixnotfound","waiting":"0"}]},{"activity_id":86084,"title":"羽毛球","desc":"娱乐","arenaid":11750,"fromtime":"2016-04-04 17:30:00","show_day":"周一","show_fromtime":"17:30","arenaname":"湖医球馆","cid":1676,"clubname":"湖医快乐羽毛球俱乐部","logo":"http://www.tiyushe.com/upload/2015/12/21/1027278308.jpg","distance":13.2,"subdomain":"hykl","curmembers":1,"max_members":"不限","state":"报名中","statecode":1,"url":"http://m.tiyushe.com/act/","memberInfo":[{"uid":174349,"avatar":"http://passport.tiyushe.com/avatar/000/17/43/49_avatar_middle.jpg?_avatar_fixnotfound","waiting":"0"}]}]
     * advertise : {"hasAd":1,"title":"","src":"http://www.tiyushe.com/upload/2016/02/02/16212074764.jpg","url":"http://www.tiyushe.com/index.php?c=ad&a=go&url=http%3A%2F%2Fwww.tiyushe.com%2Frt%2FSignClub%2F&fid=1002&cid=0&adid=0&apptag=@aykAPP_othersite","width":750,"height":130}
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
        private int authflag;
        private int nearflag;
        private String page;
        /**
         * hasAd : 1
         * title :
         * src : http://www.tiyushe.com/upload/2016/02/02/16212074764.jpg
         * url : http://www.tiyushe.com/index.php?c=ad&a=go&url=http%3A%2F%2Fwww.tiyushe.com%2Frt%2FSignClub%2F&fid=1002&cid=0&adid=0&apptag=@aykAPP_othersite
         * width : 750
         * height : 130
         */

        private AdvertiseEntity advertise;
        private List<?> auth;
        /**
         * activity_id : 86718
         * title : 周一下午中科院打球！
         * desc : 下
         * arenaid : 11266
         * fromtime : 2016-04-04 17:00:00
         * show_day : 周一
         * show_fromtime : 17:00
         * arenaname : 中科院羽毛球馆
         * cid : 1137
         * clubname : 湖北武汉羽我同行羽毛球俱乐部
         * logo : http://www.tiyushe.com/bzdata/globalapi/themes/admin/noimage.gif
         * distance : 12.1
         * subdomain : whywtx
         * curmembers : 5
         * max_members : 16人
         * state : 报名中
         * statecode : 1
         * url : http://m.tiyushe.com/act/
         * memberInfo : [{"uid":197288,"avatar":"http://passport.tiyushe.com/avatar/000/19/72/88_avatar_middle.jpg?_avatar_fixnotfound","waiting":"0"},{"uid":112990,"avatar":"http://passport.tiyushe.com/avatar/000/11/29/90_avatar_middle.jpg?_avatar_fixnotfound","waiting":"0"},{"uid":112995,"avatar":"http://passport.tiyushe.com/avatar/000/11/29/95_avatar_middle.jpg?_avatar_fixnotfound","waiting":"0"},{"uid":139989,"avatar":"http://passport.tiyushe.com/avatar/000/13/99/89_avatar_middle.jpg?_avatar_fixnotfound","waiting":"0"},{"uid":112994,"avatar":"http://passport.tiyushe.com/avatar/000/11/29/94_avatar_middle.jpg?_avatar_fixnotfound","waiting":"0"}]
         */

        private List<NearbyEntity> nearby;

        public void setAuthflag(int authflag) {
            this.authflag = authflag;
        }

        public void setNearflag(int nearflag) {
            this.nearflag = nearflag;
        }

        public void setPage(String page) {
            this.page = page;
        }

        public void setAdvertise(AdvertiseEntity advertise) {
            this.advertise = advertise;
        }

        public void setAuth(List<?> auth) {
            this.auth = auth;
        }

        public void setNearby(List<NearbyEntity> nearby) {
            this.nearby = nearby;
        }

        public int getAuthflag() {
            return authflag;
        }

        public int getNearflag() {
            return nearflag;
        }

        public String getPage() {
            return page;
        }

        public AdvertiseEntity getAdvertise() {
            return advertise;
        }

        public List<?> getAuth() {
            return auth;
        }

        public List<NearbyEntity> getNearby() {
            return nearby;
        }

        public static class AdvertiseEntity  implements Serializable{
            private int hasAd;
            private String title;
            private String src;
            private String url;
            private int width;
            private int height;

            public void setHasAd(int hasAd) {
                this.hasAd = hasAd;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public void setSrc(String src) {
                this.src = src;
            }

            public void setUrl(String url) {
                this.url = url;
            }

            public void setWidth(int width) {
                this.width = width;
            }

            public void setHeight(int height) {
                this.height = height;
            }

            public int getHasAd() {
                return hasAd;
            }

            public String getTitle() {
                return title;
            }

            public String getSrc() {
                return src;
            }

            public String getUrl() {
                return url;
            }

            public int getWidth() {
                return width;
            }

            public int getHeight() {
                return height;
            }
        }

        public static class NearbyEntity  implements Serializable{
            private int activity_id;
            private String title;
            private String desc;
            private int arenaid;
            private String fromtime;
            private String show_day;
            private String show_fromtime;
            private String arenaname;
            private int cid;
            private String clubname;
            private String logo;
            private double distance;
            private String subdomain;
            private int curmembers;
            private String max_members;
            private String state;
            private int statecode;
            private String url;
            /**
             * uid : 197288
             * avatar : http://passport.tiyushe.com/avatar/000/19/72/88_avatar_middle.jpg?_avatar_fixnotfound
             * waiting : 0
             */

            private List<MemberInfoEntity> memberInfo;

            public void setActivity_id(int activity_id) {
                this.activity_id = activity_id;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public void setDesc(String desc) {
                this.desc = desc;
            }

            public void setArenaid(int arenaid) {
                this.arenaid = arenaid;
            }

            public void setFromtime(String fromtime) {
                this.fromtime = fromtime;
            }

            public void setShow_day(String show_day) {
                this.show_day = show_day;
            }

            public void setShow_fromtime(String show_fromtime) {
                this.show_fromtime = show_fromtime;
            }

            public void setArenaname(String arenaname) {
                this.arenaname = arenaname;
            }

            public void setCid(int cid) {
                this.cid = cid;
            }

            public void setClubname(String clubname) {
                this.clubname = clubname;
            }

            public void setLogo(String logo) {
                this.logo = logo;
            }

            public void setDistance(double distance) {
                this.distance = distance;
            }

            public void setSubdomain(String subdomain) {
                this.subdomain = subdomain;
            }

            public void setCurmembers(int curmembers) {
                this.curmembers = curmembers;
            }

            public void setMax_members(String max_members) {
                this.max_members = max_members;
            }

            public void setState(String state) {
                this.state = state;
            }

            public void setStatecode(int statecode) {
                this.statecode = statecode;
            }

            public void setUrl(String url) {
                this.url = url;
            }

            public void setMemberInfo(List<MemberInfoEntity> memberInfo) {
                this.memberInfo = memberInfo;
            }

            public int getActivity_id() {
                return activity_id;
            }

            public String getTitle() {
                return title;
            }

            public String getDesc() {
                return desc;
            }

            public int getArenaid() {
                return arenaid;
            }

            public String getFromtime() {
                return fromtime;
            }

            public String getShow_day() {
                return show_day;
            }

            public String getShow_fromtime() {
                return show_fromtime;
            }

            public String getArenaname() {
                return arenaname;
            }

            public int getCid() {
                return cid;
            }

            public String getClubname() {
                return clubname;
            }

            public String getLogo() {
                return logo;
            }

            public double getDistance() {
                return distance;
            }

            public String getSubdomain() {
                return subdomain;
            }

            public int getCurmembers() {
                return curmembers;
            }

            public String getMax_members() {
                return max_members;
            }

            public String getState() {
                return state;
            }

            public int getStatecode() {
                return statecode;
            }

            public String getUrl() {
                return url;
            }

            public List<MemberInfoEntity> getMemberInfo() {
                return memberInfo;
            }

            public static class MemberInfoEntity  implements Serializable{
                private int uid;
                private String avatar;
                private String waiting;

                public void setUid(int uid) {
                    this.uid = uid;
                }

                public void setAvatar(String avatar) {
                    this.avatar = avatar;
                }

                public void setWaiting(String waiting) {
                    this.waiting = waiting;
                }

                public int getUid() {
                    return uid;
                }

                public String getAvatar() {
                    return avatar;
                }

                public String getWaiting() {
                    return waiting;
                }
            }
        }
    }
}
