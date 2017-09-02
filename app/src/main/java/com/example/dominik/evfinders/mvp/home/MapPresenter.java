package com.example.dominik.evfinders.mvp.home;

import com.example.dominik.evfinders.base.BaseView;
import com.example.dominik.evfinders.database.pojo.Event;
import com.example.dominik.evfinders.model.base.home.IMapRepository;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

/**
 * Created by Dominik on 23.06.2017.
 */

public class MapPresenter implements MapContract.Presenter {

    private IMapRepository repository;
    private MapContract.View view;

    @Inject
    public MapPresenter(IMapRepository repository) {
        this.repository = repository;
    }


    @Override
    public void attach(MapContract.View view) {
        if (this.view == null) {
            this.view = view;
        }

    }

    @Override
    public void detach() {
        //// TODO: 01.09.2017 Null Object
        view = null;
    }

    @Override
    public void getEvents() {
        List<Event> events = repository.getEvents();
        view.showEvents(events);
    }

}
