package com.example.dominik.evfinders.mvp.register;

import android.content.Intent;

import com.example.dominik.evfinders.mvp.login.LoginContract;

/**
 * Created by Dominik on 06.09.2017.
 */

public interface RegisterContract {

    interface View {

        void showToast(String message);

        void hideProgressDialog();

        void showProgressDialog();

        void startActivity();
    }

    interface Presenter {

        void attach(RegisterContract.View view);

        void detach();

        void register(String username, String password, String email);

        boolean validateData(String username, String password, String email);
    }
}
