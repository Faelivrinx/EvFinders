package com.example.dominik.evfinders.mvp.login;

import com.example.dominik.evfinders.model.base.home.login.ILoginRepository;

import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Dominik on 05.09.2017.
 */

public class LoginPresenter implements LoginContract.Presenter, Observer<String> {

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
        if (validateData(username, password)) {
            Observable<String> loginReponse = repository.getLoginReponse();
            loginReponse.subscribeOn(Schedulers.newThread())
                    .observeOn(AndroidSchedulers.mainThread())
                    .filter(s -> s != null)
                    .timeout(10, TimeUnit.SECONDS)
                    .subscribe(this);
        } else {
            view.hideProgressDialog();
            view.showToast("Wrong data");
        }

    }

    @Override
    public boolean validateData(String username, String password) {
        return !(username.isEmpty() || password.isEmpty());
    }

    @Override
    public void onSubscribe(Disposable d) {
        view.showToast("Getting key");
    }

    @Override
    public void onNext(String key) {
        repository.saveKey(key);
        // TODO: 05.09.2017 Start main activity
    }

    @Override
    public void onError(Throwable e) {
        view.hideProgressDialog();
        view.showToast("Error Connection");
    }

    @Override
    public void onComplete() {
        view.hideProgressDialog();
        view.showToast("Complete");
    }
}
