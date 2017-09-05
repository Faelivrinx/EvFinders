package com.example.dominik.evfinders.base;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.dominik.evfinders.R;

import butterknife.BindView;

/**
 * Created by Dominik on 22.06.2017.
 */

public abstract class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getContentView());
        onViewReady(savedInstanceState, getIntent());
    }


    protected void onViewReady(Bundle savedInstanceState, Intent intent) {

    }


    protected abstract int getContentView();

}
