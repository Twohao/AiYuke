package com.riyue.aiyuke.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;

import com.google.zxing.Result;
import com.riyue.aiyuke.ui.BaseActivity;

import me.dm7.barcodescanner.zxing.ZXingScannerView;

public class ScannerActivity extends BaseActivity implements ZXingScannerView.ResultHandler {
    private ZXingScannerView mScannerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mScannerView = new ZXingScannerView(this);   // Programmatically initialize the scanner view
        setContentView(mScannerView);                // Set the scanner view as the content view
    }

    @Override
    public void onResume() {
        super.onResume();
        mScannerView.setResultHandler(this); // Register ourselves as a handler for scan results.
        mScannerView.startCamera();          // Start camera on resume
    }

    @Override
    public void onPause() {
        super.onPause();
        mScannerView.stopCamera();           // Stop camera on pause
    }

    @Override
    public void handleResult(Result rawResult) {
        // Do something with the result here
//        Log.v(TAG, rawResult.getText()); // Prints scan results
//        Log.v(TAG, rawResult.getBarcodeFormat().toString()); // Prints the scan format (qrcode, pdf417 etc.)
        if(TextUtils.indexOf(rawResult.getText(),"tiyushe.com")!=-1){
            Intent intent = new Intent();
            intent.setClassName("com.riyue.aiyuke", "com.riyue.aiyuke.ui.activity.WebActivity");
            intent.putExtra("url", rawResult.getText());
            startActivity(intent);
        }
    }
}
