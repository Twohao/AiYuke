package com.riyue.aiyuke.service;

import android.app.Service;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.IBinder;

import com.riyue.aiyuke.bean.GuideInfo;
import com.riyue.aiyuke.tools.http.HttpUtils;
import com.riyue.aiyuke.tools.http.URLConfig;
import com.riyue.aiyuke.tools.parsejson.JsonTool;
import com.riyue.aiyuke.tools.sdcard.SdCardTool;
import com.zhy.http.okhttp.callback.BitmapCallback;
import com.zhy.http.okhttp.callback.StringCallback;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import okhttp3.Call;

public class ImageSaveService extends Service {
    private File welcomeFile;

    public ImageSaveService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        String sdCardPath = SdCardTool.getSdCardPath();
        StringBuffer path = new StringBuffer();
        path.append(sdCardPath).append(File.separator).append("aiyuke")
                .append(File.separator).append("ImageCache")
                .append(File.separator).append("welcome.jpg");
        welcomeFile = new File(path.toString());

        //引导页数据
        HttpUtils.get(URLConfig.GUIDE_JSON, new StringCallback() {
            @Override
            public void onError(Call call, Exception e) {

            }

            @Override
            public void onResponse(String response) {
                GuideInfo guideInfo = JsonTool.parseJson2Object(response, GuideInfo.class);
                if (guideInfo != null && guideInfo.getMsg() != null && guideInfo.getMsg().getThefile() != null) {
//                        sdvBg.setImageURI(Uri.parse(guideInfo.getMsg().getThefile()));

                    HttpUtils.get(guideInfo.getMsg().getThefile(), new BitmapCallback() {
                        @Override
                        public void onError(Call call, Exception e) {

                        }

                        @Override
                        public void onResponse(final Bitmap response) {
                            new Thread(new Runnable() {
                                @Override
                                public void run() {
                                    if (SdCardTool.isSdCardExist()) {
                                        BufferedOutputStream os = null;
                                        try {

                                            String path = welcomeFile.getAbsolutePath();
                                            int end = path.lastIndexOf(File.separator);
                                            String _filePath = path.substring(0, end); //获取图片路径
                                            File filePath = new File(_filePath);
                                            if (!filePath.exists()) {  //如果文件夹不存在，创建文件夹
                                                filePath.mkdirs();
                                            }
                                            welcomeFile.createNewFile();  //创建图片文件
                                            os = new BufferedOutputStream(new FileOutputStream(welcomeFile));
                                            response.compress(Bitmap.CompressFormat.JPEG, 100, os);
                                        } catch (IOException e) {
                                            e.printStackTrace();
                                        } finally {
                                            try {
                                                if (null != os) {
                                                    os.close();
                                                }
                                            } catch (IOException e) {
                                                e.printStackTrace();
                                            }
                                            os = null;
                                        }
                                    }
                                }
                            }).start();
                        }
                    });
                }
            }
        });
    }
}
