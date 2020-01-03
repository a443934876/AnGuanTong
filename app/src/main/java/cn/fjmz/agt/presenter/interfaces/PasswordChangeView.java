package cn.fjmz.agt.presenter.interfaces;


import cn.fjmz.agt.base.BaseView;
import cn.fjmz.agt.bean.SetPersonalUserEntity;
import cn.fjmz.agt.bean.UserEntity;

/**
 *
 */

public interface PasswordChangeView extends BaseView {

    void onGetSinglePersonalUserFromLoginSuccess(UserEntity userEntityList);

    void onSetPersonalUser(SetPersonalUserEntity entity);

}
