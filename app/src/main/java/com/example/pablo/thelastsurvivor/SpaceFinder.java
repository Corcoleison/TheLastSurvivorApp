package com.example.pablo.thelastsurvivor;

import android.content.Intent;
import android.media.Image;
import android.media.MediaPlayer;
import android.net.Uri;
import android.speech.RecognizerIntent;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Locale;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

public class SpaceFinder extends AppCompatActivity {
    private EditText search_text;
    private Button search_button;
    private Button loupe_button;
    private ImageView explore_ImageView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if(AppCompatDelegate.getDefaultNightMode()==AppCompatDelegate.MODE_NIGHT_YES){
            setTheme(R.style.DarkTheme);
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.space_finder);
        final MediaPlayer button_sound = MediaPlayer.create(this, R.raw.sound_button);
        search_text=(EditText) findViewById(R.id.search_text);
        search_button=(Button)findViewById(R.id.btnSpeak);
        search_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                button_sound.start();
                getSpeechInput();
            }
        });

        loupe_button = (Button)findViewById(R.id.loupe_button);
        loupe_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(search_text.getText().toString().matches("")) {
                    Toast.makeText(SpaceFinder.this, "Enter something", Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(SpaceFinder.this, "Searching...", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.google.com/#q=" + search_text.getText())));
                }
            }
        });

        explore_ImageView = (ImageView)findViewById(R.id.explore_imageView);
        explore_ImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), Web.class);
                startActivity(i);
            }
        });
    }

    public void getSpeechInput() {

        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());

        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(intent, 10);
        } else {
            Toast.makeText(this, "Your Device Don't Support Speech Input", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {
            case 10:
                if (resultCode == RESULT_OK && data != null) {
                    ArrayList<String> result = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    search_text.setText(result.get(0));
                }
                break;
        }
    }
}
