package com.example.dominik.evfinders.mvp.events;

/**
 * Created by Dominik on 09.10.2017.
 */

public interface EventsContract {

    interface View {

    }

    interface Presenter {

        void attach(View view);

        void detach();

    }
}

