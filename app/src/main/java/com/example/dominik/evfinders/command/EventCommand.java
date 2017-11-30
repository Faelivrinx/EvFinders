package com.example.dominik.evfinders.command;

import android.os.Parcel;
import android.os.Parcelable;

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
}
