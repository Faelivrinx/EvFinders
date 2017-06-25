package com.example.dominik.evfinders.mvp.presenter;

import com.example.dominik.evfinders.base.BasePresenter;
import com.example.dominik.evfinders.mvp.view.MapView;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import javax.inject.Inject;

/**
 * Created by Dominik on 23.06.2017.
 */

public class MapPresenter extends BasePresenter<MapView> implements OnMapReadyCallback {

    private GoogleMap map;

    @Inject
    public MapPresenter() {
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        map = googleMap;
        LatLng sydney = new LatLng(-34, 151);
        map.addMarker(new MarkerOptions().position(sydney).title("Sydney"));
        map.moveCamera(CameraUpdateFactory.newLatLng(sydney));
    }
}
