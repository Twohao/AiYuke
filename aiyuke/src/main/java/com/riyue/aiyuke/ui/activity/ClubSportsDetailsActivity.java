package com.riyue.aiyuke.ui.activity;

import android.net.Uri;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.riyue.aiyuke.R;
import com.riyue.aiyuke.adapter.ClubSportsHeaderListAdapter;
import com.riyue.aiyuke.bean.ClubSportsHeaderDetailsInfo;
import com.riyue.aiyuke.tools.http.HttpUtils;
import com.riyue.aiyuke.tools.http.URLConfig;
import com.riyue.aiyuke.tools.parsejson.JsonTool;
import com.riyue.aiyuke.ui.listview.CustomListView;
import com.zhy.http.okhttp.callback.StringCallback;

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
 * 俱乐部
 */
public class ClubSportsDetailsActivity extends AppCompatActivity {
    private List<ClubSportsHeaderDetailsInfo.MsgEntity.RecentEntity.DataEntity> entityList = new ArrayList<>();
    private static final String PAREM = "url";
    @Bind(R.id.sports_details_activity_list)
    CustomListView mListView;
    @Bind(R.id.sports_details_title)
    TextView mTitle;
    @Bind(R.id.sports_details_name)
    TextView mName;
    @Bind(R.id.sports_details_Icon)
    SimpleDraweeView mIcon;
    @Bind(R.id.sports_details_num)
    TextView mNum;
    @Bind(R.id.sports_details_count)
    TextView mCount;
    @Bind(R.id.image_one)
    SimpleDraweeView imageOne;
    @Bind(R.id.image_two)
    SimpleDraweeView imageTwo;
    @Bind(R.id.image_three)
    SimpleDraweeView imageThree;
    @Bind(R.id.image_four)
    SimpleDraweeView imageFour;
    @Bind(R.id.image_five)
    SimpleDraweeView imageFive;
    @Bind(R.id.image_six)
    SimpleDraweeView imageSix;
    @Bind(R.id.sports_details_zarbar_icon)
    SimpleDraweeView mZarbar;
    @Bind(R.id.sports_details_content)
    TextView mContent;
    @Bind(R.id.sports_details_scrollview)
    ScrollView mScrollView;


    private String url;
    private ClubSportsHeaderListAdapter mAdapter;
    private ClubSportsHeaderDetailsInfo.MsgEntity msg;
    private AlertDialog alertDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_club_sports_details);
        ButterKnife.bind(this);
        url = getIntent().getStringExtra(PAREM);
        initView();
    }



    @OnClick({R.id.sports_details_back, R.id.sports_details_share,R.id.sports_details_zarbar})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.sports_details_zarbar:
                popupDialog();
                break;
            case R.id.sports_details_back:
                finish();
                break;
            case R.id.sports_details_share:
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

    /**
     * 自定义dialog
     */
    private void popupDialog() {

        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        View v = LayoutInflater.from(this).inflate(R.layout.item_zarbar_dialog, null);
        SimpleDraweeView imageView =(SimpleDraweeView) v.findViewById(R.id.dialog_zarbar);
        TextView close = (TextView)v.findViewById(R.id.dialog_close);
        TextView name = (TextView)v.findViewById(R.id.dialog_club_name);
        name.setText(msg.getSportClubDetail().getData().getClubname());
        imageView.setImageURI(getUri(msg.getQcord().getData()));
        builder.setView(v);
        alertDialog = builder.create();
        alertDialog.show();
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
            }
        });
    }


    private void initView() {
        initData();
        mAdapter = new ClubSportsHeaderListAdapter(this, entityList);
        mListView.setAdapter(mAdapter);
    }

    private void initData() {

        HttpUtils.get(url, new StringCallback() {
            @Override
            public void onError(Call call, Exception e) {

            }

            @Override
            public void onResponse(String response) {
                ClubSportsHeaderDetailsInfo clubSportsHeaderDetailsInfo = JsonTool.parseJson2Object(
                        response, ClubSportsHeaderDetailsInfo.class);
                msg = clubSportsHeaderDetailsInfo.getMsg();
                entityList.addAll(msg.getRecent().getData());
                mAdapter.notifyDataSetChanged();
                mTitle.setText(msg.getSportClubDetail().getData().getClubname());
                mName.setText(msg.getSportClubDetail().getData().getClubname());
                mIcon.setImageURI(getUri(msg.getSportClubDetail().getData().getIndeximage()));
                mNum.setText("会员" + msg.getSportClubDetail().getData().getMembercount() + "人");
                mCount.setText("每周" + msg.getSportClubDetail().getData().getActperweek() + "场活动");
                imageOne.setImageURI(getUri(msg.getMemeber().getData().get(0).getAvatar()));
                imageTwo.setImageURI(getUri(msg.getMemeber().getData().get(1).getAvatar()));
                imageThree.setImageURI(getUri(msg.getMemeber().getData().get(2).getAvatar()));
                imageFour.setImageURI(getUri(msg.getMemeber().getData().get(3).getAvatar()));
                imageFive.setImageURI(getUri(msg.getMemeber().getData().get(4).getAvatar()));
                imageSix.setImageURI(getUri(msg.getMemeber().getData().get(5).getAvatar()));
                mZarbar.setImageURI(getUri(msg.getQcord().getData()));
                mContent.setText(msg.getIntroduce().getData());
            }
        });
    }

    private Uri getUri(String indeximage) {
        return Uri.parse(indeximage);
    }


}
