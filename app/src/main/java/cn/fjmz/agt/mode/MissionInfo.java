package cn.fjmz.agt.mode;

import cn.fjmz.agt.util.StringUtil;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Administrator on 2019/1/25.
 */

public class MissionInfo implements Serializable {
    private static final String KEY_TRANSACTION = "事务";
    private static final String KEY_MISSIONTITLE = "missionTitle";
    private static final String KEY_MISSIONLIMIT = "missionLimit";
    private static final String KEY_DISPATHNAME = "dispathName";
    private String missionTitle;
    private String dispathName;
    private String missionLimit;
    private String transaction;

    public String getTransaction() {
        return transaction;
    }

    public void setTransaction(String transaction) {
        this.transaction = transaction;
    }

    public String getMissionTitle() {
        return missionTitle;
    }

    public void setMissionTitle(String missionTitle) {
        this.missionTitle = missionTitle;
    }

    public String getDispathName() {
        return dispathName;
    }

    public void setDispathName(String dispathName) {
        this.dispathName = dispathName;
    }

    public String getMissionLimit() {
        return missionLimit;
    }

    public void setMissionLimit(String missionLimit) {
        this.missionLimit = missionLimit;
    }

    public static ArrayList<MissionInfo> fromData(ArrayList<HashMap<String, Object>> result) {
        if (result == null || result.isEmpty()) {
            return null;
        }
        ArrayList<MissionInfo> missionInfoList = new ArrayList<>();
        for (HashMap<String, Object> list : result) {
            MissionInfo info = new MissionInfo();
            info.setDispathName(StringUtil.noNull(list.get(KEY_DISPATHNAME)));
            String time = StringUtil.noNull(list.get(KEY_MISSIONLIMIT));
            if (time.indexOf("T") > 0) {
                info.setMissionLimit(time.substring(0, time.indexOf("T")));
            } else {
                info.setMissionLimit("");
            }

            info.setMissionTitle(StringUtil.noNull(list.get(KEY_MISSIONTITLE)));
            info.setTransaction(StringUtil.noNull(list.get(KEY_TRANSACTION)));
            missionInfoList.add(info);
        }
        return missionInfoList;
    }
}
