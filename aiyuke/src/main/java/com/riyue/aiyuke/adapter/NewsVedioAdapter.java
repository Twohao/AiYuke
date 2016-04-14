package com.riyue.aiyuke.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.riyue.aiyuke.R;
import com.riyue.aiyuke.bean.NewsEventInfo;
import com.riyue.aiyuke.bean.NewsVideoInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/4/5.
 */
//注释
public class NewsVedioAdapter extends RecyclerView.Adapter<NewsVedioAdapter.ViewHolder>{
    private Context mContext;
    private List<NewsVideoInfo.MsgEntity.VideoEntity> mVedioList = new ArrayList<>();
    public NewsVedioAdapter(Context mContext, List<NewsVideoInfo.MsgEntity.VideoEntity> mVedioList) {
        this.mContext = mContext;
        this.mVedioList = mVedioList;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_event_recycle,null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        if (mVedioList.get(position).getTitle()!=null){
            Log.i("chen", "onBindViewHolder: "+mVedioList.get(position).getTitle());
            Log.i("chen", "onBindViewHolder: "+mVedioList.get(position).getImage());
            holder.mTextView.setText(mVedioList.get(position).getTitle());
        }if (mVedioList.get(position).getImage()!=null){
            holder.simpleDraweeView.setImageURI(Uri.parse(mVedioList.get(position).getImage()));
        }
    }


    @Override
    public int getItemCount() {
        return mVedioList==null?0:mVedioList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        public SimpleDraweeView simpleDraweeView;
        public TextView mTextView;

        public ViewHolder(View itemView) {
            super(itemView);
            simpleDraweeView = (SimpleDraweeView) itemView.findViewById(R.id.event_imageview);
            mTextView = (TextView) itemView.findViewById(R.id.event_detail);
        }
    }
}
