package com.example.dominik.evfinders.di;

import com.example.dominik.evfinders.model.api.RegisterService;
import com.example.dominik.evfinders.model.base.home.register.IRegisterRepository;
import com.example.dominik.evfinders.model.base.home.register.RegisterRepository;
import com.example.dominik.evfinders.model.repo.IPrefs;
import com.example.dominik.evfinders.mvp.register.RegisterPresenter;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

/**
 * Created by Dominik on 06.09.2017.
 */

@Module
public class RegisterActivityModule {

    @ActivityScope
    @Provides
    static RegisterService provideRegisterService(@Named("non-auth")Retrofit retrofit){
        return retrofit.create(RegisterService.class);
    }

    @ActivityScope
    @Provides
    static IRegisterRepository provideRepository(IPrefs prefs, RegisterService service){
        return new RegisterRepository(prefs, service);
    }

    @ActivityScope
    @Provides
    static RegisterPresenter providePresenter(IRegisterRepository repository){
        return new RegisterPresenter(repository);
    }
}
