package cn.fjmz.agt.bean;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class ComIndexEntity {


    /**
     * comindex : 1
     */

    @SerializedName("comindex")
    private String comIndex;

    public static ComIndexEntity objectFromData(String str) {

        return new Gson().fromJson(str, ComIndexEntity.class);
    }

    public static List<ComIndexEntity> arrayComIndexEntityFromData(String str) {

        Type listType = new TypeToken<ArrayList<ComIndexEntity>>() {
        }.getType();

        return new Gson().fromJson(str, listType);
    }

    public String getComIndex() {
        return comIndex;
    }

    public void setComIndex(String comIndex) {
        this.comIndex = comIndex;
    }
}
