package cn.fjmz.agt.presenter.interfaces;


import cn.fjmz.agt.base.BaseView;
import cn.fjmz.agt.bean.DocumentListEntity;

import java.util.List;

/**
 *
 */

public interface DocumentView extends BaseView {

    void getWebInformFroEmID(List<DocumentListEntity> entity);

}
