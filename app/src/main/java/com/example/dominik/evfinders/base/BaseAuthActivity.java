package com.example.dominik.evfinders.base;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.CallSuper;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.example.dominik.evfinders.application.Authorization;
import com.example.dominik.evfinders.application.EvApplication;
import com.example.dominik.evfinders.di.component.ApplicationComponent;
import com.example.dominik.evfinders.di.component.AuthorizationComponent;
import com.example.dominik.evfinders.di.component.DaggerAuthorizationComponent;
import com.example.dominik.evfinders.di.module.AuthorizationModule;
import com.example.dominik.evfinders.module.home.MainActivity;
import com.example.dominik.evfinders.module.login.LoginActivity;

import javax.inject.Inject;

/**
 * Created by Dominik on 22.06.2017.
 */

public abstract class BaseAuthActivity extends AppCompatActivity{

    AuthorizationComponent authorizationComponent;

    @Inject
    Authorization authorization;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getContentView());
        onViewReady(savedInstanceState, getIntent());

        checkAuthorization();
    }

    private void checkAuthorization() {
        if(authorization.hasAuthorization()){
            // setResult(RESULT_OK);
            //  return;
        } else {
            startActivity(new Intent(this, LoginActivity.class));
        }
    }


    protected void onViewReady(Bundle savedInstanceState, Intent intent) {
        resolveDepedency();

    }

    protected void resolveDepedency(){
        authorizationComponent = DaggerAuthorizationComponent.builder()
                .applicationComponent(getApplicationComponent())
                .authorizationModule(new AuthorizationModule())
                .build();
    }

    protected abstract int getContentView();

    public ApplicationComponent getApplicationComponent(){
        return ((EvApplication) getApplication()).getApplicationComponent();
    }

    public AuthorizationComponent getAuthorizationComponent() {
        return authorizationComponent;
    }

    public Authorization getAuthorization() {
        return authorization;
    }
}
