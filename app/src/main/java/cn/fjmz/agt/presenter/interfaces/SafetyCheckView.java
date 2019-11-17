package cn.fjmz.agt.presenter.interfaces;


import cn.fjmz.agt.base.BaseView;
import cn.fjmz.agt.bean.PlaceEntity;
import cn.fjmz.agt.bean.SafetyCheck;

import java.util.List;

/**
 *
 */

public interface SafetyCheckView extends BaseView {

    void getSafetyCheckList(List<SafetyCheck> list);

    void getAllPlace(List<PlaceEntity> list);

}
