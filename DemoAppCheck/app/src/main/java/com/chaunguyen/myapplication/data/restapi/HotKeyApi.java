package com.chaunguyen.myapplication.data.restapi;

import java.util.List;
import retrofit2.Call;
import retrofit2.http.GET;

public interface HotKeyApi {

    @GET("android-home-test/v2/keywords.json")
    Call<List<String>> getListDataHotKey();
}
