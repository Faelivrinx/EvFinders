package com.example.dominik.evfinders.mvp.start_test;

import android.animation.ObjectAnimator;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.v7.widget.AppCompatButton;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dominik.evfinders.R;
import com.example.dominik.evfinders.application.DeleteToken;
import com.example.dominik.evfinders.base.BaseActivity;
import com.example.dominik.evfinders.mvp.home.MainActivity;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import dagger.android.AndroidInjection;

/**
 * Created by Dominik on 10.10.2017.
 */

public class StartActivityTest extends BaseActivity implements StartActivityTestContract.View {

    public static final int START_STATE = 0;
    public static final int LOGIN_STATE = 1;
    public static final int REGISTER_STATE = 2;

    @BindView(R.id.activity_start_start)
    LinearLayout mainLayout;

    @BindView(R.id.activity_start_login)
    LinearLayout loginLayout;
    @BindView(R.id.activity_start_test_backView)
    LinearLayout backView;

    @BindView(R.id.activity_start_start_btnLogin)
    AppCompatButton btnStartLogin;

    @BindView(R.id.activity_start_login_etPassword)
    TextInputEditText etLoginPassword;

    @BindView(R.id.activity_start_login_etUsername)
    TextInputEditText etLoginUsername;

    @BindView(R.id.activity_start_register)
    LinearLayout registerLayout;

    @BindView(R.id.activity_start_register_etUsername)
    TextInputEditText etRegisterUsername;
    @BindView(R.id.activity_start_register_etPassword)
    TextInputEditText etRegisterPassword;
    @BindView(R.id.activity_start_register_etEmail)
    TextInputEditText etRegisterEmail;

    @BindView(R.id.imageView3)
    ImageView imageView;

    @BindView(R.id.activity_start_test_status)
    TextView tvStatus;

    @Inject
    StartActivityTestPresenter presenter;


    private AnimationFactory animationFactory;
    private int current_state;
    private AlertDialog alertDialog;
    private AlertDialog.Builder alertDialogBuilder;
    private TextView alertMessage;
    private View currentView;
    private boolean readyToLogin = false;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        AndroidInjection.inject(this);
        current_state = 0;
        getWindow().setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN
        );
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        animationFactory = new AnimationFactoryImp();
        createAlertDialog();
    }

    @OnClick(R.id.activity_start_login_btnLogin)
    public void loginBtnClicked() {
        if (readyToLogin){
            presenter.login(etLoginUsername.getText().toString(), etLoginPassword.getText().toString());
        } else {
            new DeleteToken().execute();
            showProgressDialog("Getting fcm token...");
        }
    }

    @OnClick(R.id.activity_start_register_btnRegister)
    public void onRegisterClick() {
        presenter.register(
                etRegisterUsername.getText().toString(),
                etRegisterPassword.getText().toString(),
                etRegisterEmail.getText().toString());
    }

    @OnClick(R.id.activity_start_start_btnLogin)
    public void onLoginClicked() {
        etLoginUsername.setText("");
        etLoginPassword.setText("");

        imageView.setBackgroundResource(R.drawable.background);
        changeCurrentState(LOGIN_STATE);
        AnimationDrawable frameAnimation = (AnimationDrawable) imageView.getBackground();
        frameAnimation.start();
        ObjectAnimator animator = ObjectAnimator.ofFloat(mainLayout, "x", -1000);
        animator.setDuration(400);
        animator.start();

        loginLayout.setVisibility(View.VISIBLE);
        ObjectAnimator animationLogin = ObjectAnimator.ofFloat(loginLayout, "x", 1500, 16);
        animationLogin.setDuration(400);
        animationLogin.start();
        backView.setVisibility(View.VISIBLE);
    }

    @OnClick(R.id.activity_start_start_register)
    public void onRegister() {
        etRegisterUsername.setText("");
        etRegisterPassword.setText("");
        etRegisterEmail.setText("");

        current_state = REGISTER_STATE;
        imageView.setBackgroundResource(R.drawable.background);
        changeCurrentState(REGISTER_STATE);
        AnimationDrawable frameAnimation = (AnimationDrawable) imageView.getBackground();
        frameAnimation.start();
        ObjectAnimator animator = ObjectAnimator.ofFloat(mainLayout, "x", -1000);
        animator.setDuration(400);
        animator.start();

        registerLayout.setVisibility(View.VISIBLE);
        ObjectAnimator animationLogin = ObjectAnimator.ofFloat(registerLayout, "x", 1500, 16);
        animationLogin.setDuration(400);
        animationLogin.start();
        backView.setVisibility(View.VISIBLE);
    }

    @OnClick(R.id.back_animation)
    public void onBackClicked() {
        AnimationState state = animationFactory.createState(current_state);
        hideKeyboard(getWindow().getCurrentFocus());
        switch (state) {
            case START_STATE:
                break;
            case LOGIN_STATE:
                imageView.setBackgroundResource(R.drawable.background_to_left);
                AnimationDrawable leftAnimation = (AnimationDrawable) imageView.getBackground();
                leftAnimation.start();

                ObjectAnimator animator = ObjectAnimator.ofFloat(mainLayout, "x", 0);
                animator.setDuration(400);
                animator.start();

                ObjectAnimator animatorlogin = ObjectAnimator.ofFloat(loginLayout, "x", 1500);
                animatorlogin.setDuration(400);
                animatorlogin.start();
                backView.setVisibility(View.GONE);
                break;
            case REGISTER_STATE:
                imageView.setBackgroundResource(R.drawable.background_to_left);
                AnimationDrawable leftRegAnim = (AnimationDrawable) imageView.getBackground();
                leftRegAnim.start();

                ObjectAnimator regAnim = ObjectAnimator.ofFloat(mainLayout, "x", 0);
                regAnim.setDuration(400);
                regAnim.start();

                ObjectAnimator animatorRegi = ObjectAnimator.ofFloat(registerLayout, "x", 1500);
                animatorRegi.setDuration(400);
                animatorRegi.start();
                backView.setVisibility(View.GONE);
                break;

            default:
                break;
        }
    }


    private void changeCurrentState(int newState) {
        current_state = newState;
    }

    private void createAlertDialog() {
        alertDialogBuilder = new AlertDialog.Builder(this);
        View dialogView = getLayoutInflater().inflate(R.layout.alert_dialog, null);
        alertDialogBuilder.setView(dialogView);
        alertDialog = alertDialogBuilder.create();
        alertMessage = (dialogView).findViewById(R.id.alarm_dialog_message);
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
    public void showProgressDialog(String message) {
        alertMessage.setText(message);
        alertDialog.show();
    }

    @Override
    public void startActivity() {
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }

    @Override
    public void setLoginStatus(boolean isReady) {
        readyToLogin = isReady;
        if (isReady){
            tvStatus.setText("OK");
            tvStatus.setTextColor(Color.GREEN);
        } else {
            tvStatus.setText("NOT OK");
            tvStatus.setTextColor(Color.RED);
        }
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_start_test;
    }

    @Override
    protected void onResume() {
        presenter.attach(this);
        currentView = this.getCurrentFocus();
        presenter.checkLoginState();
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        presenter.detach();
        super.onDestroy();
    }

    @Override
    public void onBackPressed() {
        onBackClicked();
    }

    public void hideKeyboard(View view) {
        InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
        assert inputMethodManager != null;
        if (view != null) {
            inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }
}

