package com.example.dominik.evfinders.model.repo;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;

import javax.inject.Inject;

/**
 * Created by Dominik on 05.09.2017.
 */

public class Prefs implements IPrefs {
    public static final String TAG = Prefs.class.getSimpleName();

    public static final String API_KEY = "API_KEY";
    public static final String FCM_TOKEN = "FCM_TOKEN";
    public static final String RECOMMENDATION_TYPE = "RECOMMENDATION_TYPE";


    private final Context application;
    private final SharedPreferences preferences;
    private SharedPreferences.Editor editor;

    @Inject
    public Prefs(Context context) {
        this.application = context;
        preferences = PreferenceManager.getDefaultSharedPreferences(context);
        editor = preferences.edit();
    }

    @Override

    public void save(String key, String value) {
        Log.d(TAG, "save key: " + key + " wit value: " + value);
        editor.putString(key, value);
        editor.apply();
    }


    @Override
    public void save(String key, int value) {
        Log.d(TAG, "save key: " + key + " wit value: " + value);
        editor.putInt(key, value);
        editor.apply();
    }

    @Override
    public String get(String key) {
        return preferences.getString(key, "");
    }

    @Override
    public int get(String key, int defaultValue) {
        return preferences.getInt(key, defaultValue);
    }

    @Override
    public boolean del(String key) {
        editor.remove(key);
        editor.apply();
        return true;
    }
}
