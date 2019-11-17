package cn.fjmz.agt.presenter.compl;

import cn.fjmz.agt.App;
import cn.fjmz.agt.YhfcInfo;
import cn.fjmz.agt.YhfcInfoDao;
import cn.fjmz.agt.dao.SqliteOperator;
import cn.fjmz.agt.presenter.IYhfcPresenter;
import cn.fjmz.agt.util.StringUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Administrator on 2016/5/4.
 */
public class YhfcPresenterImpl implements IYhfcPresenter {

    private IYhfcPresenter.View mView;

    public YhfcPresenterImpl(IYhfcPresenter.View pView) {
        mView = pView;
    }

    @Override
    public void getYhfcList(final App pApp, final String startDate, final String endDate, final String name) {
        mView.pendingDialog();
//        Observable.create(new Observable.OnSubscribe<List<YhfcInfo>>() {
//            @Override
//            public void call(Subscriber<? super List<YhfcInfo>> pSubscriber) {
//                try {
//                    if (!App.isConnection) {
//                        try {
//                            YhfcInfoDao yhfcInfoDao = SqliteOperator.INSTANCE.getYhfcInfoDao(mView.getContext());
//                            List<YhfcInfo> result = yhfcInfoDao.queryBuilder().where(YhfcInfoDao.Properties.IsYhzg.eq(mView.isYhzg())).list();
//                            if (result == null || result.isEmpty()) {
//                                result = new ArrayList<YhfcInfo>();
//                            }
//                            pSubscriber.onNext(result);
//                            pSubscriber.onCompleted();
//                        } catch (Exception pE) {
//                            pE.printStackTrace();
//                            pSubscriber.onError(pE);
//                        }
//                        return;
//                    }
//                    String keys2[] = {"uComid","isFinished", "isReviewed",  "cStart",
//                            "cEnd", "hgrade", "areaRangeID", "industryStr", "objOrgName"};
//                    int comid = Integer.parseInt(pApp.getComInfo().getCom_id());
//                    String sDate = "2000-01-01T00:00:00.000";
//                    if (!TextUtils.isEmpty(startDate)) {
//                        sDate = String.format("%sT00:00:00.000", startDate);
//                    }
//                    String eDate = String.format("%sT00:00:00.000", DateFormat.format("yyyy-MM-dd",new Date()));
//                    if (!TextUtils.isEmpty(endDate)) {
//                        eDate = String.format("%sT00:00:00.000", endDate);
//                    }
//                    Object values2[] = {comid, mView.getFinished(),mView.getReview(), sDate, eDate, "", 0, "", name};
//                    ArrayList<HashMap<String, Object>> result = WebServiceUtil.getWebServiceMsg(keys2, values2,
//                            "getRelHiddenIllness",
//                            WebServiceUtil.HUIWEI_SAFE_URL,WebServiceUtil.HUIWEI_NAMESPACE);
//                    pSubscriber.onNext(parseYhfcInfo(result));
//                } catch (Exception e) {
//                    e.printStackTrace();
//                    pSubscriber.onError(e);
//                }
//                pSubscriber.onCompleted();
//            }
//        })
//                .map(new Func1<List<YhfcInfo>, List<YhfcInfo>>() {
//                    @Override
//                    public List<YhfcInfo> call(List<YhfcInfo> pYhfcInfoList) {
//                        Collections.sort(pYhfcInfoList, new Comparator<YhfcInfo>() {
//                            @Override
//                            public int compare(YhfcInfo pYhfcInfo, YhfcInfo pT1) {
//                                int ret = 0;
//                                try {
//                                    Calendar otherCalendar = Calendar.getInstance();
//                                    SimpleDateFormat sformat = new SimpleDateFormat("yyyy-MM-dd");
//                                    Date date = sformat.parse(pYhfcInfo.getCheckDate());
//                                    otherCalendar.setTime(date);
//                                    Calendar nowCalendar = Calendar.getInstance();
//                                    Date serDate = sformat.parse(pT1.getCheckDate());
//                                    nowCalendar.setTime(serDate);
//                                    ret = nowCalendar.compareTo(otherCalendar);
//                                } catch (ParseException pE) {
//                                    pE.printStackTrace();
//                                }
//                                /**
//                                 * 0 if the times of the two Calendars are equal, -1 if the time of
//                                 * this Calendar is before the other one, 1 if the time of this
//                                 * Calendar is after the other one.
//                                 */
//                                return ret;
//                            }
//                        });
//                        return pYhfcInfoList;
//                    }
//                })
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribeOn(Schedulers.io())
//                .subscribe(new Subscriber<List<YhfcInfo>>() {
//                    @Override
//                    public void onCompleted() {
//                        mView.cancelDialog();
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//                        mView.cancelDialog();
//                        mView.toast(e.getMessage());
//                    }
//
//                    @Override
//                    public void onNext(List<YhfcInfo> pYhfcInfos) {
//                        mView.getYhfcListSuccess(pYhfcInfos);
//                    }
//                });
    }

    private List<YhfcInfo> parseYhfcInfo(ArrayList<HashMap<String, Object>> pResult) {
        List<YhfcInfo> infos = new ArrayList<YhfcInfo>();
        for (HashMap<String, Object> map : pResult) {
            YhfcInfo yhInfo = new YhfcInfo();
            yhInfo.setActionOrgName(StringUtil.noNull(map.get("actionOrgName")));
            yhInfo.setIsYhzg(mView.isYhzg());
            yhInfo.setAreaName(StringUtil.noNull(map.get("areaName")));
            yhInfo.setCheckDate(StringUtil.noNull(map.get("checkDate")));
            yhInfo.setCheckObject(StringUtil.noNull(map.get("checkObject")));
            yhInfo.setDightCost(StringUtil.noNull(map.get("dightCost")));
            yhInfo.setEsCost(StringUtil.noNull(map.get("esCost")));
            yhInfo.setFinishDate(StringUtil.noNull(map.get("finishDate")));
            yhInfo.setHTroubleID(StringUtil.noNull(map.get("hTroubleID")));
            yhInfo.setInduName(StringUtil.noNull(map.get("induName")));
            yhInfo.setLiabelEmid(StringUtil.noNull(map.get("LiabelEmid")));
            yhInfo.setLiabelName(StringUtil.noNull(map.get("LiabelName")));
            yhInfo.setLimitDate(StringUtil.noNull(map.get("limitDate")));
            yhInfo.setReviewDate(StringUtil.noNull(map.get("reviewDate")));
            yhInfo.setSafetyTrouble(StringUtil.noNull(map.get("safetyTrouble")));
            yhInfo.setTroubleGrade(StringUtil.noNull(map.get("troubleGrade")));
            infos.add(yhInfo);
        }
        YhfcInfoDao yhfcInfoDao = SqliteOperator.INSTANCE.getYhfcInfoDao(mView.getContext());
        yhfcInfoDao.deleteAll();
        yhfcInfoDao.insertInTx(infos);
        return infos;
    }
}
