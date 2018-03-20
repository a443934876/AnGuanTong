package com.cqj.test.wbd2_gwpy.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.MenuItem;
import com.cqj.test.wbd2_gwpy.R;
import com.cqj.test.wbd2_gwpy.adapter.ViewPagerAdapter;
import com.cqj.test.wbd2_gwpy.fragment.AqfcFragment;
import com.cqj.test.wbd2_gwpy.fragment.AqjcFragment;
import com.cqj.test.wbd2_gwpy.fragment.JcfkFragment;
import java.util.ArrayList;
import java.util.List;

/*
 * Created by Administrator on 2016/3/7.
 */
public class Yhdj_Activity_V2 extends FragmentActivity {

    private Fragment mYhdjFragment;
    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.yhdj_view_v2);
        initComplement();
        registListener();
    }

    private void registListener() {
        mViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                switch (position){
                    case 0:
                        getActionBar().setTitle("隐患整改未反馈");
                        break;
                    case 1:
                        getActionBar().setTitle("隐患登记");
                        break;
                    case 2:
                        getActionBar().setTitle("隐患未复查");
                        break;
                    default:
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private void initComplement() {
        getActionBar().setTitle("隐患登记");
        getActionBar().setDisplayHomeAsUpEnabled(true);
        mViewPager = (ViewPager) findViewById(R.id.yhdj_viewpager);
        mYhdjFragment = AqjcFragment.newInstance();
        AqfcFragment aqfcFragment = AqfcFragment.newInstance();
        JcfkFragment jcfkFragment = JcfkFragment.newInstance();
        List<Fragment> fragments = new ArrayList<>();
        fragments.add(jcfkFragment);
        fragments.add(mYhdjFragment);
        fragments.add(aqfcFragment);
        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager(), fragments);
        mViewPager.setAdapter(viewPagerAdapter);
        mViewPager.setOffscreenPageLimit(3);
        mViewPager.setCurrentItem(1);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                break;

            default:
                break;
        }
        return true;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        try {
            mYhdjFragment.onActivityResult(requestCode, resultCode, data);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
