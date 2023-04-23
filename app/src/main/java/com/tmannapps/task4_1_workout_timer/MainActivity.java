package com.tmannapps.task4_1_workout_timer;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {
    Button myGoQueenButton;
    TextView myTextViewTitle, myTextViewWorkDuration, myTextViewRestDuration,  myTextViewNumSets;
    EditText myEditTextWorkDuration, myEditTextRestPeriod, myEditTextNumSets;

    public void goQueen () {
            Intent intentGoQueen = new Intent(this, Countdown.class);
            startActivity(intentGoQueen);
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
            Log.v("Checking", "Button is clicked");
            goQueen();
        });
    }

}