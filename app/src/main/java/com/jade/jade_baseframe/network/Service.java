package com.jade.jade_baseframe.network;

import com.jade.jade_baseframe.network.response.FemaleNameResponse;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface Service {

    @POST("femaleNameApi")
    @FormUrlEncoded
    Observable<FemaleNameResponse> getFemaleNameList(@Field("page") int page);
}
