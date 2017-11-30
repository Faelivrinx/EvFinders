package com.example.dominik.evfinders.mvp.home.event;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.example.dominik.evfinders.R;
import com.example.dominik.evfinders.base.BaseActivity;
import com.example.dominik.evfinders.base.BaseAuthActivity;
import com.example.dominik.evfinders.command.EventCommand;
import com.example.dominik.evfinders.database.pojo.Event;

import static com.example.dominik.evfinders.mvp.home.MainActivity.CHOOSEN_EVENT;

/**
 * Created by Dominik on 16.10.2017.
 */

public class EventActivity extends BaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        EventCommand event = getIntent().getParcelableExtra(CHOOSEN_EVENT);
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_main_event;
    }

}
