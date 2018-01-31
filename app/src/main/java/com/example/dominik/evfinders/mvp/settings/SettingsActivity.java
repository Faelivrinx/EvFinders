package com.example.dominik.evfinders.mvp.settings;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.TextView;

import com.example.dominik.evfinders.R;
import com.example.dominik.evfinders.base.BaseAuthActivity;
import com.example.dominik.evfinders.mvp.events.EventsActivity;
import com.example.dominik.evfinders.mvp.friends.FriendsListActivity;
import com.example.dominik.evfinders.mvp.home.MainActivity;
import com.example.dominik.evfinders.mvp.profile.ProfileActivity;
import com.example.dominik.evfinders.mvp.start_test.StartActivityTest;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import dagger.android.AndroidInjection;

/**
 * Created by Dominik on 13.12.2017.
 */

public class SettingsActivity extends BaseAuthActivity implements SettingsContract.View, NavigationView.OnNavigationItemSelectedListener {

    @BindView(R.id.activity_settings_rbNoRec)
    RadioButton rbNoRec;

    @BindView(R.id.activity_settings_rbProfileRec)
    RadioButton rbProfileRec;

    @BindView(R.id.activity_settings_rbUserBased)
    RadioButton rbUserBased;

    @BindView(R.id.activity_settings_rbUserCount)
    RadioButton rbUserCount;

    @BindView(R.id.activity_settings_radioGroup)
    RadioGroup radioGroup;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.activity_settings_drawer_layout)
    DrawerLayout drawerLayout;

    @BindView(R.id.activity_settings_radius_result)
    TextView tvResultRadius;

    @BindView(R.id.activity_settings_radius)
    SeekBar seekBar;

    @Inject
    SettingsContract.Presenter presenter;


    @Override
    protected void onViewReady(Bundle savedInstanceState, Intent intent) {
        AndroidInjection.inject(this);
        ButterKnife.bind(this);

        setNavigation();
        super.onViewReady(savedInstanceState, intent);


        radioGroup.setOnCheckedChangeListener((radioGroup1, id) -> {
            if (id == R.id.activity_settings_rbNoRec) {
                presenter.changeRecommendation(0);
            } else if (id == R.id.activity_settings_rbProfileRec) {
                presenter.changeRecommendation(1);
            } else if (id == R.id.activity_settings_rbUserBased) {
                presenter.changeRecommendation(2);
            } else {
                presenter.changeRecommendation(3);
            }
        });
        seekBar.setProgress(presenter.getRadius());
        tvResultRadius.setText(presenter.getRadius() + " KM");

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                tvResultRadius.setText(i + "KM");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                presenter.setRadius(seekBar.getProgress());
            }
        });
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_settings;
    }

    @Override
    protected int getNavItem() {
        return R.id.nav_settings;
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.attach(this);
        presenter.loadActualType();
    }

    @Override
    protected void onDestroy() {
        presenter.detach();
        super.onDestroy();
    }

    @Override
    public void onActualStateLoaded(int type) {
        switch (type){
            case 0:
                rbNoRec.toggle();
                break;
            case 1:
                rbProfileRec.toggle();
                break;
            case 2:
                rbUserBased.toggle();
                break;
            case 3:
                rbUserCount.toggle();
                break;
        }
    }

    @Override
    public void startLoginActivity() {
        startActivity(new Intent(this, StartActivityTest.class));
    }

    private void setNavigation() {
        setSupportActionBar(toolbar);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.activity_settings_nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setCheckedItem(getNavItem());
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        drawerLayout.closeDrawer(GravityCompat.START);
        if (item.getItemId() == R.id.nav_logout) {
            presenter.logout();
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
        }else if(item.getItemId() == R.id.nav_map){
            Intent intent = new Intent(this, MainActivity.class);
            intent.putExtra(BaseAuthActivity.DRAWER_ITEM, R.id.nav_settings);
            startActivity(intent);
        }
        return true;
    }
}
