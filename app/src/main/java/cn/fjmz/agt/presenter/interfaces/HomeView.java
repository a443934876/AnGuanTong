package cn.fjmz.agt.presenter.interfaces;


import cn.fjmz.agt.base.BaseView;
import cn.fjmz.agt.bean.ComIndexEntity;
import cn.fjmz.agt.bean.HiddenIllnessEntity;
import cn.fjmz.agt.bean.LessonEntity;
import cn.fjmz.agt.bean.MissionEntity;
import cn.fjmz.agt.bean.SafetyEntity;

import java.util.List;

/**
 *
 */

public interface HomeView extends BaseView {

    void getSafetyIndexFromComSuccess(ComIndexEntity entity);

    void getMissionFromEm(List<MissionEntity> entity);

    void getAllHiddenIllness(List<HiddenIllnessEntity> entity);

    void getLessonFromEm(List<LessonEntity> entity);

    void getSafetySetList(List<SafetyEntity> entity);

}
