package com.example.ibnahmad.question10.network;

import com.example.ibnahmad.question10.model.IPClass;

import retrofit2.Call;
import retrofit2.http.GET;

public interface IPService {

    @GET("json")
    Call<IPClass> getIPDetail();

}