package cn.fjmz.agt.activity;

import java.util.ArrayList;
import java.util.HashMap;

import cn.fjmz.agt.MyApplication;
import cn.fjmz.agt.R;
import cn.fjmz.agt.adapter.DutyListAdapter;
import cn.fjmz.agt.util.WebServiceUtil;
import cn.fjmz.agt.view.XListView;

import android.app.ActionBar;
import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.MenuItem;


public class DutyDetailActivity extends Activity {

	private XListView dutyList;
	protected ArrayList<HashMap<String, Object>> data;
	protected Handler mHandler = new Handler(new Handler.Callback() {

		@Override
		public boolean handleMessage(Message msg) {
			// TODO Auto-generated method stub
			switch (msg.what) {
			case 1:
				DutyListAdapter adapter = new DutyListAdapter(data,
						DutyDetailActivity.this);
				dutyList.setAdapter(adapter);
				break;

			default:
				break;
			}
			return false;
		}
	});
	protected MyApplication myMyApplication;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.duty_detail_view);
		myMyApplication = (MyApplication) getApplication();
		initComplement();
		getData();
	}

	private void getData() {
		new Thread(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				try {
					String keys[] = { "Emid", "partid", "duid", "stat" };
					// System.out.println("orgidstr" +
					// orgItem.get("Emid").toString());
					Object values[] = {
							Integer.parseInt(myMyApplication.getComInfo().getEmid()), 0,
							0, "在职" };
					data = WebServiceUtil.getWebServiceMsg(keys, values,
							"getPartDutyFromEm", new String[] { "pName",
									"partName", "sName", "partid", "dutyid",
									"duName" }, WebServiceUtil.PART_DUTY_URL);

					mHandler.sendEmptyMessage(1);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}).start();
	}

	private void initComplement() {
		ActionBar bar = getActionBar();
		bar.setDisplayHomeAsUpEnabled(true);
		dutyList = (XListView) findViewById(R.id.duty_list);
		dutyList.setPullLoadEnable(false);
		dutyList.setPullRefreshEnable(false);
		data = new ArrayList<HashMap<String, Object>>();
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
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
