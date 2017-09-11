package com.example.dominik.evfinders.model.base.home;

import com.example.dominik.evfinders.database.pojo.Event;

import java.util.List;

/**
 * Created by Dominik on 8/19/2017.
 */

public interface IMapRepository {

    List<Event> getEvents();

    boolean removeUserKey();

    boolean removeFcmToken();
}
