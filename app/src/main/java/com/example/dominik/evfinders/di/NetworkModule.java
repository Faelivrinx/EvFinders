package com.example.dominik.evfinders.di;

import com.example.dominik.evfinders.model.repo.IPrefs;

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
public class NetworkModule {

    @Provides
    @Named("non-auth")
    static OkHttpClient provideOkhttpClientNonAuth(){
        return new OkHttpClient.Builder()
                .readTimeout(20, TimeUnit.SECONDS)
                .writeTimeout(20, TimeUnit.SECONDS)
                .build();
    }

    @Provides
    @Named("auth")
    static OkHttpClient provideOkhttpClientAuth(IPrefs prefs){
        return new OkHttpClient.Builder()
                .readTimeout(20, TimeUnit.SECONDS)
                .writeTimeout(20, TimeUnit.SECONDS)
                .addInterceptor(chain -> {
                    Request request = chain.request();

                    request.newBuilder().addHeader("Authorization", prefs.get("API_KEY"));

                    return chain.proceed(request);
                })
                .build();
    }

    @Provides
    static RxJava2CallAdapterFactory adapterFactory(){
        return RxJava2CallAdapterFactory.create();
    }

    @Provides
    static GsonConverterFactory gsonConverterFactory(){
        return GsonConverterFactory.create();
    }

    @Provides
    @Named("non-auth")
    static Retrofit provideRetrofitNonAuth(@Named("non-auth") OkHttpClient client, GsonConverterFactory gsonConverterFactory, RxJava2CallAdapterFactory adapterFactory){
        return new Retrofit.Builder()
                .baseUrl("http://jurasz-dev.pl/login.php/")
                .addConverterFactory(gsonConverterFactory)
                .addCallAdapterFactory(adapterFactory)
                .client(client)
                .build();
    }

    @Provides
    @Named("auth")
    static Retrofit provideRetrofitAuth(@Named("auth") OkHttpClient client, GsonConverterFactory gsonConverterFactory, RxJava2CallAdapterFactory adapterFactory){
        return new Retrofit.Builder()
                .baseUrl("http://jurasz-dev.pl/login.php/")
                .addConverterFactory(gsonConverterFactory)
                .addCallAdapterFactory(adapterFactory)
                .client(client)
                .build();
    }
}
