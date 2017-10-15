package com.example.dominik.evfinders.mvp.events;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.example.dominik.evfinders.R;
import com.example.dominik.evfinders.base.BaseAuthActivity;
import com.example.dominik.evfinders.database.pojo.Event;
import com.example.dominik.evfinders.mvp.friends.FriendsAdapter;
import com.example.dominik.evfinders.mvp.friends.FriendsListActivity;
import com.example.dominik.evfinders.mvp.home.MainActivity;
import com.example.dominik.evfinders.mvp.start_test.StartActivityTest;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import dagger.android.AndroidInjection;

public class EventsActivity extends BaseAuthActivity implements EventsContract.View, NavigationView.OnNavigationItemSelectedListener {
    public static final String TAG = EventsActivity.class.getSimpleName();


    @BindView(R.id.activity_events_mainLayout)
    DrawerLayout mainLayout;

    @BindView(R.id.activity_events_recyclerView)
    RecyclerView recyclerView;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @Inject
    EventsPresenter presenter;

    private EventsAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AndroidInjection.inject(this);
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        setNavigation();

        adapter = new EventsAdapter(getLayoutInflater());
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
        recyclerView.setAdapter(adapter);
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_events;
    }

    @Override
    protected int getNavItem() {
        return R.id.nav_events;
    }


    @Override
    public void showProgressBar() {

    }

    @Override
    public void hideProgressBar() {

    }

    @Override
    public void startActivity() {
        startActivity(new Intent(this, StartActivityTest.class));
    }

    @Override
    public void showEvents(List<Event> events) {
        adapter.notifyDataChanged(events);
    }

    @Override
    protected void onResume() {
        presenter.attach(this);
        presenter.getEvents();
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        presenter.detach();
        super.onDestroy();
    }

    private void setNavigation() {
        setSupportActionBar(toolbar);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, mainLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        mainLayout.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.activity_events_nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setCheckedItem(getNavItem());
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.nav_map) {
            startActivity(new Intent(this, MainActivity.class));
        } else if (item.getItemId() == R.id.nav_friends) {
            startActivity(new Intent(this, FriendsListActivity.class));
        } else if(item.getItemId() == R.id.nav_logout){
            presenter.logoutUser();
        }
        mainLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
}
