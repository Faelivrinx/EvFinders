package com.example.dominik.evfinders.di;

import com.example.dominik.evfinders.model.base.home.IEventsRepository;
import com.example.dominik.evfinders.model.base.home.MockEventsRepository;
import com.example.dominik.evfinders.model.repo.IPrefs;
import com.example.dominik.evfinders.mvp.events.EventsContract;
import com.example.dominik.evfinders.mvp.events.EventsPresenter;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Dominik on 09.10.2017.
 */

@Module
abstract class EventsActivityModule {

//    @ActivityScope
//    @Provides
//    static IEventsRepository provideRepository(IPrefs prefs){
//        return new MockEventsRepository(prefs);
//    }

    @ActivityScope
    @Provides
    static EventsPresenter providePresenter (IEventsRepository repository){
        return new EventsPresenter(repository);
    }
}
