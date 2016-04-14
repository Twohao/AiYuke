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
import com.riyue.aiyuke.bean.NewsHealthInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/4/5.
 */
public class NewsHealthAdapter extends BaseAdapter{
    private Context mContext;
    private List<NewsHealthInfo.MsgEntity.NewsListEntity> mHealth = new ArrayList<>();
    //构造方法
    public NewsHealthAdapter(Context mContext, List<NewsHealthInfo.MsgEntity.NewsListEntity> mHealth) {
        this.mContext = mContext;
        this.mHealth = mHealth;
    }

    @Override
    public int getCount() {
        if (mHealth == null) {
            return 0;
        } else {
            return this.mHealth.size();
        }
    }

    @Override
    public Object getItem(int i) {
        if (mHealth == null) {
            return null;
        } else {
            return this.mHealth.get(i);
        }
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder =null;
        if (mHealth !=null && mHealth.get(i).getShowtype().equals("longimage")){
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
            if (this.mHealth!=null){
                if (mHealth.get(i).getDescription()!=null){
                    holder.mTextView.setText(mHealth.get(i).getDescription());
                }if (mHealth.get(i).getCommentCount()!=null){
                    holder.mTextViewCount.setText(mHealth.get(i).getCommentCount() + "");
                }if (mHealth.get(i).getImage()!=null){
                    holder.simpleDraweeView.setImageURI(Uri.parse(mHealth.get(i).getImage()));
                }if (mHealth.get(i).getTitle()!=null){
                    holder.mTextViewTitle.setText(mHealth.get(i).getTitle());
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
            if (this.mHealth!=null){
                if (mHealth.get(i).getDescription()!=null){
                    holder.mTextView.setText(mHealth.get(i).getDescription());
                }if (mHealth.get(i).getCommentCount()!=null){
                    holder.mTextViewCount.setText(mHealth.get(i).getCommentCount() + "");
                }if (mHealth.get(i).getImage()!=null){
                    holder.simpleDraweeView.setImageURI(Uri.parse(mHealth.get(i).getImage()));
                }if (mHealth.get(i).getModule()!=null&&mHealth.get(i).getModule().equals("推广")){
                    //设置控件宽度
                    ViewGroup.LayoutParams layoutParams = holder.mTextTuiguang.getLayoutParams();
                    layoutParams.width= ViewGroup.LayoutParams.WRAP_CONTENT;
                    holder.mTextTuiguang.setLayoutParams(layoutParams);
                    holder.mTextTuiguang.setPadding(10,0,10,0);
                    holder.mTextTuiguang.setVisibility(View.VISIBLE);
                    holder.mTextTuiguang.setText(mHealth.get(i).getModule());
                }
            }
        }
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
