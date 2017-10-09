package com.example.dominik.evfinders.model.base.home.friends;

import com.example.dominik.evfinders.database.pojo.Friend;
import com.example.dominik.evfinders.database.pojo.network.FriendResponse;
import com.example.dominik.evfinders.database.pojo.network.TaskResponse;

import org.json.JSONObject;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.Response;

/**
 * Created by Dominik on 11.09.2017.
 */

public interface IFriendsRepository {

    Observable<Response<List<FriendResponse>>> getFriends();
    Observable<Response<TaskResponse>> delFriends(List<String> usernames);
    Observable<Response<TaskResponse>> addFriends(String username);
    Observable<Response<TaskResponse>> addFriendsRequest(String username);

}
