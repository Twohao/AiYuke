package com.riyue.aiyuke.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.riyue.aiyuke.R;
import com.riyue.aiyuke.ui.fragment.ClubFragment;
import com.riyue.aiyuke.ui.fragment.MatchFragment;
import com.riyue.aiyuke.ui.fragment.MineFragment;
import com.riyue.aiyuke.ui.fragment.NewsFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * @author mtf
 * @date 2016/4/2
 * <p/>
 * 程序主界面，负责Fragment切换等操作
 */
public class MainActivity extends AppCompatActivity {

    @Bind(R.id.main_activity_rg_bottom)
    RadioGroup mRadioGroup;

    private List<Fragment> fragmentList = new ArrayList<>();
    private int fromFragmentIndex = 0;
    private FragmentManager manager;
    private Bundle mBundle;
    private ClubFragment mClubFragment;
    private MineFragment mMineFragment;
    private NewsFragment newsFragment;

    public NewsFragment getNewsFragment() {
        return newsFragment;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initData();
        initRadioGroup();
        initFragment();
    }

    /**
     * 得到导航界面传递的数据
     */
    private void initData() {
        Intent intent = getIntent();
        if (intent != null) {
            /*
            Bundle中有
            球讯模块Tab数据
            俱乐部模块日期数据
            球讯模块头条数据
            球讯模块视频数据
             */
            mBundle = intent.getBundleExtra("data");
        }
    }

    /**
     * 设置RadioGroup相关
     */
    private void initRadioGroup() {
        RadioButton radiobutton = (RadioButton) mRadioGroup.getChildAt(0);
        radiobutton.setChecked(true);
        mRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                int position = 0;
                switch (checkedId) {
                    case R.id.main_activity_rb_news:
                        position = 0;
                        break;
                    case R.id.main_activity_rb_club:
                        position = 1;
                        break;
                    case R.id.main_activity_rb_match:
                        position = 2;
                        break;
                    case R.id.main_activity_rb_mine:
                        position = 3;
                        break;
                }
                changeFragment(position);
            }
        });
    }

    private void changeFragment(int position) {
        FragmentTransaction transaction = manager.beginTransaction();
        Fragment toFragment = fragmentList.get(position);
        Fragment fromFragment = fragmentList.get(fromFragmentIndex);
        if (toFragment.isAdded()) {
            transaction.hide(fromFragment).show(toFragment);
        } else {
            transaction.hide(fromFragment).add(R.id.main_activity_contains, toFragment);
        }
        transaction.commit();
        fromFragmentIndex = position;
    }

    /**
     * 初始化Fragment并默认显示第一个
     */
    private void initFragment() {
        manager = getSupportFragmentManager();

        mClubFragment = ClubFragment.newInstance(mBundle);
        mMineFragment=MineFragment.newInstance();
        // TODO: 2016/4/4 Fragment中需要添加的数据没有传递

        newsFragment = NewsFragment.newInstance(mBundle);
        fragmentList.add(newsFragment);
        fragmentList.add(mClubFragment);
        fragmentList.add(MatchFragment.newInstance(null, null));
        fragmentList.add(mMineFragment);

        manager.beginTransaction()
                .add(R.id.main_activity_contains, fragmentList.get(fromFragmentIndex))
                .commit();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
        switch (resultCode) {
            case 2:
                mClubFragment.onActivityResult(requestCode, resultCode, data);
                break;
            case 200:
                mMineFragment.onActivityResult(requestCode,resultCode,data);
                break;
        }
    }
}
