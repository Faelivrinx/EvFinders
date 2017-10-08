package com.example.dominik.evfinders.model.base.home;

import com.example.dominik.evfinders.database.pojo.Event;

import java.util.List;

import io.reactivex.Single;

/**
 * Created by Dominik on 8/19/2017.
 */

public interface IMapRepository {

    Single<List<Event>> getEvents();

    boolean removeUserKey();
}
