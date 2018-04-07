package ite.kmitl.airnose.fragment;

import android.app.Dialog;
import android.app.DialogFragment;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.Calendar;

import ite.kmitl.airnose.R;

/**
 * Created by ASUS-PC on 24/12/2560.
 */


public class TimePickerFragment extends DialogFragment implements TimePickerDialog.OnTimeSetListener {

    String time="";
    TextView tvTime;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the current time as the default values for the picker
        final Calendar c = Calendar.getInstance();
        int hour = c.get(Calendar.HOUR_OF_DAY);
        int minute = c.get(Calendar.MINUTE);
        // Create a new instance of TimePickerDialog and return it
        return new TimePickerDialog(getActivity(), this, hour, minute,
                DateFormat.is24HourFormat(getActivity()));
    }

    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        // Do something with the time chosen by the user
        tvTime =(TextView)getActivity().findViewById(R.id.tvTime);
        setTimeToAmPm(hourOfDay,minute);
        tvTime.setText(time);
    }

    // set format time
    private void setTimeToAmPm(int hourOfDay ,int minute) {
        String period="";
        String stringHourOfDay="";
        String stringMinute=minute+"";
        if(hourOfDay%24 < 12){
            period = "AM";
        }
        else if(hourOfDay%24 >= 12){
            period = "PM";
        }
        hourOfDay = hourOfDay%12;
        if (hourOfDay == 0){
            hourOfDay = 12;
        }
        stringHourOfDay = ""+hourOfDay;
        if(hourOfDay < 10){
            stringHourOfDay = "0"+hourOfDay;
        }
        if(minute < 10){
            stringMinute = "0"+minute;
        }
        time = stringHourOfDay+" : "+stringMinute+" "+period;
    }

    // get time
    public String getTime(){
        return time;
    }


}
