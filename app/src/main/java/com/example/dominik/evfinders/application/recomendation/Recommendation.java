package com.example.dominik.evfinders.application.recomendation;

import com.example.dominik.evfinders.database.pojo.Event;

import java.util.List;

/**
 * Created by Dominik on 08.10.2017.
 */

public interface Recommendation {
    List<Event> sortEventsByRecommendation(List<Event> events);
}
