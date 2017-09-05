package com.example.dominik.evfinders.model.base.home.login;

import com.example.dominik.evfinders.model.repo.IPrefs;
import com.example.dominik.evfinders.model.repo.Prefs;

import java.util.concurrent.TimeUnit;

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
    public Observable<String> getLoginReponse() {
        String secureKey = "F4214DS543AFDSA5435FDS54353AFDSA";

        return Observable.fromArray(secureKey).delay(4, TimeUnit.SECONDS);
    }

    @Override
    public void saveKey(String value) {
        prefs.save(Prefs.API_KEY, value);
    }
}
