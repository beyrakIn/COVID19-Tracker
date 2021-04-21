package com.example.covid_19.api;

import com.example.covid_19.api.models.BaseResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Query;
import retrofit2.http.Url;

public interface AppService {

    @GET
    Call<BaseResponse> GET_STATISTICS(
            @Url String url,
            @Header("x-rapidapi-key") String key,
            @Header("x-rapidapi-host") String host
    );

}
