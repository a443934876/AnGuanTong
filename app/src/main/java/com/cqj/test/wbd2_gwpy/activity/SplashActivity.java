package com.cqj.test.wbd2_gwpy.activity;

import android.Manifest;
import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.widget.Toast;

import com.cqj.test.wbd2_gwpy.CompanyInfo;
import com.cqj.test.wbd2_gwpy.CompanyInfoDao;
import com.cqj.test.wbd2_gwpy.R;
import com.cqj.test.wbd2_gwpy.UserInfo;
import com.cqj.test.wbd2_gwpy.UserInfoDao;
import com.cqj.test.wbd2_gwpy.dao.SqliteOperator;
import com.cqj.test.wbd2_gwpy.mode.provider.Provider;
import com.cqj.test.wbd2_gwpy.util.DownloadIntentService;
import com.cqj.test.wbd2_gwpy.util.GetVersionUtil;
import com.cqj.test.wbd2_gwpy.util.NotificationsUtils;
import com.cqj.test.wbd2_gwpy.util.StringUtil;

import java.util.HashMap;
import java.util.List;

import pub.devrel.easypermissions.AfterPermissionGranted;
import pub.devrel.easypermissions.AppSettingsDialog;
import pub.devrel.easypermissions.EasyPermissions;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Administrator on 2016/3/6.
 */
public class SplashActivity extends Activity implements EasyPermissions.PermissionCallbacks{
    private UserInfoDao mUserInfoDao;
    private CompanyInfoDao mComInfoDao;
    public static final int num = 123;
    public static final String AGT_PACKAGEID = "30";
    public static final String AGT_COMID = "6";
    public static final String AGT_Cache = "/hwagtCache";
    public static final String URL = "http://huiweioa.chinasafety.org/ClientDownload/huiwei_agt_android.apk";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_view);
        checkPerm();

    }

    private void initComplement() {
        mUserInfoDao = SqliteOperator.INSTANCE.getUserInfoDao(this);
        mComInfoDao = SqliteOperator.INSTANCE.getCompanyInfoDao(this);
        Intent intent = new Intent(this, NetworkStateService.class);
        startService(intent);
    }

    private void waitToJump() {
        Observable.create(new Observable.OnSubscribe<Boolean>() {
            @Override
            public void call(Subscriber<? super Boolean> subscriber) {
                try {
                    Thread.sleep(1500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                List<UserInfo> userInfos = mUserInfoDao.loadAll();
                if (userInfos == null || userInfos.isEmpty()) {
                    subscriber.onNext(true);
                } else {
                    UserInfo userInfo = userInfos.get(0);
                    if (userInfo.getIs_login()) {
                        ((MyApplication) getApplication()).setUser(userInfo);
                        List<CompanyInfo> comInfos = mComInfoDao.loadAll();
                        if (comInfos == null || comInfos.isEmpty()) {
                            subscriber.onNext(true);
                            return;
                        } else {
                            for (CompanyInfo companyInfo : comInfos) {
                                if (companyInfo.getIs_choose()) {
                                    ((MyApplication) getApplication()).setComInfo(companyInfo);
                                    break;
                                }
                            }
                        }
                        subscriber.onNext(false);
                    } else {
                        subscriber.onNext(true);
                    }
                }
                subscriber.onCompleted();
            }
        })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Boolean>() {
                    @Override
                    public void onCompleted() {
                        finish();
                    }

                    @Override
                    public void onError(Throwable e) {
                    }

                    @Override
                    public void onNext(Boolean aBoolean) {
                        if (aBoolean) {
                            Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                            startActivity(intent);
                        } else {
                            Intent intent = new Intent(SplashActivity.this, HomePageActivity.class);
                            startActivity(intent);
                        }
                        finish();
                    }
                });
    }



    public void getVersion() {
        Provider.getVersion(AGT_PACKAGEID, AGT_COMID)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<HashMap<String, Object>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(HashMap<String, Object> value) {
                        setNewVersion(value.get("ver").toString(), value.get("funmo").toString());
                    }
                });


    }
    @AfterPermissionGranted(num)
    private void checkPerm() {
        String[] params = {
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.CAMERA,
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.RECORD_AUDIO,
                Manifest.permission.READ_PHONE_STATE,
        };

        if (EasyPermissions.hasPermissions(this, params)) {
            boolean b = StringUtil.isFolderExists(Environment.getExternalStorageDirectory() + AGT_Cache);
            if (b) {
                //获取版本
                getVersion();
            } else {
                Toast.makeText(this, "无法创建目录！", Toast.LENGTH_SHORT).show();
            }
        } else {
            EasyPermissions.requestPermissions(this, "需要权限", num, params);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
    }

    @Override
    public void onPermissionsGranted(int requestCode, List<String> perms) {
        //如果checkPerm方法，没有注解AfterPermissionGranted，也可以在这里调用该方法。

    }

    @Override
    public void onPermissionsDenied(int requestCode, List<String> perms) {
        if (EasyPermissions.somePermissionPermanentlyDenied(this, perms)) {
            //这里需要重新设置Rationale和title，否则默认是英文格式
            new AppSettingsDialog.Builder(this)
                    .setRationale("没有该权限，此应用程序可能无法正常工作。打开应用设置界面以修改应用权限")
                    .setTitle("必需权限")
                    .build()
                    .show();
        }
    }
    public void setNewVersion(String ver, String content) {
        int newVer = GetVersionUtil.getVersion(this);
        if (newVer < Integer.parseInt(ver)) {
            ShowDialog(content);
        } else {
            initComplement();
            waitToJump();
        }

    }
    private void ShowDialog(String content) {
        if (!NotificationsUtils.isNotificationEnabled(SplashActivity.this)) {
            Toast.makeText(this, "未打开通知栏权限，请点击更新跳转手动打开", Toast.LENGTH_LONG).show();
        }
        AlertDialog.Builder builder = new AlertDialog.Builder(this)
                .setTitle("版本更新")
                .setMessage(content)
                .setPositiveButton("更新", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        if (!NotificationsUtils.isNotificationEnabled(SplashActivity.this)) {
                            Intent localIntent = new Intent();
                            localIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            if (Build.VERSION.SDK_INT >= 9) {
                                localIntent.setAction("android.settings.APPLICATION_DETAILS_SETTINGS");
                                localIntent.setData(Uri.fromParts("package", SplashActivity.this.getPackageName(), null));
                            } else if (Build.VERSION.SDK_INT <= 8) {
                                localIntent.setAction(Intent.ACTION_VIEW);
                                localIntent.setClassName("com.android.settings",
                                        "com.android.settings.InstalledAppDetails");
                                localIntent.putExtra("com.android.settings.ApplicationPkgName",
                                        SplashActivity.this.getPackageName());
                            }

                            startActivity(localIntent);
                            finish();
                        } else {
                            appDownload();
                        }

                    }
                })
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        initComplement();
                        waitToJump();
                    }
                });
        AlertDialog alertDialog = builder.create();
        alertDialog.setCancelable(false);//设置这个对话框不能被用户按[返回键]而取消掉
        alertDialog.show();


    }
    private void appDownload() {
        Intent intent = new Intent(this, DownloadIntentService.class);
        intent.putExtra(DownloadIntentService.INTENT_DOWNLOAD_URL, URL);
        startService(intent);

    }

}
