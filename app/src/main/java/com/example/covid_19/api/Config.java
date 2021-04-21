package com.example.covid_19.api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Config {
    public static Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://covid-193.p.rapidapi.com/statistics/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();
}
