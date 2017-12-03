package com.example.dominik.evfinders.application;

import android.content.Context;
import android.content.Intent;

import com.example.dominik.evfinders.application.services.FCMFriendService;
import com.example.dominik.evfinders.application.services.FcmService;
import com.example.dominik.evfinders.application.services.LocationService;
import com.example.dominik.evfinders.di.DaggerApplicationComponent;

import dagger.android.AndroidInjector;
import dagger.android.DaggerApplication;

/**
 * Created by Dominik on 22.06.2017.
 */

public class EvApplication extends DaggerApplication {

    private static Context context;


    public EvApplication() {
    }

    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
        startService(new Intent(this, FcmService.class));
        startService(new Intent(this, FCMFriendService.class));
        startService(new Intent(this, LocationService.class));
    }

    @Override
    protected AndroidInjector<? extends DaggerApplication> applicationInjector() {
        return DaggerApplicationComponent.builder().create(this);
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
    }

    public static Context getApplication() {
        return context;
    }
}
