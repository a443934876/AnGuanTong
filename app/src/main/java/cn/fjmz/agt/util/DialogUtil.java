package cn.fjmz.agt.util;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import cn.fjmz.agt.R;
import cn.fjmz.agt.bean.SafetyCheck;

import java.util.ArrayList;

public class DialogUtil {
    public static SafetyCheck getListDialog(final Context context,
                                            ArrayList<SafetyCheck> items, String title) {
        final SafetyCheck[] safetyCheck = {new SafetyCheck()};
        BaseQuickAdapter<SafetyCheck, BaseViewHolder> adapter = new BaseQuickAdapter<SafetyCheck,
                BaseViewHolder>(R.layout.item_company_list,
                items) {
            @Override
            protected void convert(@NonNull BaseViewHolder helper, final SafetyCheck item) {
                helper.setText(R.id.tv_company_name, item.getObliTitle());
                helper.getView(R.id.tv_company_name).setSelected(true);
            }
        };
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        final AlertDialog dialog = builder.create();
        @SuppressLint("InflateParams") View view = LayoutInflater.from(context).inflate(R.layout.dialog_list, null);
        RecyclerView list = view.findViewById(R.id.rv_company_list);
        ((TextView) view.findViewById(R.id.tv_dialog_title)).setText(title);
        list.setLayoutManager(new LinearLayoutManager(context));
        list.setAdapter(adapter);
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                safetyCheck[0] = (SafetyCheck) adapter.getItem(position);
                dialog.dismiss();
            }
        });

        dialog.setView(view);
        dialog.show();
        return safetyCheck[0];
    }

}
