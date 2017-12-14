package com.example.dominik.evfinders.model.base.home.event;

import android.util.Log;

import com.example.dominik.evfinders.command.CoordinateCommand;
import com.example.dominik.evfinders.command.EventCommand;
import com.example.dominik.evfinders.database.pojo.Event;
import com.example.dominik.evfinders.database.pojo.network.TaskResponse;
import com.example.dominik.evfinders.model.api.EventService;
import com.example.dominik.evfinders.model.repo.IPrefs;
import com.example.dominik.evfinders.model.repo.Prefs;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Single;
import retrofit2.Response;

/**
 * Created by Dominik on 25.08.2017.
 */

public class EventsRepository implements IEventsRepository {

    private IPrefs prefs;
    private EventService eventService;

    @Inject
    public EventsRepository(IPrefs prefs, EventService eventService) {
        this.prefs = prefs;
        this.eventService = eventService;
    }

    @Override
    public Single<Response<List<EventCommand>>> getEvents(CoordinateCommand coordinateCommand) {
        return eventService.getEvents(prefs.get(Prefs.API_KEY), coordinateCommand);
    }

    @Override
    public Single<Response<List<EventCommand>>> getEventsWithRecommendation(CoordinateCommand coordinateCommand) {
        coordinateCommand.setRecommendationType(prefs.get(Prefs.RECOMMENDATION_TYPE, 0));
        return eventService.getEventsWithRecommendation(prefs.get(Prefs.API_KEY), coordinateCommand);
    }

    @Override
    public Single<Response<TaskResponse>> createEvent(EventCommand eventCommand) {
        return eventService.createEvent(prefs.get(Prefs.API_KEY), eventCommand);
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
