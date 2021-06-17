package com.example.pablo.thelastsurvivor;

import android.content.Intent;
import android.graphics.ColorMatrixColorFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

public class Tools extends AppCompatActivity {
    public ImageView barometer_image;
    public ImageView measures_image;
    public ImageView nightvision_image;
    public ImageView compass_image;
    public ImageView flashlight_image;
    public ImageView lightsensor_image;
    private static final float[] NEGATIVE = {
            -1.0f,     0,     0,    0, 255, // red
            0, -1.0f,     0,    0, 255, // green
            0,     0, -1.0f,    0, 255, // blue
            0,     0,     0, 1.0f,   0  // alpha
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if (AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_YES) {
            setTheme(R.style.DarkTheme);
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tools);
        Initialize();


        Bundle parametros = this.getIntent().getExtras();
        if (parametros != null) {
            boolean invertColorsEnabled = parametros.getBoolean("invertColorsEnabled");
            if (invertColorsEnabled) {
                invertir();
            }
        }

        flashlight_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), Flashlight_tool.class);
                startActivity(i);
            }
        });

        nightvision_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), NightVision.class);
                startActivity(i);
            }
        });

        barometer_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), Barometer.class);
                startActivity(i);
            }
        });

        measures_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), Ruler.class);
                startActivity(i);
            }
        });

        lightsensor_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), LightSensor.class);
                startActivity(i);
            }
        });

        compass_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), Compass.class);
                startActivity(i);
            }
        });
    }

    public void invertir() {
        lightsensor_image.setColorFilter(new ColorMatrixColorFilter(NEGATIVE));
        barometer_image.setColorFilter(new ColorMatrixColorFilter(NEGATIVE));
        compass_image.setColorFilter(new ColorMatrixColorFilter(NEGATIVE));
        measures_image.setColorFilter(new ColorMatrixColorFilter(NEGATIVE));
        nightvision_image.setColorFilter(new ColorMatrixColorFilter(NEGATIVE));
        flashlight_image.setColorFilter(new ColorMatrixColorFilter(NEGATIVE));
    }

    public void Initialize(){
        lightsensor_image = (ImageView)findViewById(R.id.lightsensor_imageView);
        barometer_image = (ImageView)findViewById(R.id.barometer_imageView);
        compass_image = (ImageView)findViewById(R.id.conversor_imageView);
        measures_image = (ImageView)findViewById(R.id.measures_imageView);
        nightvision_image = (ImageView)findViewById(R.id.nightvision_imageView);
        flashlight_image = (ImageView)findViewById(R.id.flashlight_imageView);
    }
}
