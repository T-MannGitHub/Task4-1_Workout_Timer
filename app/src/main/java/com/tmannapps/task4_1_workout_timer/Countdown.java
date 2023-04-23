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
    CountDownTimer workCountdownTimer, restCountdownTimer;
    // CountDownTimer restCountdownTimer;
    Boolean timerRunning = false;
    Boolean resting = true;
    public static long timeLeft;
    public static long restLeft;
    int i = 0; //work iteration variable
    int j = 0; //rest iteration variable
    TextView myTextViewRemainingTime, myTextViewPhase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_countdown);

        myStartStopButton = findViewById(R.id.myStartStopButton);
        myResetButton = findViewById(R.id.myResetButton);
        myResetButton.setBackgroundColor(Color.GRAY);

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
                        i += 1;
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
            // get value from main
            //if the time has previously been started and not finished, don't reset the value
            if (i == 0) {
                Intent workIntent = getIntent();
                final long[] workDurationLong = {workIntent.getLongExtra("workDuration", 0)};
                timeLeft = workDurationLong[0];
            }
            workCountdownTimer = new CountDownTimer(timeLeft, 1000) {
                public void onTick(long millisUntilFinished) {
                    timeLeft = millisUntilFinished;
                    NumberFormat f = new DecimalFormat("00");
                    long hour = (millisUntilFinished / 3600000) % 24;
                    long min = (millisUntilFinished/ 60000) % 60;
                    long sec = (millisUntilFinished / 1000) % 60;
                    myTextViewRemainingTime.setText(f.format(hour) + ":" + f.format(min) + ":" + f.format(sec));
                    myTextViewPhase.setText(getString(R.string.WorkPhase));
                    myResetButton.setBackgroundColor(Color.GREEN);
                }
                public void onFinish() {
                    myStartStopButton.setText(getString(R.string.Start));
                    myTextViewRemainingTime.setText(getString(R.string.endTimer));
                    setRestPhaseTimer();
                    i = 0;
                }
            }.start();
            timerRunning = true;
            myStartStopButton.setText(getString(R.string.Stop));
            myResetButton.setBackgroundColor(Color.GRAY);
        } catch (Exception e) {
            Toast.makeText(Countdown.this, "Error in startWorkTimer()", Toast.LENGTH_SHORT).show();
        }

    }
    public void setRestPhaseTimer() {
        try {
            if (j == 0) {
                // get intent from here for rest
                Intent restIntent = getIntent();
                final long[] restDurationLong = {restIntent.getLongExtra("restDuration", 0)};
                restLeft = restDurationLong[0];
            }
        restCountdownTimer = new CountDownTimer(restLeft, 1000) {
            public void onTick(long millisUntilFinished) {
                restLeft = millisUntilFinished;
                updateRestTimer();
                myTextViewPhase.setText(getString(R.string.RestPhase));
                j += 1;
            }
            public void updateRestTimer () {
                NumberFormat f = new DecimalFormat("00");
                long hour = (restLeft / 3600000) % 24;
                long min = (restLeft / 60000) % 60;
                long sec = (restLeft / 1000) % 60;
                myTextViewRemainingTime.setText(f.format(hour) + ":" + f.format(min) + ":" + f.format(sec));
            }

            public void onFinish() {
                myTextViewPhase.setText(getString(R.string.EndPhase));
                myTextViewRemainingTime.setText(getString(R.string.endTimer));
                timerRunning = false;
                j = 0;
            }
        }.start(); }
        catch (Exception e) {
            Toast.makeText(Countdown.this, "error in setRestPhaseTimer", Toast.LENGTH_SHORT).show();
        }
    }
    private void pauseTimer() {
        try {
            //pause function not working in rest phase
            resting = true;

            workCountdownTimer.cancel();
            timerRunning = false;
            myStartStopButton.setText(getString(R.string.Start));
            myResetButton.setBackgroundColor(Color.GRAY);
            NumberFormat f = new DecimalFormat("00");
            long hour = (timeLeft / 3600000) % 24;
            long min = (timeLeft / 60000) % 60;
            long sec = (timeLeft / 1000) % 60;
            myTextViewRemainingTime.setText(f.format(hour) + ":" + f.format(min) + ":" + f.format(sec));
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
        private void resetTimer () {

            NumberFormat f = new DecimalFormat("00");
            int hrs = (int) (timeLeft/3600000) % 24;
            int mins = (int) (timeLeft/6000) % 60;
            int sec = (int) (timeLeft/1000) % 60;
            myTextViewRemainingTime.setText(f.format(hrs) + ":" + f.format(mins) + ":" + f.format(sec));
            myResetButton.setBackgroundColor(Color.GREEN);
        }

}