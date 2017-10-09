package com.example.dominik.evfinders.utils;

import com.example.dominik.evfinders.database.pojo.Event;
import com.example.dominik.evfinders.database.pojo.Marker;

/**
 * Created by Dominik on 09.10.2017.
 */

public interface MarkerFactory {
    Marker createMarkerByEventType(Event event);
}
