package com.example.dominik.evfinders.mvp.home;

import android.util.Log;

import com.example.dominik.evfinders.application.DeleteToken;
import com.example.dominik.evfinders.command.CoordinateCommand;
import com.example.dominik.evfinders.command.EventCommand;
import com.example.dominik.evfinders.database.pojo.Event;
import com.example.dominik.evfinders.model.base.home.event.IEventsRepository;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Response;

/**
 * Created by Dominik on 23.06.2017.
 */

public class MapPresenter implements MapContract.Presenter {

    private IEventsRepository repository;
    private MapContract.View view;

    @Inject
    public MapPresenter(IEventsRepository repository) {
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
        view = null;
    }

    @Override
    public void getEvents(CoordinateCommand coordinateCommand) {
        view.showProgressBar();
        Single<Response<List<EventCommand>>> events = repository.getEvents(coordinateCommand);
        events
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::checkResponse, throwable -> {
            Log.d("TAG", "getEvents: " + throwable.getMessage());
        });

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

    public MapContract.View getView(){
        return this.view;
    }

    private void checkResponse(Response<List<EventCommand>> response){
        view.hideProgressBar();
        if (response.code() == 200){
            view.showEvents(response.body());
        } else {

        }
    }

}
