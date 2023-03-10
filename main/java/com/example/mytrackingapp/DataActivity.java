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
    EditText etAlter, etGroeße, etGewicht;
    RadioGroup rgGeschlecht, rgAktivitaetslevel, rgZiel;
    RadioButton rbMännlich, rbWeiblich, rbSitzend, rbMäßigAktiv, rbAktiv, rbSehrAktiv, rbAbnehmen, rbGewichtHalten, rbZunehmen;
    TextView tvErgebnis;
    Button btnBerechnen;

    // Überschreiben der onCreate Methode

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data);

        // Zuweisen der UI-Elemente zu den Variablen
        etAlter = findViewById(R.id.etAge);
        etGroeße = findViewById(R.id.etHeight);
        etGewicht = findViewById(R.id.etWeight);

        rgGeschlecht = findViewById(R.id.rgGender);
        rbMännlich = findViewById(R.id.rbMale);
        rbWeiblich = findViewById(R.id.rbFemale);

        rgAktivitaetslevel = findViewById(R.id.rgActivityLevel);
        rbSitzend = findViewById(R.id.rbSedentary);
        rbMäßigAktiv = findViewById(R.id.rbModeratelyActive);
        rbAktiv = findViewById(R.id.rbActive);
        rbSehrAktiv = findViewById(R.id.rbVeryActive);

        rgZiel = findViewById(R.id.rgGoal);
        rbAbnehmen = findViewById(R.id.rbLoseWeight);
        rbGewichtHalten = findViewById(R.id.rbKeepWeight);
        rbZunehmen = findViewById(R.id.rbGainWeight);


        tvErgebnis = findViewById(R.id.tvResult);


        btnBerechnen = findViewById(R.id.btnCalculate);

        // Hinzufügen eines OnClickListener für den Button
        btnBerechnen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DataActivity.this, ProfileActivity.class);

                // Auslesen der eingegebenen Daten
                double age = Double.parseDouble(etAlter.getText().toString());
                double height = Double.parseDouble(etGroeße.getText().toString());
                double weight = Double.parseDouble(etGewicht.getText().toString());

                // Berechnung des Grundumsatzes
                double kcal;

                if (rbMännlich.isChecked()) {
                    kcal = 66.47 + (13.75 * weight) + (5 * height) - (6.8 * age);
                } else {
                    kcal = 655.1 + (9.6 * weight) + (1.8 * height) - (4.7 * age);
                }

                // Bestimmung des Aktivitätsfaktors
                double activityLevel = 1.2;

                if (rbSitzend.isChecked()) {
                    activityLevel = 1.375;
                } else if (rbMäßigAktiv.isChecked()) {
                    activityLevel = 1.55;
                } else if (rbAktiv.isChecked()) {
                    activityLevel = 1.725;
                } else if (rbSehrAktiv.isChecked()) {
                    activityLevel = 1.9;
                }

                // Berechnung der benötigten Kalorien
                double calories = activityLevel * kcal;

                if (rbAbnehmen.isChecked()) {
                    calories = calories - 500;
                } else if (rbGewichtHalten.isChecked()) {
                    calories = calories + 0;
                } else if (rbZunehmen.isChecked()) {
                    calories = calories + 300;
                }

                String selectedRadioButtonGender = "";

                // Überprüft, welcher RadioButton in der Gruppe "Gender" ausgewählt wurde
                if (rbMännlich.isChecked()) {
                    selectedRadioButtonGender = rbMännlich.getText().toString();
                } else if (rbWeiblich.isChecked()) {
                    selectedRadioButtonGender = rbWeiblich.getText().toString();
                }

                String selectedRadioButtonActivity = "";

                // Überprüft, welcher RadioButton in der Gruppe "Activity Level" ausgewählt wurde
                if (rbSitzend.isChecked()) {
                    selectedRadioButtonActivity = rbSitzend.getText().toString();
                } else if (rbMäßigAktiv.isChecked()) {
                    selectedRadioButtonActivity = rbMäßigAktiv.getText().toString();
                } else if (rbAktiv.isChecked()) {
                    selectedRadioButtonActivity = rbAktiv.getText().toString();
                } else if (rbSehrAktiv.isChecked()) {
                    selectedRadioButtonActivity = rbSehrAktiv.getText().toString();
                }

                String selectedRadioButtonGoal = "";

                // Überprüft, welcher RadioButton in der Gruppe "Goal" ausgewählt wurde
                if (rbAbnehmen.isChecked()) {
                    selectedRadioButtonGoal = rbAbnehmen.getText().toString();
                } else if (rbGewichtHalten.isChecked()) {
                    selectedRadioButtonGoal = rbGewichtHalten.getText().toString();
                } else if (rbZunehmen.isChecked()) {
                    selectedRadioButtonGoal = rbZunehmen.getText().toString();
                }

                tvErgebnis.setText(String.format("%.2f", + calories));

                // Fügt die ausgewählten RadioButton-Texte als Extra in den Intent hinzu
                intent.putExtra("selectedRadioButtonGender", selectedRadioButtonGender);
                intent.putExtra("selectedRadioButtonActivity", selectedRadioButtonActivity);
                intent.putExtra("selectedRadioButtonGoal", selectedRadioButtonGoal);

                // Fügt die vom Benutzer eingegebenen Werte als Extra in den Intent hinzu
                intent.putExtra("Age", etAlter.getText().toString());
                intent.putExtra("Height", etGroeße.getText().toString());
                intent.putExtra("Weight", etGewicht.getText().toString());
                intent.putExtra("result", tvErgebnis.getText().toString());

                startActivity(intent);
            }


        });


    }
}




