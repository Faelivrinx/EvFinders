package com.example.dominik.evfinders.mvp.events;

import android.support.annotation.VisibleForTesting;

import com.example.dominik.evfinders.application.DeleteToken;
import com.example.dominik.evfinders.application.services.LocationService;
import com.example.dominik.evfinders.command.EventCommand;
import com.example.dominik.evfinders.database.pojo.Event;
import com.example.dominik.evfinders.model.base.home.event.IEventsRepository;

import java.util.List;

import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Response;

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
        Single<Response<List<EventCommand>>> events = repository.getEventsWithRecommendation(LocationService.LocationListener.getLastKnowCoordinate());
        events
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                this::checkResponse,
                throwable -> {});
    }

    private void checkResponse(Response<List<EventCommand>> eventsResponse) {
        if (eventsResponse.isSuccessful()){
            view.showEvents(eventsResponse.body());
        }
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
