package com.example.dominik.evfinders.model.base.home.login;

import com.example.dominik.evfinders.database.pojo.ApiKeyResponse;

import io.reactivex.Observable;
import io.reactivex.Single;

/**
 * Created by Dominik on 05.09.2017.
 */

public interface ILoginRepository {

    Observable<ApiKeyResponse> getLoginResponse(String username, String password);

    void saveKey(ApiKeyResponse key);
}
