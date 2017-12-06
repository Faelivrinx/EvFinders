package com.example.dominik.evfinders.mvp.friends;

import com.example.dominik.evfinders.database.pojo.Friend;

import java.util.List;

/**
 * Created by Dominik on 11.09.2017.
 */

public interface FriendsContract {

    interface View {
        void showToast(String message);
        void showDialog(String message);
        void hideDialog();
        void onFriendsLoaded(List<Friend> friends);
        void startActivity();

        void onFriendsDeleted();
    }

    interface Presenter {
        void attach(View view);
        void detach();
        void getFriendsList();
        void addFriend(String username);
        void deleteFriends(List<Friend> friends);
        void logoutUser();
    }
}
