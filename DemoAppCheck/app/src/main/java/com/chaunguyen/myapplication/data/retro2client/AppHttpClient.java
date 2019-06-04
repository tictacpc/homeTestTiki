package com.chaunguyen.myapplication.data.retro2client;

import com.chaunguyen.myapplication.BuildConfig;
import com.chaunguyen.myapplication.data.restapi.HotKeyApi;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class AppHttpClient extends Retro2Client {

    private HotKeyApi hotKeyApi;

    private static class AppHttpClientHelper {
        private static final AppHttpClient INSTANCE = new AppHttpClient();
    }

    public static AppHttpClient getInstance() {
        return AppHttpClientHelper.INSTANCE;
    }

    public HotKeyApi getHotKeyApi() {
        return hotKeyApi;
    }

    private AppHttpClient() {

        final Retrofit aspireOauthRetrofit = new Retrofit.Builder()
                .baseUrl(BuildConfig.API_KEY_HOT)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(provideOkHttpClient())
                .build();

        hotKeyApi = aspireOauthRetrofit.create(HotKeyApi.class);


    }


}
