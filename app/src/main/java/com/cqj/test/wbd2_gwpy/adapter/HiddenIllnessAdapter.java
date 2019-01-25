package com.cqj.test.wbd2_gwpy.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.cqj.test.wbd2_gwpy.R;
import com.cqj.test.wbd2_gwpy.mode.HiddenIllnessInfo;

import java.util.ArrayList;

/*
 * Created by Administrator on 2019/1/25.
 */

public class HiddenIllnessAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<HiddenIllnessInfo> infoList;

    public HiddenIllnessAdapter(Context context,
                                ArrayList<HiddenIllnessInfo> infoList) {
        this.context = context;
        this.infoList = infoList;
    }

    @Override
    public int getCount() {
        return infoList.size();
    }

    @Override
    public Object getItem(int position) {
        return infoList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = LayoutInflater.from(context).inflate(
                    R.layout.item_hiddenillnessinfo, parent, false);
            // 获取控件对象
            holder.TV_checkDate = (TextView) convertView.findViewById(R.id.TV_checkDate);
            holder.TV_actionOrgName = (TextView) convertView.findViewById(R.id.TV_actionOrgName);
            holder.TV_cpeoname = (TextView) convertView.findViewById(R.id.TV_cpeoname);
            holder.TV_troubleGrade = (TextView) convertView.findViewById(R.id.TV_troubleGrade);
            holder.TV_safetyTrouble = (TextView) convertView.findViewById(R.id.TV_safetyTrouble);
            holder.TV_dScheme = (TextView) convertView.findViewById(R.id.TV_dScheme);
            holder.TV_limitDate = (TextView) convertView.findViewById(R.id.TV_limitDate);
            holder.TV_LiabelName = (TextView) convertView.findViewById(R.id.TV_LiabelName);
            holder.TV_alertmphone = (TextView) convertView.findViewById(R.id.TV_alertmphone);
            // 设置控件集到convertView
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.TV_checkDate.setText(infoList.get(position).getCheckDate());
        holder.TV_actionOrgName.setText(infoList.get(position).getActionOrgName());
        holder.TV_cpeoname.setText(infoList.get(position).getCpeoname());
        holder.TV_troubleGrade.setText(infoList.get(position).getTroubleGrade());
        holder.TV_safetyTrouble.setText(infoList.get(position).getSafetyTrouble());
        holder.TV_dScheme.setText(infoList.get(position).getdScheme());
        holder.TV_limitDate.setText(infoList.get(position).getLimitDate());
        holder.TV_LiabelName.setText(infoList.get(position).getLiabelName());
        holder.TV_alertmphone.setText(infoList.get(position).getAlertmphone());
        return convertView;
    }

    private class ViewHolder {
        TextView TV_checkDate, TV_actionOrgName, TV_cpeoname, TV_troubleGrade, TV_safetyTrouble, TV_dScheme, TV_limitDate, TV_LiabelName, TV_alertmphone;
    }
}
