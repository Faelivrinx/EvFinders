package com.example.dominik.evfinders.mvp.events;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.Menu;
import android.view.MenuItem;

import com.example.dominik.evfinders.R;
import com.example.dominik.evfinders.base.BaseAuthActivity;
import com.example.dominik.evfinders.command.EventCommand;
import com.example.dominik.evfinders.database.pojo.Event;
import com.example.dominik.evfinders.mvp.friends.FriendsAdapter;
import com.example.dominik.evfinders.mvp.friends.FriendsListActivity;
import com.example.dominik.evfinders.mvp.home.MainActivity;
import com.example.dominik.evfinders.mvp.home.event.EventActivity;
import com.example.dominik.evfinders.mvp.start_test.StartActivityTest;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import dagger.android.AndroidInjection;

public class EventsActivity extends BaseAuthActivity implements EventsContract.View, NavigationView.OnNavigationItemSelectedListener {
    public static final String TAG = EventsActivity.class.getSimpleName();
    public static final String CHOOSE_EVENT = "CHOOSE_EVENT";


    @BindView(R.id.activity_events_mainLayout)
    DrawerLayout mainLayout;

    @BindView(R.id.activity_events_recyclerView)
    RecyclerView recyclerView;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @Inject
    EventsPresenter presenter;

    private EventsAdapter adapter;
    private List<EventCommand> events;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AndroidInjection.inject(this);
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        setNavigation();
        events = new ArrayList<>();

        adapter = new EventsAdapter(getLayoutInflater(), this);
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
        recyclerView.setAdapter(adapter);

        ItemTouchHelper.SimpleCallback simpleCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                final int position = viewHolder.getAdapterPosition();
                removeItemOnSwipe(direction, position);
            }
        };
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleCallback);
        itemTouchHelper.attachToRecyclerView(recyclerView);
    }

    private void removeItemOnSwipe(int direction, int position) {
        if (direction == ItemTouchHelper.LEFT || direction == ItemTouchHelper.RIGHT) {
            adapter.notifyItemRemoved(position);
            adapter.getEvents().remove(position);
            adapter.notifyItemRangeChanged(0, adapter.getEvents().size());
        }
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
    public void showEvents(List<EventCommand> events) {
        this.events.clear();
        this.events.addAll(events);
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
        getMenuInflater().inflate(R.menu.blank, menu);
        return true;
    }
}
