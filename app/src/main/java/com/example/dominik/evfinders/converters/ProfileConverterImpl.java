package com.example.dominik.evfinders.converters;

import com.bluelinelabs.logansquare.LoganSquare;
import com.example.dominik.evfinders.database.pojo.ProfileItem;
import com.google.gson.JsonArray;

import java.io.IOException;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Dominik on 21.11.2017.
 */

public class ProfileConverterImpl implements ProfileConverter {

    @Override
    public String convertArrayToJson(List<ProfileItem> profileItems) {
        JsonArray jsonArray = new JsonArray();
        Map<String, Integer> map = new HashMap<>();
        String result = "";

        for (ProfileItem profileItem : profileItems) {
            map.put(profileItem.getId().toString(), profileItem.getRating());
        }

        try {
            result =  LoganSquare.serialize(map);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

        return result;
    }
}
