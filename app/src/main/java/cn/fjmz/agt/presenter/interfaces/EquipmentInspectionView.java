package cn.fjmz.agt.presenter.interfaces;


import java.util.List;

import cn.fjmz.agt.base.BaseView;
import cn.fjmz.agt.bean.SafetySetEntity;

/**
 *
 */

public interface EquipmentInspectionView extends BaseView {

    void getSafetySetList(List<SafetySetEntity> entity);

    void AddSafetySetCheck(String entity);
}
