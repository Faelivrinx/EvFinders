package com.example.dominik.evfinders.mvp.home;

import com.example.dominik.evfinders.base.BasePresenter;
import com.example.dominik.evfinders.database.pojo.Event;
import com.example.dominik.evfinders.base.BaseView;

import java.util.List;

/**
 * Created by Dominik on 26.08.2017.
 */

public interface MapContract {

    interface View extends BaseView {

        void showEvents(List<Event> events);

        void showProgressBar();

        void hideProgressBar();
    }

    interface Presenter extends BasePresenter {

        void attach(View view);

        void detach();

        void getEvents();

    }

}
