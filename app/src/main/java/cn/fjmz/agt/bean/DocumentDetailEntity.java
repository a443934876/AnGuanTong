package cn.fjmz.agt.bean;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class DocumentDetailEntity {


    /**
     * allpseq : 1
     * parentSeq : 0
     * UserCompanyID : 60
     * overView : 为贯彻“安全第一，预防为主，综合治理”的安全生产方针，保障公司安全生产工作的顺利进行，经公司研究决定，成立安全科，负责全公司的安全生产管理工作，并任命林为祥为安全科主任，张艳为安全科副主任，安全员贺英平、王杰为安全科成员。
     * cDate : 2015-10-04T19:34:00+08:00
     * dSequence : 1
     * carryPartName : 安全生产事故应急救援指挥中心
     * createcom : 福清永春
     * mpart : 2639
     * inTable : anyType#$
     * cDocID : 26681
     * sameratecount : 2
     * cDocDetail : 安全科主任职责
     * cState : 正常
     * cDocTitle : 福清市永春混凝土外加剂有限公司关于设立安全生产管理机构等事项的通知
     * cDocDetailID : 987164
     * cDocType : 公文信息
     * cRemark : anyType#$
     * appcount : 0
     * dLevel : 0
     * inPartID : anyType#$
     */

    @SerializedName("allpseq")
    private String allpseq;
    @SerializedName("parentSeq")
    private String parentSeq;
    @SerializedName("UserCompanyID")
    private String UserCompanyID;
    @SerializedName("overView")
    private String overView;
    @SerializedName("cDate")
    private String cDate;
    @SerializedName("dSequence")
    private String dSequence;
    @SerializedName("carryPartName")
    private String carryPartName;
    @SerializedName("createcom")
    private String createcom;
    @SerializedName("mpart")
    private String mpart;
    @SerializedName("inTable")
    private String inTable;
    @SerializedName("cDocID")
    private String cDocID;
    @SerializedName("sameratecount")
    private String sameratecount;
    @SerializedName("cDocDetail")
    private String cDocDetail;
    @SerializedName("cState")
    private String cState;
    @SerializedName("cDocTitle")
    private String cDocTitle;
    @SerializedName("cDocDetailID")
    private String cDocDetailID;
    @SerializedName("cDocType")
    private String cDocType;
    @SerializedName("cRemark")
    private String cRemark;
    @SerializedName("appcount")
    private String appcount;
    @SerializedName("dLevel")
    private String dLevel;
    @SerializedName("inPartID")
    private String inPartID;

    public static DocumentDetailEntity objectFromData(String str) {

        return new Gson().fromJson(str, DocumentDetailEntity.class);
    }

    public static List<DocumentDetailEntity> arrayDocumentDetailEntityFromData(String str) {

        Type listType = new TypeToken<ArrayList<DocumentDetailEntity>>() {
        }.getType();

        return new Gson().fromJson(str, listType);
    }

    public String getAllpseq() {
        return allpseq;
    }

    public void setAllpseq(String allpseq) {
        this.allpseq = allpseq;
    }

    public String getParentSeq() {
        return parentSeq;
    }

    public void setParentSeq(String parentSeq) {
        this.parentSeq = parentSeq;
    }

    public String getUserCompanyID() {
        return UserCompanyID;
    }

    public void setUserCompanyID(String UserCompanyID) {
        this.UserCompanyID = UserCompanyID;
    }

    public String getOverView() {
        return overView;
    }

    public void setOverView(String overView) {
        this.overView = overView;
    }

    public String getCDate() {
        return cDate;
    }

    public void setCDate(String cDate) {
        this.cDate = cDate;
    }

    public String getDSequence() {
        return dSequence;
    }

    public void setDSequence(String dSequence) {
        this.dSequence = dSequence;
    }

    public String getCarryPartName() {
        return carryPartName;
    }

    public void setCarryPartName(String carryPartName) {
        this.carryPartName = carryPartName;
    }

    public String getCreatecom() {
        return createcom;
    }

    public void setCreatecom(String createcom) {
        this.createcom = createcom;
    }

    public String getMpart() {
        return mpart;
    }

    public void setMpart(String mpart) {
        this.mpart = mpart;
    }

    public String getInTable() {
        return inTable;
    }

    public void setInTable(String inTable) {
        this.inTable = inTable;
    }

    public String getCDocID() {
        return cDocID;
    }

    public void setCDocID(String cDocID) {
        this.cDocID = cDocID;
    }

    public String getSameratecount() {
        return sameratecount;
    }

    public void setSameratecount(String sameratecount) {
        this.sameratecount = sameratecount;
    }

    public String getCDocDetail() {
        return cDocDetail;
    }

    public void setCDocDetail(String cDocDetail) {
        this.cDocDetail = cDocDetail;
    }

    public String getCState() {
        return cState;
    }

    public void setCState(String cState) {
        this.cState = cState;
    }

    public String getCDocTitle() {
        return cDocTitle;
    }

    public void setCDocTitle(String cDocTitle) {
        this.cDocTitle = cDocTitle;
    }

    public String getCDocDetailID() {
        return cDocDetailID;
    }

    public void setCDocDetailID(String cDocDetailID) {
        this.cDocDetailID = cDocDetailID;
    }

    public String getCDocType() {
        return cDocType;
    }

    public void setCDocType(String cDocType) {
        this.cDocType = cDocType;
    }

    public String getCRemark() {
        return cRemark;
    }

    public void setCRemark(String cRemark) {
        this.cRemark = cRemark;
    }

    public String getAppcount() {
        return appcount;
    }

    public void setAppcount(String appcount) {
        this.appcount = appcount;
    }

    public String getDLevel() {
        return dLevel;
    }

    public void setDLevel(String dLevel) {
        this.dLevel = dLevel;
    }

    public String getInPartID() {
        return inPartID;
    }

    public void setInPartID(String inPartID) {
        this.inPartID = inPartID;
    }
}
