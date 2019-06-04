package com.chaunguyen.myapplication.data.restapi;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface HotKeyApi {

    @GET(".")
    Call<List<String>[]> getListDataHotKey();
}