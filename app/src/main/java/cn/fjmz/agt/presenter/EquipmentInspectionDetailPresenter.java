package cn.fjmz.agt.presenter;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.fjmz.agt.base.BaseObserver;
import cn.fjmz.agt.base.BasePresenter;
import cn.fjmz.agt.bean.SafetySetCheckEntity;
import cn.fjmz.agt.bean.SafetySetEntity;
import cn.fjmz.agt.presenter.interfaces.EquipmentInspectionDetailView;
import cn.fjmz.agt.presenter.interfaces.EquipmentInspectionView;
import cn.fjmz.agt.util.DateParseUtil;
import cn.fjmz.agt.util.WebServiceUtil;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;

/**
 *
 */

public class EquipmentInspectionDetailPresenter extends BasePresenter<EquipmentInspectionDetailView> {
    public EquipmentInspectionDetailPresenter(EquipmentInspectionDetailView baseView) {
        super(baseView);
    }

    public void getSafetySetCheck(final String cpId) {
        baseView.showLoading();
        addDisposable(Observable.create(new ObservableOnSubscribe<List<SafetySetCheckEntity>>() {
            @Override
            public void subscribe(ObservableEmitter<List<SafetySetCheckEntity>> emitter) {
                Map<String, Object> map = new HashMap<>();
                map.put("SCPID", 0);
                map.put("CPID", cpId);
                map.put("cStart", DateParseUtil.getMonth(60));
                map.put("cEnd", DateParseUtil.getEndData());
                map.put("cState", "");
                try {
                    String entity = WebServiceUtil.getWebServiceMsgList(map, "getSafetySetCheck",
                            WebServiceUtil.WISE_BUS_5VS, WebServiceUtil.WISEBUS_NAMESPACE);
                    emitter.onNext(SafetySetCheckEntity.arraySafetySetCheckEntityFromData(entity));
                } catch (Exception e) {
                    emitter.onError(e);
                }

            }
        }), new BaseObserver<List<SafetySetCheckEntity>>(baseView, true) {

            @Override
            public void onSuccess(List<SafetySetCheckEntity> o) {
                baseView.hideLoading();
                baseView.getSafetySetCheck(o);
            }

            @Override
            public void onError(String msg) {
                baseView.showError(msg);
            }
        });
    }

    public void AddSafetySetCheck(final String cpID, final String rEmId, final String cRemark, final String cState) {
        baseView.showLoading();
        addDisposable(Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> emitter) {
                Map<String, Object> map = new HashMap<>();
                map.put("CPID", cpID);
                map.put("cDate", DateParseUtil.getEndData());
                map.put("cEmid", 0);
                map.put("rEmid", rEmId);
                map.put("cRemark", cRemark);
                map.put("cState", cState);
                try {
                    String user = WebServiceUtil.getWebServiceMsgStr(map, "AddSafetySetCheck",
                            WebServiceUtil.HUI_WEI_5VS, WebServiceUtil.HUI_WEI_NAMESPACE);
                    emitter.onNext(user);
                } catch (Exception e) {
                    emitter.onError(e);
                }

            }
        }), new BaseObserver<String>(baseView, true) {

            @Override
            public void onSuccess(String o) {
                baseView.hideLoading();
                baseView.AddSafetySetCheck(o);
            }

            @Override
            public void onError(String msg) {
                baseView.showError(msg);
            }
        });
    }


}
