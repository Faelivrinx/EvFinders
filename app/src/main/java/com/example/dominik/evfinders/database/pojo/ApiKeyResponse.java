package com.example.dominik.evfinders.database.pojo;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Dominik on 05.09.2017.
 */

public class ApiKeyResponse {

    @SerializedName("name")
    private String name;
    @SerializedName("value")
    private String value;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
