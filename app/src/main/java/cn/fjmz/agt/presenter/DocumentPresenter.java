package cn.fjmz.agt.presenter;


import cn.fjmz.agt.base.BaseObserver;
import cn.fjmz.agt.base.BasePresenter;
import cn.fjmz.agt.bean.DocumentListEntity;
import cn.fjmz.agt.presenter.interfaces.DocumentView;
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

public class DocumentPresenter extends BasePresenter<DocumentView> {
    public DocumentPresenter(DocumentView baseView) {
        super(baseView);
    }

    public void getWebInformFroEmID(final String emId) {
        baseView.showLoading();
        addDisposable(Observable.create(new ObservableOnSubscribe<List<DocumentListEntity>>() {
            @Override
            public void subscribe(ObservableEmitter<List<DocumentListEntity>> emitter) {
                Map<String, Object> map = new HashMap<>();
                map.put("Emid", emId);
                map.put("DayCount", 0);
                map.put("TopCount", 0);
                map.put("InfoID", 0);
                map.put("viewed", false);
                try {
                    String msg = WebServiceUtil.getWebServiceMsg1(map, "getWebInformFroEmID",
                            WebServiceUtil.HUIWEI_5VIN_URL, WebServiceUtil.HUIWEI_NAMESPACE);
                    emitter.onNext(DocumentListEntity.arrayDocumentListEntityFromData(msg));
                } catch (Exception e) {
                    emitter.onError(e);
                }

            }
        }), new BaseObserver<List<DocumentListEntity>>(baseView, true) {

            @Override
            public void onSuccess(List<DocumentListEntity> o) {
                baseView.hideLoading();
                baseView.getWebInformFroEmID(o);
            }

            @Override
            public void onError(String msg) {
                baseView.showError(msg);
            }
        });
    }


}
