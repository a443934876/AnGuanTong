package cn.fjmz.agt.activity;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.Result;
import com.google.zxing.client.result.ParsedResult;
import com.jaeger.library.StatusBarUtil;
import com.mylhyl.zxing.scanner.OnScannerCompletionListener;
import com.mylhyl.zxing.scanner.ScannerOptions;
import com.mylhyl.zxing.scanner.ScannerView;
import com.mylhyl.zxing.scanner.decode.QRDecode;

import java.io.IOException;

import butterknife.BindView;
import cn.fjmz.agt.R;
import cn.fjmz.agt.base.BaseActivity;
import cn.fjmz.agt.base.BaseModel;
import cn.fjmz.agt.base.BasePresenter;
import pub.devrel.easypermissions.AfterPermissionGranted;
import pub.devrel.easypermissions.EasyPermissions;

public class ScanActivity extends BaseActivity implements OnScannerCompletionListener {

    @BindView(R.id.scanner_view)
    ScannerView mScannerView;
    @BindView(R.id.iv_lamp)
    ImageView mIvLamp;
    @BindView(R.id.iv_picture)
    ImageView mIvPicture;
    private boolean lampState = false;
    private static final int CAMERA_CODE = 129;

    public static void launch(Context context) {
        Intent intent = new Intent(context, ScanActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        context.startActivity(intent);
    }

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_scan;
    }

    @Override
    protected void initData() {
        StatusBarUtil.setDarkMode(this);
        getPersimmons();
        initListener();
    }

    private void initListener() {
        mScannerView.setOnScannerCompletionListener(this);
        mIvLamp.setOnClickListener(view -> {
            if (lampState) {
                lampState = false;
                mScannerView.toggleLight(true);
            } else {
                lampState = true;
                mScannerView.toggleLight(false);
            }
        });
        mIvPicture.setOnClickListener(view -> {
            Intent intent = new Intent();
            intent.setType("image/*");
            intent.setAction(Intent.ACTION_GET_CONTENT);
            startActivityForResult(intent, 5002);
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        mScannerView.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mScannerView.onPause();
    }

    @AfterPermissionGranted(CAMERA_CODE)
    private void getPersimmons() {
        String[] mPerms = {Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.CAMERA};
        if (EasyPermissions.hasPermissions(this, mPerms)) {
            initScannerView();
        } else {
            EasyPermissions.requestPermissions(this, "需要相机和存储权限", CAMERA_CODE, mPerms);
        }
    }

    private void initScannerView() {
        ScannerOptions.Builder builder = new ScannerOptions.Builder();
        builder.setFrameStrokeColor(Color.RED)
                .setFrameStrokeWidth(1.5f)
                .setScanMode(BarcodeFormat.QR_CODE)
                .setTipText("取景对准二维码，即可自动扫描")
                .setTipTextSize(14);
        mScannerView.setScannerOptions(builder.build());
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            // 照片的原始资源地址
            if (data != null) {
                Uri uri = data.getData();
                if (uri != null) {
                    try {
                        Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);
                        QRDecode.decodeQR(bitmap, this);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    @Override
    public void onScannerCompletion(Result rawResult, ParsedResult parsedResult, Bitmap barcode) {
        if (rawResult != null) {
            String result = rawResult.toString();
            if (result.contains("-")) {
                String[] results = result.split("-");
                if (results.length > 1) {
                    if (results[0].equals("aqss") || results[0].equals("sssb")) {
                        EquipmentInspectionDetailActivity.launch(this, results[1]);
                    } else {
                        toScan();
                    }
                } else {
                    toScan();
                }
            } else {
                toScan();
            }
        } else {
            toScan();
        }
    }

    private void toScan() {
        showToast("此设备无法识别");
        mScannerView.restartPreviewAfterDelay(3);
    }

    @Override
    public void onErrorCode(BaseModel model) {

    }

}
