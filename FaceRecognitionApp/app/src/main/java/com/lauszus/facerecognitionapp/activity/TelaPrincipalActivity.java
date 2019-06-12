package com.lauszus.facerecognitionapp.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.lauszus.facerecognitionapp.R;
import com.lauszus.facerecognitionapp.interfaces.FieldInitializer;

public class TelaPrincipalActivity extends AppCompatActivity  {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_principal);
    }




    public void register(View view) {
        Intent intent = new Intent(TelaPrincipalActivity.this, MapaActivity.class);
    }
}
