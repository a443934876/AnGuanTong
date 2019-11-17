package cn.fjmz.agt.activity;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import cn.fjmz.agt.R;
import cn.fjmz.agt.base.BaseActivity;
import cn.fjmz.agt.base.BaseModel;
import cn.fjmz.agt.base.BasePresenter;
import cn.fjmz.agt.bean.CompanyEntity;
import cn.fjmz.agt.bean.RememberPasswordEntity;
import cn.fjmz.agt.util.Utils;

import butterknife.BindView;
import io.realm.Realm;
import io.realm.RealmResults;


public class CompanyListActivity extends BaseActivity {

    private static String mEmName = "emName";
    @BindView(R.id.tv_title_center)
    TextView mTvTitleCenter;
    @BindView(R.id.iv_title_left)
    ImageView mIvTitleLeft;
    private BaseQuickAdapter<CompanyEntity, BaseViewHolder> mAdapter;
    @BindView(R.id.rv_company_list)
    RecyclerView mRvCompanyList;

    public static void launch(Context context, String emName) {
        Intent intent = new Intent(context, CompanyListActivity.class);
        intent.putExtra(mEmName, emName);
        intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        context.startActivity(intent);
    }


    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_company_list;
    }

    @Override
    protected void intiListener() {
        super.intiListener();
        mIvTitleLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    @Override
    protected void initData() {
        mIvTitleLeft.setImageResource(R.drawable.ic_title_back_arrow);
        mTvTitleCenter.setText("公司列表");
        String emName = getIntent().getStringExtra(mEmName);
        RealmResults<CompanyEntity> companyEntities = getRealm().where(CompanyEntity.class).
                equalTo("emName", emName).findAll();
        if (companyEntities.size() > 0) {
            mAdapter = new BaseQuickAdapter<CompanyEntity, BaseViewHolder>(R.layout.item_company_list,
                    companyEntities) {
                @Override
                protected void convert(@NonNull BaseViewHolder helper, final CompanyEntity item) {
                    helper.setText(R.id.tv_company_name, item.getComName());
                }
            };
        } else {
            mAdapter.setEmptyView(Utils.getEmptyView(this));
        }
        mRvCompanyList.setLayoutManager(new LinearLayoutManager(this));
        mRvCompanyList.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                final CompanyEntity item = (CompanyEntity) adapter.getItem(position);
                if (item != null) {
                    MainActivity.launch(CompanyListActivity.this, item.getComName());
                    getRealm().executeTransaction(new Realm.Transaction() {
                        @Override
                        public void execute(@NonNull Realm realm) {
                            RememberPasswordEntity entity = getRealm().where(RememberPasswordEntity.class).findFirst();
                            if (entity != null) {
                                entity.setComName(item.getComName());
                            }
                        }
                    });
                    finish();
                }
            }
        });
    }

    @Override
    public void onErrorCode(BaseModel model) {

    }

}
