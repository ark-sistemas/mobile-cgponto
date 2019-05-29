package com.lauszus.facerecognitionapp.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.lauszus.facerecognitionapp.FaceRecognitionAppActivity;
import com.lauszus.facerecognitionapp.R;

public class LoginActivity extends AppCompatActivity {

    protected TextView txtForgotPassword;

    protected SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    public void recoverPasswd(View view) {

        txtForgotPassword = findViewById(R.id.txtForgotPassword);

        Intent i = new Intent(LoginActivity.this, ForgotPasswdActivity.class);
        startActivity(i);
    }

    public void login(View view) {
        try {

            Intent i = new Intent(LoginActivity.this, FaceRecognitionAppActivity.class);
//        Intent i = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            startActivity(i);
        } catch (Exception e) {
            Log.println(Log.DEBUG, "login", "Deu errado");
            e.printStackTrace();
        }
    }
}