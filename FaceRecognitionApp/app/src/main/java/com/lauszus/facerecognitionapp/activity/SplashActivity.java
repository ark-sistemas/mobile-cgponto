package com.lauszus.facerecognitionapp.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.lauszus.facerecognitionapp.R;
import com.lauszus.facerecognitionapp.util.PreferencesMap;


public class SplashActivity extends AppCompatActivity {

    private static final int TIME_OUT = 2000;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        SharedPreferences sp = PreferencesMap.getSharedPreferences(this);
        SharedPreferences.Editor ed = sp.edit();
        ed.clear();
        ed.apply();

        new Handler().postDelayed(() -> {
            Intent i = new Intent(SplashActivity.this, WelcomeActivity.class);
            startActivity(i);
            finish();
        }, TIME_OUT);
    }
}