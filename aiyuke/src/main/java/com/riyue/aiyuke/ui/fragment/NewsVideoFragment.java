package com.riyue.aiyuke.ui.fragment;


import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.TextView;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;
import com.bigkoo.convenientbanner.holder.Holder;
import com.facebook.drawee.drawable.ScalingUtils;
import com.facebook.drawee.generic.GenericDraweeHierarchy;
import com.facebook.drawee.generic.GenericDraweeHierarchyBuilder;
import com.facebook.drawee.view.SimpleDraweeView;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.riyue.aiyuke.R;
import com.riyue.aiyuke.adapter.NewsEventAdapter;
import com.riyue.aiyuke.adapter.NewsVedioAdapter;
import com.riyue.aiyuke.adapter.VedioExpandViewListAdapter;
import com.riyue.aiyuke.bean.NewsEventInfo;
import com.riyue.aiyuke.bean.NewsVideoInfo;
import com.riyue.aiyuke.tools.http.HttpUtils;
import com.riyue.aiyuke.tools.http.URLConfig;
import com.riyue.aiyuke.tools.parsejson.JsonTool;
import com.riyue.aiyuke.ui.BaseFragment;
import com.zhy.http.okhttp.callback.StringCallback;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import okhttp3.Call;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link NewsVideoFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class NewsVideoFragment extends BaseFragment {
    private Bundle bundle;
    @Bind(R.id.vedio_expand_lv)
    ExpandableListView mExpandableListView;


    private List<NewsVideoInfo.MsgEntity.VideoEntity> mVedioList = new ArrayList<>();
    private List<String> mVedioTitle = new ArrayList<>();
    private Map<String,List<NewsVideoInfo.MsgEntity.VideoEntity>> mExpandMapData = new HashMap<>();
    private NewsVideoInfo.MsgEntity.VideoEntity videoEntity;

    private VedioExpandViewListAdapter newsVedioAdapter;
    private NewsVideoInfo newsVideoInfo;
    private Context mContext;
    private View headView;

    public NewsVideoFragment() {
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment NewsVideoFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static NewsVideoFragment newInstance(Bundle bundle) {
        NewsVideoFragment fragment = new NewsVideoFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            this.bundle = getArguments();
            newsVideoInfo = (NewsVideoInfo) bundle.getSerializable("NewsVideo");
        }
        mContext = getActivity();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_news_video, container, false);
        ButterKnife.bind(this, view);
        initBanner();
        initView();
        return view;
    }

    private void initView(){
        mVedioTitle.clear();
        mExpandMapData.clear();
        //获取数据
        if(newsVideoInfo!=null) {
            for (int i = 0; i < newsVideoInfo.getMsg().size(); i++) {
                List<NewsVideoInfo.MsgEntity.VideoEntity> entityList = newsVideoInfo.getMsg().get(i).getVideo();
                String key = newsVideoInfo.getMsg().get(i).getTitle();
                mExpandMapData.put(key, entityList);
                mVedioTitle.add(newsVideoInfo.getMsg().get(i).getTitle());
            }
        }
        newsVedioAdapter = new VedioExpandViewListAdapter(getActivity(), mVedioTitle,mExpandMapData);
        for (int i = 0; i <mVedioList.size();i++){
            Log.i("chen6", "initView: "+i);
            mExpandableListView.expandGroup(i);
        }

        //关联适配i
        mExpandableListView.setAdapter(newsVedioAdapter);
        for (int j = 0; j <newsVideoInfo.getMsg().size();j++){
            mExpandableListView.expandGroup(j);
        }
        mExpandableListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView expandableListView, View view, int i, long l) {
                return true;
            }
        });

    }
    private void initBanner() {
        headView = LayoutInflater.from(mContext).inflate(R.layout.header_banner, null);
        ViewHolderBanner viewHolderBanner = new ViewHolderBanner(headView);
        if (newsVideoInfo.getMsg().get(0) != null) {
            List<NewsVideoInfo.MsgEntity.SpecialEntity> specialEntities = newsVideoInfo.getMsg().get(0).getSpecial();
            //自定义你的Holder，实现更多复杂的界面，不一定是图片翻页，其他任何控件翻页亦可。
            viewHolderBanner.convenientBanner.setPages(
                    new CBViewHolderCreator<LocalImageHolderView>() {
                        @Override
                        public LocalImageHolderView createHolder() {
                            return new LocalImageHolderView();
                        }
                    }, specialEntities)
                    //设置两个点图片作为翻页指示器，不设置则没有指示器，可以根据自己需求自行配合自己的指示器,不需要圆点指示器可用不设
                    .setPageIndicator(new int[]{R.drawable.indicator_gray_shape, R.drawable.indicator_green_shape})
                            //设置指示器的方向
                    .setPageIndicatorAlign(ConvenientBanner.PageIndicatorAlign.CENTER_HORIZONTAL);
            viewHolderBanner.convenientBanner.startTurning(3000);
        }
    }
    //设置翻页的效果，不需要翻页效果可用不设
    //.setPageTransformer(Transformer.DefaultTransformer);
    // 集成特效之后会有白屏现象，新版已经分离，如果要集成特效的例子可以看Demo的点击响应。
//      //  convenientBanner.setManualPageable(false);//设置不能手动影响


    //}

    class LocalImageHolderView implements Holder<NewsVideoInfo.MsgEntity.SpecialEntity> {
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
        public void UpdateUI(Context context, int position, NewsVideoInfo.MsgEntity.SpecialEntity data) {
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
}
