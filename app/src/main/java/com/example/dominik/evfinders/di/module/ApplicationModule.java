package com.example.dominik.evfinders.di.module;

import android.content.Context;

import com.example.dominik.evfinders.di.scope.PerApplication;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Dominik on 22.06.2017.
 */
@Module
public class ApplicationModule {

    private Context context;

    public ApplicationModule(Context context) {
        this.context = context;
    }

    @PerApplication
    @Provides
    Context context(){
        return context;
    }
}
