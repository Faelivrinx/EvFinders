package com.example.dominik.evfinders.mvp.events.detail;

import com.example.dominik.evfinders.command.EventCommand;
import com.example.dominik.evfinders.database.pojo.Event;

/**
 * Created by Dominik on 27.10.2017.
 */

public class EventDetailPresenter implements EventDetailContract.Presenter {

    private EventDetailContract.View view;

    @Override
    public void attach(EventDetailContract.View view) {
        if (this.view == null){
            this.view = view;
        }
    }

    @Override
    public void detach() {
        view = null;
    }

    @Override
    public void checkEvent(EventCommand event) {
        view.showProgressBar();
        if (event != null){
            view.showEvent(event);
            view.hideProgressBar();
        }
    }
}
