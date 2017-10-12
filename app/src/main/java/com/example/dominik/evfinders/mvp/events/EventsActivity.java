package com.example.dominik.evfinders.mvp.events;

import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.AppCompatButton;
import android.widget.LinearLayout;

import com.example.dominik.evfinders.R;
import com.example.dominik.evfinders.base.BaseAuthActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import dagger.android.AndroidInjection;

public class EventsActivity extends BaseAuthActivity {
    public static final String TAG = EventsActivity.class.getSimpleName();


    @BindView(R.id.activity_events_mainLayout)
    ConstraintLayout mainLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AndroidInjection.inject(this);
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
    }

    @Override
    protected int getContentView() {
        return R.layout.events_activity;
    }

    @Override
    protected int getNavItem() {
        return R.id.nav_events;
    }


}
