package com.example.dominik.evfinders.di;

import com.example.dominik.evfinders.converters.ProfileConverter;
import com.example.dominik.evfinders.converters.ProfileConverterImpl;
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
    static ProfileConverter provideConverter(){
        return new ProfileConverterImpl();
    }

    @Provides
    static ProfileContract.Presenter providePresenter(IProfileRepository repository, ProfileConverter converter){
        return new ProfilePresenter(repository, converter);
    }
}
