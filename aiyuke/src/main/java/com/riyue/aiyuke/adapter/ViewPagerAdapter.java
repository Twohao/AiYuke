package com.riyue.aiyuke.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.riyue.aiyuke.bean.NewsTabInfo;
import com.riyue.aiyuke.ui.BaseFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/4/5.
 */
public class ViewPagerAdapter extends FragmentPagerAdapter {
    private List<BaseFragment> list_fragment = new ArrayList<>();
    private List<NewsTabInfo.MsgEntity.ListEntity> mTitleList;
    public ViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    public ViewPagerAdapter(FragmentManager fm, List<BaseFragment> list_fragment, List<NewsTabInfo.MsgEntity.ListEntity> mTitleList) {
        super(fm);
        this.list_fragment = list_fragment;
        this.mTitleList = mTitleList;
    }

    @Override
    public Fragment getItem(int position) {
        return list_fragment.get(position);
    }

    @Override
    public int getCount() {
        return list_fragment.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mTitleList.get(position).getName();
    }
}
