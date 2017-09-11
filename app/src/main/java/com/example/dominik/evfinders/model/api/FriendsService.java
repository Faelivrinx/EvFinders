package com.example.dominik.evfinders.model.api;

import com.example.dominik.evfinders.database.pojo.Friend;
import com.example.dominik.evfinders.database.pojo.network.FriendResponse;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.Response;
import retrofit2.http.Header;
import retrofit2.http.POST;

/**
 * Created by Dominik on 11.09.2017.
 */

public interface FriendsService {

    @POST("friends")
    Observable<Response<List<FriendResponse>>> getFriends(@Header("Authorization") String key);
}
