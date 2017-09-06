package com.example.dominik.evfinders.mvp.register;

import com.example.dominik.evfinders.database.pojo.network.ApiKeyResponse;
import com.example.dominik.evfinders.database.pojo.User;
import com.example.dominik.evfinders.database.pojo.network.UserRequest;
import com.example.dominik.evfinders.model.base.home.register.IRegisterRepository;

import java.io.EOFException;

import javax.inject.Inject;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Response;

/**
 * Created by Dominik on 06.09.2017.
 */

public class RegisterPresenter implements RegisterContract.Presenter, Observer<Response<ApiKeyResponse>> {

    private RegisterContract.View view;
    private IRegisterRepository repository;

    @Inject
    public RegisterPresenter(IRegisterRepository repository) {
        this.repository = repository;
    }

    @Override
    public void register(String username, String password, String email) {
        view.showProgressDialog();
        if (validateData(username, password, email)) {
            repository.getRegisterResponse(createUser(username, password, email))
                    .filter(apiKeyResponseResponse -> !apiKeyResponseResponse.message().isEmpty())
                    .subscribeOn(Schedulers.newThread())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(this);
        }
    }


    private UserRequest createUser(String username, String password, String email) {
        return new UserRequest(username, password, email);
    }

    @Override
    public boolean validateData(String username, String password, String email) {
        return true;
    }

    @Override
    public void attach(RegisterContract.View view) {
        if (this.view == null) {
            this.view = view;
        }
    }

    @Override
    public void detach() {
        this.view = null;
    }

    @Override
    public void onSubscribe(Disposable d) {

    }

    @Override
    public void onNext(Response<ApiKeyResponse> apiKeyResponseResponse) {
        if (apiKeyResponseResponse.code() == 200) {
            repository.saveKey(apiKeyResponseResponse.body());
            // TODO: 06.09.2017 start activity
        } else {
            view.showToast("Error: " + apiKeyResponseResponse.code());
        }
    }

    @Override
    public void onError(Throwable e) {
        view.hideProgressDialog();
        if (e instanceof EOFException) {
            view.showToast("Username already exist");
        } else {
            view.showToast("Error connection");

        }
    }

    @Override
    public void onComplete() {
        view.showToast("Completed");
        view.hideProgressDialog();
    }
}
