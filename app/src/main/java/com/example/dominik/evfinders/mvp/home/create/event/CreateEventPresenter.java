package com.example.dominik.evfinders.mvp.home.create.event;

import com.example.dominik.evfinders.application.recommendation.Recommendation;
import com.example.dominik.evfinders.command.EventCommand;
import com.example.dominik.evfinders.converters.ProfileConverter;
import com.example.dominik.evfinders.converters.ProfileConverterImpl;
import com.example.dominik.evfinders.database.pojo.network.TaskResponse;
import com.example.dominik.evfinders.model.base.home.event.IEventsRepository;

import java.net.ConnectException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

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
        } else if (command.getProfile().equals("{\"25\":0,\"10\":0,\"5\":0,\"14\":0,\"2\":0,\"19\":0,\"13\":0,\"8\":0,\"27\":0,\"6\":0,\"16\":0,\"28\":0,\"7\":0,\"4\":0,\"20\":0,\"17\":0,\"3\":0,\"22\":0,\"1\":0,\"29\":0,\"11\":0,\"9\":0,\"21\":0,\"24\":0,\"26\":0,\"15\":0,\"30\":0,\"18\":0}")) {
            view.showToast("Uzupełnij informację o profilu");
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
                    .subscribe(this::checkResponse, throwable -> checkError(throwable));
        }
        else {
            view.hideProgressDialog();
        }
    }

    private void checkError(Throwable throwable) {
        if (throwable instanceof ConnectException)
            view.showToast("Brak połączenia z serwerem");
        else if(throwable instanceof TimeoutException)
            view.showToast("Zbyt dlugie oczekiwanie na odpowiedź");

        view.hideProgressDialog();
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
