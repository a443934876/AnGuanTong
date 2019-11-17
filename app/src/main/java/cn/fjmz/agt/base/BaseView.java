package cn.fjmz.agt.base;

/**
 * 作者： ch
 * 时间： 2016/12/27.13:56
 * 描述：
 * 来源：
 */

public interface BaseView {
    /**
     * 显示dialog
     */
    void showLoading();


    /**
     * 隐藏 dialog
     */

    void hideLoading();

    /**
     * 显示错误信息
     *
     * @param msg msg
     */
    void showError(String msg);

    /**
     * 错误码
     */
    void onErrorCode(BaseModel model);


}
