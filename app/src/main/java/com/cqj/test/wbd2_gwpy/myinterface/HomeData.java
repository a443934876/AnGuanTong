package com.cqj.test.wbd2_gwpy.myinterface;

/**
 * Created by Administrator on 2018-05-24.
 */

public interface HomeData {
    void  getRenWuData();
    void  getYinHuanData();
    void  getKeChengData();
    void  getSheShiData();
    interface GetInfo{
        void  getRenWuDataSum(String result);
        void  getYinHuanDataSum(String result);
        void  getKeChengDataSum(String result);
        void  getSheShiDataSum(String result);
    }
}
