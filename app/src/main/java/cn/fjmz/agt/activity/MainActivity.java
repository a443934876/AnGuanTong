package cn.fjmz.agt.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.view.KeyEvent;
import android.widget.Toast;

import cn.fjmz.agt.R;
import cn.fjmz.agt.base.BaseActivity;
import cn.fjmz.agt.base.BaseModel;
import cn.fjmz.agt.base.BasePresenter;
import cn.fjmz.agt.base.Constants;
import cn.fjmz.agt.bean.CompanyEntity;
import cn.fjmz.agt.bean.TabEntity;
import cn.fjmz.agt.fragment.HomeFragment;
import cn.fjmz.agt.fragment.MineFragment;
import cn.fjmz.agt.util.ActivityManageUtils;

import com.flyco.tablayout.CommonTabLayout;
import com.flyco.tablayout.listener.CustomTabEntity;

import java.util.ArrayList;

import butterknife.BindView;
import io.realm.RealmResults;


public class MainActivity extends BaseActivity {

    private static String mComName = "comName";
    @BindView(R.id.tab_layout)
    CommonTabLayout mTabLayout;
    private Integer[] mIconUnSelectIds = {R.drawable.tab_home_unselect, R.drawable.tab_contact_unselect};
    private Integer[] mIconSelectIds = {R.drawable.tab_home_select, R.drawable.tab_contact_select};
    private String[] mTitles = {"主页", "我的"};
    private ArrayList<Fragment> mFragments = new ArrayList<>();

    public static void launch(Context context, String comName) {
        Intent intent = new Intent(context, MainActivity.class);
        intent.putExtra(mComName, comName);
        intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        context.startActivity(intent);
    }


    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initData() {
        RealmResults<CompanyEntity> entities = getRealm().where(CompanyEntity.class).
                equalTo("comName", getIntent().getStringExtra(mComName)).findAll();
        if (entities.size() > 0) {
            Constants.companyEntity = entities.get(0);
            mFragments.add(HomeFragment.newInstance());
            mFragments.add(MineFragment.newInstance());
            ArrayList<CustomTabEntity> mTabEntities = new ArrayList<>();
            for (int i = 0; i < mTitles.length; i++) {
                mTabEntities.add(new TabEntity(mTitles[i], mIconSelectIds[i], mIconUnSelectIds[i]));
            }
            mTabLayout.setTabData(mTabEntities, this, R.id.fl_main, mFragments);
            mTabLayout.setCurrentTab(0);
        } else {
            LoginActivity.launch(LoginActivity.class, this);
        }
    }

    private long mLastPressTime = 0;

    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        if (event.getKeyCode() == KeyEvent.KEYCODE_BACK
                && event.getAction() != KeyEvent.ACTION_UP) {
            long thisPressTime = System.currentTimeMillis();
            // 6秒内连按两次,就退出程序
            if (thisPressTime - mLastPressTime > 5000) {
                Toast.makeText(this, "再按一次返回键就可以退出啦", Toast.LENGTH_SHORT)
                        .show();
                mLastPressTime = thisPressTime;
            } else {
                ActivityManageUtils.getInstance().appExit(this);
            }
            return true;
        }
        if (event.getKeyCode() == KeyEvent.KEYCODE_MENU) {// 监听menu键
            return false;
        }
        try {
            return super.dispatchKeyEvent(event);
        } catch (Exception e) {
            return true;
        }
    }


    @Override
    public void onErrorCode(BaseModel model) {

    }
}
