package com.riyue.aiyuke.ui.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import com.riyue.aiyuke.R;
import com.riyue.aiyuke.adapter.NewsStarAdapter;
import com.riyue.aiyuke.adapter.NewsStarSearchAdapter;
import com.riyue.aiyuke.adapter.NewsTechAdapter;
import com.riyue.aiyuke.bean.NewsStarInfo;
import com.riyue.aiyuke.bean.NewsTechInfo;
import com.riyue.aiyuke.tools.http.HttpUtils;
import com.riyue.aiyuke.tools.http.URLConfig;
import com.riyue.aiyuke.tools.listview.MyGridView;
import com.riyue.aiyuke.tools.parsejson.JsonTool;
import com.riyue.aiyuke.ui.BaseActivity;
import com.riyue.aiyuke.ui.BaseFragment;
import com.riyue.aiyuke.ui.activity.SearchActivity;
import com.riyue.aiyuke.ui.activity.StarUrlIntentActivity;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link NewsStarFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class NewsStarFragment extends BaseFragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    @Bind(R.id.news_star_MyGridView)
    MyGridView myGridViewStar;
    @Bind(R.id.news_star_search_MyGridView)
    MyGridView myGridViewSearch;
    private List<NewsStarInfo.MsgEntity.ListEntity> mStarList = new ArrayList<>();
    private String[] str = { "A", "B", "C", "D", "E", "F", "G", "H", "I",
                           "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V",
                           "W", "X", "Y", "Z", "其他" };

    private NewsStarAdapter newsStarAdapter;
    private NewsStarSearchAdapter newsStarSearchAdapter;
    public NewsStarFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment NewsStarFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static NewsStarFragment newInstance(String param1, String param2) {
        NewsStarFragment fragment = new NewsStarFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_news_star, container, false);
        ButterKnife.bind(this,view);
        initData();
        return view;
    }

    private void initData() {
        //球星模块数据
        HttpUtils.get(URLConfig.NEWS_PLAYER_JSON, new StringCallback() {
            @Override
            public void onError(Call call, Exception e) {

            }
            //请求数据
            @Override
            public void onResponse(String response) {
                NewsStarInfo newsStarInfo = JsonTool.parseJson2Object(response, NewsStarInfo.class);
                Log.i("chen2", "onResponse: NewsEquipInfo" + newsStarInfo.getMsg().getList());
                mStarList.addAll(newsStarInfo.getMsg().getList());
                newsStarAdapter = new NewsStarAdapter(getActivity(), mStarList);
                myGridViewStar.setAdapter(newsStarAdapter);
                newsStarAdapter.notifyDataSetChanged();
            }
        });
        newsStarSearchAdapter = new NewsStarSearchAdapter(getActivity(),str);
        myGridViewSearch.setAdapter(newsStarSearchAdapter);
        myGridViewSearch.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String num = str[i];
                Log.i("chen7", "onItemClick: num"+num);
                Intent intent = new Intent();
                intent.putExtra("num1",num);
                intent.setClass(getActivity(), StarUrlIntentActivity.class);
                startActivity(intent);
            }
        });
        newsStarSearchAdapter.notifyDataSetChanged();
    }

    @OnClick({R.id.news_star_search,R.id.news_star_category})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.news_star_search:
                Intent intent = new Intent();
                intent.setClass(getActivity(),SearchActivity.class);
                startActivity(intent);
                break;
            case R.id.news_star_category:
                break;
        }
    }
}
