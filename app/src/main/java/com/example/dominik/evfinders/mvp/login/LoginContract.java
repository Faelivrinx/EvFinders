package com.example.dominik.evfinders.mvp.login;

import com.example.dominik.evfinders.base.BasePresenter;
import com.example.dominik.evfinders.base.BaseView;

/**
 * Created by Dominik on 05.09.2017.
 */

public interface LoginContract {

    interface View extends BaseView {

        void showToast(String message);

        void hideProgressDialog();

        void showProgressDialog();

    }

    interface Presenter extends BasePresenter{

        void attach(View view);

        void detach();

        void login(String username, String password);

        boolean validateData(String username, String password);

    }

}
