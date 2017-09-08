package com.example.dominik.evfinders.model.base.home.register;

import com.example.dominik.evfinders.database.pojo.network.ApiKeyResponse;
import com.example.dominik.evfinders.database.pojo.User;
import com.example.dominik.evfinders.database.pojo.network.UserRequest;
import com.example.dominik.evfinders.model.api.RegisterService;
import com.example.dominik.evfinders.model.repo.IPrefs;
import com.example.dominik.evfinders.model.repo.Prefs;

import javax.inject.Inject;

import io.reactivex.Observable;
import retrofit2.Response;

/**
 * Created by Dominik on 06.09.2017.
 */

public class RegisterRepository implements IRegisterRepository {

    private IPrefs prefs;
    private RegisterService service;

    @Inject
    public RegisterRepository(IPrefs prefs, RegisterService service) {
        this.prefs = prefs;
        this.service = service;
    }

    @Override
    public Observable<Response<ApiKeyResponse>> getRegisterResponse(UserRequest userRequest) {
        return service.register(userRequest);
    }

    @Override
    public void saveKey(ApiKeyResponse key) {
        prefs.save(Prefs.API_KEY, key.getValue());
    }
}
