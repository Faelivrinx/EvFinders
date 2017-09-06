package com.example.dominik.evfinders.model.base.home.register;

import com.example.dominik.evfinders.database.pojo.network.ApiKeyResponse;
import com.example.dominik.evfinders.database.pojo.User;
import com.example.dominik.evfinders.database.pojo.network.UserRequest;

import io.reactivex.Observable;
import retrofit2.Response;

/**
 * Created by Dominik on 06.09.2017.
 */

public interface IRegisterRepository {

    Observable<Response<ApiKeyResponse>> getRegisterResponse(UserRequest user);
    void saveKey(ApiKeyResponse key);
}
