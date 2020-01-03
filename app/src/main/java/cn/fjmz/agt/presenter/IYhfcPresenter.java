package cn.fjmz.agt.presenter;

import android.content.Context;

import cn.fjmz.agt.MyApplication;
import cn.fjmz.agt.YhfcInfo;

import java.util.List;

/**
 * Created by Administrator on 2016/5/4.
 */
public interface IYhfcPresenter {

    void getYhfcList(MyApplication pMyApplication, String startDate, String endDate, String name);

    interface View{
        void getYhfcListSuccess(List<YhfcInfo> pYhfcInfoList);
        void pendingDialog();
        void cancelDialog();
        void toast(String toast);
        void toast(int toast);
        Context getContext();
        int getReview();
        int getFinished();

        boolean isYhzg();
    }
}
