package cn.fjmz.agt.base;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;

import cn.fjmz.agt.util.ActivityManageUtils;
import cn.fjmz.agt.util.KeyBoardUtils;
import cn.fjmz.agt.view.LoadingDialog;
import com.hjq.toast.ToastUtils;
import com.jaeger.library.StatusBarUtil;

import java.util.List;
import java.util.Map;

import butterknife.ButterKnife;
import io.realm.Realm;
import pub.devrel.easypermissions.AppSettingsDialog;
import pub.devrel.easypermissions.EasyPermissions;

/**
 * File descripition: activity基类
 * <p>
 */
public abstract class BaseActivity<P extends BasePresenter> extends AppCompatActivity implements BaseView, EasyPermissions.PermissionCallbacks {
    protected final String TAG = this.getClass().getSimpleName();
    public Context mContext;
    protected P mPresenter;
    private Realm mRealm;


    protected static void launch(Class clz, Context context) {
        launch(clz, context, null);
    }

    protected static void launch(Class clz, Context context, Map<String, Object> extras) {
        Intent intent = new Intent(context, clz);
        if (extras != null && extras.size() > 0) {
            for (Map.Entry<String, Object> entry : extras.entrySet()) {
                if (entry.getValue() instanceof String) {
                    intent.putExtra(entry.getKey(), (String) entry.getValue());
                } else if (entry.getValue() instanceof Integer) {
                    intent.putExtra(entry.getKey(), (int) entry.getValue());
                } else if (entry.getValue() instanceof Long) {
                    intent.putExtra(entry.getKey(), (long) entry.getValue());
                } else if (entry.getValue() instanceof Double) {
                    intent.putExtra(entry.getKey(), (double) entry.getValue());
                } else if (entry.getValue() instanceof Boolean) {
                    intent.putExtra(entry.getKey(), (boolean) entry.getValue());
                }
            }
        }
        intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
        setContentView(getLayoutId());
        ButterKnife.bind(this);
        mPresenter = createPresenter();
        setStatusBar();
        initData();
        intiListener();
        ActivityManageUtils.getInstance().addActivity(this);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mPresenter != null) {
            mPresenter.detachView();
        }
        if (loadingDialog != null) {
            loadingDialog.dismiss();
        }
        if (mRealm != null) {
            mRealm.close();
        }
        ActivityManageUtils.getInstance().finishActivity(this);
    }

    protected Realm getRealm() {
        if (mRealm == null || mRealm.isClosed()) {
            mRealm = Realm.getDefaultInstance();
        }

        return mRealm;
    }

    protected abstract P createPresenter();

    /**
     * 设置View事件
     */
    protected void intiListener() {

    }

    private LoadingDialog loadingDialog;

    /**
     * 获取布局ID
     *
     * @return
     */
    protected abstract int getLayoutId();

    /**
     * 数据初始化操作
     */
    protected abstract void initData();

    /**
     * 此处设置沉浸式地方
     */
    protected void setStatusBar() {
        StatusBarUtil.setTransparentForImageViewInFragment(this, null);
    }

    /**
     * 封装toast方法（自行定制实现）
     *
     * @param str
     */
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

    @Override
    public void hideLoading() {
        dissMissDialog();
    }

    public void showLoadingDialog() {
        showLoadingDialog("加载中...");
    }

    /**
     * 加载  黑框...
     */
    public void showLoadingDialog(String msg) {
        if (loadingDialog == null) {
            loadingDialog = new LoadingDialog(this);
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


    /**
     * [页面跳转]
     *
     * @param clz
     */
    public void startActivity(Class<?> clz) {
        startActivity(clz, null);
    }


    /**
     * [携带数据的页面跳转]
     *
     * @param clz
     * @param bundle
     */
    public void startActivity(Class<?> clz, Bundle bundle) {
        Intent intent = new Intent();
        intent.setClass(this, clz);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivity(intent);
    }

    /**
     * [含有Bundle通过Class打开编辑界面]
     *
     * @param cls
     * @param bundle
     * @param requestCode
     */
    public void startActivityForResult(Class<?> cls, Bundle bundle, int requestCode) {
        Intent intent = new Intent();
        intent.setClass(this, cls);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivityForResult(intent, requestCode);
    }

    /**
     * 以下是关于软键盘的处理
     */

    /**
     * 清除editText的焦点
     *
     * @param v   焦点所在View
     * @param ids 输入框
     */
    public void clearViewFocus(View v, int... ids) {
        if (null != v && null != ids && ids.length > 0) {
            for (int id : ids) {
                if (v.getId() == id) {
                    v.clearFocus();
                    break;
                }
            }
        }
    }

    /**
     * 隐藏键盘
     *
     * @param v   焦点所在View
     * @param ids 输入框
     * @return true代表焦点在edit上
     */
    public boolean isFocusEditText(View v, int... ids) {
        if (v instanceof EditText) {
            EditText et = (EditText) v;
            for (int id : ids) {
                if (et.getId() == id) {
                    return true;
                }
            }
        }
        return false;
    }

    //是否触摸在指定view上面,对某个控件过滤
    public boolean isTouchView(View[] views, MotionEvent ev) {
        if (views == null || views.length == 0) {
            return false;
        }
        int[] location = new int[2];
        for (View view : views) {
            view.getLocationOnScreen(location);
            int x = location[0];
            int y = location[1];
            if (ev.getX() > x && ev.getX() < (x + view.getWidth())
                    && ev.getY() > y && ev.getY() < (y + view.getHeight())) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (ev.getAction() == MotionEvent.ACTION_DOWN) {
            if (isTouchView(filterViewByIds(), ev)) {
                return super.dispatchTouchEvent(ev);
            }
            if (hideSoftByEditViewIds() == null || hideSoftByEditViewIds().length == 0) {
                return super.dispatchTouchEvent(ev);
            }
            View v = getCurrentFocus();
            if (isFocusEditText(v, hideSoftByEditViewIds())) {
                KeyBoardUtils.hideInputForce(this);
                clearViewFocus(v, hideSoftByEditViewIds());
            }
        }
        return super.dispatchTouchEvent(ev);
    }


    /**
     * 传入EditText的Id
     * 没有传入的EditText不做处理
     *
     * @return id 数组
     */
    public int[] hideSoftByEditViewIds() {
        return null;
    }

    /**
     * 传入要过滤的View
     * 过滤之后点击将不会有隐藏软键盘的操作
     *
     * @return id 数组
     */
    public View[] filterViewByIds() {
        return null;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);

    }

    @Override
    public void onPermissionsGranted(int requestCode, @NonNull List<String> perms) {
        Log.e("EasyPermissions", "获取成功的权限" + perms.size());
    }

    @Override
    public void onPermissionsDenied(int requestCode, @NonNull List<String> perms) {
        StringBuilder sb = new StringBuilder();
        for (String s : perms) {
            sb.append(s);
            sb.append("\n");
        }
        sb.replace(sb.length() - 2, sb.length(), "");
        new AppSettingsDialog.Builder(this)
                .setRationale("此功能需要" + sb + "权限，否则无法正常使用，是否打开设置")
                .setPositiveButton("好")
                .setNegativeButton("不行")
                .build()
                .show();
    }
}
