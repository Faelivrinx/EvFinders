package com.example.dominik.evfinders.database.pojo;

import com.google.android.gms.maps.model.LatLng;

/**
 * Created by Dominik on 09.10.2017.
 */

public class Marker {

    private long id;
    private String name;
    private LatLng coordinates;
    private MarkerType markerType;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public MarkerType getMarkerType() {
        return markerType;
    }

    public void setMarkerType(MarkerType markerType) {
        this.markerType = markerType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LatLng getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(LatLng coordinates) {
        this.coordinates = coordinates;
    }

    public enum MarkerType {
        MY_LOCATION, SPORT, MUSIC, CINEMA, FRIENDS
    }
}
