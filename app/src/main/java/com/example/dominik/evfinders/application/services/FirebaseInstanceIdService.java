package com.example.dominik.evfinders.application.services;

import android.util.Log;

import com.example.dominik.evfinders.model.repo.IPrefs;
import com.example.dominik.evfinders.model.repo.Prefs;
import com.google.firebase.iid.FirebaseInstanceId;

import java.io.IOException;

import javax.inject.Inject;

import dagger.android.AndroidInjection;
import io.reactivex.Observable;
import io.reactivex.subjects.PublishSubject;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

/**
 * Created by Dominik on 11.09.2017.
 */

public class FirebaseInstanceIdService extends com.google.firebase.iid.FirebaseInstanceIdService {

    private static PublishSubject<String> publishSubject = PublishSubject.create();

    @Override
    public void onTokenRefresh() {
        String token = FirebaseInstanceId.getInstance().getToken();
        publishSubject.onNext(token);

    }

    public static Observable<String> GET_TOKEN_SUBJECT(){
        return publishSubject;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
