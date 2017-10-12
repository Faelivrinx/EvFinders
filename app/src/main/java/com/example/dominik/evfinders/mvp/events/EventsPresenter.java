package com.example.dominik.evfinders.mvp.events;

import com.example.dominik.evfinders.model.base.home.IEventsRepository;

/**
 * Created by Dominik on 09.10.2017.
 */

public class EventsPresenter implements EventsContract.Presenter {
    private EventsContract.View view;
    private IEventsRepository repository;


    public EventsPresenter(IEventsRepository repository) {
        this.repository = repository;
    }



    @Override
    public void attach(EventsContract.View view) {
        if (this.view == null) {
            this.view = view;
        }
    }

    @Override
    public void detach() {
        view = null;
    }
}
