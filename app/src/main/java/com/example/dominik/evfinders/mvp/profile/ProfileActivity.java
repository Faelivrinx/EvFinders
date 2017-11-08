package com.example.dominik.evfinders.mvp.profile;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.dominik.evfinders.R;
import com.example.dominik.evfinders.base.BaseActivity;
import com.example.dominik.evfinders.database.pojo.ProfileItem;

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

public class ProfileActivity extends BaseActivity implements ProfileContract.View{

    @BindView(R.id.activity_profile_create_viewPager)
    ViewPager viewPager;

    @BindView(R.id.activity_profile_create_tabLayout)
    TabLayout tabLayout;

    @BindView(R.id.activity_profile_create_recyclerView)
    RecyclerView recyclerView;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @Inject
    ProfileContract.Presenter presenter;

    private SelectedProfileAdapter adapter;

    private List<ProfileItem> currentProfiles = new ArrayList<>();
    private List<ProfileItem> selectedItems = new ArrayList<>();

    @Override
    protected void onViewReady(Bundle savedInstanceState, Intent intent) {
        AndroidInjection.inject(this);
        super.onViewReady(savedInstanceState, intent);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);

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
        // TODO: 08.11.2017 Create request to server with update!
        if (item.getItemId() == R.id.action_profile_add){
            Toast.makeText(this, "Are you sure to add ?", Toast.LENGTH_SHORT).show();
        }
        return true;
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_profile_create;
    }

    @Override
    public void showMessage(String message) {

    }

    @Override
    public void onLoadProfiles(List<ProfileItem> profileItems) {
        currentProfiles.clear();
        currentProfiles.addAll(profileItems);
    }

    public List<ProfileItem> getProfilesItem(){
        return currentProfiles;
    }

    public void setItemToSelected(ProfileItem item){
        selectedItems.add(item);
        logSelectedList();
        adapter.notifyDataSetChanged();
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

    public List<ProfileItem> getSelectedItems() {
        return selectedItems;
    }

    public void removeItemFromSelected(ProfileItem profileItem) {
        for (Iterator<ProfileItem> iterator = selectedItems.listIterator(); iterator.hasNext();) {
            ProfileItem item = iterator.next();
            if (item.getId() == profileItem.getId()){
                iterator.remove();
            }
        }

        adapter.notifyDataSetChanged();
        logSelectedList();
    }

    private void logSelectedList(){
        for (ProfileItem selectedItem : selectedItems) {
            Log.e("TEST", selectedItem.getName());
        }
    }
}
