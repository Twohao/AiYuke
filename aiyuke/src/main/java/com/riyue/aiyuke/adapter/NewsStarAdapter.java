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
import com.riyue.aiyuke.bean.NewsStarInfo;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2016/4/7.
 */
public class NewsStarAdapter extends BaseAdapter{
    private Context mContext;
    private List<NewsStarInfo.MsgEntity.ListEntity> mStarList = new ArrayList<>();

    public NewsStarAdapter(Context mContext, List<NewsStarInfo.MsgEntity.ListEntity> mStarList) {
        this.mContext = mContext;
        this.mStarList = mStarList;
    }

    @Override
    public int getCount() {
        if (mStarList == null) {
            return 0;
        } else {
            return this.mStarList.size();
        }
    }

    @Override
    public Object getItem(int i) {
        if (mStarList == null) {
            return null;
        } else {
            return this.mStarList.get(i);
        }
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ButterKnife.bind(this,view);
        ViewHolder holder =null;
        if(view == null){
            holder = new ViewHolder();
            view = LayoutInflater.from(mContext).inflate(R.layout.news_star_gridview_item,null);
            holder.mTextView = (TextView) view.findViewById(R.id.star_name);
            holder.simpleDraweeView = (SimpleDraweeView) view.findViewById(R.id.star_imageview);
            view.setTag(holder);
        }else{
            holder = (ViewHolder) view.getTag();
        }if (mStarList!=null){
            Log.i("chen4", "getView: mStarList"+mStarList);
            if (mStarList.get(i).getName()!=null){
                holder.mTextView.setText(mStarList.get(i).getName());
            }if (mStarList.get(i).getImage()!=null){
                holder.simpleDraweeView.setImageURI(Uri.parse(mStarList.get(i).getImage()));
            }
        }
        return view;
    }
    class ViewHolder{
        //@Bind(R.id.star_imageview)
        SimpleDraweeView simpleDraweeView;
        //@Bind(R.id.star_name)
        TextView mTextView;
    }
}
