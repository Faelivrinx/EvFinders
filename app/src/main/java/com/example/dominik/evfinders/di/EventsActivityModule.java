package com.example.dominik.evfinders.di;

import com.example.dominik.evfinders.model.base.home.IEventsRepository;
import com.example.dominik.evfinders.mvp.events.EventsContract;
import com.example.dominik.evfinders.mvp.events.EventsPresenter;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Dominik on 09.10.2017.
 */

@Module
abstract class EventsActivityModule {

    @Provides
    static EventsPresenter presenter (IEventsRepository repository){
        return new EventsPresenter(repository);
    }
}
