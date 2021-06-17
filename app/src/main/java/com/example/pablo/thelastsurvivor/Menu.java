package com.example.pablo.thelastsurvivor;

import android.Manifest;
import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.ColorMatrixColorFilter;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Vibrator;
import android.speech.RecognizerIntent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.core.app.ActivityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

public class Menu extends AppCompatActivity {
    private com.google.android.material.floatingactionbutton.FloatingActionButton mapButton;
    private com.google.android.material.floatingactionbutton.FloatingActionButton guideButton;
    private com.google.android.material.floatingactionbutton.FloatingActionButton toolsButton;
    private com.google.android.material.floatingactionbutton.FloatingActionButton astronomyButton;
    private com.google.android.material.floatingactionbutton.FloatingActionButton sosButton;
    private com.google.android.material.floatingactionbutton.FloatingActionButton barraButton;
    private com.google.android.material.floatingactionbutton.FloatingActionButton configuracionButton;
    private ImageView barraSuperiorImageView;
    private ImageView medioFondo;
    private int  REQUEST_SPEECH = 0;
    private Button buttonMicro;
    private Switch dianocheButton;
    DrawerLayout barra_layout;
    private static final float[] NEGATIVE = {
            -1.0f,     0,     0,    0, 255, // red
            0, -1.0f,     0,    0, 255, // green
            0,     0, -1.0f,    0, 255, // blue
            0,     0,     0, 1.0f,   0  // alpha
    };
    public static Boolean invertColorsB = false;
    public static Boolean deuteraB = false;
    public static Boolean tritaB = false;
    public static Boolean protaB = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if (AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_YES) {
            setTheme(R.style.DarkTheme);
        }

        /*Bundle parametros = this.getIntent().getExtras();
        if(parametros !=null){
           boolean  invertColorsEnabled = parametros.getBoolean("invertColorsEnabled");
            invertir();
        }*/

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        final MediaPlayer screamplay = MediaPlayer.create(this, R.raw.grito_pablo);

        final MediaPlayer button_sound = MediaPlayer.create(this, R.raw.sound_button);
        final Vibrator vibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);
        final MediaPlayer sos_sound = MediaPlayer.create(this, R.raw.emergency);


        mapButton = (com.google.android.material.floatingactionbutton.FloatingActionButton) findViewById(R.id.mapButton);
        mapButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                button_sound.start();
                vibrator.vibrate(70);

                Animator scale = ObjectAnimator.ofPropertyValuesHolder(v,
                        PropertyValuesHolder.ofFloat(View.SCALE_X, 1, 1.5f, 1),
                        PropertyValuesHolder.ofFloat(View.SCALE_Y, 1, 1.5f, 1)
                );
                scale.setDuration(1000);
                scale.start();
                Intent i = new Intent(getApplicationContext(), MapsActivity.class);
                startActivity(i);
                //openMap();
            }
        });

        buttonMicro = (Button) findViewById(R.id.buttonMicro);
        buttonMicro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(v, "Commands: Maps, Astronomy, Guide, Accessibility, Water and Space Finder", Snackbar.LENGTH_LONG).setAction("Action", null).show();
                displaySpeechRecognizer();
            }
        });

        guideButton = (com.google.android.material.floatingactionbutton.FloatingActionButton) findViewById(R.id.guideButton);
        guideButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                button_sound.start();
                vibrator.vibrate(70);

                Animator scale = ObjectAnimator.ofPropertyValuesHolder(v,
                        PropertyValuesHolder.ofFloat(View.SCALE_X, 1, 1.5f, 1),
                        PropertyValuesHolder.ofFloat(View.SCALE_Y, 1, 1.5f, 1)
                );
                scale.setDuration(1000);
                scale.start();
                openGuide();
            }
        });

        toolsButton = (com.google.android.material.floatingactionbutton.FloatingActionButton) findViewById(R.id.toolsButton);
        astronomyButton = (com.google.android.material.floatingactionbutton.FloatingActionButton) findViewById(R.id.astronomyButton);
        barraSuperiorImageView = (ImageView) findViewById(R.id.barraSuperiorImageView);
        sosButton = (com.google.android.material.floatingactionbutton.FloatingActionButton) findViewById(R.id.sosFloatinActionButton);
        sosButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vibrator.vibrate(100);
                String number ="610089569";
                Intent intent = new Intent(Intent.ACTION_CALL);
                intent.setData(Uri.parse("tel: "+ number));

                if(ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.CALL_PHONE)!= PackageManager.PERMISSION_GRANTED){
                    return;
                }
                startActivity(intent);
                //emergencyCall_onClick();
            }
        });
        barraButton = (com.google.android.material.floatingactionbutton.FloatingActionButton) findViewById(R.id.barraButton);

        configuracionButton = (com.google.android.material.floatingactionbutton.FloatingActionButton) findViewById(R.id.configuracionFloatingActionButton);
        configuracionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                button_sound.start();
                Intent i = new Intent(getApplicationContext(), Accesibility.class);
                vibrator.vibrate(70);

                startActivity(i);
            }
        });
        Bundle parametros = this.getIntent().getExtras();
        if (parametros != null) {
            boolean invertColorsEnabled = parametros.getBoolean("invertColorsEnabled");
            invertColorsB = invertColorsEnabled;
            if (invertColorsEnabled) {
                invertir();
            }

        }
        astronomyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                button_sound.start();
                Intent i = new Intent(getApplicationContext(), Astronomy.class);
                vibrator.vibrate(70);

                Animator scale = ObjectAnimator.ofPropertyValuesHolder(v,
                        PropertyValuesHolder.ofFloat(View.SCALE_X, 1, 1.5f, 1),
                        PropertyValuesHolder.ofFloat(View.SCALE_Y, 1, 1.5f, 1)
                );
                scale.setDuration(1000);
                scale.start();
                i.putExtra("invertColorsEnabled", invertColorsB);
                startActivity(i);
            }
        });

        Bundle parametros2 = this.getIntent().getExtras();
        if (parametros2 != null) {
            boolean deutera = parametros2.getBoolean("deutera");
            boolean prota = parametros2.getBoolean("prota");
            boolean trita = parametros2.getBoolean("trita");
            deuteraB = deutera;
            tritaB = trita;
            protaB= prota;
            if (deutera) {
                deutera();
            }
            if(trita){
                trita();
            }
            if(prota){
                prota();
            }
        }

        toolsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                button_sound.start();
                Intent i = new Intent(getApplicationContext(), Tools.class);
                vibrator.vibrate(70);

                Animator scale = ObjectAnimator.ofPropertyValuesHolder(v,
                        PropertyValuesHolder.ofFloat(View.SCALE_X, 1, 1.5f, 1),
                        PropertyValuesHolder.ofFloat(View.SCALE_Y, 1, 1.5f, 1)
                );
                scale.setDuration(1000);
                scale.start();
                i.putExtra("invertColorsEnabled", invertColorsB);
                startActivity(i);
            }
        });

    }

    public void deutera(){
        mapButton.setImageResource(R.drawable.map_deutera);
        toolsButton.setImageResource(R.drawable.compass_deutera);
        guideButton.setImageResource(R.drawable.spruce_deutera);
        astronomyButton.setImageResource(R.drawable.scope_deutera);
        barraSuperiorImageView.setBackgroundColor(Color.parseColor("#b18a48"));
        sosButton.setImageResource(R.drawable.boton_sos_deutera);
        }
    public void trita(){
        mapButton.setImageResource(R.drawable.map_trita);
        toolsButton.setImageResource(R.drawable.compass_trita);
        guideButton.setImageResource(R.drawable.spruce_trita);
        astronomyButton.setImageResource(R.drawable.scope_trita);
        barraSuperiorImageView.setBackgroundColor(Color.parseColor("#649ba9"));
        sosButton.setImageResource(R.drawable.boton_sos_trita);
    }
    public void prota(){
        mapButton.setImageResource(R.drawable.map_prota);
        toolsButton.setImageResource(R.drawable.compass_prota);
        guideButton.setImageResource(R.drawable.spruce_prota);
        astronomyButton.setImageResource(R.drawable.scope_prota);
        barraSuperiorImageView.setBackgroundColor(Color.parseColor("#a2913c"));
        sosButton.setImageResource(R.drawable.boton_sos_prota);
    }


    public void invertir(){
        /**
         * Color matrix that flips the components (<code>-1.0f * c + 255 = 255 - c</code>)
         * and keeps the alpha intact.
         */

            mapButton.setColorFilter(new ColorMatrixColorFilter(NEGATIVE));
            toolsButton.setColorFilter(new ColorMatrixColorFilter(NEGATIVE));
            guideButton.setColorFilter(new ColorMatrixColorFilter(NEGATIVE));
            astronomyButton.setColorFilter(new ColorMatrixColorFilter(NEGATIVE));
            barraSuperiorImageView.setColorFilter(new ColorMatrixColorFilter(NEGATIVE));
            sosButton.setColorFilter(new ColorMatrixColorFilter(NEGATIVE));
            medioFondo = (ImageView) findViewById(R.id.medioFondo);
            medioFondo.setColorFilter(new ColorMatrixColorFilter(NEGATIVE));
    }

    public void restartApp(){
        Intent i = new Intent(getApplicationContext(), Menu.class);
        startActivity(i);
        finish();
    }

    public void openGuide(){
        Intent intent = new Intent(this, Guide.class);
        startActivity(intent);
    }

    public void openMap(){
        Intent intent = new Intent(this, Map.class);
        startActivity(intent);
    }

    public void displaySpeechRecognizer() {
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT, "Choose Activity");
        startActivityForResult(intent, REQUEST_SPEECH);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_SPEECH) {
            if (resultCode == RESULT_OK) {
                ArrayList<String> matches = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);

                if (matches.size() == 0) {
                    // didn't hear anything
                } else {
                    String mostLikelyThingHeard = matches.get(0);
                    // toUpperCase() used to make string comparison equal
                    if (mostLikelyThingHeard.toUpperCase().equals("MAPS")) {
                        startActivity(new Intent(this, MapsActivity.class));
                    } else if (mostLikelyThingHeard.toUpperCase().equals("GUIDE")) {
                        startActivity(new Intent(this, Guide.class));
                    }else if (mostLikelyThingHeard.toUpperCase().equals("ASTRONOMY")) {
                        startActivity(new Intent(this, Astronomy.class));
                    }else if (mostLikelyThingHeard.toUpperCase().equals("ACCESSIBILITY")) {
                        startActivity(new Intent(this, Accesibility.class));
                    }else if (mostLikelyThingHeard.toUpperCase().equals("WATER")) {
                        startActivity(new Intent(this, Water.class));
                    }else if (mostLikelyThingHeard.toUpperCase().equals("SPACE FINDER")) {
                        startActivity(new Intent(this, SpaceFinder.class));
                    }else if (mostLikelyThingHeard.toUpperCase().equals("DATE")) {
                        startActivity(new Intent(this, DayNight.class));
                    }else if (mostLikelyThingHeard.toUpperCase().equals("TIME")) {
                        startActivity(new Intent(this, DayNight.class));
                    }else if (mostLikelyThingHeard.toUpperCase().equals("FLASHLIGHT")) {
                        startActivity(new Intent(this, Flashlight_tool.class));
                    }else if (mostLikelyThingHeard.toUpperCase().equals("BAROMETER")) {
                        startActivity(new Intent(this, Barometer.class));
                    }else if (mostLikelyThingHeard.toUpperCase().equals("LIGHT SENSOR")) {
                        startActivity(new Intent(this, LightSensor.class));
                    }else if (mostLikelyThingHeard.toUpperCase().equals("NIGHT VISIÓN")) {
                        startActivity(new Intent(this, NightVision.class));
                    }
                    //else if() //Añadir más comandos de voz
                }
            }
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    public void emergencyCall_onClick(){
        String number ="610089569";
        Intent intent = new Intent(Intent.ACTION_CALL);
        intent.setData(Uri.parse("tel: "+ number));

        if(ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE)!= PackageManager.PERMISSION_GRANTED){
            return;
        }
        startActivity(intent);
    }

}
