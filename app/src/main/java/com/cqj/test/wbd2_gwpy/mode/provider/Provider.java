package com.cqj.test.wbd2_gwpy.mode.provider;

import com.cqj.test.wbd2_gwpy.util.WebServiceUtil;

import java.util.ArrayList;
import java.util.HashMap;

import rx.Observable;
import rx.Subscriber;


/**
 * Created by Administrator on 2018/4/2.
 */

public class Provider {
    public static Observable<String> getVersion(final String packageId, final String comId){
        return rx.Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> e) {
                try {
                String[] key = {"packageid", "comid"};
                Object[] value = {packageId, comId};
                ArrayList<HashMap<String, Object>> getVision= WebServiceUtil.getWebServiceMsg(key, value,
                            "getNewPackageVersion", new String[]{"ver"}, WebServiceUtil.HUIWEI_PM_URL,
                            WebServiceUtil.HUIWEI_NAMESPACE);
                    if (getVision != null) {
                        String ver = getVision.get(0).get("ver").toString();
                        e.onNext(ver);
                        e.onCompleted();
                    }
                } catch (Exception e1) {
                    e1.printStackTrace();
                }

            }
        });
    }

}
