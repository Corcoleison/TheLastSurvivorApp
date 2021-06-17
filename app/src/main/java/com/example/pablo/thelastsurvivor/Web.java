package com.example.pablo.thelastsurvivor;

import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;

public class Web extends AppCompatActivity {
    private String Search;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);


            WebView myWebView = new WebView(getApplicationContext());
            setContentView(myWebView);

            myWebView.loadUrl("https://www.google.com/intl/es_es/sky/");

            WebSettings webSettings = myWebView.getSettings();
            webSettings.setJavaScriptEnabled(true);


    }
    }
