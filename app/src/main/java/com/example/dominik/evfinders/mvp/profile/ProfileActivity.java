package com.example.dominik.evfinders.mvp.profile;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.dominik.evfinders.R;
import com.example.dominik.evfinders.base.BaseActivity;
import com.example.dominik.evfinders.base.BaseAuthActivity;
import com.example.dominik.evfinders.database.pojo.ProfileItem;
import com.example.dominik.evfinders.mvp.events.EventsActivity;
import com.example.dominik.evfinders.mvp.friends.FriendsListActivity;
import com.example.dominik.evfinders.mvp.home.MainActivity;
import com.example.dominik.evfinders.mvp.settings.SettingsActivity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import dagger.android.AndroidInjection;

/**
 * Created by Dominik on 07.11.2017.
 */
public class ProfileActivity extends BaseAuthActivity implements ProfileContract.View, NavigationView.OnNavigationItemSelectedListener {

    @BindView(R.id.activity_profile_create_viewPager)
    ViewPager viewPager;

    @BindView(R.id.activity_profile_create_tabLayout)
    TabLayout tabLayout;

    @BindView(R.id.activity_profile_create_recyclerView)
    RecyclerView recyclerView;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.activity_profile_create_drawerLayout)
    DrawerLayout drawerLayout;

    @Inject
    ProfileContract.Presenter presenter;

    private SelectedProfileAdapter adapter;

    private List<ProfileItem> currentProfiles = new ArrayList<>();

    @Override
    protected void onViewReady(Bundle savedInstanceState, Intent intent) {
        AndroidInjection.inject(this);
        super.onViewReady(savedInstanceState, intent);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        setNavigation();

        adapter = new SelectedProfileAdapter(getLayoutInflater(), this);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager(), this);
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);

    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.profile_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_profile_add){
            presenter.updateProfile(currentProfiles);
        }
        return true;
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_profile_create;
    }

    @Override
    protected int getNavItem() {
        return R.id.nav_preferences;
    }

    @Override
    public void showMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onLoadProfiles(List<ProfileItem> profileItems) {
        currentProfiles.clear();
        currentProfiles.addAll(profileItems);
    }

    public List<ProfileItem> getProfilesItem(){
        return currentProfiles;
    }

    @Override
    protected void onResume() {
        presenter.onAttach(this);
        presenter.getProfiles();
        super.onResume();
    }


    @Override
    protected void onDestroy() {
        presenter.onDetach();
        super.onDestroy();
    }

    public void updateItemsAdapter(){
        adapter.updateItems();
    }

    public SelectedProfileAdapter getAdapter() {
        return adapter;
    }

    private void setNavigation() {
        setSupportActionBar(toolbar);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.activity_profile_create_nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setCheckedItem(getNavItem());
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
        } else if(item.getItemId() == R.id.nav_map){
            Intent intent = new Intent(this, MainActivity.class);
            intent.putExtra(BaseAuthActivity.DRAWER_ITEM, R.id.nav_map);
            startActivity(intent);
        }else if(item.getItemId() == R.id.nav_settings){
            Intent intent = new Intent(this, SettingsActivity.class);
            startActivity(intent);
        }
        return true;
    }

    private void onLogoutClicked() {
        // TODO: 01.12.2017 implement
        presenter.logout();
    }
}
