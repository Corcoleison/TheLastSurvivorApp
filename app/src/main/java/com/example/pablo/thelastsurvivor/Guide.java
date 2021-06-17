package com.example.pablo.thelastsurvivor;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.content.Intent;
import android.os.Vibrator;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

public class Guide extends AppCompatActivity {
    private Button waterButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if(AppCompatDelegate.getDefaultNightMode()==AppCompatDelegate.MODE_NIGHT_YES) {
            setTheme(R.style.DarkTheme);
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.guide);
        final Vibrator vibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);



        waterButton = (Button) findViewById(R.id.waterButton);
        waterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vibrator.vibrate(70);
                Animator scale = ObjectAnimator.ofPropertyValuesHolder(v,
                        PropertyValuesHolder.ofFloat(View.SCALE_X, 1, 1.5f, 1),
                        PropertyValuesHolder.ofFloat(View.SCALE_Y, 1, 1.5f, 1)
                );
                scale.setDuration(1000);
                scale.start();
                openWater();
            }
        });
    }

    public void restartApp(){
        Intent i = new Intent(getApplicationContext(), Guide.class);
        startActivity(i);
        finish();
    }

    public void openWater() {
        Intent intent = new Intent(this, Water.class);
        startActivity(intent);
    }
}
