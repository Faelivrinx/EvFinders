package com.example.dominik.evfinders.mvp.home.create.event;

import com.example.dominik.evfinders.command.EventCommand;
import com.example.dominik.evfinders.database.pojo.network.TaskResponse;
import com.example.dominik.evfinders.model.base.home.event.IEventsRepository;

import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Response;

/**
 * Created by Dominik on 21.11.2017.
 */

public class CreateEventPresenter implements CreateEventContract.Presenter {

    private CreateEventContract.View view;
    private IEventsRepository eventsRepository;

    @Inject
    public CreateEventPresenter(IEventsRepository eventsRepository) {
        this.eventsRepository = eventsRepository;
    }

    @Override
    public boolean isValidData(EventCommand command) {
        if (command.getName().equals("")) {
            return false;
        } else if (command.getAddress().equals("")) {
            return false;
        } else if (command.getDescription().equals("")) {
            return false;
        } else if (command.getDate() < 0) {
            return false;
        } else if (command.getProfile().equals("")) {
            return false;
        }
        return true;
    }

    @Override
    public void attach(CreateEventContract.View view) {
        if (this.view == null) {
            this.view = view;
        }
    }

    @Override
    public void detach() {
        this.view = null;
    }

    @Override
    public void sendEvent(EventCommand command) {
        view.showProgressDialog("Tworzenie wydarzenia...");
        if (isValidData(command)) {
            Single<Response<TaskResponse>> event = eventsRepository.createEvent(command);
            event
                    .timeout(7, TimeUnit.SECONDS)
                    .subscribeOn(Schedulers.newThread())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(this::checkResponse, throwable -> {
                    });
        }
        else {
            view.hideProgressDialog();
        }
    }

    private void checkResponse(Response<TaskResponse> response) {
        view.hideProgressDialog();
        if (response.code() == 200) {
            view.onCreateEventSuccess();
        } else if (response.code() == 401) {
            view.onCreateEventFailed("Nie autoryzowana próba");
        } else {
            view.onCreateEventFailed("Blad przy tworzeniu wydarzenia " + response.errorBody());
        }
    }
}
