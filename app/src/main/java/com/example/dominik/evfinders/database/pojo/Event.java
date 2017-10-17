package com.example.dominik.evfinders.database.pojo;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * Created by Dominik on 23.06.2017.
 */

public class Event implements Parcelable{

    private Long id;
    private String name;
    private String description;
    private double latituide;
    private double longitude;
    private int[] profileVector = new int[40];
    private double correlation;

    public Event() {
    }

    public Event(Parcel in) {
        if (in.readByte() == 0) {
            id = null;
        } else {
            id = in.readLong();
        }
        name = in.readString();
        description = in.readString();
        latituide = in.readDouble();
        longitude = in.readDouble();
        profileVector = in.createIntArray();
        correlation = in.readDouble();
    }



    public double getCorrelation() {
        return correlation;
    }

    public void setCorrelation(double correlation) {
        this.correlation = correlation;
    }

    public double getLatituide() {
        return latituide;
    }

    public void setLatituide(double latituide) {
        this.latituide = latituide;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public int[] getProfileVector() {
        return profileVector;
    }

    public void setProfileVector(int[] profileVector) {
        this.profileVector = profileVector;
    }

    public List<Rating> getRatings() {
        return ratings;
    }

    public void setRatings(List<Rating> ratings) {
        this.ratings = ratings;
    }

    private List<Rating> ratings;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    private List<User> usersRegisteredToEvent;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public List<User> getUsersRegisteredToEvent() {
        return usersRegisteredToEvent;
    }

    public void setUsersRegisteredToEvent(List<User> usersRegisteredToEvent) {
        this.usersRegisteredToEvent = usersRegisteredToEvent;
    }

    public EventType getEventType() {
        if (getProfileVector()[0] == 1) {
            return EventType.SPORT_AND_RECREATION;
        } else if (getProfileVector()[14] == 1) {
            return EventType.MUSIC;
        } else if (getProfileVector()[27] == 1) {
            return EventType.CINEMA;
        } else {
            return EventType.FRIENDS;
        }

    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {

        if (id == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeLong(id);
        }
        parcel.writeString(name);
        parcel.writeString(description);
        parcel.writeDouble(latituide);
        parcel.writeDouble(longitude);
        parcel.writeIntArray(profileVector);
        parcel.writeDouble(correlation);
    }

    public static final Creator<Event> CREATOR = new Creator<Event>() {
        @Override
        public Event createFromParcel(Parcel in) {
            return new Event(in);
        }

        @Override
        public Event[] newArray(int size) {
            return new Event[size];
        }
    };

    public enum EventType {
        SPORT_AND_RECREATION, MUSIC, CINEMA, FRIENDS
    }
}