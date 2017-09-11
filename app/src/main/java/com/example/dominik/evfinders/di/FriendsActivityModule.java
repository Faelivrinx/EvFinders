package com.example.dominik.evfinders.di;

import android.util.Log;

import com.example.dominik.evfinders.model.api.FriendsService;
import com.example.dominik.evfinders.model.base.home.friends.FriendsRepository;
import com.example.dominik.evfinders.model.base.home.friends.IFriendsRepository;
import com.example.dominik.evfinders.model.repo.IPrefs;
import com.example.dominik.evfinders.mvp.friends.FriendsPresenter;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

/**
 * Created by Dominik on 11.09.2017.
 */

@Module
abstract class FriendsActivityModule {


    @ActivityScope
    @Provides
    static FriendsService provideService(@Named("auth") Retrofit retrofit) {
        Log.d("MODULE", "provideService: ");
        return retrofit.create(FriendsService.class);
    }

    @ActivityScope
    @Provides
    static IFriendsRepository provideRepository(IPrefs prefs, FriendsService service){
        return new FriendsRepository(prefs, service);
    }

    @ActivityScope
    @Provides
    static FriendsPresenter providePresenter(IFriendsRepository repository){
        return new FriendsPresenter(repository);
    }
}
