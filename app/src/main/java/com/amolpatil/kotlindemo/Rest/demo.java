package com.amolpatil.kotlindemo.Rest;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class demo {

    public ApiInterface getClient(){

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.github.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiInterface service = retrofit.create(ApiInterface.class);

        return service;
    }

    public static demo getInstance(){
        return new demo();
    }


    ApiInterface apiInterface;

}
