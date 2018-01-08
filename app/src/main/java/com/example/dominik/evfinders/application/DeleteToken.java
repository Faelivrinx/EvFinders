package com.example.dominik.evfinders.application;

import android.os.AsyncTask;

import com.example.dominik.evfinders.application.services.FirebaseInstanceIdService;
import com.google.firebase.iid.FirebaseInstanceId;

import java.io.IOException;

/**
 * Created by Dominik on 11.09.2017.
 */

public class DeleteToken extends AsyncTask<Void, String, Void> {

    @Override
    protected Void doInBackground(Void... voids) {
        try {
            FirebaseInstanceId.getInstance().deleteInstanceId();
            FirebaseInstanceId.getInstance().getToken();
        } catch (IOException e) {
            FirebaseInstanceIdService.GET_TOKEN_SUBJECT().doOnError(throwable -> {});
            e.printStackTrace();
        }
        return null;
    }

}
