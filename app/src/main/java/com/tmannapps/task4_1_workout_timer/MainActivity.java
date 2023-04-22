package com.tmannapps.task4_1_workout_timer;

import androidx.annotation.StringRes;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.text.DecimalFormat;
import java.text.NumberFormat;

public class MainActivity extends AppCompatActivity {

    Button myStartStopButton;
    TextView myTextViewTitle, myTextViewSetDuration, myTextViewRestDuration, myTextViewRemainingTime, myTextViewNumSets, myTextViewPhase;
    EditText myEditTextSetDuration, myEditTextRestPeriod, myEditTextNumSets;
    ProgressBar myProgressBar;
    int i = 1;

    public void setValue () {
    String setDurationStr = myEditTextSetDuration.getText().toString();
    long setDurationLong = Integer.parseInt(setDurationStr)*1000;
    new CountDownTimer(setDurationLong, 1000) {
        //TODO click stop to stop timer mid way
        //todo end of finish - reset i to 1 and stop process
        public void onTick(long millisUntilFinished) {
            NumberFormat f = new DecimalFormat("00");
            long hour = (millisUntilFinished / 3600000) % 24;
            long min = (millisUntilFinished / 60000) % 60;
            long sec = (millisUntilFinished / 1000) % 60;
            myTextViewPhase.setText(getString(R.string.WorkPhase));
            myTextViewRemainingTime.setText(f.format(hour) + ":" + f.format(min) + ":" + f.format(sec));
        }
        public void onFinish() {
            String numSetsStr = myEditTextNumSets.getText().toString();
            int numSetsInt = Integer.parseInt(numSetsStr);
            restValue();
            if (numSetsInt > 1) {
                //i = numSetsInt;
                while (i < numSetsInt) {
                    restValue();
                    setValue();
                    restValue();
                    i += 1;
                }
            }
            myTextViewRemainingTime.setText(getString(R.string.endTimer));

        }
    }.start();
}
public void restValue () {
    String restDurationStr = myEditTextRestPeriod.getText().toString();
    long restDurationLong = Integer.parseInt(restDurationStr)*1000;
    new CountDownTimer(restDurationLong, 1000) {
        public void onTick(long millisUntilFinished) {
            NumberFormat f = new DecimalFormat("00");
            long hour = (millisUntilFinished / 3600000) % 24;
            long min = (millisUntilFinished / 60000) % 60;
            long sec = (millisUntilFinished / 1000) % 60;
            myTextViewPhase.setText(getString(R.string.RestPhase));
            myTextViewRemainingTime.setText(f.format(hour) + ":" + f.format(min) + ":" + f.format(sec));
        }
        public void onFinish() {
            //myTextViewPhase.setText(getString(R.string.EndPhase));
            //myTextViewRemainingTime.setText(getString(R.string.endTimer));
        }
    }.start();
}


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myStartStopButton = findViewById(R.id.myStartStopButton);
        myTextViewTitle = findViewById(R.id.myTextViewTitle);
        myTextViewSetDuration = findViewById(R.id.myTextViewSetDuration);
        myTextViewRestDuration = findViewById(R.id.myTextViewRestDuration);
        myTextViewNumSets = findViewById(R.id.myTextViewNumSets);
        myTextViewRemainingTime = findViewById(R.id.myTextViewRemainingTime);
        myTextViewPhase = findViewById(R.id.myTextViewPhase);
        myEditTextSetDuration = findViewById(R.id.myEditTextSetDuration);
        myEditTextRestPeriod = findViewById(R.id.myEditTextRestPeriod);
        myEditTextNumSets = findViewById(R.id.myEditTextNumSets);
        myProgressBar = findViewById(R.id.myProgressBar);




        myStartStopButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                //start and stop the timers as appropriate
                //To update the UI with the current time remaining, you can use a Handler and a Runnable that is
                //called periodically to update the TextViews and ProgressBar. You can also play a sound or
                //vibrate the device when each phase ends to alert the user.

                //TODO - add error handling for if no values entered

                setValue();
                i = 1;
                myTextViewPhase.setText(getString(R.string.EndPhase));


                //Workout period
                //tutorial for countdown timer found at https://www.geeksforgeeks.org/countdowntimer-in-android-with-example/


            }
        });
    }
}