package com.example.dominik.evfinders.di;

import com.example.dominik.evfinders.model.api.LoginService;
import com.example.dominik.evfinders.model.api.RegisterService;
import com.example.dominik.evfinders.model.base.home.login.ILoginRepository;
import com.example.dominik.evfinders.model.base.home.login.LoginRepository;
import com.example.dominik.evfinders.model.base.home.register.IRegisterRepository;
import com.example.dominik.evfinders.model.base.home.register.RegisterRepository;
import com.example.dominik.evfinders.model.repo.IPrefs;
import com.example.dominik.evfinders.mvp.login.LoginPresenter;
import com.example.dominik.evfinders.mvp.start.StartPresenter;
import com.example.dominik.evfinders.mvp.start_test.StartActivityTestPresenter;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

/**
 * Created by Dominik on 07.09.2017.
 */
@Module
abstract class StartActivityModule {

    @ActivityScope
    @Provides
    static LoginService provideService(@Named("non-auth") Retrofit retrofit){
        return retrofit.create(LoginService.class);
    }

    @ActivityScope
    @Provides
    static RegisterService provideServiceRegister(@Named("non-auth") Retrofit retrofit){
        return retrofit.create(RegisterService.class);
    }

    @ActivityScope
    @Provides
    static ILoginRepository provideRepository(IPrefs prefs, LoginService service){
        return new LoginRepository(prefs, service);
    }

    @ActivityScope
    @Provides
    static IRegisterRepository provideRepositoryRegister(IPrefs prefs, RegisterService service){
        return new RegisterRepository(prefs, service);
    }

    @ActivityScope
    @Provides
    static StartActivityTestPresenter providePresenter(ILoginRepository loginRepo, IRegisterRepository registerRepo){
        return new StartActivityTestPresenter(loginRepo, registerRepo);
    }
}
