package com.example.dominik.evfinders.database.pojo;

import java.util.List;

/**
 * Created by Dominik on 23.06.2017.
 */

public class Event {

    private Long id;
    private String name;
    private String description;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    private List<Category> categories;
    private List<User> usersRegisteredToEvent;

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

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }

    public List<User> getUsersRegisteredToEvent() {
        return usersRegisteredToEvent;
    }

    public void setUsersRegisteredToEvent(List<User> usersRegisteredToEvent) {
        this.usersRegisteredToEvent = usersRegisteredToEvent;
    }
}
