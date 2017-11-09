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
 * Created by Dominik on 08.11.2017.
 */

public class CultureFragment extends Fragment {

    private RecyclerView recyclerView;
    private ProfileAdapter adapter;
    private ProfileActivity activity;

   private List<ProfileItem> cultureItems = new ArrayList<>();

    public CultureFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_culture, container, false);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activity = (ProfileActivity) getActivity();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = view.findViewById(R.id.fragment_culture_recyclerView);
        adapter = new ProfileAdapter(getLayoutInflater(), activity);
        for (ProfileItem profileItem : activity.getProfilesItem()) {
            if (profileItem.getId() > 23 && profileItem.getId() < 31){
                cultureItems.add(profileItem);
            }
        }
        adapter.onUpdateItems(cultureItems);
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL));
        recyclerView.setAdapter(adapter);
    }
}
