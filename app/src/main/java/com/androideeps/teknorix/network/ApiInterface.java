package com.androideeps.teknorix.network;
import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiInterface {

    String JSONURL = "https://reqres.in/api/";

    @GET("users")
    Call<String> getString();
}