package com.lauszus.facerecognitionapp.activity;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.lauszus.facerecognitionapp.R;
import com.lauszus.facerecognitionapp.bootstrap.APIClient;
import com.lauszus.facerecognitionapp.interfaces.FieldInitializer;
import com.lauszus.facerecognitionapp.model.Usuario;
import com.lauszus.facerecognitionapp.resource.UsuarioResource;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ForgotPasswdActivity extends AppCompatActivity implements FieldInitializer {

    private EditText txtEmailRedef;
    private UsuarioResource usuarioResource;
    private Button btnSendPassw;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_pass);
        initFields();

        btnSendPassw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendEmail(txtEmailRedef.getText().toString());
            }
        });
    }

    @Override
    public void initFields() {
        txtEmailRedef = findViewById(R.id.txtEmailRedef);
        btnSendPassw = findViewById(R.id.btn_send_passwd);
        usuarioResource = APIClient.getClient("/usuario/").create(UsuarioResource.class);
    }


    private boolean validateFields(String email){
            if(email == null || email.isEmpty()){
                Toast.makeText(this, "Digite seu e-mail cadastrado!", Toast.LENGTH_SHORT).show();
                return false;
            }

            if(!email.contains("@") || email.contains("[\u0020-\u002D|\u003A-\u003F|\u005B-\u0060|" +
                    "\u007B-\u007E]")){
                Toast.makeText(this, "Digite um e-mail válido!", Toast.LENGTH_SHORT).show();
                return false;
            }


        return true;
    }

    private void sendEmail(String email){
        if(validateFields(txtEmailRedef.getText().toString())){
            Toast.makeText(ForgotPasswdActivity.this, email, Toast.LENGTH_SHORT).show();

            Call<Usuario> put = usuarioResource.put(email);

            put.enqueue(new Callback<Usuario>() {
                @Override
                public void onResponse(Call<Usuario> call, Response<Usuario> response) {
                    Toast.makeText(ForgotPasswdActivity.this, "Nova senha enviada ao seu e-mail.", Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onFailure(Call<Usuario> call, Throwable t) {
                    Toast.makeText(ForgotPasswdActivity.this, "E-mail inválido!", Toast.LENGTH_SHORT).show();
                }
            });

        }
    }
}