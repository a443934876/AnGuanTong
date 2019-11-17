package cn.fjmz.agt.presenter;


import cn.fjmz.agt.base.BaseObserver;
import cn.fjmz.agt.base.BasePresenter;
import cn.fjmz.agt.bean.PlaceEntity;
import cn.fjmz.agt.bean.SafetyCheck;
import cn.fjmz.agt.presenter.interfaces.SafetyCheckView;
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

public class SafetyCheckPresenter extends BasePresenter<SafetyCheckView> {
    public SafetyCheckPresenter(SafetyCheckView baseView) {
        super(baseView);
    }

    public void getSafetyCheckList(final String comId) {
        baseView.showLoading();
        addDisposable(Observable.create(new ObservableOnSubscribe<List<SafetyCheck>>() {
            @Override
            public void subscribe(ObservableEmitter<List<SafetyCheck>> emitter) {
                Map<String, Object> map = new HashMap<>();
                map.put("SCheckID", 0);
                map.put("CDocID", 0);
                map.put("InduID", 0);
                map.put("FieldID", 0);
                map.put("ProName", "");
                map.put("Proclid", 0);
                map.put("Comid", comId);
                try {
                    String safetyCheckList = WebServiceUtil.getWebServiceMsg1(map, "getSafetyCheckList",
                            WebServiceUtil.HUIWEI_SAFE_URL, WebServiceUtil.HUIWEI_NAMESPACE);
                    emitter.onNext(SafetyCheck.arraySafetyCheckFromData(safetyCheckList));
                } catch (Exception e) {
                    emitter.onError(e);
                }

            }
        }), new BaseObserver<List<SafetyCheck>>(baseView,true) {

            @Override
            public void onSuccess(List<SafetyCheck> o) {
                baseView.hideLoading();
                baseView.getSafetyCheckList(o);
            }

            @Override
            public void onError(String msg) {
                baseView.hideLoading();
                baseView.showError(msg);
            }
        });
    }

    public void getAllPlace(final String comId) {
        addDisposable(Observable.create(new ObservableOnSubscribe<List<PlaceEntity>>() {
            @Override
            public void subscribe(ObservableEmitter<List<PlaceEntity>> emitter) {
                Map<String, Object> map = new HashMap<>();
                map.put("comid", comId);
                map.put("keepEmid", 0);
                try {
                    String place = WebServiceUtil.getWebServiceMsg1(map, "getAllPlace",
                            WebServiceUtil.HUIWEI_URL, WebServiceUtil.HUIWEI_NAMESPACE);
                    emitter.onNext(PlaceEntity.arrayPlaceEntityFromData(place));
                } catch (Exception e) {
                    emitter.onError(e);
                }

            }
        }), new BaseObserver<List<PlaceEntity>>(baseView,true) {

            @Override
            public void onSuccess(List<PlaceEntity> o) {
                baseView.getAllPlace(o);
            }

            @Override
            public void onError(String msg) {
                baseView.showError(msg);
            }
        });
    }


}
