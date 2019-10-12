package com.jade.jade_baseframe.network;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class NetWorkManager {
    private static final Retrofit RETROFIT = new Retrofit.Builder()
            .baseUrl("https://www.apiopen.top")
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    public static <T> T getService(Class<? extends T> clazz) {
        return RETROFIT.create(clazz);
    }
}