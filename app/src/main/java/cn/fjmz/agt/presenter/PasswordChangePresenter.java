package cn.fjmz.agt.presenter;


import java.util.HashMap;
import java.util.Map;

import cn.fjmz.agt.base.BaseObserver;
import cn.fjmz.agt.base.BasePresenter;
import cn.fjmz.agt.bean.UserEntity;
import cn.fjmz.agt.bean.SetPersonalUserEntity;
import cn.fjmz.agt.presenter.interfaces.PasswordChangeView;
import cn.fjmz.agt.util.WebServiceUtil;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;

/**
 *
 */

public class PasswordChangePresenter extends BasePresenter<PasswordChangeView> {
    public PasswordChangePresenter(PasswordChangeView baseView) {
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
                    String user = WebServiceUtil.getWebServiceMsgList(map, "getSinglePersonalUserFromLogin",
                            WebServiceUtil.HUI_WEI_5VC, WebServiceUtil.HUI_WEI_NAMESPACE);
                    emitter.onNext(UserEntity.arrayUserEntityFromData(user).get(0));
                } catch (Exception e) {
                    emitter.onError(e);
                }

            }
        }), new BaseObserver<UserEntity>(baseView, true) {

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

    public void getSetPersonalUser(final Map<String, Object> map) {
        baseView.showLoading();
        addDisposable(Observable.create(new ObservableOnSubscribe<SetPersonalUserEntity>() {
            @Override
            public void subscribe(ObservableEmitter<SetPersonalUserEntity> emitter) {
                try {
                    String user = WebServiceUtil.getWebServiceMsgStr(map, "setPersonalUser",
                            WebServiceUtil.HUI_WEI_5VC, WebServiceUtil.HUI_WEI_NAMESPACE);
                    emitter.onNext(SetPersonalUserEntity.arraysetPersonalUserEntityFromData(user).get(0));
                } catch (Exception e) {
                    emitter.onError(e);
                }
            }
        }), new BaseObserver<SetPersonalUserEntity>(baseView, true) {

            @Override
            public void onSuccess(SetPersonalUserEntity o) {
                baseView.hideLoading();
                baseView.onSetPersonalUser(o);
            }

            @Override
            public void onError(String msg) {
                baseView.showError(msg);
            }
        });
    }


}
