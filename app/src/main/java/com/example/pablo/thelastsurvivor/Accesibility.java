package com.example.pablo.thelastsurvivor;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.ColorMatrixColorFilter;
import android.media.MediaPlayer;
import android.os.Vibrator;
import android.os.Bundle;

import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Switch;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

public class Accesibility extends AppCompatActivity {
    private RadioGroup radioGroup;
    private RadioButton radioButton;
    private RadioButton r_trita;
    private RadioButton r_deutera;
    private RadioButton r_prota;
    private Switch colorCorrectionSwitch;
    private ImageView imagen_prueba;
    private Switch dianocheButton;
    private Switch invertColorsButton;
    private com.google.android.material.floatingactionbutton.FloatingActionButton homeButton;
    public ImageView spaceFindeButton;
    public ImageView mapButton;
    public ImageView barraSuperior;
    public static Boolean invertColorsEnabled = false;
    public static Boolean deutera = false;
    public static Boolean prota = false;
    public static Boolean trita = false;
    private static final float[] NEGATIVE = {
            -1.0f,     0,     0,    0, 255, // red
            0, -1.0f,     0,    0, 255, // green
            0,     0, -1.0f,    0, 255, // blue
            0,     0,     0, 1.0f,   0  // alpha
    };
    private static final float[] NORMAL = {
            1.0f,     0,     0,    0, 0, // red
            0, 1.0f,     0,    0, 0, // green
            0,     0, 1.0f,    0, 0, // blue
            0,     0,     0, 1.0f,   0  // alpha
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if(AppCompatDelegate.getDefaultNightMode()==AppCompatDelegate.MODE_NIGHT_YES){
            setTheme(R.style.DarkTheme);
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.accesibility);

        final Vibrator vibrator = (Vibrator)getSystemService(VIBRATOR_SERVICE);
        final MediaPlayer button_sound = MediaPlayer.create(this, R.raw.sound_button);

        barraSuperior = (ImageView)findViewById(R.id.barraSuperiorImageView) ;
        imagen_prueba = (ImageView)findViewById(R.id.imagen_prueba);

        dianocheButton = (Switch) findViewById(R.id.dianocheButton);
        if(AppCompatDelegate.getDefaultNightMode()==AppCompatDelegate.MODE_NIGHT_YES){
            dianocheButton.setChecked(true);
        }
        dianocheButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                button_sound.start();
                vibrator.vibrate(100);

                if(isChecked){
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                    restartApp();
                }
                else{
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                    restartApp();
                }
            }
        });

        homeButton = findViewById(R.id.homeButton);
        homeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                button_sound.start();
                Intent i = new Intent(getApplicationContext(), Menu.class);
                i.putExtra("invertColorsEnabled", invertColorsEnabled);
                i.putExtra("deutera", deutera);
                i.putExtra("prota", prota);
                i.putExtra("trita",trita);
                vibrator.vibrate(70);

                startActivity(i);
            }
        });



        invertColorsButton = (Switch) findViewById(R.id.invertcolorButton);
        if(invertColorsEnabled == true){
            invertColorsButton.setChecked(true);
        }
        invertColorsButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                button_sound.start();
                vibrator.vibrate(100);

                if (isChecked){
                    invertColorsEnabled = true;
                    imagen_prueba.setColorFilter(new ColorMatrixColorFilter(NEGATIVE));
                }else{
                    invertColorsEnabled=false;
                    imagen_prueba.setColorFilter(new ColorMatrixColorFilter(NORMAL));
                    //restartApp();
                }
            }
        });

        radioGroup = findViewById(R.id.radioGroup);



        colorCorrectionSwitch = (Switch)findViewById(R.id.colorCorrectionSwitch);
        if(trita || prota || deutera){
            colorCorrectionSwitch.setChecked(true);
        }
        colorCorrectionSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                button_sound.start();
                vibrator.vibrate(100);

                if(isChecked){
                    int radioId = radioGroup.getCheckedRadioButtonId();
                    radioButton = findViewById(radioId);
                    switch (radioId) {
                        case R.id.deuteronomalia_radio:
                            deutera =true;
                            imagen_prueba.setImageResource(R.drawable.deutera);
                            barraSuperior.setBackgroundColor(Color.parseColor("#b18a48"));
                            break;
                        case R.id.protanomalia_radio:
                            prota=true;
                            imagen_prueba.setImageResource(R.drawable.prota);
                            barraSuperior.setBackgroundColor(Color.parseColor("#a2913c"));
                            break;

                        case R.id.tritanomalia_radio:
                            trita=true;
                            imagen_prueba.setImageResource(R.drawable.trita);
                            barraSuperior.setBackgroundColor(Color.parseColor("#649ba9"));
                            break;
                    }
                }else{
                    prota=false;
                    deutera=false;
                    trita=false;
                    imagen_prueba.setImageResource(R.drawable.prueba_color_normal);
                    barraSuperior.setBackgroundColor(Color.parseColor("#27712a"));
                }
            }
        });

        if(invertColorsEnabled){ //Para mantener la imagen invertida al volver a la actividad
            imagen_prueba.setColorFilter(new ColorMatrixColorFilter(NEGATIVE));
        }

        r_trita=(RadioButton)findViewById(R.id.tritanomalia_radio);
        r_deutera=(RadioButton)findViewById(R.id.deuteronomalia_radio);
        r_prota=(RadioButton)findViewById(R.id.protanomalia_radio);
        if(trita){
            r_trita.setChecked(true);
            r_deutera.setChecked(false);
            r_prota.setChecked(false);
            imagen_prueba.setImageResource(R.drawable.trita);
            barraSuperior.setBackgroundColor(Color.parseColor("#649ba9"));
        }else if(deutera){
            r_trita.setChecked(false);
            r_deutera.setChecked(true);
            r_prota.setChecked(false);
            imagen_prueba.setImageResource(R.drawable.deutera);
            barraSuperior.setBackgroundColor(Color.parseColor("#b18a48"));
        }else if(prota){
            r_trita.setChecked(false);
            r_deutera.setChecked(false);
            r_prota.setChecked(true);
            imagen_prueba.setImageResource(R.drawable.prota);
            barraSuperior.setBackgroundColor(Color.parseColor("#a2913c"));
        }
    }
    public void restartApp(){
        Intent i = new Intent(getApplicationContext(), Accesibility.class);
        startActivity(i);
        finish();
    }

}
