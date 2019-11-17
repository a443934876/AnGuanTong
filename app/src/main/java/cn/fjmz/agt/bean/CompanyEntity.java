package cn.fjmz.agt.bean;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class CompanyEntity extends RealmObject {


    /**
     * Popedom_Area : 8,
     * orgname : 福建省明正安全技术服务有限公司
     * firstareaid : 8
     * orgid : 214510
     * comname : 福建明正
     * Emid : 79641
     * orgidstr : 20161231174445230
     * emname : 刘家京
     * PopedomID : 79737
     * comid : 648
     */

    @SerializedName("Popedom_Area")
    private String area;
    @PrimaryKey
    @SerializedName("orgname")
    private String orgName;
    @SerializedName("firstareaid")
    private String firstAreaId;
    @SerializedName("orgid")
    private String orgId;
    @SerializedName("comname")
    private String comName;
    @SerializedName("Emid")
    private String emId;
    @SerializedName("orgidstr")
    private String orgIdStr;
    @SerializedName("emname")
    private String emName;
    @SerializedName("PopedomID")
    private String areaID;
    @SerializedName("comid")
    private String comId;

    public CompanyEntity() {

    }

    public CompanyEntity(CompanyEntity entity) {
        this.area = entity.area;
        this.orgName = entity.orgName;
        this.firstAreaId = entity.firstAreaId;
        this.orgId = entity.orgId;
        this.comName = entity.comName;
        this.emId = entity.emId;
        this.orgIdStr = entity.orgIdStr;
        this.emName = entity.emName;
        this.areaID = entity.areaID;
        this.comId = entity.comId;
    }

    public static List<CompanyEntity> arrayCompanyEntityFromData(String str) {

        Type listType = new TypeToken<ArrayList<CompanyEntity>>() {
        }.getType();

        return new Gson().fromJson(str, listType);
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public String getFirstAreaId() {
        return firstAreaId;
    }

    public void setFirstAreaId(String firstAreaId) {
        this.firstAreaId = firstAreaId;
    }

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    public String getComName() {
        return comName;
    }

    public void setComName(String comName) {
        this.comName = comName;
    }

    public String getEmId() {
        return emId;
    }

    public void setEmId(String emId) {
        this.emId = emId;
    }

    public String getOrgIdStr() {
        return orgIdStr;
    }

    public void setOrgIdStr(String orgIdStr) {
        this.orgIdStr = orgIdStr;
    }

    public String getEmName() {
        return emName;
    }

    public void setEmName(String emName) {
        this.emName = emName;
    }

    public String getAreaID() {
        return areaID;
    }

    public void setAreaID(String areaID) {
        this.areaID = areaID;
    }

    public String getComId() {
        return comId;
    }

    public void setComId(String comId) {
        this.comId = comId;
    }


}
