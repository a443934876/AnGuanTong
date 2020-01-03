package cn.fjmz.agt.activity;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;
import cn.fjmz.agt.R;
import cn.fjmz.agt.base.BaseActivity;
import cn.fjmz.agt.base.BaseModel;
import cn.fjmz.agt.base.Constants;
import cn.fjmz.agt.bean.RememberPasswordEntity;
import cn.fjmz.agt.bean.SetPersonalUserEntity;
import cn.fjmz.agt.bean.UserEntity;
import cn.fjmz.agt.presenter.PasswordChangePresenter;
import cn.fjmz.agt.presenter.interfaces.PasswordChangeView;
import cn.fjmz.agt.util.StringUtil;

public class PasswordChangeActivity extends BaseActivity<PasswordChangePresenter> implements PasswordChangeView {
    @BindView(R.id.tv_title_center)
    TextView mTvTitleCenter;
    @BindView(R.id.et_account)
    EditText mEtAccount;
    @BindView(R.id.et_old_password)
    EditText mEtOldPassword;
    @BindView(R.id.et_password)
    EditText mEtPassword;
    @BindView(R.id.et_confirm_password)
    EditText mEtConfirmPassword;
    @BindView(R.id.iv_title_left)
    ImageView mIvTitleLeft;

    public static void launch(Context context) {
        Intent intent = new Intent(context, PasswordChangeActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        context.startActivity(intent);
    }

    @Override
    protected PasswordChangePresenter createPresenter() {
        return new PasswordChangePresenter(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_password_change;
    }

    @Override
    protected void initData() {
        mIvTitleLeft.setImageResource(R.drawable.ic_title_back_arrow);
        mIvTitleLeft.setOnClickListener(view -> finish());
        mTvTitleCenter.setText("密码修改");
        mEtAccount.setText(Constants.userEntity.getAccount());
    }

    @OnClick(R.id.btn_change_password)
    public void onClick(View view) {
        if (view.getId() == R.id.btn_change_password) {
            passwordChange();
        }
    }

    private void passwordChange() {
        String oldPassword = mEtOldPassword.getText().toString();
        String password = mEtPassword.getText().toString();
        String confirmPassword = mEtConfirmPassword.getText().toString();
        if (oldPassword.isEmpty()) {
            showToast("旧密码不能为空！");
            return;
        }
        if (password.isEmpty()) {
            showToast("新密码不能为空！");
            return;
        }
        if (confirmPassword.isEmpty()) {
            showToast("确认密码不能为空！");
            return;
        }
        if (!password.equals(confirmPassword)) {
            showToast("两次输入的密码不一样，请重新输入！");
            return;
        }
        mPresenter.getSinglePersonalUserFromLogin(Constants.userEntity.getAccount(), oldPassword);
    }

    @Override
    public void onErrorCode(BaseModel model) {

    }

    @Override
    public void onGetSinglePersonalUserFromLoginSuccess(UserEntity entity) {
        switch (entity.getRet()) {
            case "0":
                Map<String, Object> map = new HashMap<>();
                map.put("uPeopleID", Integer.valueOf(Constants.companyEntity.getAreaID()));
                map.put("oldPWord", mEtOldPassword.getText().toString());
                map.put("newPWord", mEtPassword.getText().toString());
                map.put("newNickName", mEtAccount.getText().toString());
                map.put("uState", true);
                map.put("setSysUser", true);
                map.put("mPhone", Constants.userEntity.getPhone());
                mPresenter.getSetPersonalUser(map);
                break;
            case "1":
                showToast("用户名不存在");
                break;
            case "2":
                showToast("手机号码不存在");
                break;
            case "3":
                showToast("微信openid不存在");
                break;
            case "4":
                showToast("旧密码不正确");
                break;
            case "5":
                showToast("用户昵称电子邮件手机号码格式不正确(如用户昵称全为数字,用户昵称小于六位,电子邮件格式不正确,手机号码格式不正确等)");
                break;
            case "6":
                showToast("微信公众号未绑定OA");
                break;
            case "-1":
                showToast("没有查到相关用户信息");
                break;
        }

    }

    @Override
    public void onSetPersonalUser(SetPersonalUserEntity entity) {

    }
}
