package com.example.dominik.evfinders.mvp.start_test;

import com.example.dominik.evfinders.application.DeleteToken;
import com.example.dominik.evfinders.database.pojo.network.ApiKeyResponse;
import com.example.dominik.evfinders.database.pojo.network.UserRequest;
import com.example.dominik.evfinders.model.base.home.login.ILoginRepository;
import com.example.dominik.evfinders.model.base.home.register.IRegisterRepository;

import java.io.EOFException;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Dominik on 11.10.2017.
 */

public class StartActivityTestPresenter implements StartActivityTestContract.Presenter, Observer<ApiKeyResponse> {

    private StartActivityTestContract.View view;
    private ILoginRepository loginRepo;
    private IRegisterRepository registerRepo;

    @Inject
    public StartActivityTestPresenter(ILoginRepository loginRepo, IRegisterRepository registerRepo) {
        this.loginRepo = loginRepo;
        this.registerRepo = registerRepo;
    }

    @Override
    public void attach(StartActivityTestContract.View view) {
        if (this.view == null){
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
        String fcmToken = loginRepo.getFcmToken();
        if (!fcmToken.isEmpty()) {
            if (validateData(username, password)) {
                Observable<ApiKeyResponse> loginReponse = loginRepo.getLoginResponse(username, password, fcmToken);
                loginReponse.subscribeOn(Schedulers.newThread())
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
    public void register(String username, String password, String email) {
        view.showProgressDialog();
        if (validateData(username, password, email)) {
            registerRepo.getRegisterResponse(createUser(username, password, email))
                    .filter(apiKeyResponseResponse -> !apiKeyResponseResponse.message().isEmpty())
                    .timeout(10, TimeUnit.SECONDS)
                    .subscribeOn(Schedulers.newThread())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(apiKeyResponseResponse -> {
                        if (apiKeyResponseResponse.code() == 200) {
                            registerRepo.saveKey(apiKeyResponseResponse.body());
                            // TODO: 06.09.2017 start activity
                        } else {
                            view.showToast("Error: " + apiKeyResponseResponse.code());
                        }
                    }, throwable -> {
                        view.hideProgressDialog();
                        if (throwable instanceof EOFException) {
                            view.showToast("Username already exist");
                        } else {
                            view.showToast("Error connection");

                        }
                    }, () -> {
                        view.startActivity();
                        view.hideProgressDialog();
                    });
        }
    }

    @Override
    public boolean validateData(String username, String password) {
        return !(username.isEmpty() || password.isEmpty());
    }

    @Override
    public boolean validateData(String username, String password, String email) {
        return true;
    }

    @Override
    public void onSubscribe(Disposable d) {

    }

    private UserRequest createUser(String username, String password, String email) {
        return new UserRequest(username, password, email);
    }

    @Override
    public void onNext(ApiKeyResponse apiKeyResponse) {
        loginRepo.saveKey(apiKeyResponse);
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

    @Override
    public void logoutUser() {
        new DeleteToken().execute();

        loginRepo.removeFcmToken();
        view.showProgressDialog();
        if (loginRepo.removeUserKey()) {
            view.startActivity();
        }
    }
}
