/**
 * Class made by Rodrigo Leite Morais Gomes e Godoy
 * Using Lauszus project under the terms of the GNU General Public License version 3
 * <a href="https://github.com/Lauszus/FaceRecognitionApp">Lauszus Face Recognition Project</a>
 *
 */

package com.lauszus.facerecognitionapp.activity;

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
import com.lauszus.facerecognitionapp.bootstrap.APIClient;
import com.lauszus.facerecognitionapp.interfaces.FieldInitializer;
import com.lauszus.facerecognitionapp.model.Usuario;
import com.lauszus.facerecognitionapp.resource.UsuarioResource;
import com.lauszus.facerecognitionapp.util.PreferencesMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity implements FieldInitializer {

    protected EditText edtUser;
    protected EditText edtPassword;

    protected TextView txtForgotPassword;
    private SharedPreferences sharedPreferences;
    private UsuarioResource usuarioResource;
    private Usuario usuario;


    /**
     * TrObLHxE
     * @param savedInstanceState
     */


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initFields();

    }

    @Override
    public void initFields(){
        edtUser = findViewById(R.id.edtUser);
        edtPassword = findViewById(R.id.edtPassword);
        sharedPreferences = PreferencesMap.getSharedPreferences(this);
        usuarioResource = APIClient.getClient("/usuario/").create(UsuarioResource.class);
    }

    public void recoverPasswd(View view) {

        txtForgotPassword = findViewById(R.id.txtForgotPassword);

        Intent i = new Intent(LoginActivity.this, ForgotPasswdActivity.class);
        startActivity(i);
    }

    public void login(View view) {
        try {
            if(validateFields(edtUser.getText().toString(), edtPassword.getText().toString())) {
                this.usuario = new Usuario();
                this.usuario.setLogin(edtUser.getText().toString());
                this.usuario.setSenha(edtPassword.getText().toString());
                this.sendToLoginService(this.usuario);
            }
        } catch (Exception e) {
            Log.println(Log.DEBUG, "login", "Error on login");
        }
    }

    private void sendToLoginService(Usuario usuario){
        Call<Boolean> patch = usuarioResource.patch(usuario);
        patch.enqueue(new Callback<Boolean>() {
            @Override
            public void onResponse(Call<Boolean> call, Response<Boolean> response) {
                Boolean condition = response.body();
                if(condition == Boolean.TRUE){
                    boolean firstLogin = isFirstLogin(edtUser.getText().toString());
                    if(firstLogin) {
                        Intent i = new Intent(LoginActivity.this, FaceRecognitionAppActivity.class);
                        startActivity(i);
                    } else {
                        Intent in = new Intent(LoginActivity.this, TelaPrincipalActivity.class);
                        startActivity(in);
                    }
                } else {
                    Toast.makeText(LoginActivity.this, "Usuário inexistente.",
                            Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<Boolean> call, Throwable t) {
                Toast.makeText(LoginActivity.this, "Erro ao tentar realizar login!",
                        Toast.LENGTH_LONG).show();
            }
        });
    }

    private boolean validateFields(String... values){
        for (int i = 0; i < values.length; i++) {
            if(values[i] == null || values[i].isEmpty()){
                Toast.makeText(this, "Digite o e-mail e a senha!", Toast.LENGTH_SHORT).show();
                return false;
            }

            if(!values[0].contains("@") && values[0].contains("[\u0020-\u002D|\u003A-\u003F|\u005B-\u0060|" +
                    "\u007B-\u007E]")){
                Toast.makeText(this, "Digite um e-mail válido!", Toast.LENGTH_SHORT).show();
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
     * @param email
     * @return
     */
    private boolean isFirstLogin(String email){

        if(!this.sharedPreferences.getBoolean("email", Boolean.FALSE)){
            setFirstLoginStatus(email);
            return true;
        } else {
            return false;
        }
    }

    /**
     * After first sign in, is setted as {@code false} in SharedPreferences the status of
     * that specific user, where his email is used as key to the Map in SharedPreferences.
     *
     * In addition, will be mapped a String using his password as key to his email,
     * like this : putString(hisPassword, hisUsername), where this will be used to
     * define when his face will be used to train and when will be used just the recognition
     *
     * @param email email used as value to the SharedPreferences
     */
    private void setFirstLoginStatus(String email){
        SharedPreferences.Editor editor = this.sharedPreferences.edit();
        editor.putBoolean("email", Boolean.FALSE);
        editor.putString("emailString", email);
        editor.apply();
    }
}