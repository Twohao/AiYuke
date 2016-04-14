package com.riyue.aiyuke.ui.fragment;


import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;
import com.bigkoo.convenientbanner.holder.Holder;
import com.facebook.drawee.view.SimpleDraweeView;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.riyue.aiyuke.R;
import com.riyue.aiyuke.adapter.NewsEventAdapter;
import com.riyue.aiyuke.adapter.NewsTechAdapter;
import com.riyue.aiyuke.bean.NewsEventInfo;
import com.riyue.aiyuke.bean.NewsTechInfo;
import com.riyue.aiyuke.tools.http.HttpUtils;
import com.riyue.aiyuke.tools.http.URLConfig;
import com.riyue.aiyuke.tools.parsejson.JsonTool;
import com.riyue.aiyuke.ui.BaseFragment;
import com.zhy.http.okhttp.callback.StringCallback;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import okhttp3.Call;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link NewsEventFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class NewsEventFragment extends BaseFragment {

    @Bind(R.id.news_event_recycler_view)
    XRecyclerView mRecycleView;
    private List<NewsEventInfo.MsgEntity.PastEntity> mEventList = new ArrayList<>();
    private List<NewsEventInfo.MsgEntity.ListEntity.FutureEntity> mEventFutureData = new ArrayList<>();
    private List<NewsEventInfo.MsgEntity.ListEntity> mEventData = new ArrayList<>();

    private NewsEventAdapter newsEventAdapter;
    private Context mContext;
    private View headerView;
    private HeaderViewHolder headerViewHolder;
    private List<String> mBannerDataList = new ArrayList<>();
    private NewsEventInfo newsEventInfo;

    public NewsEventFragment() {
        // Required empty public constructor
    }

    public static NewsEventFragment newInstance(String param1, String param2) {
        NewsEventFragment fragment = new NewsEventFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = getActivity();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_news_event, container, false);
        ButterKnife.bind(this, view);

        setupHeaderView();
        initData();
        mRecycleView.addHeaderView(headerView);

        return view;
    }

    private void setupHeaderView() {
        headerView = LayoutInflater.from(mContext).inflate(R.layout.head_event_recycleview, null);
        headerViewHolder = new HeaderViewHolder(headerView);
    }

    private void initData() {
        setupHeaderView();
        //赛事模块数据
        HttpUtils.get(URLConfig.NEWS_MATCH_JSON, new StringCallback() {
            @Override
            public void onError(Call call, Exception e) {
            }

            //请求数据
            @Override
            public void onResponse(String response) {
                newsEventInfo = JsonTool.parseJson2Object(response, NewsEventInfo.class);
                mEventList.addAll(newsEventInfo.getMsg().getPast());
                mEventFutureData = newsEventInfo.getMsg().getList().getFuture();
                mEventData.add(newsEventInfo.getMsg().getList());
                newsEventAdapter = new NewsEventAdapter(getActivity(), mEventList);
                mRecycleView.setLayoutManager(new GridLayoutManager(mContext, 2, GridLayoutManager.VERTICAL, false));
                mRecycleView.setAdapter(newsEventAdapter);
                newsEventAdapter.notifyDataSetChanged();
                bannerData();
//                textViewData();
            }

            public void bannerData() {
                for (int i = 0; i < 5; i++) {
                    mBannerDataList.add(mEventList.get(i).getImage());
                }
                headerViewHolder.convenientBanner.setPages(new CBViewHolderCreator() {
                    @Override
                    public Object createHolder() {
                        return new BannerViewHolder();
                    }
                }, mBannerDataList)
                        .setPageIndicator(new int[]{R.drawable.indicator_gray_shape, R.drawable.indicator_green_shape})
                        .setPageIndicatorAlign(ConvenientBanner.PageIndicatorAlign.CENTER_HORIZONTAL);
            }

        });
    }
            private void textViewData() {
                //Object o = mEventData.get(0).getNow().get(0);
                //headerViewHolder.news_event_first_left_tv1.setText();
                //headerViewHolder.news_event_first_left_tv2.setText(mEventFutureData.get(0).getState());
                //headerViewHolder.news_event_first_right_tv1.setText(mEventData.getNow().get(0));
                headerViewHolder.news_event_mid_left_tv1.setText(mEventFutureData.get(0).getShow_day());
                headerViewHolder.news_event_mid_left_tv2.setText(mEventFutureData.get(0).getLivetime_date());
                headerViewHolder.news_event_mid_right_tv1.setText(mEventFutureData.get(0).getLiving_title());
                headerViewHolder.news_event_mid_right_tv2.setText("时间:"+mEventFutureData.get(0).getDate_range());

                headerViewHolder.news_event_third_left_tv1.setText(mEventFutureData.get(1).getShow_day());
                headerViewHolder.news_event_third_left_tv2.setText(mEventFutureData.get(1).getLivetime_date());
                headerViewHolder.news_event_third_right_tv1.setText(mEventFutureData.get(1).getLiving_title());
                headerViewHolder.news_event_third_right_tv2.setText("时间:"+mEventFutureData.get(1).getDate_range());


            }


    class BannerViewHolder implements Holder<String> {

        private SimpleDraweeView mBannerImageView;

        @Override
        public View createView(Context context) {
            mBannerImageView = new SimpleDraweeView(mContext);
            mBannerImageView.setScaleType(SimpleDraweeView.ScaleType.CENTER_CROP);
            return mBannerImageView;
        }

        @Override
        public void UpdateUI(Context context, int position, String data) {
            mBannerImageView.setImageURI(Uri.parse(data));
        }
    }

    class HeaderViewHolder {
        @Bind(R.id.news_tech_convenientbanner)
        ConvenientBanner convenientBanner;
        @Bind(R.id.news_event_first_left_tv1)
        TextView news_event_first_left_tv1;
        @Bind(R.id.news_event_first_left_tv2)
        TextView news_event_first_left_tv2;
        @Bind(R.id.news_event_first_right_tv1)
        TextView news_event_first_right_tv1;
        @Bind(R.id.news_event_first_right_tv2)
        TextView news_event_first_right_tv2;
        @Bind(R.id.news_event_first_tuiguang)
        TextView news_event_first_tuiguang;

        @Bind(R.id.news_event_mid_left_tv1)
        TextView news_event_mid_left_tv1;
        @Bind(R.id.news_event_mid_left_tv2)
        TextView news_event_mid_left_tv2;
        @Bind(R.id.news_event_mid_right_tv1)
        TextView news_event_mid_right_tv1;
        @Bind(R.id.news_event_mid_right_tv2)
        TextView news_event_mid_right_tv2;
        @Bind(R.id.news_event_mid_tuiguang)
        TextView news_event_mid_tuiguang;

        @Bind(R.id.news_event_third_left_tv1)
        TextView news_event_third_left_tv1;
        @Bind(R.id.news_event_third_left_tv2)
        TextView news_event_third_left_tv2;
        @Bind(R.id.news_event_third_right_tv1)
        TextView news_event_third_right_tv1;
        @Bind(R.id.news_event_third_right_tv2)
        TextView news_event_third_right_tv2;
        @Bind(R.id.news_event_third_tuiguang)
        TextView news_event_third_tuiguang;


        public HeaderViewHolder(View headerView) {
            ButterKnife.bind(this, headerView);
        }
    }
}
