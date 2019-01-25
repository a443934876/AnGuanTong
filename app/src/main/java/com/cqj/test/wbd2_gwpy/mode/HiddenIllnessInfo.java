package com.cqj.test.wbd2_gwpy.mode;

import com.cqj.test.wbd2_gwpy.util.StringUtil;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Administrator on 2019/1/25.
 */

public class HiddenIllnessInfo implements Serializable {
    private static final String KEY_ACTIONORGNAME = "actionOrgName";
    private static final String KEY_TROUBLEGRADE = "troubleGrade";
    private static final String KEY_CPEONAME = "cpeoname";
    private static final String KEY_SAFETYTROUBLE = "safetyTrouble";
    private static final String KEY_LIMITDATE = "limitDate";
    private static final String KEY_ALERTMPHONE = "alertmphone";
    private static final String KEY_CHECKDATE = "checkDate";
    private static final String KEY_LIABELNAME = "LiabelName";
    private static final String KEY_DSCHEME = "dScheme";
    private String actionOrgName;
    private String troubleGrade;
    private String cpeoname;
    private String safetyTrouble;
    private String limitDate;
    private String alertmphone;
    private String checkDate;
    private String LiabelName;
    private String dScheme;

    public String getActionOrgName() {
        return actionOrgName;
    }

    public void setActionOrgName(String actionOrgName) {
        this.actionOrgName = actionOrgName;
    }

    public String getTroubleGrade() {
        return troubleGrade;
    }

    public void setTroubleGrade(String troubleGrade) {
        this.troubleGrade = troubleGrade;
    }

    public String getCpeoname() {
        return cpeoname;
    }

    public void setCpeoname(String cpeoname) {
        this.cpeoname = cpeoname;
    }

    public String getSafetyTrouble() {
        return safetyTrouble;
    }

    public void setSafetyTrouble(String safetyTrouble) {
        this.safetyTrouble = safetyTrouble;
    }

    public String getLimitDate() {
        return limitDate;
    }

    public void setLimitDate(String limitDate) {
        this.limitDate = limitDate;
    }

    public String getAlertmphone() {
        return alertmphone;
    }

    public void setAlertmphone(String alertmphone) {
        this.alertmphone = alertmphone;
    }

    public String getCheckDate() {
        return checkDate;
    }

    public void setCheckDate(String checkDate) {
        this.checkDate = checkDate;
    }

    public String getLiabelName() {
        return LiabelName;
    }

    public void setLiabelName(String liabelName) {
        LiabelName = liabelName;
    }

    public String getdScheme() {
        return dScheme;
    }

    public void setdScheme(String dScheme) {
        this.dScheme = dScheme;
    }


    public static ArrayList<HiddenIllnessInfo> fromData(ArrayList<HashMap<String, Object>> result) {
        if (result == null || result.isEmpty()) {
            return null;
        }
        ArrayList<HiddenIllnessInfo> hiddenIllnessInfos = new ArrayList<>();
        for (HashMap<String, Object> list : result) {
            HiddenIllnessInfo info = new HiddenIllnessInfo();
            info.setActionOrgName(StringUtil.noNull(list.get(KEY_ACTIONORGNAME)));
            info.setTroubleGrade(StringUtil.noNull(list.get(KEY_TROUBLEGRADE)));
            info.setCpeoname(StringUtil.noNull(list.get(KEY_CPEONAME)));
            info.setSafetyTrouble(StringUtil.noNull(list.get(KEY_SAFETYTROUBLE)));
            String limitDate = StringUtil.noNull(list.get(KEY_LIMITDATE));
            if (limitDate.indexOf("T") > 0) {
                info.setLimitDate(limitDate.substring(0, limitDate.indexOf("T")));
            } else {
                info.setLimitDate("");
            }
            info.setAlertmphone(StringUtil.noNull(list.get(KEY_ALERTMPHONE)));
            String checkDate = StringUtil.noNull(list.get(KEY_CHECKDATE));
            if (checkDate.indexOf("T") > 0) {
                info.setCheckDate(checkDate.substring(0, checkDate.indexOf("T")));
            } else {
                info.setCheckDate("");
            }
            info.setLiabelName(StringUtil.noNull(list.get(KEY_LIABELNAME)));
            info.setdScheme(StringUtil.noNull(list.get(KEY_DSCHEME)));
            hiddenIllnessInfos.add(info);
        }
        return hiddenIllnessInfos;
    }
}
