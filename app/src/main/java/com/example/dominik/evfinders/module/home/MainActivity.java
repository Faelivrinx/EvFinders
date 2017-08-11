package com.example.dominik.evfinders.module.home;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.example.dominik.evfinders.R;
import com.example.dominik.evfinders.base.BaseAuthActivity;
import com.example.dominik.evfinders.mvp.presenter.MapPresenter;
import com.example.dominik.evfinders.mvp.view.MapView;
import com.google.android.gms.maps.SupportMapFragment;

import javax.inject.Inject;

import butterknife.ButterKnife;

public class MainActivity extends BaseAuthActivity implements MapView{
    public static final String TAG = MainActivity.class.getSimpleName();

    @Inject
    MapPresenter mapPresenter;

    @Override
    protected void onViewReady(Bundle savedInstanceState, Intent intent) {
        super.onViewReady(savedInstanceState, intent);
        ButterKnife.bind(this);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.activity_main_map);
        mapFragment.getMapAsync(mapPresenter);
    }

    @Override
    protected void resolveDepedency() {
        getApplicationComponent().inject(this);
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_main;
    }

    @Override
    public void onLongMapClick() {

    }

    @Override
    public void onEventClick() {

    }
}
