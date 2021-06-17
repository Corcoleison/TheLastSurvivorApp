package com.example.pablo.thelastsurvivor;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraManager;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class Flashlight_tool extends AppCompatActivity {

    private CameraManager mCameraManager;
    private String mCameraId;
    private ImageView flashligth_background;
    private Boolean flashlight_on=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.flashlight_tool);

        Initialize();

        boolean isFlashAvailable = getApplicationContext().getPackageManager()
                .hasSystemFeature(PackageManager.FEATURE_CAMERA_FLASH);

        if (!isFlashAvailable) {
            showNoFlashError();
        }

        mCameraManager = (CameraManager) getSystemService(Context.CAMERA_SERVICE);
        try {
            mCameraId = mCameraManager.getCameraIdList()[0];
        } catch (CameraAccessException e) {
            e.printStackTrace();
        }

        flashligth_background.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(flashlight_on==false) {
                    flashligth_background.setBackgroundResource(R.drawable.flashlight_on_prueba_todoblanco);
                    flashlight_on=true;
                    switchFlashLight(true);
                }else{
                    flashligth_background.setBackgroundResource(R.drawable.flashlight_background);
                    flashlight_on=false;
                    switchFlashLight(false);
                }
            }
        });

    }

    public void Initialize(){
        flashligth_background = (ImageView)findViewById(R.id.flahslight_background_imageView);
    }

    public void showNoFlashError() {
        AlertDialog alert = new AlertDialog.Builder(this)
                .create();
        alert.setTitle("Oops!");
        alert.setMessage("Flash not available in this device...");
        alert.setButton(DialogInterface.BUTTON_POSITIVE, "OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });
        alert.show();
    }

    public void switchFlashLight(boolean status) {
        try {
            mCameraManager.setTorchMode(mCameraId, status);
        } catch (CameraAccessException e) {
            e.printStackTrace();
        }
    }
}
