package ite.kmitl.enose.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import ite.kmitl.enose.R;


@SuppressWarnings("unused")
public class FragmentSmellTime extends Fragment {

    String smellDetails;
    String timeSmell;
    String time;
    String tempTime;
    String smellDetailsTime;
    TimePickerFragment timePickerFragment;
    // set curent time
    Calendar currentTime;
    int hour,minute;
    TextView tvTime;
    SimpleDateFormat simpleDateFormat;

    Button btnSetTime;
    ImageButton imageBtnLessThan1,imageBtnMoreThan1,imageBtnTime15
            ,imageBtnTime30,imageBtnTime45,imageBtnTime60;


    public FragmentSmellTime() {
        super();
    }

    @SuppressWarnings("unused")
    public static FragmentSmellTime newInstance(String smellDetails) {
        FragmentSmellTime fragment = new FragmentSmellTime();
        Bundle args = new Bundle();
        args.putString("smellDetails",smellDetails);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init(savedInstanceState);

        if (savedInstanceState != null)
            onRestoreInstanceState(savedInstanceState);

        smellDetails = getArguments().getString("smellDetails");


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_smell_time, container, false);
        initInstances(rootView, savedInstanceState);
        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        final InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(getView().getWindowToken(), 0);
    }

    private void init(Bundle savedInstanceState) {
        // Init Fragment level's variable(s) here
        timePickerFragment = new TimePickerFragment();
        currentTime = Calendar.getInstance();
        hour = currentTime.get(Calendar.HOUR_OF_DAY);
        minute = currentTime.get(Calendar.MINUTE);
        setTimeToAmPm();
    }

    @SuppressWarnings("UnusedParameters")
    private void initInstances(View rootView, Bundle savedInstanceState) {
        // Init 'View' instance(s) with rootView.findViewById here
        tvTime = (TextView) rootView.findViewById(R.id.tvTime);
        btnSetTime = (Button) rootView.findViewById(R.id.btnSetTime);
        imageBtnLessThan1 = (ImageButton) rootView.findViewById(R.id.imageBtnLessThan1);
        imageBtnMoreThan1 = (ImageButton) rootView.findViewById(R.id.imageBtnMoreThan1);
        imageBtnTime15 = (ImageButton) rootView.findViewById(R.id.imageBtnTime15);
        imageBtnTime30 = (ImageButton) rootView.findViewById(R.id.imageBtnTime30);
        imageBtnTime45 = (ImageButton) rootView.findViewById(R.id.imageBtnTime45);
        imageBtnTime60 = (ImageButton) rootView.findViewById(R.id.imageBtnTime60);
        tvTime.setText(tempTime);
        btnSetTime.setOnClickListener(btnTimeClick);
        imageBtnLessThan1.setOnClickListener(btnSmellTimeClick);
        imageBtnMoreThan1.setOnClickListener(btnSmellTimeClick);
        imageBtnTime15.setOnClickListener(btnSmellTimeClick);
        imageBtnTime30.setOnClickListener(btnSmellTimeClick);
        imageBtnTime45.setOnClickListener(btnSmellTimeClick);
        imageBtnTime60.setOnClickListener(btnSmellTimeClick);

    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    /*
     * Save Instance State Here
     */
    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        // Save Instance State here
    }

    /*
     * Restore Instance State Here
     */
    @SuppressWarnings("UnusedParameters")
    private void onRestoreInstanceState(Bundle savedInstanceState) {
        // Restore Instance State here
    }

    // set format time
    private void setTimeToAmPm() {
        String period="";
        String stringHourOfDay="";
        String stringMinute=minute+"";
        if(hour%24 < 12){
            period = "AM";
        }
        else if(hour%24 >= 12){
            period = "PM";
        }
        hour = hour%12;
        if (hour == 0){
            hour = 12;
        }
        stringHourOfDay = ""+hour;
        if(hour < 10){
            stringHourOfDay = "0"+hour;
        }
        if(minute < 10){
            stringMinute = "0"+minute;
        }
        time = stringHourOfDay+" : "+stringMinute+" "+period;
        tempTime = time;
    }


    /*********
     * On Click Listener
     */
    final View.OnClickListener btnTimeClick = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if(view == btnSetTime){
                timePickerFragment.show(getActivity().getFragmentManager(), "timePicker");
            }
        }
    };

    final View.OnClickListener btnSmellTimeClick = new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            time = timePickerFragment.getTime();
            timeSmell = "";
            if(time.equals("")){
                time = tempTime;
            }
            tempTime = time;
            time = time.replace(" ","_");
            if(view == imageBtnLessThan1){
                timeSmell = getString(R.string.less_than_1_minute);
            }
            if(view == imageBtnMoreThan1){
                timeSmell = getString(R.string.more_than_1_minute);
            }
            if(view == imageBtnTime15){
                timeSmell = getString(R.string.more_than_15_minute);
            }
            if(view == imageBtnTime30){
                timeSmell = getString(R.string.more_than_30_minute);
            }
            if(view == imageBtnTime45){
                timeSmell = getString(R.string.more_than_45_minute);
            }
            if(view == imageBtnTime60){
                timeSmell = getString(R.string.more_than_1_hour);
            }
            timeSmell = timeSmell.replace(" ","_");
            smellDetailsTime = smellDetails+" "+timeSmell+" "+time;
            getFragmentManager().beginTransaction()
                    .replace(R.id.contentContainer,FragmentSmellFeel.newInstance(smellDetailsTime),"FragmentSmellTime")
                    .addToBackStack(null)
                    .commit();
        }
    };
}
