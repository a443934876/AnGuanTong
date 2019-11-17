package cn.fjmz.agt.presenter.interfaces;


import cn.fjmz.agt.base.BaseView;
import cn.fjmz.agt.bean.CompanyEntity;
import cn.fjmz.agt.bean.UserEntity;

import java.util.List;

/**
 *
 */

public interface LoginView extends BaseView {

    void onGetSinglePersonalUserFromLoginSuccess(UserEntity userEntityList);

    void onGetMoreComsSuccess(List<CompanyEntity> companyEntityList);
}
