package cn.fjmz.agt.util;

import android.content.Context;
import android.content.pm.PackageManager;

public class AppUtil {
    /**
     * 从包信息中获取版本号
     *
     * @param context
     * @return int
     * @author zhuofq
     */
    public static int getVersionCode(Context context) {
        try {
            return context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return -1;
    }
    /**
     * 获取包名
     *
     * @作者 huangssh
     * @创建时间 2015-12-2 下午4:08:16
     * @版本
     * @param context
     * @return
     */
    public static String getPackageName(Context context) {
        try {
            return context.getPackageManager().getPackageInfo(context.getPackageName(), 0).packageName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return "-1";
    }
}
