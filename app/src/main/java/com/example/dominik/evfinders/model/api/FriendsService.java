package com.example.dominik.evfinders.model.api;

import com.example.dominik.evfinders.database.pojo.Friend;
import com.example.dominik.evfinders.database.pojo.network.FriendDeleteRequest;
import com.example.dominik.evfinders.database.pojo.network.FriendResponse;
import com.example.dominik.evfinders.database.pojo.network.TaskResponse;

import org.json.JSONObject;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Header;
import retrofit2.http.POST;

/**
 * Created by Dominik on 11.09.2017.
 */

public interface FriendsService {

    @POST("friends")
    Observable<Response<List<FriendResponse>>> getFriends(@Header("Authorization") String key);

    @FormUrlEncoded
    @POST("friend/request")
    Observable<Response<TaskResponse>> addFriendRequest(@Header("authorization") String key, @Field("username")String username);

    @FormUrlEncoded
    @POST("friend/add")
    Observable<Response<TaskResponse>> addFriend(@Header("authorization") String key, @Field("username")String username);

    @POST("friends/delete")
    Observable<Response<TaskResponse>> deleteFriends(@Header("Authorization")String key, @Body FriendDeleteRequest usernames);
}
