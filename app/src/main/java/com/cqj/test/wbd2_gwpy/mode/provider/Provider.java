package com.cqj.test.wbd2_gwpy.mode.provider;

import com.cqj.test.wbd2_gwpy.util.StringUtil;
import com.cqj.test.wbd2_gwpy.util.WebServiceUtil;

import java.util.ArrayList;
import java.util.HashMap;

import rx.Observable;
import rx.Subscriber;


/**
 * Created by Administrator on 2018/4/2.
 */

public class Provider {
    public static Observable<HashMap<String, Object>> getVersion(final String packageId, final String comId){
        return rx.Observable.create(new Observable.OnSubscribe<HashMap<String, Object>>() {
            @Override
            public void call(Subscriber<? super HashMap<String, Object>> e) {
                try {
                    String keys[] = {"packageid", "ver", "comid"};
                    Object values[] = {packageId, "", comId};
                    ArrayList<HashMap<String, Object>> result = WebServiceUtil.getWebServiceMsg(keys, values,
                            "getPackageVersion",
                            WebServiceUtil.HUIWEI_5VPM_URL, WebServiceUtil.HUIWEI_NAMESPACE);
                    HashMap<String, Object> map = new HashMap<>();
                    if (result.size() > 0) {
                        if (StringUtil.isNotEmpty(StringUtil.noNull(result.get(0).get("ver")))) {
                            map.put("ver", result.get(0).get("ver"));
                        } else {
                            map.put("ver", "");
                        }
                        if (StringUtil.isNotEmpty(StringUtil.noNull(result.get(0).get("funmo")))) {
                            map.put("funmo", result.get(0).get("funmo"));
                        } else {
                            map.put("funmo", "");
                        }
                    }
                    e.onNext(map);
                } catch (Exception ep) {
                    ep.printStackTrace();
                    e.onError(ep);
                }
                e.onCompleted();

            }
        });
    }

}
