package cn.fjmz.agt.presenter;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.fjmz.agt.base.BaseObserver;
import cn.fjmz.agt.base.BasePresenter;
import cn.fjmz.agt.base.Constants;
import cn.fjmz.agt.bean.SafetySetEntity;
import cn.fjmz.agt.bean.UserEntity;
import cn.fjmz.agt.presenter.interfaces.EquipmentInspectionView;
import cn.fjmz.agt.util.DateParseUtil;
import cn.fjmz.agt.util.WebServiceUtil;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;

/**
 *
 */

public class EquipmentInspectionPresenter extends BasePresenter<EquipmentInspectionView> {
    public EquipmentInspectionPresenter(EquipmentInspectionView baseView) {
        super(baseView);
    }

    public void getSafetySetList(final String comId) {
        baseView.showLoading();
        addDisposable(Observable.create((ObservableOnSubscribe<List<SafetySetEntity>>) emitter -> {
            Map<String, Object> map = new HashMap<>();
            map.put("SCPID", 0);
            map.put("SCType", "");
            map.put("FliedID", 0);
            map.put("isOver", -1);
            map.put("KEmid", 0);
            map.put("Comid", comId);
            try {
                String entity = WebServiceUtil.getWebServiceMsgList(map, "getSafetySetList",
                        WebServiceUtil.HUI_WEI_5VS, WebServiceUtil.HUI_WEI_NAMESPACE);
                emitter.onNext(SafetySetEntity.arraySafetySetEntityFromData(entity));
            } catch (Exception e) {
                emitter.onError(e);
            }

        }), new BaseObserver<List<SafetySetEntity>>(baseView, true) {

            @Override
            public void onSuccess(List<SafetySetEntity> o) {
                baseView.hideLoading();
                baseView.getSafetySetList(o);
            }

            @Override
            public void onError(String msg) {
                baseView.showError(msg);
            }
        });
    }

    public void AddSafetySetCheck(final String cpID, final String rEmId, final String cRemark, final String cState) {
        baseView.showLoading();
        addDisposable(Observable.create((ObservableOnSubscribe<String>) emitter -> {
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
