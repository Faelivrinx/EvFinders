package com.example.dominik.evfinders.application;

import android.content.SharedPreferences;

import com.example.dominik.evfinders.di.component.AuthorizationComponent;
import com.example.dominik.evfinders.di.component.DaggerAuthorizationComponent;
import com.example.dominik.evfinders.di.module.AuthorizationModule;

import javax.inject.Inject;

/**
 * Created by Dominik on 22.06.2017.
 */

public class Authorization {

    public static final String AUTH_USER = "AUTH_USER";
    private SharedPreferences preferences;

    public Authorization(SharedPreferences preferences) {
        this.preferences = preferences;
    }

    public boolean hasAuthorization() {
        if (preferences != null) {
            String loginInformation = preferences.getString(AUTH_USER, null);
            if (loginInformation != null && !loginInformation.isEmpty()) {
                return true;
            }
        }
        return false;
    }

    public void setAuthorization(String authInfo){
        if (preferences != null && authInfo != null) {
            SharedPreferences.Editor editor = preferences.edit();
            editor.putString(AUTH_USER, authInfo);
            editor.apply();
            editor.commit();
        }
    }

    public void authClear(){
        SharedPreferences.Editor editor = preferences.edit();
        editor.clear();
        editor.apply();
        editor.clear();
    }

}
