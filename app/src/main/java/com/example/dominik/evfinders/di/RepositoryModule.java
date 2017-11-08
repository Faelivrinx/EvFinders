package com.example.dominik.evfinders.di;

import com.example.dominik.evfinders.model.api.FriendsService;
import com.example.dominik.evfinders.model.api.LoginService;
import com.example.dominik.evfinders.model.api.RegisterService;
import com.example.dominik.evfinders.model.base.home.IEventsRepository;
import com.example.dominik.evfinders.model.base.home.MockEventsRepository;
import com.example.dominik.evfinders.model.base.home.friends.FriendsRepository;
import com.example.dominik.evfinders.model.base.home.friends.IFriendsRepository;
import com.example.dominik.evfinders.model.base.home.login.ILoginRepository;
import com.example.dominik.evfinders.model.base.home.login.LoginRepository;
import com.example.dominik.evfinders.model.base.home.profile.IProfileRepository;
import com.example.dominik.evfinders.model.base.home.profile.ProfileRepository;
import com.example.dominik.evfinders.model.base.home.register.IRegisterRepository;
import com.example.dominik.evfinders.model.base.home.register.RegisterRepository;
import com.example.dominik.evfinders.model.repo.IPrefs;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

/**
 * Created by Dominik on 19.10.2017.
 */

@Module
abstract class RepositoryModule {


    @Provides
    static ILoginRepository provideLoginRepository(IPrefs prefs, LoginService loginService){
        return new LoginRepository(prefs, loginService);
    }

    @Provides
    static IRegisterRepository provideRegisterRepository(IPrefs prefs, RegisterService registerSErvice){
        return new RegisterRepository(prefs, registerSErvice);
    }

    @Provides
    static IFriendsRepository provideFriendsRepository(IPrefs prefs, FriendsService friendsService){
        return new FriendsRepository(prefs, friendsService);
    }

    @Provides
    static IEventsRepository provideEventsRepository(IPrefs prefs){
        return new MockEventsRepository(prefs);
    }

    @Provides
    static IProfileRepository provideProfileRepository(){
        return new ProfileRepository();
    }
}
