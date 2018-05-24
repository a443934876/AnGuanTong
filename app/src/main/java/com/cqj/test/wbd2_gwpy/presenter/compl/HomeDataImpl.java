package com.cqj.test.wbd2_gwpy.presenter.compl;

import android.app.Activity;

import com.cqj.test.wbd2_gwpy.activity.MyApplication;
import com.cqj.test.wbd2_gwpy.myinterface.HomeData;
import com.cqj.test.wbd2_gwpy.util.StringUtil;
import com.cqj.test.wbd2_gwpy.util.WebServiceUtil;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by Administrator on 2018-05-24.
 */

public class HomeDataImpl implements HomeData {
    private CompositeSubscription mySubscription = new CompositeSubscription();
    private MyApplication myApp;
    private HomeData.GetInfo mGetInfo;


    public HomeDataImpl(Activity activity, HomeData.GetInfo mGetInfo) {
        this.myApp = (MyApplication) activity.getApplication();
        this.mGetInfo = mGetInfo;

    }

    /**
     * 通过时间秒毫秒数判断两个时间的间隔
     *
     * @param date1
     * @param date2
     * @return
     */


    public static int getDays(Date date1, Date date2) {
        int days = (int) ((date2.getTime() - date1.getTime()) / (1000 * 3600 * 24));
        return days;
    }

    @Override
    public void getRenWuData() {
        Subscription subscription = Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                Calendar ca = Calendar.getInstance();
                ca.setTime(new Date());
                Date now = ca.getTime();
                String nowTime = formatter.format(now);
                ca.add(Calendar.YEAR, -1);
                Date lastYear = ca.getTime();
                String lastYearTime = formatter.format(lastYear);
                String keys[] = {"emid", "actionStart", "actionEnd"};
                Object values[] = {myApp.getComInfo().getEmid(), lastYearTime, nowTime};
                try {
                    ArrayList<HashMap<String, Object>> result = WebServiceUtil.getWebServiceMsg(keys, values,
                            "getMissionFromEm",
                            WebServiceUtil.HUIWEI_5VTF_URL, WebServiceUtil.HUIWEI_NAMESPACE);
                    subscriber.onNext(String.valueOf(result.size()));
                } catch (Exception e) {
                    e.printStackTrace();
                }
                subscriber.onCompleted();
            }
        })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<String>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(String result) {
                        mGetInfo.getRenWuDataSum(result);
                    }
                });
        mySubscription.add(subscription);
    }

    @Override
    public void getYinHuanData() {
        Subscription subscription = Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                Calendar ca = Calendar.getInstance();
                ca.setTime(new Date());
                Date now = ca.getTime();
                String nowTime = formatter.format(now);
                ca.add(Calendar.YEAR, -1);
                Date lastYear = ca.getTime();
                String lastYearTime = formatter.format(lastYear);
                String keys[] = {"uEmid", "isFinished", "isReviewed", "cStart", "cEnd",
                        "hgrade", "areaRangeID", "industryStr", "objOrgName"};
                Object values[] = {myApp.getComInfo().getEmid(), 2, 2, lastYearTime, nowTime, "", 0, "", ""};
                try {
                    ArrayList<HashMap<String, Object>> result = WebServiceUtil.getWebServiceMsg(keys, values,
                            "getAllHiddenIllness",
                            WebServiceUtil.HUIWEI_SAFE_URL, WebServiceUtil.HUIWEI_NAMESPACE);
                    int sum = 0;
                    for (HashMap<String, Object> hashMap : result) {
                        String noNull = StringUtil.noNull(hashMap.get("LiabelName"));
                        if (!StringUtil.isEmpty(noNull) && noNull.equals(myApp.getUser().getName())) {
                            sum++;
                        }
                    }
                    subscriber.onNext(String.valueOf(sum));
                } catch (Exception e) {
                    e.printStackTrace();
                }
                subscriber.onCompleted();
            }
        })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<String>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(String result) {
                        mGetInfo.getYinHuanDataSum(result);
                    }
                });
        mySubscription.add(subscription);

    }

    @Override
    public void getKeChengData() {
        Subscription subscription = Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                Calendar ca = Calendar.getInstance();
                ca.setTime(new Date());
                Date now = ca.getTime();
                String nowTime = formatter.format(now);
                ca.add(Calendar.YEAR, -1);
                Date lastYear = ca.getTime();
                String lastYearTime = formatter.format(lastYear);
                String keys[] = {"Emid", "SStart", "SEnd", "IsStudyed", "IsExamed"};
                Object values[] = {myApp.getComInfo().getEmid(), lastYearTime, nowTime, 0, -1};
                try {
                    ArrayList<HashMap<String, Object>> result = WebServiceUtil.getWebServiceMsg(keys, values,
                            "getLessonFromEm",
                            WebServiceUtil.HUIWEI_5HR, WebServiceUtil.HUIWEI_NAMESPACE);
                    subscriber.onNext(String.valueOf(result.size()));
                } catch (Exception e) {
                    e.printStackTrace();
                }
                subscriber.onCompleted();
            }
        })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<String>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(String result) {
                        mGetInfo.getKeChengDataSum(result);
                    }
                });
        mySubscription.add(subscription);

    }

    @Override
    public void getSheShiData() {
        Subscription subscription = Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                Calendar ca = Calendar.getInstance();
                ca.setTime(new Date());
                Date now = ca.getTime();
                String[] keys = {"SCPID", "SCType", "FliedID", "isOver",
                        "KEmid", "Comid"};
                Object[] values = {0, "", 0, -1, 0,
                        Integer.parseInt(myApp.getComInfo().getCom_id())};
                try {
                    ArrayList<HashMap<String, Object>> result = WebServiceUtil.getWebServiceMsg(keys, values,
                            "getSafetySetList",
                            WebServiceUtil.HUIWEI_SAFE_URL, WebServiceUtil.HUIWEI_NAMESPACE);
                    int sum = 0;
                    ArrayList<HashMap<String, Object>> newResult = new ArrayList<>();
                    for (HashMap<String, Object> hashMap : result) {
                        if (hashMap.get("cpmaster").equals(myApp.getUser().getName())) {
                            newResult.add(hashMap);
                        }
                    }
                    for (HashMap<String, Object> hashMap : newResult) {
                        if (hashMap.get("lastcheck") == null) {
                            sum++;
                        } else {
                            String time = hashMap.get("lastcheck").toString().substring(0, hashMap
                                    .get("lastcheck").toString().indexOf("T"));
                            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                            Date date = format.parse(time);
                            int days = getDays(date, now);
                            if (days > 30) {
                                sum++;
                            }
                        }
                    }
                    subscriber.onNext(String.valueOf(sum));
                } catch (Exception e) {
                    e.printStackTrace();
                }
                subscriber.onCompleted();
            }
        })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<String>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(String result) {
                        mGetInfo.getSheShiDataSum(result);
                    }
                });
        mySubscription.add(subscription);

    }
}
