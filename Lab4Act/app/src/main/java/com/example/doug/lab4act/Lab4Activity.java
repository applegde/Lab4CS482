package com.example.doug.lab4act;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.text.InputType;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.doug.lab4act.view.SlidingTabLayout;

import java.util.ArrayList;

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
        Intent intent = new Intent(this, ManualInputActivity.class);
        startActivity(intent);
    }

    public void goSettingActivity(View view){
        Intent intent = new Intent(this, SettingActivity.class);
        startActivity(intent);
    }

    public void privacyCheck(View view){
        CheckBox checkBox = (CheckBox) findViewById(R.id.checky);
        if(checkBox.isChecked()){
            checkBox.setChecked(false);
        }
        else{
            checkBox.setChecked(true);
        }
    }

    public void spawn_pref(View view){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Unit Preference");

        //add layout
        LinearLayout layout = new LinearLayout(getApplicationContext());
        layout.setOrientation(LinearLayout.VERTICAL);

        //set up input
        RadioGroup radioGroup = new RadioGroup(this);
        RadioButton metric = new RadioButton(this);
        RadioButton imperial = new RadioButton(this);

        radioGroup.setOrientation(RadioGroup.VERTICAL);

        metric.setText("Metric(Kilometers)");
        imperial.setText("Imperial(Miles)");


        radioGroup.addView(metric);
        radioGroup.addView(imperial);

        //add cancel
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        layout.addView(radioGroup);
        builder.setView(layout);
        builder.show();

    }

    public void ask_comment(View view){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Comment");

        //add layout
        LinearLayout layout = new LinearLayout(getApplicationContext());
        layout.setOrientation(LinearLayout.VERTICAL);

        //Set edit Text
        EditText editText = new EditText(this);

        //set input type
        editText.setInputType(InputType.TYPE_CLASS_TEXT);

        //set hint
        editText.setHint("Any suggestions for this course?");

        //add cancel
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        //setLayout
        layout.addView(editText);
        builder.setView(layout);
        builder.show();
    }

    public void go_web(View view){
        Uri webpage = Uri.parse("http://www.cs.dartmouth.edu/~campbell");
        Intent launch = new Intent(Intent.ACTION_VIEW, webpage);
        startActivity(launch);
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