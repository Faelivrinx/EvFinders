package com.example.dominik.evfinders.model.base.home;

import com.example.dominik.evfinders.database.pojo.Event;
import com.example.dominik.evfinders.model.repo.EventsRepo;

import java.util.List;

/**
 * Created by Dominik on 25.08.2017.
 */

public class MapRepository implements IMapRepository {

    private EventsRepo repo;

    public MapRepository(EventsRepo repo) {
        this.repo = repo;
    }


    @Override
    public List<Event> getEvents() {
        return null;
    }

}
