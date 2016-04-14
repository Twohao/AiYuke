package com.riyue.aiyuke.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

import com.riyue.aiyuke.R;
import com.riyue.aiyuke.ui.BaseActivity;

import butterknife.OnClick;


/**
 * Created by Administrator on 2016/3/27.
 */
public class StarUrlIntentActivity extends BaseActivity {
    private TextView mTextView;
    private WebView mWebView;
    String urlHead="http://app.aiyuke.com//index.php?rm=AppData&rc=SportPlayer&ra=List&letter=";
    String num;
    StringBuffer sb = new StringBuffer();
    String url;
    int id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.star_url_layout);
        Intent intent = getIntent();
        num = intent.getStringExtra("num1");
        Log.i("should", "onCreate: " + num);
        url = sb.append(urlHead).append(num).toString();
        mWebView = (WebView) findViewById(R.id.star_webview);
        mTextView = (TextView) findViewById(R.id.star_head_textview);
        getData();
    }

    private void getData() {
        mWebView.loadUrl(url);
        mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
//                return super.shouldOverrideUrlLoading(view, url);
                view.loadUrl(url);
                return true;
            }
        });
        mTextView.setText("球星-"+num);
        Log.e("should", url);
    }
    @OnClick(R.id.star_back_image)
    public void onClick(View view){
        switch (view.getId()){
            case R.id.star_back_image:
                finish();
                break;
        }
    }
}
