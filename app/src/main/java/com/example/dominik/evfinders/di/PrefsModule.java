package com.example.dominik.evfinders.di;

import android.content.Context;

import com.example.dominik.evfinders.model.repo.IPrefs;
import com.example.dominik.evfinders.model.repo.Prefs;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Dominik on 05.09.2017.
 */
@Module
abstract class PrefsModule {

    @Provides
    static IPrefs providePrefs(Context application){
        return new Prefs(application);
    }
}
