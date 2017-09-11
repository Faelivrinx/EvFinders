package com.example.dominik.evfinders.model.base.home;

import android.util.Log;

import com.example.dominik.evfinders.database.pojo.Event;
import com.example.dominik.evfinders.model.repo.EventsRepo;
import com.example.dominik.evfinders.model.repo.IPrefs;
import com.example.dominik.evfinders.model.repo.Prefs;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by Dominik on 25.08.2017.
 */

public class MapRepository implements IMapRepository {

    private IPrefs prefs;

    @Inject
    public MapRepository(IPrefs prefs) {
        this.prefs = prefs;
    }

    @Override
    public List<Event> getEvents() {
        return null;
    }

    @Override
    public boolean removeUserKey() {
        Log.d("MapRepo", "key deleted: ");
        return prefs.del(Prefs.API_KEY);
    }

    @Override
    public boolean removeFcmToken() {
        return prefs.del(Prefs.FCM_TOKEN);
    }

}
