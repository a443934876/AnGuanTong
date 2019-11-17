package cn.fjmz.agt.activity;

import android.content.Context;
import android.content.Intent;

import cn.fjmz.agt.SbjcCommitInfo;
import cn.fjmz.agt.dao.SqliteOperator;

import java.util.List;

public class SbjcCommitActivity extends CheckCommitActivity<SbjcCommitInfo> {

    public static void launch(Context context) {
        Intent intent = new Intent(context, SbjcCommitActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        context.startActivity(intent);
    }

    @Override
    protected List<SbjcCommitInfo> getData() {
        return SqliteOperator.INSTANCE.getSbCommitDao(this).loadAll();
    }
}
