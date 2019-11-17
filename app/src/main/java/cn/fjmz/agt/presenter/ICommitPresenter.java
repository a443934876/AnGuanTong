package cn.fjmz.agt.presenter;

import android.content.Context;

import cn.fjmz.agt.myinterface.INoCommitItem;

import java.util.List;
import java.util.Set;

/**
 *
 * Created by Administrator on 2016/3/10.
 */
public interface ICommitPresenter<T extends INoCommitItem> {


    void upload(List<T> info, Set<Integer> keySet);

    void cancel(List<T> commitData);

    interface View{
        void commitStatus(int position, boolean isSuccess);
        void toast(String toast);
        Context getContext();
    }
}
