package com.riyue.aiyuke.tools.parsejson;

import com.google.gson.Gson;

/**
 * @author mtf
 * @date 2016/4/4
 */
public class JsonTool {
    private static Gson gson = new Gson();

    /**
     * 将一个json字符串转换成Object对象
     * @param json
     * @param object
     * @param <T>
     * @return
     */
    public static <T> T parseJson2Object(String json,Class<T> object) {
        return gson.fromJson(json,object);
    }
}
