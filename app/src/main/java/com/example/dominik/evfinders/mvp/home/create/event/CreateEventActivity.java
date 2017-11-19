package com.example.dominik.evfinders.mvp.home.create.event;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.widget.DatePicker;
import android.widget.EditText;

import com.example.dominik.evfinders.R;
import com.example.dominik.evfinders.base.BaseActivity;
import com.example.dominik.evfinders.model.base.home.profile.IProfileRepository;
import com.example.dominik.evfinders.model.base.home.profile.ProfileRepository;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Dominik on 12.11.2017.
 */

public class CreateEventActivity extends BaseActivity implements DatePickerDialog.OnDateSetListener {

    @BindView(R.id.activity_create_event_name)
    EditText etName;

    @BindView(R.id.activity_create_event_describe)
    EditText etDescription;

    @BindView(R.id.activity_create_event_place)
    EditText etPlace;

    @BindView(R.id.activity_create_event_date)
    EditText etDate;

    @BindView(R.id.activity_create_event_recyclerView)
    RecyclerView recyclerView;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    private Locale locale;
    private CreateEventAdapter adapter;

    IProfileRepository repository;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onViewReady(Bundle savedInstanceState, Intent intent) {
        super.onViewReady(savedInstanceState, intent);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Tworzenie wydarzenia");
        repository = new ProfileRepository();
        locale = getResources().getConfiguration().locale;
        adapter = new CreateEventAdapter(getLayoutInflater());
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL));
        recyclerView.setAdapter(adapter);
        setDateListener();

        adapter.setProfileItems(repository.getAllProfiles());

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.profile_menu, menu);
        return true;
    }

    private void setDateListener() {
        etDate.setOnFocusChangeListener((view, hasFocus) -> {
            if (hasFocus) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(this, this, 2017, 1, 1);
                datePickerDialog.show();
            } else {

            }
        });
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_event_create;
    }


    @Override
    public void onDateSet(DatePicker datePicker, int year, int month, int dayOfMonth) {
        final Calendar calendar = Calendar.getInstance(locale);
        final DateFormat dateFormat = DateFormat.getDateInstance(DateFormat.MEDIUM, locale);

        calendar.set(year, month, dayOfMonth);
        etDate.setText(dateFormat.format(calendar.getTime()));
    }
}
