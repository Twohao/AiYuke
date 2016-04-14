package com.riyue.aiyuke.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.riyue.aiyuke.R;

import butterknife.Bind;
import butterknife.ButterKnife;

public class WebActivity extends AppCompatActivity {

    @Bind(R.id.web_activity_web)
    WebView mWebView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);
        ButterKnife.bind(this);
        initWeb();

    }

    private void initWeb() {
        Intent intent = getIntent();
        String url = intent.getStringExtra("url");
        WebSettings setting = mWebView.getSettings();
        setting.setLoadWithOverviewMode(true);
        setting.setJavaScriptEnabled(true);
        setting.setUseWideViewPort(true);
        mWebView.setWebChromeClient(new WebChromeClient());
        mWebView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
//                return super.shouldOverrideUrlLoading(view, url);
                view.loadUrl(url);
                return true;
            }
        });
        mWebView.loadUrl(url);
    }
}
