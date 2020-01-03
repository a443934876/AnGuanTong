package cn.fjmz.agt.adapter;

import android.support.annotation.NonNull;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.ArrayList;
import java.util.List;

import cn.fjmz.agt.R;
import cn.fjmz.agt.bean.SafetySetEntity;
import cn.fjmz.agt.util.DateParseUtil;

public class SafetySetAdapter extends BaseQuickAdapter<SafetySetEntity, BaseViewHolder> {

    private List<SafetySetEntity> selectList = new ArrayList<>();

    public SafetySetAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    public List<SafetySetEntity> getSelectList() {
        return selectList;
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, final SafetySetEntity item) {
        CheckBox checkBox = helper.getView(R.id.cb_equipment_inspection);
        checkBox.setOnCheckedChangeListener((compoundButton, b) -> {
            if (b) {
                selectList.add(item);
            } else {
                selectList.remove(item);
            }
            notifyDataSetChanged();
        });
        helper.setText(R.id.tv_title, item.getCpname());
        helper.setText(R.id.tv_local, item.getCplocal());
        helper.setText(R.id.tv_master, item.getCpmaster());
        helper.setText(R.id.tv_check_date, DateParseUtil.getStringTime(item.getLastcheck()));
    }
}
