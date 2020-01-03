package cn.fjmz.agt.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.fjmz.agt.R;
import cn.fjmz.agt.base.BaseActivity;
import cn.fjmz.agt.base.BaseModel;
import cn.fjmz.agt.base.BasePresenter;

public class ShareActivity extends BaseActivity {

    @BindView(R.id.tv_title_center)
    TextView tvTitleCenter;
    @BindView(R.id.iv_title_left)
    ImageView mIvTitleLeft;

    public static void launch(Context context) {
        Intent intent = new Intent(context, ShareActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        context.startActivity(intent);
    }

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_share;
    }

    @Override
    protected void initData() {
        mIvTitleLeft.setImageResource(R.drawable.ic_title_back_arrow);
        mIvTitleLeft.setOnClickListener(view -> finish());
        tvTitleCenter.setText("分享");
    }

    @Override
    public void onErrorCode(BaseModel model) {

    }

}
