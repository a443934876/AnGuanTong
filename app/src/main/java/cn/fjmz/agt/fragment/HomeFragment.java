package cn.fjmz.agt.fragment;

import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;
import com.bigkoo.convenientbanner.holder.Holder;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import cn.fjmz.agt.R;
import cn.fjmz.agt.activity.DocumentListActivity;
import cn.fjmz.agt.activity.PxksActivity;
import cn.fjmz.agt.activity.SafetyCheckActivity;
import cn.fjmz.agt.activity.SbjcCommitActivity;
import cn.fjmz.agt.base.BaseFragment;
import cn.fjmz.agt.base.BaseModel;
import cn.fjmz.agt.base.Constants;
import cn.fjmz.agt.bean.ComIndexEntity;
import cn.fjmz.agt.bean.HiddenIllnessEntity;
import cn.fjmz.agt.bean.IconItemEntity;
import cn.fjmz.agt.bean.LessonEntity;
import cn.fjmz.agt.bean.MissionEntity;
import cn.fjmz.agt.bean.MsgEntity;
import cn.fjmz.agt.bean.SafetyEntity;
import cn.fjmz.agt.presenter.HomePresenter;
import cn.fjmz.agt.presenter.interfaces.HomeView;
import cn.fjmz.agt.util.DateParseUtil;
import cn.fjmz.agt.util.StringUtil;
import cn.fjmz.agt.widget.ColorArcProgressBar;
import cn.fjmz.agt.widget.ComplexViewMF;
import cn.fjmz.agt.widget.ImageHolderView;
import com.gongwen.marqueen.MarqueeView;
import com.gongwen.marqueen.util.OnItemClickListener;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;

public class HomeFragment extends BaseFragment<HomePresenter> implements HomeView {

    @BindView(R.id.color_arc_progress_bar)
    ColorArcProgressBar mColorArcProgressBar;
    @BindView(R.id.rv_main)
    RecyclerView mRvMain;
    @BindView(R.id.cb_main)
    ConvenientBanner<Integer> mCbMain;
    private static String mComName = "comName";
    @BindView(R.id.tv_user_name)
    TextView mTvUserName;
    @BindView(R.id.tv_date)
    TextView mTvDate;
    @BindView(R.id.mv_mission_msg)
    MarqueeView mMvMissionMsg;
    @BindView(R.id.mv_hiddenIllness_msg)
    MarqueeView mMvHiddenIllnessMsg;
    @BindView(R.id.mv_lesson_msg)
    MarqueeView mMvLessonMsg;
    @BindView(R.id.mv_safety_msg)
    MarqueeView mMvSafetyMsg;
    @BindView(R.id.rl_main_mission_msg)
    RelativeLayout mRlMainMissionMsg;
    @BindView(R.id.rl_main_hiddenIllness_msg)
    RelativeLayout mRlMainHiddenIllnessMsg;
    @BindView(R.id.rl_main_lesson_msg)
    RelativeLayout mRlMainLessonMsg;
    @BindView(R.id.rl_main_safety_msg)
    RelativeLayout mRlMainSafetyMsg;
    @BindView(R.id.srl_home)
    SmartRefreshLayout mSrlHome;
    private BaseQuickAdapter<IconItemEntity, BaseViewHolder> mAdapter;
    private String[] names = {"安全检查", "公文信息", "培训考试", "设备巡查"};
    private String[] clss = {"SafetyCheckActivity", "DocumentListActivity",
            "PxksActivity", "SbjcCommitActivity"};
    private Integer[] icons = {R.drawable.icon_security_check, R.drawable.icon_document_information,
            R.drawable.icon_training, R.drawable.icon_equipment};
    private Integer[] images = new Integer[]{R.drawable.image1, R.drawable.image2};
    private List<Integer> imageList = new ArrayList<>();

    public static HomeFragment newInstance() {
        return new HomeFragment();
    }

    @Override
    public void onResume() {
        super.onResume();
        //开始自动翻页
        mCbMain.startTurning();
        mMvMissionMsg.startFlipping();
        mMvHiddenIllnessMsg.startFlipping();
        mMvLessonMsg.startFlipping();
        mMvSafetyMsg.startFlipping();
    }

    @Override
    public void onPause() {
        super.onPause();
        //停止翻页
        mCbMain.stopTurning();
        mMvMissionMsg.stopFlipping();
        mMvHiddenIllnessMsg.stopFlipping();
        mMvLessonMsg.stopFlipping();
        mMvSafetyMsg.stopFlipping();
    }


    @Override
    protected HomePresenter createPresenter() {
        return new HomePresenter(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_home;
    }

    @Override
    protected void initData() {
        mTvDate.setText(String.format("%s，%s", DateParseUtil.getNowDate(), DateParseUtil.getWeek()));
        initView();
        initBanner();
        if (Constants.entity != null) {
            mPresenter.getSafetyIndexFromComSuccess(StringUtil.noNull(Constants.entity.getComId()));
            mPresenter.getMissionFromEm(StringUtil.noNull(Constants.entity.getEmId()));
            mPresenter.getAllHiddenIllness(StringUtil.noNull(Constants.entity.getEmId()));
            mPresenter.getLessonFromEm(StringUtil.noNull(Constants.entity.getEmId()));
            mPresenter.getSafetySetList(StringUtil.noNull(Constants.entity.getComId()),
                    StringUtil.noNull(Constants.entity.getEmId()));
            mTvUserName.setText(getString(R.string.tv_hello, StringUtil.noNull(Constants.entity.getEmName())));
        }
        List<IconItemEntity> iconItemEntityList = new ArrayList<>();
        for (int i = 0; i < names.length; i++) {
            IconItemEntity entity = new IconItemEntity();
            entity.setName(names[i]);
            entity.setIcon(icons[i]);
            entity.setCls(clss[i]);
            iconItemEntityList.add(entity);
        }
        mAdapter.setNewData(iconItemEntityList);
    }


    private void initBanner() {
        imageList.addAll(Arrays.asList(images));
        mCbMain.setPages(new CBViewHolderCreator() {

            @Override
            public Holder createHolder(View itemView) {
                return new ImageHolderView(itemView);
            }

            @Override
            public int getLayoutId() {
                return R.layout.item_banner_image;
            }
        }, imageList)
                //设置两个点图片作为翻页指示器，不设置则没有指示器，可以根据自己需求自行配合自己的指示器，不需要圆点指示器可以不设
                .setPageIndicator(new int[]{R.drawable.icon_indicator_selected, R.drawable.icon_indicator_uncheck})
                //设置指示器的位置（左、中、右）
                .setPageIndicatorAlign(ConvenientBanner.PageIndicatorAlign.CENTER_HORIZONTAL)
                //设置指示器是否可见
                .setPointViewVisible(true);
    }

    @Override
    protected void initListener() {
        super.initListener();
        mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                IconItemEntity entity = (IconItemEntity) adapter.getItem(position);
                if (entity != null) {
                    switch (entity.getName()) {
                        case "安全检查":
                            SafetyCheckActivity.launch(getContext());
                            break;
                        case "公文信息":
                            DocumentListActivity.launch(getContext());
                            break;
                        case "培训考试":
                            PxksActivity.launch(getContext());
                            break;
                        case "设备巡查":
                            SbjcCommitActivity.launch(getContext());
                            break;
                    }
                }
            }
        });

        mMvMissionMsg.setOnItemClickListener(new OnItemClickListener<RelativeLayout, MsgEntity>() {

            @Override
            public void onItemClickListener(RelativeLayout mView, MsgEntity mData, int mPosition) {
                showToast(mData.getMsg());
            }
        });
        mMvHiddenIllnessMsg.setOnItemClickListener(new OnItemClickListener<RelativeLayout, MsgEntity>() {

            @Override
            public void onItemClickListener(RelativeLayout mView, MsgEntity mData, int mPosition) {
                showToast(mData.getMsg());
            }
        });
        mMvLessonMsg.setOnItemClickListener(new OnItemClickListener<RelativeLayout, MsgEntity>() {

            @Override
            public void onItemClickListener(RelativeLayout mView, MsgEntity mData, int mPosition) {
                showToast(mData.getMsg());
            }
        });
        mMvSafetyMsg.setOnItemClickListener(new OnItemClickListener<RelativeLayout, MsgEntity>() {

            @Override
            public void onItemClickListener(RelativeLayout mView, MsgEntity mData, int mPosition) {
                showToast(mData.getMsg());
            }
        });
        mSrlHome.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                mPresenter.getSafetyIndexFromComSuccess(StringUtil.noNull(Constants.entity.getComId()));
                mPresenter.getMissionFromEm(StringUtil.noNull(Constants.entity.getEmId()));
                mPresenter.getAllHiddenIllness(StringUtil.noNull(Constants.entity.getEmId()));
                mPresenter.getLessonFromEm(StringUtil.noNull(Constants.entity.getEmId()));
                mPresenter.getSafetySetList(StringUtil.noNull(Constants.entity.getComId()),
                        StringUtil.noNull(Constants.entity.getEmId()));
                refreshLayout.finishRefresh(1000);
            }
        });
    }

    private void initView() {
        mRvMain.setLayoutManager(new GridLayoutManager(getContext(), 4));
        mAdapter = new BaseQuickAdapter<IconItemEntity, BaseViewHolder>(R.layout.item_home_icon) {
            @Override
            protected void convert(@NonNull BaseViewHolder helper, IconItemEntity item) {
                helper.setText(R.id.tv_name_item_icon, item.getName());
                helper.setImageResource(R.id.iv_item_icon, item.getIcon());
            }
        };
        mRvMain.setAdapter(mAdapter);
    }


    @Override
    public void getSafetyIndexFromComSuccess(ComIndexEntity o) {
        Float value = Float.valueOf(o.getComIndex());
        mColorArcProgressBar.setNeedProgress(true);
        mColorArcProgressBar.setHintSize(20);
        mColorArcProgressBar.setCurrentValues(100 * value / 4);
    }

    @Override
    public void getMissionFromEm(List<MissionEntity> entities) {
        List<MsgEntity> data = new ArrayList<>();
        if (entities.size() > 0) {
            for (MissionEntity list : entities) {
                data.add(new MsgEntity(list.getMissionTitle()));
            }
        }
        initComplexViewMF(data, mMvMissionMsg);
    }

    @Override
    public void getAllHiddenIllness(List<HiddenIllnessEntity> entities) {
        List<MsgEntity> data = new ArrayList<>();
        if (entities.size() > 0) {
            for (HiddenIllnessEntity list : entities) {
                data.add(new MsgEntity(list.getSafetyTrouble()));
            }
        }
        initComplexViewMF(data, mMvHiddenIllnessMsg);
    }

    @Override
    public void getLessonFromEm(List<LessonEntity> entities) {
        List<MsgEntity> data = new ArrayList<>();
        if (entities.size() > 0) {
            for (LessonEntity list : entities) {
                data.add(new MsgEntity(list.getCurrName()));
            }
        }
        initComplexViewMF(data, mMvLessonMsg);
    }

    @Override
    public void getSafetySetList(List<SafetyEntity> entities) {
        List<MsgEntity> data = new ArrayList<>();
        if (entities.size() > 0) {
            for (SafetyEntity list : entities) {
                data.add(new MsgEntity(list.getCpName()));
            }
        }
        initComplexViewMF(data, mMvSafetyMsg);
    }

    private void initComplexViewMF(List<MsgEntity> data, MarqueeView mMvSafetyMsg) {
        if (data.size() == 0) {
            data.add(new MsgEntity("暂无数据"));
        }
        ComplexViewMF complexViewMF = new ComplexViewMF(getContext());
        complexViewMF.setData(data);
        mMvSafetyMsg.setMarqueeFactory(complexViewMF);
        mMvSafetyMsg.startFlipping();
    }

    @Override
    public void onErrorCode(BaseModel model) {

    }


}
