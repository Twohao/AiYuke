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
import com.riyue.aiyuke.bean.NewsEquipInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/4/5.
 */
//注释
public class NewsEquipAdapter extends BaseAdapter{
    private Context mContext;
    private List<NewsEquipInfo.MsgEntity.NewsListEntity> mEquipList = new ArrayList<>();

    public NewsEquipAdapter(Context mContext, List<NewsEquipInfo.MsgEntity.NewsListEntity> mEquipList) {
        this.mContext = mContext;
        this.mEquipList = mEquipList;
    }

    @Override
    public int getCount() {
        if (mEquipList == null) {
            return 0;
        } else {
            return this.mEquipList.size();
        }
    }

    @Override
    public Object getItem(int i) {
        if (mEquipList == null) {
            return null;
        } else {
            return this.mEquipList.get(i);
        }
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder =null;
        if (mEquipList !=null && mEquipList.get(i).getShowtype().equals("longimage")){
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
            if (this.mEquipList!=null){
                if (mEquipList.get(i).getDescription()!=null){
                    holder.mTextView.setText(mEquipList.get(i).getDescription());
                }if (mEquipList.get(i).getCommentCount()!=null){
                    holder.mTextViewCount.setText(mEquipList.get(i).getCommentCount() + "");
                }if (mEquipList.get(i).getImage()!=null){
                    holder.simpleDraweeView.setImageURI(Uri.parse(mEquipList.get(i).getImage()));
                }if (mEquipList.get(i).getTitle()!=null){
                    holder.mTextViewTitle.setText(mEquipList.get(i).getTitle());
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
            if (this.mEquipList!=null){
                if (mEquipList.get(i).getDescription()!=null){
                    holder.mTextView.setText(mEquipList.get(i).getDescription());
                }if (mEquipList.get(i).getCommentCount()!=null){
                    holder.mTextViewCount.setText(mEquipList.get(i).getCommentCount() + "");
                }if (holder.simpleDraweeView!=null){
                    holder.simpleDraweeView.setImageURI(Uri.parse(mEquipList.get(i).getImage()));
                }if (mEquipList.get(i).getModule()!=null){
                    //设置控件宽度
                    ViewGroup.LayoutParams layoutParams = holder.mTextTuiguang.getLayoutParams();
                    layoutParams.width= ViewGroup.LayoutParams.WRAP_CONTENT;
                    holder.mTextTuiguang.setLayoutParams(layoutParams);
                    holder.mTextTuiguang.setVisibility(View.VISIBLE);
                    holder.mTextTuiguang.setText(mEquipList.get(i).getModule());
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
