package com.riyue.aiyuke.adapter;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.riyue.aiyuke.R;
import com.riyue.aiyuke.bean.NewsHeadlineInfo;
import com.riyue.aiyuke.tools.log.LogTool;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/4/5.
 */
public class NewsHeadlineAdapter extends BaseAdapter{
    private Context mContext;
    private List<NewsHeadlineInfo.MsgEntity.NewslistEntity> mNewsList = new ArrayList<>();

    public NewsHeadlineAdapter(Context mContext, List<NewsHeadlineInfo.MsgEntity.NewslistEntity> mNewsList) {
        this.mContext = mContext;
        this.mNewsList = mNewsList;
    }

    @Override
    public int getCount() {
        if (mNewsList == null) {
            return 0;
        } else {
            return this.mNewsList.size();
        }
    }

    @Override
    public Object getItem(int i) {
        if (mNewsList == null) {
            return null;
        } else {
            return this.mNewsList.get(i);
        }
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder =null;
        if (mNewsList !=null && mNewsList.get(i).getShowtype().equals("longimage")){
            if (view == null)  {
                holder = new ViewHolder();
                view = LayoutInflater.from(mContext).inflate(R.layout.fragment_top_item2, null);
                holder.simpleDraweeView = (SimpleDraweeView) view.findViewById(R.id.top_list1_imageview);
                holder.mTextViewTitle = (TextView) view.findViewById(R.id.top_list1_tvtitle);
                holder.mTextView = (TextView) view.findViewById(R.id.top_list1_tvdetail);
                holder.mTextViewCount = (TextView) view.findViewById(R.id.top_count);

                view.setTag(holder);
            }else{
                holder = (ViewHolder) view.getTag();
            }
            if (this.mNewsList!=null){
                if (mNewsList.get(i).getDescription()!=null){
                    holder.mTextView.setText(mNewsList.get(i).getDescription());
                }else{
                    holder.mTextView.setText("");
                }
                if (mNewsList.get(i).getCommentCount()!=null){
                    holder.mTextViewCount.setText(mNewsList.get(i).getCommentCount() + "");
                }else{
                    holder.mTextViewCount.setText(0+"");
                }
                if (mNewsList.get(i).getImage()!=null){
                    holder.simpleDraweeView.setImageURI(Uri.parse(mNewsList.get(i).getImage()));
                }if (mNewsList.get(i).getTitle()!=null){
                    String title = mNewsList.get(i).getTitle();
                    LogTool.LOG_E(NewsHeadlineAdapter.class,title);
                    holder.mTextViewTitle.setText(mNewsList.get(i).getTitle());
                }else{
                    holder.mTextViewTitle.setText("");
                }
            }
        }else{
            if(view == null){
                holder = new ViewHolder();
                view = LayoutInflater.from(mContext).inflate(R.layout.fragment_top_item,null);
                holder.simpleDraweeView = (SimpleDraweeView) view.findViewById(R.id.top_imageview);
                holder.mTextView = (TextView) view.findViewById(R.id.top_detail);
                holder.mTextViewCount = (TextView) view.findViewById(R.id.top_count);
                holder.mTextTuiguang = (TextView) view.findViewById(R.id.textview_tuiguang);
                //holder.mTextTuiguang = (TextView) view.findViewById(R.id.textview_vedio);
                view.setTag(holder);
            }else{
                holder = (ViewHolder) view.getTag();
            }
            if (this.mNewsList!=null){
                if (mNewsList.get(i).getTitle()!=null){
                    holder.mTextView.setText(mNewsList.get(i).getTitle());
                }if (mNewsList.get(i).getCommentCount()!=null){
                    holder.mTextViewCount.setText(mNewsList.get(i).getCommentCount() + "");
                }if (mNewsList.get(i).getImage()!=null){
                    holder.simpleDraweeView.setImageURI(Uri.parse(mNewsList.get(i).getImage()));
                }if (mNewsList.get(i).getModule()!=null&&mNewsList.get(i).getModule().equals("推广")){
                    ViewGroup.LayoutParams layoutParams = holder.mTextTuiguang.getLayoutParams();
                    layoutParams.width= ViewGroup.LayoutParams.WRAP_CONTENT;
                    holder.mTextTuiguang.setLayoutParams(layoutParams);
                    holder.mTextTuiguang.setVisibility(View.VISIBLE);
                    holder.mTextTuiguang.setText(mNewsList.get(i).getModule());
                }

            }
        }
//        注释
        return view;
    }

    class ViewHolder{
        SimpleDraweeView simpleDraweeView;
        TextView mTextView;
        TextView mTextViewCount;
        TextView mTextViewTitle;
        //TextView mTextVedio;
        TextView mTextTuiguang;
    }
}
