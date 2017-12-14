package com.example.dominik.evfinders.di;

import com.example.dominik.evfinders.application.services.FcmService;
import com.example.dominik.evfinders.application.services.FCMFriendService;
import com.example.dominik.evfinders.mvp.events.EventsActivity;
import com.example.dominik.evfinders.mvp.events.detail.EventDetailActivity;
import com.example.dominik.evfinders.mvp.friends.FriendsListActivity;
import com.example.dominik.evfinders.mvp.home.MainActivity;
import com.example.dominik.evfinders.mvp.home.create.event.CreateEventActivity;
import com.example.dominik.evfinders.mvp.login.LoginActivity;
import com.example.dominik.evfinders.mvp.profile.ProfileActivity;
import com.example.dominik.evfinders.mvp.register.RegisterActivity;
import com.example.dominik.evfinders.mvp.settings.SettingsActivity;
import com.example.dominik.evfinders.mvp.start_test.StartActivityTest;

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
    abstract StartActivityTest startActivity();

    @ActivityScope
    @ContributesAndroidInjector(modules = {
            FriendsActivityModule.class
    })
    abstract FriendsListActivity friendsActivity();

    @ActivityScope
    @ContributesAndroidInjector
    abstract FcmService fcmService();

    @ActivityScope
    @ContributesAndroidInjector(modules = {
            FriendsServiceModule.class
    })
    abstract FCMFriendService friendsService();

    @ActivityScope
    @ContributesAndroidInjector(modules = {
            EventsActivityModule.class
    })
    abstract EventsActivity eventsActivity();

    @ActivityScope
    @ContributesAndroidInjector(
            modules = {
                    EventDetailModule.class
            }
    )
    abstract EventDetailActivity eventsDetail();

    @ActivityScope
    @ContributesAndroidInjector(
            modules = {
                    ProfileModule.class
            }
    )
    abstract ProfileActivity profileActivity();

    @ActivityScope
    @ContributesAndroidInjector(
            modules = {
                    CreateEventActivityModule.class
            }
    )
    abstract CreateEventActivity createEventActivity();

    @ActivityScope
    @ContributesAndroidInjector(
            modules = {
                    SettingsActivityModule.class
            }
    )
    abstract SettingsActivity settingsActivity();
}
