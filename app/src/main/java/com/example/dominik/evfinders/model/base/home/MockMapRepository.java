package com.example.dominik.evfinders.model.base.home;

import com.example.dominik.evfinders.database.pojo.Event;

import java.util.List;

/**
 * Created by Dominik on 01.09.2017.
 */

public class MockMapRepository implements IMapRepository{

    @Override
    public List<Event> getEvents() {
        return null;
    }

    @Override
    public boolean removeUserKey() {
        return false;
    }
}
