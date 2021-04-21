package com.example.covid_19.api.methods;

import com.example.covid_19.api.AppService;
import com.example.covid_19.api.Config;
import com.example.covid_19.api.models.BaseResponse;

import retrofit2.Call;

public class GetStatistic {
    private AppService appService;

    public Call<BaseResponse> getStatistics() {
        String url = "https://covid-193.p.rapidapi.com/statistics/";
        String key = "a6bbdf2257mshbddc0d75b517587p164465jsn6bf0b909752f";
        String host = "covid-193.p.rapidapi.com";
        appService = Config.retrofit.create(AppService.class);
        return appService.GET_STATISTICS(url, key, host);
    }
}
