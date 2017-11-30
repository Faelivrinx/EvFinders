package com.example.dominik.evfinders.command;

import com.bluelinelabs.logansquare.annotation.JsonObject;

import org.json.JSONObject;

/**
 * Created by Dominik on 22.11.2017.
 */

@JsonObject
public class JsonProfile{

    private int id;
    private int value;

    public JsonProfile() {
    }

    public JsonProfile(int id, int value) {
        this.id = id;
        this.value = value;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
