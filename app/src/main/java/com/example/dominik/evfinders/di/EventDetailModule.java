package com.example.dominik.evfinders.di;

import com.example.dominik.evfinders.model.base.home.comment.ICommentRepository;
import com.example.dominik.evfinders.model.base.home.event.IEventsRepository;
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
    static EventDetailContract.Presenter providePresenter(ICommentRepository commentRepository, IEventsRepository eventsRepository){
        return new EventDetailPresenter(commentRepository, eventsRepository);
    }
}
