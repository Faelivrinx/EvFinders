package com.example.dominik.evfinders.base;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.example.dominik.evfinders.application.Authorization;
import com.example.dominik.evfinders.application.EvApplication;
import com.example.dominik.evfinders.di.component.ApplicationComponent;

import javax.inject.Inject;

/**
 * Created by Dominik on 22.06.2017.
 */

public abstract class BaseAuthActivity extends AppCompatActivity {
    private static final String TAG = BaseAuthActivity.class.getSimpleName();

    @Inject
    Authorization authorization;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getContentView());
        onViewReady(savedInstanceState, getIntent());

        // checkAuthorization();
    }

    // FIXME: 8/11/2017 implement working version of check authorization
    private void checkAuthorization() {
//        if(authorization.hasAuthorization()){
//            // setResultRESULT_OK);
//            //  return;
//        } else {
//            startActivity(new Intent(this, LoginActivity.class));
//        }
    }


    protected void onViewReady(Bundle savedInstanceState, Intent intent) {
        this.resolveDepedency();
    }

    protected abstract void resolveDepedency();

    protected abstract int getContentView();

    public ApplicationComponent getApplicationComponent() {
        return ((EvApplication) getApplication()).getApplicationComponent();
    }

}
