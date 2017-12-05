package com.example.dominik.evfinders.command;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Dominik on 05.12.2017.
 */

public class CommentCommand implements Parcelable{

    private Long id;
    private Long eventId;
    private Long userID;

    private String comment;
    private String author;
    private int rating;

    protected CommentCommand(Parcel in) {
        if (in.readByte() == 0) {
            id = null;
        } else {
            id = in.readLong();
        }
        if (in.readByte() == 0) {
            eventId = null;
        } else {
            eventId = in.readLong();
        }
        if (in.readByte() == 0) {
            userID = null;
        } else {
            userID = in.readLong();
        }
        comment = in.readString();
        author = in.readString();
        rating = in.readInt();
    }

    public static final Creator<CommentCommand> CREATOR = new Creator<CommentCommand>() {
        @Override
        public CommentCommand createFromParcel(Parcel in) {
            return new CommentCommand(in);
        }

        @Override
        public CommentCommand[] newArray(int size) {
            return new CommentCommand[size];
        }
    };

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getEventId() {
        return eventId;
    }

    public void setEventId(Long eventId) {
        this.eventId = eventId;
    }

    public Long getUserID() {
        return userID;
    }

    public void setUserID(Long userID) {
        this.userID = userID;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
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
        if (eventId == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeLong(eventId);
        }
        if (userID == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeLong(userID);
        }
        parcel.writeString(comment);
        parcel.writeString(author);
        parcel.writeInt(rating);
    }
}
