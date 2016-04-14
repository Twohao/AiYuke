package com.riyue.aiyuke.tools.http;

import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.Callback;

import java.util.Map;

/**
 * @author mtf
 * @date 2016/4/2
 */
public class HttpUtils {


    /**
     * Get请求
     * @param url url地址
     * @param params  参数集合
     * @param callback 回调接口
     */
    public static void get(String url,Map<String,String> params,Callback callback){
        OkHttpUtils.get()
                .url(url)
                .params(params)
                .build()
                .execute(callback);
    }

    /**
     *
     * @param url url地址
     * @param callback 回调接口
     */
    public static void get(String url,Callback callback){
        HttpUtils.get(url, null, callback);
    }




    /**
     * Post请求
     * @param url url地址
     * @param params  参数集合
     * @param callback 回调接口
     */
    public static void post(String url,Map<String,String> params,Callback callback){
        OkHttpUtils.post()
                .url(url)
                .params(params)
                .build()
                .execute(callback);
    }
}
