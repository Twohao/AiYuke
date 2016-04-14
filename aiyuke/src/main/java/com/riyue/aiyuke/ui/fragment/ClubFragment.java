package com.riyue.aiyuke.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.gugalor.citylist.CityList;
import com.riyue.aiyuke.R;
import com.riyue.aiyuke.adapter.ClubTabLayoutAdapter;
import com.riyue.aiyuke.bean.ClubDateInfo;
import com.riyue.aiyuke.tools.log.LogTool;
import com.riyue.aiyuke.ui.BaseFragment;
import com.riyue.aiyuke.ui.activity.NearGymnasiumActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author mtf
 * @date 2016/4/2
 * <p/>
 * 主界面的俱乐部模块
 */
public class ClubFragment extends BaseFragment {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private ClubDateInfo mParam1;
    private List<ClubDateInfo.MsgEntity> entityList = new ArrayList<>();
    private List<BaseFragment> fragmentList = new ArrayList<>();
    private ClubTabLayoutAdapter mAdapter;
    @Bind(R.id.club_tab_layout)
    TabLayout mTabLayout;
    @Bind(R.id.club_view_pager)
    ViewPager mViewPager;
    @Bind(R.id.club_city)
    TextView tvCity;


    public ClubFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment ClubFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ClubFragment newInstance(Bundle bundle) {
        ClubFragment fragment = new ClubFragment();
        Bundle args = bundle;
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = (ClubDateInfo) getArguments().getSerializable("ClubDate");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_club, container, false);
        ButterKnife.bind(this, view);
        initView();
        return view;
    }

    /**
     * 初始化视图
     */
    private void initView() {
        mTabLayout.setTabMode(TabLayout.MODE_FIXED);
        entityList.addAll(mParam1.getMsg());
        for (int i = 0; i < entityList.size(); i++) {
            fragmentList.add(ClubChildFragment.newInstance(mParam1.getMsg().get(i).getSubmit()));
        }
        mAdapter = new ClubTabLayoutAdapter(entityList, fragmentList, getActivity().getSupportFragmentManager());
        mViewPager.setAdapter(mAdapter);
        //tablayout与viewpager关联
        mTabLayout.setupWithViewPager(mViewPager);
    }


    @OnClick({R.id.club_gymnasium, R.id.club_city})
    public void onClick(View view) {
        Intent intent = new Intent();
        switch (view.getId()) {
            case R.id.club_gymnasium:
                intent.setClass(getActivity(), NearGymnasiumActivity.class);
                startActivity(intent);
                break;
            case R.id.club_city:
                intent.setClass(getActivity(), CityList.class);
                startActivityForResult(intent,1);
                break;
        }

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (data != null)
            switch (resultCode) {
                case 2:
                    tvCity.setText(data.getStringExtra("city"));
                    break;

                default:
                    break;
            }
    }


}
