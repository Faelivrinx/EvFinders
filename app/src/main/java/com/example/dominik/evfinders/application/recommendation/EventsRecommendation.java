package com.example.dominik.evfinders.application.recommendation;

import com.example.dominik.evfinders.database.pojo.Event;
import com.example.dominik.evfinders.database.pojo.User;

import java.util.Collections;
import java.util.List;

/**
 * Created by Dominik on 09.10.2017.
 */

public class EventsRecommendation implements Recommendation {

    private User user;

    //    @Inject
    public EventsRecommendation(User user) {
        this.user = user;
    }

    @Override
    public List<Event> sortEventsByRecommendation(List<Event> events) {
        for (Event event : events) {
            event.setCorrelation(measureCorrelation(user, event));
        }

        Collections.sort(events, (event1, event2) -> {
            if (event1.getCorrelation() == event2.getCorrelation()) {
                return 0;
            }
            return event1.getCorrelation() < event2.getCorrelation() ? 1 : -1;
        });

        for (Event event : events) {
            System.out.println(event.getName() + " have correlation = " + event.getCorrelation());
        }

        return events;
    }

    private double measureCorrelation(User user, Event event) {
        int sum = 0;
        int sumUser = 0;
        int sumEvent = 0;

        for (int i = 0; i < 40; i++) {
            sum += user.getProfile()[i] * event.getProfileVector()[i];
        }

        for (int i = 0; i < 40; i++) {
            if (user.getProfile()[i] != 0) {
                sumUser += Math.pow(user.getProfile()[i], 2);
            }
        }

        for (int i = 0; i < 40; i++) {
            if (event.getProfileVector()[i] != 1) {
                sumEvent += Math.pow(event.getProfileVector()[i], 2);
            }
        }

        return sum / (Math.sqrt(sumUser) * Math.sqrt(sumEvent));
    }
}

