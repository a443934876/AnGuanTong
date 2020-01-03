package cn.fjmz.agt.presenter;


import cn.fjmz.agt.base.BaseObserver;
import cn.fjmz.agt.base.BasePresenter;
import cn.fjmz.agt.bean.DocumentDetailEntity;
import cn.fjmz.agt.bean.DocumentEntity;
import cn.fjmz.agt.bean.DocumentListEntity;
import cn.fjmz.agt.presenter.interfaces.DocumentReviewView;
import cn.fjmz.agt.util.DateParseUtil;
import cn.fjmz.agt.util.Utils;
import cn.fjmz.agt.util.WebServiceUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.functions.Function;

/**
 *
 */

public class DocumentReviewPresenter extends BasePresenter<DocumentReviewView> {
    public DocumentReviewPresenter(DocumentReviewView baseView) {
        super(baseView);
    }

    public void getCapacityDocument(final String cDocID) {
        baseView.showLoading();
        addDisposable(Observable.create(new ObservableOnSubscribe<DocumentEntity>() {
            @Override
            public void subscribe(ObservableEmitter<DocumentEntity> emitter) {
                Map<String, Object> map = new HashMap<>();
                map.put("orgIDstr", "");
                map.put("cDocID", cDocID);
                map.put("keyWord", "");
                map.put("cStart", DateParseUtil.getMonth(60));
                map.put("cEnd", DateParseUtil.getEndData());
                map.put("onlyInTitle", false);
                map.put("cState", true);
                map.put("userId", 0);
                map.put("cType", "");
                map.put("docTempId", 0);
                map.put("retstr", "");
                try {
                    String msg = WebServiceUtil.getWebServiceMsgList(map, "getCapacityDocument",
                            WebServiceUtil.HUI_WEI_5VC, WebServiceUtil.HUI_WEI_NAMESPACE);
                    List<DocumentEntity> entity = DocumentEntity.arrayDocumentDetailsEntityFromData(msg);
                    if (entity.size() > 0) {
                        emitter.onNext(entity.get(0));
                    } else {
                        emitter.onComplete();
                    }
                } catch (Exception e) {
                    emitter.onError(e);
                }

            }
        }), new BaseObserver<DocumentEntity>(baseView, true) {

            @Override
            public void onSuccess(DocumentEntity o) {
                baseView.hideLoading();
                baseView.getCapacityDocument(o);
            }

            @Override
            public void onError(String msg) {
                baseView.showError(msg);
            }
        });
    }

    public void setInfoTurning(final String infoId, final String emId, final String cRemark) {
        baseView.showLoading();
        addDisposable(Observable.create(new ObservableOnSubscribe<Object>() {
            @Override
            public void subscribe(ObservableEmitter<Object> emitter) {
                Map<String, Object> map = new HashMap<>();
                map.put("Infoid", infoId);
                map.put("Turnemid", emId);
                map.put("CRemark", cRemark);
                map.put("IPStr", Utils.getSerialNumber());
                try {
                    String msg = WebServiceUtil.getWebServiceMsgList(map, "setInfoTurning",
                            WebServiceUtil.HUI_WEI_5VC, WebServiceUtil.HUI_WEI_NAMESPACE);
                    emitter.onNext(DocumentListEntity.arrayDocumentListEntityFromData(msg));
                } catch (Exception e) {
                    emitter.onError(e);
                }

            }
        }), new BaseObserver<Object>(baseView, true) {

            @Override
            public void onSuccess(Object o) {
                baseView.hideLoading();
                baseView.setInfoTurning(o);
            }

            @Override
            public void onError(String msg) {
                baseView.showError(msg);
            }
        });
    }

    public void getCapacityDocumentDetail(final String orgIdStr, final String cDocId, final String parentCDocID) {
        baseView.showLoading();
        addDisposable(Observable.create(new ObservableOnSubscribe<List<DocumentDetailEntity>>() {
            @Override
            public void subscribe(ObservableEmitter<List<DocumentDetailEntity>> emitter) {
                Map<String, Object> map = new HashMap<>();
                map.put("orgIDstr", orgIdStr);
                map.put("cDocID", cDocId);
                map.put("titleKeyWord", "");
                map.put("detailKeyWord", "");
                map.put("carryPartID", 0);
                map.put("carryDutyID", 0);
                map.put("docType", "");
                map.put("parentCDocID", parentCDocID);
                map.put("cDocDetailID", 0);
                map.put("retstr", "");
                try {
                    String msg = WebServiceUtil.getWebServiceMsgList(map, "getCapacityDocumentDetail",
                            WebServiceUtil.HUI_WEI_5VC, WebServiceUtil.HUI_WEI_NAMESPACE);
                    emitter.onNext(DocumentDetailEntity.arrayDocumentDetailEntityFromData(msg));
                } catch (Exception e) {
                    emitter.onError(e);
                }
            }
        }), new BaseObserver<List<DocumentDetailEntity>>(baseView, true) {

            @Override
            public void onSuccess(List<DocumentDetailEntity> o) {
                baseView.hideLoading();
                baseView.getCapacityDocumentDetail(o);
            }

            @Override
            public void onError(String msg) {
                baseView.showError(msg);
            }
        });
    }


}
