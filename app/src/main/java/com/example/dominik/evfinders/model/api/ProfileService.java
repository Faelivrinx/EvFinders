package com.example.dominik.evfinders.model.api;

import com.example.dominik.evfinders.database.pojo.network.TaskResponse;

import io.reactivex.Single;
import retrofit2.Response;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Header;
import retrofit2.http.POST;

/**
 * Created by Dominik on 01.12.2017.
 */

public interface ProfileService {

    @FormUrlEncoded
    @POST("profile/update")
    Single<Response<TaskResponse>> saveOrUpdate(@Header("Authorization")String key, @Field("profile") String profile);
}
