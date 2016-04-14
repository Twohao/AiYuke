package com.riyue.aiyuke.adapter;

import android.content.Context;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.riyue.aiyuke.R;
import com.riyue.aiyuke.bean.NewsEquipInfo;
import com.riyue.aiyuke.bean.NewsTechInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/4/5.
 */
//注释
public class NewsTechAdapter extends BaseAdapter{
    private Context mContext;
    private List<NewsTechInfo.MsgEntity.NewsListEntity> mTechList = new ArrayList<>();
    public NewsTechAdapter(Context mContext, List<NewsTechInfo.MsgEntity.NewsListEntity> mTechList) {
        this.mContext = mContext;
        this.mTechList = mTechList;
    }

    @Override
    public int getCount() {
        if (mTechList == null) {
            return 0;
        } else {
            return this.mTechList.size();
        }
    }

    @Override
    public Object getItem(int i) {
        if (mTechList == null) {
            return null;
        } else {
            return this.mTechList.get(i);
        }
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder =null;
        if (mTechList !=null && mTechList.get(i).getShowtype().equals("longimage")){
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
            if (this.mTechList!=null){
                if (mTechList.get(i).getDescription()!=null){
                    holder.mTextView.setText(mTechList.get(i).getDescription());
                }if (mTechList.get(i).getCommentCount()!=null){
                    holder.mTextViewCount.setText(mTechList.get(i).getCommentCount() + "");
                }if (mTechList.get(i).getImage()!=null){
                    holder.simpleDraweeView.setImageURI(Uri.parse(mTechList.get(i).getImage()));
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
                view.setTag(holder);
            }else{
                holder = (ViewHolder) view.getTag();
            }
            if (this.mTechList!=null){
                if (mTechList.get(i).getTitle()!=null){
                    holder.mTextView.setText(mTechList.get(i).getTitle());
                }if (mTechList.get(i).getCommentCount()!=null){
                    holder.mTextViewCount.setText(mTechList.get(i).getCommentCount() + "");
                }if (holder.simpleDraweeView!=null){
                    holder.simpleDraweeView.setImageURI(Uri.parse(mTechList.get(i).getImage()));
                }if (mTechList.get(i).getModule()!=null){
                    //设置控件宽度
                    ViewGroup.LayoutParams layoutParams = holder.mTextTuiguang.getLayoutParams();
                    layoutParams.width= ViewGroup.LayoutParams.WRAP_CONTENT;
                    holder.mTextTuiguang.setLayoutParams(layoutParams);
                    holder.mTextTuiguang.setVisibility(View.VISIBLE);
                    holder.mTextTuiguang.setText(mTechList.get(i).getModule());
                }
            }
        }
//注释
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
