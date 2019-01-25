package com.cqj.test.wbd2_gwpy.myinterface;

import com.cqj.test.wbd2_gwpy.mode.HiddenIllnessInfo;
import com.cqj.test.wbd2_gwpy.mode.LessonInfo;
import com.cqj.test.wbd2_gwpy.mode.MissionInfo;
import com.cqj.test.wbd2_gwpy.mode.SafetySetInfo;

import java.util.ArrayList;

/**
 * Created by Administrator on 2018-05-24.
 */

public interface HomeData {
    void  getRenWuData();
    void  getYinHuanData();
    void  getKeChengData();
    void  getSheShiData();
    interface GetInfo{
        void  getRenWuDataSum(ArrayList<MissionInfo> result);
        void  getYinHuanDataSum(ArrayList<HiddenIllnessInfo> result);
        void  getKeChengDataSum(ArrayList<LessonInfo> result);
        void  getSheShiDataSum(ArrayList<SafetySetInfo> result);
    }
}
