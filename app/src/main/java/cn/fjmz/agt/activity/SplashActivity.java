package cn.fjmz.agt.activity;

import android.Manifest;
import android.animation.ValueAnimator;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.TextView;

import cn.fjmz.agt.R;
import cn.fjmz.agt.base.BaseActivity;
import cn.fjmz.agt.base.BaseModel;
import cn.fjmz.agt.base.Constants;
import cn.fjmz.agt.bean.CompanyEntity;
import cn.fjmz.agt.bean.RememberPasswordEntity;
import cn.fjmz.agt.bean.UserEntity;
import cn.fjmz.agt.bean.VersionEntity;
import cn.fjmz.agt.presenter.SplashPresenter;
import cn.fjmz.agt.presenter.interfaces.SplashView;
import cn.fjmz.agt.util.AppUtil;
import cn.fjmz.agt.util.NetWorkUtils;
import com.king.app.dialog.AppDialog;
import com.king.app.dialog.AppDialogConfig;
import com.king.app.updater.AppUpdater;

import java.util.List;

import butterknife.BindView;
import io.realm.Realm;
import pub.devrel.easypermissions.EasyPermissions;

/**
 * Created by Administrator on 2016/3/6.
 */
public class SplashActivity extends BaseActivity<SplashPresenter> implements SplashView {
    @BindView(R.id.tv_text)
    TextView tvText;
    private String[] dotText = {" . ", " . . ", " . . ."};
    ValueAnimator valueAnimator;
    private String mComName;

    @Override
    protected SplashPresenter createPresenter() {
        return new SplashPresenter(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.splash_view;
    }

    @Override
    protected void initData() {
        if (NetWorkUtils.isConnected()) {
            checkPerm();
        } else {
            showToast("当前网络不可用！");
            finish();
        }
        animator();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        valueAnimator.cancel();
    }

    private void animator() {
        if (valueAnimator == null) {
            valueAnimator = ValueAnimator.ofInt(0, 3).setDuration(1000);
            valueAnimator.setRepeatCount(ValueAnimator.INFINITE);
            valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator animation) {
                    int i = (int) animation.getAnimatedValue();
                    tvText.setText(dotText[i % dotText.length]);
                }
            });
        }
        valueAnimator.start();
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    private void checkPerm() {
        String[] params = {Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_PHONE_STATE,};
        EasyPermissions.requestPermissions(this, "安管通应用需要以下权限，请允许", 0, params);
    }

    @Override
    public void onPermissionsGranted(int requestCode, @NonNull List<String> perms) {
        if (requestCode == 0) {
            if (perms.size() > 0) {
                if (perms.contains(Manifest.permission.READ_PHONE_STATE) && perms.contains(Manifest
                        .permission.WRITE_EXTERNAL_STORAGE)) {
                    mPresenter.getNewPackageVersion(Constants.PACKAGE_ID, Constants.COM_ID);
                }
            }
        }
    }


    @Override
    public void onSplashGetVersionSuccess(VersionEntity versionEntity) {
        int code = AppUtil.getVersionCode(this);
        if (versionEntity.getVer() > code) {
            AppDialogConfig config = new AppDialogConfig();
            config.setTitle("安管通版本更新")
                    .setOk("升级")
                    .setContent(versionEntity.getFunmo())
                    .setOnClickCancel(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            AppDialog.INSTANCE.dismissDialog();
                            goToLogin();
                        }
                    })
                    .setOnClickOk(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            new AppUpdater(SplashActivity.this, Constants.URL).start();
                            AppDialog.INSTANCE.dismissDialog();
                        }
                    });
            AppDialog.INSTANCE.showDialog(this, config);
        } else {
            goToLogin();
        }


    }

    private void goToLogin() {
        RememberPasswordEntity entity = getRealm().where(RememberPasswordEntity.class).findFirst();
        if (entity != null) {
            if (entity.getIsAutomaticLogin()) {
                mComName = entity.getComName();
                mPresenter.getSinglePersonalUserFromLogin(entity.getAccount(), entity.getPassword());
            } else {
                LoginActivity.launch(LoginActivity.class, this);
                finish();
            }
        } else {
            LoginActivity.launch(LoginActivity.class, this);
            finish();
        }
    }

    @Override
    public void onGetSinglePersonalUserFromLoginSuccess(UserEntity userEntityList) {
        if (userEntityList.getRet().equals("0")) {
            mPresenter.getMoreComs(userEntityList.getUid());
        } else {
            LoginActivity.launch(LoginActivity.class, this);
            finish();
        }
    }

    @Override
    public void onGetMoreComsSuccess(final List<CompanyEntity> companyEntityList) {
        for (final CompanyEntity company : companyEntityList) {
            if (company.getComName().equals(mComName)) {
                getRealm().executeTransaction(new Realm.Transaction() {
                    @Override
                    public void execute(@NonNull Realm realm) {
                        getRealm().where(CompanyEntity.class).findAll().deleteAllFromRealm();
                        realm.copyToRealm(company);
                    }
                });
                MainActivity.launch(this, company.getComName());
            }
        }
    }

    @Override
    public void onVersionError(String error) {
        showToast(error);
        finish();
    }

    @Override
    public void onErrorCode(BaseModel model) {
        showToast(model.getErrmsg());
        LoginActivity.launch(LoginActivity.class, this);
        finish();
    }

}
