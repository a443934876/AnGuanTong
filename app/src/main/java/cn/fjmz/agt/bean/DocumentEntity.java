package cn.fjmz.agt.bean;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class DocumentEntity {

    /**
     * cComid : 60
     * createpeople : 林为祥
     * cDocTitle : 福清市永春混凝土外加剂有限公司关于印发《2016年各岗位安全生产职责》的通知
     * doctype : 公文信息
     * cRemark : anyType#$
     * cDocTempID : 260
     * cdate : 2015-11-18T18:32:00+08:00
     * cstate : 正常
     * cDocid : 35879
     * overview : 为了更好的完成年度安全生产目标，规范各岗位安全生产操作，减少生产事故，保证产品质量，现将各岗位安全生产职责整理发布，请相关岗位认真贯彻执行。
     * cchildren : 0
     */

    @SerializedName("cComid")
    private String cComid;
    @SerializedName("createpeople")
    private String createpeople;
    @SerializedName("cDocTitle")
    private String cDocTitle;
    @SerializedName("doctype")
    private String doctype;
    @SerializedName("cRemark")
    private String cRemark;
    @SerializedName("cDocTempID")
    private String cDocTempID;
    @SerializedName("cdate")
    private String cdate;
    @SerializedName("cstate")
    private String cstate;
    @SerializedName("cDocid")
    private String cDocid;
    @SerializedName("overview")
    private String overview;
    @SerializedName("cchildren")
    private String cchildren;

    public static DocumentEntity objectFromData(String str) {

        return new Gson().fromJson(str, DocumentEntity.class);
    }

    public static List<DocumentEntity> arrayDocumentDetailsEntityFromData(String str) {

        Type listType = new TypeToken<ArrayList<DocumentEntity>>() {
        }.getType();

        return new Gson().fromJson(str, listType);
    }

    public String getCComid() {
        return cComid;
    }

    public void setCComid(String cComid) {
        this.cComid = cComid;
    }

    public String getCreatepeople() {
        return createpeople;
    }

    public void setCreatepeople(String createpeople) {
        this.createpeople = createpeople;
    }

    public String getCDocTitle() {
        return cDocTitle;
    }

    public void setCDocTitle(String cDocTitle) {
        this.cDocTitle = cDocTitle;
    }

    public String getDoctype() {
        return doctype;
    }

    public void setDoctype(String doctype) {
        this.doctype = doctype;
    }

    public String getCRemark() {
        return cRemark;
    }

    public void setCRemark(String cRemark) {
        this.cRemark = cRemark;
    }

    public String getCDocTempID() {
        return cDocTempID;
    }

    public void setCDocTempID(String cDocTempID) {
        this.cDocTempID = cDocTempID;
    }

    public String getCdate() {
        return cdate;
    }

    public void setCdate(String cdate) {
        this.cdate = cdate;
    }

    public String getCstate() {
        return cstate;
    }

    public void setCstate(String cstate) {
        this.cstate = cstate;
    }

    public String getCDocid() {
        return cDocid;
    }

    public void setCDocid(String cDocid) {
        this.cDocid = cDocid;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getCchildren() {
        return cchildren;
    }

    public void setCchildren(String cchildren) {
        this.cchildren = cchildren;
    }
}
