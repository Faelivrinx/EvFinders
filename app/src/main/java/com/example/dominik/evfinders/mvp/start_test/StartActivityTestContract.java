package com.example.dominik.evfinders.mvp.start_test;

import com.example.dominik.evfinders.mvp.login.LoginContract;

/**
 * Created by Dominik on 11.10.2017.
 */

public interface    StartActivityTestContract {

    interface View{
        void showToast(String message);

        void hideProgressDialog();

        void showProgressDialog(String message);

        void startActivity();

        void setLoginStatus(boolean isReady);

    }

    interface Presenter{

        void attach(StartActivityTestContract.View view);

        void detach();

        void login(String username, String password);

        void register(String username, String password, String email);

        boolean validateData(String username, String password);

        boolean validateData(String username, String password, String email);

        void logoutUser();

        void checkLoginState();

    }
}
