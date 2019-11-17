package cn.fjmz.agt.bean;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class SafetyCheck {

    /**
     * induname : 其他合成材料制造
     * mtypeid : 21
     * odetail : 燃料、蒸汽、水、电有计量，有考核，有记录，有成本核算
     * sCListID : 7497
     * MalTypeID : 21
     * malName : 粉尘爆炸
     * dSeq : 1
     * obdeid : 2625470
     * InsecureID : 167
     * oblititle : 福清市永春混凝土外加剂有限公司2018版锅炉房安全检查表
     * inseName : 其他现场管理
     * pSeq : 5
     * pdetail : 节能与环保
     * dLevel : 1
     * oblid : 104341
     * oalldetail : 节能与环保   -->燃料、蒸汽、水、电有计量，有考核，有记录，有成本核
     * pLevel : 0
     */

    @SerializedName("induname")
    private String induname;
    @SerializedName("mtypeid")
    private String mtypeid;
    @SerializedName("odetail")
    private String odetail;
    @SerializedName("sCListID")
    private String sCListID;
    @SerializedName("MalTypeID")
    private String MalTypeID;
    @SerializedName("malName")
    private String malName;
    @SerializedName("dSeq")
    private String dSeq;
    @SerializedName("obdeid")
    private String obdeId;
    @SerializedName("InsecureID")
    private String insecureID;
    @SerializedName("oblititle")
    private String obliTitle;
    @SerializedName("inseName")
    private String inseName;
    @SerializedName("pSeq")
    private String pSeq;
    @SerializedName("pdetail")
    private String pDetail;
    @SerializedName("dLevel")
    private String dLevel;
    @SerializedName("oblid")
    private String oblId;
    @SerializedName("oalldetail")
    private String oallDetail;
    @SerializedName("pLevel")
    private String pLevel;

    public static SafetyCheck objectFromData(String str) {

        return new Gson().fromJson(str, SafetyCheck.class);
    }

    public static List<SafetyCheck> arraySafetyCheckFromData(String str) {

        Type listType = new TypeToken<ArrayList<SafetyCheck>>() {
        }.getType();

        return new Gson().fromJson(str, listType);
    }

    public String getInduname() {
        return induname;
    }

    public void setInduname(String induname) {
        this.induname = induname;
    }

    public String getMtypeid() {
        return mtypeid;
    }

    public void setMtypeid(String mtypeid) {
        this.mtypeid = mtypeid;
    }

    public String getOdetail() {
        return odetail;
    }

    public void setOdetail(String odetail) {
        this.odetail = odetail;
    }

    public String getSCListID() {
        return sCListID;
    }

    public void setSCListID(String sCListID) {
        this.sCListID = sCListID;
    }

    public String getMalTypeID() {
        return MalTypeID;
    }

    public void setMalTypeID(String MalTypeID) {
        this.MalTypeID = MalTypeID;
    }

    public String getMalName() {
        return malName;
    }

    public void setMalName(String malName) {
        this.malName = malName;
    }

    public String getDSeq() {
        return dSeq;
    }

    public void setDSeq(String dSeq) {
        this.dSeq = dSeq;
    }

    public String getObdeId() {
        return obdeId;
    }

    public void setObdeId(String obdeId) {
        this.obdeId = obdeId;
    }

    public String getInsecureID() {
        return insecureID;
    }

    public void setInsecureID(String insecureID) {
        this.insecureID = insecureID;
    }

    public String getObliTitle() {
        return obliTitle;
    }

    public void setObliTitle(String obliTitle) {
        this.obliTitle = obliTitle;
    }

    public String getInseName() {
        return inseName;
    }

    public void setInseName(String inseName) {
        this.inseName = inseName;
    }

    public String getPSeq() {
        return pSeq;
    }

    public void setPSeq(String pSeq) {
        this.pSeq = pSeq;
    }

    public String getPDetail() {
        return pDetail;
    }

    public void setPDetail(String pDetail) {
        this.pDetail = pDetail;
    }

    public String getDLevel() {
        return dLevel;
    }

    public void setDLevel(String dLevel) {
        this.dLevel = dLevel;
    }

    public String getOblId() {
        return oblId;
    }

    public void setOblId(String oblId) {
        this.oblId = oblId;
    }

    public String getOallDetail() {
        return oallDetail;
    }

    public void setOallDetail(String oallDetail) {
        this.oallDetail = oallDetail;
    }

    public String getPLevel() {
        return pLevel;
    }

    public void setPLevel(String pLevel) {
        this.pLevel = pLevel;
    }
}
