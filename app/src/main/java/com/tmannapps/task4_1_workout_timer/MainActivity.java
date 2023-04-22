package com.tmannapps.task4_1_workout_timer;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.text.NumberFormat;

public class MainActivity extends AppCompatActivity {

    Button myStartStopButton, myResetButton;
    TextView myTextViewTitle, myTextViewSetDuration, myTextViewRestDuration, myTextViewRemainingTime, myTextViewNumSets, myTextViewPhase;
    EditText myEditTextSetDuration, myEditTextRestPeriod, myEditTextNumSets;
    ProgressBar myProgressBar;
    CountDownTimer workCountdownTimer;
   // CountDownTimer restCountdownTimer;
    Boolean timerRunning;


    //tutorial for countdown timer found at https://www.geeksforgeeks.org/countdowntimer-in-android-with-example/
    //tutorial for pause function from https://www.youtube.com/watch?v=MDuGwI6P-X8&t=92s

  /*  public void setRestPhaseTimer() {
        String restDurationStr = myEditTextRestPeriod.getText().toString();
        long restDurationLong = Integer.parseInt(restDurationStr) * 1000;
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
                myTextViewRemainingTime.setText(getString(R.string.endTimer));
            }
        }.start();
    }*/

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
        myResetButton = findViewById(R.id.myResetButton);
        myResetButton.setBackgroundColor(Color.parseColor("FFD9DFDC"));


        myStartStopButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (timerRunning) {
                    stopTimer();
                } else {
                        startWorkTimer();
                    }
                }
            }
            );
        myResetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetTimer();
            }
        });
        }

        private void startWorkTimer() {
            String setDurationStr = myEditTextSetDuration.getText().toString();
            long setDurationLong = Integer.parseInt(setDurationStr) * 1000;
            workCountdownTimer = new CountDownTimer(setDurationLong, 1000) {
                public void onTick(long millisUntilFinished) {
                    NumberFormat f = new DecimalFormat("00");
                    long hour = (millisUntilFinished / 3600000) % 24;
                    long min = (millisUntilFinished/ 60000) % 60;
                    long sec = (millisUntilFinished / 1000) % 60;
                    myTextViewRemainingTime.setText(f.format(hour) + ":" + f.format(min) + ":" + f.format(sec));
                    myTextViewPhase.setText(getString(R.string.WorkPhase));
                    myResetButton.setBackgroundColor(Color.parseColor("FF70D8A8"));
            }
            public void onFinish() {
                timerRunning = false;
                myStartStopButton.setText(getString(R.string.Start));
                myTextViewRemainingTime.setText(getString(R.string.endTimer));
                //setRestPhaseTimer();
            }
        }.start();
            timerRunning = true;
            myStartStopButton.setText(getString(R.string.Stop));
            myResetButton.setBackgroundColor(Color.parseColor("FFD9DFDC"));
        }

        private void stopTimer () {
            workCountdownTimer.cancel();
            timerRunning = false;
            myStartStopButton.setText(getString(R.string.Start));
            myResetButton.setBackgroundColor(Color.parseColor("FFD9DFDC"));
        }

        private void resetTimer () {
            String setDurationStr = myEditTextSetDuration.getText().toString();
            long setDurationLong = Integer.parseInt(setDurationStr) * 1000;
            NumberFormat f = new DecimalFormat("00");
            int hrs = (int) (setDurationLong/3600000) % 24;
            int mins = (int) (setDurationLong/6000) % 60;
            int sec = (int) (setDurationLong/1000) % 60;
            myTextViewRemainingTime.setText(f.format(hrs) + ":" + f.format(mins) + ":" + f.format(sec));
            myResetButton.setBackgroundColor(Color.parseColor("FFD9DFDC"));
        }
}

