package com.cqj.test.wbd2_gwpy.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.cqj.test.wbd2_gwpy.R;
import com.cqj.test.wbd2_gwpy.mode.LessonInfo;
import com.cqj.test.wbd2_gwpy.util.StringUtil;

import java.util.ArrayList;

/*
 * Created by Administrator on 2019/1/25.
 */

public class LessonAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<LessonInfo> infoList;

    public LessonAdapter(Context context,
                         ArrayList<LessonInfo> infoList) {
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
                    R.layout.item_safetysetinfo, parent, false);
            // 获取控件对象
            holder.TV_currName = (TextView) convertView.findViewById(R.id.TV_currName);
            holder.TV_lesstart = (TextView) convertView.findViewById(R.id.TV_lesstart);
            holder.TV_lesroom = (TextView) convertView.findViewById(R.id.TV_lesroom);
            holder.TV_lesmunitue = (TextView) convertView.findViewById(R.id.TV_lesmunitue);
            holder.TV_leshour = (TextView) convertView.findViewById(R.id.TV_leshour);
            holder.TV_teachemname = (TextView) convertView.findViewById(R.id.TV_teachemname);
            holder.TV_lescomm = (TextView) convertView.findViewById(R.id.TV_lescomm);
            holder.TV_stuemname = (TextView) convertView.findViewById(R.id.TV_stuemname);
            // 设置控件集到convertView
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.TV_currName.setText(StringUtil.noNull(infoList.get(position).getCurrName()));
        holder.TV_lesstart.setText(StringUtil.noNull(infoList.get(position).getLesstart()));
        holder.TV_lesroom.setText(StringUtil.noNull(infoList.get(position).getLesroom()));
        holder.TV_lesmunitue.setText(StringUtil.noNull(infoList.get(position).getLesmunitue()));
        holder.TV_leshour.setText(StringUtil.noNull(infoList.get(position).getLeshour()));
        holder.TV_teachemname.setText(StringUtil.noNull(infoList.get(position).getTeachemname()));
        holder.TV_lescomm.setText(StringUtil.noNull(infoList.get(position).getLescomm()));
        holder.TV_stuemname.setText(StringUtil.noNull(infoList.get(position).getStuemname()));
        return convertView;
    }

    private class ViewHolder {
        TextView TV_currName, TV_lesstart, TV_lesroom, TV_lesmunitue, TV_leshour, TV_teachemname, TV_lescomm, TV_stuemname;
    }
}
