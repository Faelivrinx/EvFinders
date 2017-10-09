package com.example.dominik.evfinders.database.pojo.network;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Dominik on 06.09.2017.
 */
public class UserRequest {

   private String username;
   private String password;
   private String email;
   private String fcm_token;

    public UserRequest(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFcm_token() {
        return fcm_token;
    }

    public void setFcm_token(String fcm_token) {
        this.fcm_token = fcm_token;
    }
}
