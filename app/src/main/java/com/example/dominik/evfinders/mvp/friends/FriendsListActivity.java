package com.example.dominik.evfinders.mvp.friends;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.dominik.evfinders.R;
import com.example.dominik.evfinders.base.BaseAuthActivity;
import com.example.dominik.evfinders.database.pojo.Friend;
import com.example.dominik.evfinders.mvp.home.MainActivity;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import dagger.android.AndroidInjection;

/**
 * Created by Dominik on 11.09.2017.
 */

public class FriendsListActivity extends BaseAuthActivity implements FriendsContract.View, NavigationView.OnNavigationItemSelectedListener {

    public static final String TAG = FriendsListActivity.class.getSimpleName();


    @BindView(R.id.activity_friends_list_recyclerView)    RecyclerView recyclerView;
    @BindView(R.id.activity_friends_list_drawer_layout)   DrawerLayout drawerLayout;
    @BindView(R.id.toolbar)                               Toolbar toolbar;

    private AlertDialog alertDialog;
    private AlertDialog.Builder alertDialogBuilder;
    private EditText etDialogUsername;
    private Button btnDialogAccept;
    private Button btnDialogCancel;

    @Inject FriendsPresenter presenter;

    private FriendsAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        AndroidInjection.inject(this);
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        setNavigation();
        adapter = new FriendsAdapter(new ArrayList<>(), getLayoutInflater());
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
        createAlertDialog();

    }

    private void createAlertDialog() {
        alertDialogBuilder = new AlertDialog.Builder(this);
        View dialogView = getLayoutInflater().inflate(R.layout.add_friend_dialog, null);
        alertDialogBuilder.setView(dialogView);
        alertDialog = alertDialogBuilder.create();
        etDialogUsername = dialogView.findViewById(R.id.dialog_add_friend_etUsername);
        btnDialogAccept = dialogView.findViewById(R.id.dialog_add_friend_btnAdd);
        btnDialogCancel = dialogView.findViewById(R.id.dialog_add_friend_btnCancel);

        btnDialogAccept.setOnClickListener(view -> {});
        btnDialogCancel.setOnClickListener(view -> {alertDialog.dismiss();});
    }

//        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.activity_main_map);
//        mapFragment.getMapAsync(this);


    private void setNavigation() {
        setSupportActionBar(toolbar);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawerLayout, toolbar , R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.activity_friends_list_nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setCheckedItem(getNavItem());
    }

    @OnClick(R.id.activity_friends_list_addFriend)
    public void addFriendClicked(){
        alertDialog.show();
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_friends_list;
    }

    @Override
    protected int getNavItem() {
        return R.id.nav_friends;
    }

    @Override
    public void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showDialog(String message) {
        Log.d(TAG, "message: " + message);
    }

    @Override
    public void hideDialog() {

    }

    @Override
    public void onFriendsLoaded(List<Friend> friends) {
        adapter.notifyDataChange(friends);
    }


    @Override
    protected void onStart() {
        presenter.attach(this);
        super.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.getFriendsList();
    }

    @Override
    protected void onDestroy() {
        presenter.detach();
        super.onDestroy();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.nav_map){
            startActivity(new Intent(this, MainActivity.class));
        } else if(item.getItemId() == R.id.nav_friends){
//            startActivity(new Intent(this, FriendsListActivity.class));
        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }
}
