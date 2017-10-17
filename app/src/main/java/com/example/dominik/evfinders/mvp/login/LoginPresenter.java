package com.example.dominik.evfinders.mvp.login;

import com.example.dominik.evfinders.database.pojo.network.ApiKeyResponse;
import com.example.dominik.evfinders.model.base.home.login.ILoginRepository;

import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Dominik on 05.09.2017.
 */

public class LoginPresenter implements LoginContract.Presenter, Observer<ApiKeyResponse> {

    private LoginContract.View view;
    private ILoginRepository repository;

    @Inject
    public LoginPresenter(ILoginRepository repository) {
        this.repository = repository;
    }

    @Override
    public void attach(LoginContract.View view) {
        if (this.view == null) {
            this.view = view;
        }
    }

    @Override
    public void detach() {
        view = null;
    }

    @Override
    public void login(String username, String password) {
        view.showProgressDialog();
        String fcmToken = repository.getFcmToken();
        if (!fcmToken.isEmpty()) {
            if (validateData(username, password)) {
                Observable<ApiKeyResponse> loginReponse = repository.getLoginResponse(username, password, fcmToken);
                loginReponse
                        .subscribeOn(Schedulers.newThread())
                        .observeOn(AndroidSchedulers.mainThread())
                        .timeout(10, TimeUnit.SECONDS)
                        .subscribe(this);
            } else {
                view.hideProgressDialog();
                view.showToast("Wrong data");
            }
        } else {
            view.hideProgressDialog();
            view.showToast("Refresh FCM key");
        }

    }

    @Override
    public boolean validateData(String username, String password) {
        return !(username.isEmpty() || password.isEmpty());
    }

    @Override
    public void onSubscribe(Disposable d) {
    }

    @Override
    public void onNext(ApiKeyResponse key) {
        repository.saveKey(key);
        // TODO: 05.09.2017 Start main activity
    }

    @Override
    public void onError(Throwable e) {
        e.printStackTrace();
        view.hideProgressDialog();
        view.showToast("Can't login");
    }

    @Override
    public void onComplete() {
        view.startActivity();
        view.hideProgressDialog();
    }
}
