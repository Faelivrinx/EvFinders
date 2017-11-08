package com.example.dominik.evfinders.di;

import com.example.dominik.evfinders.model.base.home.profile.IProfileRepository;
import com.example.dominik.evfinders.mvp.profile.ProfileContract;
import com.example.dominik.evfinders.mvp.profile.ProfilePresenter;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Dominik on 07.11.2017.
 */
@Module
abstract class ProfileModule {

    @Provides
    static ProfileContract.Presenter providePresenter(IProfileRepository repository){
        return new ProfilePresenter(repository);
    }
}
