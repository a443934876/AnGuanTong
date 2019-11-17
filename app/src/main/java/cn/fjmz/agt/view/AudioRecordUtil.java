package cn.fjmz.agt.view;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import cn.fjmz.agt.R;
import cn.fjmz.agt.util.ArcProgress;

import java.util.List;

import pub.devrel.easypermissions.AfterPermissionGranted;
import pub.devrel.easypermissions.AppSettingsDialog;
import pub.devrel.easypermissions.EasyPermissions;


/**
 * 录音
 *
 * @author Administrator
 */
public class AudioRecordUtil extends Activity implements EasyPermissions.PermissionCallbacks {
    public static final String TAG_RECORD = "record";
    public static final String TAG_PLAY = "play";
    public static final String TAG_PAUSE = "pause";
    public static final String TAG_FINISH = "finish";
    private Button mAudioStartBtn;
    private Button mAudioStopBtn;
    private MediaRecorder mMediaRecorder;// MediaRecorder对象
    private String audioPath;
    private ArcProgress mArcProgress;
    private MediaPlayer mMediaPlayer;
 //   private Subscription mSubscription;
    private int mTime;
    public static final int num = 123;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mediarecorderdemo1);
        getActionBar().setTitle("录音");
        getActionBar().setDisplayHomeAsUpEnabled(true);
        mAudioStartBtn = (Button) findViewById(R.id.mediarecorder1_AudioStartBtn);
        mAudioStopBtn = (Button) findViewById(R.id.mediarecorder1_AudioStopBtn);
        mArcProgress = (ArcProgress) findViewById(R.id.audio_progress_time);
        audioPath = getIntent().getStringExtra("mAudioPath");

		/* 按钮状态 */
        mAudioStartBtn.setEnabled(true);
        mAudioStopBtn.setEnabled(false);
        checkPerm();
        addLinster();

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

    @AfterPermissionGranted(num)
    private void checkPerm() {
        String[] params = {
                Manifest.permission.RECORD_AUDIO
        };
        if (EasyPermissions.hasPermissions(this, params)) {

        } else {
            EasyPermissions.requestPermissions(this, "需要权限", num, params);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
    }

    @Override
    public void onPermissionsGranted(int requestCode, List<String> perms) {
        //如果checkPerm方法，没有注解AfterPermissionGranted，也可以在这里调用该方法。

    }

    @Override
    public void onPermissionsDenied(int requestCode, List<String> perms) {
        if (EasyPermissions.somePermissionPermanentlyDenied(this, perms)) {
            //这里需要重新设置Rationale和title，否则默认是英文格式
            new AppSettingsDialog.Builder(this)
                    .setRationale("没有该权限，此应用程序可能无法正常工作。打开应用设置界面以修改应用权限")
                    .setTitle("必需权限")
                    .build()
                    .show();
        }
    }

    /**
     * 添加监听
     */
    private void addLinster() {
        /* 开始按钮事件监听 */
        mAudioStartBtn.setOnClickListener(new Button.OnClickListener() {

            @Override
            public void onClick(View v) {


                if (v.getTag() == null) {
                    startRecord();
                } else if (v.getTag().toString().equals(TAG_RECORD)) {
                    play();
                } else if (v.getTag().toString().equals(TAG_PLAY)) {
                    pause();
                } else if (v.getTag().toString().equals(TAG_PAUSE)) {
                    play();
                }

            }

        });

		/* 停止按钮事件监听 */
        mAudioStopBtn.setOnClickListener(new Button.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (v.getTag() == null) {
                    stop();
                } else if (v.getTag().equals(TAG_FINISH)) {
                    Intent intent = new Intent();
                    intent.putExtra("result", audioPath);
                    AudioRecordUtil.this.setResult(Activity.RESULT_OK, intent);
                    finish();
                }

            }

        });
    }

    private void startRecord() {
        mAudioStartBtn.setText("播放");
        mAudioStartBtn.setTag(TAG_RECORD);
        mMediaRecorder = new MediaRecorder();
        mMediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        mMediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
        mMediaRecorder.setOutputFile(audioPath);
        mMediaRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
        try {
            mMediaRecorder.prepare();
            mMediaRecorder.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
        /* 按钮状态 */
        mAudioStartBtn.setEnabled(false);
        mAudioStopBtn.setEnabled(true);
        startTime(mArcProgress.getMax(), false);
    }

    private void stop() {
        mAudioStopBtn.setText("录音完成");
        mAudioStopBtn.setTag(TAG_FINISH);
        mMediaRecorder.stop();
        mMediaRecorder.release();
        mMediaRecorder = null;
        mAudioStartBtn.setEnabled(true);
        mAudioStartBtn.setText("播放");
        stopTime();

    }

    private void play() {
        mAudioStartBtn.setTag(TAG_PLAY);
        mAudioStartBtn.setText("停止");
        mMediaPlayer = new MediaPlayer();
        mMediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer pMediaPlayer) {
                mMediaPlayer.release();
                mAudioStartBtn.setTag(TAG_PAUSE);
                mAudioStartBtn.setText("播放");
                stopTime();
            }
        });
        try {
            mMediaPlayer.setDataSource(audioPath);
            mMediaPlayer.prepare();
            mMediaPlayer.start();
            mArcProgress.setMax(mTime);
            startTime(mTime, true);
        } catch (Exception pE) {
            pE.printStackTrace();
        }
    }

    private void pause() {
        mAudioStartBtn.setTag(TAG_PAUSE);
        mAudioStartBtn.setText("播放");
        mMediaPlayer.stop();
        mMediaPlayer.release();
        stopTime();
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mMediaRecorder != null) {
            mMediaRecorder.release();
            mMediaRecorder = null;
        }
        if (mMediaPlayer != null) {
            mMediaPlayer.release();
            mMediaPlayer = null;
        }
    }

    private void stopTime() {
     //   mSubscription.unsubscribe();
        if (mTime == 0) {
            mTime = mArcProgress.getMax() - mArcProgress.getProgress();
        }
    }

    private void startTime(int max, boolean isPlay) {
//        mSubscription = RxCountDown.countdown(max, isPlay)
//                .doOnSubscribe(new Action0() {
//                    @Override
//                    public void call() {
//                    }
//                })
//                .subscribe(new Subscriber<Integer>() {
//                    @Override
//                    public void onCompleted() {
//                        stop();
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//
//                    }
//
//                    @Override
//                    public void onNext(Integer integer) {
//                        mArcProgress.setProgress(integer);
//                    }
//                });
    }
}
