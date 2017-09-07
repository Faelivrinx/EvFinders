package com.example.dominik.evfinders.mvp.register;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dominik.evfinders.R;
import com.example.dominik.evfinders.base.BaseActivity;
import com.example.dominik.evfinders.mvp.home.MainActivity;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import dagger.android.AndroidInjection;

/**
 * Created by Dominik on 06.09.2017.
 */

public class RegisterActivity extends BaseActivity implements RegisterContract.View {

    @BindView(R.id.activity_register_etUsername)
    EditText etUsername;
    @BindView(R.id.activity_register_etPassword)
    EditText etPassword;
    @BindView(R.id.activity_register_etEmail)
    EditText etEmail;


    @Inject
    RegisterPresenter presenter;

    private AlertDialog alertDialog;
    private AlertDialog.Builder alertDialogBuilder;
    private TextView alertMessage;

    @OnClick(R.id.activity_register_btnRegister)
    public void onRegisterClick() {
        presenter.register(
                etUsername.getText().toString(),
                etPassword.getText().toString(),
                etEmail.getText().toString());
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        AndroidInjection.inject(this);
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        createAlertDialog();
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_register;
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
        alertMessage.setText("Register...");
        alertDialog.show();
    }

    private void createAlertDialog() {
        alertDialogBuilder = new AlertDialog.Builder(this);
        View dialogView = getLayoutInflater().inflate(R.layout.alert_dialog, null);
        alertDialogBuilder.setView(dialogView);
        alertDialog = alertDialogBuilder.create();
        alertMessage = (dialogView).findViewById(R.id.alarm_dialog_message);
    }

    @Override
    protected void onResume() {
        presenter.attach(this);
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        presenter.detach();
        super.onDestroy();
    }

    @Override
    public void startActivity() {
        startActivity(new Intent(this, MainActivity.class));
    }
}
