package cn.fjmz.agt.presenter.interfaces;


import cn.fjmz.agt.base.BaseView;
import cn.fjmz.agt.bean.DocumentDetailEntity;
import cn.fjmz.agt.bean.DocumentEntity;

import java.util.List;

/**
 *
 */

public interface DocumentReviewView extends BaseView {

    void getCapacityDocument(DocumentEntity entity);

    void setInfoTurning(Object entity);

    void getCapacityDocumentDetail(List<DocumentDetailEntity> entity);

}
