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
import com.riyue.aiyuke.adapter.NewsRecyclerAdapter;
import com.riyue.aiyuke.bean.NewsTechInfo;
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
 * Use the {@link NewsEquipFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
//注释
public class NewsEquipFragment extends BaseFragment {
    @Bind(R.id.equip_news_fragment_recycler)
    XRecyclerView mRecyclerView;
    private Bundle bundle;

    private Context mContext;
    private ViewGroup viewGroup;

    private List<NewsTechInfo.MsgEntity.NewsListEntity> mEquipList = new ArrayList<>();
    private NewsRecyclerAdapter mRecyclerAdapter;
    private int page=1;
    private View headView;
    private  List<NewsTechInfo.MsgEntity.SlideImageEntity> imageEntityList=new ArrayList<>();
    private NewsTechInfo newsEquipInfo;
    private ViewHolderBanner viewHolderBanner;

    public NewsEquipFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment NewsEquipFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static NewsEquipFragment newInstance(Bundle bundle) {
        NewsEquipFragment fragment = new NewsEquipFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            this.bundle = getArguments();
        }
        mContext=getActivity();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_news_equip, container, false);
        ButterKnife.bind(this, view);
        viewGroup=container;
        initBanner();
        initRecycler();
        initData();
        return view;
    }


    private void initBanner() {
        imageEntityList.clear();
        headView = LayoutInflater.from(mContext).inflate(R.layout.header_banner, viewGroup, false);
         viewHolderBanner = new ViewHolderBanner(headView);

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

    class ViewHolderBanner {
        @Bind(R.id.header_banner)
        ConvenientBanner convenientBanner;

        public ViewHolderBanner(View view) {
            ButterKnife.bind(this, view);
        }
    }

    private void initRecycler() {
        mEquipList.clear();
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        mRecyclerAdapter = new NewsRecyclerAdapter(mContext, mEquipList);
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

        mRecyclerView.setRefreshProgressStyle(AVLoadingIndicatorView.BallSpinFadeLoader);
        mRecyclerView.setLaodingMoreProgressStyle(AVLoadingIndicatorView.BallSpinFadeLoader);
    }

    private void initData() {
        HttpUtils.get(URLConfig.NEWS_EQUIP_JSON+page, new StringCallback() {
            @Override
            public void onError(Call call, Exception e) {
            }

            @Override
            public void onResponse(String response) {
                NewsTechInfo newsEquipInfo = JsonTool.parseJson2Object(response, NewsTechInfo.class);
                mEquipList.addAll(newsEquipInfo.getMsg().getNewsList());
                mRecyclerAdapter.notifyDataSetChanged();
                imageEntityList.addAll(newsEquipInfo.getMsg().getSlideImage());
                viewHolderBanner.convenientBanner.notifyDataSetChanged();
            }
        });
    }


    private void downloadJson(final boolean isLoadMore) {
        HttpUtils.get(URLConfig.NEWS_EQUIP_JSON + page, new StringCallback() {
            @Override
            public void onError(Call call, Exception e) {

            }

            @Override
            public void onResponse(String response) {
                newsEquipInfo = JsonTool.parseJson2Object(response, NewsTechInfo.class);
                List<NewsTechInfo.MsgEntity.NewsListEntity> newslist = newsEquipInfo.getMsg().getNewsList();
                if (!isLoadMore) {
                    mEquipList.clear();
                }
                mEquipList.addAll(newslist);
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
}
