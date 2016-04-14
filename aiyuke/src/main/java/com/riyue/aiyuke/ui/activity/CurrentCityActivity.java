package com.riyue.aiyuke.ui.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.riyue.aiyuke.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CurrentCityActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_current_city);
        ButterKnife.bind(this);
    }
    @OnClick(R.id.current_city_topbar_back)
    public void onClick(View view){
        switch (view.getId()){
            case R.id.current_city_topbar_back:
                finish();
                break;
        }
    }
}
