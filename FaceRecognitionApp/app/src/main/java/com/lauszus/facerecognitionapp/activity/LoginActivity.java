/**
 * Class made by Rodrigo Leite Morais Gomes e Godoy
 * Using Lauszus project under the terms of the GNU General Public License version 3
 * <a href="https://github.com/Lauszus/FaceRecognitionApp">Lauszus Face Recognition Project</a>
 *
 */

package com.lauszus.facerecognitionapp.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.lauszus.facerecognitionapp.FaceRecognitionAppActivity;
import com.lauszus.facerecognitionapp.R;

public class LoginActivity extends AppCompatActivity {

    protected EditText edtUser;
    protected EditText edtPassword;

    protected TextView txtForgotPassword;

    protected SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initFields();

    }

    public void recoverPasswd(View view) {

        txtForgotPassword = findViewById(R.id.txtForgotPassword);

        Intent i = new Intent(LoginActivity.this, ForgotPasswdActivity.class);
        startActivity(i);
    }

    public void login(View view) {
        try {
            if(validateFields(edtUser.getText().toString(), edtPassword.getText().toString())) {
                Intent i = new Intent(LoginActivity.this, isFirstLogin(edtUser.getText().toString()));
                startActivity(i);
            } else{
                Toast.makeText(this, "Digite o usuário e a senha!", Toast.LENGTH_SHORT).show();
            }
        } catch (Exception e) {
            Log.println(Log.DEBUG, "login", "Error on login");
            e.printStackTrace();
        }
    }

    private void initFields(){
        edtUser = findViewById(R.id.edtUser);
        edtPassword = findViewById(R.id.edtPassword);
    }

    private boolean validateFields(String... values){
        for (String value : values) {
            if(value == null || value.isEmpty()){
                return false;
            }
        }

        return true;
    }

    /**
     * For more understanding about SharedPreferences map get methods see it in
     * <a href="https://android.googlesource.com/platform/frameworks/base/+/master/core/java/android/app/SharedPreferencesImpl.java">
     *     SharedPreferencesImpl
     * </a>
     * @param userAsKey
     * @return
     */
    private Class isFirstLogin(String userAsKey){
        SharedPreferences sharedPreferences = getApplicationContext()
                .getSharedPreferences("cgponto", Context.MODE_PRIVATE);

        if(sharedPreferences.getBoolean(userAsKey, Boolean.TRUE)){
            setFirstLoginStatus(userAsKey);
            return FaceRecognitionAppActivity.class;
        } else {
            Toast.makeText(this, "Não é a primeira", Toast.LENGTH_SHORT).show();
            return null;
        }
    }

    /**
     * After first sign in, is setted as {@code false} in SharedPreferences the status of
     * that specific user, where his username is used as key to the Map in SharedPreferences
     *
     * @param userAsKey username used as key to the SharedPreferences
     */
    private void setFirstLoginStatus(String userAsKey){
        SharedPreferences sharedPreferences = getApplicationContext()
                .getSharedPreferences("cgponto", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(userAsKey, Boolean.FALSE);
        editor.apply();
    }
}