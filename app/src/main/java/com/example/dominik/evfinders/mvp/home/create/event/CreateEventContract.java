package com.example.dominik.evfinders.mvp.home.create.event;

import com.example.dominik.evfinders.command.EventCommand;
import com.example.dominik.evfinders.database.pojo.network.TaskResponse;

/**
 * Created by Dominik on 21.11.2017.
 */

public interface CreateEventContract {

    interface View {

        void onSubmitClicked();

        void onCancelClicked();

        void onDateClicked();

        void onTimeClicked();

        void onCreateEventSuccess();

        void onCreateEventFailed(String failedMessage);

        void showProgressDialog(String message);

        void hideProgressDialog();

        void showToast(String message);

    }

    interface Presenter {

        boolean isValidData(EventCommand command);

        void attach(View view);

        void detach();

        void sendEvent(EventCommand command);

    }
}
