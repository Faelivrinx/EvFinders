package com.example.dominik.evfinders.di;

import com.example.dominik.evfinders.model.repo.IPrefs;
import com.example.dominik.evfinders.mvp.settings.SettingsContract;
import com.example.dominik.evfinders.mvp.settings.SettingsPresenter;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Dominik on 13.12.2017.
 */

@Module
abstract class SettingsActivityModule {

    @Provides
    static SettingsContract.Presenter providePresenter(IPrefs prefs){
        return new SettingsPresenter(prefs);
    }
}
