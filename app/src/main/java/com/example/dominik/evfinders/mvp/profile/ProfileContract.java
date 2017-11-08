package com.example.dominik.evfinders.mvp.profile;

import com.example.dominik.evfinders.database.pojo.ProfileItem;

import java.util.List;

/**
 * Created by Dominik on 07.11.2017.
 */

public interface ProfileContract {

    interface View {

        void showMessage(String message);
        void onLoadProfiles(List<ProfileItem> profileItems);

    }

    interface Presenter {

        void onAttach(View view);
        void onDetach();

        void getProfiles();
    }
}
