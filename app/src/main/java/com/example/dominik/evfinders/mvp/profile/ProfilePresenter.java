package com.example.dominik.evfinders.mvp.profile;

import android.content.Intent;

import com.example.dominik.evfinders.application.DeleteToken;
import com.example.dominik.evfinders.converters.ProfileConverter;
import com.example.dominik.evfinders.database.pojo.ProfileItem;
import com.example.dominik.evfinders.database.pojo.network.TaskResponse;
import com.example.dominik.evfinders.model.base.home.profile.IProfileRepository;
import com.example.dominik.evfinders.model.base.home.profile.ProfileRepository;

import java.net.ConnectException;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import javax.inject.Inject;

import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Response;

/**
 * Created by Dominik on 07.11.2017.
 */

public class ProfilePresenter implements ProfileContract.Presenter {

    private ProfileContract.View view;
    private IProfileRepository repository;
    private ProfileConverter profileConverter;

    @Inject
    public ProfilePresenter(IProfileRepository repository, ProfileConverter converter) {
        this.profileConverter = converter;
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

    @Override
    public void updateProfile(List<ProfileItem> profileItems) {
        Single<Response<TaskResponse>> responseSingle = repository.saveProfile(profileConverter.convertArrayToJson(profileItems));
        responseSingle
                .timeout(10, TimeUnit.SECONDS)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::checkResponse, this::checkError);
    }

    private void checkResponse(Response<TaskResponse> taskResponseResponse) {
        if (taskResponseResponse.isSuccessful()){
            view.showMessage("Profil zaktualizowany!");
        } else {
            view.showMessage("Nie można zaktualizować profilu!");
        }
    }

    private void checkError(Throwable throwable) {
        if (throwable instanceof TimeoutException)
            view.showMessage("Zbyt długie nawiązywanie połączenia.");
        else if (throwable instanceof ConnectException)
            view.showMessage("Brak połączenia z serwerem!");
    }

    @Override
    public void logout() {
        new DeleteToken().execute();
        repository.removeFcmToken();
        if (repository.removeUserKey()) {
            view.startLoginActivity();
        }
    }
}
