package com.example.dominik.evfinders.mvp.profile;

import com.example.dominik.evfinders.database.pojo.ProfileItem;
import com.example.dominik.evfinders.model.base.home.profile.IProfileRepository;
import com.example.dominik.evfinders.model.base.home.profile.ProfileRepository;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by Dominik on 07.11.2017.
 */

public class ProfilePresenter implements ProfileContract.Presenter {

    private ProfileContract.View view;
    private IProfileRepository repository;

    @Inject
    public ProfilePresenter(IProfileRepository repository) {
        this.repository = repository;
    }

    @Override
    public void onAttach(ProfileContract.View view) {
        if (this.view == null){
            this.view = view;
        }
    }

    @Override
    public void onDetach() {
        this.view = null;
    }

    @Override
    public void getProfiles() {
        List<ProfileItem> allProfiles = repository.getAllProfiles();
        view.onLoadProfiles(allProfiles);
    }
}
