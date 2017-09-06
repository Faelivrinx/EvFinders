package com.example.dominik.evfinders.database.pojo.network;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Dominik on 06.09.2017.
 */
public class UserRequest {

   private String username;
   private String password;
   private String email;

    public UserRequest(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
    }
}
