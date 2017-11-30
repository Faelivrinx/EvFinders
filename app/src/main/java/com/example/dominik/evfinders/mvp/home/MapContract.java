package com.example.dominik.evfinders.mvp.home;

import com.example.dominik.evfinders.base.BasePresenter;
import com.example.dominik.evfinders.command.CoordinateCommand;
import com.example.dominik.evfinders.command.EventCommand;
import com.example.dominik.evfinders.database.pojo.Event;
import com.example.dominik.evfinders.base.BaseView;
import com.google.android.gms.maps.model.LatLng;

import java.util.List;

/**
 * Created by Dominik on 26.08.2017.
 */

public interface MapContract {

    interface View extends BaseView {

        void showEvents(List<EventCommand> events);

        void onLogoutClicked();

        void showProgressBar();

        void hideProgressBar();

        void zoomMapToPosition(LatLng eventPosition);

        void startActivity();
    }

    interface Presenter extends BasePresenter {

        void attach(View view);

        void detach();

        void getEvents(CoordinateCommand coordinateCommand);

        void logoutUser();

    }

}
