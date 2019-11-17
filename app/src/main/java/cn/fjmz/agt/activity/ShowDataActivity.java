package cn.fjmz.agt.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;

import cn.fjmz.agt.R;
import cn.fjmz.agt.adapter.HiddenIllnessAdapter;
import cn.fjmz.agt.adapter.LessonAdapter;
import cn.fjmz.agt.adapter.MissionAdapter;
import cn.fjmz.agt.adapter.SafetySetAdapter;
import cn.fjmz.agt.mode.HiddenIllnessInfo;
import cn.fjmz.agt.mode.LessonInfo;
import cn.fjmz.agt.mode.MissionInfo;
import cn.fjmz.agt.mode.SafetySetInfo;

import java.util.ArrayList;

public class ShowDataActivity extends Activity {
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_data);
        listView = (ListView) findViewById(R.id.lv);
        show();
    }

    private void show() {
        Intent intent = getIntent();
        String type = intent.getStringExtra("type");
        Bundle bundle = intent.getBundleExtra("bundle");
        switch (type) {
            case "missionInfoList":
                ArrayList<MissionInfo> missionInfoList = (ArrayList<MissionInfo>) bundle.getSerializable("missionInfoList");
                MissionAdapter missionAdapter = new MissionAdapter(this, missionInfoList);
                listView.setAdapter(missionAdapter);
                break;
            case "hiddenIllnessInfoList":
                ArrayList<HiddenIllnessInfo> hiddenIllnessInfoList = (ArrayList<HiddenIllnessInfo>) bundle.getSerializable("hiddenIllnessInfoList");
                HiddenIllnessAdapter hiddenIllnessAdapter = new HiddenIllnessAdapter(this, hiddenIllnessInfoList);
                listView.setAdapter(hiddenIllnessAdapter);
                break;
            case "lessonInfoList":
                ArrayList<LessonInfo> lessonInfoList = (ArrayList<LessonInfo>) bundle.getSerializable("lessonInfoList");
                LessonAdapter lessonAdapter = new LessonAdapter(this, lessonInfoList);
                listView.setAdapter(lessonAdapter);
                break;
            case "safetySetInfoList":
                ArrayList<SafetySetInfo> safetySetInfoList = (ArrayList<SafetySetInfo>) bundle.getSerializable("safetySetInfoList");
                SafetySetAdapter safetySetAdapter = new SafetySetAdapter(this, safetySetInfoList);
                listView.setAdapter(safetySetAdapter);
                break;
        }


    }


}
