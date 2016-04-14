package com.riyue.aiyuke.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.ScrollView;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshScrollView;
import com.riyue.aiyuke.R;
import com.riyue.aiyuke.adapter.ClubChildExpandAdapter;
import com.riyue.aiyuke.bean.ClubSportsInfo;
import com.riyue.aiyuke.tools.http.HttpUtils;
import com.riyue.aiyuke.tools.http.URLConfig;
import com.riyue.aiyuke.tools.log.LogTool;
import com.riyue.aiyuke.tools.parsejson.JsonTool;
import com.riyue.aiyuke.ui.AiYusheApplication;
import com.riyue.aiyuke.ui.BaseFragment;
import com.riyue.aiyuke.ui.activity.ClubDetailsActivity;
import com.riyue.aiyuke.ui.activity.ClubSportsDetailsActivity;
import com.riyue.aiyuke.ui.expandablelistview.CustomExpandableListView;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import okhttp3.Call;

/**
 * @author xyh
 * @date  2016/4/5
 *
 * 俱乐部子模块
 */
public class ClubChildFragment extends BaseFragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String PAREM = "url";
    private List<ClubSportsInfo.MsgEntity.NearbyEntity> entityList = new ArrayList<>();
    private List<Integer> keyList = new ArrayList<>();

    private ClubChildExpandAdapter mAdapter;

    private String mParam1;
    @Bind(R.id.club_child_expand)
    CustomExpandableListView mExpandableListView;
    @Bind(R.id.club_child_scrollview)
    PullToRefreshScrollView mScrollView;



    public ClubChildFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @return A new instance of fragment ClubChildFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ClubChildFragment newInstance(String param1) {
        ClubChildFragment fragment = new ClubChildFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_club_child, container, false);
        ButterKnife.bind(this,view);
        initExpandListView();

        setupListener();
        return view;
    }

    /**
     * 设置监听
     */
    private void setupListener() {
        mExpandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                String url = URLConfig.CLUB_SPORTS_DETAILS_JSON + entityList.get(childPosition).getActivity_id() + "/?appPlatform=ANDROID&uid=276452&ssotoken=36dcf6f5facc81ad1a0ef694919a1e89ad6df06027fb41a888a6";
                Intent intent = new Intent(getActivity(), ClubDetailsActivity.class);
                intent.putExtra(PAREM, url);
                startActivity(intent);
                return true;
            }
        });
        mExpandableListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
                String url = URLConfig.CLUB_SPORTS_HEADER_JSON + entityList.get(groupPosition).getCid();
                Intent intent = new Intent(getActivity(), ClubSportsDetailsActivity.class);
                intent.putExtra(PAREM, url);
                startActivity(intent);
                return true;
            }
        });
        mScrollView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ScrollView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ScrollView> refreshView) {
                initData();
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ScrollView> refreshView) {

            }
        });
    }

    /**
     * 初始化ExpandListView视图
     */
    private void initExpandListView() {

        initData();
        mAdapter = new ClubChildExpandAdapter(entityList,keyList,getActivity());
        mExpandableListView.setAdapter(mAdapter);

        //ExpandableListView点击不收缩
        mExpandableListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
                return true;
            }
        });
    }

    /**
     * 初始化数据
     */
    private void initData() {
        AiYusheApplication application = new AiYusheApplication();
        String lon = String.valueOf(application.lon);
        String lat = String.valueOf(application.lat);
        String url = URLConfig.CLUB_SPORTS_JSON +mParam1+"&city="+ application.city+"&lng="+lon+"&lat="+lat+"&page=1";
        HttpUtils.get(url, new StringCallback() {
            @Override
            public void onError(Call call, Exception e) {

            }

            @Override
            public void onResponse(String response) {
                ClubSportsInfo clubSportsInfo = JsonTool.parseJson2Object(response, ClubSportsInfo.class);
                if (entityList.isEmpty()) {
                    entityList.addAll(clubSportsInfo.getMsg().getNearby());
                    for (int i = 0; i < entityList.size(); i++) {
                        keyList.add(entityList.get(i).getCid());
                    }
                }
                mAdapter.notifyDataSetChanged();
                //默认展开所有Group
                for (int i = 0; i < mAdapter.getGroupCount(); i++) {
                    mExpandableListView.expandGroup(i);
                }
                mScrollView.onRefreshComplete();
            }
        });
    }


}
