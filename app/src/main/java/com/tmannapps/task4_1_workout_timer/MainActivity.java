package com.tmannapps.task4_1_workout_timer;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    Button myStartStopButton;
    TextView myTextViewTitle;
    TextView myTextViewSetDuration;
    TextView myTextViewRestDuration;
    TextView myTextViewNumSets;
    EditText myEditTextSetDuration;
    EditText myEditTextRestPeriod;
    EditText myEditTextNumSets;
    ProgressBar myProgressBar;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myStartStopButton = findViewById(R.id.myStartStopButton);
        myTextViewTitle = findViewById(R.id.myTextViewTitle);
        myTextViewSetDuration = findViewById(R.id.myTextViewSetDuration);
        myTextViewRestDuration = findViewById(R.id.myTextViewRestDuration);
        myTextViewNumSets = findViewById(R.id.myTextViewNumSets);
        myEditTextSetDuration = findViewById(R.id.myEditTextSetDuration);
        myEditTextRestPeriod = findViewById(R.id.myEditTextRestPeriod);
        myEditTextNumSets = findViewById(R.id.myEditTextNumSets);
        myProgressBar = findViewById(R.id.myProgressBar);

        myStartStopButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}