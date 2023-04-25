package com.tmannapps.task4_1_workout_timer;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    Button myGoQueenButton;
    TextView myTextViewTitle, myTextViewWorkDuration, myTextViewRestDuration,  myTextViewNumSets;
    EditText myEditTextWorkDuration, myEditTextRestPeriod, myEditTextNumSets;


    int workInput;
    int restInput;
    int setsInput;
    String numSetsString;
    //public static int workTime;
    //public static int restTime;

    //tutorial for countdown timer found at https://www.geeksforgeeks.org/countdowntimer-in-android-with-example/
    //tutorial for pause function from https://www.youtube.com/watch?v=MDuGwI6P-X8&t=92s
    public void goQueen () {
        try {
            String workDurationStr = myEditTextWorkDuration.getText().toString();
            long workDurationLong = Integer.parseInt(workDurationStr) * 1000;
            workInput = Integer.parseInt(workDurationStr);
            //workTime = workInput;

            String restDurationStr = myEditTextRestPeriod.getText().toString();
            long restDurationLong = Integer.parseInt(restDurationStr) * 1000;
            restInput = Integer.parseInt(restDurationStr);
            //restTime = restInput;

            numSetsString = myEditTextNumSets.getText().toString();
            //setsInput = myEditTextNumSets.getInputType();


            Intent intentGoQueen = new Intent(this, Countdown.class);
            intentGoQueen.putExtra("workDuration", workDurationLong);
            intentGoQueen.putExtra("restDuration", restDurationLong);
            intentGoQueen.putExtra("numSets", numSetsString);
            startActivity(intentGoQueen);
        } catch (Exception e) {
            Toast.makeText(MainActivity.this, "Please input values", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myTextViewTitle = findViewById(R.id.myTextViewTitle);
        myTextViewWorkDuration = findViewById(R.id.myTextViewWorkDuration);
        myTextViewRestDuration = findViewById(R.id.myTextViewRestDuration);
        myTextViewNumSets = findViewById(R.id.myTextViewNumSets);

        myGoQueenButton = findViewById(R.id.myGoButton);
        myGoQueenButton.setText(getString(R.string.GoMainToCountdown));

        myEditTextWorkDuration = findViewById(R.id.myEditTextWorkDuration);
        myEditTextRestPeriod = findViewById(R.id.myEditTextRestPeriod);
        myEditTextNumSets = findViewById(R.id.myEditTextNumSets);


        myGoQueenButton.setOnClickListener(v -> {
            try {
                goQueen();
//                workInput();
//                restInput();
//                setsInput();
            } catch (Exception e) {
                Toast.makeText(MainActivity.this, "error in QueenOnClick", Toast.LENGTH_SHORT).show();
            }
        });
    }

    // sets the number of sets to an integer and to a long depending what we need
   /* private void workInput () {
        try {
            String workDurationStr = myEditTextWorkDuration.getText().toString();
            long workDurationLong = Integer.parseInt(workDurationStr) * 1000L;
            workInput = Integer.parseInt(workDurationStr);

        } catch (Exception e) {
            Toast.makeText(MainActivity.this, "error in WorkInput()", Toast.LENGTH_SHORT).show();
        }

    }*/

    // sets the rest duration to an integer and to a long depending what we need
/*    private void restInput () {
        String restDurationStr = myEditTextRestPeriod.getText().toString();
        long restDurationLong = Integer.parseInt(restDurationStr) * 1000L;
        restInput = Integer.parseInt(restDurationStr);

    }*/

    // sets the number of sets to a string and an int
/*    private void setsInput () {
        setsInput = myEditTextNumSets.getInputType();


    }*/

}