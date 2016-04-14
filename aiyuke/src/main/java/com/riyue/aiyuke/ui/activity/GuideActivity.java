package com.riyue.aiyuke.ui.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.riyue.aiyuke.R;
import com.riyue.aiyuke.bean.ClubDateInfo;
import com.riyue.aiyuke.bean.NewsHeadlineInfo;
import com.riyue.aiyuke.bean.NewsTabInfo;
import com.riyue.aiyuke.bean.NewsVideoInfo;
import com.riyue.aiyuke.tools.http.HttpUtils;
import com.riyue.aiyuke.tools.http.URLConfig;
import com.riyue.aiyuke.tools.parsejson.JsonTool;
import com.riyue.aiyuke.tools.sdcard.SdCardTool;
import com.riyue.aiyuke.ui.BaseActivity;
import com.zhy.http.okhttp.callback.StringCallback;

import java.io.File;
import java.util.Timer;
import java.util.TimerTask;

import butterknife.Bind;
import butterknife.ButterKnife;
import cn.jpush.android.api.JPushInterface;
import okhttp3.Call;

/**
 * @author mtf
 * @date 2016/4/2
 * <p/>
 * 引导界面，加载部分数据
 */
public class GuideActivity extends BaseActivity {

    private Timer timer;
    private TimerTask timerTask;
    private int countdown = 4;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
//            super.handleMessage(msg);
            switch (msg.what) {
                case 1:
                    if (countdown > 1) {
                        tvTime.setText(--countdown + "S\n跳过");
                    } else {
                        Judge();
                        timerTask.cancel();
                    }
                    break;
            }
        }
    };

    private Bundle mBundle = new Bundle();


    @Bind(R.id.guide_bg)
    SimpleDraweeView sdvBg;
    @Bind(R.id.guide_time)
    TextView tvTime;

    private boolean welcomeFlag;
    private File welcomeFile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide);
        ButterKnife.bind(this);
        initBackground();
        initTime();
        initData();
    }

    /**
     * 查看本地是否有图片存储
     */
    private void initBackground() {
        String sdCardPath = SdCardTool.getSdCardPath();
        if (sdCardPath != null) {
            StringBuffer path = new StringBuffer();
            path.append(sdCardPath).append(File.separator).append("aiyuke")
                    .append(File.separator).append("ImageCache")
                    .append(File.separator).append("welcome.jpg");
            welcomeFile = new File(path.toString());
            if (welcomeFile.isFile()) {
                sdvBg.setImageURI(Uri.fromFile(welcomeFile));
                welcomeFlag = true;
            }
        }
    }

    /**
     * 设置定时器，执行倒计时操作
     */
    private void initTime() {
        timer = new Timer();
        timerTask = new TimerTask() {
            @Override
            public void run() {
                handler.sendEmptyMessage(1);
            }
        };
        timer.schedule(timerTask, 1000, 1000);
    }

    /**
     * 下载数据
     */
    private void initData() {

        //如果本地存储没有背景则启动服务下载并存在本地
        if (!welcomeFlag) {
            Intent intent=new Intent();
            intent.setClassName("com.riyue.aiyuke","com.riyue.aiyuke.service.ImageSaveService");
            GuideActivity.this.startService(intent);
        }
        //球讯模块Tab数据
        HttpUtils.get(URLConfig.NEWS_TAB_JSON, new StringCallback() {
            @Override
            public void onError(Call call, Exception e) {

            }

            @Override
            public void onResponse(String response) {
                NewsTabInfo newsTabInfo = JsonTool.parseJson2Object(response, NewsTabInfo.class);
                mBundle.putSerializable("NewsTab", newsTabInfo);
                Judge();
            }
        });

        //俱乐部模块日期数据
        HttpUtils.get(URLConfig.CLUB_DATE_JSON, new StringCallback() {
            @Override
            public void onError(Call call, Exception e) {

            }

            @Override
            public void onResponse(String response) {
                ClubDateInfo clubDateInfo = JsonTool.parseJson2Object(response, ClubDateInfo.class);
                mBundle.putSerializable("ClubDate", clubDateInfo);
                Judge();
            }
        });

        //球讯模块头条数据
        HttpUtils.get(URLConfig.NEWS_HEADLINE_JSON+"1", new StringCallback() {
            @Override
            public void onError(Call call, Exception e) {

            }

            @Override
            public void onResponse(String response) {
                NewsHeadlineInfo newsHeadlineInfo = JsonTool.parseJson2Object(response, NewsHeadlineInfo.class);
                mBundle.putSerializable("NewsHeadline", newsHeadlineInfo);
                Judge();
            }
        });

        //球讯模块视频数据
        HttpUtils.get(URLConfig.NEWS_VIDEO_JSON+"1", new StringCallback() {
            @Override
            public void onError(Call call, Exception e) {

            }

            @Override
            public void onResponse(String response) {
                NewsVideoInfo newsVideoInfo = JsonTool.parseJson2Object(response, NewsVideoInfo.class);
                mBundle.putSerializable("NewsVideo", newsVideoInfo);
                Judge();
            }
        });
    }

    /**
     * 倒计时为1秒，并且下载完相应的Json（通过mBundle个数判定)执行跳转
     */
    private void Judge() {
        if (countdown == 1 && mBundle.size() == 4) {
            skip();
        }
    }


    /**
     * 跳转
     */
    private void skip() {
        Intent intent = new Intent();
        intent.setClassName("com.riyue.aiyuke", "com.riyue.aiyuke.ui.activity.MainActivity");
        intent.putExtra("data", mBundle);
        GuideActivity.this.startActivity(intent);
    }

    @Override
    protected void onResume() {
        super.onResume();
        JPushInterface.onResume(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        JPushInterface.onPause(this);
    }
}
