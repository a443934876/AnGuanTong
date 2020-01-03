package cn.fjmz.agt.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.ListIterator;
import java.util.Set;

import butterknife.BindView;
import butterknife.OnClick;
import cn.fjmz.agt.R;
import cn.fjmz.agt.adapter.SafetySetAdapter;
import cn.fjmz.agt.base.BaseActivity;
import cn.fjmz.agt.base.BaseModel;
import cn.fjmz.agt.base.Constants;
import cn.fjmz.agt.bean.SafetySetEntity;
import cn.fjmz.agt.presenter.EquipmentInspectionPresenter;
import cn.fjmz.agt.presenter.interfaces.EquipmentInspectionView;


/**
 * 安全设施检查
 *
 * @author Administrator
 */
public class EquipmentInspectionActivity extends BaseActivity<EquipmentInspectionPresenter> implements EquipmentInspectionView {
    @BindView(R.id.rv_equipment_inspection)
    RecyclerView mRvEquipmentInspection;
    @BindView(R.id.tv_title_center)
    TextView mTvTitleCenter;
    @BindView(R.id.iv_title_left)
    ImageView mIvTitleLeft;
    @BindView(R.id.tv_title_right)
    TextView mTvTitleRight;
    @BindView(R.id.et_content)
    EditText mEtContent;
    @BindView(R.id.tv_normal)
    TextView mTvNormal;
    @BindView(R.id.tv_unattended)
    TextView mTvUnattended;
    @BindView(R.id.tv_lost)
    TextView mTvLost;
    @BindView(R.id.tv_damaged)
    TextView mTvDamaged;
    @BindView(R.id.sp_place)
    Spinner mSpPlace;
    @BindView(R.id.sp_head)
    Spinner mSpHead;
    private SafetySetAdapter mAdapter;
    private String mState = "正常";
    public List<SafetySetEntity> mEntities;

    public static void launch(Context context) {
        Intent intent = new Intent(context, EquipmentInspectionActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        context.startActivity(intent);
    }

    @Override
    protected EquipmentInspectionPresenter createPresenter() {
        return new EquipmentInspectionPresenter(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_equipment_inspection;
    }

    @Override
    protected void initData() {
        mTvTitleCenter.setText("设备巡查");
        mIvTitleLeft.setImageResource(R.drawable.bg_scan);
        mTvTitleRight.setText("提交");
        initAdapter();
        mPresenter.getSafetySetList(Constants.companyEntity.getComId());
    }

    @OnClick({R.id.iv_title_left, R.id.tv_title_right, R.id.tv_normal, R.id.tv_unattended,
            R.id.tv_lost, R.id.tv_damaged, R.id.btn_query})
    public void onClick(@NonNull View v) {
        switch (v.getId()) {
            case R.id.iv_title_left:
                ScanActivity.launch(this);
                break;
            case R.id.tv_title_right:
                if (mEtContent.getText().toString().length() < 1) {
                    showToast("对不起！记录内容不能为空！");
                    return;
                }
                List<SafetySetEntity> selectList = mAdapter.getSelectList();
                for (SafetySetEntity entity : selectList) {
                    mPresenter.AddSafetySetCheck(entity.getCpid(), Constants.companyEntity.getEmId(), mEtContent.getText().toString(), mState);
                }
                break;
            case R.id.tv_normal:
                mState = "正常";
                changeTextColor(R.color.color_blue, R.color.text_color, R.color.text_color,
                        R.color.text_color, Typeface.BOLD, Typeface.NORMAL, Typeface.NORMAL, Typeface.NORMAL);
                break;
            case R.id.tv_unattended:
                mState = "待修";
                changeTextColor(R.color.text_color, R.color.color_blue, R.color.text_color,
                        R.color.text_color, Typeface.NORMAL, Typeface.BOLD, Typeface.NORMAL, Typeface.NORMAL);
                break;
            case R.id.tv_lost:
                mState = "遗失";
                changeTextColor(R.color.text_color, R.color.text_color, R.color.color_blue,
                        R.color.text_color, Typeface.NORMAL, Typeface.NORMAL, Typeface.BOLD, Typeface.NORMAL);
                break;
            case R.id.tv_damaged:
                mState = "损毁";
                changeTextColor(R.color.text_color, R.color.text_color, R.color.text_color,
                        R.color.color_blue, Typeface.NORMAL, Typeface.NORMAL, Typeface.NORMAL, Typeface.BOLD);
                break;
            case R.id.btn_query:
                String placeSelectedItem = (String) mSpPlace.getSelectedItem();
                String headSelectedItem = (String) mSpHead.getSelectedItem();
                List<SafetySetEntity> entities = new ArrayList<>(mEntities);
                if (placeSelectedItem != null && headSelectedItem != null) {
                    ListIterator<SafetySetEntity> iterator = entities.listIterator();
                    while (iterator.hasNext()) {
                        SafetySetEntity entity = iterator.next();
                        if (!placeSelectedItem.equals(entity.getCplocal())) {
                            iterator.remove();
                        }
                    }
                    ListIterator<SafetySetEntity> iterator1 = entities.listIterator();
                    while (iterator1.hasNext()) {
                        SafetySetEntity entity = iterator1.next();
                        if (!headSelectedItem.equals(entity.getCpmaster())) {
                            iterator1.remove();
                        }
                    }
                    mAdapter.setNewData(entities);
                }
                break;
        }
    }

    private void changeTextColor(int p, int p2, int p3, int p4, int bold, int normal, int normal2, int normal3) {
        mTvNormal.setTextColor(ContextCompat.getColor(this, p));
        mTvUnattended.setTextColor(ContextCompat.getColor(this, p2));
        mTvLost.setTextColor(ContextCompat.getColor(this, p3));
        mTvDamaged.setTextColor(ContextCompat.getColor(this, p4));
        mTvNormal.setTypeface(Typeface.defaultFromStyle(bold));
        mTvUnattended.setTypeface(Typeface.defaultFromStyle(normal));
        mTvLost.setTypeface(Typeface.defaultFromStyle(normal2));
        mTvDamaged.setTypeface(Typeface.defaultFromStyle(normal3));
    }

    @Override
    protected void intiListener() {
        super.intiListener();
        mAdapter.setOnItemClickListener((adapter, view, position) -> {
            SafetySetEntity item = (SafetySetEntity) adapter.getItem(position);
            if (item != null) {
                EquipmentInspectionDetailActivity.launch(mContext, item.getCpid());
            }
        });
    }

    private void initAdapter() {
        mAdapter = new SafetySetAdapter(R.layout.item_equipment_inspection);
        mRvEquipmentInspection.setLayoutManager(new LinearLayoutManager(this));
        mRvEquipmentInspection.setAdapter(mAdapter);
        mRvEquipmentInspection.setHasFixedSize(true);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mPresenter.getSafetySetList(Constants.companyEntity.getComId());
    }

    @Override
    public void onErrorCode(BaseModel model) {

    }

    @Override
    public void getSafetySetList(List<SafetySetEntity> entities) {
        mEntities = entities;
        mAdapter.notifyDataSetChanged();
        mAdapter.setNewData(entities);
        Set<String> placeSet = new HashSet<>();
        Set<String> headSet = new HashSet<>();
        for (SafetySetEntity entity : entities) {
            placeSet.add(entity.getCplocal());
            headSet.add(entity.getCpmaster());
        }
        ArrayAdapter<String> placeAdapter = new ArrayAdapter<>(this, R.layout.item_spinner_select, new ArrayList<>(placeSet));
        placeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mSpPlace.setAdapter(placeAdapter);
        ArrayAdapter<String> headAdapter = new ArrayAdapter<>(this, R.layout.item_spinner_select, new ArrayList<>(headSet));
        headAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mSpHead.setAdapter(headAdapter);
    }

    @Override
    public void AddSafetySetCheck(String entity) {
        if ("".equals(entity)) {
            showToast("提交成功！");
            mEtContent.setText("");
            mPresenter.getSafetySetList(Constants.companyEntity.getComId());
        }
    }
}
