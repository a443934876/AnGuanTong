package cn.fjmz.agt.adapter;

import java.util.HashMap;
import java.util.List;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.TextView;

import cn.fjmz.agt.DeviceInfo;
import cn.fjmz.agt.R;
import cn.fjmz.agt.activity.AqssDetailActivity;

public class AqssAdapter extends BaseAdapter {

    private Context context;
    private List<DeviceInfo> dataList;
    private HashMap<Integer, Integer> checkMap;

    public HashMap<Integer, Integer> getCheckMap() {
        return checkMap;
    }

    public AqssAdapter(Context context,
                       List<DeviceInfo> dataList) {
        this.context = context;
        this.dataList = dataList;
        checkMap = new HashMap<>();
    }

    @Override
    public int getCount() {
        return dataList.size();
    }

    @Override
    public DeviceInfo getItem(int arg0) {
        return dataList.get(arg0);
    }

    @Override
    public long getItemId(int arg0) {
        return arg0;
    }

    @SuppressLint("InflateParams")
    @Override
    public View getView(final int position, View arg1, ViewGroup arg2) {
        ViewHolder vh;
        if (arg1 == null) {
            vh = new ViewHolder();
            arg1 = LayoutInflater.from(context).inflate(R.layout.aqss_item,
                    null);
            vh.cBox = (CheckBox) arg1.findViewById(R.id.aqssitem_check);
            vh.date = (TextView) arg1.findViewById(R.id.aqssitem_byrq);
            vh.name = (TextView) arg1.findViewById(R.id.aqssitem_sbmc);
            vh.tvDeviceLocation = (TextView) arg1.findViewById(R.id.tv_device_location);
            vh.ln = (LinearLayout) arg1.findViewById(R.id.aqssitem_ln);
            arg1.setTag(vh);
        } else {
            vh = (ViewHolder) arg1.getTag();
        }
        final DeviceInfo item = dataList.get(position);
        if (item != null) {
            vh.name.setText(item.getProdname());
            vh.tvDeviceLocation.setText(String.format("设备场所：%s", item.getDeviceLocation()));
            String date = item.getSccheckdate();
            if (date.length() > 10) {
                date = date.substring(0, 10);
            }
            vh.date.setText(String.format("上次保养：%s", date));
            vh.cBox.setOnCheckedChangeListener(new OnCheckedChangeListener() {

                @Override
                public void onCheckedChanged(CompoundButton arg0, boolean arg1) {
                    if (arg1) {
                        checkMap.put(position, item.getCpid());
                    } else {
                        checkMap.remove(position);
                    }
                }
            });
            if (checkMap.get(position) == null) {
                vh.cBox.setChecked(false);
            } else {
                vh.cBox.setChecked(true);
            }
            vh.ln.setOnClickListener(new OnClickListener() {

                @Override
                public void onClick(View arg0) {
                    Intent intent = new Intent(context,
                            AqssDetailActivity.class);
                    intent.putExtra("SCPID", item.getCpid());
                    context.startActivity(intent);
                }
            });
            vh.name.setOnClickListener(new OnClickListener() {

                @Override
                public void onClick(View arg0) {
                    Intent intent = new Intent(context,
                            AqssDetailActivity.class);
                    intent.putExtra("SCPID", item.getCpid());
                    context.startActivity(intent);
                }
            });
        }
        return arg1;
    }

    class ViewHolder {
        TextView name, date, tvDeviceLocation;
        CheckBox cBox;
        LinearLayout ln;
    }
}
