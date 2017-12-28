package com.example.dominik.evfinders.di;

import com.example.dominik.evfinders.model.api.CommentService;
import com.example.dominik.evfinders.model.api.EventService;
import com.example.dominik.evfinders.model.api.FriendsService;
import com.example.dominik.evfinders.model.api.LoginService;
import com.example.dominik.evfinders.model.api.ProfileService;
import com.example.dominik.evfinders.model.api.RegisterService;
import com.example.dominik.evfinders.model.base.home.comment.CommentRepository;
import com.example.dominik.evfinders.model.base.home.comment.ICommentRepository;
import com.example.dominik.evfinders.model.base.home.event.EventsRepository;
import com.example.dominik.evfinders.model.base.home.event.IEventsRepository;
import com.example.dominik.evfinders.model.base.home.event.MockEventsRepository;
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
    static IEventsRepository provideEventsRepository(IPrefs prefs, EventService service){
        return new EventsRepository(prefs, service);
    }

    @Provides
    static IProfileRepository provideProfileRepository(IPrefs prefs, ProfileService service){
        return new ProfileRepository(prefs, service);
    }

    @Provides
    static ICommentRepository provideCommentRepository(IPrefs prefs, CommentService service){
        return new CommentRepository(prefs, service);
    }
}
