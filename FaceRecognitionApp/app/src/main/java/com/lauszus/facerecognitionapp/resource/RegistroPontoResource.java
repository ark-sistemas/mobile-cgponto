package com.lauszus.facerecognitionapp.resource;

import com.lauszus.facerecognitionapp.model.RegistroPonto;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface RegistroPontoResource {

    @GET("/get")
    Call<List<RegistroPonto>> get();

    @POST("/post")
    Call<RegistroPonto> post(@Body RegistroPonto registro);
}
