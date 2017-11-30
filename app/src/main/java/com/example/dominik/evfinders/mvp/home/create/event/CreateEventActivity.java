package com.example.dominik.evfinders.mvp.home.create.event;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.dominik.evfinders.R;
import com.example.dominik.evfinders.base.BaseActivity;
import com.example.dominik.evfinders.command.EventCommand;
import com.example.dominik.evfinders.converters.DateConverter;
import com.example.dominik.evfinders.converters.DateConverterImpl;
import com.example.dominik.evfinders.converters.ProfileConverter;
import com.example.dominik.evfinders.converters.ProfileConverterImpl;
import com.example.dominik.evfinders.model.base.home.event.EventsRepository;
import com.example.dominik.evfinders.model.base.home.profile.IProfileRepository;
import com.example.dominik.evfinders.model.base.home.profile.ProfileRepository;
import com.example.dominik.evfinders.mvp.home.MainActivity;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Locale;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import dagger.android.AndroidInjection;

/**
 * Created by Dominik on 12.11.2017.
 */

public class CreateEventActivity extends BaseActivity implements DatePickerDialog.OnDateSetListener, CreateEventContract.View, TimePickerDialog.OnTimeSetListener {

    @BindView(R.id.activity_create_event_name)
    EditText etName;

    @BindView(R.id.activity_create_event_describe)
    EditText etDescription;

    @BindView(R.id.activity_create_event_place)
    EditText etPlace;

    @BindView(R.id.activity_create_event_date)
    EditText etDate;

    @BindView(R.id.activity_create_event_time)
    EditText etTime;

    @BindView(R.id.activity_create_event_recyclerView)
    RecyclerView recyclerView;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @Inject       CreateEventContract.Presenter presenter;
    @Inject       IProfileRepository repository;
    @Inject       ProfileConverter converter;
    @Inject       DateConverter dateConverter;

    private CreateEventAdapter adapter;
    private AlertDialog alertDialog;
    private AlertDialog.Builder alertDialogBuilder;
    private TextView alertMessage;


    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onViewReady(Bundle savedInstanceState, Intent intent) {
        AndroidInjection.inject(this);
        super.onViewReady(savedInstanceState, intent);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Tworzenie wydarzenia");
        adapter = new CreateEventAdapter(getLayoutInflater());
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL));
        recyclerView.setAdapter(adapter);
        setListeners();
        createAlertDialog();

        adapter.setProfileItems(repository.getAllProfiles());

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_profile_add) {
            onSubmitClicked();
        }
        return true;
    }

    @Override
    public void onDateSet(DatePicker datePicker, int year, int month, int dayOfMonth) {
        etDate.setText(year + "-" + month + "-" + dayOfMonth);
    }

    @Override
    public void onSubmitClicked() {
        presenter.sendEvent(createEventCommand());
    }


    @Override
    public void onCancelClicked() {
        // TODO: 22.11.2017 RETURN TO MAIN ACITIVITY
    }


    @Override
    public void onDateClicked() {
        DatePickerDialog datePickerDialog = new DatePickerDialog(this, this, 2017, 1, 1);
        datePickerDialog.show();
    }

    @Override
    public void onTimeClicked() {
        TimePickerDialog timePickerDialog = new TimePickerDialog(this, this, 0, 0, true);
        timePickerDialog.show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.profile_menu, menu);
        return true;
    }

    @Override
    public void onCreateEventSuccess() {
        setResult(RESULT_OK);
        finish();
    }

    @Override
    public void onCreateEventFailed(String failedMessage) {
        showToast(failedMessage);
    }

    @Override
    public void showProgressDialog(String message) {
        alertMessage.setText(message);
        alertDialog.show();
    }

    @Override
    public void hideProgressDialog() {
        alertDialog.hide();
        alertMessage.setText("");
    }

    @Override
    public void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onTimeSet(TimePicker timePicker, int hours, int minutes) {
        etTime.setText(hours + ":" + minutes);
    }

    private EventCommand createEventCommand() {
        EventCommand eventCommand = new EventCommand();
        eventCommand.setName(etName.getText().toString());
        eventCommand.setDescription(etDescription.getText().toString());
        eventCommand.setAddress(etPlace.getText().toString());
        eventCommand.setProfile(converter.convertArrayToJson(adapter.getProfileItems()));
        eventCommand.setDate(dateConverter.convertDateStringToLong(etDate.getText().toString() + " " + etTime.getText().toString()));
        eventCommand.setLatitude(getIntent().getDoubleExtra("LATITUDE", 0));
        eventCommand.setLongitude(getIntent().getDoubleExtra("LONGITUDE", 0));
        return eventCommand;
    }

    private void setListeners() {
        etDate.setOnFocusChangeListener((view, hasFocus) -> {
            if (hasFocus) {
                onDateClicked();
            } else {

            }
        });

        etTime.setOnFocusChangeListener((view, hasFocus) -> {
            if (hasFocus) {
                onTimeClicked();
            }
        });
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_event_create;
    }

    @Override
    protected void onDestroy() {
        presenter.detach();
        super.onDestroy();
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.attach(this);
    }

    private void createAlertDialog() {
        alertDialogBuilder = new AlertDialog.Builder(this);
        View dialogView = getLayoutInflater().inflate(R.layout.alert_dialog, null);
        alertDialogBuilder.setView(dialogView);
        alertDialog = alertDialogBuilder.create();
        alertMessage = (dialogView).findViewById(R.id.alarm_dialog_message);
    }
}
