package com.riyue.aiyuke.ui.fragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.riyue.aiyuke.R;
import com.riyue.aiyuke.bean.LoginInfo;
import com.riyue.aiyuke.ui.AiYusheApplication;
import com.riyue.aiyuke.ui.BaseFragment;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author mtf
 * @date 2016/4/2主界面的个人信息模块
 *
 *
 */
public class MineFragment extends BaseFragment {
    private AiYusheApplication mApplication;

    @Bind(R.id.mine_fragment_user_tv)
    TextView tvUser;
    @Bind(R.id.mine_fragment_user_img)
    SimpleDraweeView simpleDraweeViewImg;

    public MineFragment() {
    }

    public static MineFragment newInstance() {
        MineFragment fragment = new MineFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mApplication = AiYusheApplication.getInstance();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_mine, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @OnClick(R.id.mine_fragment_scanner)
    public  void ScannerClick(){
        Intent intent=new Intent();
        intent.setClassName("com.riyue.aiyuke","com.riyue.aiyuke.ui.activity.ScannerActivity");
        startActivity(intent);
    }

    @OnClick(R.id.mine_fragment_login_ll)
    public void loginClick(){
        if(!mApplication.isLogin()){//如果没有登录，进行登录操作
            Intent intent=new Intent();
            intent.setClassName("com.riyue.aiyuke","com.riyue.aiyuke.ui.activity.LoginActivity");
            startActivityForResult(intent, 2);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
        switch (resultCode){
            case 200:
                LoginInfo loginInfo = mApplication.getLoginInfo();
                String username = loginInfo.getMsg().getUsername();
                String mobile = loginInfo.getMsg().getMobile();
                tvUser.setText(username+"\n账号："+mobile);
                simpleDraweeViewImg.setImageURI(Uri.parse(loginInfo.getMsg().getAvatar()));
                break;
        }
    }
}
