package com.example.mytrackingapp;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class DataActivity extends AppCompatActivity {

    // Deklarieren der benötigten Variablen
    EditText etAge, etHeight, etWeight;
    RadioGroup rgGender, rgActivityLevel, rgGoal;
    RadioButton rbMale, rbFemale, rbSedentary, rbModeratelyActive, rbActive, rbVeryActive, rbLoseWeight, rbKeepWeight, rbGainWeight;
    TextView tvResult;
    Button btnCalculate;

    // Überschreiben der onCreate Methode

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data);

        // Zuweisen der UI-Elemente zu den Variablen
        etAge = findViewById(R.id.etAge);
        etHeight = findViewById(R.id.etHeight);
        etWeight = findViewById(R.id.etWeight);

        rgGender = findViewById(R.id.rgGender);
        rbMale = findViewById(R.id.rbMale);
        rbFemale = findViewById(R.id.rbFemale);

        rgActivityLevel = findViewById(R.id.rgActivityLevel);
        rbSedentary = findViewById(R.id.rbSedentary);
        rbModeratelyActive = findViewById(R.id.rbModeratelyActive);
        rbActive = findViewById(R.id.rbActive);
        rbVeryActive = findViewById(R.id.rbVeryActive);

        rgGoal = findViewById(R.id.rgGoal);
        rbLoseWeight = findViewById(R.id.rbLoseWeight);
        rbKeepWeight = findViewById(R.id.rbKeepWeight);
        rbGainWeight = findViewById(R.id.rbGainWeight);


        tvResult = findViewById(R.id.tvResult);


        btnCalculate = findViewById(R.id.btnCalculate);

        // Hinzufügen eines OnClickListener für den Button
        btnCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DataActivity.this, ProfileActivity.class);

                // Auslesen der eingegebenen Daten
                double age = Double.parseDouble(etAge.getText().toString());
                double height = Double.parseDouble(etHeight.getText().toString());
                double weight = Double.parseDouble(etWeight.getText().toString());

                // Berechnung des Grundumsatzes
                double bmr;

                if (rbMale.isChecked()) {
                    bmr = 66.47 + (13.75 * weight) + (5 * height) - (6.8 * age);
                } else {
                    bmr = 655.1 + (9.6 * weight) + (1.8 * height) - (4.7 * age);
                }

                // Bestimmung des Aktivitätsfaktors
                double activityFactor = 1.2;

                if (rbSedentary.isChecked()) {
                    activityFactor = 1.375;
                } else if (rbModeratelyActive.isChecked()) {
                    activityFactor = 1.55;
                } else if (rbActive.isChecked()) {
                    activityFactor = 1.725;
                } else if (rbVeryActive.isChecked()) {
                    activityFactor = 1.9;
                }

                // Berechnung der benötigten Kalorien
                double calories = bmr * activityFactor;

                if (rbLoseWeight.isChecked()) {
                    calories = calories - 500;
                } else if (rbKeepWeight.isChecked()) {
                    calories = calories + 0;
                } else if (rbGainWeight.isChecked()) {
                    calories = calories + 300;
                }

                String selectedRadioButtonGender = "";

                // Überprüft, welcher RadioButton in der Gruppe "Gender" ausgewählt wurde
                if (rbMale.isChecked()) {
                    selectedRadioButtonGender = rbMale.getText().toString();
                } else if (rbFemale.isChecked()) {
                    selectedRadioButtonGender = rbFemale.getText().toString();
                }

                String selectedRadioButtonActivity = "";

                // Überprüft, welcher RadioButton in der Gruppe "Activity Level" ausgewählt wurde
                if (rbSedentary.isChecked()) {
                    selectedRadioButtonActivity = rbSedentary.getText().toString();
                } else if (rbModeratelyActive.isChecked()) {
                    selectedRadioButtonActivity = rbModeratelyActive.getText().toString();
                } else if (rbActive.isChecked()) {
                    selectedRadioButtonActivity = rbActive.getText().toString();
                } else if (rbVeryActive.isChecked()) {
                    selectedRadioButtonActivity = rbVeryActive.getText().toString();
                }

                String selectedRadioButtonGoal = "";

                // Überprüft, welcher RadioButton in der Gruppe "Goal" ausgewählt wurde
                if (rbLoseWeight.isChecked()) {
                    selectedRadioButtonGoal = rbLoseWeight.getText().toString();
                } else if (rbKeepWeight.isChecked()) {
                    selectedRadioButtonGoal = rbKeepWeight.getText().toString();
                } else if (rbGainWeight.isChecked()) {
                    selectedRadioButtonGoal = rbGainWeight.getText().toString();
                }

                tvResult.setText(String.format("%.2f", + calories));

                // Fügt die ausgewählten RadioButton-Texte als Extra in den Intent hinzu
                intent.putExtra("selectedRadioButtonGender", selectedRadioButtonGender);
                intent.putExtra("selectedRadioButtonActivity", selectedRadioButtonActivity);
                intent.putExtra("selectedRadioButtonGoal", selectedRadioButtonGoal);

                // Fügt die vom Benutzer eingegebenen Werte als Extra in den Intent hinzu
                intent.putExtra("Age", etAge.getText().toString());
                intent.putExtra("Height", etHeight.getText().toString());
                intent.putExtra("Weight", etWeight.getText().toString());
                intent.putExtra("result", tvResult.getText().toString());

                startActivity(intent);
            }


        });


    }
}




