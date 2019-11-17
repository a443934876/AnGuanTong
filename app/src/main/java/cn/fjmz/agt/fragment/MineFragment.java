package cn.fjmz.agt.fragment;

import cn.fjmz.agt.R;
import cn.fjmz.agt.base.BaseFragment;
import cn.fjmz.agt.base.BaseModel;
import cn.fjmz.agt.base.BasePresenter;

public class MineFragment extends BaseFragment {

    public static MineFragment newInstance() {
        return new MineFragment();
    }


    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_mine;
    }

    @Override
    protected void initData() {

    }


    @Override
    public void onErrorCode(BaseModel model) {

    }
}
