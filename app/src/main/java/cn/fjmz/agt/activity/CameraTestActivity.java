/*
 * Basic no frills app which integrates the ZBar barcode scanner with
 * the camera.
 * 
 * Created by lisah0 on 2012-02-24
 */
package cn.fjmz.agt.activity;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.hardware.Camera;
import android.hardware.Camera.AutoFocusCallback;
import android.hardware.Camera.Parameters;
import android.hardware.Camera.PreviewCallback;
import android.hardware.Camera.Size;
import android.os.Bundle;
import android.os.Handler;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.tbruyelle.rxpermissions.RxPermissions;

import cn.fjmz.agt.R;

//import net.sourceforge.zbar.Config;
//import net.sourceforge.zbar.Image;
//import net.sourceforge.zbar.ImageScanner;
//import net.sourceforge.zbar.Symbol;
//import net.sourceforge.zbar.SymbolSet;

/* Import ZBar Class files */

public class CameraTestActivity extends Activity {
    private Camera mCamera;
    private Handler autoFocusHandler;

//    ImageScanner scanner;

    private boolean previewing = true;

    static {
        System.loadLibrary("iconv");
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.main);
        getActionBar().setTitle("二维码扫描");
        getActionBar().setDisplayHomeAsUpEnabled(true);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        autoFocusHandler = new Handler();
        if (!RxPermissions.getInstance(this)
                .isGranted(android.Manifest.permission.CAMERA)) {
//            RxPermissions.getInstance(this)
//                    .request(android.Manifest.permission.CAMERA)
//                    .subscribe(new Action1<Boolean>() {
//                        @Override
//                        public void call(Boolean aBoolean) {
//                            if (aBoolean) {
//                                init();
//                            } else {
//                                Toast.makeText(CameraTestActivity.this, "无相机权限", Toast.LENGTH_SHORT).show();
//                                finish();
//                            }
//                        }
//                    });
            return;
        }
        init();
    }

    private void init() {
        try {
            mCamera = getCameraInstance();
        } catch (Exception e) {
        }

        if (mCamera == null) {
            Toast.makeText(this, "无相机权限", Toast.LENGTH_SHORT).show();
            finish();
        }
        /* Instance barcode scanner */
//        scanner = new ImageScanner();
//        scanner.setConfig(0, Config.X_DENSITY, 3);
//        scanner.setConfig(0, Config.Y_DENSITY, 3);

        CameraPreview preview1 = new CameraPreview(this, mCamera, previewCb, autoFocusCB);
        FrameLayout preview = (FrameLayout) findViewById(R.id.cameraPreview);
        preview.addView(preview1);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (mCamera != null) {
            mCamera.setPreviewCallback(previewCb);
            /*mCamera.autoFocus(autoFocusCB);*/
            mCamera.startPreview();
            previewing = true;

        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return true;
    }

    public void onPause() {
        super.onPause();
        releaseCamera();
    }

    /**
     * A safe way to get an instance of the Camera object.
     */
    public static Camera getCameraInstance() throws Exception {
        Camera c = null;
        try {
            c = Camera.open();
        } catch (Exception e) {
            throw new Exception();
        }
        return c;
    }

    private void releaseCamera() {
        if (mCamera != null) {
            previewing = false;
            mCamera.setPreviewCallback(null);
            mCamera.release();
            mCamera = null;
        }
    }

    private Runnable doAutoFocus = new Runnable() {
        public void run() {
            if (previewing)
                mCamera.autoFocus(autoFocusCB);
        }
    };

    PreviewCallback previewCb = new PreviewCallback() {
        public void onPreviewFrame(byte[] data, Camera camera) {
            Parameters parameters = camera.getParameters();
            Size size = parameters.getPreviewSize();

//            Image barcode = new Image(size.width, size.height, "Y800");
//            barcode.setData(data);
//
//            int result = scanner.scanImage(barcode);
//
//            if (result != 0) {
//                previewing = false;
//                mCamera.setPreviewCallback(null);
//                mCamera.stopPreview();
//
//                SymbolSet syms = scanner.getResults();
//                String data1 = null;
//                for (Symbol sym : syms) {
//                    data1 = sym.getData();
//                }
//                Intent resultIntent = new Intent();
//                Bundle bundle = new Bundle();
//                bundle.putString("result", data1);
//                resultIntent.putExtras(bundle);
//                setResult(RESULT_OK, resultIntent);
//                finish();
//            }
        }
    };

    // Mimic continuous auto-focusing
    AutoFocusCallback autoFocusCB = new AutoFocusCallback() {
        public void onAutoFocus(boolean success, Camera camera) {
            autoFocusHandler.postDelayed(doAutoFocus, 1000);
        }
    };
}
