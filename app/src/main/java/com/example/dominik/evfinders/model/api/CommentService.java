package com.example.dominik.evfinders.model.api;

import com.example.dominik.evfinders.command.CommentCommand;
import com.example.dominik.evfinders.command.EventCommand;

import io.reactivex.Single;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.POST;

/**
 * Created by Dominik on 27.12.2017.
 */

public interface CommentService {

    @POST("comment/add")
    Single<Response<CommentCommand>> addComment(@Header("Authorization")String token, @Body CommentCommand eventCommand);

}
