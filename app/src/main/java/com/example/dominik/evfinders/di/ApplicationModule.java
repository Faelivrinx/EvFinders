package com.example.dominik.evfinders.di;

import android.app.Application;
import android.content.Context;

import com.example.dominik.evfinders.application.EvApplication;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;

/**
 * Created by Dominik on 01.09.2017.
 */
@Module
abstract class ApplicationModule {

    @Binds
    abstract Application application(EvApplication application);

    @Provides
    static Context provideContext(Application application) {
        return application.getApplicationContext();
    }

}
