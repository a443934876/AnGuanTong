package cn.fjmz.agt.bean;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class SafetyEntity {

    /**
     * lon : 120.5995
     * cname : 福清永春
     * SCPID : 289
     * nextcheck : 2019-09-24T00:00:00+08:00
     * lastcheck : 2019-08-24T00:00:00+08:00
     * cplocal : 1号车间
     * cpnumber : 7-4
     * cpmaster : 林为祥
     * cpid : 3055
     * cpname : 永春干粉灭火器
     * lat : 28.02039
     * checkcount : 35
     * cpmaintain : 林为祥
     */

    @SerializedName("lon")
    private String lon;
    @SerializedName("cname")
    private String cName;
    @SerializedName("SCPID")
    private String SCPID;
    @SerializedName("nextcheck")
    private String nextCheck;
    @SerializedName("lastcheck")
    private String lastCheck;
    @SerializedName("cplocal")
    private String cpLocal;
    @SerializedName("cpnumber")
    private String cpNumber;
    @SerializedName("cpmaster")
    private String cpMaster;
    @SerializedName("cpid")
    private String cpId;
    @SerializedName("cpname")
    private String cpName;
    @SerializedName("lat")
    private String lat;
    @SerializedName("checkcount")
    private String checkCount;
    @SerializedName("cpmaintain")
    private String cpMaintain;

    public static SafetyEntity objectFromData(String str) {

        return new Gson().fromJson(str, SafetyEntity.class);
    }

    public static List<SafetyEntity> arraySafetyEntityFromData(String str) {

        Type listType = new TypeToken<ArrayList<SafetyEntity>>() {
        }.getType();

        return new Gson().fromJson(str, listType);
    }

    public String getLon() {
        return lon;
    }

    public void setLon(String lon) {
        this.lon = lon;
    }

    public String getCName() {
        return cName;
    }

    public void setCName(String cName) {
        this.cName = cName;
    }

    public String getSCPID() {
        return SCPID;
    }

    public void setSCPID(String SCPID) {
        this.SCPID = SCPID;
    }

    public String getNextCheck() {
        return nextCheck;
    }

    public void setNextCheck(String nextCheck) {
        this.nextCheck = nextCheck;
    }

    public String getLastCheck() {
        return lastCheck;
    }

    public void setLastCheck(String lastCheck) {
        this.lastCheck = lastCheck;
    }

    public String getCpLocal() {
        return cpLocal;
    }

    public void setCpLocal(String cpLocal) {
        this.cpLocal = cpLocal;
    }

    public String getCpNumber() {
        return cpNumber;
    }

    public void setCpNumber(String cpNumber) {
        this.cpNumber = cpNumber;
    }

    public String getCpMaster() {
        return cpMaster;
    }

    public void setCpMaster(String cpMaster) {
        this.cpMaster = cpMaster;
    }

    public String getCpId() {
        return cpId;
    }

    public void setCpId(String cpId) {
        this.cpId = cpId;
    }

    public String getCpName() {
        return cpName;
    }

    public void setCpName(String cpName) {
        this.cpName = cpName;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getCheckCount() {
        return checkCount;
    }

    public void setCheckCount(String checkCount) {
        this.checkCount = checkCount;
    }

    public String getCpMaintain() {
        return cpMaintain;
    }

    public void setCpMaintain(String cpMaintain) {
        this.cpMaintain = cpMaintain;
    }
}
