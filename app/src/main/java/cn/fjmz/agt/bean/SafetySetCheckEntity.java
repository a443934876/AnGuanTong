package cn.fjmz.agt.bean;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import cn.fjmz.agt.util.DateParseUtil;

public class SafetySetCheckEntity {
    /**
     * screcordname : 刘家京
     * prodname : 力天HR01型空压机
     * sccheckname : 张宝良
     * sccheckdate : 2020-01-01T20:42:10+08:00
     * scrapdate : 10
     * sccheckstat : 正常
     * scnumber : 26-4
     * sccheckid : 18660
     * sccheckdetail : 测试
     */

    private String screcordname;
    private String prodname;
    private String sccheckname;
    private String sccheckdate;
    private String scrapdate;
    private String sccheckstat;
    private String scnumber;
    private String sccheckid;
    private String sccheckdetail;

    public static SafetySetCheckEntity objectFromData(String str) {

        return new Gson().fromJson(str, SafetySetCheckEntity.class);
    }

    public static List<SafetySetCheckEntity> arraySafetySetCheckEntityFromData(String str) {

        Type listType = new TypeToken<ArrayList<SafetySetCheckEntity>>() {
        }.getType();

        return new Gson().fromJson(str, listType);
    }

    public String getScrecordname() {
        return screcordname;
    }

    public void setScrecordname(String screcordname) {
        this.screcordname = screcordname;
    }

    public String getProdname() {
        return prodname;
    }

    public void setProdname(String prodname) {
        this.prodname = prodname;
    }

    public String getSccheckname() {
        return sccheckname;
    }

    public void setSccheckname(String sccheckname) {
        this.sccheckname = sccheckname;
    }

    public String getSccheckdate() {
        if (!sccheckdate.isEmpty()) {
            return DateParseUtil.getStringTime(sccheckdate);
        }
        return sccheckdate;
    }

    public void setSccheckdate(String sccheckdate) {
        this.sccheckdate = sccheckdate;
    }

    public String getScrapdate() {
        if (!scrapdate.isEmpty()) {
            return DateParseUtil.getStringTime(scrapdate);
        }
        return scrapdate;
    }

    public void setScrapdate(String scrapdate) {
        this.scrapdate = scrapdate;
    }

    public String getSccheckstat() {
        return sccheckstat;
    }

    public void setSccheckstat(String sccheckstat) {
        this.sccheckstat = sccheckstat;
    }

    public String getScnumber() {
        return scnumber;
    }

    public void setScnumber(String scnumber) {
        this.scnumber = scnumber;
    }

    public String getSccheckid() {
        return sccheckid;
    }

    public void setSccheckid(String sccheckid) {
        this.sccheckid = sccheckid;
    }

    public String getSccheckdetail() {
        if (sccheckdetail == null) {
            return "无";
        } else {
            return sccheckdetail;
        }
    }

    public void setSccheckdetail(String sccheckdetail) {
        this.sccheckdetail = sccheckdetail;
    }
}
