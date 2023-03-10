package com.example.mytrackingapp;

import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ProfileActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        // Auslesen der übergebenen Daten aus dem Intent
        String age = getIntent().getStringExtra("Age");
        ((TextView)findViewById(R.id.textViewAge)).setText(age);

        String height = getIntent().getStringExtra("Height");
        ((TextView)findViewById(R.id.textViewHeight)).setText(height);

        String weight = getIntent().getStringExtra("Weight");
        ((TextView)findViewById(R.id.textViewWeight)).setText(weight);

        String result = getIntent().getStringExtra("result");
        ((TextView)findViewById(R.id.tvKcal)).setText(result);

        String gender = getIntent().getStringExtra("selectedRadioButtonGender");
        ((TextView)findViewById(R.id.textViewGender)).setText(gender);

        String activity = getIntent().getStringExtra("selectedRadioButtonActivity");
        ((TextView)findViewById(R.id.textViewActivity)).setText(activity);

        String goal = getIntent().getStringExtra("selectedRadioButtonGoal");
        ((TextView)findViewById(R.id.textViewGoal)).setText(goal);


        // Implementieren des Klick-Handlers für den Zurück-Button
        Button back = (Button)findViewById(R.id.btnEdit);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        // Implementieren des Klick-Handlers für den Weiter-Button
        Button next = (Button)findViewById(R.id.btnNext);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ProfileActivity.this, HomeActivity.class);


                startActivity(intent);
            }
        });
    }
}