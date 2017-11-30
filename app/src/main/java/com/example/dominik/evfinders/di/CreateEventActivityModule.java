package com.example.dominik.evfinders.di;

import com.example.dominik.evfinders.converters.DateConverter;
import com.example.dominik.evfinders.converters.DateConverterImpl;
import com.example.dominik.evfinders.converters.ProfileConverter;
import com.example.dominik.evfinders.converters.ProfileConverterImpl;
import com.example.dominik.evfinders.model.base.home.event.IEventsRepository;
import com.example.dominik.evfinders.mvp.home.create.event.CreateEventContract;
import com.example.dominik.evfinders.mvp.home.create.event.CreateEventPresenter;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Dominik on 22.11.2017.
 */
@Module
abstract class CreateEventActivityModule {

    @Provides
    static CreateEventContract.Presenter providePresenter(IEventsRepository repository) {
        return new CreateEventPresenter(repository);
    }

    @Provides
    static ProfileConverter provideProfileConterter() {
        return new ProfileConverterImpl();
    }

    @Provides
    static DateConverter provideDateConverter() {
        return new DateConverterImpl();
    }

}
