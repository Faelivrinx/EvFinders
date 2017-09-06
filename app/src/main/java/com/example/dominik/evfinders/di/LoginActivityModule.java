package com.example.dominik.evfinders.di;

import com.example.dominik.evfinders.model.api.LoginService;
import com.example.dominik.evfinders.model.base.home.login.ILoginRepository;
import com.example.dominik.evfinders.model.base.home.login.LoginRepository;
import com.example.dominik.evfinders.model.repo.IPrefs;
import com.example.dominik.evfinders.mvp.login.LoginPresenter;

import java.util.concurrent.TimeUnit;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Dominik on 05.09.2017.
 */

@Module
abstract class LoginActivityModule {


    @ActivityScope
    @Provides
     static LoginService provideService(@Named("non-auth") Retrofit retrofit){
        return retrofit.create(LoginService.class);
    }

    @ActivityScope
    @Provides
    static ILoginRepository provideRepository(IPrefs prefs, LoginService service){
        return new LoginRepository(prefs, service);
    }

    @ActivityScope
    @Provides
    static LoginPresenter providePresenter(ILoginRepository repository){
        return new LoginPresenter(repository);
    }


}
