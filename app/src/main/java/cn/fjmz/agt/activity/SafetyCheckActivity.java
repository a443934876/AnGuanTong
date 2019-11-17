package cn.fjmz.agt.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import cn.fjmz.agt.R;
import cn.fjmz.agt.base.BaseActivity;
import cn.fjmz.agt.base.BaseModel;
import cn.fjmz.agt.base.BasePresenter;
import cn.fjmz.agt.fragment.RectificationFragment;
import cn.fjmz.agt.fragment.SafetyCheckFragment;
import cn.fjmz.agt.fragment.JcfkFragment;
import com.flyco.tablayout.SlidingTabLayout;

import java.util.ArrayList;

import butterknife.BindView;

public class SafetyCheckActivity extends BaseActivity {

    @BindView(R.id.iv_title_left)
    ImageView mIvTitleLeft;
    @BindView(R.id.tv_title_center)
    TextView mTvTitleCenter;
    @BindView(R.id.st_safety_check)
    SlidingTabLayout mStSafetyCheck;
    @BindView(R.id.vp_safety_check)
    ViewPager mVpSafetyCheck;
    private ArrayList<Fragment> mFragments = new ArrayList<>();
    private final String[] mTitles = {"整改未反馈", "隐患登记", "整改未复查"};

    public static void launch(Context context) {
        Intent intent = new Intent(context, SafetyCheckActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        context.startActivity(intent);
    }


    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_safety_check;
    }

    @Override
    protected void initData() {
        mIvTitleLeft.setImageResource(R.drawable.ic_title_back_arrow);
        mTvTitleCenter.setText("安全检查");
        initSlidingTabLayout();
    }

    private void initSlidingTabLayout() {
        mFragments.add(JcfkFragment.newInstance());
        mFragments.add(SafetyCheckFragment.newInstance());
        mFragments.add(RectificationFragment.newInstance());
        SafetyCheckPagerAdapter mAdapter = new SafetyCheckPagerAdapter(getSupportFragmentManager());
        mVpSafetyCheck.setAdapter(mAdapter);

        mStSafetyCheck.setViewPager(mVpSafetyCheck, mTitles, this, mFragments);
        mVpSafetyCheck.setCurrentItem(1);
    }

    @Override
    protected void intiListener() {
        super.intiListener();
        mIvTitleLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    @Override
    public void onErrorCode(BaseModel model) {

    }

    private class SafetyCheckPagerAdapter extends FragmentPagerAdapter {
        SafetyCheckPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public int getCount() {
            return mFragments.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mTitles[position];
        }

        @Override
        public Fragment getItem(int position) {
            return mFragments.get(position);
        }
    }

}
