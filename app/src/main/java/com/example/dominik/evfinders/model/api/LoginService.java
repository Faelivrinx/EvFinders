package com.example.dominik.evfinders.model.api;


import com.example.dominik.evfinders.database.pojo.ApiKeyResponse;

import io.reactivex.Observable;
import io.reactivex.Single;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.POST;

/**
 * Created by Dominik on 05.09.2017.
 */

public interface LoginService {

    @GET("message")
    Observable<ApiKeyResponse> getToken();

}
