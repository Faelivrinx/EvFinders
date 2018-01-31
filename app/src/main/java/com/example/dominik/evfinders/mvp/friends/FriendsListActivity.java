package com.example.dominik.evfinders.mvp.friends;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.dominik.evfinders.R;
import com.example.dominik.evfinders.base.BaseAuthActivity;
import com.example.dominik.evfinders.database.pojo.Friend;
import com.example.dominik.evfinders.mvp.events.EventsActivity;
import com.example.dominik.evfinders.mvp.home.MainActivity;
import com.example.dominik.evfinders.mvp.profile.ProfileActivity;
import com.example.dominik.evfinders.mvp.settings.SettingsActivity;
import com.example.dominik.evfinders.mvp.start_test.StartActivityTest;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import dagger.android.AndroidInjection;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 * Created by Dominik on 11.09.2017.
 */

public class FriendsListActivity extends BaseAuthActivity implements FriendsContract.View, NavigationView.OnNavigationItemSelectedListener {

    public static final String TAG = FriendsListActivity.class.getSimpleName();

    private Boolean STATE = false;


    @BindView(R.id.activity_friends_list_recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.activity_friends_list_drawer_layout)
    DrawerLayout drawerLayout;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.activity_friends_layout_nullFriends)
    LinearLayout lyFriends;
    @BindView(R.id.activity_friends_list_refresh)
    SwipeRefreshLayout swipeRefreshLayout;

    private AlertDialog alertDialog;
    private AlertDialog.Builder alertDialogBuilder;
    private EditText etDialogUsername;
    private Button btnDialogAccept;
    private Button btnDialogCancel;

    @Inject
    FriendsPresenter presenter;

    private FriendsAdapter adapter;
    CompositeDisposable disposable = new CompositeDisposable();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        AndroidInjection.inject(this);
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        setNavigation();
        adapter = new FriendsAdapter(new ArrayList<>(), getLayoutInflater(), this);
        disposable.add(adapter.getSelectionModeSubject().subscribe(
                selectionMode -> {
                    STATE = selectionMode;
                    invalidateOptionsMenu();
                }
        ));
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
        createAlertDialog();
        swipeRefreshLayout.setOnRefreshListener(() -> {
            presenter.getFriendsList();
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (STATE) {
            getMenuInflater().inflate(R.menu.friend_menu, menu);
            return true;
        } else {
            return false;
        }
    }



    private void createAlertDialog() {
        alertDialogBuilder = new AlertDialog.Builder(this);
        View dialogView = getLayoutInflater().inflate(R.layout.add_friend_dialog, null);
        alertDialogBuilder.setView(dialogView);
        alertDialog = alertDialogBuilder.create();
        etDialogUsername = dialogView.findViewById(R.id.dialog_add_friend_etUsername);
        btnDialogAccept = dialogView.findViewById(R.id.dialog_add_friend_btnAdd);
        btnDialogCancel = dialogView.findViewById(R.id.dialog_add_friend_btnCancel);

        btnDialogAccept.setOnClickListener(view -> {
            presenter.addFriend(etDialogUsername.getText().toString());
            etDialogUsername.setText("");
            alertDialog.dismiss();
        });
        btnDialogCancel.setOnClickListener(view -> {
            alertDialog.dismiss();
        });
    }



    private void setNavigation() {
        setSupportActionBar(toolbar);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.activity_friends_list_nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setCheckedItem(getNavItem());
    }

    @OnClick(R.id.activity_friends_list_addFriend)
    public void addFriendClicked() {
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
        if (friends.size() > 0){
            lyFriends.setVisibility(View.GONE);
        } else {
            onEmptyList();
        }
    }

    @Override
    public void startActivity() {
        startActivity(new Intent(this, StartActivityTest.class));
    }

    @Override
    public void onEmptyList() {
        lyFriends.setVisibility(View.VISIBLE);
    }

    @Override
    public void onFriendsDeleted() {
        STATE = false;
        invalidateOptionsMenu();
    }

    @Override
    public void hideRefresh() {
        swipeRefreshLayout.setRefreshing(false);
    }


    @Override
    protected void onStart() {
        presenter.attach(this);
        super.onStart();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_delete_friend:
                List<Friend> selected = new ArrayList<>();
                for (Friend friend : adapter.getFriendList()) {
                    if (friend.isSelected()) {
                        selected.add(friend);
                    }
                }
                presenter.deleteFriends(selected);
                break;
        }
        return true;
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.getFriendsList();
    }

    @Override
    protected void onDestroy() {
        disposable.dispose();
        presenter.detach();
        super.onDestroy();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.nav_map) {
            startActivity(new Intent(this, MainActivity.class));
        } else if (item.getItemId() == R.id.nav_events) {
            startActivity(new Intent(this, EventsActivity.class));
        } else if (item.getItemId() == R.id.nav_logout) {
            presenter.logoutUser();
        } else if(item.getItemId() == R.id.nav_preferences){
            startActivity(new Intent(this, ProfileActivity.class));
        }else if(item.getItemId() == R.id.nav_settings){
            Intent intent = new Intent(this, SettingsActivity.class);
            startActivity(intent);
        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }
}
