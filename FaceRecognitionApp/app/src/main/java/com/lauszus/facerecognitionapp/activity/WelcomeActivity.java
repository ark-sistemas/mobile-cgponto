package com.lauszus.facerecognitionapp.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.os.Bundle;

import com.github.paolorotolo.appintro.AppIntro;
import com.github.paolorotolo.appintro.AppIntroFragment;
import com.lauszus.facerecognitionapp.R;


public class WelcomeActivity extends AppIntro {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if(!isFirstTimeStartApp()){
            startMainActivity();
            finish();
        }

        addSliders();

    }

    @Override
    public void onSkipPressed(Fragment currentFragment) {
        super.onSkipPressed(currentFragment);
        startMainActivity();
    }

    @Override
    public void onDonePressed(Fragment currentFragment) {
        super.onDonePressed(currentFragment);
        startMainActivity();
    }

    private boolean isFirstTimeStartApp(){
        SharedPreferences sharedPreferences = getApplicationContext()
                .getSharedPreferences("cgponto", Context.MODE_PRIVATE);
        return sharedPreferences.getBoolean("FirstTimeStartFlag", Boolean.TRUE);
    }

    private void setFirstTimeStartStatus(Boolean start){
        SharedPreferences sharedPreferences = getApplicationContext()
                .getSharedPreferences("cgponto", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean("FirstTimeStartFlag", start);
        editor.apply();
    }

    private void startMainActivity(){
        setFirstTimeStartStatus(Boolean.FALSE);
        startActivity(new Intent(WelcomeActivity.this, LoginActivity.class));
        finish();
    }



    private void addSliders(){

        //Login slider
        addSlide(AppIntroFragment.newInstance(null, null,
                getResources().getString(R.string.label_slide_login),
                null,
                R.drawable.login_password,
                ContextCompat.getColor(this, R.color.backgroundColor),
                ContextCompat.getColor(this, R.color.textColor),
                ContextCompat.getColor(this, R.color.textColor)));

        // Face picture slider
        addSlide(AppIntroFragment.newInstance(null, null,
                getResources().getString(R.string.label_slide_fc_pic),
                null,
                R.drawable.opt_camera,
                ContextCompat.getColor(this, R.color.backgroundColor),
                ContextCompat.getColor(this, R.color.textColor),
                ContextCompat.getColor(this, R.color.textColor)));

        // Face recognition slider
        addSlide(AppIntroFragment.newInstance(null, null,
                getResources().getString(R.string.label_slide_recognition),
                null,
                R.drawable.opt_fc_recognition,
                ContextCompat.getColor(this, R.color.backgroundColor),
                ContextCompat.getColor(this, R.color.textColor),
                ContextCompat.getColor(this, R.color.textColor)));

        showSkipButton(true);
        setProgressButtonEnabled(true);
        setFadeAnimation();

        setColorDoneText(ContextCompat.getColor(this, R.color.textColor));
        setColorSkipButton(ContextCompat.getColor(this, R.color.textColor));
        setNextArrowColor(ContextCompat.getColor(this, R.color.textColor));
        setIndicatorColor(ContextCompat.getColor(this, R.color.textColor),
                Color.parseColor("#56aaff"));

    }
}
