package com.example.dominik.evfinders.di.component;

import com.example.dominik.evfinders.application.Authorization;
import com.example.dominik.evfinders.base.BaseAuthActivity;
import com.example.dominik.evfinders.di.module.AuthorizationModule;
import com.example.dominik.evfinders.di.scope.PerApplication;
import com.example.dominik.evfinders.module.home.MainActivity;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by Dominik on 22.06.2017.
 * @author Dominik
 * @since 1.0
 */
@PerApplication
@Component(modules = AuthorizationModule.class, dependencies = ApplicationComponent.class)
public interface AuthorizationComponent {

    void inject(MainActivity activity);
    void inject(BaseAuthActivity activity);

}
