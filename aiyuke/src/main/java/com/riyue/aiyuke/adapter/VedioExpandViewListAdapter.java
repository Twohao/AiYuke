package com.riyue.aiyuke.adapter;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.BaseExpandableListAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.riyue.aiyuke.R;
import com.riyue.aiyuke.bean.NewsVideoInfo;
import com.riyue.aiyuke.tools.listview.MyGridView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2016/4/9.
 */
public class VedioExpandViewListAdapter extends BaseExpandableListAdapter {
    private Context mContext;
    private List<String> mVedioTitle = new ArrayList<>();
    private Map<String,List<NewsVideoInfo.MsgEntity.VideoEntity>> mExpandMapData = new HashMap<>();

    public VedioExpandViewListAdapter(Context mContext, List<String> mVedioTitle, Map<String, List<NewsVideoInfo.MsgEntity.VideoEntity>> mExpandMapData) {
        this.mContext = mContext;
        this.mVedioTitle = mVedioTitle;
        this.mExpandMapData = mExpandMapData;
    }

    @Override
    public int getGroupCount() {
        return mVedioTitle==null?0:mVedioTitle.size();
    }

    @Override
    public int getChildrenCount(int i) {
//        if (mExpandMapData != null && mVedioTitle != null
//                && mVedioTitle.size() > i && mExpandMapData.get(mVedioTitle.get(i)) != null) {
//            return mExpandMapData.get(mVedioTitle.get(i)).size();
//        }
        if(mExpandMapData.get(mVedioTitle.get(i))!=null)
        {
            return 1;
        }
        return 0;
    }

    @Override
    public Object getGroup(int i) {
        return mVedioTitle.get(i);
    }

    @Override
    public Object getChild(int i, int i1) {
        return mExpandMapData.get(mVedioTitle.get(i)).get(i1);
    }

    @Override
    public long getGroupId(int i) {
        return i;
    }

    @Override
    public long getChildId(int i, int i1) {
        return i1;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int i, boolean b, View view, ViewGroup viewGroup) {
        View groupView = view;
        GroupViewHolder groupViewHolder = null;
        if (groupView!=null){
            groupViewHolder = (GroupViewHolder) groupView.getTag();
        }else{
            groupView = LayoutInflater.from(mContext).inflate(R.layout.vedio_expand_title, null);
            groupViewHolder = new GroupViewHolder(groupView);
            groupView.setTag(groupViewHolder);
        }
        groupViewHolder.mTitleText.setText(mVedioTitle.get(i));
        return groupView;
    }

    @Override
    public View getChildView(int i, int i1, boolean b, View view, ViewGroup viewGroup) {
        View childView = view;
        ChildViewHolder childViewHolder = null;
        if (childView!=null){
            childViewHolder = (ChildViewHolder) childView.getTag();
        }else{
            childView = LayoutInflater.from(mContext).inflate(R.layout.vedio_childview_item, null);
            childViewHolder = new ChildViewHolder(childView);
            childView.setTag(childViewHolder);
        }
//        NewsVideoInfo.MsgEntity.VideoEntity videoEntity = mExpandMapData.get(mVedioTitle.get(i)).get(i1);

        List<NewsVideoInfo.MsgEntity.VideoEntity> entityList = mExpandMapData.get(mVedioTitle.get(i));
        GridViewAdapter gridViewAdapter = new GridViewAdapter(mContext,entityList);
        childViewHolder.mGridView.setAdapter(gridViewAdapter);
        return childView;
    }

    @Override
    public boolean isChildSelectable(int i, int i1) {
        return true;
    }

    class GroupViewHolder {
        @Bind(R.id.vedio_expand_title_tv)
        TextView mTitleText;
        public GroupViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
    class ChildViewHolder {
        @Bind(R.id.vedio_child_gridview)
        MyGridView mGridView;
        public ChildViewHolder(View view) {
            ButterKnife.bind(this,view);
        }
    }
}

class GridViewAdapter extends BaseAdapter{
    private Context mContext;
//    private NewsVideoInfo.MsgEntity.VideoEntity videoEntity;
    private List<NewsVideoInfo.MsgEntity.VideoEntity> entityList;

    public GridViewAdapter(Context mContext, List<NewsVideoInfo.MsgEntity.VideoEntity> entityList) {
        this.mContext = mContext;
        this.entityList = entityList;
    }

    @Override
    public int getCount() {
        return entityList==null?0:entityList.size();
    }

    @Override
    public Object getItem(int i) {
        return entityList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder = null;
        if (view == null){

            view = LayoutInflater.from(mContext).inflate(R.layout.vedio_child_gridview_item,null,false);
            holder = new ViewHolder(view);
            view.setTag(holder);
        }else{
            holder = (ViewHolder) view.getTag();
        }
        NewsVideoInfo.MsgEntity.VideoEntity videoEntity = entityList.get(i);
        if (videoEntity!=null){
            holder.textViewDetail.setText(videoEntity.getLabel());
            holder.textViewTitle.setText(videoEntity.getTitle());
            holder.simpleDraweeView.setImageURI(Uri.parse(videoEntity.getImage()));
        }

        return view;
    }

    class ViewHolder{
        @Bind(R.id.star_imageview)
        SimpleDraweeView simpleDraweeView;
        @Bind(R.id.vedio_expand_image_detail)
        TextView textViewDetail;
        @Bind(R.id.vedio_title)
        TextView textViewTitle;
        public ViewHolder(View view){
            ButterKnife.bind(this,view);
        }
    }
}
