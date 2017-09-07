package com.example.dominik.evfinders.mvp.start;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.Toast;

import com.example.dominik.evfinders.R;
import com.example.dominik.evfinders.base.BaseActivity;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.OnClick;
import dagger.android.AndroidInjection;

/**
 * Created by Dominik on 07.09.2017.
 */

public class StartActivity extends BaseActivity implements StartContract.View{

    @Inject StartPresenter presenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        AndroidInjection.inject(this);
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_start;
    }

    @OnClick(R.id.activity_start_btnRegister)
    @Override
    public void onRegisterClick() {
        presenter.startRegisterActivity(this);
    }

    @OnClick(R.id.activity_start_btnLogin)
    @Override
    public void onLoginClick() {
        presenter.startLoginActivity(this);
    }

    @Override
    public void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showDialog(String message) {

    }

    @Override
    public void hideDialog() {

    }
}
