package com.example.dominik.evfinders.converters;

import com.example.dominik.evfinders.database.pojo.ProfileItem;

import java.util.List;

/**
 * Created by Dominik on 21.11.2017.
 */

public interface ProfileConverter {

    String convertArrayToJson(List<ProfileItem> profileItems);
    boolean isValid(String json);

}
