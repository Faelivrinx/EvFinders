package com.example.dominik.evfinders.application;

import android.app.Application;
import android.content.Context;
import android.content.Intent;

import com.example.dominik.evfinders.di.DaggerApplicationComponent;
import com.example.dominik.evfinders.model.repo.IPrefs;
import com.example.dominik.evfinders.model.repo.Prefs;

import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.DaggerApplication;

/**
 * Created by Dominik on 22.06.2017.
 */

public class EvApplication extends DaggerApplication {


    public EvApplication() {
    }

    @Override
    public void onCreate() {
        super.onCreate();
        startService(new Intent(this, FcmService.class));
    }

    @Override
    protected AndroidInjector<? extends DaggerApplication> applicationInjector() {
        return DaggerApplicationComponent.builder().create(this);
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
    }


}
