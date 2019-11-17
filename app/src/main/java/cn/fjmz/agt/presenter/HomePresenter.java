package cn.fjmz.agt.presenter;


import cn.fjmz.agt.base.BaseObserver;
import cn.fjmz.agt.base.BasePresenter;
import cn.fjmz.agt.bean.ComIndexEntity;
import cn.fjmz.agt.bean.HiddenIllnessEntity;
import cn.fjmz.agt.bean.LessonEntity;
import cn.fjmz.agt.bean.MissionEntity;
import cn.fjmz.agt.bean.SafetyEntity;
import cn.fjmz.agt.presenter.interfaces.HomeView;
import cn.fjmz.agt.util.DateParseUtil;
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

public class HomePresenter extends BasePresenter<HomeView> {
    public HomePresenter(HomeView baseView) {
        super(baseView);
    }

    public void getSafetyIndexFromComSuccess(final String comId) {
        baseView.showLoading();
        addDisposable(Observable.create(new ObservableOnSubscribe<ComIndexEntity>() {
            @Override
            public void subscribe(ObservableEmitter<ComIndexEntity> emitter) {
                Map<String, Object> map = new HashMap<>();
                map.put("Comid", comId);
                map.put("nStart", DateParseUtil.getMonth(1));
                map.put("nEnd", DateParseUtil.getEndData());
                map.put("staIndexID", 7);
                try {
                    String safetyIndex = WebServiceUtil.getWebServiceMsg1(map, "getSafetyIndexFromCom",
                            WebServiceUtil.HUIWEI_SAFE_URL, WebServiceUtil.HUIWEI_NAMESPACE);
                    emitter.onNext(ComIndexEntity.arrayComIndexEntityFromData(safetyIndex).get(0));
                } catch (Exception e) {
                    emitter.onError(e);
                }

            }
        }), new BaseObserver<ComIndexEntity>(baseView, true) {

            @Override
            public void onSuccess(ComIndexEntity o) {
                baseView.hideLoading();
                baseView.getSafetyIndexFromComSuccess(o);
            }

            @Override
            public void onError(String msg) {
                baseView.hideLoading();
                baseView.showError(msg);
            }
        });
    }

    public void getMissionFromEm(final String emId) {
        baseView.showLoading();
        addDisposable(Observable.create(new ObservableOnSubscribe<List<MissionEntity>>() {
            @Override
            public void subscribe(ObservableEmitter<List<MissionEntity>> emitter) {
                Map<String, Object> map = new HashMap<>();
                map.put("emid", emId);
                map.put("actionStart", DateParseUtil.getMonth(60));
                map.put("actionEnd", DateParseUtil.getEndData());
                try {
                    String mission = WebServiceUtil.getWebServiceMsg1(map, "getMissionFromEm",
                            WebServiceUtil.HUIWEI_5VTF_URL, WebServiceUtil.HUIWEI_NAMESPACE);
                    emitter.onNext(MissionEntity.arrayMissionEntityFromData(mission));
                } catch (Exception e) {
                    emitter.onError(e);
                }

            }
        }), new BaseObserver<List<MissionEntity>>(baseView, true) {

            @Override
            public void onSuccess(List<MissionEntity> o) {
                baseView.hideLoading();
                baseView.getMissionFromEm(o);
            }

            @Override
            public void onError(String msg) {
                baseView.hideLoading();
                baseView.showError(msg);
            }
        });
    }

    public void getAllHiddenIllness(final String emId) {
        baseView.showLoading();
        addDisposable(Observable.create(new ObservableOnSubscribe<List<HiddenIllnessEntity>>() {
            @Override
            public void subscribe(ObservableEmitter<List<HiddenIllnessEntity>> emitter) {
                Map<String, Object> map = new HashMap<>();
                map.put("uEmid", emId);
                map.put("isFinished", 2);
                map.put("isReviewed", 2);
                map.put("cStart", DateParseUtil.getMonth(60));
                map.put("cEnd", DateParseUtil.getEndData());
                map.put("hgrade", "");
                map.put("areaRangeID", 0);
                map.put("industryStr", "");
                map.put("objOrgName", "");
                try {
                    String hiddenIllness = WebServiceUtil.getWebServiceMsg1(map, "getAllHiddenIllness",
                            WebServiceUtil.HUIWEI_SAFE_URL, WebServiceUtil.HUIWEI_NAMESPACE);
                    emitter.onNext(HiddenIllnessEntity.arrayHiddenIllnessEntityFromData(hiddenIllness));
                } catch (Exception e) {
                    emitter.onError(e);
                }

            }
        }), new BaseObserver<List<HiddenIllnessEntity>>(baseView, true) {

            @Override
            public void onSuccess(List<HiddenIllnessEntity> o) {
                baseView.hideLoading();
                baseView.getAllHiddenIllness(o);
            }

            @Override
            public void onError(String msg) {
                baseView.hideLoading();
                baseView.showError(msg);
            }
        });
    }

    public void getLessonFromEm(final String emId) {
        baseView.showLoading();
        addDisposable(Observable.create(new ObservableOnSubscribe<List<LessonEntity>>() {
            @Override
            public void subscribe(ObservableEmitter<List<LessonEntity>> emitter) {
                Map<String, Object> map = new HashMap<>();
                map.put("Emid", emId);
                map.put("SStart", DateParseUtil.getMonth(60));
                map.put("SEnd", DateParseUtil.getEndData());
                map.put("IsStudyed", 0);
                map.put("IsExamed", -1);
                try {
                    String lesson = WebServiceUtil.getWebServiceMsg1(map, "getLessonFromEm",
                            WebServiceUtil.HUIWEI_5HR, WebServiceUtil.HUIWEI_NAMESPACE);
                    emitter.onNext(LessonEntity.arrayLessonEntityFromData(lesson));
                } catch (Exception e) {
                    emitter.onError(e);
                }

            }
        }), new BaseObserver<List<LessonEntity>>(baseView, true) {

            @Override
            public void onSuccess(List<LessonEntity> o) {
                baseView.hideLoading();
                baseView.getLessonFromEm(o);
            }

            @Override
            public void onError(String msg) {
                baseView.hideLoading();
                baseView.showError(msg);
            }
        });
    }

    public void getSafetySetList(final String comId, final String emId) {
        baseView.showLoading();
        addDisposable(Observable.create(new ObservableOnSubscribe<List<SafetyEntity>>() {
            @Override
            public void subscribe(ObservableEmitter<List<SafetyEntity>> emitter) {
                Map<String, Object> map = new HashMap<>();
                map.put("SCPID", 0);
                map.put("SCType", "");
                map.put("FliedID", 0);
                map.put("isOver", 0);
                map.put("KEmid", emId);
                map.put("Comid", comId);
                try {
                    String safety = WebServiceUtil.getWebServiceMsg1(map, "getSafetySetList",
                            WebServiceUtil.HUIWEI_SAFE_URL, WebServiceUtil.HUIWEI_NAMESPACE);
                    emitter.onNext(SafetyEntity.arraySafetyEntityFromData(safety));
                } catch (Exception e) {
                    emitter.onError(e);
                }

            }
        }), new BaseObserver<List<SafetyEntity>>(baseView, true) {

            @Override
            public void onSuccess(List<SafetyEntity> o) {
                baseView.hideLoading();
                baseView.getSafetySetList(o);
            }

            @Override
            public void onError(String msg) {
                baseView.hideLoading();
                baseView.showError(msg);
            }
        });
    }


}
