package com.example.pablo.thelastsurvivor;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Vibrator;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;

import java.util.Locale;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

public class Water extends AppCompatActivity {
    private TextToSpeech TTSen, TTSde, TTSes, TTSzh;
    private TextToSpeech mTTS;
    private Button mButtonSpeak;
    private TextView mTextView;
    private TextView mTextView1;
    private TextView mTextView2;
    private TextView mTextView3;
    public static int chosen = 0;


    protected void onCreate(Bundle savedInstanceState) {
        if (AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_YES) {
            setTheme(R.style.DarkTheme);
        }
        super.onCreate(savedInstanceState);
        loadLocale();
        setContentView(R.layout.water);

        final MediaPlayer button_sound = MediaPlayer.create(this, R.raw.sound_button);
        final Vibrator vibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);

        TTSde = new TextToSpeech(this, new TextToSpeech.OnInitListener() {

            @Override
            public void onInit(int status) {
                if (status == TextToSpeech.SUCCESS) {
                    int result = TTSde.setLanguage(Locale.GERMAN);

                    if (result == TextToSpeech.LANG_MISSING_DATA
                            || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                        Log.e("TTS", "Language not supported");
                    } else {
                        mButtonSpeak.setEnabled(true);
                    }
                } else {
                    Log.e("TTS", "Initialization failed");
                }
            }
        });

        TTSen = new TextToSpeech(this, new TextToSpeech.OnInitListener() {

            @Override
            public void onInit(int status) {
                if (status == TextToSpeech.SUCCESS) {
                    int result = TTSen.setLanguage(Locale.ENGLISH);

                    if (result == TextToSpeech.LANG_MISSING_DATA
                            || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                        Log.e("TTS", "Language not supported");
                    } else {
                        mButtonSpeak.setEnabled(true);
                    }
                } else {
                    Log.e("TTS", "Initialization failed");
                }
            }
        });


        TTSes = new TextToSpeech(this, new TextToSpeech.OnInitListener() {

            @Override
            public void onInit(int status) {
                if (status == TextToSpeech.SUCCESS) {
                    Locale spanish = new Locale("es", "ES");
                    int result = TTSes.setLanguage(spanish);


                    if (result == TextToSpeech.LANG_MISSING_DATA
                            || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                        Log.e("TTS", "Language not supported");
                    } else {
                        mButtonSpeak.setEnabled(true);
                    }
                } else {
                    Log.e("TTS", "Initialization failed");
                }
            }
        });

        TTSzh = new TextToSpeech(this, new TextToSpeech.OnInitListener() {

            @Override
            public void onInit(int status) {
                if (status == TextToSpeech.SUCCESS) {
                    int result = TTSzh.setLanguage(Locale.SIMPLIFIED_CHINESE);

                    if (result == TextToSpeech.LANG_MISSING_DATA
                            || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                        Log.e("TTS", "Language not supported");
                    } else {
                        mButtonSpeak.setEnabled(true);
                    }
                } else {
                    Log.e("TTS", "Initialization failed");
                }
            }
        });

        mButtonSpeak = (Button) findViewById(R.id.button_speak);

        mTTS = new TextToSpeech(this, new TextToSpeech.OnInitListener() {

            @Override
            public void onInit(int status) {
                if (status == TextToSpeech.SUCCESS) {
                    int result = mTTS.setLanguage(Locale.ENGLISH);

                    if (result == TextToSpeech.LANG_MISSING_DATA
                            || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                        Log.e("TTS", "Language not supported");
                    } else {
                        mButtonSpeak.setEnabled(true);
                    }
                } else {
                    Log.e("TTS", "Initialization failed");
                }
            }
        });

        mTextView = findViewById(R.id.beforeStartingTitle);
        mTextView1 = findViewById(R.id.waterTitle2);
        mTextView2 = findViewById(R.id.techniquesTitle);
        mTextView3 = findViewById(R.id.waterText2);

        mButtonSpeak.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                button_sound.start();
                vibrator.vibrate(50);
                Snackbar.make(v, getString(R.string.LongTap), Snackbar.LENGTH_LONG).setAction("Action", null).show();
                showChangeLanguageDialogSpeak();
            }
        });

        mButtonSpeak.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                TTSde.stop();
                TTSde.shutdown();
                TTSes.stop();
                TTSes.shutdown();
                TTSzh.stop();
                TTSzh.shutdown();
                TTSen.stop();
                TTSen.shutdown();
                restartApp();
                return true;
            }
        });

        Button changeLang = findViewById(R.id.change_Lang);

        changeLang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                button_sound.start();
                vibrator.vibrate(50);
                showChangeLanguageDialog();
            }

        });

    }
/*
    private void speak() {
        CharSequence text = mTextView.getText().toString();
        CharSequence text1 = mTextView1.getText().toString();
        CharSequence text2 = mTextView2.getText().toString();
        CharSequence text3 = mTextView3.getText().toString();

        mTTS.speak(text, TextToSpeech.QUEUE_ADD, null, null);
        mTTS.speak(text1, TextToSpeech.QUEUE_ADD, null, null);
        mTTS.speak(text2, TextToSpeech.QUEUE_ADD, null, null);
        mTTS.speak(text3, TextToSpeech.QUEUE_ADD, null, null);
    }*/
/*
    @Override
    protected void onDestroy() {
        if (mTTS != null) {
            mTTS.stop();
            mTTS.shutdown();
        }

        super.onDestroy();
    }

    public void onPause() {
        if (mTTS != null) {
            mTTS.stop();
            mTTS.shutdown();
        }
        super.onPause();
    }*/

    public void showChangeLanguageDialogSpeak() {
        final String[] listItems = {"English", "Deutsch", "Español", "中国人"};
        AlertDialog.Builder mBuilder = new AlertDialog.Builder(Water.this);
        mBuilder.setTitle("Choose Language");
        final String text = mTextView.getText().toString();
        final String text1 = mTextView1.getText().toString();
        final String text2 = mTextView2.getText().toString();
        final String text3 = mTextView3.getText().toString();


        switch (chosen) {
            case 0:
                TTSen.speak(text, TextToSpeech.QUEUE_FLUSH, null);
                TTSen.speak(text1, TextToSpeech.QUEUE_ADD, null);
                TTSen.speak(text2, TextToSpeech.QUEUE_ADD, null);
                TTSen.speak(text3, TextToSpeech.QUEUE_ADD, null);
                break;
            case 1:
                TTSde.speak(text, TextToSpeech.QUEUE_FLUSH, null);
                TTSde.speak(text1, TextToSpeech.QUEUE_ADD, null);
                TTSde.speak(text2, TextToSpeech.QUEUE_ADD, null);
                TTSde.speak(text3, TextToSpeech.QUEUE_ADD, null);
                break;
            case 2:
                TTSes.speak(text, TextToSpeech.QUEUE_FLUSH, null);
                TTSes.speak(text1, TextToSpeech.QUEUE_ADD, null);
                TTSes.speak(text2, TextToSpeech.QUEUE_ADD, null);
                TTSes.speak(text3, TextToSpeech.QUEUE_ADD, null);
                break;
            case 3:
                TTSzh.speak(text, TextToSpeech.QUEUE_FLUSH, null);
                TTSzh.speak(text1, TextToSpeech.QUEUE_ADD, null);
                TTSzh.speak(text2, TextToSpeech.QUEUE_ADD, null);
                TTSzh.speak(text3, TextToSpeech.QUEUE_ADD, null);
                break;
        }


       /* mBuilder.setSingleChoiceItems(listItems, -1, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int i) {
                    if (i == 0) {
                        TTSen.speak(text, TextToSpeech.QUEUE_FLUSH, null);
                        TTSen.speak(text1, TextToSpeech.QUEUE_ADD, null);
                        TTSen.speak(text2, TextToSpeech.QUEUE_ADD, null);
                        TTSen.speak(text3, TextToSpeech.QUEUE_ADD, null);

                    } else if (i == 1) {
                        TTSde.speak(text, TextToSpeech.QUEUE_FLUSH, null);
                        TTSde.speak(text1, TextToSpeech.QUEUE_ADD, null);
                        TTSde.speak(text2, TextToSpeech.QUEUE_ADD, null);
                        TTSde.speak(text3, TextToSpeech.QUEUE_ADD, null);
                    } else if (i == 2) {
                        TTSes.speak(text, TextToSpeech.QUEUE_FLUSH, null);
                        TTSes.speak(text1, TextToSpeech.QUEUE_ADD, null);
                        TTSes.speak(text2, TextToSpeech.QUEUE_ADD, null);
                        TTSes.speak(text3, TextToSpeech.QUEUE_ADD, null);

                    } else if (i == 3) {
                        TTSzh.speak(text, TextToSpeech.QUEUE_FLUSH, null);
                        TTSzh.speak(text1, TextToSpeech.QUEUE_ADD, null);
                        TTSzh.speak(text2, TextToSpeech.QUEUE_ADD, null);
                        TTSzh.speak(text3, TextToSpeech.QUEUE_ADD, null);
                    }


                dialog.dismiss();
            }

        });
        AlertDialog mDialog = mBuilder.create();
        mDialog.show();*/
    }

    @Override
    protected void onDestroy() {
        if (TTSen == null) {
            TTSen.stop();
            TTSen.shutdown();
        }
        if (TTSde == null) {
            TTSde.stop();
            TTSde.shutdown();
        }
        if (TTSes == null) {
            TTSes.stop();
            TTSes.shutdown();
        }
        if (TTSzh == null) {
            TTSzh.stop();
            TTSzh.shutdown();
        }
        super.onDestroy();
    }

    public void restartApp() {
        Intent i = new Intent(getApplicationContext(), Water.class);
        startActivity(i);
        finish();
    }

    //Multiple translations
    public void showChangeLanguageDialog() {
        final String[] listItems = {"English", "Deutsch", "Español", "中国人"};
        AlertDialog.Builder mBuilder = new AlertDialog.Builder(Water.this);
        mBuilder.setTitle("Choose Language");
        mBuilder.setSingleChoiceItems(listItems, -1, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int i) {
                chosen = i;
                if (i == 0) {
                    setLocale("en");
                    recreate();

                } else if (i == 1) {
                    setLocale("de");
                    recreate();
                } else if (i == 2) {
                    setLocale("es");
                    recreate();
                } else if (i == 3) {
                    setLocale("zh");
                    recreate();
                }
                dialog.dismiss();


            }
        });

        AlertDialog mDialog = mBuilder.create();
        mDialog.show();

    }

    private void setLocale(String lang) {
        Locale locale = new Locale(lang);
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.locale = locale;
        getBaseContext().getResources().updateConfiguration(config, getBaseContext().getResources().getDisplayMetrics());
        SharedPreferences.Editor editor = getSharedPreferences("Settings", MODE_PRIVATE).edit();
        editor.putString("My_Lang", lang);
        editor.apply();
    }

    public void loadLocale() {
        SharedPreferences prefs = getSharedPreferences("Settings", Activity.MODE_PRIVATE);
        String language = prefs.getString("My_Lang", "");
        setLocale(language);
    }
}
