package com.example.dominik.evfinders.di;

import com.example.dominik.evfinders.mvp.start.StartPresenter;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Dominik on 07.09.2017.
 */
@Module
public class StartActivityModule {

    @ActivityScope
    @Provides
    static StartPresenter providePresenter(){
        return new StartPresenter();
    }
}
