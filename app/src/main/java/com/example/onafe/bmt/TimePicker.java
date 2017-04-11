package com.example.onafe.bmt;

import android.app.Dialog;
import android.app.DialogFragment;
import android.app.TimePickerDialog;
import android.icu.util.Calendar;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.widget.TextView;

import java.util.Locale;

/**
 * Created by onafe on 03/04/2017.
 */

public class TimePicker extends DialogFragment
        implements TimePickerDialog.OnTimeSetListener {
        int tipoOrario;
    public TimePicker() {
        // Required empty public constructor
    }



    public  TimePicker (int orario){
            tipoOrario=orario;
            }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the current time as the default values for the picker
        final Calendar calendar = Calendar.getInstance();
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);

        // Create a new instance of TimePickerDialog and return it
        return new TimePickerDialog(getActivity(), this, hour, minute,
                DateFormat.is24HourFormat(getActivity()));
    }

    public void onTimeSet(android.widget.TimePicker view, int hourOfDay, int minute) {
            // Do something with the time chosen by the user
            TextView entrata= (TextView) getActivity().findViewById(tipoOrario);
            entrata.setText(hourOfDay+" : "+minute);

            }

   
}
