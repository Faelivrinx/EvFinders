package com.example.dominik.evfinders.mvp.start;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.example.dominik.evfinders.mvp.login.LoginActivity;
import com.example.dominik.evfinders.mvp.register.RegisterActivity;
import com.google.firebase.iid.FirebaseInstanceId;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Dominik on 07.09.2017.
 */

public class StartPresenter implements StartContract.Presenter, Observer<String>{

    private StartContract.View view;

    @Override
    public void attach(StartContract.View view) {
        if (this.view == null) {
            this.view = view;
        }
    }

    @Override
    public void detach() {
        view = null;
    }

    @Override
    public void startRegisterActivity(Context context) {
        context.startActivity(new Intent(context, RegisterActivity.class));
    }

    @Override
    public void startLoginActivity(Context context) {
        context.startActivity(new Intent(context, LoginActivity.class));
    }

    @Override
    public void generateFCMToken() {
//        FirebaseInstanceId.getInstance().getToken();
//        Observable.fromArray(FirebaseInstanceId.getInstance().getToken())
//                .subscribeOn(Schedulers.newThread())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(this);
    }

    @Override
    public void onSubscribe(Disposable d) {

    }

    @Override
    public void onNext(String token) {
    }

    @Override
    public void onError(Throwable e) {

    }

    @Override
    public void onComplete() {

    }
}
