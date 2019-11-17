package cn.fjmz.agt.bean;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class PlaceEntity {

    /**
     * mplid : 4103
     * lon : 0
     * mset : 2222222222
     * kemid : 63312
     * alt : 0
     * madd : 2222222222
     * keeppartname : 评估小组
     * mplstate : true
     * isused : 正常
     * roomtype : 测试添加场所
     * PlaceSeq : 2
     * keeppartid : 69
     * cdate : 2018-07-16T00:02:00+08:00
     * kemname : 王小亮
     * mplname : 测试添加场所
     * lat : 0
     */

    @SerializedName("mplid")
    private String mplId;
    @SerializedName("lon")
    private String lon;
    @SerializedName("mset")
    private String mset;
    @SerializedName("kemid")
    private String kemId;
    @SerializedName("alt")
    private String alt;
    @SerializedName("madd")
    private String madd;
    @SerializedName("keeppartname")
    private String keeppartname;
    @SerializedName("mplstate")
    private String mplstate;
    @SerializedName("isused")
    private String isused;
    @SerializedName("roomtype")
    private String roomtype;
    @SerializedName("PlaceSeq")
    private String PlaceSeq;
    @SerializedName("keeppartid")
    private String keeppartid;
    @SerializedName("cdate")
    private String cdate;
    @SerializedName("kemname")
    private String kemname;
    @SerializedName("mplname")
    private String mplname;
    @SerializedName("lat")
    private String lat;

    public static PlaceEntity objectFromData(String str) {

        return new Gson().fromJson(str, PlaceEntity.class);
    }

    public static List<PlaceEntity> arrayPlaceEntityFromData(String str) {

        Type listType = new TypeToken<ArrayList<PlaceEntity>>() {
        }.getType();

        return new Gson().fromJson(str, listType);
    }

    public String getMplId() {
        return mplId;
    }

    public void setMplId(String mplId) {
        this.mplId = mplId;
    }

    public String getLon() {
        return lon;
    }

    public void setLon(String lon) {
        this.lon = lon;
    }

    public String getMset() {
        return mset;
    }

    public void setMset(String mset) {
        this.mset = mset;
    }

    public String getKemId() {
        return kemId;
    }

    public void setKemId(String kemId) {
        this.kemId = kemId;
    }

    public String getAlt() {
        return alt;
    }

    public void setAlt(String alt) {
        this.alt = alt;
    }

    public String getMadd() {
        return madd;
    }

    public void setMadd(String madd) {
        this.madd = madd;
    }

    public String getKeeppartname() {
        return keeppartname;
    }

    public void setKeeppartname(String keeppartname) {
        this.keeppartname = keeppartname;
    }

    public String getMplstate() {
        return mplstate;
    }

    public void setMplstate(String mplstate) {
        this.mplstate = mplstate;
    }

    public String getIsused() {
        return isused;
    }

    public void setIsused(String isused) {
        this.isused = isused;
    }

    public String getRoomtype() {
        return roomtype;
    }

    public void setRoomtype(String roomtype) {
        this.roomtype = roomtype;
    }

    public String getPlaceSeq() {
        return PlaceSeq;
    }

    public void setPlaceSeq(String PlaceSeq) {
        this.PlaceSeq = PlaceSeq;
    }

    public String getKeeppartid() {
        return keeppartid;
    }

    public void setKeeppartid(String keeppartid) {
        this.keeppartid = keeppartid;
    }

    public String getCdate() {
        return cdate;
    }

    public void setCdate(String cdate) {
        this.cdate = cdate;
    }

    public String getKemname() {
        return kemname;
    }

    public void setKemname(String kemname) {
        this.kemname = kemname;
    }

    public String getMplname() {
        return mplname;
    }

    public void setMplname(String mplname) {
        this.mplname = mplname;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }
}
