package com.example.pablo.thelastsurvivor;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.content.Intent;
import android.graphics.ColorMatrixColorFilter;
import android.media.MediaPlayer;
import android.os.Vibrator;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

public class Astronomy extends AppCompatActivity {
    private ImageView spaceFinder;
    private ImageView constellationView;
    private ImageView day_nightImageView;
    private static final float[] NEGATIVE = {
            -1.0f, 0, 0, 0, 255, // red
            0, -1.0f, 0, 0, 255, // green
            0, 0, -1.0f, 0, 255, // blue
            0, 0, 0, 1.0f, 0  // alpha
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if (AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_YES) {
            setTheme(R.style.DarkTheme);
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.astronomy);
        final Vibrator vibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);

        final MediaPlayer button_sound = MediaPlayer.create(this, R.raw.sound_button);

        spaceFinder = (ImageView) findViewById(R.id.spaceFinderView);
        spaceFinder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                button_sound.start();
                Intent i = new Intent(getApplicationContext(), SpaceFinder.class);
                vibrator.vibrate(70);

                Animator scale = ObjectAnimator.ofPropertyValuesHolder(v,
                        PropertyValuesHolder.ofFloat(View.SCALE_X, 1, 1.5f, 1),
                        PropertyValuesHolder.ofFloat(View.SCALE_Y, 1, 1.5f, 1)
                );
                scale.setDuration(1000);
                scale.start();
                startActivity(i);

            }
        });
        day_nightImageView = (ImageView)findViewById(R.id.day_night_ImageView);
        day_nightImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), DayNight.class);
                startActivity(i);
            }
        });

        Bundle parametros = this.getIntent().getExtras();
        if (parametros != null) {
            boolean invertColorsEnabled = parametros.getBoolean("invertColorsEnabled");
            if (invertColorsEnabled) {
                invertir();
            }
        }


    }

    public void invertir() {
        spaceFinder.setColorFilter(new ColorMatrixColorFilter(NEGATIVE));
        constellationView.setColorFilter(new ColorMatrixColorFilter(NEGATIVE));
        day_nightImageView.setColorFilter(new ColorMatrixColorFilter(NEGATIVE));
    }
}