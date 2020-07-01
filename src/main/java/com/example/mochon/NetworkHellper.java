package com.example.mochon;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface NetworkHellper {
    final String Base_URL="http://0.0.0.0/";
    @FormUrlEncoded
    @POST("/list")
//    Call<String> Auth(@Field("list[]") ArrayList<MainItem> list );

    Call<ArrayList<String>> Auth(@Field("list") String list);
}
