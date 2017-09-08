package com.example.dominik.evfinders.base;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.example.dominik.evfinders.model.repo.IPrefs;
import com.example.dominik.evfinders.model.repo.Prefs;
import com.example.dominik.evfinders.mvp.login.LoginActivity;
import com.example.dominik.evfinders.mvp.start.StartActivity;

import javax.inject.Inject;

/**
 * Created by Dominik on 22.06.2017.
 */

public abstract class BaseAuthActivity extends AppCompatActivity {
    private static final String TAG = BaseAuthActivity.class.getSimpleName();

    @Inject
    IPrefs prefs;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getContentView());
        onViewReady(savedInstanceState, getIntent());

    }

    protected void onViewReady(Bundle savedInstanceState, Intent intent) {
        checkAuthorization();
    }

    private void checkAuthorization() {
        if(!prefs.get(Prefs.API_KEY).isEmpty()){
            return;
        } else {
            startActivity(new Intent(this, StartActivity.class));
        }
    }

    protected abstract int getContentView();

}
