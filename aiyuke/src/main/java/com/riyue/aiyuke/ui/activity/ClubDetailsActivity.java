package com.riyue.aiyuke.ui.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.drawee.view.SimpleDraweeView;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshScrollView;
import com.riyue.aiyuke.R;
import com.riyue.aiyuke.bean.ClubSportsDetailsInfo;
import com.riyue.aiyuke.tools.http.HttpUtils;
import com.riyue.aiyuke.tools.log.LogTool;
import com.riyue.aiyuke.tools.parsejson.JsonTool;
import com.riyue.aiyuke.tools.toast.ToastTool;
import com.riyue.aiyuke.ui.AiYusheApplication;
import com.zhy.http.okhttp.callback.StringCallback;

import java.io.File;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.onekeyshare.OnekeyShare;
import okhttp3.Call;

/**
 * @author xyh
 * @date 2016/4/8
 */
public class ClubDetailsActivity extends AppCompatActivity {
    private List<ClubSportsDetailsInfo.MsgEntity> msgEntityList = new ArrayList<>();
    private static final String PAREM = "url";
    private String url;
    private ClubSportsDetailsInfo.MsgEntity msgEntity;
    @Bind(R.id.club_details_day)
    TextView mTitle;
    @Bind(R.id.club_details_time)
    TextView showTime;
    @Bind(R.id.club_details_money)
    TextView mMoney;
    @Bind(R.id.club_details_people)
    TextView mPeople;
    @Bind(R.id.club_details_lure)
    TextView mLure;
    @Bind(R.id.club_details_club_icon)
    SimpleDraweeView mClubIcon;
    @Bind(R.id.club_details_club_title)
    TextView mClubName;
    @Bind(R.id.club_details_club_people)
    TextView mNumPeople;
    @Bind(R.id.club_details_club_num)
    TextView mClubCount;
    @Bind(R.id.club_details_num_people)
    TextView mCountPeople;
    @Bind(R.id.club_details_gymnasium_name)
    TextView mGymnasium;
    @Bind(R.id.club_details_gymnasium_address)
    TextView mGymnasiumAddress;
    @Bind(R.id.club_details_address_name)
    TextView mAddressName;
    @Bind(R.id.club_details_organizer_name)
    TextView mOrganizerName;
    @Bind(R.id.club_details_punish_take)
    TextView mPunishTake;
    @Bind(R.id.club_details_punish_price)
    TextView mPunishPrice;
    @Bind(R.id.club_details_add)
    TextView mAdd;
    @Bind(R.id.club_details_add_price)
    TextView mAddPrice;
    @Bind(R.id.club_details_close_time)
    TextView mCloseTime;
    @Bind(R.id.club_details_leave_task)
    TextView mLeaveTask;
    @Bind(R.id.club_details_surplus_time)
    TextView mSurplusTime;
    @Bind(R.id.club_details_people_icon)
    GridLayout mGridLayout;
    @Bind(R.id.club_details_scroll)
    PullToRefreshScrollView mScrollView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_club_details);
        ButterKnife.bind(this);
        url = getIntent().getStringExtra(PAREM);
        initData();
        setupListener();
    }

    /**
     * 设置监听
     */
    private void setupListener() {
        mScrollView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ScrollView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ScrollView> refreshView) {
                initData();

            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ScrollView> refreshView) {

            }
        });
    }

    @OnClick({R.id.club_details_back,R.id.club_details_share,R.id.club_details_organizer_tel})
    public void onClick(View view){
        switch (view.getId()){
            //拨号界面
            case R.id.club_details_organizer_tel:
                callPhone();
                break;
            case R.id.club_details_sign:

                break;
            case R.id.club_details_back:
                finish();
                break;
            case R.id.club_details_share:
                ShareSDK.initSDK(this);
                OnekeyShare oks = new OnekeyShare();
                //关闭sso授权
                oks.disableSSOWhenAuthorize();

// 分享时Notification的图标和文字  2.5.9以后的版本不调用此方法
                //oks.setNotification(R.drawable.ic_launcher, getString(R.string.app_name));
                // title标题，印象笔记、邮箱、信息、微信、人人网和QQ空间使用
                oks.setTitle("你的果照");
                // titleUrl是标题的网络链接，仅在人人网和QQ空间使用
                oks.setTitleUrl("http://sharesdk.cn");
                // text是分享文本，所有平台都需要这个字段
                oks.setText("我是分享文本");
                // imagePath是图片的本地路径，Linked-In以外的平台都支持此参数
                //oks.setImagePath("/sdcard/test.jpg");//确保SDcard下面存在此张图片
                // url仅在微信（包括好友和朋友圈）中使用
                oks.setUrl("http://sharesdk.cn");
                // comment是我对这条分享的评论，仅在人人网和QQ空间使用
                oks.setComment("我是测试评论文本");
                // site是分享此内容的网站名称，仅在QQ空间使用
                oks.setSite(getString(R.string.app_name));
                // siteUrl是分享此内容的网站地址，仅在QQ空间使用
                oks.setSiteUrl("http://sharesdk.cn");

// 启动分享GUI
                oks.show(this);
                break;
        }
    }

    private void callPhone() {
        String mobile = msgEntity.getManager().getMobile();
        if (mobile.equals("")) {
            Toast.makeText(ClubDetailsActivity.this, "没有号码", Toast.LENGTH_SHORT).show();
        }else {
            Intent intent = new Intent();
            intent.setAction(Intent.ACTION_DIAL);
            intent.setData(Uri.parse("tel:" + mobile));
            startActivity(intent);
        }
    }

    private void initData() {
        LogTool.LOG_E(ClubDetailsActivity.class, url + "--------------------------");
        HttpUtils.get(url, callback);
    }

    StringCallback callback = new StringCallback() {
        @Override
        public void onError(Call call, Exception e) {

        }

        @Override
        public void onResponse(String response) {
            ClubSportsDetailsInfo clubSportsDetailsInfo = JsonTool.parseJson2Object(response, ClubSportsDetailsInfo.class);
            msgEntityList.add(clubSportsDetailsInfo.getMsg());
            if (!msgEntityList.isEmpty()) {
                msgEntity = msgEntityList.get(0);
                mTitle.setText(msgEntity.getTitle());
                showTime.setText(msgEntity.getShow_time());
                mMoney.setText(msgEntity.getMoney());
                mPeople.setText(msgEntity.getMemeber());
                mLure.setText("活动诱惑： " + msgEntity.getDescription());

                mClubIcon.setImageURI(getUri(msgEntity.getClub().getIndeximage()));
                mClubName.setText(msgEntity.getClub().getClubname());
                mNumPeople.setText("会员" + msgEntity.getClub().getMembercount() + "人");
                mClubCount.setText("每周" + msgEntity.getClub().getActperweek() + "场活动");
                mCountPeople.setText("已报名球友（" + msgEntity.getMemeber() + ")");

                mGymnasium.setText(msgEntity.getArena().getShow_arena());
                mGymnasiumAddress.setText(msgEntity.getArena().getAddress());
                mAddressName.setText(msgEntity.getArena().getPlace());

                mOrganizerName.setText(msgEntity.getManager().getNickname());

                mPunishTake.setText(msgEntity.getFine().getNotjoin_desc());
                mPunishPrice.setText(msgEntity.getFine().getNotjoin_price());
                mAdd.setText(msgEntity.getFine().getNosign_desc());
                mAddPrice.setText(msgEntity.getFine().getNosign_price());

                mCloseTime.setText(msgEntity.getShow_closejointime());

                mLeaveTask.setText(msgEntity.getLeave_desc());
                List<String> ballfriends = msgEntity.getBall_friends();
                if (!ballfriends.isEmpty()) {
                    for (int i = 0; i < ballfriends.size(); i++) {
                        SimpleDraweeView simpleDraweeView = new SimpleDraweeView(ClubDetailsActivity.this);
                        simpleDraweeView.setImageURI(getUri(ballfriends.get(i)));
                        mGridLayout.addView(simpleDraweeView);
                    }
                }
                String canceljointime = msgEntity.getCanceljointime();
                mSurplusTime.setText(parseTime(Integer.valueOf(canceljointime)));
                mScrollView.onRefreshComplete();
            }

        }
    };

    private Uri getUri(String indeximage) {
        return Uri.parse(indeximage);
    }

    private String parseTime(int time) {
        int minute = time % 3600 / 60;
        int hour = time % (24 * 3600) / 3600;
        int day = time / (24 * 3600);
        StringBuffer stringBuffer = new StringBuffer();
        if (day > 0) {
            stringBuffer.append(day).append("天");
        }
        if (hour > 0) {
            stringBuffer.append(hour).append("小时");
        }
        if ((minute > 0)) {
            if (minute < 10) {
                stringBuffer.append("0");
            }
            stringBuffer.append(minute).append("分");
        }
        return stringBuffer.toString();
    }

    @OnClick(R.id.club_details_gymnasium_gps)
    public void gpsClick() {
        if (isInstallByread("com.autonavi.minimap")) {
            openGaoDeMap();
        }
//        else if (isInstallByread("com.baidu.BaiduMap")) {
//            openBaiduMap();
//        }
        else {
            ToastTool.ToastShowShort(this, mGymnasiumAddress.getText().toString());
        }
    }


    /**
     * 判定是否安装某个应用
     *
     * @param packageName
     * @return
     */
    private boolean isInstallByread(String packageName) {
        return new File("/data/data/" + packageName).exists();
    }


    private void openBaiduMap() {
        try {
            Intent intent = Intent.getIntent("intent://map/marker?location=" +
                    AiYusheApplication.lat + "," + AiYusheApplication.lon +
                    "&title=我的位置&content=" + mGymnasiumAddress.getText().toString() +
                    "&src=Aiyuke#Intent;scheme=bdapp;package=com.baidu.BaiduMap;end");
            startActivity(intent); //启动调用
        } catch (URISyntaxException e1) {
            e1.printStackTrace();
        }
    }

    private void openGaoDeMap() {
        try {
            Intent intent = Intent.getIntent("androidamap://viewMap?sourceApplication=Aiyuke&poiname=" +
                    mGymnasiumAddress.getText().toString() +
                    "&lat=" + AiYusheApplication.lat + "&lon=" + AiYusheApplication.lon + "&dev=0");
            startActivity(intent);
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }
}
