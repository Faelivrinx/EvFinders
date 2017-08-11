package com.example.dominik.evfinders.di.component;

import android.content.Context;

import com.example.dominik.evfinders.base.BaseAuthActivity;
import com.example.dominik.evfinders.di.module.ApplicationModule;
import com.example.dominik.evfinders.di.module.AuthorizationModule;
import com.example.dominik.evfinders.di.scope.PerApplication;
import com.example.dominik.evfinders.module.home.MainActivity;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by Dominik on 22.06.2017.
 */
@PerApplication
@Component(modules = {ApplicationModule.class, AuthorizationModule.class})
public interface ApplicationComponent {

    Context exposeContext();
    void inject(MainActivity mainActivity);
    void inject(BaseAuthActivity mainActivity);

}
