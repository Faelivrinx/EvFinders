package com.example.dominik.evfinders.mvp.settings;

import com.example.dominik.evfinders.application.DeleteToken;
import com.example.dominik.evfinders.model.repo.IPrefs;
import com.example.dominik.evfinders.model.repo.Prefs;

import javax.inject.Inject;

/**
 * Created by Dominik on 13.12.2017.
 */

public class SettingsPresenter implements SettingsContract.Presenter {
    private static final String TAG = SettingsPresenter.class.getSimpleName();
    private SettingsContract.View view;
    private IPrefs prefs;

    @Inject
    public SettingsPresenter(IPrefs prefs) {
        this.prefs = prefs;
    }

    @Override
    public void attach(SettingsContract.View view) {
        if (this.view == null) {
            this.view = view;
        }
    }

    @Override
    public void detach() {
        view = null;
    }

    @Override
    public void changeRecommendation(int type) {
        prefs.save(Prefs.RECOMMENDATION_TYPE, type);
    }

    @Override
    public void loadActualType() {
        view.onActualStateLoaded(prefs.get(Prefs.RECOMMENDATION_TYPE, 0));
    }

    @Override
    public void logout() {
        new DeleteToken().execute();
        prefs.del(Prefs.FCM_TOKEN);
        if (prefs.del(Prefs.FCM_TOKEN)) {
            view.startLoginActivity();
        }
    }
}
