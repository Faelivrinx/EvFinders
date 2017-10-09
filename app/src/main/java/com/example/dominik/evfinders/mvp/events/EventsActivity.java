package com.example.dominik.evfinders.mvp.events;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatButton;
import android.util.Log;

import com.example.dominik.evfinders.R;
import com.example.dominik.evfinders.base.BaseAuthActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Dominik on 08.10.2017.
 */

public class EventsActivity extends BaseAuthActivity {

    private static final String TAG = EventsActivity.class.getSimpleName();

    @BindView(R.id.activity_events_btnTest)
    AppCompatButton button;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);

        button.setOnClickListener(view -> {
            Log.e(TAG, "Clicked!");
        });
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_events;
    }

//    @Override
    protected int getNavItem() {
        return 0;
    }
}
