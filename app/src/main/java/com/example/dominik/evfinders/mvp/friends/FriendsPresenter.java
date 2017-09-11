package com.example.dominik.evfinders.mvp.friends;

import com.example.dominik.evfinders.database.pojo.Friend;
import com.example.dominik.evfinders.database.pojo.network.FriendResponse;
import com.example.dominik.evfinders.model.base.home.friends.IFriendsRepository;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Response;

/**
 * Created by Dominik on 11.09.2017.
 */

public class FriendsPresenter implements FriendsContract.Presenter, Observer<Response<List<FriendResponse>>> {

    private FriendsContract.View view;
    private IFriendsRepository repository;

    @Inject
    public FriendsPresenter(IFriendsRepository repository) {
        this.repository = repository;
    }

    @Override
    public void getFriendsList() {
        view.showDialog("Retrieving friends list...");
        Observable<Response<List<FriendResponse>>> friends = repository.getFriends();
        friends.subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this);
    }

    @Override
    public void onSubscribe(Disposable d) {

    }

    @Override
    public void onNext(Response<List<FriendResponse>> listResponse) {
        if (listResponse.code() == 200) {

            List<FriendResponse> body = listResponse.body();
            List<Friend> friendList = new ArrayList<>();
            for (FriendResponse friendResponse : body) {
                Friend friend = new Friend();
                friend.setName(friendResponse.getName());
                friend.setUsername(friendResponse.getUsername());
                friendList.add(friend);
            }
            view.onFriendsLoaded(friendList);
            view.hideDialog();
        } else {
            view.showToast("Unauthorized :(");
            view.hideDialog();
        }
    }

    @Override
    public void onError(Throwable e) {
        view.showToast("Error");
        e.printStackTrace();
    }

    @Override
    public void onComplete() {
        view.showToast("Complete");
    }

    @Override
    public void attach(FriendsContract.View view) {
        if (this.view == null) {
            this.view = view;
        }
    }

    @Override
    public void detach() {
        view = null;
    }
}
