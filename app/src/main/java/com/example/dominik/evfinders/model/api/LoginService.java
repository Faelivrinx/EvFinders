package com.example.dominik.evfinders.model.api;


import com.example.dominik.evfinders.database.pojo.ApiKeyResponse;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

/**
 * Created by Dominik on 05.09.2017.
 */

public interface LoginService {

    @FormUrlEncoded
    @POST("login")
    Observable<ApiKeyResponse> getToken(@Field("username") String username, @Field("password") String password);

}
