package cn.fjmz.agt.activity;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import cn.fjmz.agt.App;
import cn.fjmz.agt.DeviceInfo;
import cn.fjmz.agt.R;
import cn.fjmz.agt.SbjcCommitInfo;
import cn.fjmz.agt.SbjcCommitInfoDao;
import cn.fjmz.agt.SbjcListInfoDao;
import cn.fjmz.agt.dao.SqliteOperator;
import cn.fjmz.agt.util.StringUtil;
import cn.fjmz.agt.util.WebServiceUtil;

import de.greenrobot.dao.query.QueryBuilder;


public class AqssDetailActivity extends Activity {

    private int SCPID;
    private Context context;
    private TextView sbmc, byry, byrq, sbzt, qtqk;
    private EditText edt;
    private Button detailCommit;
    private Spinner sbztSp;
    protected Calendar canl;
    protected App myApp;
    protected ArrayList<HashMap<String, Object>> dataList;
    protected Handler handler = new Handler(new Handler.Callback() {

        @Override
        public boolean handleMessage(Message arg0) {
            switch (arg0.what) {
                case 1:
                    if (dataList.size() > 0) {
                        HashMap<String, Object> hashMap = dataList.get(0);
                        String sbNum = StringUtil.noNull(hashMap.get("scnumber"));
                        if (!TextUtils.isEmpty(sbNum)) {
                            sbNum = String.format("(%s)", sbNum);
                        }
                        sbmc.setText(String.format("%s%s", hashMap.get("prodname").toString(), sbNum));
                        byry.setText(hashMap.get("sccheckname").toString());
                        String date = StringUtil.noNull(hashMap.get("sccheckdate"));
                        if (date.length() > 10) {
                            date = date.substring(0, 10);
                        }
                        byrq.setText(date);
                        sbzt.setText(hashMap.get("sccheckstat").toString());
                        qtqk.setText(hashMap.get("sccheckdetail").toString());
                    }
                    break;
                case 5:
                    detailCommit.setEnabled(true);
                    Toast.makeText(context, "提交失败。", Toast.LENGTH_LONG).show();
                    break;
                case 6:
                    detailCommit.setEnabled(true);
                    Toast.makeText(context, "提交成功。", Toast.LENGTH_LONG).show();
                    edt.setText("");
                    finish();
                    break;
                case 7:
                    detailCommit.setEnabled(true);
                    Toast.makeText(context, "未联网，已保存！等待下次联网后再提示", Toast.LENGTH_LONG)
                            .show();
                    edt.setText("");
                    finish();
                    break;
                default:
                    break;
            }
            return false;
        }
    });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.aqss_detail);
        initComplement();
        if (App.isConnection) {
            getData();
        } else {
            SbjcListInfoDao dao = SqliteOperator.INSTANCE.getSbjcListInfoDao(AqssDetailActivity.this);
            DeviceInfo info = getDeviceInfo(dao);
            sbmc.setText(info.getProdname());
            byry.setText(info.getSccheckname());
            byrq.setText(info.getSccheckdate());
            sbzt.setText(info.getSccheckstat());
            qtqk.setText(info.getSccheckdetail());
        }
    }


    private DeviceInfo getDeviceInfo(SbjcListInfoDao pDao) {
        QueryBuilder<DeviceInfo> qb = pDao.queryBuilder();
        qb.where(SbjcListInfoDao.Properties.Cpid.eq(SCPID));
        List<DeviceInfo> listInfos = qb.build().list();
        DeviceInfo info;
        if (listInfos != null && !listInfos.isEmpty()) {
            info = listInfos.get(0);
        } else {
            info = new DeviceInfo();

        }
        return info;
    }

    private void initComplement() {
        canl = Calendar.getInstance();
        myApp = (App) getApplication();
        if (getActionBar() != null) {
            getActionBar().setTitle("设备巡查");
            getActionBar().setDisplayHomeAsUpEnabled(true);
        }
        context = AqssDetailActivity.this;
        SCPID = getIntent().getIntExtra("SCPID", 0);
        sbmc = (TextView) findViewById(R.id.aqssdetail_sbmc);
        byry = (TextView) findViewById(R.id.aqssdetail_byry);
        byrq = (TextView) findViewById(R.id.aqssdetail_byrq);
        sbzt = (TextView) findViewById(R.id.aqssdetail_sbzt);
        qtqk = (TextView) findViewById(R.id.aqssdetail_qtqk);
        edt = (EditText) findViewById(R.id.aqssdetail_edt);
        sbztSp = (Spinner) findViewById(R.id.aqssdetail_sbztsp);
        detailCommit = (Button) findViewById(R.id.aqssdetail_commit);
    }

    public void commit(View view) {
        if (TextUtils.isEmpty(edt.getText().toString())) {
            return;
        }
        detailCommit.setEnabled(false);
        new Thread(new Runnable() {

            @Override
            public void run() {
                // TODO Auto-generated method stub
                try {
                    int month = canl.get(Calendar.MONTH) + 1;
                    String monthStr = "";
                    if (month < 10) {
                        monthStr = "0" + month;
                    } else {
                        monthStr = month + "";
                    }
                    int day = canl.get(Calendar.DAY_OF_MONTH);
                    String dayStr = "";
                    if (day < 10) {
                        dayStr = "0" + day;
                    } else {
                        dayStr = day + "";
                    }
                    String nowDate = canl.get(Calendar.YEAR) + "-" + monthStr
                            + "-" + dayStr + "T00:00:00.000";
                    int emid = Integer.parseInt(myApp.getComInfo().getEmid());
                    if (!App.isConnection) {
                        SbjcCommitInfo info = new SbjcCommitInfo();
                        info.setCPID(SCPID);
                        info.setCpName(sbmc.getText().toString());
                        info.setCDate(nowDate);
                        info.setCEmid(emid);
                        info.setREmid(emid);
                        info.setCheckDate(new Date());
                        info.setCRemark(edt.getText().toString());
                        info.setCState(sbztSp.getSelectedItem().toString());
                        SbjcCommitInfoDao sbjcCommitInfoDao = SqliteOperator.INSTANCE.getSbCommitDao(AqssDetailActivity.this);
                        sbjcCommitInfoDao.insert(info);
                    } else {
                        String[] keys = {"CPID", "cDate", "cEmid", "rEmid",
                                "cRemark", "cState"};
                        Object[] values = {SCPID, nowDate, emid, emid,
                                edt.getText().toString(),
                                sbztSp.getSelectedItem().toString()};
                        dataList = WebServiceUtil.getWebServiceMsg(keys,
                                values, "AddSafetySetCheck", new String[]{},
                                WebServiceUtil.SAFE_URL);
                    }
                    if (App.isConnection) {
                        handler.sendEmptyMessage(6);
                    } else {
                        handler.sendEmptyMessage(7);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    handler.sendEmptyMessage(5);
                }
            }
        }).start();
    }

    private void getData() {
        new Thread(new Runnable() {

            @Override
            public void run() {
                try {
                    String[] keys = {"SCPID", "CPID", "cStart", "cEnd",
                            "cState"};
                    Object[] values = {0, SCPID, "2000-01-01T00:00:00.000",
                            "2035-12-31T00:00:00.000", "正常"};//
                    dataList = WebServiceUtil.getWebServiceMsg(keys, values,
                            "getSafetySetCheck",
                            WebServiceUtil.SAFE_URL, WebServiceUtil.WEBSERVICE_NAMESPACE);
                    if (dataList != null)
                        handler.sendEmptyMessage(1);
                } catch (Exception e) {
                    Log.e("Exception:", e.getMessage());
                }
            }
        }).start();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
            default:
                break;
        }
        return true;
    }
}
