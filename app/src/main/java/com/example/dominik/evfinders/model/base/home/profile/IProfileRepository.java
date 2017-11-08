package com.example.dominik.evfinders.model.base.home.profile;

import com.example.dominik.evfinders.database.pojo.ProfileItem;

import java.util.List;

/**
 * Created by Dominik on 07.11.2017.
 */

public interface IProfileRepository {

    List<ProfileItem> getAllProfiles();
    ProfileItem getProfileById(Long id);

}
