package com.riyue.aiyuke.ui.fragment;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.riyue.aiyuke.R;
import com.riyue.aiyuke.adapter.ViewPagerAdapter;
import com.riyue.aiyuke.bean.NewsTabInfo;
import com.riyue.aiyuke.ui.BaseFragment;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;


/**
 * @author mtf
 * @date 2016/4/2
 *
 * 主界面的球讯模块
 */
public class NewsFragment extends BaseFragment implements Serializable{
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private static final String TAG = "NewsFragment.class";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    @Bind(R.id.fragment_content_tab)
    TabLayout mTabLayout;
    @Bind(R.id.fragment_content_viewpage)
    ViewPager mViewPager;
    private ViewPagerAdapter mTopViewPagerAdapter;
    private List<String> mTitleName = new ArrayList<>();
    private List<NewsTabInfo.MsgEntity.ListEntity> mTitleList = new ArrayList<>();
    private List<BaseFragment> fragmentList = new ArrayList<>();
    private Bundle bundle;

    public NewsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment NewsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static NewsFragment newInstance(Bundle bundle) {
        NewsFragment fragment = new NewsFragment();
//        Bundle args = new Bundle();
//        args.putString(ARG_PARAM1, param1);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            this.bundle = getArguments();

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_news, container, false);
        ButterKnife.bind(this, view);
        setupTabLayout();
        return view;
    }

    private void setupTabLayout() {
        mTabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        NewsTabInfo newsTabInfo = (NewsTabInfo) bundle.getSerializable("NewsTab");
        mTitleList = newsTabInfo.getMsg().getList();
        Log.i(TAG, "setupTabLayout: "+mTitleList);
        for(NewsTabInfo.MsgEntity.ListEntity title : mTitleList){
            mTitleName.add(title.getName());
            Log.i(TAG, "setupTabLayout: "+mTitleName);
        }
        fragmentList.add(NewsTopFragment.newInstance(bundle));
        fragmentList.add(NewsVideoFragment.newInstance(bundle));
        fragmentList.add(NewsEventFragment.newInstance(null, null));
        fragmentList.add(NewsTechFragment.newInstance());
        fragmentList.add(NewsStarFragment.newInstance(null,null));
        fragmentList.add(NewsEquipFragment.newInstance(bundle));
        fragmentList.add(NewsHealthFragment.newInstance(bundle));
//        注释
        setupViewPager();
    }

    private void setupViewPager() {
        mTopViewPagerAdapter = new ViewPagerAdapter(getActivity().getSupportFragmentManager(),fragmentList,mTitleList);
        mViewPager.setAdapter(mTopViewPagerAdapter);
        mTabLayout.setupWithViewPager(mViewPager);
    }
}
