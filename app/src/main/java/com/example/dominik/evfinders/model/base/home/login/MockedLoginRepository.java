package com.example.dominik.evfinders.model.base.home.login;

import com.example.dominik.evfinders.database.pojo.network.ApiKeyResponse;
import com.example.dominik.evfinders.model.repo.IPrefs;
import com.example.dominik.evfinders.model.repo.Prefs;

import javax.inject.Inject;

import io.reactivex.Observable;

/**
 * Created by Dominik on 05.09.2017.
 */

public class MockedLoginRepository implements ILoginRepository {
    private IPrefs prefs;

    @Inject
    public MockedLoginRepository(IPrefs prefs) {
        this.prefs = prefs;
    }

    @Override
    public Observable<ApiKeyResponse> getLoginResponse(String username, String password,String fcm_token) {
        ApiKeyResponse response = new ApiKeyResponse();
        response.setName("api_key");
        response.setValue("vd32dfas$#@$fdsg$%#dvs");


        return Observable.fromArray(response);
    }

    @Override
    public void saveKey(ApiKeyResponse value) {
        prefs.save(Prefs.API_KEY, value.getValue());
    }

    @Override
    public String getFcmToken() {
        return null;
    }
}
