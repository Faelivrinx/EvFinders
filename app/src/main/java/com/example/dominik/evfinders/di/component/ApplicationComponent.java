package com.example.dominik.evfinders.di.component;

import android.content.Context;

import com.example.dominik.evfinders.di.module.ApplicationModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by Dominik on 22.06.2017.
 */
@Singleton
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {

    Context exposeContext();

}
