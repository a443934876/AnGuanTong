package cn.fjmz.agt.activity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import cn.fjmz.agt.R;
import cn.fjmz.agt.base.BaseActivity;
import cn.fjmz.agt.base.BaseModel;
import cn.fjmz.agt.base.Constants;
import cn.fjmz.agt.bean.SafetySetCheckEntity;
import cn.fjmz.agt.presenter.EquipmentInspectionDetailPresenter;
import cn.fjmz.agt.presenter.interfaces.EquipmentInspectionDetailView;


public class EquipmentInspectionDetailActivity extends BaseActivity<EquipmentInspectionDetailPresenter> implements EquipmentInspectionDetailView {

    @BindView(R.id.tv_title_center)
    TextView mTvTitleCenter;
    @BindView(R.id.tv_name)
    TextView mTvName;
    @BindView(R.id.tv_record_name)
    TextView mTvRecordName;
    @BindView(R.id.tv_check_stat)
    TextView mTvCheckStat;
    @BindView(R.id.tv_rap_date)
    TextView mTvRapDate;
    @BindView(R.id.tv_check_name)
    TextView mTvCheckName;
    @BindView(R.id.tv_number)
    TextView mTvNumber;
    @BindView(R.id.tv_check_date)
    TextView mTvCheckDate;
    @BindView(R.id.tv_check_detail)
    TextView mTvCheckDetail;
    @BindView(R.id.tv_normal)
    TextView mTvNormal;
    @BindView(R.id.tv_unattended)
    TextView mTvUnattended;
    @BindView(R.id.tv_lost)
    TextView mTvLost;
    @BindView(R.id.tv_damaged)
    TextView mTvDamaged;
    @BindView(R.id.et_content)
    EditText mEtContent;
    @BindView(R.id.iv_title_left)
    ImageView mIvTitleLeft;
    private String mState = "正常";
    private static String mKey_Cp_Id = "cpId";
    public String mCpId;

    public static void launch(Context context, String cpId) {
        Intent intent = new Intent(context, EquipmentInspectionDetailActivity.class);
        intent.putExtra(mKey_Cp_Id, cpId);
        intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        context.startActivity(intent);
    }

    @Override
    protected EquipmentInspectionDetailPresenter createPresenter() {
        return new EquipmentInspectionDetailPresenter(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_equipment_inspection_detail;
    }

    @Override
    protected void initData() {
        mIvTitleLeft.setImageResource(R.drawable.ic_title_back_arrow);
        mIvTitleLeft.setOnClickListener(view -> finish());
        mTvTitleCenter.setText("设备详情");
        mCpId = getIntent().getStringExtra(mKey_Cp_Id);
        mPresenter.getSafetySetCheck(mCpId);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void getSafetySetCheck(List<SafetySetCheckEntity> entity) {
        if (!entity.isEmpty()) {
            mTvName.setText("设备名称：" + entity.get(0).getProdname());
            mTvNumber.setText("设施编号：" + entity.get(0).getScnumber());
            mTvCheckStat.setText("设备状态：" + entity.get(0).getSccheckstat());
            mTvRapDate.setText("报废时间：" + entity.get(0).getScrapdate());
            mTvCheckName.setText("保养人员：" + entity.get(0).getSccheckname());
            mTvRecordName.setText("记录人员：" + entity.get(0).getScrecordname());
            mTvCheckDate.setText("记录日期：" + entity.get(0).getSccheckdate());
            mTvCheckDetail.setText("记录详情：" + entity.get(0).getSccheckdetail());
        }
    }

    @OnClick({R.id.tv_normal, R.id.tv_unattended, R.id.tv_lost, R.id.tv_damaged, R.id.btn_commit})
    public void onClick(@NonNull View v) {
        switch (v.getId()) {
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
            case R.id.btn_commit:
                if (mEtContent.getText().toString().length() < 1) {
                    showToast("对不起！记录内容不能为空！");
                    return;
                }
                mPresenter.AddSafetySetCheck(mCpId, Constants.companyEntity.getEmId(),
                        mEtContent.getText().toString(), mState);
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
    public void AddSafetySetCheck(String entity) {
        if ("".equals(entity)) {
            showToast("提交成功！");
            mEtContent.setText("");
            mPresenter.getSafetySetCheck(mCpId);
        }
    }

    @Override
    public void onErrorCode(BaseModel model) {

    }
}
