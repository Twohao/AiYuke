package com.riyue.aiyuke.tools.location;

import com.riyue.aiyuke.ui.AiYusheApplication;

/**
 * Created by Mr.xu on 2016/3/2.
 */
public class LocationService {
    AiYusheApplication aiYusheApplication = AiYusheApplication.getInstance();

    public void start(){
        if (aiYusheApplication.mLocationClient.isStarted()) {
            //请求位置
            aiYusheApplication.mLocationClient.requestLocation();
        }else{
            aiYusheApplication.mLocationClient.start();
        }
    }

    public void stop(){
        if (aiYusheApplication.mLocationClient.isStarted()){
            aiYusheApplication.mLocationClient.stop();
        }
    }
}
