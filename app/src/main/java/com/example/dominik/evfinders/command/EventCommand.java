package com.example.dominik.evfinders.command;

import android.os.Parcel;
import android.os.Parcelable;

import com.bluelinelabs.logansquare.LoganSquare;
import com.example.dominik.evfinders.database.pojo.Event;
import com.example.dominik.evfinders.database.pojo.Marker;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Dominik on 21.11.2017.
 */

public class EventCommand implements Parcelable{

    private String name;
    private String address;
    private long date;
    private String description;
    private String profile;
    private double longitude;
    private double latitude;


    public EventCommand() {
    }

    protected EventCommand(Parcel in) {
        name = in.readString();
        address = in.readString();
        date = in.readLong();
        description = in.readString();
        profile = in.readString();
        longitude = in.readDouble();
        latitude = in.readDouble();
    }

    public static final Creator<EventCommand> CREATOR = new Creator<EventCommand>() {
        @Override
        public EventCommand createFromParcel(Parcel in) {
            return new EventCommand(in);
        }

        @Override
        public EventCommand[] newArray(int size) {
            return new EventCommand[size];
        }
    };

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public long getDate() {
        return date;
    }

    public void setDate(long date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(name);
        parcel.writeString(address);
        parcel.writeLong(date);
        parcel.writeString(description);
        parcel.writeString(profile);
        parcel.writeDouble(longitude);
        parcel.writeDouble(latitude);
    }

    public Event.EventType getEventType() {
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
            return Event.EventType.SPORT_AND_RECREATION;
        } else if (musicCount > sportCount && musicCount > cultureCount) {
            return Event.EventType.MUSIC;
        } else if (cultureCount > sportCount && cultureCount > musicCount) {
            return Event.EventType.CINEMA;
        } else {
            return Event.EventType.SPORT_AND_RECREATION;
            // TODO: 22.11.2017 various event
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
