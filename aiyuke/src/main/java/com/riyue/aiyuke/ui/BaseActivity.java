package com.riyue.aiyuke.ui;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

/**
 * @author mtf
 * @date 2016/4/2
 *
 *
 */
public class BaseActivity extends AppCompatActivity implements BaseFragment.OnFragmentInteractionListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

}
