package com.tmannapps.task4_1_workout_timer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;
import java.text.NumberFormat;

//Tiffany Mann Task 4.1 SID:221457972
public class Countdown extends AppCompatActivity {
    Button myStartStopButton, myResetButton, myButtonReturnMain;
    ProgressBar myProgressBar;
    CountDownTimer workCountdownTimer, restCountdownTimer;
    Boolean TimerRunning = false;
    public static long timeLeft;
    public static long restLeft;
    public static int numSetsInt;
    String numSets;
    public int setsLeftInt;
    public String setsLeftString;

    int i = 0; //work iteration variable
    int j = 0; //rest iteration variable
    int setsDone = 0;
    MediaPlayer mediaPlayer = null;
    TextView myTextViewRemainingTime, myTextViewPhase, myTextViewSets;
    int progress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_countdown);

        myStartStopButton = findViewById(R.id.myStartStopButton);
        myResetButton = findViewById(R.id.myResetButton);

        myProgressBar = findViewById(R.id.myProgressBar);
        TimerRunning = false;

        myTextViewRemainingTime = findViewById(R.id.myTextViewRemainingTime);
        myTextViewPhase = findViewById(R.id.myTextViewPhase);
        myTextViewSets = findViewById(R.id.myTextViewSets);

        myButtonReturnMain = findViewById(R.id.myButtonReturnMain);
        myButtonReturnMain.setText(getString(R.string.GoCountdownToMain));

        mediaPlayer = MediaPlayer.create(this, R.raw.sonic);

        myStartStopButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    //while setsDone <= numsetsInt
                    // run the start timer -> run rest timer
                    // set the sets left text view to numSetsInt - setsDone
                    if (!TimerRunning) {
                        //check which phase was going and start that timer TODO
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

        myResetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetTimer();
                if (TimerRunning){
                workCountdownTimer.cancel();
                setRestPhaseTimer(); //get a null value exception if you reset before the rest phase as the rest timer hasn't been created
                restCountdownTimer.cancel();}
                startWorkTimer();
                myTextViewSets.setText("Sets left: " + numSets);
                setsLeftInt = numSetsInt;
            }
        });
        }

        private void startWorkTimer() {
        try {
            // get value from main
            //if the time has previously been started and not finished, don't reset the value
            if (i == 0) {
                Intent workIntent = getIntent();
                final long[] workDurationLong = {workIntent.getLongExtra("workDuration", 0)};
                timeLeft = workDurationLong[0];
                getSets();
            }

            workCountdownTimer = new CountDownTimer(timeLeft, 1000) {
                public void onTick(long millisUntilFinished) {
                    timeLeft = millisUntilFinished;
                    updateWorkTimer();
                    myTextViewPhase.setText(getString(R.string.WorkPhase));
                }
                public void onFinish() {
                    mediaPlayer.start();
                    myStartStopButton.setText(getString(R.string.Start));
                    myTextViewRemainingTime.setText(getString(R.string.endTimer));
                    setsDone += 1;
                    i = 0;
                    setRestPhaseTimer();
                    progress = (setsDone%numSetsInt)*100;
                    myProgressBar.incrementProgressBy(progress);

                }
            }.start();
            TimerRunning = true;
            i += 1;
            myStartStopButton.setText(getString(R.string.Stop));
        }
        catch (Exception e) {
            Toast.makeText(Countdown.this, "error in work countdown timer()", Toast.LENGTH_SHORT).show();
        }
    }
    private void getSets() {
        Intent setIntent = getIntent();
        numSets = setIntent.getStringExtra("numSets"); //from Main - user input
        numSetsInt = Integer.parseInt(numSets); //user input sets to int so can operate
        setsLeftInt = numSetsInt - setsDone; //calculating number of sets left to go
        setsLeftString = String.valueOf(setsLeftInt); //turning num sets left back to string so can put in textView
        myTextViewSets.setText("Sets left: " + setsLeftString); //sets the sets left text view to num sets left
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
            }
            public void onFinish() {
                myTextViewRemainingTime.setText(getString(R.string.endTimer));
                TimerRunning = false;
                mediaPlayer.start();
                j = 0;
                getSets();
                for (int k = 1; k < setsLeftInt; k ++) {
                    startWorkTimer();
                    k += 1;}
            }
        }.start();
            myStartStopButton.setText(getString(R.string.Stop));
            j += 1;
        }
        catch (Exception e) {
            Toast.makeText(Countdown.this, "error in setRestPhaseTimer", Toast.LENGTH_SHORT).show();
        }
    }
    public void updateWorkTimer () {
        NumberFormat f = new DecimalFormat("00");
        long hour = (timeLeft / 3600000) % 24;
        long min = (timeLeft / 60000) % 60;
        long sec = (timeLeft / 1000) % 60;
        myTextViewRemainingTime.setText(f.format(hour) + ":" + f.format(min) + ":" + f.format(sec));
    }

    public void updateRestTimer () {
        NumberFormat f = new DecimalFormat("00");
        long hour = (restLeft / 3600000) % 24;
        long min = (restLeft / 60000) % 60;
        long sec = (restLeft / 1000) % 60;
        myTextViewRemainingTime.setText(f.format(hour) + ":" + f.format(min) + ":" + f.format(sec));
    }
    private void pauseTimer() {
        try {
            //pause function not restarting after pause in rest phase: TODO
            if (i == 0) {
                restCountdownTimer.cancel();
                updateRestTimer();
            } else if (j == 0) {
                workCountdownTimer.cancel();
                updateWorkTimer();
            }
            TimerRunning = false;
            myStartStopButton.setText(getString(R.string.Start));
        } catch (Exception e) {
            Toast.makeText(Countdown.this, "error in pauseTimer()", Toast.LENGTH_SHORT).show();
        }
    }

    public void returnToMain (View view) {
        try {
            Intent intentReturn = new Intent (this, MainActivity.class);
            startActivity(intentReturn);
            //mediaPlayer.release();
        } catch (Exception e) {
            Toast.makeText(Countdown.this, "error in returnToMain()", Toast.LENGTH_SHORT).show();
        }
    }
        private void resetTimer() {
            i = 0;
            j = 0;
        }
}