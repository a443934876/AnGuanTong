package com.cqj.test.wbd2_gwpy.activity;

import com.cqj.test.wbd2_gwpy.AqjcCommitInfo;
import com.cqj.test.wbd2_gwpy.dao.SqliteOperator;

import java.util.List;

public class AqjcCommitActivity extends CheckCommitActivity<AqjcCommitInfo> {

    @Override
    protected List<AqjcCommitInfo> getData() {
        return SqliteOperator.INSTANCE.getCommitInfo(this).loadAll();
    }
}
