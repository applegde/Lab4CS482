package com.example.doug.lab4act;

import android.content.Intent;
import android.net.Uri;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.view.View;

import java.util.ArrayList;

import com.example.doug.lab4act.view.SlidingTabLayout;

public class Lab4Activity extends ActionBarActivity implements SettingsFragment.OnFragmentInteractionListener,
                                                              StartFragment.OnFragmentInteractionListener,
                                                              HistoryFragment.OnFragmentInteractionListener{
    private SlidingTabLayout slidingTabLayout;
    private ViewPager viewPager;
    private ArrayList<Fragment> fragments;
    private Lab4TabsViewPagerAdapter myViewPageAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lab4);

        // Define SlidingTabLayout (shown at top)
        // and ViewPager (shown at bottom) in the layout.
        // Get their instances.
        slidingTabLayout = (SlidingTabLayout) findViewById(R.id.tab);
        viewPager = (ViewPager) findViewById(R.id.viewpager);

        // create a fragment list in order.
        fragments = new ArrayList<>();
        fragments.add(new StartFragment());
        fragments.add(new HistoryFragment());
        fragments.add(new SettingsFragment());

        // use FragmentPagerAdapter to bind the slidingTabLayout (tabs with different titles)
        // and ViewPager (different pages of fragment) together.
        myViewPageAdapter = new Lab4TabsViewPagerAdapter(getSupportFragmentManager(), fragments);
        viewPager.setAdapter(myViewPageAdapter);

        // make sure the tabs are equally spaced.
        slidingTabLayout.setDistributeEvenly(true);
        slidingTabLayout.setViewPager(viewPager);
    }

    public void onStart(View view)
    {
        Intent intent = new Intent(this, MapActivity.class);
        startActivity(intent);
    }

    //<editor-fold desc="Implementing Interfaces">
    public void onFragmentInteractionStart(Uri uri)
    {

    }
    public void onFragmentInteractionSettings(Uri uri)
    {

    }
    public void onFragmentInteractionHistory(Uri uri)
    {

    }
    //</editor-fold>
}