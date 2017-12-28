package com.example.dominik.evfinders.model.base.home.event;

import com.example.dominik.evfinders.command.CoordinateCommand;
import com.example.dominik.evfinders.command.EventCommand;
import com.example.dominik.evfinders.database.pojo.Event;

import java.util.List;

import io.reactivex.Single;
import retrofit2.Response;

/**
 * Created by Dominik on 8/19/2017.
 */

public interface IEventsRepository {

    Single createEvent(EventCommand eventCommand);

    Single<Response<List<EventCommand>>> getEvents(CoordinateCommand coordinateCommand);

    Single<Response<List<EventCommand>>> getEventsWithRecommendation(CoordinateCommand coordinateCommand);

    Single<Response<EventCommand>> attendAtEvent(Long userId);

    boolean removeUserKey();

    boolean removeFcmToken();
}
