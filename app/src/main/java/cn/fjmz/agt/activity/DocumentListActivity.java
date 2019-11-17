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
import cn.fjmz.agt.base.Constants;
import cn.fjmz.agt.bean.DocumentListEntity;
import cn.fjmz.agt.presenter.DocumentPresenter;
import cn.fjmz.agt.presenter.interfaces.DocumentView;
import cn.fjmz.agt.util.Utils;

import java.util.List;

import butterknife.BindView;


public class DocumentListActivity extends BaseActivity<DocumentPresenter> implements DocumentView {
    @BindView(R.id.tv_title_center)
    TextView mTvTitleCenter;
    @BindView(R.id.iv_title_left)
    ImageView mIvTitleLeft;
    private BaseQuickAdapter<DocumentListEntity, BaseViewHolder> mAdapter;
    @BindView(R.id.rv_document)
    RecyclerView mRvDocument;

    public static void launch(Context context) {
        Intent intent = new Intent(context, DocumentListActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        context.startActivity(intent);
    }


    @Override
    protected DocumentPresenter createPresenter() {
        return new DocumentPresenter(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_document_list;
    }

    @Override
    protected void initData() {
        mTvTitleCenter.setText("公文信息列表");
        mIvTitleLeft.setImageResource(R.drawable.ic_title_back_arrow);
        mPresenter.getWebInformFroEmID(Constants.entity.getEmId());
        intiRecyclerView();
    }

    private void intiRecyclerView() {
        mAdapter = new BaseQuickAdapter<DocumentListEntity, BaseViewHolder>(R.layout.item_document_list) {
            @Override
            protected void convert(@NonNull BaseViewHolder helper, DocumentListEntity item) {
                helper.setText(R.id.tv_document_title, item.getInfoTitle());
                helper.setText(R.id.tv_document_company, item.getPubComname());
                helper.setText(R.id.tv_document_time, item.getPubDate());
            }
        };
        mRvDocument.setLayoutManager(new LinearLayoutManager(this));
        mRvDocument.setAdapter(mAdapter);
    }

    @Override
    protected void intiListener() {
        super.intiListener();
        mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                DocumentListEntity entity = (DocumentListEntity) adapter.getItem(position);
                if (entity != null) {
                    DocumentReviewActivity.launch(DocumentListActivity.this, entity);
                }
            }
        });
        mIvTitleLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    @Override
    public void onErrorCode(BaseModel model) {

    }

    @Override
    public void getWebInformFroEmID(List<DocumentListEntity> entity) {
        if (entity.size() > 0) {
            mAdapter.setNewData(entity);
        } else {
            mAdapter.setEmptyView(Utils.getEmptyView(this));
        }

    }
}