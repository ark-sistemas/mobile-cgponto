package com.lauszus.facerecognitionapp.resource;

import com.lauszus.facerecognitionapp.model.Usuario;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface UsuarioResource {

    @PUT("/usuario")
    Call<Usuario> put(@Body String email); //To change password

    @PATCH("/usuario")
    Call<Boolean> patch(@Body Usuario usuario);
}
