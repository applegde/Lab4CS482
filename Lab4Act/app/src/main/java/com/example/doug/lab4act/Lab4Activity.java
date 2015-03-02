package com.example.doug.lab4act;

import android.net.Uri;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;

import java.util.ArrayList;

import com.example.doug.lab4act.view.SlidingTabLayout;

public class Lab4Activity extends FragmentActivity implements Settings.OnFragmentInteractionListener, Start.OnFragmentInteractionListener, History.OnFragmentInteractionListener{
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
        fragments.add(new History());
        fragments.add(new Settings());
        fragments.add(new Start());

        // use FragmentPagerAdapter to bind the slidingTabLayout (tabs with different titles)
        // and ViewPager (different pages of fragment) together.
        myViewPageAdapter = new Lab4TabsViewPagerAdapter(getSupportFragmentManager(), fragments);
        viewPager.setAdapter(myViewPageAdapter);

        // make sure the tabs are equally spaced.
        slidingTabLayout.setDistributeEvenly(true);
        slidingTabLayout.setViewPager(viewPager);
    }

    //Implementing interfaces
    public void onFragmentInteractionStart(Uri uri)
    {

    }
    public void onFragmentInteractionSettings(Uri uri)
    {

    }
    public void onFragmentInteractionHistory(Uri uri)
    {

    }
}