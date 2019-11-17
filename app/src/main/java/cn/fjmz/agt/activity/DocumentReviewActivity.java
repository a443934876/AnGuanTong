package cn.fjmz.agt.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TableRow.LayoutParams;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import cn.fjmz.agt.App;
import cn.fjmz.agt.R;
import cn.fjmz.agt.base.BaseActivity;
import cn.fjmz.agt.base.BaseModel;
import cn.fjmz.agt.base.Constants;
import cn.fjmz.agt.bean.DocumentDetailEntity;
import cn.fjmz.agt.bean.DocumentEntity;
import cn.fjmz.agt.bean.DocumentListEntity;
import cn.fjmz.agt.presenter.DocumentReviewPresenter;
import cn.fjmz.agt.presenter.interfaces.DocumentReviewView;
import cn.fjmz.agt.util.StringUtil;
import cn.fjmz.agt.util.TableParse;
import cn.fjmz.agt.util.WebServiceUtil;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import cn.pedant.SweetAlert.SweetAlertDialog;


public class DocumentReviewActivity extends BaseActivity<DocumentReviewPresenter> implements DocumentReviewView {

    @BindView(R.id.iv_title_left)
    ImageView mIvTitleLeft;
    @BindView(R.id.tv_title_center)
    TextView mTvTitleCenter;
    @BindView(R.id.tv_review_company)
    TextView mTvReviewCompany;
    @BindView(R.id.tv_review_number)
    TextView mTvReviewNumber;
    @BindView(R.id.tv_review_title)
    TextView mTvReviewTitle;
    @BindView(R.id.tv_review_content)
    TextView mTvReviewContent;
    @BindView(R.id.tv_review_remark)
    TextView mTvReviewRemark;
    @BindView(R.id.tv_review_company_name)
    TextView mTvReviewCompanyName;
    @BindView(R.id.tv_review_date)
    TextView mTvReviewDate;
    @BindView(R.id.tv_review_prompt)
    TextView mTvReviewPrompt;
    @BindView(R.id.et_review)
    EditText mEtReview;
    @BindView(R.id.bt_review_commit)
    Button mBtReviewCommit;
    @BindView(R.id.tv_review_object)
    TextView mTvReviewObject;
    private TextView gwfbsj, cyrs;
    private String gwid;
    private String emid;
    private String gwcyid;
    private String orgidStr;
    private String DocId;
    private App myApp;
    private Thread myGetDataThread;
    private ArrayList<HashMap<String, Object>> gwData;
    private HashMap<String, String> tableOrImageData;
    private String comnameStr = "";
    private LinearLayout gwpy_fjwd, gwpy_fjbg;
    private static String mKey_DocumentEntity = "DocumentListEntity";
    private DocumentListEntity mDocumentListEntity;
    private StringBuilder mBuilder = new StringBuilder();
    private List<List<DocumentDetailEntity>> mEntityList = new ArrayList<>();

    public static void launch(Context context, DocumentListEntity entity) {
        Intent intent = new Intent(context, DocumentReviewActivity.class);
        intent.putExtra(mKey_DocumentEntity, entity);
        intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        context.startActivity(intent);
    }

    private Handler mHandler = new Handler(new Handler.Callback() {

        @Override
        public boolean handleMessage(Message msg) {
            // TODO Auto-generated method stub
            switch (msg.what) {
                case 1:
                    new SweetAlertDialog(DocumentReviewActivity.this,
                            SweetAlertDialog.SUCCESS_TYPE).setContentText("提交成功！")
                            .setConfirmText("确定")
                            .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {

                                @Override
                                public void onClick(
                                        SweetAlertDialog sweetAlertDialog) {
                                    sweetAlertDialog.cancel();
                                    myApp.isRefresh = true;
                                    finish();
                                }
                            }).show();
                    break;
                case 2:
                    Toast.makeText(DocumentReviewActivity.this, "提交失败。", Toast.LENGTH_LONG)
                            .show();
                    break;
                case 3:
                    if (gwData.size() > 0) {
                        mTvReviewTitle.setText(gwData.get(0).get("cDocTitle").toString());
                        StringBuilder sb = new StringBuilder();
                        sb.append(StringUtil.SPACE);
                        String overView = gwData.get(0).get("overview").toString();
                        overView = overView.replace("anyType#$", "");
                        sb.append(overView);
                        for (int i = 1; i < gwData.size(); i++) {
                            String detail = StringUtil.noNull(gwData.get(i).get(
                                    "cDocDetail"));
                            detail = detail.replace("anyType{}", "");
                            if (StringUtil.isNotEmpty(detail)) {
                                sb.append(StringUtil.ENTER + StringUtil.SPACE);
                                sb.append(detail);
                            }
                        }
                        mTvReviewContent.setText(sb.toString());
                        new Thread(new Runnable() {

                            @Override
                            public void run() {
                                String detail = setDetail("-1");
                                Message msg = mHandler.obtainMessage();
                                msg.what = 5;
                                msg.obj = detail;
                                mHandler.sendMessage(msg);
                            }
                        }).start();
                        String detail2 = StringUtil.noNull(gwData.get(0).get(
                                "cRemark"));
                        detail2 = detail2.replace("anyType#$", "");

                        if (StringUtil.isNotEmpty(detail2)) {
                            mTvReviewRemark.setText(String.format("%s%s%s", StringUtil.ENTER, StringUtil.SPACE, detail2));
                        }
                        String dateStr = gwData.get(0).get("cdate").toString();
                        String[] dateStrs = dateStr.split("T");
                        if (dateStrs.length > 0) {
                            mTvReviewDate.setText(dateStrs[0]);
                            gwfbsj.setText(dateStrs[0]);
                        }
                        // System.out.println(StringUtil.noNull(gwData.get(1).get(
                        // "inImage")));
                    } else {
                        Toast.makeText(DocumentReviewActivity.this, "获取无数据，请重试。",
                                Toast.LENGTH_LONG).show();
                    }
                    break;
                case 4:
                    Toast.makeText(DocumentReviewActivity.this, "连接服务器失败，请重试。",
                            Toast.LENGTH_LONG).show();
                    break;
                case 5:
                    mTvReviewContent.append(String.valueOf(msg.obj));
                    mTvReviewCompanyName.setText(comnameStr);
                    if (StringUtil.isNotEmpty(tableOrImageData.get("image"))) {
                        String[] images = tableOrImageData.get("image").split(";");
                        for (int i = 0; i < images.length; i++) {
                            String url = images[i].replace("../", "");
                            ImageView iv = new ImageView(DocumentReviewActivity.this);
                            iv.setClickable(true);
                            gwpy_fjwd.addView(iv);
                            String realUrl = WebServiceUtil.IMAGE_URLPATH + url;
                            Glide.with(DocumentReviewActivity.this)
                                    .load(realUrl)
                                    .placeholder(R.drawable.picture_load)
                                    .error(R.drawable.picture_load)
                                    .into(iv);
                            iv.setOnClickListener(new OnClickListener() {

                                @Override
                                public void onClick(View v) {
                                    String url = (String) v.getTag();
                                    if (StringUtil.isNotEmpty(url)) {
                                        System.out.println("imageUrl:" + url);
                                        File file = new File(url);
                                        Intent it = new Intent(Intent.ACTION_VIEW);
                                        it.setDataAndType(Uri.fromFile(file),
                                                "image/*");
                                        startActivity(it);
                                    }
                                }
                            });

                        }
                    }
                    if (StringUtil.isNotEmpty(tableOrImageData.get("table"))) {
                        try {
                            ArrayList<ArrayList<String>> tableData = TableParse
                                    .getTableData(tableOrImageData.get("table"));
                            TableLayout t = new TableLayout(DocumentReviewActivity.this);
                            t.setBackgroundColor(getResources().getColor(
                                    R.color.balck));
                            for (int x = 0; x < tableData.size(); x++) {
                                TableRow row = new TableRow(DocumentReviewActivity.this);
                                for (int i = 0; i < tableData.get(i).size(); i++) {
                                    TextView t1 = new TextView(DocumentReviewActivity.this);
                                    LayoutParams params = new LayoutParams();
                                    params.setMargins(1, 1, 1, 1);
                                    t1.setLayoutParams(params);
                                    t1.setBackgroundColor(getResources().getColor(
                                            R.color.white));
                                    t1.setText(tableData.get(x).get(i));
                                    t1.setTextColor(Color.BLACK);
                                    row.addView(t1);
                                }
                                t.addView(row);
                            }
                            gwpy_fjbg.addView(t);
                            // GridLinearLayout gl = new GridLinearLayout(
                            // DocumentReviewActivity.this);
                            // gl.setColumns(tableData.get(0).size());
                            // gl.setRows(tableData.size());
                            // gl.setHorizontalSpace(1);
                            // gl.setVerticalSpace(1);
                            // ArrayList<String> data = new ArrayList<String>();
                            // for (int i = 0; i < tableData.size(); i++) {
                            // for (int j = 0; j < tableData.get(i).size(); j++) {
                            // data.add(tableData.get(i).get(j));
                            // }
                            // }
                            // ArrayAdapter<String> ada = new ArrayAdapter<String>(
                            // DocumentReviewActivity.this,
                            // android.R.layout.simple_list_item_1, data);
                            // gl.setAdapter(ada);
                            // gl.bindLinearLayout();
                            // gwpy_fjbg.addView(gl);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                    }
                default:
                    break;
            }
            return false;
        }
    });


    private void getGwData() {
        myGetDataThread = new Thread(new Runnable() {

            @Override
            public void run() {
                // TODO Auto-generated method stub
                try {
                    String keys[] = {"orgIDstr", "cDocID", "keyWord",
                            "cStart", "cEnd", "onlyInTitle", "cState",
                            "userId", "cType", "docTempId", "retstr"};
                    // System.out.println("orgidstr" +
                    // orgItem.get("Emid").toString());
                    Object values[] = {"", DocId, "",
                            "1900-01-01T00:00:00.850",
                            "2049-12-31T00:00:00.850", false, true, 0, "", 0, ""};
                    ArrayList<HashMap<String, Object>> data = WebServiceUtil
                            .getWebServiceMsg(keys, values,
                                    "getCapacityDocument", WebServiceUtil.HUIWEI_URL, WebServiceUtil.HUIWEI_NAMESPACE);
                    gwData.addAll(data);
                    mHandler.sendEmptyMessage(3);
                } catch (InterruptedException ignored) {
                } catch (Exception e) {
                    e.printStackTrace();
                    mHandler.sendEmptyMessage(4);
                }
            }
        });
        myGetDataThread.start();
    }

    private void initComplement() {
        myApp = (App) getApplication();
        gwData = new ArrayList<>();
        String gwidStr = getIntent().getStringExtra(mDocumentListEntity.getCDocID());// 公文ID
        String emidStr = Constants.entity.getEmId();
        String gwcyidStr = gwidStr + emidStr;
        try {
            DocId = getIntent().getStringExtra(mDocumentListEntity.getCDocID());
        } catch (Exception e) {
            Toast.makeText(DocumentReviewActivity.this, "获取雇员ID错误，请重试。",
                    Toast.LENGTH_LONG).show();
            return;
        }
        orgidStr = Constants.entity.getOrgIdStr();
        if (StringUtil.isNotEmpty(emidStr)) {
            emid = emidStr;
            gwcyid = gwcyidStr;
        } else {
            Toast.makeText(DocumentReviewActivity.this, "获取雇员ID错误，请重试。",
                    Toast.LENGTH_LONG).show();
            return;
        }
        if (StringUtil.isNotEmpty(gwidStr)) {
            gwid = gwidStr;
        } else {
            Toast.makeText(DocumentReviewActivity.this, "获取公文ID错误，请重试。",
                    Toast.LENGTH_LONG).show();
            return;
        }
        mTvReviewTitle = (TextView) findViewById(R.id.gwpy_title);
        gwfbsj = (TextView) findViewById(R.id.gwpy_fbsj);
        cyrs = (TextView) findViewById(R.id.gwpy_cyrs);
        mTvReviewContent = (TextView) findViewById(R.id.gwpy_content);
        mTvReviewDate = (TextView) findViewById(R.id.gwpy_date);
        mTvReviewCompanyName = (TextView) findViewById(R.id.gwpy_comname);
        mEtReview = (EditText) findViewById(R.id.gwpy_pyedt);
        mBtReviewCommit = (Button) findViewById(R.id.gwpy_commit);
        mTvReviewRemark = (TextView) findViewById(R.id.gwpy_remark);
        gwpy_fjwd = (LinearLayout) findViewById(R.id.gwpy_fjwd);
        gwpy_fjbg = (LinearLayout) findViewById(R.id.gwpy_fjbg);
        tableOrImageData = new HashMap<>();
    }

    public void commit_gw(View view) {
        // if (StringUtil.isEmpty(mEtReview.getText().toString())) {
        // Toast.makeText(DocumentReviewActivity.this, "请填写批阅意见。", Toast.LENGTH_LONG)
        // .show();
        // return;
        // }
        new Thread(new Runnable() {

            @Override
            public void run() {
                // TODO Auto-generated method stub
                try {
                    // AuditEmid Infoid CRemark AddfilePaths IPStr IsAudited
                    // NextAuditEmid
                    String keys[] = {"Infoid", "Turnemid", "CRemark", "IPStr"};
                    // System.out.println("orgidstr" +
                    // orgItem.get("Emid").toString());
                    Object values[] = {gwid, emid, mEtReview.getText().toString(),
                            ""};
                    WebServiceUtil.putWebServiceMsg(keys, values,
                            "setInfoTurning", WebServiceUtil.HUIWEI_URL, WebServiceUtil.HUIWEI_NAMESPACE);
                    mHandler.sendEmptyMessage(1);
                } catch (Exception e) {
                    e.printStackTrace();
                    mHandler.sendEmptyMessage(2);
                }
            }
        }).start();
    }

    private void changeButtonStyle(boolean isPress, String alertMsg) {
        if (isPress) {
            mBtReviewCommit.setText(alertMsg);
            mBtReviewCommit.setBackgroundColor(Color.GRAY);
            mBtReviewCommit.setEnabled(false);
        } else {
            mBtReviewCommit.setEnabled(true);
            mBtReviewCommit.setText("提　　交");
            mBtReviewCommit.setBackgroundColor(getResources().getColor(R.color.login_bg));
        }
    }


    @Override
    protected DocumentReviewPresenter createPresenter() {
        return new DocumentReviewPresenter(this);
    }

    @Override
    protected void intiListener() {
        super.intiListener();
        mIvTitleLeft.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_document_review;
    }

    @Override
    protected void initData() {
        mTvTitleCenter.setText("公文详情");
        mIvTitleLeft.setImageResource(R.drawable.ic_title_back_arrow);
        mDocumentListEntity = (DocumentListEntity) getIntent().getSerializableExtra(mKey_DocumentEntity);
        mTvReviewCompany.setText(String.format(getString(R.string.org_name_file), mDocumentListEntity.getPuborgname()));
        mTvReviewTitle.setText(mDocumentListEntity.getInfoTitle());
        mTvReviewNumber.setText(mDocumentListEntity.getInfoNumber());
        mTvReviewObject.setText(String.format(getString(R.string.colon), mDocumentListEntity.getPubobj()));
        mTvReviewCompanyName.setText(mDocumentListEntity.getPubComname());
        mTvReviewDate.setText(mDocumentListEntity.getPubDate());
        mPresenter.getCapacityDocument(mDocumentListEntity.getCDocID());
//        initComplement();
//        getGwData();
    }


    private String setDetail(final String cDocDetailID) {
        StringBuffer sb = new StringBuffer();
        if (StringUtil.isEmpty(cDocDetailID)) {
            return sb.toString();
        }
        String keys2[] = {"orgIDstr", "cDocID", "titleKeyWord",
                "detailKeyWord", "carryPartID", "carryDutyID", "docType",
                "parentCDocID", "cDocDetailID", "retstr"};
        Object values2[] = {orgidStr, DocId, "", "", 0, 0, "",
                Integer.parseInt(cDocDetailID), 0, ""};
        ArrayList<HashMap<String, Object>> data2 = new ArrayList<HashMap<String, Object>>();
        try {
            data2 = WebServiceUtil.getWebServiceMsg(keys2, values2,
                    "getCapacityDocumentDetail", new String[]{
                            "carryPartName", "dLevel", "cDocDetailID",
                            "dSequence", "cDocDetail", "inTable", "inImage",
                            "createcom", "cDocDetail", "info_additional",
                            "info_additiondoc"}, WebServiceUtil.HUIWEI_URL, WebServiceUtil.HUIWEI_NAMESPACE);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        if (data2.size() > 0) {
            // 表格和图片数据
            String inImage = StringUtil.noNull(data2.get(0).get("inImage"))
                    .trim();
            inImage = inImage.replace("anyType{}", "");
            if (StringUtil.isNotEmpty(inImage)) {
                tableOrImageData.put("image", inImage);
            }
            String inTable = StringUtil.noNull(data2.get(0).get("inTable"))
                    .trim();
            inTable = inTable.replace("anyType{}", "");
            if (StringUtil.isNotEmpty(inTable)) {
                tableOrImageData.put("table", inTable);
            }
            for (int i = 0; i < data2.size(); i++) {
                String detail = StringUtil.noNull(
                        data2.get(i).get("cDocDetail")).trim();
                String sQe = StringUtil.noNull(data2.get(i).get("dSequence"));
                comnameStr = StringUtil.noNull(data2.get(i).get("createcom"));
                int code = Integer.parseInt(sQe);
                detail = detail.replace("anyType{}", "");
                if (StringUtil.isNotEmpty(detail)) {
                    sb.append(StringUtil.ENTER);
                    String dLevel = StringUtil.noNull(data2.get(i)
                            .get("dLevel"));
                    int level = 2;
                    if (StringUtil.isNotEmpty(dLevel)) {
                        level = Integer.parseInt(dLevel) + 1;
                        sQe = StringUtil.parseNumberByLevel(level, code);
                    }
                    for (int j = 0; j < level; j++) {
                        sb.append(StringUtil.SPACE);
                    }
                    sb.append(sQe + ".");
                    sb.append(detail);
                }
                sb.append(setDetail(StringUtil.noNull(data2.get(i).get(
                        "cDocDetailID"))));
            }
        }
        return sb.toString();
    }

    @Override
    public void onErrorCode(BaseModel model) {

    }

    @Override
    public void getCapacityDocument(DocumentEntity entity) {
        mTvReviewContent.setText(String.format("%s%s%s", StringUtil.SPACE, StringUtil.SPACE, entity.getOverview()));
        mPresenter.getCapacityDocumentDetail(Constants.entity.getOrgIdStr(), entity.getCDocid(), "-1");
    }

    @Override
    public void setInfoTurning(Object entity) {

    }

    @Override

    public void getCapacityDocumentDetail(List<DocumentDetailEntity> entityList) {
        if (entityList.size() > 0) {
            mEntityList.add(entityList);
        }
        for (DocumentDetailEntity entity : entityList) {
            String detail = StringUtil.noNull(entity.getCDocDetail());
            String sQe = StringUtil.noNull(entity.getDSequence());
            int code = Integer.parseInt(sQe);
            detail = detail.replace("anyType{}", "");
            if (StringUtil.isNotEmpty(detail)) {
                mBuilder.append(StringUtil.ENTER);
                String dLevel = StringUtil.noNull(entity.getDLevel());
                int level = 2;
                if (StringUtil.isNotEmpty(dLevel)) {
                    level = Integer.parseInt(dLevel) + 1;
                    sQe = StringUtil.parseNumberByLevel(level, code);
                }
                for (int j = 0; j < level; j++) {
                    mBuilder.append(StringUtil.SPACE);
                }
                mBuilder.append(sQe).append(".");
                mBuilder.append(detail);
                DocumentReviewPresenter presenter = new DocumentReviewPresenter(this);
                presenter.getCapacityDocumentDetail(Constants.entity.getOrgIdStr(), entity.getCDocID(), entity.getCDocDetailID());
                //TODO  MainPresenter1 presenter1 = new MainPresenter1(this);
                //        presenter.getTextApi();
//                mPresenter.getCapacityDocumentDetail(Constants.entity.getOrgIdStr(), entity.getCDocID(), entity.getCDocDetailID());
            }
        }
        mTvReviewContent.setText(mBuilder);
    }

}
