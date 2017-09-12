package com.example.dominik.evfinders.application.services;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import com.example.dominik.evfinders.model.repo.IPrefs;
import com.example.dominik.evfinders.model.repo.Prefs;

import javax.inject.Inject;

import dagger.android.AndroidInjection;
import io.reactivex.disposables.Disposable;

/**
 * Created by Dominik on 11.09.2017.
 */

public class FcmService extends Service {

    @Inject
    IPrefs prefs;

    private Disposable disposable;

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        disposable = FirebaseInstanceIdService.GET_TOKEN_SUBJECT()
                .subscribe(this::savePrefs, Throwable::printStackTrace);
        return START_NOT_STICKY;
    }

    private void savePrefs(String token) {
        prefs.save(Prefs.FCM_TOKEN, token);
    }

    @Override
    public void onCreate() {
        AndroidInjection.inject(this);
        super.onCreate();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onDestroy() {
        disposable.dispose();
        super.onDestroy();
    }
}
