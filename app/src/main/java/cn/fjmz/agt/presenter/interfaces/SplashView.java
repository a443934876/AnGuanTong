package cn.fjmz.agt.presenter.interfaces;


import cn.fjmz.agt.base.BaseView;
import cn.fjmz.agt.bean.CompanyEntity;
import cn.fjmz.agt.bean.UserEntity;
import cn.fjmz.agt.bean.VersionEntity;

import java.util.List;

/**
 * File descripition:
 *
 * @author lp
 * @date 2018/6/19
 */

public interface SplashView extends BaseView {

    void onSplashGetVersionSuccess(VersionEntity o);

    void onGetSinglePersonalUserFromLoginSuccess(UserEntity userEntityList);

    void onGetMoreComsSuccess(List<CompanyEntity> companyEntityList);

    void onVersionError(String error);
}
