package com.example.dominik.evfinders.database.pojo.network;

/**
 * Created by Dominik on 06.09.2017.
 */

public class TaskResponse implements Response {

    private String task;
    private String value;

    public String getName() {
        return task;
    }

    public void setName(String action) {
        this.task = action;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }


    @Override
    public int getStatus() {
        return 200;
    }
}
