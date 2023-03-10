package com.example.mytrackingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity {

    private EditText etNahrungsmittel;
    private EditText etPortionGröße;
    private EditText etkalorienPro100g;
    private Button addButton, logOutButton;
    private Button berechneteKalorienButton;


    // Liste, um alle hinzugefügten Lebensmittel zu speichern
    private List<Nahrungsmittel> nahrungsmittelListe = new ArrayList<>();


    private TextView tvgesamtKalorien;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        etNahrungsmittel = findViewById(R.id.foodNameEditText);
        etPortionGröße = findViewById(R.id.portionSizeEditText);
        etkalorienPro100g = findViewById(R.id.caloriesPer100gEditText);
        addButton = findViewById(R.id.addFoodButton);
        berechneteKalorienButton = findViewById(R.id.calculateCaloriesButton);
        logOutButton = findViewById(R.id.btnLogout);
        tvgesamtKalorien = findViewById(R.id.totalCaloriesTextView);

        logOutButton.setOnClickListener(view -> {
            Intent intent = new Intent(HomeActivity.this, LoginActivity.class);
            startActivity(intent);
        });
    }



    // Funktion, um ein neues Lebensmittel zur Liste hinzuzufügen
    public void addNahrungsmittel(View view) {
        String nahrungsmittel = etNahrungsmittel.getText().toString();
        double portionGröße = Double.parseDouble(etPortionGröße.getText().toString());
        int kalorienPro100g = Integer.parseInt(etkalorienPro100g.getText().toString());

        Nahrungsmittel Nahrungsmittel = new Nahrungsmittel(nahrungsmittel, portionGröße, kalorienPro100g);
        nahrungsmittelListe.add(Nahrungsmittel);

        // Zurücksetzen der EditText-Felder
        etNahrungsmittel.setText("");
        etPortionGröße.setText("");
        etkalorienPro100g.setText("");
    }

    // Funktion, um die Gesamtkalorienzahl aller Lebensmittel in der Liste zu berechnen
    public void berechneGesamtKlorien(View view) {
        int gesamtKalorien = 0;
        for (Nahrungsmittel Nahrungsmittel : nahrungsmittelListe) {
            double portionGröße = Nahrungsmittel.getPortionGröße();
            int kalorienPro100g = Nahrungsmittel.getKalorienPro100g();
            double calories = portionGröße / 100 * kalorienPro100g;
            gesamtKalorien += calories;
        }

        tvgesamtKalorien.setText(String.valueOf(gesamtKalorien));
    }
}