package cn.fjmz.agt.activity;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import java.util.List;

import butterknife.BindView;
import cn.fjmz.agt.R;
import cn.fjmz.agt.base.BaseActivity;
import cn.fjmz.agt.base.BaseModel;
import cn.fjmz.agt.base.Constants;
import cn.fjmz.agt.bean.CompanyEntity;
import cn.fjmz.agt.bean.RememberPasswordEntity;
import cn.fjmz.agt.bean.UserEntity;
import cn.fjmz.agt.presenter.LoginPresenter;
import cn.fjmz.agt.presenter.interfaces.LoginView;
import cn.fjmz.agt.util.StringUtil;
import io.realm.Realm;

public class LoginActivity extends BaseActivity<LoginPresenter> implements LoginView, View.OnClickListener {

    @BindView(R.id.et_login_account)
    EditText mEtAccount;
    @BindView(R.id.et_login_password)
    EditText mEtPassword;
    //    @BindView(R.id.et_verification_code)
//    EditText mEtVerificationCode;
//    @BindView(R.id.iv_verification_code)
//    ImageView mIvVerificationCode;
    @BindView(R.id.btn_login)
    Button mBtnLogin;
    @BindView(R.id.cb_save_account)
    CheckBox mCbSaveAccount;
    @BindView(R.id.cb_automatic_login)
    CheckBox mCbAutomaticLogin;
//    private String mVerificationCode = "";

    public static void launch(Context context) {
        Intent intent = new Intent(context, LoginActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        context.startActivity(intent);
    }

    @Override
    protected LoginPresenter createPresenter() {
        return new LoginPresenter(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    protected void initData() {
        RememberPasswordEntity entity = getRealm().where(RememberPasswordEntity.class).findFirst();
        if (entity != null) {
            if (entity.getIsSaveAccount()) {
                mEtAccount.setText(entity.getAccount());
                mEtPassword.setText(entity.getPassword());
                mCbSaveAccount.setChecked(true);
            }
        }
//        initComplement();
    }

    @Override
    protected void intiListener() {
        mBtnLogin.setOnClickListener(this);
//        mIvVerificationCode.setOnClickListener(this);
    }

//    private void initComplement() {
////        mIvVerificationCode.setImageBitmap(Code.getInstance().createBitmap());
//        mVerificationCode = Code.getInstance().getCode();
//    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    public void onGetSinglePersonalUserFromLoginSuccess(UserEntity entity) {
        switch (entity.getRet()) {
            case "0":
                Constants.userEntity = entity;
                mPresenter.getMoreComs(entity.getUid());
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
                showToast("密码不正确");
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
    public void onGetMoreComsSuccess(final List<CompanyEntity> companyEntityList) {

        getRealm().executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(@NonNull Realm realm) {
                getRealm().where(CompanyEntity.class).findAll().deleteAllFromRealm();
                for (CompanyEntity company : companyEntityList) {
                    CompanyEntity entity = getRealm().where(CompanyEntity.class).equalTo("orgName", company.getOrgName()).findFirst();
                    if (entity == null) {
                        CompanyEntity newEntity = new CompanyEntity(company);
                        realm.copyToRealm(newEntity);
                    }
                }
            }
        });

        if (companyEntityList.size() > 0) {
            if (mCbSaveAccount.isChecked()) {
                boolean automaticLogin = false;
                if (mCbAutomaticLogin.isChecked()) {
                    automaticLogin = true;
                }
                final boolean finalAutomaticLogin = automaticLogin;
                getRealm().executeTransaction(new Realm.Transaction() {
                    @Override
                    public void execute(@NonNull Realm realm) {
                        realm.where(RememberPasswordEntity.class).findAll().deleteAllFromRealm();
                        RememberPasswordEntity newEntity = new RememberPasswordEntity();
                        newEntity.setAccount(mEtAccount.getText().toString());
                        newEntity.setPassword(mEtPassword.getText().toString());
                        newEntity.setIsSaveAccount(true);
                        newEntity.setIsAutomaticLogin(finalAutomaticLogin);
                        realm.copyToRealm(newEntity);
                    }
                });
            } else {
                getRealm().executeTransaction(new Realm.Transaction() {
                    @Override
                    public void execute(@NonNull Realm realm) {
                        realm.where(RememberPasswordEntity.class).findAll().deleteAllFromRealm();
                    }
                });
            }
            CompanyListActivity.launch(this, companyEntityList.get(0).getEmName());
        }
    }

    @Override
    public void onErrorCode(BaseModel model) {

    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.btn_login) {
            login();
            //            case R.id.iv_verification_code:
//                changeCode();
//                break;
        }
    }

//    private void changeCode() {
//        mIvVerificationCode.setImageBitmap(Code.getInstance().createBitmap());
//        mVerificationCode = Code.getInstance().getCode();
//    }

    private void login() {
        final String account = mEtAccount.getText().toString();
        final String password = mEtPassword.getText().toString();
//        String verificationCode = mEtVerificationCode.getText().toString();
        if (StringUtil.isEmpty(account)) {
            showToast("帐号不能为空！");
            return;
        }
        if (StringUtil.isEmpty(password)) {
            showToast("密码不能为空！");
            return;
        }
//        if (StringUtil.isEmpty(verificationCode)) {
//            showToast("验证码不能为空！");
//            return;
//        }
//        if (!mVerificationCode.equalsIgnoreCase(verificationCode)) {
//            showToast("验证码不正确！");
//            return;
//        }
        mPresenter.getSinglePersonalUserFromLogin(account, password);
    }
}
