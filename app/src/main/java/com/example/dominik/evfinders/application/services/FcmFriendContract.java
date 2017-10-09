package com.example.dominik.evfinders.application.services;

import android.content.Context;

/**
 * Created by Dominik on 12.09.2017.
 */

public interface FcmFriendContract {

    interface View{
        void closeNotification(Context context);
    }

    interface Presenter{
        void attach(View view);
        void detach();
        void addFriend(String username, Context context);
    }
}
