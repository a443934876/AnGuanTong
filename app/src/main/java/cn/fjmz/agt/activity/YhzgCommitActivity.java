package cn.fjmz.agt.activity;

import cn.fjmz.agt.YhzgCommitInfo;
import cn.fjmz.agt.dao.SqliteOperator;

import java.util.List;

public class YhzgCommitActivity extends CheckCommitActivity<YhzgCommitInfo> {

    @Override
    protected List<YhzgCommitInfo> getData() {
        return SqliteOperator.INSTANCE.getYhzgCommitInfo(this).loadAll();
    }
}
