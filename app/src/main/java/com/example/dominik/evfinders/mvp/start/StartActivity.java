package com.example.dominik.evfinders.mvp.start;

import android.app.Notification;
import android.app.RemoteInput;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.Log;
import android.widget.Toast;

import com.example.dominik.evfinders.R;
import com.example.dominik.evfinders.base.BaseActivity;
import com.google.android.gms.cast.framework.media.NotificationAction;
import com.google.android.gms.iid.InstanceID;
import com.google.firebase.iid.FirebaseInstanceId;

import java.io.IOException;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.OnClick;
import dagger.android.AndroidInjection;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

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

    @RequiresApi(api = Build.VERSION_CODES.KITKAT_WATCH)
    @OnClick(R.id.activity_start_test)
    public void onTestClick(){
        RemoteInput remoteInput = new RemoteInput.Builder("key_text_reply")
                .setLabel("Reply")
                .build();

        Notification.Action action = new Notification.Action.Builder(R.mipmap.ic_friends, "Friends",)
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

    @Override
    public void onBackPressed() {
        //super.onBackPressed();
    }

    @Override
    protected void onStart() {
        presenter.attach(this);
        presenter.generateFCMToken();
        super.onStart();
    }

    @Override
    protected void onDestroy() {
        presenter.detach();
        super.onDestroy();
    }
}
