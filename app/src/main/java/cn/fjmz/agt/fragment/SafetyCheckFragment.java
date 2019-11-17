package cn.fjmz.agt.fragment;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import cn.fjmz.agt.App;
import cn.fjmz.agt.AqjcCommitInfo;
import cn.fjmz.agt.R;
import cn.fjmz.agt.base.BaseFragment;
import cn.fjmz.agt.base.BaseModel;
import cn.fjmz.agt.base.Constants;
import cn.fjmz.agt.bean.IconItemEntity;
import cn.fjmz.agt.bean.PlaceEntity;
import cn.fjmz.agt.bean.SafetyCheck;
import cn.fjmz.agt.presenter.SafetyCheckPresenter;
import cn.fjmz.agt.presenter.interfaces.SafetyCheckView;
import cn.fjmz.agt.util.StringUtil;
import com.github.ikidou.fragmentBackHandler.FragmentBackHandler;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import butterknife.BindView;
import butterknife.OnClick;
import cn.pedant.SweetAlert.SweetAlertDialog;

public class SafetyCheckFragment extends BaseFragment<SafetyCheckPresenter> implements SafetyCheckView,
        FragmentBackHandler, DatePickerDialog.OnDateSetListener {

    private final static int AUDIO = 25;
    public static final int QRCODE_REQUEST = 110;
    private final static int CAMERA = 50;
    @BindView(R.id.tv_safety_check_content)
    TextView mTvSafetyCheckContent;
    @BindView(R.id.rl_safety_check)
    RelativeLayout mRlSafetyCheck;
    @BindView(R.id.tv_place_content)
    TextView mTvPlaceContent;
    @BindView(R.id.rl_place)
    RelativeLayout mRlPlace;
    @BindView(R.id.et_rectification_period_date)
    EditText mEtRectificationPeriodDate;
    @BindView(R.id.tv_hazard_registration_content)
    TextView mTvHazardRegistrationContent;
    @BindView(R.id.rl_hazard_registration)
    RelativeLayout mRlHazardRegistration;
    @BindView(R.id.rv_btn)
    RecyclerView mRvBtn;
    @BindView(R.id.rl_corrective)
    RelativeLayout mRlCorrective;
    @BindView(R.id.et_safety_check_detail)
    EditText mEtSafetyCheckDetail;
    @BindView(R.id.ib_up)
    Button mIbUp;
    @BindView(R.id.tv_safety_check_number)
    TextView mTvSafetyCheckNumber;
    @BindView(R.id.ib_down)
    Button mIbDown;
    @BindView(R.id.ll_select)
    LinearLayout mLlSelect;
    //    private ImageView mJcbDetailPre, mJcbDetailNext;
    private TextView mJcbPositionTv, mJcbCountTv;
    //    private Button mJcbDetail;
    /*private TextView mPhotoCount;*/
    private File mCache;
    private Calendar mCalendar;
    private App myApp;
    private int mObjOrganizationID;
    private boolean isSetPlace;
    private int mEwmSssbId;
    //提交标记
    private Boolean commitTag = false;
    private List<SafetyCheck> mSafetyCheckList;
    private List<SafetyCheck> mAllSafetyCheckList;
    private List<SafetyCheck> mSelectSafetyCheckList = new ArrayList<>();
    private List<PlaceEntity> mPlaceList;
    private String[] hazardRegistration = {"无隐患", "一般隐患", "较大隐患", "重大隐患"};
    private String[] iconName = {"照片记录", "语音记录"};
    private int[] iconBackground = {R.drawable.bg_camera, R.drawable.bg_microphone};
    private List<String> mHazardRegistration = new ArrayList<>(Arrays.asList(hazardRegistration));
    private BaseQuickAdapter<IconItemEntity, BaseViewHolder> mAdapter;
    private int mIndex = 0;

    public static SafetyCheckFragment newInstance() {
        return new SafetyCheckFragment();
    }


    @Override
    protected SafetyCheckPresenter createPresenter() {
        return new SafetyCheckPresenter(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_safety_check;
    }

    @Override
    protected void initData() {
        mPresenter.getSafetyCheckList(StringUtil.noNull(Constants.entity.getComId()));
        mPresenter.getAllPlace(StringUtil.noNull(Constants.entity.getComId()));
        initComplement();
        registListener();
        initIcon();
        getIconData();
    }

    private void getIconData() {
        List<IconItemEntity> list = new ArrayList<>();
        for (int i = 0; i < iconName.length; i++) {
            IconItemEntity entity = new IconItemEntity();
            entity.setIcon(iconBackground[i]);
            entity.setName(iconName[i]);
            list.add(entity);
        }
        mAdapter.setNewData(list);
    }

    private void initIcon() {
        mRvBtn.setLayoutManager(new GridLayoutManager(mContext, 2));
        mAdapter = new BaseQuickAdapter<IconItemEntity, BaseViewHolder>(R.layout.item_safety_check_icon) {
            @Override
            protected void convert(@NonNull BaseViewHolder helper, IconItemEntity item) {
                helper.setBackgroundRes(R.id.ib_item_icon, item.getIcon());
                helper.setText(R.id.tv_name_item_icon, item.getName());
            }
        };
        mRvBtn.setAdapter(mAdapter);

    }

    @Override
    protected void initListener() {
        super.initListener();
        mTvSafetyCheckContent.setSelected(true);
        mTvPlaceContent.setSelected(true);
        mTvHazardRegistrationContent.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (charSequence.toString().equals("无隐患")) {
                    mRlCorrective.setVisibility(View.GONE);
                } else {
                    mRlCorrective.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        mTvSafetyCheckContent.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (charSequence.length() > 0) {
                    mLlSelect.setVisibility(View.VISIBLE);
                } else {
                    mLlSelect.setVisibility(View.GONE);
                }
                safetyCheckDetail(charSequence);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    private void safetyCheckDetail(CharSequence charSequence) {
        mSelectSafetyCheckList.clear();
        for (SafetyCheck safetyCheck : mAllSafetyCheckList) {
            if (safetyCheck.getObliTitle().equals(charSequence.toString())) {
                mSelectSafetyCheckList.add(safetyCheck);
            }
        }
        if (mSelectSafetyCheckList.size() > 0) {
            mIndex = 0;
            mEtSafetyCheckDetail.setText(mSelectSafetyCheckList.get(mIndex).getOdetail());
            mTvSafetyCheckNumber.setText(String.format(getString(R.string.tv_safety_check_number), mIndex + 1, mSelectSafetyCheckList.size()));
        }
    }

    private void initComplement() {
        myApp = (App) getActivity().getApplication();
        mCalendar = Calendar.getInstance();
        mCache = new File(Environment.getExternalStorageDirectory(),
                "hwagtCache");
        if (!mCache.exists())
            mCache.mkdirs();

        mObjOrganizationID = -1;
        try {
            mObjOrganizationID = Integer.parseInt(myApp.getComInfo().getOrg_id());
        } catch (Exception pE) {
            pE.printStackTrace();
        }
    }

    private void registListener() {
//        mJcbDetailEdit.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View pView, MotionEvent pMotionEvent) {
//                if (canVerticalScroll(mJcbDetailEdit)) {
//                    pView.getParent().requestDisallowInterceptTouchEvent(true);
//                    if (pMotionEvent.getAction() == MotionEvent.ACTION_UP) {
//                        pView.getParent().requestDisallowInterceptTouchEvent(false);
//                    }
//                }
//                return false;
//            }
//        });
//        mJcbDetailPre.setOnClickListener(this);
//        mJcbDetailNext.setOnClickListener(this);
//        mJcbDetail.setOnClickListener(this);
//        mDateZg.setInputType(InputType.TYPE_DATETIME_VARIATION_NORMAL);
//        mDateZg.setOnClickListener(new View.OnClickListener() {
//
//            @Override
//            public void onClick(View arg0) {
//                // TODO Auto-generated method stub
//                new DatePickerDialog(getActivity(),
//                        new DatePickerDialog.OnDateSetListener() {
//                            public void onDateSet(DatePicker view, int year,
//                                                  int monthOfYear, int dayOfMonth) {
//                                String monthStr;
//                                if (monthOfYear < 9) {
//                                    monthStr = "0" + (monthOfYear + 1);
//                                } else {
//                                    monthStr = (monthOfYear + 1) + "";
//                                }
//                                String dayStr;
//                                if (dayOfMonth < 10) {
//                                    dayStr = "0" + dayOfMonth;
//                                } else {
//                                    dayStr = dayOfMonth + "";
//                                }
//                                mDateZg.setText(String.format("%s-%s-%s", String.valueOf(year), monthStr, dayStr));
//                            }
//                        }, mCalendar.get(Calendar.YEAR), mCalendar.get(Calendar.MONTH),
//                        mCalendar.get(Calendar.DAY_OF_MONTH)).show();
//            }
//        });
//        mDateZg.setOnFocusChangeListener(new View.OnFocusChangeListener() {
//
//            @Override
//            public void onFocusChange(View arg0, boolean arg1) {
//                // TODO Auto-generated method stub
//                if (arg1) {
//                    new DatePickerDialog(getActivity(),
//                            new DatePickerDialog.OnDateSetListener() {
//                                public void onDateSet(DatePicker view,
//                                                      int year, int monthOfYear,
//                                                      int dayOfMonth) {
//                                    String monthStr;
//                                    if (monthOfYear < 9) {
//                                        monthStr = "0" + (monthOfYear + 1);
//                                    } else {
//                                        monthStr = (monthOfYear + 1) + "";
//                                    }
//                                    String dayStr;
//                                    if (dayOfMonth < 10) {
//                                        dayStr = "0" + dayOfMonth;
//                                    } else {
//                                        dayStr = dayOfMonth + "";
//                                    }
//                                    mDateZg.setText(String.format("%s-%s-%s", String.valueOf(year), monthStr, dayStr));
//                                }
//                            }, mCalendar.get(Calendar.YEAR), mCalendar
//                            .get(Calendar.MONTH), mCalendar
//                            .get(Calendar.DAY_OF_MONTH)).show();
//
//                }
//            }
//        });
//        mYhdjSp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> pAdapterView, View pView, int pI, long pL) {
//                if (pI > 0) {
//                    mDateZg.setText(getAfterFiveDay());
//                } else {
//                    mDateZg.setText("");
//                }
//                String item = (String) pAdapterView.getItemAtPosition(pI);
//                if ("无隐患".equals(item)) {
//                    mzgLayout.setVisibility(View.GONE);
//
//                } else {
//                    mzgLayout.setVisibility(View.VISIBLE);
//                }
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> pAdapterView) {
//            }
//        });
//        mJcbSp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> pAdapterView, View pView, int pI, long pL) {
//                IChooseItem item = (IChooseItem) pAdapterView.getItemAtPosition(pI);
//                if ("0".equals(item.getItemId())) {
//                    mJcbLn.setVisibility(View.GONE);
//                    mJyzgEdt.setText("");
//                } else {
//                    mJcbLn.setVisibility(View.VISIBLE);
////                    mPresenter.getJcbDetail(item.getItemId());
//                    bar.setVisibility(View.VISIBLE);
//                }
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> pAdapterView) {
//
//            }
//        });
//        mCsSp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> pAdapterView, View pView, int pI, long pL) {
//                IChooseItem item = (IChooseItem) pAdapterView.getItemAtPosition(pI);
////                mPresenter.getSbData(item.getItemId());
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> pAdapterView) {
//
//            }
//        });
    }


//    @Override
//    public void commitStatus(boolean isSuccess) {
//        toast("提交" + (isSuccess ? "成功" : "失败,请重试"));
//        if (isSuccess) {
//            MyCamera myCamera = new MyCamera(getActivity());
//            myCamera.success();
//            mCameraBtn.setBackgroundResource(R.drawable.yjbj_xcpz_btn);
//            mCameraBtn.setText("");
//            mCameraBtn.setTag(null);
//            /* mPhotoCount.setText("0");*/
//            mYhdjSp.setSelection(0);
//            mAudioBtn.setBackgroundResource(R.drawable.yjbj_xcly_btn);
//            mAudioBtn.setText("");
//            mAudioBtn.setTag(null);
//            mJyzgEdt.setText("");
//        }
//    }
//

//    @Override
//    public void pendingDialog(int resId) {
//        if (mProgressDialog == null) {
//            mProgressDialog = new ProgressDialog(getActivity());
//            mProgressDialog.setCanceledOnTouchOutside(false);
//        }
//        mProgressDialog.setMessage(getString(resId));
//        mProgressDialog.show();
//    }
//
//    @Override
//    public void setJcbDetail(String detail) {
//        Intent intent = new Intent(getActivity(), JcbDetailActivity.class);
//        intent.putExtra("detail", detail);
//        startActivity(intent);
//    }
//
//    @Override
//    public void setJcbDetailGone() {
//        mJcbDetail.setVisibility(View.GONE);
//    }
//
//    @Override
//    public void cancelDialog() {
//        if (mProgressDialog != null) {
//            mProgressDialog.cancel();
//        }
//    }
//
//    @Override
//    public void setPlaceId(String pS) {
//        MySpinnerAdapter<CsInfo> adapter = (MySpinnerAdapter<CsInfo>) mCsSp.getAdapter();
//        int selection = adapter.selectItemById(pS);
//        mCsSp.setSelection(selection == -1 ? 0 : selection);
//        isSetPlace = true;
//    }

    @OnClick({R.id.rl_safety_check, R.id.rl_place, R.id.et_rectification_period_date, R.id.rl_hazard_registration
            , R.id.ib_up, R.id.ib_down})
    public void onClick(View pView) {
        switch (pView.getId()) {
            case R.id.rl_safety_check:
                showSafetyCheck(mSafetyCheckList);
                break;
            case R.id.rl_place:
                showPlace(mPlaceList);
                break;
            case R.id.et_rectification_period_date:
                showDateDialog();
                break;
            case R.id.rl_hazard_registration:
                showHazardRegistration(mHazardRegistration);
                break;
            case R.id.ib_up:
                if (mIndex == 0) {
                    showToast("没有上一条");
                    return;
                }
                mIndex--;
                mEtSafetyCheckDetail.setText(mSelectSafetyCheckList.get(mIndex).getOdetail());
                mTvSafetyCheckNumber.setText(String.format(getString(R.string.tv_safety_check_number), mIndex + 1, mSelectSafetyCheckList.size()));
                break;
            case R.id.ib_down:
                if (mIndex == mSelectSafetyCheckList.size() - 1) {
                    showToast("没有下一条");
                    return;
                }
                mIndex++;
                mEtSafetyCheckDetail.setText(mSelectSafetyCheckList.get(mIndex).getOdetail());
                mTvSafetyCheckNumber.setText(String.format(getString(R.string.tv_safety_check_number), mIndex + 1, mSelectSafetyCheckList.size()));
                break;

        }
        int id = pView.getId();
//        if (id == mCameraBtn.getId()) {
//            Intent intent = new Intent(getActivity(), CameraActivity.class);
//            startActivityForResult(intent, CAMERA);
//            /*mMyCamera.takePhoto();*/
//        } else if (id == mAudioBtn.getId()) {
//            Intent intent = new Intent(getActivity(), AudioRecordUtil.class);
//            String audioPath = mCache.getAbsolutePath() + File.separator
//                    + DateFormat.format("yyyyMMddHHmmss", new Date())
//                    + ".mp3";
//            intent.putExtra("mAudioPath", audioPath);
//
//            startActivityForResult(intent, AUDIO);
//        } else
//        if (id == mJcbDetailNext.getId()) {
////            mPresenter.getNextPage();
//        } else if (id == mJcbDetailPre.getId()) {
////            mPresenter.getPrePage();
////        } else if (id == mQrCodeBtn.getId()) {
//////            mMyCamera.releaseCamera();
////            Intent intent = new Intent(getActivity(), CameraTestActivity.class);
////            startActivityForResult(intent, QRCODE_REQUEST);
////        }else if (id == mCommitBtn.getId()) {
////            commitTag = true;
////            commit();
//        } else if (id == mJcbDetail.getId()) {
////            mPresenter.getJcbDetail();
//        } else

    }

    private void showDateDialog() {
        Calendar calendar = Calendar.getInstance();
        DatePickerDialog dialog = new DatePickerDialog(mContext, this,
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH));
        dialog.show();
    }

    private void commit() {
        int month = mCalendar.get(Calendar.MONTH) + 1;
        String monthStr;
        if (month < 10) {
            monthStr = "0" + month;
        } else {
            monthStr = month + "";
        }
        int day = mCalendar.get(Calendar.DAY_OF_MONTH);
        String dayStr;
        if (day < 10) {
            dayStr = "0" + day;
        } else {
            dayStr = day + "";
        }
        String nowDate = mCalendar.get(Calendar.YEAR) + "-"
                + monthStr + "-" + dayStr + "T00:00:00.000";
        String limit = "";
        int lEmid = 0;
        int bmId = -1;
        float dCost = 0f;
        String dScheme = "", hGrade = "";
//        if (!"无隐患".equals(mYhdjSp.getSelectedItem().toString())) {
//            String zgDate = mDateZg.getText().toString();
//            if (!TextUtils.isEmpty(zgDate)) {
//                limit = zgDate
//                        + "T00:00:00.000";
//            }
////            lEmid = 0;
//            dScheme = mJyzgEdt.getText().toString();
//            hGrade = mYhdjSp.getSelectedItem().toString();
//            String zgfyStr = mYgfyEdt.getText()
//                    .toString();
//            if (!TextUtils.isEmpty(zgfyStr)) {
//                dCost = Float.parseFloat(zgfyStr);
//            }
//        }
        /* int taskId = Integer.parseInt(((IChooseItem) mRwSp.getSelectedItem()).getItemId());*/
//        int fliedId = Integer.parseInt(((IChooseItem) mCsSp.getSelectedItem()).getItemId());
        /* String sbMark = ((IChooseItem) mSbSp.getSelectedItem()).getItemName();*/
        String sbMark = "";
        AqjcCommitInfo info = new AqjcCommitInfo();
        info.setCheckDate(nowDate);
        info.setDCost(dCost);
        info.setDLimit(limit);
        info.setDScheme(dScheme);
//        info.setFliedID(fliedId);
//        String hDetail = /*"场所：" + ((IChooseItem) mCsSp.getSelectedItem()).getItemName() +
//                ",设备：" + ((IChooseItem) mSbSp.getSelectedItem()).getItemName() + "。" +*/ mJcbDetailEdit.getText().toString();
//        /*String hDetail = "场所：,设备：";*/
//        info.setHDetail(hDetail);
        info.setHGrade(hGrade);
        info.setLEmid(lEmid);
        info.setObjOrganizationID(mObjOrganizationID);
        info.setObjPartid(bmId);
        /*  info.setTaskName(((IChooseItem) mRwSp.getSelectedItem()).getItemName());*/
//        info.setCsName(((IChooseItem) mCsSp.getSelectedItem()).getItemName());
        /*    info.setTaskid(taskId);*/
        info.setSetStr(sbMark);
        info.setRecEmid(Integer.parseInt(myApp.getComInfo().getEmid()));
        // TODO: 2018/3/1  照片
       /* ArrayList<String> imagePaths = mMyCamera.getImageDatas();
        StringBuilder stringBuilder = new StringBuilder();
        for (String imagePath : imagePaths) {
            stringBuilder.append(imagePath);
            stringBuilder.append(",");
        }
        String imagePath = "";
        if (stringBuilder.length() > 0) {
            imagePath = stringBuilder.substring(0, stringBuilder.length() - 1);
        }*/
//        String audioPath = mAudioBtn.getTag() == null ? "" : "," + StringUtil.noNull(mAudioBtn.getTag());
//        String imagePath = mCameraBtn.getTag() == null ? "" : "," + StringUtil.noNull(mCameraBtn.getTag());
        /* info.setImagePath(imagePath + audioPath);*/
//        info.setImagePath(imagePath + audioPath);
//        mPresenter.upload(info);
    }

    private String getAfterFiveDay() {
        String limit;
        long fiveTime = new Date().getTime() + 5 * 24 * 60 * 60 * 1000;
        Date five = new Date(fiveTime);
        Calendar instance = Calendar.getInstance();
        instance.setTime(five);
        int fiveMonth = instance.get(Calendar.MONTH) + 1;
        String fiveMonthStr;
        if (fiveMonth < 10) {
            fiveMonthStr = "0" + fiveMonth;
        } else {
            fiveMonthStr = fiveMonth + "";
        }
        int fiveDay = instance.get(Calendar.DAY_OF_MONTH);
        String fiveDayStr;
        if (fiveDay < 10) {
            fiveDayStr = "0" + fiveDay;
        } else {
            fiveDayStr = fiveDay + "";
        }
        limit = instance.get(Calendar.YEAR) + "-"
                + fiveMonthStr + "-" + fiveDayStr;
        return limit;
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        try {
            if (requestCode == CAMERA) {
                String imagePathResult = data.getStringExtra("imagePathResult");
                if (imagePathResult != null) {
//                    mCameraBtn.setBackgroundResource(R.drawable.blue_button_background);
//                    mCameraBtn.setText("重拍");
//                    mCameraBtn.setTag(imagePathResult);
                }
            } else if (requestCode == AUDIO) // 录音
            {
                // 显示多媒体View
                String sdStatus = Environment.getExternalStorageState();
                if (!sdStatus.equals(Environment.MEDIA_MOUNTED)) {
                    // 检测 sdcard 是否可用
                    Toast.makeText(getActivity(), "SD卡不存在", Toast.LENGTH_LONG).show();
                    return;
                }
                String strResult = data.getStringExtra("result");
                System.out.println("audio:" + strResult);
                if (strResult != null) {
//                    mAudioBtn.setBackgroundResource(R.drawable.blue_button_background);
//                    mAudioBtn.setText("重录");
//                    mAudioBtn.setTag(strResult);
                }

            } else if (requestCode == QRCODE_REQUEST) {
                String ewmStr = data.getStringExtra("result");
                String[] resultArr = ewmStr.split("-");
                if (resultArr.length == 2) {
                    try {
                        String type = resultArr[0];
                        if (type.equals("aqss")) {
//                            MySpinnerAdapter<SbInfo> adapter = (MySpinnerAdapter<SbInfo>) mSbSp.getAdapter();
//                            // TODO: 2018/3/21
////                                adapter.resetData();
//                            mSbSp.setSelection(adapter.selectItemById(resultArr[1]));
//                        } else if (type.equals("chsu")) {
//                            MySpinnerAdapter<CsInfo> adapter = (MySpinnerAdapter<CsInfo>) mCsSp.getAdapter();
//                            int selection = adapter.selectItemById(resultArr[1]);
//                            mCsSp.setSelection(selection == -1 ? 0 : selection);
//                            if (selection != -1) {
//                                MySpinnerAdapter<SbInfo> sbAdapter = (MySpinnerAdapter<SbInfo>) mSbSp.getAdapter();
//                                sbAdapter.filterSb(resultArr[1]);
//                            }
                        } else if (type.equals("sjdw")) {
                            try {
                                mObjOrganizationID = Integer.parseInt(resultArr[1]);
                            } catch (NumberFormatException pE) {
//                                toast(getString(R.string.ewm_failed));
                            }
                        } else if ("sssb".equals(type)) {
                            try {
                                mEwmSssbId = Integer.parseInt(resultArr[1]);
//                                mPresenter.getSssb(mEwmSssbId, Integer.parseInt(myApp.getComInfo().getCom_id()));
                            } catch (NumberFormatException pE) {
//                                toast(getString(R.string.ewm_failed));
                            }
                        } else {
//                            toast(getString(R.string.ewm_failed));
                        }
                    } catch (Exception pE) {
                        pE.printStackTrace();
//                        toast(getString(R.string.ewm_failed));
                    }
                } else {
//                    toast(getString(R.string.ewm_failed));
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean onBackPressed() {
        if (!commitTag) {
            new SweetAlertDialog(mContext,
                    SweetAlertDialog.WARNING_TYPE)
                    .setTitleText(
                            getResources().getString(
                                    R.string.dialog_default_title))
                    .setContentText("当前数据未提交是否要返回?").setCancelText("取消")
                    .setConfirmText("确定").showCancelButton(true)
                    .setCancelClickListener(new SweetAlertDialog.OnSweetClickListener() {

                        @Override
                        public void onClick(SweetAlertDialog sweetAlertDialog) {
                            sweetAlertDialog.cancel();
                        }
                    }).setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {

                @Override
                public void onClick(SweetAlertDialog sweetAlertDialog) {
                    sweetAlertDialog.cancel();
                    getActivity().finish();
                }
            }).show();
            return true;
        } else {
            return false;
        }

    }

    @Override
    public void getSafetyCheckList(List<SafetyCheck> list) {
        mAllSafetyCheckList = list;
        mSafetyCheckList = removeDuplicateSafetyCheck(list);
    }

    @Override
    public void getAllPlace(List<PlaceEntity> list) {
        mPlaceList = list;
    }

    private void showSafetyCheck(List<SafetyCheck> safetyCheckList) {

        BaseQuickAdapter<SafetyCheck, BaseViewHolder> adapter = new BaseQuickAdapter<SafetyCheck,
                BaseViewHolder>(R.layout.item_company_list, safetyCheckList) {
            @Override
            protected void convert(@NonNull BaseViewHolder helper, final SafetyCheck item) {
                helper.setText(R.id.tv_company_name, item.getObliTitle());
                helper.getView(R.id.tv_company_name).setSelected(true);
            }
        };
        AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
        final AlertDialog dialog = builder.create();
        @SuppressLint("InflateParams") View view = LayoutInflater.from(mContext).inflate(R.layout.dialog_list, null);
        RecyclerView dialogList = view.findViewById(R.id.rv_dialog_list);
        ((TextView) view.findViewById(R.id.tv_dialog_title)).setText("检查表");
        dialogList.setLayoutManager(new LinearLayoutManager(mContext));
        dialogList.setAdapter(adapter);
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                SafetyCheck item = (SafetyCheck) adapter.getItem(position);
                if (item != null) {
                    mTvSafetyCheckContent.setText(item.getObliTitle());
                }
                dialog.dismiss();
            }
        });
        dialog.setView(view);
        dialog.show();
    }

    private void showPlace(List<PlaceEntity> place) {

        BaseQuickAdapter<PlaceEntity, BaseViewHolder> adapter = new BaseQuickAdapter<PlaceEntity,
                BaseViewHolder>(R.layout.item_company_list, place) {
            @Override
            protected void convert(@NonNull BaseViewHolder helper, final PlaceEntity item) {
                helper.setText(R.id.tv_company_name, item.getMplname());
                helper.getView(R.id.tv_company_name).setSelected(true);
            }
        };
        AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
        final AlertDialog dialog = builder.create();
        @SuppressLint("InflateParams") View view = LayoutInflater.from(mContext).inflate(R.layout.dialog_list, null);
        RecyclerView dialogList = view.findViewById(R.id.rv_dialog_list);
        ((TextView) view.findViewById(R.id.tv_dialog_title)).setText("场所");
        dialogList.setLayoutManager(new LinearLayoutManager(mContext));
        dialogList.setAdapter(adapter);
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                PlaceEntity item = (PlaceEntity) adapter.getItem(position);
                if (item != null) {
                    mTvPlaceContent.setText(item.getMplname());
                }
                dialog.dismiss();
            }
        });
        dialog.setView(view);
        dialog.show();
    }

    private void showHazardRegistration(List<String> hazardRegistration) {

        BaseQuickAdapter<String, BaseViewHolder> adapter = new BaseQuickAdapter<String,
                BaseViewHolder>(R.layout.item_company_list, hazardRegistration) {
            @Override
            protected void convert(@NonNull BaseViewHolder helper, final String item) {
                helper.setText(R.id.tv_company_name, item);
                helper.getView(R.id.tv_company_name).setSelected(true);
            }
        };
        AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
        final AlertDialog dialog = builder.create();
        @SuppressLint("InflateParams") View view = LayoutInflater.from(mContext).inflate(R.layout.dialog_list, null);
        RecyclerView dialogList = view.findViewById(R.id.rv_dialog_list);
        ((TextView) view.findViewById(R.id.tv_dialog_title)).setText("隐患等级");
        dialogList.setLayoutManager(new LinearLayoutManager(mContext));
        dialogList.setAdapter(adapter);
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                String item = (String) adapter.getItem(position);
                if (item != null) {
                    mTvHazardRegistrationContent.setText(item);
                }
                dialog.dismiss();
            }
        });
        dialog.setView(view);
        dialog.show();
    }

    private static ArrayList<SafetyCheck> removeDuplicateSafetyCheck(List<SafetyCheck> list) {
        Set<SafetyCheck> set = new TreeSet<>(new Comparator<SafetyCheck>() {
            @Override
            public int compare(SafetyCheck o1, SafetyCheck o2) {
                return o1.getObliTitle().compareTo(o2.getObliTitle());
            }
        });
        set.addAll(list);
        return new ArrayList<>(set);
    }

    @Override
    public void onErrorCode(BaseModel model) {

    }


    @Override
    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
        mEtRectificationPeriodDate.setText(String.format("%s-%s-%s", i, i1 + 1, i2));
    }
}
