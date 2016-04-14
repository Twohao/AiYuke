package com.riyue.aiyuke.tools.toast;

import android.content.Context;
import android.widget.Toast;

/**
 * @author mtf
 * @date 2016/4/2
 */
public class ToastTool {

    private final static boolean TEST_DEBUG=true;
    /**
     * 测试用的Toast
     * @param context Context对象
     * @param content 显示内容
     */
    public static void ToastTest(Context context,String content){
        if(TEST_DEBUG) {
            Toast.makeText(context, content, Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * 时间为short的Toast
     * @param context Context对象
     * @param content 显示内容
     */
    public static void ToastShowShort(Context context,String content){
        Toast.makeText(context, content, Toast.LENGTH_SHORT).show();
    }

    /**
     * 时间为long的Toast
     * @param context Context对象
     * @param content 显示内容
     */
    public static void ToastShowLong(Context context,String content){
        Toast.makeText(context, content, Toast.LENGTH_LONG).show();
    }
}
