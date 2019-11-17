package cn.fjmz.agt.activity;

import cn.fjmz.agt.AqjcCommitInfo;
import cn.fjmz.agt.dao.SqliteOperator;

import java.util.List;

public class AqjcCommitActivity extends CheckCommitActivity<AqjcCommitInfo> {

    @Override
    protected List<AqjcCommitInfo> getData() {
        return SqliteOperator.INSTANCE.getCommitInfo(this).loadAll();
    }
}
