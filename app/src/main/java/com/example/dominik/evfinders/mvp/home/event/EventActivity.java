package com.example.dominik.evfinders.mvp.home.event;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.TextView;

import com.example.dominik.evfinders.R;
import com.example.dominik.evfinders.base.BaseActivity;
import com.example.dominik.evfinders.base.BaseAuthActivity;
import com.example.dominik.evfinders.command.EventCommand;
import com.example.dominik.evfinders.converters.DateConverter;
import com.example.dominik.evfinders.converters.DateConverterImpl;
import com.example.dominik.evfinders.database.pojo.Event;

import java.sql.Date;
import java.text.DateFormat;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.example.dominik.evfinders.mvp.home.MainActivity.CHOOSEN_EVENT;

/**
 * Created by Dominik on 16.10.2017.
 */

public class EventActivity extends BaseActivity {

    @BindView(R.id.activity_main_event_title)
    TextView tvTitle;

    @BindView(R.id.activity_main_event_place)
    TextView tvPlace;

    @BindView(R.id.activity_main_event_date)
    TextView tvDate;

    @BindView(R.id.activity_main_event_description)
    TextView tvDescription;

    private DateConverter dateConverter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);

        dateConverter = new DateConverterImpl();

        EventCommand event = getIntent().getParcelableExtra(CHOOSEN_EVENT);
        fillData(event);
    }

    private void fillData(EventCommand event) {
        tvTitle.setText(event.getName());
        tvPlace.setText(event.getAddress());
        tvDate.setText(android.text.format.DateFormat.format("dd/MM/yyyy HH:mm", new java.util.Date(event.getDate())).toString());
        tvDescription.setText(event.getDescription());
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_main_event;
    }

}
