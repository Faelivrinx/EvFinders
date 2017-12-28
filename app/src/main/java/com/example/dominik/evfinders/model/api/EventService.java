package com.example.dominik.evfinders.model.api;

import com.example.dominik.evfinders.command.CoordinateCommand;
import com.example.dominik.evfinders.command.EventCommand;
import com.example.dominik.evfinders.database.pojo.network.TaskResponse;

import java.util.List;

import io.reactivex.Single;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Created by Dominik on 25.08.2017.
 */

public interface EventService {

    @POST("event/add")
    Single<Response<TaskResponse>> createEvent(@Header("Authorization")String key, @Body EventCommand eventCommand);

    @POST("events")
    Single<Response<List<EventCommand>>> getEvents(@Header("Authorization") String key, @Body CoordinateCommand coordinates);

    @POST("events/recommendation")
    Single<Response<List<EventCommand>>> getEventsWithRecommendation(@Header("Authorization") String key, @Body CoordinateCommand coordinates);

    @GET("event/{id}/attend")
    Single<Response<EventCommand>> attendEvent(@Header("Authorization")String token, @Path("id")Long id);

}
