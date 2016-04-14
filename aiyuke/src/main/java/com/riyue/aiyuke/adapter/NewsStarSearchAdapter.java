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

import butterknife.ButterKnife;

/**
 * Created by Administrator on 2016/4/7.
 */
public class NewsStarSearchAdapter extends BaseAdapter{
    private Context mContext;
    private String[] str1;

    public NewsStarSearchAdapter(Context mContext, String[] str) {
        this.mContext = mContext;
        this.str1 = str;
    }

    @Override
    public int getCount() {
        if (str1 == null) {
            return 0;
        } else {
            return this.str1.length;
        }
    }

    @Override
    public Object getItem(int i) {
        if (str1 == null) {
            return null;
        } else {
            return this.str1;
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
            view = LayoutInflater.from(mContext).inflate(R.layout.news_star_search_gridview_item,null);
            holder.mTextView = (TextView) view.findViewById(R.id.star_name);
            view.setTag(holder);
        }else{
            holder = (ViewHolder) view.getTag();
        }if (str1!=null){
                holder.mTextView.setText(str1[i]);

        }
        return view;
    }
    class ViewHolder{
        //@Bind(R.id.star_imageview)
        //SimpleDraweeView simpleDraweeView;
        //@Bind(R.id.star_name)
        TextView mTextView;
    }
}
