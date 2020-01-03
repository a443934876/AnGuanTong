package cn.fjmz.agt.bean;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class SafetySetEntity {
    /**
     * lon : 119.37723
     * cname : 慧为测试
     * SCPID : 299
     * nextcheck : 2019-07-20T01:00:00+08:00
     * lastcheck : 2019-07-20T00:00:00+08:00
     * cplocal : 龙山公园
     * cpnumber : 26-4
     * cpmaster : 张宝良
     * cpid : 2881
     * cpname : 力天HR01型空压机
     * lat : 25.75296
     * checkcount : 4
     * cpmaintain : 张宝良
     * concapid : 3276
     */

    private String lon;
    private String cname;
    private String SCPID;
    private String nextcheck;
    private String lastcheck;
    private String cplocal;
    private String cpnumber;
    private String cpmaster;
    private String cpid;
    private String cpname;
    private String lat;
    private String checkcount;
    private String cpmaintain;
    private String concapid;

    public static SafetySetEntity objectFromData(String str) {

        return new Gson().fromJson(str, SafetySetEntity.class);
    }

    public static List<SafetySetEntity> arraySafetySetEntityFromData(String str) {

        Type listType = new TypeToken<ArrayList<SafetySetEntity>>() {
        }.getType();

        return new Gson().fromJson(str, listType);
    }

    public String getLon() {
        return lon;
    }

    public void setLon(String lon) {
        this.lon = lon;
    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }

    public String getSCPID() {
        return SCPID;
    }

    public void setSCPID(String SCPID) {
        this.SCPID = SCPID;
    }

    public String getNextcheck() {
        return nextcheck;
    }

    public void setNextcheck(String nextcheck) {
        this.nextcheck = nextcheck;
    }

    public String getLastcheck() {
        return lastcheck;
    }

    public void setLastcheck(String lastcheck) {
        this.lastcheck = lastcheck;
    }

    public String getCplocal() {
        return cplocal;
    }

    public void setCplocal(String cplocal) {
        this.cplocal = cplocal;
    }

    public String getCpnumber() {
        return cpnumber;
    }

    public void setCpnumber(String cpnumber) {
        this.cpnumber = cpnumber;
    }

    public String getCpmaster() {
        return cpmaster;
    }

    public void setCpmaster(String cpmaster) {
        this.cpmaster = cpmaster;
    }

    public String getCpid() {
        return cpid;
    }

    public void setCpid(String cpid) {
        this.cpid = cpid;
    }

    public String getCpname() {
        return cpname;
    }

    public void setCpname(String cpname) {
        this.cpname = cpname;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getCheckcount() {
        return checkcount;
    }

    public void setCheckcount(String checkcount) {
        this.checkcount = checkcount;
    }

    public String getCpmaintain() {
        return cpmaintain;
    }

    public void setCpmaintain(String cpmaintain) {
        this.cpmaintain = cpmaintain;
    }

    public String getConcapid() {
        return concapid;
    }

    public void setConcapid(String concapid) {
        this.concapid = concapid;
    }
}
