package com.example.dominik.evfinders.di;

import com.example.dominik.evfinders.mvp.events.detail.EventDetailContract;
import com.example.dominik.evfinders.mvp.events.detail.EventDetailPresenter;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Dominik on 27.10.2017.
 */

@Module
abstract class EventDetailModule {

    @ActivityScope
    @Provides
    static EventDetailContract.Presenter providePresenter(){
        return new EventDetailPresenter();
    }
}
