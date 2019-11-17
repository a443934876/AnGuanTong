package cn.fjmz.agt.presenter;


import cn.fjmz.agt.base.BaseObserver;
import cn.fjmz.agt.base.BasePresenter;
import cn.fjmz.agt.bean.CompanyEntity;
import cn.fjmz.agt.bean.UserEntity;
import cn.fjmz.agt.bean.VersionEntity;
import cn.fjmz.agt.presenter.interfaces.SplashView;
import cn.fjmz.agt.util.WebServiceUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;

/**
 * File descripition:
 *
 * @author lp
 * @date 2018/6/19
 */

public class SplashPresenter extends BasePresenter<SplashView> {
    public SplashPresenter(SplashView baseView) {
        super(baseView);
    }

    public void getNewPackageVersion(final String packageId, final String comId) {
        addDisposable(Observable.create(new ObservableOnSubscribe<VersionEntity>() {
            @Override
            public void subscribe(ObservableEmitter<VersionEntity> emitter) {
                Map<String, Object> map = new HashMap<>();
                map.put("packageid", packageId);
                map.put("comid", comId);
                try {
                    String version = WebServiceUtil.getWebServiceMsg1(map, "getNewPackageVersion",
                            WebServiceUtil.HUIWEI_5VPM_URL, WebServiceUtil.HUIWEI_NAMESPACE);
                    List<VersionEntity> entityList = VersionEntity.arrayVersionEntityFromData(version);
                    if (entityList.size() > 0) {
                        emitter.onNext(VersionEntity.arrayVersionEntityFromData(version).get(0));
                    }
                } catch (Exception e) {
                    emitter.onError(e);
                }

            }
        }), new BaseObserver<VersionEntity>(baseView) {
            @Override
            public void onSuccess(VersionEntity o) {
                baseView.onSplashGetVersionSuccess(o);
            }

            @Override
            public void onError(String msg) {
                baseView.onVersionError(msg);
            }
        });
    }

    public void getSinglePersonalUserFromLogin(final String account, final String password) {
        addDisposable(Observable.create(new ObservableOnSubscribe<UserEntity>() {
            @Override
            public void subscribe(ObservableEmitter<UserEntity> emitter) {
                Map<String, Object> map = new HashMap<>();
                map.put("requestName", account);
                map.put("mphone", "");
                map.put("email", "");
                map.put("pwd", password);
                map.put("ret", -1);
                try {
                    String user = WebServiceUtil.getWebServiceMsg1(map, "getSinglePersonalUserFromLogin",
                            WebServiceUtil.HUIWEI_URL, WebServiceUtil.HUIWEI_NAMESPACE);
                    emitter.onNext(UserEntity.arrayUserEntityFromData(user).get(0));
                } catch (Exception e) {
                    emitter.onError(e);
                }

            }
        }), new BaseObserver<UserEntity>(baseView) {

            @Override
            public void onSuccess(UserEntity o) {
                baseView.onGetSinglePersonalUserFromLoginSuccess(o);
            }

            @Override
            public void onError(String msg) {
                baseView.showError(msg);
            }
        });
    }

    public void getMoreComs(final String Uid) {
        addDisposable(Observable.create(new ObservableOnSubscribe<List<CompanyEntity>>() {
            @Override
            public void subscribe(ObservableEmitter<List<CompanyEntity>> emitter) {
                Map<String, Object> map = new HashMap<>();
                map.put("uPersonalID", Uid);
                map.put("sState", "在职");
                try {
                    String user = WebServiceUtil.getWebServiceMsg1(map, "getMoreComs",
                            WebServiceUtil.HUIWEI_URL, WebServiceUtil.HUIWEI_NAMESPACE);
                    emitter.onNext(CompanyEntity.arrayCompanyEntityFromData(user));
                } catch (Exception e) {
                    emitter.onError(e);
                }

            }
        }), new BaseObserver<List<CompanyEntity>>(baseView) {

            @Override
            public void onSuccess(List<CompanyEntity> o) {
                baseView.hideLoading();
                baseView.onGetMoreComsSuccess(o);
            }

            @Override
            public void onError(String msg) {
                baseView.showError(msg);
            }
        });
    }
}
