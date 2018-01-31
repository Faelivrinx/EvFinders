package com.example.dominik.evfinders.mvp.home;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.dominik.evfinders.R;
import com.example.dominik.evfinders.base.BaseAuthActivity;
import com.example.dominik.evfinders.command.CoordinateCommand;
import com.example.dominik.evfinders.command.EventCommand;
import com.example.dominik.evfinders.database.pojo.Marker;
import com.example.dominik.evfinders.mvp.events.EventsActivity;
import com.example.dominik.evfinders.mvp.friends.FriendsListActivity;
import com.example.dominik.evfinders.mvp.home.create.event.CreateEventActivity;
import com.example.dominik.evfinders.mvp.home.event.EventActivity;
import com.example.dominik.evfinders.mvp.profile.ProfileActivity;
import com.example.dominik.evfinders.mvp.settings.SettingsActivity;
import com.example.dominik.evfinders.mvp.start_test.StartActivityTest;
import com.example.dominik.evfinders.utils.MarkerFactory;
import com.example.dominik.evfinders.utils.MarkerFactoryImp;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import dagger.android.AndroidInjection;

public class MainActivity extends BaseAuthActivity implements OnMapReadyCallback, MapContract.View, NavigationView.OnNavigationItemSelectedListener, GoogleApiClient.ConnectionCallbacks, LocationListener, GoogleApiClient.OnConnectionFailedListener, GoogleMap.OnMarkerClickListener, GoogleMap.OnMapClickListener {
    public static final String TAG = MainActivity.class.getSimpleName();
    public static final int MY_PERMISSIONS_REQUEST_LOCATION = 99;
    public static final String CHOOSEN_EVENT = "CHOOSEN_EVENT";
    public static final int ADD_EVENT_REQUEST = 1;

    private GoogleMap googleMap;
    private GoogleApiClient googleApiClient;
    private LocationRequest locationRequest;
    private List<Marker> markerList;
    private List<EventCommand> actualEvents = new ArrayList<>();
    private com.google.android.gms.maps.model.Marker newEventMarker;
    private Location currentLocation;

    private static int CREATE_EVENT_STATE = 0;

    @BindView(R.id.activity_main_drawer_layout)
    DrawerLayout drawerLayout;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.activity_main_detailEvent)
    Button btnDetailEvent;

    @Inject
    MapPresenter presenter;


    MarkerFactory markerFactory = new MarkerFactoryImp();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        AndroidInjection.inject(this);
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        setNavigation();
        markerList = new ArrayList<>();

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.activity_main_map);
        mapFragment.getMapAsync(this);
    }

    private void setNavigation() {
        setSupportActionBar(toolbar);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.activity_main_nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setCheckedItem(getNavItem());
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_mainActivity_add_event) {
            CREATE_EVENT_STATE = 1;
        } else if (item.getItemId() == R.id.action_mainActivity_back) {
            CREATE_EVENT_STATE = 0;
        } else if (item.getItemId() == R.id.action_mainActivity_cancel_create){
            CREATE_EVENT_STATE = 0;
            newEventMarker.remove();
        } else if(item.getItemId() == R.id.action_mainActivity_refresh){
            if (currentLocation != null){
                presenter.getEvents(new CoordinateCommand(currentLocation.getLatitude(), currentLocation.getLongitude()));
            } else {
                showMessage("Nie można pobrać lokalizacji");
            }
        } else {
            Intent intent = new Intent(this, CreateEventActivity.class);
            intent.putExtra("LATITUDE", newEventMarker.getPosition().latitude);
            intent.putExtra("LONGITUDE", newEventMarker.getPosition().longitude);
            startActivityForResult(intent, ADD_EVENT_REQUEST);
        }
        invalidateOptionsMenu();
        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        CREATE_EVENT_STATE = 0;
        invalidateOptionsMenu();
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        chooseMenuItems(menu);
        chooseActionBarTitle();
        return true;
    }

    private void chooseMenuItems(Menu menu) {
        switch (CREATE_EVENT_STATE) {
            case 0:
                menu.findItem(R.id.action_mainActivity_back).setVisible(false);
                menu.findItem(R.id.action_mainActivity_cancel_create).setVisible(false);
                menu.findItem(R.id.action_mainActivity_add_event).setVisible(true);
                menu.findItem(R.id.action_mainActivity_accept_event).setVisible(false);
                break;
            case 1:
                menu.findItem(R.id.action_mainActivity_back).setVisible(true);
                menu.findItem(R.id.action_mainActivity_cancel_create).setVisible(false);
                menu.findItem(R.id.action_mainActivity_add_event).setVisible(false);
                menu.findItem(R.id.action_mainActivity_accept_event).setVisible(false);
                break;
            case 2:
                menu.findItem(R.id.action_mainActivity_back).setVisible(false);
                menu.findItem(R.id.action_mainActivity_cancel_create).setVisible(true);
                menu.findItem(R.id.action_mainActivity_add_event).setVisible(false);
                menu.findItem(R.id.action_mainActivity_accept_event).setVisible(true);
                break;
            default:
                throw new IllegalStateException("Illegal state in create event! " + CREATE_EVENT_STATE);
        }
    }

    private void chooseActionBarTitle() {
        switch (CREATE_EVENT_STATE) {
            case 0:
                getSupportActionBar().setTitle("");
                break;
            case 1:
                getSupportActionBar().setTitle("Wskaż lokację...");
                break;
            case 2:
                getSupportActionBar().setTitle("Stworz wydarzenie");
                break;
            default:
                throw new IllegalStateException("Illegatl state to create title of action bar " + CREATE_EVENT_STATE);
        }
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_main;
    }

    @Override
    protected int getNavItem() {
        return R.id.nav_map;
    }

    @Override
    public void showEvents(List<EventCommand> events) {
        markerList.clear();
        actualEvents.clear();
        actualEvents.addAll(events);
        createMarkersFromEvents(events);

        if (actualEvents.size() >0){
            googleMap.clear();
            zoomMapToPosition(new LatLng(events.get(0).getLatitude(), events.get(0).getLongitude()));
            addMarkersToMap();
        }
    }

    private void addMarker(Marker marker) {
        BitmapDescriptor icon = null;

        switch (marker.getMarkerType()) {
            case MUSIC:
                icon = BitmapDescriptorFactory.fromResource(R.drawable.music);
                break;
            case SPORT:
                icon = BitmapDescriptorFactory.fromResource(R.drawable.sport);
                break;
            case CINEMA:
                icon = BitmapDescriptorFactory.fromResource(R.drawable.cinema);
                break;
            default:
                break;
        }

        googleMap.addMarker(new MarkerOptions().position(marker.getCoordinates()).title(marker.getName()).icon(icon));
    }

    private void addMarkersToMap() {
        for (Marker marker : markerList) {
            addMarker(marker);
        }
    }

    private void createMarkersFromEvents(List<EventCommand> events) {
        for (EventCommand event : events) {
            Marker marker = markerFactory.createMarkerByEventType(event);
            markerList.add(marker);
        }
    }

    @Override
    public void onLogoutClicked() {
        presenter.logoutUser();
    }

    @Override
    public void showMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showProgressBar() {
    }

    @Override
    public void hideProgressBar() {

    }

    @Override
    public void zoomMapToPosition(LatLng eventPosition) {
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(eventPosition, 14));
    }

    @Override
    public void startActivity() {
        startActivity(new Intent(this, StartActivityTest.class));
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        this.googleMap = googleMap;
        this.googleMap.setOnMarkerClickListener(this);
        this.googleMap.setOnMapClickListener(this);
        presenter.attach(this);

        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(this,
                    Manifest.permission.ACCESS_FINE_LOCATION)
                    == PackageManager.PERMISSION_GRANTED) {
                //Location Permission already granted
                buildGoogleApiClient();
//                this.googleMap.setMyLocationEnabled(true);
            } else {
                //Request Location Permission
                checkLocationPermission();
            }
        } else {
            buildGoogleApiClient();
//            this.googleMap.setMyLocationEnabled(true);
        }
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        drawerLayout.closeDrawer(GravityCompat.START);
        if (item.getItemId() == R.id.nav_logout) {
            onLogoutClicked();
            finish();
        } else if (item.getItemId() == R.id.nav_friends) {
            Intent intent = new Intent(this, FriendsListActivity.class);
            intent.putExtra(BaseAuthActivity.DRAWER_ITEM, R.id.nav_friends);
            startActivity(intent);
        } else if (item.getItemId() == R.id.nav_events) {
            Intent intent = new Intent(this, EventsActivity.class);
            intent.putExtra(BaseAuthActivity.DRAWER_ITEM, R.id.nav_events);
            startActivity(intent);
        } else if(item.getItemId() == R.id.nav_preferences){
            Intent intent = new Intent(this, ProfileActivity.class);
            intent.putExtra(BaseAuthActivity.DRAWER_ITEM, R.id.nav_preferences);
            startActivity(intent);
        }else if(item.getItemId() == R.id.nav_settings){
            Intent intent = new Intent(this, SettingsActivity.class);
            intent.putExtra(BaseAuthActivity.DRAWER_ITEM, R.id.nav_settings);
            startActivity(intent);
        }
        return true;
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onDestroy() {
        presenter.detach();
        super.onDestroy();
    }


    protected synchronized void buildGoogleApiClient() {
        googleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();

        googleApiClient.connect();
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {
        locationRequest = new LocationRequest();
        locationRequest.setInterval(1000);
        locationRequest.setFastestInterval(1000);
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            LocationServices.FusedLocationApi.requestLocationUpdates(googleApiClient, locationRequest, this);
        }
    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    private void checkLocationPermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {

            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.ACCESS_FINE_LOCATION)) {

                // Show an explanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.
                new AlertDialog.Builder(this)
                        .setTitle("Location Permission Needed")
                        .setMessage("This app needs the Location permission, please accept to use location functionality")
                        .setPositiveButton("OK", (dialogInterface, i) -> {
                            //Prompt the user once explanation has been shown
                            ActivityCompat.requestPermissions(MainActivity.this,
                                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                                    MY_PERMISSIONS_REQUEST_LOCATION);
                        })
                        .create()
                        .show();

            } else {
                // No explanation needed, we can request the permission.
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                        MY_PERMISSIONS_REQUEST_LOCATION);
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String permissions[], @NonNull int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_LOCATION: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    // permission was granted, yay! Do the
                    // location-related task you need to do.
                    if (ContextCompat.checkSelfPermission(this,
                            Manifest.permission.ACCESS_FINE_LOCATION)
                            == PackageManager.PERMISSION_GRANTED) {

                        if (googleApiClient == null) {
                            buildGoogleApiClient();
                        }
                        this.googleMap.setMyLocationEnabled(true);
                    }

                } else {

                    Toast.makeText(this, "permission denied", Toast.LENGTH_LONG).show();
                }
            }
        }
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    @Override
    public void onLocationChanged(Location location) {
        Log.d(TAG, "onLocationChanged: " + location.getLatitude() + ", " + location.getLongitude());
        currentLocation = location;
    }

    private void addNewEventMarker(String s, LatLng latLng) {
        newEventMarker = googleMap.addMarker(new MarkerOptions().title(s).position(latLng));
    }

    @Override
    public boolean onMarkerClick(com.google.android.gms.maps.model.Marker marker) {
        for (Marker customMarker : markerList) {
            if (customMarker.getCoordinates().latitude == marker.getPosition().latitude && customMarker.getCoordinates().longitude == marker.getPosition().longitude) {
                // TODO: 14.10.2017 current marker clicked
                EventCommand eventByCoordinates = findEventByCoordinates(customMarker.getCoordinates());
                zoomMapToPosition(customMarker.getCoordinates());
//                btnDetailEvent.setVisibility(View.VISIBLE);
                Intent intent = new Intent(this, EventActivity.class);
                intent.putExtra(CHOOSEN_EVENT, eventByCoordinates);
                startActivity(intent);
            }
        }

        return true;
    }

    @Override
    public void onMapClick(LatLng latLng) {
        btnDetailEvent.setVisibility(View.GONE);
        if (CREATE_EVENT_STATE == 1){
            CREATE_EVENT_STATE = 2;
            addNewEventMarker("Tworzone wydarzenie", latLng);
            invalidateOptionsMenu();
        }
    }

    private EventCommand findEventByCoordinates(LatLng coordinates) {
        for (EventCommand actualEvent : actualEvents) {
            if (actualEvent.getLatitude() == coordinates.latitude && actualEvent.getLongitude() == coordinates.longitude) {
                return actualEvent;
            }
        }
        throw new IllegalArgumentException();
    }
}
