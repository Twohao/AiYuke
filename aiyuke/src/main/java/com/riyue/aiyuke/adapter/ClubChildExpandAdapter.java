package com.riyue.aiyuke.adapter;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.riyue.aiyuke.R;
import com.riyue.aiyuke.bean.ClubSportsInfo;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Mr.xu on 2016/4/5.
 */
public class ClubChildExpandAdapter extends BaseExpandableListAdapter {

    private List<ClubSportsInfo.MsgEntity.NearbyEntity> entityList;
    private List<Integer> keyList;
    private Context mContext;
    private LayoutInflater inflater;

    public ClubChildExpandAdapter(List<ClubSportsInfo.MsgEntity.NearbyEntity> entityList,List<Integer> keyList, Context mContext) {
        this.entityList = entityList;
        this.mContext = mContext;
        this.inflater = LayoutInflater.from(mContext);
        this.keyList = keyList;
    }

    @Override
    public int getGroupCount() {
        return keyList == null ? 0 : keyList.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return  1;
    }

    @Override
    public Object getGroup(int groupPosition) {
        return keyList == null ? null : keyList.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return entityList == null ? null : entityList.get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        GroupViewHolder groupViewHolder = null;
        if (convertView == null){
            convertView = inflater.inflate(R.layout.item_club_child_expand_title,null);
            groupViewHolder = new GroupViewHolder(convertView);
            convertView.setTag(groupViewHolder);
        }else {
            groupViewHolder = (GroupViewHolder) convertView.getTag();
        }
        ClubSportsInfo.MsgEntity.NearbyEntity nearbyEntity = entityList.get(groupPosition);
        groupViewHolder.logo.setImageURI(getUri(nearbyEntity.getLogo()));
        groupViewHolder.title.setText(nearbyEntity.getClubname());
        switch (nearbyEntity.getState()){
            case "报名中":
                groupViewHolder.imageView.setImageResource(R.drawable.lable_apply);
                break;
            case "已结束":
                break;
            case "已满员":
                break;
        }

        return convertView;
    }

    private Uri getUri(String urlPath) {
        return Uri.parse(urlPath);
    }

    class GroupViewHolder{
        @Bind(R.id.item_club_child_icon_iv)
        SimpleDraweeView logo;
        @Bind(R.id.item_club_child_title_tv)
        TextView title;
        @Bind(R.id.item_club_child_tag_iv)
        ImageView imageView;
        public GroupViewHolder(View view) {
            ButterKnife.bind(this,view);
        }
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        ChildViewHolder childViewHolder = null;
        if (convertView == null){
            convertView = inflater.inflate(R.layout.item_club_child_expand_content,null);
            childViewHolder = new ChildViewHolder(convertView);
            convertView.setTag(childViewHolder);
        }else {
            childViewHolder = (ChildViewHolder) convertView.getTag();
        }
        ClubSportsInfo.MsgEntity.NearbyEntity nearbyEntity = entityList.get(groupPosition);
        List<ClubSportsInfo.MsgEntity.NearbyEntity.MemberInfoEntity> memberInfo = entityList.get(childPosition).getMemberInfo();
        childViewHolder.time.setText(nearbyEntity.getShow_fromtime());
        childViewHolder.title.setText(nearbyEntity.getArenaname());
        childViewHolder.mContent.setText(nearbyEntity.getTitle() + "\n" + nearbyEntity.getDesc());
        childViewHolder.mLong.setText(nearbyEntity.getDistance() + "km");
        childViewHolder.num.setText(nearbyEntity.getCurmembers() + "/" + nearbyEntity.getMax_members());
        if (!memberInfo.isEmpty()) {
            childViewHolder.imageOne.setImageURI(getUri(memberInfo.get(0).getAvatar()));
        }
        return convertView;
    }

    class ChildViewHolder{
        @Bind(R.id.item_club_child_time_tv)
        TextView time;
        @Bind(R.id.item_club_child_title_tv)
        TextView title;
        @Bind(R.id.item_club_child_long)
        TextView mLong;
        @Bind(R.id.item_club_child_content_tv)
        TextView mContent;
        @Bind(R.id.item_club_child_number_tv)
        TextView num;
        @Bind(R.id.icon_one_iv)
        SimpleDraweeView imageOne;
        @Bind(R.id.icon_two_iv)
        SimpleDraweeView imageTwo;
        @Bind(R.id.icon_three_iv)
        SimpleDraweeView imageThree;
        @Bind(R.id.icon_four_iv)
        SimpleDraweeView imageFour;
        @Bind(R.id.icon_five_iv)
        SimpleDraweeView imageFive;
        public ChildViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}
