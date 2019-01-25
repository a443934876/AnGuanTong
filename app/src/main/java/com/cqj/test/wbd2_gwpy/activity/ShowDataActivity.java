package com.cqj.test.wbd2_gwpy.activity;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import com.cqj.test.wbd2_gwpy.R;
import com.cqj.test.wbd2_gwpy.adapter.HiddenIllnessAdapter;
import com.cqj.test.wbd2_gwpy.adapter.LessonAdapter;
import com.cqj.test.wbd2_gwpy.adapter.MissionAdapter;
import com.cqj.test.wbd2_gwpy.adapter.SafetySetAdapter;
import com.cqj.test.wbd2_gwpy.mode.HiddenIllnessInfo;
import com.cqj.test.wbd2_gwpy.mode.LessonInfo;
import com.cqj.test.wbd2_gwpy.mode.MissionInfo;
import com.cqj.test.wbd2_gwpy.mode.SafetySetInfo;
import com.cqj.test.wbd2_gwpy.myinterface.HomeData;
import com.cqj.test.wbd2_gwpy.presenter.compl.HomeDataImpl;

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
