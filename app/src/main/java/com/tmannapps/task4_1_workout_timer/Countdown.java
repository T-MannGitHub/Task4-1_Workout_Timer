package com.tmannapps.task4_1_workout_timer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;
import java.text.NumberFormat;

public class Countdown extends AppCompatActivity {
    Button myStartStopButton, myResetButton, myButtonReturnMain;
    ProgressBar myProgressBar;
    CountDownTimer workCountdownTimer;
    // CountDownTimer restCountdownTimer;
    Boolean timerRunning = false;

    TextView myTextViewRemainingTime, myTextViewPhase;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_countdown);

        myStartStopButton = findViewById(R.id.myStartStopButton);
        myResetButton = findViewById(R.id.myResetButton);
        myResetButton.setBackgroundColor(Color.parseColor("FFD9DFDC"));

        myProgressBar = findViewById(R.id.myProgressBar);
        timerRunning = false;

        myTextViewRemainingTime = findViewById(R.id.myTextViewRemainingTime);
        myTextViewPhase = findViewById(R.id.myTextViewPhase);

        myButtonReturnMain = findViewById(R.id.myButtonReturnMain);
        myButtonReturnMain.setText(getString(R.string.GoCountdownToMain));

        myStartStopButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    if (!timerRunning) {
                        startWorkTimer();
                    } else {
                        pauseTimer();
                    }
                } catch (Exception e) {
                    Toast.makeText(Countdown.this, "error in StartStopOnClick", Toast.LENGTH_SHORT).show();
                }

            }
        }
        );
/*        myResetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetTimer();
            }
        });*/
        }
        private void startWorkTimer() {
        try {
            // get intent from here for workdurationlong
            Intent workIntent = getIntent();
            final long[] workDurationLong = {workIntent.getLongExtra("workDuration", 0)};
            workCountdownTimer = new CountDownTimer(workDurationLong[0], 1000) {
                public void onTick(long millisUntilFinished) {
                    workDurationLong[0] = millisUntilFinished;
                    NumberFormat f = new DecimalFormat("00");
                    long hour = (millisUntilFinished / 3600000) % 24;
                    long min = (millisUntilFinished/ 60000) % 60;
                    long sec = (millisUntilFinished / 1000) % 60;
                    myTextViewRemainingTime.setText(f.format(hour) + ":" + f.format(min) + ":" + f.format(sec));
                    myTextViewPhase.setText(getString(R.string.WorkPhase));
                    myResetButton.setBackgroundColor(Color.parseColor("FF70D8A8"));
                }
                public void onFinish() {
                    myStartStopButton.setText(getString(R.string.Start));
                    myTextViewRemainingTime.setText(getString(R.string.endTimer));
                    setRestPhaseTimer();
                }
            }.start();
            timerRunning = true;
            myStartStopButton.setText(getString(R.string.Stop));
            myResetButton.setBackgroundColor(Color.parseColor("FFD9DFDC"));
        } catch (Exception e) {
            Toast.makeText(Countdown.this, "Error in startWorkTimer()", Toast.LENGTH_SHORT).show();
        }

    }
    public void setRestPhaseTimer() {
        try {
        // get intent from here for rest
        Intent restIntent = getIntent();
        long restDurationLong = restIntent.getLongExtra("restDuration", 0);
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
                timerRunning = false;
            }
        }.start(); }
        catch (Exception e) {
            Toast.makeText(Countdown.this, "error in setRestPhaseTimer", Toast.LENGTH_SHORT).show();
        }
    }
    private void pauseTimer() {
        try {
            //workCountdownTimer.cancel();
            timerRunning = false;
            myStartStopButton.setText(getString(R.string.Start));
            myResetButton.setBackgroundColor(Color.parseColor("FFD9DFDC"));
        } catch (Exception e) {
            Toast.makeText(Countdown.this, "error in pauseTiemr()", Toast.LENGTH_SHORT).show();
        }

    }

    public void returnToMain (View view) {
        try {
            Intent intentReturn = new Intent (this, MainActivity.class);
            startActivity(intentReturn);
        } catch (Exception e) {
            Toast.makeText(Countdown.this, "error in returnToMain()", Toast.LENGTH_SHORT).show();
        }

    }
        /*private void resetTimer () {
            String setDurationStr = myEditTextSetDuration.getText().toString();
            long setDurationLong = Integer.parseInt(setDurationStr) * 1000;
            NumberFormat f = new DecimalFormat("00");
            int hrs = (int) (setDurationLong/3600000) % 24;
            int mins = (int) (setDurationLong/6000) % 60;
            int sec = (int) (setDurationLong/1000) % 60;
            myTextViewRemainingTime.setText(f.format(hrs) + ":" + f.format(mins) + ":" + f.format(sec));
            myResetButton.setBackgroundColor(Color.parseColor("FFD9DFDC"));
        }*/

}