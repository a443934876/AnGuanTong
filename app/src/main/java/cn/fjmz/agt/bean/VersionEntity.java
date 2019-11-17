package cn.fjmz.agt.bean;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class VersionEntity {

    /**
     * sname : 仲能信息
     * pubdate : 2019-07-20T00:00:00+08:00
     * fpath : anyType#$
     * funmo : 2019年7月20日 17:49:50 修改一些bug
     * promaid : 30
     * ismust : false
     * cdate : 2019-07-20T17:50:10.29+08:00
     * proverid : 39
     * ver : 5
     * comid : 6
     * proname : 安管通
     */

    @SerializedName("sname")
    private String sName;
    @SerializedName("pubdate")
    private String pubDate;
    @SerializedName("fpath")
    private String fPath;
    @SerializedName("funmo")
    private String funmo;
    @SerializedName("promaid")
    private String promaId;
    @SerializedName("ismust")
    private String isMust;
    @SerializedName("cdate")
    private String cDate;
    @SerializedName("proverid")
    private String proverId;
    @SerializedName("ver")
    private int ver;
    @SerializedName("comid")
    private String comId;
    @SerializedName("proname")
    private String proName;

    public static VersionEntity objectFromData(String str) {

        return new Gson().fromJson(str, VersionEntity.class);
    }

    public static List<VersionEntity> arrayVersionEntityFromData(String str) {

        Type listType = new TypeToken<ArrayList<VersionEntity>>() {
        }.getType();

        return new Gson().fromJson(str, listType);
    }

    public String getSName() {
        return sName;
    }

    public void setSName(String sName) {
        this.sName = sName;
    }

    public String getPubDate() {
        return pubDate;
    }

    public void setPubDate(String pubDate) {
        this.pubDate = pubDate;
    }

    public String getFPath() {
        return fPath;
    }

    public void setFPath(String fPath) {
        this.fPath = fPath;
    }

    public String getFunmo() {
        return funmo;
    }

    public void setFunmo(String funmo) {
        this.funmo = funmo;
    }

    public String getPromaId() {
        return promaId;
    }

    public void setPromaId(String promaId) {
        this.promaId = promaId;
    }

    public String getIsMust() {
        return isMust;
    }

    public void setIsMust(String isMust) {
        this.isMust = isMust;
    }

    public String getCDate() {
        return cDate;
    }

    public void setCDate(String cDate) {
        this.cDate = cDate;
    }

    public String getProverId() {
        return proverId;
    }

    public void setProverId(String proverId) {
        this.proverId = proverId;
    }

    public int getVer() {
        return ver;
    }

    public void setVer(int ver) {
        this.ver = ver;
    }

    public String getComId() {
        return comId;
    }

    public void setComId(String comId) {
        this.comId = comId;
    }

    public String getProName() {
        return proName;
    }

    public void setProName(String proName) {
        this.proName = proName;
    }
}
