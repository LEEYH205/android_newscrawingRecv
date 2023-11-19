package com.example.jsontestapp;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiService {
    @POST("/")
    Call<List<MyResponseModel>> sendData(@Body MyRequestModel request);
}