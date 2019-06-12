package com.lauszus.facerecognitionapp.resource;

import com.lauszus.facerecognitionapp.model.Usuario;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface UsuarioReource {

    @GET("/posts")
    Call<List<Usuario>> get();

    @POST("/posts")
    Call<Usuario> post(@Body Usuario userPost);

    @PUT("/posts/{id}")
    Call<Usuario> put(@Body Usuario userPost, @Path("id") Integer id);

    @DELETE("/posts/{id}")
    Call<Void> delete(@Path("id") Integer id);
}
