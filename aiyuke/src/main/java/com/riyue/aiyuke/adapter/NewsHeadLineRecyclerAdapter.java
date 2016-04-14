package com.riyue.aiyuke.adapter;

import android.content.Context;
import android.graphics.Color;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.riyue.aiyuke.R;
import com.riyue.aiyuke.bean.NewsHeadlineInfo;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * @author mtf
 * @date 2016/4/9
 */
public class NewsHeadLineRecyclerAdapter extends RecyclerView.Adapter implements View.OnClickListener{


    public final static String TYPE_LONG_IMAGE_STRING = "longimage";

    private final static int TYPE_LONG_IMAGE = 1;
    private static final int TYPE_NORMAL = 2;


    private List<NewsHeadlineInfo.MsgEntity.NewslistEntity> dataList;
    private LayoutInflater inflater;

    private OnRecyclerViewItemClickListener mOnItemClickListener = null;



    public NewsHeadLineRecyclerAdapter(Context context, List<NewsHeadlineInfo.MsgEntity.NewslistEntity> dataList) {
        this.dataList = dataList;
        inflater=LayoutInflater.from(context);
    }

    //    private OnRecyclerViewItemClickListener mOnItemClickListener = null;


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == TYPE_NORMAL) {
            View view = inflater.inflate(R.layout.fragment_top_item, parent, false);
            view.setOnClickListener(this);
            return new ViewHolderNormal(view);
        } else {
            View view=inflater.inflate(R.layout.fragment_top_item2,parent,false);
            view.setOnClickListener(this);
            return new ViewHolderLongImage(view);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        NewsHeadlineInfo.MsgEntity.NewslistEntity entity = dataList.get(position);
        if(holder instanceof ViewHolderNormal){
            if(entity.getImage()!=null){
                ((ViewHolderNormal) holder).simpleDraweeView.setImageURI(Uri.parse(entity.getImage()));
            }
            if(entity.getTitle()!=null){
                ((ViewHolderNormal) holder).tvDetail.setText(entity.getTitle());
            }
            if(entity.getDescription()!=null){
                ((ViewHolderNormal) holder).tvDesc.setText(entity.getDescription());
            }
            if(entity.getCommentCount()!=null) {
                ((ViewHolderNormal) holder).tvCount.setText(entity.getCommentCount());
            }
            if(entity.getModule()!=null&&!TextUtils.isEmpty(entity.getColor())){
                ((ViewHolderNormal) holder).tvTuiguang.setText(entity.getModule());
                ((ViewHolderNormal) holder).tvTuiguang.setBackgroundColor(Color.parseColor(entity.getColor()));
            }else {
                ((ViewHolderNormal) holder).tvTuiguang.setText("");
            }
            holder.itemView.setTag(dataList.get(position));
            return;
        }
        if(holder instanceof ViewHolderLongImage){
            if(entity.getImage()!=null){
                ((ViewHolderLongImage) holder).simpleDraweeView.setImageURI(Uri.parse(entity.getImage()));
            }
            if(entity.getTitle()!=null){
                ((ViewHolderLongImage) holder).tvTitle.setText(entity.getTitle());
            }
            if(entity.getDescription()!=null){
                ((ViewHolderLongImage) holder).tvDetail.setText(entity.getDescription());
            }
            if(entity.getCommentCount()!=null){
                ((ViewHolderLongImage) holder).tvCount.setText(entity.getCommentCount());
            }
            holder.itemView.setTag(dataList.get(position));
            return;
        }
    }

    @Override
    public int getItemCount() {
        return dataList==null?0:dataList.size();
    }

    @Override
    public int getItemViewType(int position) {
//        return super.getItemViewType(position);
        if(TextUtils.equals(dataList.get(position).getShowtype(), TYPE_LONG_IMAGE_STRING)){
            return TYPE_LONG_IMAGE;
        }else {
            return TYPE_NORMAL;
        }
    }

    @Override
    public void onClick(View v) {
        if (mOnItemClickListener != null) {
            //注意这里使用getTag方法获取数据
            mOnItemClickListener.onItemClick(v, (NewsHeadlineInfo.MsgEntity.NewslistEntity) v.getTag());
        }
    }

    public void setOnItemClickListener(OnRecyclerViewItemClickListener listener) {
        this.mOnItemClickListener = listener;
    }

    public static interface OnRecyclerViewItemClickListener {
        void onItemClick(View view , NewsHeadlineInfo.MsgEntity.NewslistEntity data);
    }



    class ViewHolderLongImage extends RecyclerView.ViewHolder{

        @Bind(R.id.top_list1_tvtitle)
        TextView tvTitle;
        @Bind(R.id.top_list1_imageview)
        SimpleDraweeView simpleDraweeView;
        @Bind(R.id.top_list1_tvdetail)
        TextView tvDetail;
        @Bind(R.id.top_count)
        TextView tvCount;

        public ViewHolderLongImage(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }

    class ViewHolderNormal extends RecyclerView.ViewHolder{

        @Bind(R.id.top_imageview)
        SimpleDraweeView simpleDraweeView;
        @Bind(R.id.top_detail)
        TextView tvDetail;
        @Bind(R.id.top_describe)
        TextView tvDesc;
        @Bind(R.id.top_count)
        TextView tvCount;
        @Bind(R.id.textview_tuiguang)
        TextView tvTuiguang;

        public ViewHolderNormal(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
