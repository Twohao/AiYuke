package com.riyue.aiyuke.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.widget.EditText;

import com.riyue.aiyuke.R;
import com.riyue.aiyuke.bean.LoginInfo;
import com.riyue.aiyuke.tools.http.HttpUtils;
import com.riyue.aiyuke.tools.http.URLConfig;
import com.riyue.aiyuke.tools.md5.MD5Tool;
import com.riyue.aiyuke.tools.parsejson.JsonTool;
import com.riyue.aiyuke.tools.toast.ToastTool;
import com.riyue.aiyuke.ui.AiYusheApplication;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.HashMap;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;

public class LoginLoginActivity extends AppCompatActivity {

    @Bind(R.id.login_login_activity_username)
    EditText etUserName;
    @Bind(R.id.login_login_activity_password)
    EditText etPassWord;
    Map<String, String> loginMap;
    private AiYusheApplication mApplication;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_login);
        mApplication = AiYusheApplication.getInstance();
        ButterKnife.bind(this);
    }

    @OnClick(R.id.login_login_activity_back)
    public void backClick(){
        this.finish();
    }

    @OnClick(R.id.login_login_activity_login)
    public void loginClick(){
        if(TextUtils.isEmpty(etUserName.getEditableText().toString())){
            ToastTool.ToastShowShort(this,"请输入用户名");
            return;
        }
        if(TextUtils.isEmpty(etPassWord.getEditableText().toString())){
            ToastTool.ToastShowShort(this,"请输入密码");
            return;
        }

        String password = MD5Tool.GetMD5Code(etPassWord.getEditableText().toString());
        loginMap = new HashMap<>();
        loginMap.put("username",etUserName.getEditableText().toString());
        loginMap.put("submit","1");
        loginMap.put("password",password);
        loginMap.put("authcode","");
        HttpUtils.post(URLConfig.LOGIN_JSON, loginMap, new StringCallback() {
            @Override
            public void onError(Call call, Exception e) {

            }

            @Override
            public void onResponse(String response) {
                LoginInfo loginInfo = JsonTool.parseJson2Object(response, LoginInfo.class);
                if(!TextUtils.equals(loginInfo.getType(),"succ")){
                    ToastTool.ToastShowShort(LoginLoginActivity.this,"用户名或密码错误");
                }else {
                    mApplication.setIsLogin(true);
                    mApplication.setLoginInfo(loginInfo);
                    Intent intent=new Intent();
//                    intent.putExtra("userinfo",loginInfo);
                    setResult(2,intent);
                    finish();
                }
            }
        });
    }
}
