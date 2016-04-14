package com.riyue.aiyuke.tools.log;

import android.util.Log;

/**
 * @author mtf
 * @date 2016/4/2
 */
public class LogTool {
    private static boolean DEBUG = true;

    public static void LOG_E(Class clazz,String log) {
        if (DEBUG) {
            int len = log.length();
            int num = len/1000;

            String str = "";
            for (int i=0; i<num+1; i++) {
                if (i == num) {
                    str = log.substring(i*1000,len);
                } else {
                    str = log.substring(i * 1000, 1000 * (i + 1));
                }

                Log.e(clazz.toString(), str);
            }
        }
    }
}
