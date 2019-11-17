package cn.fjmz.agt.presenter;

import cn.fjmz.agt.AqjcCommitInfo;
import cn.fjmz.agt.CsInfo;
import cn.fjmz.agt.JcbDetailInfo;
import cn.fjmz.agt.JcbInfo;
import cn.fjmz.agt.SbInfo;

import java.util.List;

/**
 * Created by Administrator on 2016/3/8.
 */
public interface IYhdjPresenter {
    //安全检查任务
   /* void getRwData();*/

    //场所
    void getCsData();

    void getJcbData();

    void getSbData(String CsId);

    void getJcbDetail(String jcbId);

    void getPrePage();

    void getNextPage();

    void getJcbDetail();

    boolean isCommitting();

    void upload(AqjcCommitInfo info);

    void cancel();

    void getSssb(int pSbid, int comid);

    interface View {
       /* void getRwDataSuccess(List<RwInfo> data);*/

        void getCsDataSuccess(List<CsInfo> data);

        void getJcbDataSuccess(List<JcbInfo> data);

        void getSbDataSuccess(List<SbInfo> data);

        void changeJcbDetail(JcbDetailInfo info, int postion, int count);

        void commitStatus(boolean isSuccess);

        void toast(String toast);

        void toast(int toast);

        void pendingDialog(int resId);

        void setJcbDetail(String detail);

        void setJcbDetailGone();

        void cancelDialog();

        void setPlaceId(String pS);
    }
}
