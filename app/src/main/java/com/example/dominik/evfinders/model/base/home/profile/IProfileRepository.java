package com.example.dominik.evfinders.model.base.home.profile;

import com.example.dominik.evfinders.database.pojo.ProfileItem;
import com.example.dominik.evfinders.database.pojo.network.TaskResponse;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Single;
import retrofit2.Response;


/**
 * Created by Dominik on 07.11.2017.
 */

public interface IProfileRepository {

    List<ProfileItem> getAllProfiles();
    ProfileItem getProfileById(Long id);
    Single<Response<TaskResponse>> saveProfile(String profile);
    boolean removeUserKey();
    boolean removeFcmToken();

}
