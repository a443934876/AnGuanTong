package cn.fjmz.agt.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import cn.fjmz.agt.R;
import cn.fjmz.agt.mode.MissionInfo;
import cn.fjmz.agt.util.StringUtil;

import java.util.ArrayList;

/**
 * Created by Administrator on 2019/1/25.
 */

public class MissionAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<MissionInfo> infoList;

    public MissionAdapter(Context context,
                          ArrayList<MissionInfo> infoList) {
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
        ViewHolder holder = null;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = LayoutInflater.from(context).inflate(
                    R.layout.item_missioninfo, parent, false);
            // 获取控件对象
            holder.TV_transaction = (TextView) convertView.findViewById(R.id.TV_transaction);
            holder.TV_title = (TextView) convertView.findViewById(R.id.TV_title);
            holder.TV_name = (TextView) convertView.findViewById(R.id.TV_name);
            holder.TV_time = (TextView) convertView.findViewById(R.id.TV_time);
            // 设置控件集到convertView
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.TV_transaction.setText(StringUtil.noNull(infoList.get(position).getTransaction()));
        holder.TV_title.setText(StringUtil.noNull(infoList.get(position).getMissionTitle()));
        holder.TV_name.setText(StringUtil.noNull(infoList.get(position).getDispathName()));
        holder.TV_time.setText(StringUtil.noNull(infoList.get(position).getMissionLimit()));


        return convertView;
    }

    private class ViewHolder {
        TextView TV_transaction, TV_title, TV_name, TV_time;
    }
}
