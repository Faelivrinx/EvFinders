package com.example.dominik.evfinders.di;

import com.example.dominik.evfinders.model.api.LoginService;
import com.example.dominik.evfinders.model.base.home.login.ILoginRepository;
import com.example.dominik.evfinders.model.base.home.login.LoginRepository;
import com.example.dominik.evfinders.model.repo.IPrefs;
import com.example.dominik.evfinders.mvp.login.LoginPresenter;

import java.util.concurrent.TimeUnit;

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
    static OkHttpClient provideOkhttpClient(IPrefs prefs){
        return new OkHttpClient.Builder()
                .readTimeout(20, TimeUnit.SECONDS)
                .writeTimeout(20, TimeUnit.SECONDS)
                .addInterceptor(chain -> {
                    Request request = chain.request();

                    request.newBuilder().addHeader("Authorization", "d0763edaa9d9bd2a9516280e9044d885");

                    return chain.proceed(request);
                })
                .build();
    }

    @ActivityScope
    @Provides
    static RxJava2CallAdapterFactory adapterFactory(){
        return RxJava2CallAdapterFactory.create();
    }
    @ActivityScope
    @Provides
    static GsonConverterFactory gsonConverterFactory(){
        return GsonConverterFactory.create();
    }

    @ActivityScope
    @Provides
    static Retrofit provideRetrofit(OkHttpClient client, GsonConverterFactory gsonConverterFactory, RxJava2CallAdapterFactory adapterFactory){
        return new Retrofit.Builder()
                .baseUrl("http://jurasz-dev.pl/index.php/")
                .addConverterFactory(gsonConverterFactory)
                .addCallAdapterFactory(adapterFactory)
                .client(client)
                .build();
    }

    @ActivityScope
    @Provides
     static LoginService provideService(Retrofit retrofit){
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
