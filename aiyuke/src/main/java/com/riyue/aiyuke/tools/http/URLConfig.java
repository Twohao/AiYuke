package com.riyue.aiyuke.tools.http;

/**
 * @author mtf
 * @date 2016/4/2
 */
public class URLConfig {
    //引导界面Json
    public static final String GUIDE_JSON="http://app.aiyuke.com/v2.0//AppData/Advertise/setting/?appPlatform=ANDROID&&width=1080&uid=&ssotoken=&page=";

    /**
     *球讯模块
     */
    //TabLayout
    public static final String NEWS_TAB_JSON="http://app.aiyuke.com/v2.0//AppData/Info/Maps//?appPlatform=ANDROID&";
    //头条
    public static final String NEWS_HEADLINE_JSON="http://app.aiyuke.com/v2.0/AppData/Info/News/?appPlatform=ANDROID&&page=";
    //视频
    public static final String NEWS_VIDEO_JSON="http://app.aiyuke.com/v2.0/AppData/Video/List/?appPlatform=ANDROID&&page=";
    //赛事
    public static final String NEWS_MATCH_JSON="http://app.aiyuke.com/v2.0/AppData/Event/Lists/?appPlatform=ANDROID&&page=";
    //教学
    public static final String NEWS_TEACH_JSON="http://app.aiyuke.com/v2.0/AppData/Tech/List/?appPlatform=ANDROID&&page=";
    //球星
    public static final String NEWS_PLAYER_JSON="http://app.aiyuke.com/v2.0/AppData/SportPlayer/hotList/?appPlatform=ANDROID&";
    //装备
    public static final String NEWS_EQUIP_JSON="http://app.aiyuke.com/v2.0/AppData/Equip/List/?appPlatform=ANDROID&&page=";
    //保健
    public static final String NEWS_HEALTH_JSON="http://app.aiyuke.com/v2.0/AppData/Health/List/?appPlatform=ANDROID&&page=";

    /**
     *俱乐部模块
     */
    //日期
    public static final String CLUB_DATE_JSON="http://app.tiyushe.com/v2.0/AppData/SportClub/SetTime/?appPlatform=ANDROID&";
    //具体日期附近活动
    /*
    http://app.tiyushe.com/v2.0//AppData/SportClub/Home//?appPlatform=ANDROID&uid=&ssotoken=&time=20160401&city=1886&lng=114.436224&lat=30.460075&page=1
     */
    public static final String CLUB_SPORTS_JSON="http://app.tiyushe.com/v2.0//AppData/SportClub/Home//?appPlatform=ANDROID&uid=&ssotoken=&time=";

    //地图上面附近体育场馆信息
    public static final String MAP_SPORTSCLUB_JSON="http://app.tiyushe.com/v2.0/AppData/SportClub/Map?/?appPlatform=ANDROID&uid=&ssotoken=";


    public static final String CLUB_SPORTS_DETAILS_JSON="http://app.tiyushe.com/v2.0//AppData/SportActivity/show?aid=";

    public static final String CLUB_SPORTS_HEADER_JSON="http://app.tiyushe.com/v2.0//AppData/SportClub/index//?appPlatform=ANDROID&uid=276452&ssotoken=36dcf6f5facc81ad1a0ef694919a1e89ad6df06027fb41a888a6&cid=";


    //登录接口
    //Post请求  username=18672391226&submit=1&password=7be8286c5aff38a4d4dc0c12eae30ae0&authcode=&
    public static final String LOGIN_JSON="http://passport.tiyushe.com/index.php?rc=SSO&ra=login&ajax=1&app=1&md5ed=1";

}
