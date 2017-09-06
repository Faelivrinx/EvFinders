package com.example.dominik.evfinders.model.base.home.login;

import com.example.dominik.evfinders.database.pojo.ApiKeyResponse;
import com.example.dominik.evfinders.model.api.LoginService;
import com.example.dominik.evfinders.model.repo.IPrefs;
import com.example.dominik.evfinders.model.repo.Prefs;

import javax.inject.Inject;

import io.reactivex.Observable;

/**
 * Created by Dominik on 05.09.2017.
 */

public class LoginRepository implements ILoginRepository{

   private IPrefs prefs;
   private LoginService service;

    @Inject
    public LoginRepository(IPrefs prefs, LoginService service) {
        this.prefs = prefs;
        this.service = service;
    }

    @Override
    public Observable<ApiKeyResponse> getLoginResponse(String username, String password) {
        return service.getToken(username, password).filter(apiKeyResponse -> apiKeyResponse != null);
    }

    @Override
    public void saveKey(ApiKeyResponse key) {
        prefs.save(Prefs.API_KEY, key.getValue());
    }
}
