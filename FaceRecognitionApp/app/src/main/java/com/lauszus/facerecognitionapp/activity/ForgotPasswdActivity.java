package com.lauszus.facerecognitionapp.activity;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.lauszus.facerecognitionapp.R;

public class ForgotPasswdActivity extends AppCompatActivity {

    EditText txtEmailRedef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_pass);
    }

    public void sendEmail(View view) {

        txtEmailRedef = findViewById(R.id.txtEmailRedef);

        final String SUBJECT = "Teste de e-mail do app CG Ponto";
        final String MSG = "Essa é uma mensagem de teste para o envio de e-mail pelo app CG Ponto";

    }

}