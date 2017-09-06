package com.example.dominik.evfinders.model.api;

import com.example.dominik.evfinders.database.pojo.network.ApiKeyResponse;
import com.example.dominik.evfinders.database.pojo.User;
import com.example.dominik.evfinders.database.pojo.network.UserRequest;


import io.reactivex.Observable;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by Dominik on 06.09.2017.
 */

public interface RegisterService {

    @POST("register")
    Observable<Response<ApiKeyResponse>> register(@Body UserRequest user);
}
