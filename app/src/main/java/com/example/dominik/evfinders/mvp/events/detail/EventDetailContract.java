package com.example.dominik.evfinders.mvp.events.detail;

import com.example.dominik.evfinders.command.EventCommand;

/**
 * Created by Dominik on 27.10.2017.
 */

public interface EventDetailContract {

    interface View {
        void getEvent();

        void showEvent(EventCommand event);

        void showProgressBar();

        void hideProgressBar();

        void onBackButtonClicked();

        void onShowCommentsButtonClicked();

        void onFriendsButtonClicked();

        void showMessage(String message);
    }

    interface Presenter {

        void attach(View view);

        void detach();

        void checkEvent(EventCommand event);

        void addComment(String comment, Long eventCommandId, int numStars);

        void attend(Long id);
    }
}
