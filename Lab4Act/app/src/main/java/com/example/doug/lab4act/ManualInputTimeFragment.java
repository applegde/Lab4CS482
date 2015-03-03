package com.example.doug.lab4act;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.text.format.Time;
import java.util.Calendar;

/**
 * Created by joshuaspencer on 3/2/15.
 */
public class ManualInputTimeFragment extends ManualInputFragment{

    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the current date as the default date in the picker

        int hourOfDay = Time.HOUR;
        int minute = Time.MINUTE;
        boolean is24HourView = false;

        // Create a new instance of DatePickerDialog and return it

        return new TimePickerDialog(getActivity(), (ManualInputActivity)getActivity(),hourOfDay, minute,  is24HourView);
    }
}

