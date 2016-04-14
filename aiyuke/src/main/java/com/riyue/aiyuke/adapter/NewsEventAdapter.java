package com.riyue.aiyuke.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.riyue.aiyuke.R;
import com.riyue.aiyuke.bean.NewsEventInfo;
import com.riyue.aiyuke.bean.NewsTechInfo;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

/**
 * Created by Administrator on 2016/4/5.
 */
//注释
public class NewsEventAdapter extends RecyclerView.Adapter<NewsEventAdapter.ViewHolder>{
    private Context mContext;
    private List<NewsEventInfo.MsgEntity.PastEntity> mEventList = new ArrayList<>();
    public NewsEventAdapter(Context mContext, List<NewsEventInfo.MsgEntity.PastEntity> mEventList) {
        this.mContext = mContext;
        this.mEventList = mEventList;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_event_recycle,null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        if (mEventList.get(position).getTitle()!=null){
            Log.i("chen", "onBindViewHolder: "+mEventList.get(position).getTitle());
            Log.i("chen", "onBindViewHolder: "+mEventList.get(position).getImage());
            holder.mTextView.setText(mEventList.get(position).getTitle());
        }if (mEventList.get(position).getImage()!=null){
            holder.simpleDraweeView.setImageURI(Uri.parse(mEventList.get(position).getImage()));
        }
    }


    @Override
    public int getItemCount() {
        return mEventList==null?0:mEventList.size();
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
