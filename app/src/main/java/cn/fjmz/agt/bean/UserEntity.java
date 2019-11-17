package cn.fjmz.agt.bean;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class UserEntity {


    /**
     * Uid : 1702
     * 昵称 : a443934876
     * 密码 : 6406241
     * peoid : 90572
     * 姓名 : 刘家京
     * 序列号 : ,
     * 通道 : 2020-07-29T00:00:00+08:00
     * 手机 : 18950990256
     * ret : 0
     */

    @SerializedName("Uid")
    private String Uid;
    @SerializedName("昵称")
    private String account;
    @SerializedName("密码")
    private String password;
    @SerializedName("peoid")
    private String peoid;
    @SerializedName("姓名")
    private String name;
    @SerializedName("序列号")
    private String serialNumber;
    @SerializedName("通道")
    private String channel;
    @SerializedName("手机")
    private String phone;
    @SerializedName("ret")
    private String ret;

    public static List<UserEntity> arrayUserEntityFromData(String str) {

        Type listType = new TypeToken<ArrayList<UserEntity>>() {
        }.getType();

        return new Gson().fromJson(str, listType);
    }


    public String getUid() {
        return Uid;
    }

    public void setUid(String Uid) {
        this.Uid = Uid;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPeoid() {
        return peoid;
    }

    public void setPeoid(String peoid) {
        this.peoid = peoid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getRet() {
        return ret;
    }

    public void setRet(String ret) {
        this.ret = ret;
    }


}
