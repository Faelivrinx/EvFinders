package com.example.dominik.evfinders.di.module;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.dominik.evfinders.application.Authorization;
import com.example.dominik.evfinders.di.scope.PerApplication;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

import static com.example.dominik.evfinders.application.Authorization.AUTH_USER;

/**
 * Created by Dominik on 22.06.2017.
 * @author Dominik
 * @since 1.0
 */
@Module
public class AuthorizationModule {

    @PerApplication
    @Provides
    SharedPreferences provideSharedPreferences(Context context){
        return context.getSharedPreferences(AUTH_USER, Context.MODE_PRIVATE);
    }

    @PerApplication
    @Provides
    Authorization provideAuthorization(SharedPreferences sharedPreferences){
        return new Authorization(sharedPreferences);
    }


}
