package com.example.dominik.evfinders.mvp.home;

import com.example.dominik.evfinders.base.BaseView;
import com.example.dominik.evfinders.database.pojo.Event;
import com.example.dominik.evfinders.model.base.home.IMapRepository;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observer;
import io.reactivex.Single;
import io.reactivex.SingleObserver;
import io.reactivex.disposables.Disposable;

/**
 * Created by Dominik on 23.06.2017.
 */

public class MapPresenter implements MapContract.Presenter, SingleObserver<List<Event>> {

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
        Single<List<Event>> events = repository.getEvents();
        events.subscribe(this);
    }

    @Override
    public void logoutUser() {
        view.showProgressBar();
        if (repository.removeUserKey()){
            view.startActivity();
        }
    }

    @Override
    public void onSubscribe(Disposable d) {
        view.showProgressBar();
    }

    @Override
    public void onSuccess(List<Event> events) {
        view.showEvents(events);
    }

    @Override
    public void onError(Throwable e) {
        view.hideProgressBar();
    }
}
