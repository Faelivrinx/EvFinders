package com.example.dominik.evfinders.mvp.events;

/**
 * Created by Dominik on 27.10.2017.
 */

public interface CardAnimator {

    void flipCardToBack(EventsAdapter.ViewHolder holder);
    void flipCardToFront(EventsAdapter.ViewHolder holder);

}
