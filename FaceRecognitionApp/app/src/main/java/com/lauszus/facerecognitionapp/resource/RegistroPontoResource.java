package com.lauszus.facerecognitionapp.resource;

import com.lauszus.facerecognitionapp.model.RegistroPonto;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;

public interface RegistroPontoResource {

    @GET("/get")
    Call<List<RegistroPonto>> get();

    @POST("/registro")
    Call<RegistroPonto> post(@Body RegistroPonto registro);

    @PUT("/registro")
    Call<RegistroPonto> put(@Body RegistroPonto registro);
}
