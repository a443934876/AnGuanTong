package cn.fjmz.agt.mode;

import cn.fjmz.agt.util.StringUtil;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

/*
 * Created by Administrator on 2019/1/25.
 */

public class LessonInfo implements Serializable {
    private static final String KEY_CURRNAME = "currName";
    private static final String KEY_LESSTART = "lesstart";
    private static final String KEY_LESROOM = "lesroom";
    private static final String KEY_LESMUNITUE = "lesmunitue";
    private static final String KEY_LESHOUR = "leshour";
    private static final String KEY_TEACHEMNAME = "teachemname";
    private static final String KEY_LESCOMM = "lescomm";
    private static final String KEY_STUEMNAME = "stuemname";
    private String currName;
    private String lesstart;
    private String lesroom;
    private String lesmunitue;
    private String leshour;
    private String teachemname;
    private String lescomm;
    private String stuemname;

    public String getCurrName() {
        return currName;
    }

    public void setCurrName(String currName) {
        this.currName = currName;
    }

    public String getLesstart() {
        return lesstart;
    }

    public void setLesstart(String lesstart) {
        this.lesstart = lesstart;
    }

    public String getLesroom() {
        return lesroom;
    }

    public void setLesroom(String lesroom) {
        this.lesroom = lesroom;
    }

    public String getLesmunitue() {
        return lesmunitue;
    }

    public void setLesmunitue(String lesmunitue) {
        this.lesmunitue = lesmunitue;
    }

    public String getLeshour() {
        return leshour;
    }

    public void setLeshour(String leshour) {
        this.leshour = leshour;
    }

    public String getTeachemname() {
        return teachemname;
    }

    public void setTeachemname(String teachemname) {
        this.teachemname = teachemname;
    }

    public String getLescomm() {
        return lescomm;
    }

    public void setLescomm(String lescomm) {
        this.lescomm = lescomm;
    }

    public String getStuemname() {
        return stuemname;
    }

    public void setStuemname(String stuemname) {
        this.stuemname = stuemname;
    }

    public static ArrayList<LessonInfo> fromData(ArrayList<HashMap<String, Object>> result) {
        if (result == null || result.isEmpty()) {
            return null;
        }
        ArrayList<LessonInfo> missionInfoList = new ArrayList<>();
        for (HashMap<String, Object> list : result) {
            LessonInfo info = new LessonInfo();
            info.setCurrName(StringUtil.noNull(list.get(KEY_CURRNAME)));
            info.setLesroom(StringUtil.noNull(list.get(KEY_LESROOM)));
            info.setLesmunitue(StringUtil.noNull(list.get(KEY_LESMUNITUE)));
            info.setLeshour(StringUtil.noNull(list.get(KEY_LESHOUR)));
            info.setTeachemname(StringUtil.noNull(list.get(KEY_TEACHEMNAME)));
            info.setLescomm(StringUtil.noNull(list.get(KEY_LESCOMM)));
            info.setStuemname(StringUtil.noNull(list.get(KEY_STUEMNAME)));
            String time = StringUtil.noNull(list.get(KEY_LESSTART));
            if (time.indexOf("T") > 0) {
                info.setLesstart(time.substring(0, time.indexOf("T")));
            } else {
                info.setLesstart("");
            }
            missionInfoList.add(info);
        }
        return missionInfoList;
    }
}
