package com.example.dominik.evfinders.mvp.events.detail;

import com.example.dominik.evfinders.database.pojo.Event;

/**
 * Created by Dominik on 27.10.2017.
 */

public interface EventDetailContract {

    interface View {
        void getEvent();

        void showEvent(Event event);

        void showProgressBar();

        void hideProgressBar();

        void onBackButtonClicked();

        void onShowCommentsButtonClicked();

        void onFriendsButtonClicked();
    }

    interface Presenter {

        void attach(View view);

        void detach();

        void checkEvent(Event event);
    }
}
