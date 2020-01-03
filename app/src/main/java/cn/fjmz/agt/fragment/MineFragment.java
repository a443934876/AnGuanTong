package cn.fjmz.agt.fragment;

import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import cn.fjmz.agt.R;
import cn.fjmz.agt.activity.AboutActivity;
import cn.fjmz.agt.activity.LoginActivity;
import cn.fjmz.agt.activity.PasswordChangeActivity;
import cn.fjmz.agt.activity.ShareActivity;
import cn.fjmz.agt.base.BaseFragment;
import cn.fjmz.agt.base.BaseModel;
import cn.fjmz.agt.base.BasePresenter;
import cn.fjmz.agt.bean.MineItemEntity;
import cn.fjmz.agt.util.ActivityManageUtils;

public class MineFragment extends BaseFragment {

    @BindView(R.id.tv_title_center)
    TextView mTvTitleCenter;
    @BindView(R.id.rv_mine)
    RecyclerView rvMine;
    private BaseQuickAdapter<MineItemEntity, BaseViewHolder> mAdapter;
    Integer[] mImages = {R.drawable.icon_password_change, R.drawable.icon_share,
            R.drawable.icon_about, R.drawable.icon_out};
    String[] mTitles = {"密码修改", "分享", "关于", "退出登录"};

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
        mTvTitleCenter.setText("个人中心");
        intAdapter();
        getData();
    }

    private void getData() {
        List<MineItemEntity> list = new ArrayList<>();
        for (int i = 0; i < mTitles.length; i++) {
            MineItemEntity entity = new MineItemEntity();
            entity.setTitle(mTitles[i]);
            entity.setImage(mImages[i]);
            list.add(entity);
        }
        mAdapter.setNewData(list);
    }

    private void intAdapter() {
        mAdapter = new BaseQuickAdapter<MineItemEntity, BaseViewHolder>(R.layout.item_mine) {
            @Override
            protected void convert(@NonNull BaseViewHolder helper, MineItemEntity item) {
                helper.setText(R.id.tv_title, item.getTitle());
                helper.setImageResource(R.id.iv_image, item.getImage());
            }
        };
        rvMine.setLayoutManager(new LinearLayoutManager(mContext));
        rvMine.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                MineItemEntity item = (MineItemEntity) adapter.getItem(position);
                if (item != null) {
                    switch (item.getTitle()) {
                        case "密码修改":
                            PasswordChangeActivity.launch(mContext);
                            break;
                        case "分享":
                            ShareActivity.launch(mContext);
                            break;
                        case "关于":
                            AboutActivity.launch(mContext);
                            break;
                        case "退出登录":
                            LoginActivity.launch(mContext);
                            ActivityManageUtils.getInstance().finishNHomeActivity(LoginActivity.class);
                            break;
                    }
                }

            }
        });
    }


    @Override
    public void onErrorCode(BaseModel model) {

    }

}
