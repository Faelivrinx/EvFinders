package com.example.dominik.evfinders.command;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Dominik on 28.12.2017.
 */

public class UserAttendCommand implements Parcelable{

    private Long id;
    private String name;

    protected UserAttendCommand(Parcel in) {
        if (in.readByte() == 0) {
            id = null;
        } else {
            id = in.readLong();
        }
        name = in.readString();
    }

    public static final Creator<UserAttendCommand> CREATOR = new Creator<UserAttendCommand>() {
        @Override
        public UserAttendCommand createFromParcel(Parcel in) {
            return new UserAttendCommand(in);
        }

        @Override
        public UserAttendCommand[] newArray(int size) {
            return new UserAttendCommand[size];
        }
    };

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
    }
}
