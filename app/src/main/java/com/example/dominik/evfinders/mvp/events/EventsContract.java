package com.example.dominik.evfinders.mvp.events;

import com.example.dominik.evfinders.command.EventCommand;
import com.example.dominik.evfinders.database.pojo.Event;

import java.util.List;

/**
 * Created by Dominik on 09.10.2017.
 */

public interface EventsContract {

    interface View {

        void showProgressBar();

        void hideProgressBar();

        void startActivity();

        void showEvents(List<EventCommand> events);

    }

    interface Presenter {

        void attach(View view);

        void detach();

        void getEvents();

        void logoutUser();

    }
}

