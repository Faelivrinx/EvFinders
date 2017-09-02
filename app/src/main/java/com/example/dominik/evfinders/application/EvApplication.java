package com.example.dominik.evfinders.application;

import android.app.Application;
import android.content.Context;

import com.example.dominik.evfinders.di.DaggerApplicationComponent;

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
