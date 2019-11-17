package cn.fjmz.agt.bean;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;
import com.google.gson.reflect.TypeToken;

import java.io.Serializable;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class DocumentListEntity implements Serializable {

    /**
     * info_AdditiondocIDs : 116131,
     * InfoTitle : 福清市永春混凝土外加剂有限公司关于印发2019年设备设施检修计划的通知
     * AddFilePathsStr : anyType#$
     * pubobj : 各部门、车间
     * PubComname : 福清永春
     * InfoWriter : 林为祥
     * viewed : 1
     * puborgname : 福清市永春混凝土外加剂有限公司
     * yetviewed : 1
     * CDocID : 116130
     * InfoNumber : 福清永春安[2019]3号
     * info_fileids : anyType#$
     * info_AdditiondocTitles : 2019年设备设施检修计划,
     * InfoID : 21961
     * NoAuditCount : 0
     * AddFileTitlesStr : anyType#$
     * PubDate : 2019-02-27T00:00:00+08:00
     */

    @SerializedName("info_AdditiondocIDs")
    private String infoAdditiondocIDs;
    @SerializedName("InfoTitle")
    private String InfoTitle;
    @SerializedName("AddFilePathsStr")
    private String AddFilePathsStr;
    @SerializedName("pubobj")
    private String pubobj;
    @SerializedName("PubComname")
    private String PubComname;
    @SerializedName("InfoWriter")
    private String InfoWriter;
    @SerializedName("viewed")
    private String viewed;
    @SerializedName("puborgname")
    private String puborgname;
    @SerializedName("yetviewed")
    private String yetviewed;
    @SerializedName("CDocID")
    private String CDocID;
    @SerializedName("InfoNumber")
    private String InfoNumber;
    @SerializedName("info_fileids")
    private String infoFileids;
    @SerializedName("info_AdditiondocTitles")
    private String infoAdditiondocTitles;
    @SerializedName("InfoID")
    private String InfoID;
    @SerializedName("NoAuditCount")
    private String NoAuditCount;
    @SerializedName("AddFileTitlesStr")
    private String AddFileTitlesStr;
    @SerializedName("PubDate")
    private String PubDate;

    public static DocumentListEntity objectFromData(String str) {

        return new Gson().fromJson(str, DocumentListEntity.class);
    }

    public static List<DocumentListEntity> arrayDocumentListEntityFromData(String str) {

        Type listType = new TypeToken<ArrayList<DocumentListEntity>>() {
        }.getType();

        return new Gson().fromJson(str, listType);
    }

    public String getInfoAdditiondocIDs() {
        return infoAdditiondocIDs;
    }

    public void setInfoAdditiondocIDs(String infoAdditiondocIDs) {
        this.infoAdditiondocIDs = infoAdditiondocIDs;
    }

    public String getInfoTitle() {
        return InfoTitle;
    }

    public void setInfoTitle(String InfoTitle) {
        this.InfoTitle = InfoTitle;
    }

    public String getAddFilePathsStr() {
        return AddFilePathsStr;
    }

    public void setAddFilePathsStr(String AddFilePathsStr) {
        this.AddFilePathsStr = AddFilePathsStr;
    }

    public String getPubobj() {
        return pubobj;
    }

    public void setPubobj(String pubobj) {
        this.pubobj = pubobj;
    }

    public String getPubComname() {
        return PubComname;
    }

    public void setPubComname(String PubComname) {
        this.PubComname = PubComname;
    }

    public String getInfoWriter() {
        return InfoWriter;
    }

    public void setInfoWriter(String InfoWriter) {
        this.InfoWriter = InfoWriter;
    }

    public String getViewed() {
        return viewed;
    }

    public void setViewed(String viewed) {
        this.viewed = viewed;
    }

    public String getPuborgname() {
        return puborgname;
    }

    public void setPuborgname(String puborgname) {
        this.puborgname = puborgname;
    }

    public String getYetviewed() {
        return yetviewed;
    }

    public void setYetviewed(String yetviewed) {
        this.yetviewed = yetviewed;
    }

    public String getCDocID() {
        return CDocID;
    }

    public void setCDocID(String CDocID) {
        this.CDocID = CDocID;
    }

    public String getInfoNumber() {
        return InfoNumber;
    }

    public void setInfoNumber(String InfoNumber) {
        this.InfoNumber = InfoNumber;
    }

    public String getInfoFileids() {
        return infoFileids;
    }

    public void setInfoFileids(String infoFileids) {
        this.infoFileids = infoFileids;
    }

    public String getInfoAdditiondocTitles() {
        return infoAdditiondocTitles;
    }

    public void setInfoAdditiondocTitles(String infoAdditiondocTitles) {
        this.infoAdditiondocTitles = infoAdditiondocTitles;
    }

    public String getInfoID() {
        return InfoID;
    }

    public void setInfoID(String InfoID) {
        this.InfoID = InfoID;
    }

    public String getNoAuditCount() {
        return NoAuditCount;
    }

    public void setNoAuditCount(String NoAuditCount) {
        this.NoAuditCount = NoAuditCount;
    }

    public String getAddFileTitlesStr() {
        return AddFileTitlesStr;
    }

    public void setAddFileTitlesStr(String AddFileTitlesStr) {
        this.AddFileTitlesStr = AddFileTitlesStr;
    }

    public String getPubDate() {
        if (!PubDate.isEmpty()) {
            return PubDate.substring(0, PubDate.indexOf("T"));
        }
        return PubDate;
    }

    public void setPubDate(String PubDate) {
        this.PubDate = PubDate;
    }
}
