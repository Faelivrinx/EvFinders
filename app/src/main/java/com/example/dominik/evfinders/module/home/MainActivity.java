package com.example.dominik.evfinders.module.home;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.dominik.evfinders.R;
import com.example.dominik.evfinders.application.Authorization;
import com.example.dominik.evfinders.base.BaseActivity;
import com.example.dominik.evfinders.base.BaseAuthActivity;
import com.example.dominik.evfinders.di.component.DaggerAuthorizationComponent;
import com.example.dominik.evfinders.di.module.AuthorizationModule;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseAuthActivity {


    @Override
    protected void onViewReady(Bundle savedInstanceState, Intent intent) {
        super.onViewReady(savedInstanceState, intent);
        ButterKnife.bind(this);

    }

    @Override
    protected void resolveDepedency() {
        DaggerAuthorizationComponent.builder()
                .applicationComponent(getApplicationComponent())
                .authorizationModule(new AuthorizationModule())
                .build().inject(this);
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_main;
    }
}
