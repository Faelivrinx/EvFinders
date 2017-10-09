package com.example.dominik.evfinders.utils;

import com.example.dominik.evfinders.database.pojo.Event;
import com.example.dominik.evfinders.database.pojo.Marker;
import com.google.android.gms.maps.model.LatLng;

/**
 * Created by Dominik on 09.10.2017.
 */

public class MarkerFactoryImp implements MarkerFactory {

    @Override
    public Marker createMarkerByEventType(Event event) {
        Marker marker = new Marker();
        marker.setName(event.getName());
        marker.setCoordinates(new LatLng(event.getLatituide(), event.getLongitude()));

        switch (event.getEventType()) {
            case SPORT_AND_RECREATION:
                marker.setMarkerType(Marker.MarkerType.SPORT);
                return marker;

            case MUSIC:
                marker.setMarkerType(Marker.MarkerType.MUSIC);
                return marker;

            case CINEMA:
                marker.setMarkerType(Marker.MarkerType.CINEMA);
                return marker;

            case FRIENDS:
                marker.setMarkerType(Marker.MarkerType.FRIENDS);
                return marker;

            default:
                marker.setMarkerType(Marker.MarkerType.MY_LOCATION);
                return marker;
        }
    }
}
