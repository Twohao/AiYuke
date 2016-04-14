package com.riyue.aiyuke.ui.fragment;


import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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
import com.riyue.aiyuke.adapter.NewsHeadLineRecyclerAdapter;
import com.riyue.aiyuke.bean.NewsHeadlineInfo;
import com.riyue.aiyuke.tools.http.HttpUtils;
import com.riyue.aiyuke.tools.http.URLConfig;
import com.riyue.aiyuke.tools.parsejson.JsonTool;
import com.riyue.aiyuke.ui.BaseFragment;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import okhttp3.Call;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link NewsTopFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class NewsTopFragment extends BaseFragment {

    @Bind(R.id.news_top_fragment_recycler)
    XRecyclerView mRecyclerView;
    private Context mContext;
    private List<NewsHeadlineInfo.MsgEntity.NewslistEntity> mNewsList = new ArrayList<>();
    private Bundle mBundle;
    //    private NewsHeadlineAdapter newsHeadlineAdapter;
    private NewsHeadLineRecyclerAdapter mRecyclerAdapter;

    private int page = 1;
    private ViewGroup viewGroup;
    private NewsHeadlineInfo newsHeadlineInfo;
    private View headView;
    private List<NewsHeadlineInfo.MsgEntity.SlideImageEntity> imageEntityList=new ArrayList<>();


    public NewsTopFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment NewsTopFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static NewsTopFragment newInstance(Bundle mBundle) {
        NewsTopFragment fragment = new NewsTopFragment();
        fragment.setArguments(mBundle);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            this.mBundle = getArguments();
            newsHeadlineInfo = (NewsHeadlineInfo) mBundle.getSerializable("NewsHeadline");

        }
        mContext = getActivity();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        this.viewGroup = container;
        View view = inflater.inflate(R.layout.fragment_top, container, false);
        ButterKnife.bind(this, view);
        initBanner();
        initData();
        return view;
    }

    private void initBanner() {
        imageEntityList.clear();
        headView = LayoutInflater.from(mContext).inflate(R.layout.header_banner, viewGroup, false);
        ViewHolderBanner viewHolderBanner = new ViewHolderBanner(headView);

         imageEntityList = newsHeadlineInfo.getMsg().getSlideImage();

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

    class LocalImageHolderView implements Holder<NewsHeadlineInfo.MsgEntity.SlideImageEntity> {
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
        public void UpdateUI(Context context, int position, NewsHeadlineInfo.MsgEntity.SlideImageEntity data) {
            mSimpleDraweeView.setImageURI(Uri.parse(data.getImage()));
        }
    }

    class ViewHolderBanner {
        @Bind(R.id.header_banner)
        ConvenientBanner convenientBanner;

        public ViewHolderBanner(View view) {
            ButterKnife.bind(this, view);
        }
    }

    //注释
    private void initData() {
        mNewsList.clear();

        mNewsList.addAll(newsHeadlineInfo.getMsg().getNewslist());
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        mRecyclerAdapter = new NewsHeadLineRecyclerAdapter(mContext, mNewsList);
        if(headView!=null) {
            mRecyclerView.addHeaderView(headView);
        }
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
        mRecyclerAdapter.setOnItemClickListener(new NewsHeadLineRecyclerAdapter.OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View view, NewsHeadlineInfo.MsgEntity.NewslistEntity data) {
                Intent intent = new Intent();
                intent.setClassName("com.riyue.aiyuke", "com.riyue.aiyuke.ui.activity.WebActivity");
                intent.putExtra("url", data.getUrl());
                startActivity(intent);
            }
        });

        mRecyclerView.setRefreshProgressStyle(AVLoadingIndicatorView.BallSpinFadeLoader);
        mRecyclerView.setLaodingMoreProgressStyle(AVLoadingIndicatorView.BallSpinFadeLoader);
    }

    private void downloadJson(final boolean isLoadMore) {
        HttpUtils.get(URLConfig.NEWS_HEADLINE_JSON + page, new StringCallback() {
            @Override
            public void onError(Call call, Exception e) {

            }

            @Override
            public void onResponse(String response) {
                NewsHeadlineInfo newsHeadlineInfo = JsonTool.parseJson2Object(response, NewsHeadlineInfo.class);
                List<NewsHeadlineInfo.MsgEntity.NewslistEntity> newslist = newsHeadlineInfo.getMsg().getNewslist();
                if (!isLoadMore) {
                    mNewsList.clear();
                }
                mNewsList.addAll(newslist);
                if (mRecyclerAdapter != null) {
                    mRecyclerAdapter.notifyDataSetChanged();
                }
                if(isLoadMore){
                    mRecyclerView.loadMoreComplete();
                }else {
                    mRecyclerView.refreshComplete();
                }
            }
        });
    }

}
