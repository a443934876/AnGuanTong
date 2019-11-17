package cn.fjmz.agt.bean;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class LessonEntity {

    /**
     * lesstart : 2019-07-09T08:00:00+08:00
     * teachemname : 梁秉福
     * stuemname : 林为祥
     * lesmunitue : 120
     * leshour : 10
     * studyid : 55442
     * currid : 4695
     * comname : 福清永春
     * currName : 应急救援知识培训课程
     * lesroom : 会议室
     * lescomm : 课程目的：加强生产工作的劳动保护，改善劳动条件，保障公司员工在生产过程中的安全和健康，促进公司事业的发展，贯彻“安全第一，预防为主，综合治理”的方针、以“管生产必须管安全”为原则，促进职工遵守有关劳动保护的法律法规,培训内容如下：
     */

    @SerializedName("lesstart")
    private String lesStart;
    @SerializedName("teachemname")
    private String teachemName;
    @SerializedName("stuemname")
    private String stuemName;
    @SerializedName("lesmunitue")
    private String lesMunitue;
    @SerializedName("leshour")
    private String lesHour;
    @SerializedName("studyid")
    private String studyId;
    @SerializedName("currid")
    private String currId;
    @SerializedName("comname")
    private String comName;
    @SerializedName("currName")
    private String currName;
    @SerializedName("lesroom")
    private String lesRoom;
    @SerializedName("lescomm")
    private String lesComm;

    public static LessonEntity objectFromData(String str) {

        return new Gson().fromJson(str, LessonEntity.class);
    }

    public static List<LessonEntity> arrayLessonEntityFromData(String str) {

        Type listType = new TypeToken<ArrayList<LessonEntity>>() {
        }.getType();

        return new Gson().fromJson(str, listType);
    }

    public String getLesStart() {
        return lesStart;
    }

    public void setLesStart(String lesStart) {
        this.lesStart = lesStart;
    }

    public String getTeachemName() {
        return teachemName;
    }

    public void setTeachemName(String teachemName) {
        this.teachemName = teachemName;
    }

    public String getStuemName() {
        return stuemName;
    }

    public void setStuemName(String stuemName) {
        this.stuemName = stuemName;
    }

    public String getLesMunitue() {
        return lesMunitue;
    }

    public void setLesMunitue(String lesMunitue) {
        this.lesMunitue = lesMunitue;
    }

    public String getLesHour() {
        return lesHour;
    }

    public void setLesHour(String lesHour) {
        this.lesHour = lesHour;
    }

    public String getStudyId() {
        return studyId;
    }

    public void setStudyId(String studyId) {
        this.studyId = studyId;
    }

    public String getCurrId() {
        return currId;
    }

    public void setCurrId(String currId) {
        this.currId = currId;
    }

    public String getComName() {
        return comName;
    }

    public void setComName(String comName) {
        this.comName = comName;
    }

    public String getCurrName() {
        return currName;
    }

    public void setCurrName(String currName) {
        this.currName = currName;
    }

    public String getLesRoom() {
        return lesRoom;
    }

    public void setLesRoom(String lesRoom) {
        this.lesRoom = lesRoom;
    }

    public String getLesComm() {
        return lesComm;
    }

    public void setLesComm(String lesComm) {
        this.lesComm = lesComm;
    }
}
