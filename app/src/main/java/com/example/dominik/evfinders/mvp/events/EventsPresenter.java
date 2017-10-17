package com.example.dominik.evfinders.mvp.events;

import android.support.annotation.VisibleForTesting;

import com.example.dominik.evfinders.application.DeleteToken;
import com.example.dominik.evfinders.database.pojo.Event;
import com.example.dominik.evfinders.model.base.home.IEventsRepository;

import java.util.List;

import io.reactivex.Single;

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

    @Override
    public void getEvents() {
        Single<List<Event>> events = repository.getEvents();
        events.subscribe(
                eventsResponse -> view.showEvents(eventsResponse),
                throwable -> {});
    }


    @Override
    public void logoutUser() {
        new DeleteToken().execute();

        repository.removeFcmToken();
        view.showProgressBar();
        if (repository.removeUserKey()) {
            view.startActivity();
        }
    }

    @VisibleForTesting
    public EventsContract.View getView(){
        return this.view;
    }
}
