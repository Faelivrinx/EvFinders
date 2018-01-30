package com.example.dominik.evfinders.application.services;

import android.app.NotificationManager;
import android.content.Context;
import android.support.annotation.VisibleForTesting;
import android.util.Log;

import com.example.dominik.evfinders.database.pojo.Friend;
import com.example.dominik.evfinders.database.pojo.network.TaskResponse;
import com.example.dominik.evfinders.model.api.FriendsService;
import com.example.dominik.evfinders.model.base.home.friends.IFriendsRepository;

import org.json.JSONObject;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Response;

/**
 * Created by Dominik on 12.09.2017.
 */

public class FcmFriendPresenter implements FcmFriendContract.Presenter, Observer<Response<TaskResponse>> {

    private FcmFriendContract.View view;
    private IFriendsRepository repository;
    private Context context;

    @Inject
    public FcmFriendPresenter(IFriendsRepository repository) {
        this.repository = repository;
    }

    @Override
    public void attach(FcmFriendContract.View view) {
        if (this.view == null) {
            this.view = view;
        }
    }

    @Override
    public void detach() {
        view = null;
    }

    @Override
    public void addFriend(String username, Context context) {
        this.context = context;
        repository.addFriends(username)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this);
    }

    @Override
    public void onSubscribe(Disposable d) {

    }

    @Override
    public void onNext(Response<TaskResponse> response) {
        if (response.code() == 200) {
            TaskResponse body = response.body();
            if (body != null && body.getValue().equals("success")) {
                view.closeNotification(context);
            }
        } else {

        }
    }

    @Override
    public void onError(Throwable e) {
        e.printStackTrace();
    }

    @Override
    public void onComplete() {

    }

    @VisibleForTesting
    public FcmFriendContract.View getView(){
        return this.view;
    }

    public void closeNotification(Context context) {
        view.closeNotification(context);
    }
}
