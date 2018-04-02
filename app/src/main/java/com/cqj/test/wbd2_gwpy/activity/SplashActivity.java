package com.cqj.test.wbd2_gwpy.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import com.cqj.test.wbd2_gwpy.CompanyInfo;
import com.cqj.test.wbd2_gwpy.CompanyInfoDao;
import com.cqj.test.wbd2_gwpy.R;
import com.cqj.test.wbd2_gwpy.UserInfo;
import com.cqj.test.wbd2_gwpy.UserInfoDao;
import com.cqj.test.wbd2_gwpy.dao.SqliteOperator;
import com.cqj.test.wbd2_gwpy.mode.provider.Provider;
import java.util.List;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 *
 * Created by Administrator on 2016/3/6.
 */
public class SplashActivity extends Activity {
    public static final String PACKAGE_ID="30";
    public static final String COM_ID="6";
    private UserInfoDao mUserInfoDao;
    private CompanyInfoDao mComInfoDao;
    private int newVersion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_view);
        if (isVersion()){

        }else {
            initComplement();
            waitToJump();
        }


    }

    private void initComplement() {
        mUserInfoDao = SqliteOperator.INSTANCE.getUserInfoDao(this);
        mComInfoDao = SqliteOperator.INSTANCE.getCompanyInfoDao(this);
        Intent intent =new Intent(this,NetworkStateService.class);
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
                if(userInfos ==null || userInfos.isEmpty()){
                    subscriber.onNext(true);
                }else{
                    UserInfo userInfo =userInfos.get(0);
                    if(userInfo.getIs_login()) {
                        ((MyApplication) getApplication()).setUser(userInfo);
                        List<CompanyInfo> comInfos = mComInfoDao.loadAll();
                        if (comInfos == null || comInfos.isEmpty()) {
                            subscriber.onNext(true);
                            return;
                        } else {
                            for (CompanyInfo companyInfo:comInfos){
                                if(companyInfo.getIs_choose()){
                                    ((MyApplication) getApplication()).setComInfo(companyInfo);
                                    break;
                                }
                            }
                        }
                        subscriber.onNext(false);
                    }else{
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
                if(aBoolean){
                    Intent intent =new Intent(SplashActivity.this,MainActivity.class);
                    startActivity(intent);
                }else{
                    Intent intent =new Intent(SplashActivity.this,HomePageActivity.class);
                    startActivity(intent);
                }
                finish();
            }
        });
    }
    /**
     * 获取版本号
     *
     * @param context Context
     * @return 版本号
     */
    public static int getVersionCode(Context context) {
        PackageInfo pi;
        int code = -1;
        PackageManager pm = context.getPackageManager();
        try {
            pi = pm.getPackageInfo(context.getPackageName(), PackageManager.GET_CONFIGURATIONS);
            code = pi.versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return code;
    }

    public boolean isVersion() {
        Provider.getVersion(PACKAGE_ID,COM_ID)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<String>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(String s) {
                        newVersion= Integer.parseInt(s);
                    }
                });

        return newVersion >getVersionCode(this) ? true:false;
    }
}
