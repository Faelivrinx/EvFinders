package com.example.dominik.evfinders.di;

import com.example.dominik.evfinders.mvp.home.MainActivity;
import com.example.dominik.evfinders.mvp.login.LoginActivity;
import com.example.dominik.evfinders.mvp.register.RegisterActivity;
import com.example.dominik.evfinders.mvp.start.StartActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * Created by Dominik on 01.09.2017.
 */
@Module
abstract class AndroidBindingModule {

    @ActivityScope
    @ContributesAndroidInjector(modules = {
            MainActivityModule.class
    })
    abstract MainActivity mapAcitivity();

    @ActivityScope
    @ContributesAndroidInjector(modules = {
            LoginActivityModule.class
    })
    abstract LoginActivity loginActivity();

    @ActivityScope
    @ContributesAndroidInjector(modules = {
            RegisterActivityModule.class
    })
    abstract RegisterActivity registerActivity();

    @ActivityScope
    @ContributesAndroidInjector(modules = {
            StartActivityModule.class
    })
    abstract StartActivity startActivity();
}
