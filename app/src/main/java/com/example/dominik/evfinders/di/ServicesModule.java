package com.example.dominik.evfinders.di;

import com.example.dominik.evfinders.model.api.CommentService;
import com.example.dominik.evfinders.model.api.EventService;
import com.example.dominik.evfinders.model.api.FriendsService;
import com.example.dominik.evfinders.model.api.LoginService;
import com.example.dominik.evfinders.model.api.ProfileService;
import com.example.dominik.evfinders.model.api.RegisterService;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

/**
 * Created by Dominik on 19.10.2017.
 */

@Module
abstract class ServicesModule {

    @Provides
    static LoginService provideLoginService(@Named("non-auth") Retrofit retrofit){
        return retrofit.create(LoginService.class);
    }

    @Provides
    static RegisterService provideRegisterService(@Named("non-auth") Retrofit retrofit){
        return retrofit.create(RegisterService.class);
    }

    @Provides
    static FriendsService provideFriendsService(@Named("auth") Retrofit retrofit){
        return retrofit.create(FriendsService.class);
    }

    @Provides
    static EventService provideEventService(@Named("auth") Retrofit retrofit){
        return retrofit.create(EventService.class);
    }

    @Provides
    static ProfileService provideProfileService(@Named("auth") Retrofit retrofit){
        return retrofit.create(ProfileService.class);
    }

    @Provides
    static CommentService provideCommentService(@Named("auth") Retrofit retrofit){
        return retrofit.create(CommentService.class);
    }
}
