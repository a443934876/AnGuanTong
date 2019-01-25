package com.cqj.test.wbd2_gwpy.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.cqj.test.wbd2_gwpy.R;
import com.cqj.test.wbd2_gwpy.mode.SafetySetInfo;
import com.cqj.test.wbd2_gwpy.util.StringUtil;

import java.util.ArrayList;

/*
 * Created by Administrator on 2019/1/25.
 */

public class SafetySetAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<SafetySetInfo> infoList;

    public SafetySetAdapter(Context context,
                            ArrayList<SafetySetInfo> infoList) {
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
            holder.TV_cpname = (TextView) convertView.findViewById(R.id.TV_cpname);
            holder.TV_cpnumber = (TextView) convertView.findViewById(R.id.TV_cpnumber);
            holder.TV_cplocal = (TextView) convertView.findViewById(R.id.TV_cplocal);
            holder.TV_lon = (TextView) convertView.findViewById(R.id.TV_lon);
            holder.TV_lat = (TextView) convertView.findViewById(R.id.TV_lat);
            holder.TV_checkcount = (TextView) convertView.findViewById(R.id.TV_checkcount);
            holder.TV_cpmaintain = (TextView) convertView.findViewById(R.id.TV_cpmaintain);
            holder.TV_cpmaster = (TextView) convertView.findViewById(R.id.TV_cpmaster);
            holder.TV_lastcheck = (TextView) convertView.findViewById(R.id.TV_lastcheck);
            holder.TV_nextcheck = (TextView) convertView.findViewById(R.id.TV_nextcheck);
            // 设置控件集到convertView
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
//
        holder.TV_cpname.setText(StringUtil.noNull(infoList.get(position).getCpname()));
        holder.TV_cpnumber.setText(StringUtil.noNull(infoList.get(position).getCpnumber()));
        holder.TV_cplocal.setText(StringUtil.noNull(infoList.get(position).getCplocal()));
        holder.TV_lon.setText(StringUtil.noNull(infoList.get(position).getLon()));
        holder.TV_lat.setText(StringUtil.noNull(infoList.get(position).getLat()));
        holder.TV_checkcount.setText(StringUtil.noNull(infoList.get(position).getCheckcount()));
        holder.TV_cpmaintain.setText(StringUtil.noNull(infoList.get(position).getCpmaintain()));
        holder.TV_cpmaster.setText(StringUtil.noNull(infoList.get(position).getCpmaster()));
        holder.TV_lastcheck.setText(StringUtil.noNull(infoList.get(position).getLastcheck()));
        holder.TV_nextcheck.setText(StringUtil.noNull(infoList.get(position).getNextcheck()));


        return convertView;
    }

    private class ViewHolder {
        TextView TV_cpname, TV_cpnumber, TV_cplocal, TV_lon, TV_lat
                , TV_checkcount, TV_cpmaintain, TV_cpmaster, TV_lastcheck, TV_nextcheck;
    }
}
