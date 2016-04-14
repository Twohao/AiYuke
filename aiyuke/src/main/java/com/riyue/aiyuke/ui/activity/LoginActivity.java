package com.riyue.aiyuke.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.riyue.aiyuke.R;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.login_activity_cancel)
    public void cancelClick(){
        this.finish();
    }

    @OnClick(R.id.login_activity_login)
    public void loginClick(){
        Intent intent=new Intent();
        intent.setClassName("com.riyue.aiyuke","com.riyue.aiyuke.ui.activity.LoginLoginActivity");
        startActivityForResult(intent, 2);
    }

    @OnClick(R.id.login_activity_register)
    public void registerClick(){
        Intent intent=new Intent();
        intent.setClassName("com.riyue.aiyuke","com.riyue.aiyuke.ui.activity.RegisterFirstActivity");
        startActivityForResult(intent, 2);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
        switch (resultCode){
            case 2:
                setResult(200,data);
                finish();
                break;
        }
    }
}
