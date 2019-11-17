package cn.fjmz.agt.myinterface;

import cn.fjmz.agt.mode.HiddenIllnessInfo;
import cn.fjmz.agt.mode.LessonInfo;
import cn.fjmz.agt.mode.MissionInfo;
import cn.fjmz.agt.mode.SafetySetInfo;

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
