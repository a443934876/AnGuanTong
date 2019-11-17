package cn.fjmz.agt.presenter.compl;

import cn.fjmz.agt.myinterface.HomeData;

/**
 * Created by Administrator on 2018-05-24.
 */

public class HomeDataImpl implements HomeData {
    @Override
    public void getRenWuData() {

    }

    @Override
    public void getYinHuanData() {

    }

    @Override
    public void getKeChengData() {

    }

    @Override
    public void getSheShiData() {

    }
//    private CompositeSubscription mySubscription = new CompositeSubscription();
//    private App myApp;
//    private HomeData.GetInfo mGetInfo;
//
//
//    public HomeDataImpl(Activity activity, HomeData.GetInfo mGetInfo) {
//        this.myApp = (App) activity.getApplication();
//        this.mGetInfo = mGetInfo;
//
//    }
//
//    /**
//     * 通过时间秒毫秒数判断两个时间的间隔
//     *
//     * @param date1
//     * @param date2
//     * @return
//     */
//
//
//    public static int getDays(Date date1, Date date2) {
//        int days = (int) ((date2.getTime() - date1.getTime()) / (1000 * 3600 * 24));
//        return days;
//    }
//
//    @Override
//    public void getRenWuData() {
//        Subscription subscription = Observable.create(new Observable.OnSubscribe<ArrayList<MissionInfo>>() {
//            @Override
//            public void call(Subscriber<? super ArrayList<MissionInfo>> subscriber) {
//                @SuppressLint("SimpleDateFormat") SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
//                Calendar ca = Calendar.getInstance();
//                ca.setTime(new Date());
//                Date now = ca.getTime();
//                String nowTime = formatter.format(now);
//                ca.add(Calendar.YEAR, -1);
//                Date lastYear = ca.getTime();
//                String lastYearTime = formatter.format(lastYear);
//                String keys[] = {"emid", "actionStart", "actionEnd"};
//                Object values[] = {myApp.getComInfo().getEmid(), lastYearTime, nowTime};
//                try {
//                    ArrayList<HashMap<String, Object>> result = WebServiceUtil.getWebServiceMsg(keys, values,
//                            "getMissionFromEm",
//                            WebServiceUtil.HUIWEI_5VTF_URL, WebServiceUtil.HUIWEI_NAMESPACE);
//                    ArrayList<MissionInfo> info = MissionInfo.fromData(result);
//                    subscriber.onNext(info);
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//                subscriber.onCompleted();
//            }
//        })
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Subscriber<ArrayList<MissionInfo>>() {
//                    @Override
//                    public void onCompleted() {
//
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//
//                    }
//
//                    @Override
//                    public void onNext(ArrayList<MissionInfo> result) {
//                        mGetInfo.getRenWuDataSum(result);
//                    }
//                });
//        mySubscription.add(subscription);
//    }
//
//    @Override
//    public void getYinHuanData() {
//        Subscription subscription = Observable.create(new Observable.OnSubscribe<ArrayList<HiddenIllnessInfo>>() {
//            @Override
//            public void call(Subscriber<? super ArrayList<HiddenIllnessInfo>> subscriber) {
//                @SuppressLint("SimpleDateFormat") SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
//                Calendar ca = Calendar.getInstance();
//                ca.setTime(new Date());
//                Date now = ca.getTime();
//                String nowTime = formatter.format(now);
//                ca.add(Calendar.YEAR, -1);
//                Date lastYear = ca.getTime();
//                String lastYearTime = formatter.format(lastYear);
//                String keys[] = {"uEmid", "isFinished", "isReviewed", "cStart", "cEnd",
//                        "hgrade", "areaRangeID", "industryStr", "objOrgName"};
//                Object values[] = {myApp.getComInfo().getEmid(), 2, 2, lastYearTime, nowTime, "", 0, "", ""};
//                try {
//                    ArrayList<HashMap<String, Object>> result = WebServiceUtil.getWebServiceMsg(keys, values,
//                            "getAllHiddenIllness",
//                            WebServiceUtil.HUIWEI_SAFE_URL, WebServiceUtil.HUIWEI_NAMESPACE);
//                    ArrayList<HiddenIllnessInfo> hiddenIllnessInfo = HiddenIllnessInfo.fromData(result);
//                    subscriber.onNext(hiddenIllnessInfo);
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//                subscriber.onCompleted();
//            }
//        })
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Subscriber<ArrayList<HiddenIllnessInfo>>() {
//                    @Override
//                    public void onCompleted() {
//
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//
//                    }
//
//                    @Override
//                    public void onNext(ArrayList<HiddenIllnessInfo> result) {
//                        mGetInfo.getYinHuanDataSum(result);
//                    }
//                });
//        mySubscription.add(subscription);
//
//    }
//
//    @Override
//    public void getKeChengData() {
//        Subscription subscription = Observable.create(new Observable.OnSubscribe<ArrayList<LessonInfo>>() {
//            @Override
//            public void call(Subscriber<? super ArrayList<LessonInfo>> subscriber) {
//                @SuppressLint("SimpleDateFormat") SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
//                Calendar ca = Calendar.getInstance();
//                ca.setTime(new Date());
//                Date now = ca.getTime();
//                String nowTime = formatter.format(now);
//                ca.add(Calendar.YEAR, -1);
//                Date lastYear = ca.getTime();
//                String lastYearTime = formatter.format(lastYear);
//                String keys[] = {"Emid", "SStart", "SEnd", "IsStudyed", "IsExamed"};
//                Object values[] = {myApp.getComInfo().getEmid(), lastYearTime, nowTime, 0, -1};
//                try {
//                    ArrayList<HashMap<String, Object>> result = WebServiceUtil.getWebServiceMsg(keys, values,
//                            "getLessonFromEm",
//                            WebServiceUtil.HUIWEI_5HR, WebServiceUtil.HUIWEI_NAMESPACE);
//                    ArrayList<LessonInfo> lessonInfo = LessonInfo.fromData(result);
//                    subscriber.onNext(lessonInfo);
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//                subscriber.onCompleted();
//            }
//        })
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Subscriber<ArrayList<LessonInfo>>() {
//                    @Override
//                    public void onCompleted() {
//
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//
//                    }
//
//                    @Override
//                    public void onNext(ArrayList<LessonInfo> result) {
//                        mGetInfo.getKeChengDataSum(result);
//                    }
//                });
//        mySubscription.add(subscription);
//
//    }
//
//    @Override
//    public void getSheShiData() {
//        Subscription subscription = Observable.create(new Observable.OnSubscribe<ArrayList<SafetySetInfo>>() {
//            @Override
//            public void call(Subscriber<? super ArrayList<SafetySetInfo>> subscriber) {
//                @SuppressLint("SimpleDateFormat") SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
//                Calendar ca = Calendar.getInstance();
//                ca.setTime(new Date());
//                Date now = ca.getTime();
//                String[] keys = {"SCPID", "SCType", "FliedID", "isOver",
//                        "KEmid", "Comid"};
//                Object[] values = {0, "", 0, -1, Integer.parseInt(myApp.getComInfo().getEmid()),
//                        Integer.parseInt(myApp.getComInfo().getCom_id())};
//                try {
//                    ArrayList<HashMap<String, Object>> result = WebServiceUtil.getWebServiceMsg(keys, values,
//                            "getSafetySetList",
//                            WebServiceUtil.HUIWEI_SAFE_URL, WebServiceUtil.HUIWEI_NAMESPACE);
//                    for (int i = 0; i < result.size(); i++) {
//                        if (result.get(i).get("lastcheck") != null) {
//                            String time = result.get(i).get("lastcheck").toString().substring(0, result.get(i)
//                                    .get("lastcheck").toString().indexOf("T"));
//                            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
//                            Date date = format.parse(time);
//                            int days = getDays(date, now);
//                            if (days < 0) {
//                                result.remove(i);
//                            }
//                        }
//                    }
//                    ArrayList<SafetySetInfo> safetySetInfoList = SafetySetInfo.fromData(result);
//                    subscriber.onNext(safetySetInfoList);
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//                subscriber.onCompleted();
//            }
//        })
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Subscriber<ArrayList<SafetySetInfo>>() {
//                    @Override
//                    public void onCompleted() {
//
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//
//                    }
//
//                    @Override
//                    public void onNext(ArrayList<SafetySetInfo> result) {
//                        mGetInfo.getSheShiDataSum(result);
//                    }
//                });
//        mySubscription.add(subscription);
//
//    }
}
