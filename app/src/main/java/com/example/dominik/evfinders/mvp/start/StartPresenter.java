package com.example.dominik.evfinders.mvp.start;

import android.content.Context;
import android.content.Intent;

import com.example.dominik.evfinders.mvp.login.LoginActivity;
import com.example.dominik.evfinders.mvp.register.RegisterActivity;

/**
 * Created by Dominik on 07.09.2017.
 */

public class StartPresenter implements StartContract.Presenter {

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
}
