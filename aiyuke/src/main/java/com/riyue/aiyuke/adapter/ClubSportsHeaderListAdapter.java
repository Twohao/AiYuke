package com.riyue.aiyuke.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.riyue.aiyuke.R;
import com.riyue.aiyuke.bean.ClubSportsHeaderDetailsInfo;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Mr.xu on 2016/4/9.
 */
public class ClubSportsHeaderListAdapter extends BaseAdapter {
    private List<ClubSportsHeaderDetailsInfo.MsgEntity.RecentEntity.DataEntity> entityList;
    private LayoutInflater inflater;

    public ClubSportsHeaderListAdapter(Context context,List<ClubSportsHeaderDetailsInfo.MsgEntity.RecentEntity.DataEntity> entityList) {
        this.entityList = entityList;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return entityList == null ? 0 :entityList.size();
    }

    @Override
    public Object getItem(int position) {
        return entityList == null ? null : entityList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null){
            convertView = inflater.inflate(R.layout.item_sports_details,null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        }else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.mDay.setText(entityList.get(position).getShow_day());
        holder.mTime.setText(entityList.get(position).getShow_fromtime());
        holder.mClub.setText(entityList.get(position).getShow_arena());
        return convertView;
    }

    class ViewHolder{
        @Bind(R.id.sports_details_day)
        TextView mDay;
        @Bind(R.id.sports_details_time)
        TextView mTime;
        @Bind(R.id.sports_details_club)
        TextView mClub;
        public ViewHolder(View view) {
            ButterKnife.bind(this,view);
        }
    }

}
