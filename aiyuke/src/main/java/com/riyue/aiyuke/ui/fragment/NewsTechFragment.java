package com.riyue.aiyuke.ui.fragment;


import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RadioGroup;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;
import com.bigkoo.convenientbanner.holder.Holder;
import com.bigkoo.convenientbanner.listener.OnItemClickListener;
import com.facebook.drawee.drawable.ScalingUtils;
import com.facebook.drawee.generic.GenericDraweeHierarchy;
import com.facebook.drawee.generic.GenericDraweeHierarchyBuilder;
import com.facebook.drawee.view.SimpleDraweeView;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.jcodecraeer.xrecyclerview.progressindicator.AVLoadingIndicatorView;
import com.riyue.aiyuke.R;
import com.riyue.aiyuke.adapter.NewsRecyclerAdapter;
import com.riyue.aiyuke.bean.NewsTechInfo;
import com.riyue.aiyuke.tools.http.HttpUtils;
import com.riyue.aiyuke.tools.http.URLConfig;
import com.riyue.aiyuke.tools.log.LogTool;
import com.riyue.aiyuke.tools.parsejson.JsonTool;
import com.riyue.aiyuke.ui.BaseFragment;
import com.riyue.aiyuke.ui.activity.MainActivity;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link NewsTechFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class NewsTechFragment extends BaseFragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private NewsFragment mParam1;
    private String mParam2;

    private Context mContext;
    private int page = 1;


    @Bind(R.id.news_tech_ll)
    LinearLayout mLinearLayout;

    @Bind(R.id.news_tech_radiogroup)
    RadioGroup mRadioGroup;

    @Bind(R.id.news_tech_recyclerview)
    XRecyclerView mRecyclerView;
    private List<NewsTechInfo.MsgEntity.NewsListEntity> mTechList = new ArrayList<>();
    //    private NewsTechAdapter newsTechAdapter;
    private NewsRecyclerAdapter mRecyclerAdapter;
    private View headView;
    private ViewGroup viewGroup;
    private NewsTechInfo newsTechInfo;

    private List<NewsTechInfo.MsgEntity.SlideImageEntity> imageEntityList = new ArrayList<>();
    private ViewHolderHead viewHolderBanner;
    private int tabLayoutHeight;
    private int statusBarHeight;

    public NewsTechFragment() {
    }

    public static NewsTechFragment newInstance() {
        NewsTechFragment fragment = new NewsTechFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MainActivity activity = (MainActivity) getActivity();
        NewsFragment newsFragment = activity.getNewsFragment();
        tabLayoutHeight = newsFragment.mTabLayout.getHeight();
        statusBarHeight = getStatusBarHeight();
        mContext = getActivity();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_news_tech, container, false);
        ButterKnife.bind(this, view);
        viewGroup = container;
        initHeader();
        initRecyclerView();
        initData();
        return view;
    }

    private void initHeader() {
        imageEntityList.clear();
        headView = LayoutInflater.from(mContext).inflate(R.layout.header_teach_fragment, viewGroup, false);
        viewHolderBanner = new ViewHolderHead(headView);


        //自定义你的Holder，实现更多复杂的界面，不一定是图片翻页，其他任何控件翻页亦可。
        viewHolderBanner.convenientBanner.setPages(
                new CBViewHolderCreator<LocalImageHolderView>() {
                    @Override
                    public LocalImageHolderView createHolder() {
                        return new LocalImageHolderView();
                    }
                }, imageEntityList)
                //设置两个点图片作为翻页指示器，不设置则没有指示器，可以根据自己需求自行配合自己的指示器,不需要圆点指示器可用不设
                .setPageIndicator(new int[]{R.drawable.indicator_gray_shape, R.drawable.indicator_green_shape})
                        //设置指示器的方向
                .setPageIndicatorAlign(ConvenientBanner.PageIndicatorAlign.CENTER_HORIZONTAL);
        viewHolderBanner.convenientBanner.startTurning(3000);
        //设置翻页的效果，不需要翻页效果可用不设
        //.setPageTransformer(Transformer.DefaultTransformer);    集成特效之后会有白屏现象，新版已经分离，如果要集成特效的例子可以看Demo的点击响应。
//        convenientBanner.setManualPageable(false);//设置不能手动影响
        viewHolderBanner.convenientBanner.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Intent intent = new Intent();
                intent.setClassName("com.riyue.aiyuke", "com.riyue.aiyuke.ui.activity.WebActivity");
                intent.putExtra("url", imageEntityList.get(position).getUrl());
                startActivity(intent);
            }
        });

    }

    class LocalImageHolderView implements Holder<NewsTechInfo.MsgEntity.SlideImageEntity> {
        private SimpleDraweeView mSimpleDraweeView;

        @Override
        public View createView(Context context) {
            mSimpleDraweeView = new SimpleDraweeView(mContext);
            GenericDraweeHierarchy genericDraweeHierarchy = GenericDraweeHierarchyBuilder.newInstance(mContext.getResources())
                    .setActualImageScaleType(ScalingUtils.ScaleType.FIT_XY).build();
            mSimpleDraweeView.setHierarchy(genericDraweeHierarchy);
            return mSimpleDraweeView;
        }

        @Override
        public void UpdateUI(Context context, int position, NewsTechInfo.MsgEntity.SlideImageEntity data) {
            mSimpleDraweeView.setImageURI(Uri.parse(data.getImage()));
        }
    }

    class ViewHolderHead {
        @Bind(R.id.news_tech_convenientbanner)
        ConvenientBanner convenientBanner;
        @Bind(R.id.news_tech_radiogroup)
        RadioGroup mRadioGroup;

        public ViewHolderHead(View view) {
            ButterKnife.bind(this, view);
        }
    }


    private void initRecyclerView() {
        mTechList.clear();
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        mRecyclerAdapter = new NewsRecyclerAdapter(mContext, mTechList);
        mRecyclerView.addHeaderView(headView);
        mRecyclerView.setAdapter(mRecyclerAdapter);
        mRecyclerView.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                page = 1;
                downloadJson(false);
            }

            @Override
            public void onLoadMore() {
                page++;
                downloadJson(true);
            }
        });
        mRecyclerAdapter.setOnItemClickListener(new NewsRecyclerAdapter.OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View view, NewsTechInfo.MsgEntity.NewsListEntity data) {
                Intent intent = new Intent();
                intent.setClassName("com.riyue.aiyuke", "com.riyue.aiyuke.ui.activity.WebActivity");
                intent.putExtra("url", data.getUrl());
                startActivity(intent);
            }
        });

        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                int[] locationRG = new int[2];
                viewHolderBanner.mRadioGroup.getLocationOnScreen(locationRG);
                int rgY = locationRG[1];
                LogTool.LOG_E(NewsTechFragment.class, "=============1-------" + rgY);
                LogTool.LOG_E(NewsTechFragment.class, "=============2----------" + statusBarHeight);
                if (rgY - tabLayoutHeight - statusBarHeight <= 0) {
                    mLinearLayout.setVisibility(View.VISIBLE);
                } else {
                    mLinearLayout.setVisibility(View.GONE);
                }
            }
        });

        mRecyclerView.setRefreshProgressStyle(AVLoadingIndicatorView.BallSpinFadeLoader);
        mRecyclerView.setLaodingMoreProgressStyle(AVLoadingIndicatorView.BallSpinFadeLoader);
    }

    private void downloadJson(final boolean isLoadMore) {
        HttpUtils.get(URLConfig.NEWS_TEACH_JSON + page, new StringCallback() {
            @Override
            public void onError(Call call, Exception e) {

            }

            @Override
            public void onResponse(String response) {
                newsTechInfo = JsonTool.parseJson2Object(response, NewsTechInfo.class);
                List<NewsTechInfo.MsgEntity.NewsListEntity> newslist = newsTechInfo.getMsg().getNewsList();
                if (!isLoadMore) {
                    mTechList.clear();
                }
                mTechList.addAll(newslist);
                if (mRecyclerAdapter != null) {
                    mRecyclerAdapter.notifyDataSetChanged();
                }
                if (isLoadMore) {
                    mRecyclerView.loadMoreComplete();
                } else {
                    mRecyclerView.refreshComplete();
                }
            }
        });
    }

    private void initData() {
        //教学模块数据
        HttpUtils.get(URLConfig.NEWS_TEACH_JSON + "1", new StringCallback() {
            @Override
            public void onError(Call call, Exception e) {

            }

            //请求数据
            @Override
            public void onResponse(String response) {
                NewsTechInfo newsTechInfo = JsonTool.parseJson2Object(response, NewsTechInfo.class);
                mTechList.addAll(newsTechInfo.getMsg().getNewsList());
                mRecyclerAdapter.notifyDataSetChanged();
                imageEntityList.addAll(newsTechInfo.getMsg().getSlideImage());
                viewHolderBanner.convenientBanner.notifyDataSetChanged();
//                listView.setAdapter(newsTechAdapter);
//                newsTechAdapter.notifyDataSetChanged();
            }
        });
    }

    @OnClick({R.id.tech_rb1, R.id.tech_rb2})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tech_rb1:
                break;
            case R.id.tech_rb2:
                break;
        }
    }


    /**
     * 获取顶部状态栏高度
     *
     * @return
     */
    private int getStatusBarHeight() {
        Resources resources = getActivity().getResources();
        int resourceId = resources.getIdentifier("status_bar_height", "dimen", "android");
        int height = resources.getDimensionPixelSize(resourceId);
        Log.v("dbw", "Status height:" + height);
        return height;
    }
}
