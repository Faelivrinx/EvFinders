package com.example.dominik.evfinders.model.base.home.friends;

import com.example.dominik.evfinders.database.pojo.Friend;
import com.example.dominik.evfinders.database.pojo.network.FriendResponse;
import com.example.dominik.evfinders.model.api.FriendsService;
import com.example.dominik.evfinders.model.repo.IPrefs;
import com.example.dominik.evfinders.model.repo.Prefs;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import retrofit2.Response;

/**
 * Created by Dominik on 11.09.2017.
 */

public class FriendsRepository implements IFriendsRepository {

    private IPrefs prefs;
    private FriendsService service;

    @Inject
    public FriendsRepository(IPrefs prefs, FriendsService service) {
        this.prefs = prefs;
        this.service = service;
    }

    @Override
    public Observable<Response<List<FriendResponse>>> getFriends() {
        return service.getFriends(prefs.get(Prefs.API_KEY));
    }

    @Override
    public Observable<Response<String>> delFriend(String username) {
        return null;
    }

    @Override
    public Observable<Response<Friend>> addFriends(String username) {
        return null;
    }
}
