package cn.fjmz.agt.bean;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class HiddenIllnessEntity {

    /**
     * checkDate : 2019-08-10T00:00:00+08:00
     * hTroubleID : 33630
     * troubleGrade : 一般
     * hiidnumber : 1034617
     * safetyTrouble : 没有防护网
     * induName : 合成材料制造.其他合成材料制造
     * LiabelEmid : 0
     * actionOrgName : 福清永春
     * esCost : 0
     * cpeoname : 林为祥
     * areaName : 中国.福建.福州.福清.新厝
     * limitDate : 2019-08-15T00:00:00+08:00
     * imgpathdot : ../Common/UploadFilse/2019818747655/20190810180412.jpg,
     * 复查人员名单 : anyType#$
     * checkObject : 福清市永春混凝土外加剂有限公司
     */

    @SerializedName("checkDate")
    private String checkDate;
    @SerializedName("hTroubleID")
    private String hTroubleID;
    @SerializedName("troubleGrade")
    private String troubleGrade;
    @SerializedName("hiidnumber")
    private String hiidNumber;
    @SerializedName("safetyTrouble")
    private String safetyTrouble;
    @SerializedName("induName")
    private String induName;
    @SerializedName("LiabelEmid")
    private String LiabelEmid;
    @SerializedName("actionOrgName")
    private String actionOrgName;
    @SerializedName("esCost")
    private String esCost;
    @SerializedName("cpeoname")
    private String cpeoName;
    @SerializedName("areaName")
    private String areaName;
    @SerializedName("limitDate")
    private String limitDate;
    @SerializedName("imgpathdot")
    private String imgPathDot;
    @SerializedName("复查人员名单")
    private String reviewName;
    @SerializedName("checkObject")
    private String checkObject;

    public static HiddenIllnessEntity objectFromData(String str) {

        return new Gson().fromJson(str, HiddenIllnessEntity.class);
    }

    public static List<HiddenIllnessEntity> arrayHiddenIllnessEntityFromData(String str) {

        Type listType = new TypeToken<ArrayList<HiddenIllnessEntity>>() {
        }.getType();

        return new Gson().fromJson(str, listType);
    }

    public String getCheckDate() {
        return checkDate;
    }

    public void setCheckDate(String checkDate) {
        this.checkDate = checkDate;
    }

    public String getHTroubleID() {
        return hTroubleID;
    }

    public void setHTroubleID(String hTroubleID) {
        this.hTroubleID = hTroubleID;
    }

    public String getTroubleGrade() {
        return troubleGrade;
    }

    public void setTroubleGrade(String troubleGrade) {
        this.troubleGrade = troubleGrade;
    }

    public String getHiidNumber() {
        return hiidNumber;
    }

    public void setHiidNumber(String hiidNumber) {
        this.hiidNumber = hiidNumber;
    }

    public String getSafetyTrouble() {
        return safetyTrouble;
    }

    public void setSafetyTrouble(String safetyTrouble) {
        this.safetyTrouble = safetyTrouble;
    }

    public String getInduName() {
        return induName;
    }

    public void setInduName(String induName) {
        this.induName = induName;
    }

    public String getLiabelEmid() {
        return LiabelEmid;
    }

    public void setLiabelEmid(String LiabelEmid) {
        this.LiabelEmid = LiabelEmid;
    }

    public String getActionOrgName() {
        return actionOrgName;
    }

    public void setActionOrgName(String actionOrgName) {
        this.actionOrgName = actionOrgName;
    }

    public String getEsCost() {
        return esCost;
    }

    public void setEsCost(String esCost) {
        this.esCost = esCost;
    }

    public String getCpeoName() {
        return cpeoName;
    }

    public void setCpeoName(String cpeoName) {
        this.cpeoName = cpeoName;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public String getLimitDate() {
        return limitDate;
    }

    public void setLimitDate(String limitDate) {
        this.limitDate = limitDate;
    }

    public String getImgPathDot() {
        return imgPathDot;
    }

    public void setImgPathDot(String imgPathDot) {
        this.imgPathDot = imgPathDot;
    }

    public String getReviewName() {
        return reviewName;
    }

    public void setReviewName(String reviewName) {
        this.reviewName = reviewName;
    }

    public String getCheckObject() {
        return checkObject;
    }

    public void setCheckObject(String checkObject) {
        this.checkObject = checkObject;
    }
}
