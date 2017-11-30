package com.example.dominik.evfinders.di;

import com.example.dominik.evfinders.model.base.home.event.IEventsRepository;
import com.example.dominik.evfinders.mvp.home.MapPresenter;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Dominik on 01.09.2017.
 */
@Module
abstract class MainActivityModule {

//    @ActivityScope
//    @Provides
//    static IEventsRepository repository(IPrefs prefs){
//        return new MockEventsRepository( prefs);
//    }

    @Provides
    static MapPresenter porovidePresenter(IEventsRepository repository){
        return new MapPresenter(repository);
    }
}
