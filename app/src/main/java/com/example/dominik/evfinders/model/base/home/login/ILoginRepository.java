package com.example.dominik.evfinders.model.base.home.login;

import io.reactivex.Observable;
import io.reactivex.Single;

/**
 * Created by Dominik on 05.09.2017.
 */

public interface ILoginRepository {

    Observable<String> getLoginReponse();

    void saveKey(String key);
}
