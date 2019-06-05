package com.chaunguyen.myapplication.domain.usecases;

import com.chaunguyen.myapplication.common.constant.ErrCode;
import com.chaunguyen.myapplication.data.restapi.HotKeyApi;
import com.chaunguyen.myapplication.data.retro2client.AppHttpClient;
import com.google.gson.JsonObject;

import java.util.List;

import io.reactivex.Single;
import okhttp3.ResponseBody;
import retrofit2.Response;

public class GetHotKey {

    public GetHotKey() {
    }

    public Single<List<String>> listDataHotKey() {
        return Single.create(e -> {
            HotKeyApi hotKeyApi = AppHttpClient.getInstance().getHotKeyApi();
            Response<List<String>> requestTokenResponse = hotKeyApi.getListDataHotKey().execute();

            if (!requestTokenResponse.isSuccessful()) {
                throw new Exception(requestTokenResponse.errorBody().string());
            }

            List<String> listKey = requestTokenResponse.body();

            if (listKey == null || listKey.size() == 0) {
                throw new Exception(ErrCode.API_ERROR.name());
            } else {
                e.onSuccess(listKey);
            }
        });
    }

}