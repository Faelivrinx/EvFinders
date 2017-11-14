package com.example.dominik.evfinders.mvp.profile.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.dominik.evfinders.R;
import com.example.dominik.evfinders.database.pojo.ProfileItem;
import com.example.dominik.evfinders.mvp.profile.ProfileActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dominik on 07.11.2017.
 */

public class SportFragment extends Fragment {

    private RecyclerView recyclerView;
    private ProfileAdapter adapter;
    private ProfileActivity activity;

    private List<ProfileItem> sportItems = new ArrayList<>();

    public SportFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_sport, container, false);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activity = (ProfileActivity) getActivity();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = view.findViewById(R.id.fragment_sport_recyclerView);
        adapter = new ProfileAdapter(getLayoutInflater(), activity);
        if (sportItems.size() == 0) {
            for (ProfileItem profileItem : activity.getProfilesItem()) {
                if (profileItem.getId() > 0L && profileItem.getId() < 12L) {
                    sportItems.add(profileItem);
                }
            }
        }
        adapter.onUpdateItems(sportItems);
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL));
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        ArrayList<Integer> values = new ArrayList<>();
        for (ProfileItem sportItem : sportItems) {
            if (sportItem.isSelected()) {
                values.add(sportItem.getId().intValue());
            }
        }


        outState.putIntegerArrayList("Id", values);
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
