package com.example.doug.lab4act;

import android.app.Fragment;
import android.app.FragmentManager;
import android.support.v13.app.FragmentPagerAdapter;

import java.util.ArrayList;

/**
 * Created by Fanglin Chen on 12/18/14.
 * Adapted by Douglas Applegate 3/2/2015
 */

public class Lab4ViewPagerAdapter extends FragmentPagerAdapter {
    private ArrayList<Fragment> fragments;

    public static final int ACTION = 0;
    public static final int HISTORY = 1;
    public static final int SETTINGS = 2;
    public static final String UI_TAB_ACTION = "ACTION";
    public static final String UI_TAB_HISTORY = "HISTORY";
    public static final String UI_TAB_SETTINGS = "SETTINGS";

    public Lab4ViewPagerAdapter(FragmentManager fm, ArrayList<Fragment> fragments){
        super(fm);
        this.fragments = fragments;
    }

    public Fragment getItem(int pos){
        return fragments.get(pos);
    }

    public int getCount(){
        return fragments.size();
    }

    public CharSequence getPageTitle(int position) {
        switch (position) {
            case ACTION
                return UI_TAB_ACTION;
            case HISTORY:
                return UI_TAB_HISTORY;
            case SETTINGS:
                return UI_TAB_SETTINGS;
            default:
                break;
        }
        return null;
    }
}
