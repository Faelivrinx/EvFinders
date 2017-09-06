package com.example.dominik.evfinders.mvp.login;

import android.app.AlertDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dominik.evfinders.R;
import com.example.dominik.evfinders.base.BaseActivity;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import dagger.android.AndroidInjection;

/**
 * Created by Dominik on 23.06.2017.
 */

public class LoginActivity extends BaseActivity implements LoginContract.View{

    @BindView(R.id.activity_login_etUsername)    EditText etUsername;
    @BindView(R.id.activity_login_etPassword)    EditText etPassword;

    @Inject LoginPresenter presenter;

    private AlertDialog alertDialog;
    private AlertDialog.Builder alertDialogBuilder;
    private TextView alertMessage;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        AndroidInjection.inject(this);
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        createAlertDialog();

    }

    private void createAlertDialog() {
        alertDialogBuilder = new AlertDialog.Builder(this);
        View dialogView = getLayoutInflater().inflate(R.layout.alert_dialog, null);
        alertDialogBuilder.setView(dialogView);
        alertDialog = alertDialogBuilder.create();
        alertMessage = (dialogView).findViewById(R.id.alarm_dialog_message);
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_login;
    }

    @OnClick(R.id.activity_login_btnLogin)
    public void loginBtnClicked(){
        presenter.login(etUsername.getText().toString(), etPassword.getText().toString());
    }

    @Override
    public void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void hideProgressDialog() {
        alertMessage.setText("");
        alertDialog.dismiss();
    }

    @Override
    public void showProgressDialog() {
        alertMessage.setText("Getting key");
        alertDialog.show();
    }

    //LifeCycle Region

    @Override
    protected void onStart() {
        presenter.attach(this);
        super.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        presenter.detach();
        alertDialog.dismiss();
        super.onDestroy();
    }

}
