package com.example.dominik.evfinders.converters;

import com.example.dominik.evfinders.command.EventCommand;
import com.example.dominik.evfinders.database.pojo.Event;

/**
 * Created by Dominik on 22.11.2017.
 */

public class EventToEventCommand implements Converter<Event, EventCommand> {

    @Override
    public EventCommand convert(Event object) {
        return null;
    }

}
