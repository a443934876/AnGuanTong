package cn.fjmz.agt.presenter.interfaces;


import java.util.List;

import cn.fjmz.agt.base.BaseView;
import cn.fjmz.agt.bean.SafetySetCheckEntity;
import cn.fjmz.agt.bean.SafetySetEntity;

/**
 *
 */

public interface EquipmentInspectionDetailView extends BaseView {

    void getSafetySetCheck(List<SafetySetCheckEntity> entity);

    void AddSafetySetCheck(String entity);
}
