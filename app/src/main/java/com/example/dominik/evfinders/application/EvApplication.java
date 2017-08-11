package com.example.dominik.evfinders.application;

import android.app.Application;

import com.example.dominik.evfinders.di.component.ApplicationComponent;
import com.example.dominik.evfinders.di.component.DaggerApplicationComponent;
import com.example.dominik.evfinders.di.module.ApplicationModule;
import com.example.dominik.evfinders.di.module.AuthorizationModule;

import javax.inject.Inject;

/**
 * Created by Dominik on 22.06.2017.
 */

public class EvApplication extends Application {

    private static ApplicationComponent applicationComponent;

    @Inject
    public EvApplication() {
    }

    @Override
    public void onCreate() {
        super.onCreate();
        initializeApplicationComponent();
    }

    private void initializeApplicationComponent() {
        applicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .authorizationModule(new AuthorizationModule())
                .build();
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
    }

    public static ApplicationComponent getApplicationComponent() {
        return applicationComponent;
    }
}
