package com.example.dominik.evfinders.utils;

import com.bluelinelabs.logansquare.LoganSquare;
import com.example.dominik.evfinders.command.EventCommand;
import com.example.dominik.evfinders.database.pojo.Event;
import com.example.dominik.evfinders.database.pojo.Marker;
import com.example.dominik.evfinders.database.pojo.ProfileItem;
import com.google.android.gms.maps.model.LatLng;
import com.google.gson.Gson;
import com.google.gson.JsonArray;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Dominik on 09.10.2017.
 */

public class MarkerFactoryImp implements MarkerFactory {

    @Override
    public Marker createMarkerByEventType(EventCommand event) {
        Marker marker = new Marker();
        marker.setName(event.getName());
        marker.setCoordinates(new LatLng(event.getLatitude(), event.getLongitude()));

//        switch (event.getEventType()) {
//            case SPORT_AND_RECREATION:
//                marker.setMarkerType(Marker.MarkerType.SPORT);
//                return marker;
//
//            case MUSIC:
//                marker.setMarkerType(Marker.MarkerType.MUSIC);
//                return marker;
//
//            case CINEMA:
//                marker.setMarkerType(Marker.MarkerType.CINEMA);
//                return marker;
//
//            case FRIENDS:
//                marker.setMarkerType(Marker.MarkerType.FRIENDS);
//                return marker;
//
//            default:
//                marker.setMarkerType(Marker.MarkerType.MY_LOCATION);
//                return marker;
//        }
        // TODO: 22.11.2017 set type
//        ArrayList hashMap = new Gson().fromJson(event.getProfile(), ArrayList.class);

        marker.setMarkerType(chooseEventType(event.getProfile()));
        return marker;
    }

    private Marker.MarkerType chooseEventType(String profile) {
        int sportCount = 0;
        int musicCount = 0;
        int cultureCount = 0;

        Map<String, Long> map = new HashMap<>();
        try {
            map = LoganSquare.parse(profile, Map.class);
        } catch (IOException e) {
            e.printStackTrace();
        }


        for (Map.Entry<String, Long> pair : map.entrySet()) {
            if (isSport(pair)) {
                sportCount++;
            } else if (isMusic(pair)) {
                musicCount++;
            } else if (isCulture(pair)) {
                cultureCount++;
            }
        }

        if (sportCount > musicCount && sportCount > cultureCount) {
            return Marker.MarkerType.SPORT;
        } else if (musicCount > sportCount && musicCount > cultureCount) {
            return Marker.MarkerType.MUSIC;
        } else if (cultureCount > sportCount && cultureCount > musicCount) {
            return Marker.MarkerType.CINEMA;
        } else {
            return Marker.MarkerType.CINEMA;
        }

    }

    private boolean isCulture(Map.Entry<String, Long> pair) {
        return Long.valueOf(pair.getKey()) > 24L && Long.valueOf(pair.getKey()) < 31L && pair.getValue() > 0L;
    }

    private boolean isMusic(Map.Entry<String, Long> pair) {
        return Long.valueOf(pair.getKey()) > 12L && Long.valueOf(pair.getKey()) < 23L && pair.getValue() > 0L;
    }

    private boolean isSport(Map.Entry<String, Long> pair) {
        return Long.valueOf(pair.getKey()) > 0L && Long.valueOf(pair.getKey()) < 11L && pair.getValue() > 0L;
    }
}
