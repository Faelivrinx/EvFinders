package com.example.dominik.evfinders.di;

import com.example.dominik.evfinders.model.base.home.IMapRepository;
import com.example.dominik.evfinders.model.base.home.MockMapRepository;
import com.example.dominik.evfinders.mvp.home.MapPresenter;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Dominik on 01.09.2017.
 */
@Module
abstract class MainActivityModule {

    @ActivityScope
    @Provides
    static IMapRepository repository(){
        return new MockMapRepository();
    }

    @ActivityScope
    @Provides
    static MapPresenter porovidePresenter(IMapRepository repository){
        return new MapPresenter(repository);
    }
}
