package com.example.dominik.evfinders.application.services;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import com.example.dominik.evfinders.command.CoordinateCommand;

import io.reactivex.subjects.BehaviorSubject;

/**
 * Created by Dominik on 03.12.2017.
 */

public class LocationService extends Service {
    public static final String TAG = LocationService.class.getSimpleName();
    private LocationManager locationManager;
    public static final int LOCATION_INTERVAL = 1000;
    public static final float LOCATION_DISTANCE = 10f;

    public static class LocationListener implements android.location.LocationListener {

        private static Location lastLocation;
        private static CoordinateCommand lastKnowCoordinate = null;
        private static BehaviorSubject<CoordinateCommand> publisher = BehaviorSubject.createDefault(new CoordinateCommand(0, 0));

        public LocationListener(String provider) {
            lastLocation = new Location(provider);
        }

        @Override
        public void onLocationChanged(Location location) {
            lastLocation.set(location);
            publisher.onNext(new CoordinateCommand(location.getLatitude(), location.getLongitude()));
        }

        @Override
        public void onStatusChanged(String s, int i, Bundle bundle) {
            Log.d(TAG, "onStatusChanged: " + s);
        }

        @Override
        public void onProviderEnabled(String s) {

        }

        @Override
        public void onProviderDisabled(String s) {

        }

        public static BehaviorSubject<CoordinateCommand> getPublisher() {
            return publisher;
        }

        public static Location getLastLocation() {
            return lastLocation;
        }

        public static CoordinateCommand getLastKnowCoordinate() {
            if (lastLocation != null) {
                return new CoordinateCommand(lastLocation.getLatitude(), lastLocation.getLongitude());
            } else {
                return null;
            }
        }
    }

    LocationListener[] locationListeners = new LocationListener[]{
            new LocationListener(LocationManager.GPS_PROVIDER),
            new LocationListener(LocationManager.NETWORK_PROVIDER)
    };

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        super.onStartCommand(intent, flags, startId);
        return START_STICKY;
    }

    @Override
    public void onCreate() {
        initializeLocationManager();
        try {
            locationManager.requestLocationUpdates(
                    LocationManager.NETWORK_PROVIDER, LOCATION_INTERVAL, LOCATION_DISTANCE,
                    locationListeners[1]);
        } catch (SecurityException | IllegalArgumentException ex) {
        }
        try {
            locationManager.requestLocationUpdates(
                    LocationManager.GPS_PROVIDER, LOCATION_INTERVAL, LOCATION_DISTANCE,
                    locationListeners[0]);
        } catch (SecurityException | IllegalArgumentException ex) {
        }
    }

    private void initializeLocationManager() {
        if (locationManager == null) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                locationManager = (LocationManager) getApplicationContext().getSystemService(Context.LOCATION_SERVICE);
            }
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        super.onDestroy();
        if (locationManager != null) {
            for (int i = 0; i < locationListeners.length; i++) {
                try {
                    locationManager.removeUpdates(locationListeners[i]);
                } catch (Exception ex) {
                }
            }
        }
    }
}
