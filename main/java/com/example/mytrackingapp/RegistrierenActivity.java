package com.example.mytrackingapp;

import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegistrierenActivity extends AppCompatActivity {

    // Deklaration der Variablen für Benutzername und Passwort Eingabefelder und DBHelper
    private EditText etBenutzername, etPasswort;

    private DBHelper dbHelper;

    private Button btnRegister;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrieren);

        // Initialisierung des DBHelper Objekts
        dbHelper = new DBHelper(this);

        // Initialisierung der Variablen für Benutzername und Passwort Eingabefelder und Registrieren-Schaltfläche
        etBenutzername = findViewById(R.id.etUsernameRegistrieren);
        etPasswort = findViewById(R.id.etPasswordRegistrieren);
        btnRegister = findViewById(R.id.btnRegisterRegistrieren);

        // Hinzufügen eines ClickListeners für die Registrieren-Schaltfläche
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Auslesen des Benutzernamens und Passworts aus den Eingabefeldern
                String username = etBenutzername.getText().toString().trim();
                String password = etPasswort.getText().toString().trim();

                // Überprüfung, ob Benutzername und Passwort Eingabefelder nicht leer sind
                if (username.isEmpty() || password.isEmpty()) {

                    // Ausgabe einer Fehlermeldung als Toast, wenn Benutzername oder Passwort leer sind
                    Toast.makeText(RegistrierenActivity.this, "Please enter your username and password", Toast.LENGTH_SHORT).show();
                } else {

                    // Hinzufügen des Benutzers zur Datenbank über den DBHelper
                    if (dbHelper.addUser(username, password)) {

                        // Ausgabe einer Erfolgsmeldung als Toast, wenn die Registrierung erfolgreich war
                        Toast.makeText(RegistrierenActivity.this, "Registration successful", Toast.LENGTH_SHORT).show();

                        // Zurücksetzen der Eingabefelder für Benutzername und Passwort
                        etBenutzername.setText("");
                        etPasswort.setText("");

                        // Starten der LoginActivity nach erfolgreicher Registrierung und Beenden der RegistrierenActivity
                        Intent intent = new Intent(RegistrierenActivity.this, LoginActivity.class);
                        startActivity(intent);
                        finish();
                    } else {

                        // Ausgabe einer Fehlermeldung als Toast, wenn die Registrierung fehlgeschlagen ist
                        Toast.makeText(RegistrierenActivity.this, "Registration failed", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });


    }
}