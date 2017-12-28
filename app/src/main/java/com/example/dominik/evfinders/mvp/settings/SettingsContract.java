package com.example.dominik.evfinders.mvp.settings;

/**
 * Created by Dominik on 13.12.2017.
 */

public interface SettingsContract {

    interface View {
        void onActualStateLoaded(int type);
    }

    interface Presenter {
        void attach(View view);
        void detach();
        void changeRecommendation(int type);
        void loadActualType();
    }
}