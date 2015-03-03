package com.example.doug.lab4act;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;import com.example.doug.lab4act.R;import java.lang.Override;import java.lang.String;

public class ManualInputActivity extends FragmentActivity implements DatePickerDialog.OnDateSetListener, TimePickerDialog.OnTimeSetListener,DialogInterface.OnCancelListener {

    Button dateButton;
    Button timeButton;
    Button durationButton;
    Button distanceButton;
    Button caloriesButton;
    Button hrButton;
    Button commentsButton;

    FragmentManager fm = getSupportFragmentManager();

    @Override
    public void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Get the view from activity_main.xml
        setContentView(R.layout.manual_input_activity);

        // Locate the button in activity_main.xml

        dateButton = (Button) findViewById(R.id.date_button);
        timeButton = (Button) findViewById(R.id.time_button);
        durationButton = (Button) findViewById(R.id.duration_Button);
        distanceButton = (Button) findViewById(R.id.distance_Button);
        caloriesButton = (Button) findViewById(R.id.calories_Button);
        hrButton = (Button) findViewById(R.id.hr_Button);
        commentsButton = (Button) findViewById(R.id.comment_Button);

        // Capture button clicks
        dateButton.setOnClickListener(new OnClickListener() {
            public void onClick(View arg0) {
                ManualInputFragment dateFragment = new ManualInputFragment();
                // Show DialogFragment
                dateFragment.show(fm, "Date Fragment");
            }
        });


        // Capture button clicks
        timeButton.setOnClickListener(new OnClickListener() {
            public void onClick(View arg0) {
                ManualInputTimeFragment timeFragment = new ManualInputTimeFragment();
                // Show DialogFragment
                timeFragment.show(fm, "Time Fragment");
            }
        });
        durationButton.setOnClickListener(new OnClickListener() {
            public void onClick(View arg0) {
                Dialog durationFragment = onCreateDialog(savedInstanceState,"Duration (Minutes):");
                // Show DialogFragment
                durationFragment.show();
            }
        });
        distanceButton.setOnClickListener(new OnClickListener() {
            public void onClick(View arg0) {
               Dialog distanceFragment =  onCreateDialog(savedInstanceState,"Distance (Miles):");
                // Show DialogFragment

                distanceFragment.show();
            }
        });
        caloriesButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog caloriesFragment = onCreateDialog(savedInstanceState,"Calories");
                //Show dialogFragment
                caloriesFragment.show();
            }
        });
        hrButton.setOnClickListener(new OnClickListener() {
            public void onClick(View arg0) {
                Dialog hrFragment =  onCreateDialog(savedInstanceState,"Heart Rate:");
                // Show DialogFragment
                hrFragment.show();
            }
        });
        commentsButton.setOnClickListener(new OnClickListener() {
            public void onClick(View arg0) {
                Dialog commentsFragment =  onCreateDialog(savedInstanceState,"Comments:");
                // Show DialogFragment
                commentsFragment.show();
            }
        });








    /*static EncryptionDialogFragment newInstance(String title){
        EncryptionDialogFragment fragment = new EncryptionDialogFragment();
        Bundle args = new Bundle();
        args.putString("title", title);
        fragment.setArguments(args);
        return fragment;
    }*/



    }
   public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth){

   }
   public void onTimeSet(TimePicker view, int hourOfDay, int minute){

   }
    public void onCancel(DialogInterface dialog){

    }
    public Dialog onCreateDialog(Bundle savedInstanceState,String title) {
        final EditText input = new EditText(this);
        return new AlertDialog.Builder(this)
                .setIcon(R.drawable.ic_launcher)
                .setTitle(title)
                .setView(input)
                .setPositiveButton("OK",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,
                                                int whichButton) {
                                //this.doPositiveClick();
                            }
                        })
                .setNegativeButton("Cancel",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,
                                                int whichButton) {
                               // ((MainActivity) getActivity()).doNegativeClick();
                            }
                        }).create();
    }
}