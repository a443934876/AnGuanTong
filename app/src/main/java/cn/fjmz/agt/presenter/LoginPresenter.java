package cn.fjmz.agt.presenter;


import cn.fjmz.agt.base.BaseObserver;
import cn.fjmz.agt.base.BasePresenter;
import cn.fjmz.agt.bean.CompanyEntity;
import cn.fjmz.agt.bean.UserEntity;
import cn.fjmz.agt.presenter.interfaces.LoginView;
import cn.fjmz.agt.util.WebServiceUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;

/**
 *
 */

public class LoginPresenter extends BasePresenter<LoginView> {
    public LoginPresenter(LoginView baseView) {
        super(baseView);
    }

    public void getSinglePersonalUserFromLogin(final String account, final String password) {
        baseView.showLoading();
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
        }), new BaseObserver<UserEntity>(baseView,true) {

            @Override
            public void onSuccess(UserEntity o) {
                baseView.hideLoading();
                baseView.onGetSinglePersonalUserFromLoginSuccess(o);
            }

            @Override
            public void onError(String msg) {
                baseView.showError(msg);
            }
        });
    }

    public void getMoreComs(final String Uid) {
        baseView.showLoading();
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
        }), new BaseObserver<List<CompanyEntity>>(baseView,true) {

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
