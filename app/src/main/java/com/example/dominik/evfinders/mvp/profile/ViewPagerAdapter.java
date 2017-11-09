package com.example.dominik.evfinders.mvp.profile;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.dominik.evfinders.mvp.profile.fragments.CultureFragment;
import com.example.dominik.evfinders.mvp.profile.fragments.MusicFragment;
import com.example.dominik.evfinders.mvp.profile.fragments.SportFragment;

/**
 * Created by Dominik on 07.11.2017.
 */

public class ViewPagerAdapter extends FragmentPagerAdapter {

    private ProfileActivity activity;

    public ViewPagerAdapter(FragmentManager fm, ProfileActivity context) {
        super(fm);
        this.activity = context;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return new SportFragment();
            case 1:
                return new MusicFragment();
            case 2:
                return new CultureFragment();
            default:
                throw new IllegalStateException("Can't create fragment at this position!");
        }
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position){
            case 0:
                return "Sport";
            case 1:
                return "Muzyka";
            case 2:
                return "Kultura";
            default:
                return "???";
        }
    }
}
