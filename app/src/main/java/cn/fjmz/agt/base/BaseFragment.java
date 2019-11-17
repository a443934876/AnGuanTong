package cn.fjmz.agt.base;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import cn.fjmz.agt.view.LoadingDialog;
import com.hjq.toast.ToastUtils;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.realm.Realm;


/**
 * ftagment 基类
 */

public abstract class BaseFragment<P extends BasePresenter> extends Fragment implements BaseView {
    public View view;
    public Context mContext;
    protected P mPresenter;
    private Realm mRealm;
    private Unbinder mUnbinder;
    private LoadingDialog loadingDialog;

    protected abstract P createPresenter();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(getLayoutId(), container, false);
        mContext = getActivity();
        mPresenter = createPresenter();
        mUnbinder = ButterKnife.bind(this, view);
        this.initGetView(view);
        this.initData();
        this.initListener();
        return view;
    }

    /**
     * 获取布局ID
     */
    protected abstract int getLayoutId();

    /**
     * 设置View事件
     */
    protected void initListener() {

    }

    /**
     * 设置View事件
     */
    protected void initGetView(View view) {

    }

    /**
     * 数据初始化操作
     */
    protected abstract void initData();

    public void showToast(String str) {
        ToastUtils.show(str);
    }

    public void showLongToast(String str) {
        ToastUtils.show(str);
    }

    @Override
    public void showError(String msg) {
        showToast(msg);
    }


    @Override
    public void showLoading() {
        showLoadingDialog();
    }

    public void showLoadingDialog() {
        showLoadingDialog("加载中...");
    }

    /**
     * 加载  黑框...
     */
    public void showLoadingDialog(String msg) {
        if (loadingDialog == null) {
            loadingDialog = new LoadingDialog(getContext());
        }
        loadingDialog.setMessage(msg);
        if (!loadingDialog.isShowing()) {
            loadingDialog.show();
        }
    }

    /**
     * 消失  黑框...
     */
    public void dissMissDialog() {
        if (loadingDialog != null) {
            loadingDialog.dismiss();
        }
    }

    @Override
    public void hideLoading() {
        dissMissDialog();
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        this.view = null;
        if (mPresenter != null) {
            mPresenter.detachView();
        }
        if (mRealm != null) {
            mRealm.close();
        }
        if (mUnbinder != null) {
            mUnbinder.unbind();
        }
        if (loadingDialog != null) {
            loadingDialog.dismiss();
        }
    }

    protected Realm getRealm() {
        if (mRealm == null || mRealm.isClosed()) {
            mRealm = Realm.getDefaultInstance();
        }

        return mRealm;
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    /**
     * [页面跳转]
     */
    public void startActivity(Class<?> clz) {
        startActivity(clz, null);
    }


    /**
     * [携带数据的页面跳转]
     */
    public void startActivity(Class<?> clz, Bundle bundle) {
        Intent intent = new Intent();
        intent.setClass(getActivity(), clz);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivity(intent);
    }

    /**
     * [含有Bundle通过Class打开编辑界面]
     */
    public void startActivityForResult(Class<?> cls, Bundle bundle,
                                       int requestCode) {
        Intent intent = new Intent();
        intent.setClass(getActivity(), cls);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivityForResult(intent, requestCode);
    }
}
