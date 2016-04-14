package com.riyue.aiyuke.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.riyue.aiyuke.bean.ClubDateInfo;
import com.riyue.aiyuke.ui.BaseFragment;

import java.util.List;

/**
 * Created by Mr.xu on 2016/4/5.
 */
public class ClubTabLayoutAdapter extends FragmentPagerAdapter {
    private List<ClubDateInfo.MsgEntity> entityList;
    private List<BaseFragment> fragmentList;

    public ClubTabLayoutAdapter(List<ClubDateInfo.MsgEntity> entityList,List<BaseFragment> fragmentList,FragmentManager fm) {
        super(fm);
        this.entityList = entityList;
        this.fragmentList = fragmentList;
    }

    @Override
    public Fragment getItem(int position) {
        return fragmentList == null ? null : fragmentList.get(position);
    }

    @Override
    public int getCount() {
        return fragmentList == null ? 0 : fragmentList.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        ClubDateInfo.MsgEntity msgEntity = entityList.get(position);
        return msgEntity.getShow_time()+"\n"+msgEntity.getCn();
    }
}
