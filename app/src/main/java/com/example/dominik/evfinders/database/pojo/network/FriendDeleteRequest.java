package com.example.dominik.evfinders.database.pojo.network;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Dominik on 17.09.2017.
 */

public class FriendDeleteRequest {

    @SerializedName("usernames")
    @Expose
   private List<String> usernames;

    public List<String> getUsernames() {
        return usernames;
    }

    public void setUsernames(List<String> usernames) {
        this.usernames = usernames;
    }

}
