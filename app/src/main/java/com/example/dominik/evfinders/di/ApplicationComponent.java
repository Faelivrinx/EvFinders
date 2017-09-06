package com.example.dominik.evfinders.di;

import com.example.dominik.evfinders.application.EvApplication;
import com.example.dominik.evfinders.model.repo.Prefs;

import dagger.Component;
import dagger.android.AndroidInjector;
import dagger.android.support.AndroidSupportInjectionModule;

/**
 * Created by Dominik on 01.09.2017.
 */

@Component(modules ={
        ApplicationModule.class,
        PrefsModule.class,
        NetworkModule.class,
        AndroidSupportInjectionModule.class,
        AndroidBindingModule.class
})
public interface ApplicationComponent extends AndroidInjector<EvApplication> {

    @Component.Builder
    abstract class Builder extends AndroidInjector.Builder<EvApplication>{

    }
}
