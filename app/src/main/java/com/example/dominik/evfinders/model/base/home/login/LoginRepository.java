package com.example.dominik.evfinders.model.base.home.login;

import com.example.dominik.evfinders.database.pojo.ApiKeyResponse;
import com.example.dominik.evfinders.model.api.LoginService;
import com.example.dominik.evfinders.model.repo.IPrefs;

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
    public Observable<String> getLoginReponse() {
        Observable<ApiKeyResponse> test = service.getToken();
        return null;
    }

    @Override
    public void saveKey(String key) {

    }
}
