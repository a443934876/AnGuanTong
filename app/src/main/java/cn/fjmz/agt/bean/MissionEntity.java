package cn.fjmz.agt.bean;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class MissionEntity {

    /**
     * actionPartName : 安全生产工作领导小组
     * 事务 : 普通安全检查
     * missionDetail : anyType#$
     * missionID : 13297
     * dispathName : 林为祥
     * missionTitle : 福清永春2019年第三季度安全生产大检查任务计划
     */

    @SerializedName("actionPartName")
    private String actionPartName;
    @SerializedName("事务")
    private String transaction;
    @SerializedName("missionDetail")
    private String missionDetail;
    @SerializedName("missionID")
    private String missionID;
    @SerializedName("dispathName")
    private String dispathName;
    @SerializedName("missionTitle")
    private String missionTitle;

    public static MissionEntity objectFromData(String str) {

        return new Gson().fromJson(str, MissionEntity.class);
    }

    public static List<MissionEntity> arrayMissionEntityFromData(String str) {

        Type listType = new TypeToken<ArrayList<MissionEntity>>() {
        }.getType();

        return new Gson().fromJson(str, listType);
    }

    public String getActionPartName() {
        return actionPartName;
    }

    public void setActionPartName(String actionPartName) {
        this.actionPartName = actionPartName;
    }

    public String getTransaction() {
        return transaction;
    }

    public void setTransaction(String transaction) {
        this.transaction = transaction;
    }

    public String getMissionDetail() {
        return missionDetail;
    }

    public void setMissionDetail(String missionDetail) {
        this.missionDetail = missionDetail;
    }

    public String getMissionID() {
        return missionID;
    }

    public void setMissionID(String missionID) {
        this.missionID = missionID;
    }

    public String getDispathName() {
        return dispathName;
    }

    public void setDispathName(String dispathName) {
        this.dispathName = dispathName;
    }

    public String getMissionTitle() {
        return missionTitle;
    }

    public void setMissionTitle(String missionTitle) {
        this.missionTitle = missionTitle;
    }
}
