package cn.fjmz.agt.mode;

import cn.fjmz.agt.util.StringUtil;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Administrator on 2019/1/25.
 */

public class SafetySetInfo implements Serializable {
    private static final String KEY_CPNAME = "cpname";
    private static final String KEY_CPNUMBER = "cpnumber";
    private static final String KEY_CPLOCAL = "cplocal";
    private static final String KEY_LON = "lon";
    private static final String KEY_LAT = "lat";
    private static final String KEY_CHECKCOUNT = "checkcount";
    private static final String KEY_CPMAINTAIN = "cpmaintain";
    private static final String KEY_CPMASTER = "cpmaster";
    private static final String KEY_LASTCHECK = "lastcheck";
    private static final String KEY_NEXTCHECK = "nextcheck";
    private String cpname;
    private String cpnumber;
    private String cplocal;
    private String lon;
    private String lat;
    private String checkcount;
    private String cpmaintain;
    private String cpmaster;
    private String lastcheck;
    private String nextcheck;

    public String getCpname() {
        return cpname;
    }

    public void setCpname(String cpname) {
        this.cpname = cpname;
    }

    public String getCpnumber() {
        return cpnumber;
    }

    public void setCpnumber(String cpnumber) {
        this.cpnumber = cpnumber;
    }

    public String getCplocal() {
        return cplocal;
    }

    public void setCplocal(String cplocal) {
        this.cplocal = cplocal;
    }

    public String getLon() {
        return lon;
    }

    public void setLon(String lon) {
        this.lon = lon;
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

    public String getCpmaster() {
        return cpmaster;
    }

    public void setCpmaster(String cpmaster) {
        this.cpmaster = cpmaster;
    }

    public String getLastcheck() {
        return lastcheck;
    }

    public void setLastcheck(String lastcheck) {
        this.lastcheck = lastcheck;
    }

    public String getNextcheck() {
        return nextcheck;
    }

    public void setNextcheck(String nextcheck) {
        this.nextcheck = nextcheck;
    }

    public static ArrayList<SafetySetInfo> fromData(ArrayList<HashMap<String, Object>> result) {
        if (result == null || result.isEmpty()) {
            return null;
        }
        ArrayList<SafetySetInfo> missionInfoList = new ArrayList<>();
        for (HashMap<String, Object> list : result) {
            SafetySetInfo info = new SafetySetInfo();
            info.setCpname(StringUtil.noNull(list.get(KEY_CPNAME)));
            info.setCpnumber(StringUtil.noNull(list.get(KEY_CPNUMBER)));
            info.setCplocal(StringUtil.noNull(list.get(KEY_CPLOCAL)));
            info.setLon(StringUtil.noNull(list.get(KEY_LON)));
            info.setLat(StringUtil.noNull(list.get(KEY_LAT)));
            info.setCheckcount(StringUtil.noNull(list.get(KEY_CHECKCOUNT)));
            info.setCpmaintain(StringUtil.noNull(list.get(KEY_CPMAINTAIN)));
            info.setCpmaster(StringUtil.noNull(list.get(KEY_CPMASTER)));
            String lastcheckTime = StringUtil.noNull(list.get(KEY_LASTCHECK));
            if (lastcheckTime.indexOf("T") > 0) {
                info.setLastcheck(lastcheckTime.substring(0, lastcheckTime.indexOf("T")));
            } else {
                info.setLastcheck("");
            }
            String nextcheckTime = StringUtil.noNull(list.get(KEY_NEXTCHECK));
            if (nextcheckTime.indexOf("T") > 0) {
                info.setNextcheck(nextcheckTime.substring(0, nextcheckTime.indexOf("T")));
            } else {
                info.setNextcheck("");
            }
            missionInfoList.add(info);
        }
        return missionInfoList;
    }
}
