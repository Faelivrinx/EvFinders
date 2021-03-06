package com.example.dominik.evfinders.database.pojo;

import com.bluelinelabs.logansquare.annotation.JsonIgnore;
import com.google.gson.JsonObject;

/**
 * Created by Dominik on 07.11.2017.
 */

public class ProfileItem {
    private Long id;
    @JsonIgnore
    private String name;

    private int rating;

    @JsonIgnore
    private boolean isSelected = false;

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

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {

        this.rating = rating;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }
}
