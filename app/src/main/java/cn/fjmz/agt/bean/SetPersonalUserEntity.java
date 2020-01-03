package cn.fjmz.agt.bean;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class SetPersonalUserEntity {
    /**
     * outUPerID : 0
     */

    private String outUPerID;

    public static SetPersonalUserEntity objectFromData(String str) {

        return new Gson().fromJson(str, SetPersonalUserEntity.class);
    }

    public static List<SetPersonalUserEntity> arraysetPersonalUserEntityFromData(String str) {

        Type listType = new TypeToken<ArrayList<SetPersonalUserEntity>>() {
        }.getType();

        return new Gson().fromJson(str, listType);
    }

    public String getOutUPerID() {
        return outUPerID;
    }

    public void setOutUPerID(String outUPerID) {
        this.outUPerID = outUPerID;
    }
}
