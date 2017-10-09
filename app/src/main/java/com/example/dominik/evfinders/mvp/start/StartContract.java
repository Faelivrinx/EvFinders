package com.example.dominik.evfinders.mvp.start;

import android.content.Context;

/**
 * Created by Dominik on 07.09.2017.
 */

public interface StartContract {

    interface View {

        void onRegisterClick();
        void onLoginClick();
        void showToast(String message);
        void showDialog(String message);
        void hideDialog();

    }

    interface Presenter {

        void attach(View view);
        void detach();
        void startRegisterActivity(Context context);
        void startLoginActivity(Context context);
        void generateFCMToken();
    }
}
