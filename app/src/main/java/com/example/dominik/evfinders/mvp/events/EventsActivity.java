package com.example.dominik.evfinders.mvp.events;

import com.example.dominik.evfinders.R;
import com.example.dominik.evfinders.base.BaseAuthActivity;

/**
 * Created by Dominik on 08.10.2017.
 */

public class EventsActivity extends BaseAuthActivity {



    @Override
    protected int getContentView() {
        return R.layout.activity_events;
    }

//    @Override
    protected int getNavItem() {
        return 0;
    }
}
