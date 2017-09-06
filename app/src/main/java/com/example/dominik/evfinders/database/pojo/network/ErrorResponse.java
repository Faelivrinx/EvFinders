package com.example.dominik.evfinders.database.pojo.network;

/**
 * Created by Dominik on 06.09.2017.
 */

public class ErrorResponse implements Response {

    private String name;
    private int value;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }


    @Override
    public int getStatus() {
        return value;
    }
}
