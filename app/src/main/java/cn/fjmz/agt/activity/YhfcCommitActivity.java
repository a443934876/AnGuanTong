package cn.fjmz.agt.activity;

import cn.fjmz.agt.YhfcCommitInfo;
import cn.fjmz.agt.dao.SqliteOperator;

import java.util.List;

public class YhfcCommitActivity extends CheckCommitActivity<YhfcCommitInfo> {


    @Override
    protected List<YhfcCommitInfo> getData() {
        return SqliteOperator.INSTANCE.getYhfcCommitInfo(this).loadAll();
    }
}
