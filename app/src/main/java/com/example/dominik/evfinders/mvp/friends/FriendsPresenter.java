package com.example.dominik.evfinders.mvp.friends;

import com.example.dominik.evfinders.application.DeleteToken;
import com.example.dominik.evfinders.database.pojo.Friend;
import com.example.dominik.evfinders.database.pojo.network.FriendResponse;
import com.example.dominik.evfinders.database.pojo.network.TaskResponse;
import com.example.dominik.evfinders.model.base.home.friends.IFriendsRepository;
import com.example.dominik.evfinders.model.base.home.login.ILoginRepository;

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
    private ILoginRepository loginRepository;

    @Inject
    public FriendsPresenter(IFriendsRepository repository, ILoginRepository loginRepository) {
        this.loginRepository = loginRepository;
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
    public void addFriend(String username) {
        repository.addFriendsRequest(username)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::checkTaskResponse, Throwable::printStackTrace);
    }

    @Override
    public void deleteFriends(List<Friend> friends) {
        List<String> usernames = getUsernames(friends);
        repository.delFriends(usernames)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::checkTaskResponse, Throwable::printStackTrace);
    }

    private List<String> getUsernames(List<Friend> friends) {
        List<String> usernames = new ArrayList<>();
        for (Friend friend : friends) {
            usernames.add(friend.getUsername());
        }
        return usernames;
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
            view.showToast("Brak autoryzacji");
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
        view.showToast("Pobrano znajomych");
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

    private void checkTaskResponse(Response<TaskResponse> response) {
        if (response.code() == 200) {
            if (response.body().getValue().equals("success")) {
                view.showToast("Task success!");
                view.onFriendsDeleted();
                getFriendsList();
            } else {
                view.showToast("Something went wrong!");
            }
        } else {
            view.showToast("Something went wrong!");
        }
    }


    @Override
    public void logoutUser() {
        new DeleteToken().execute();

        loginRepository.removeFcmToken();
//        view.showProgressBar();
        if (loginRepository.removeUserKey()) {
            view.startActivity();
        }
    }
}
