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

    private EditText fmNameEditText;
    private EditText mPortionSizeEditText;
    private EditText mCaloriesPer100gEditText;
    private Button mAddFoodButton, mLogOutButton;
    private Button mCalculateCaloriesButton;


    // Liste, um alle hinzugefügten Lebensmittel zu speichern
    private List<FoodItem> mFoodItemList = new ArrayList<>();


    private TextView totalCaloriesTextView;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        fmNameEditText = findViewById(R.id.foodNameEditText);
        mPortionSizeEditText = findViewById(R.id.portionSizeEditText);
        mCaloriesPer100gEditText = findViewById(R.id.caloriesPer100gEditText);
        mAddFoodButton = findViewById(R.id.addFoodButton);
        mCalculateCaloriesButton = findViewById(R.id.calculateCaloriesButton);
        mLogOutButton = findViewById(R.id.btnLogout);
        totalCaloriesTextView = findViewById(R.id.totalCaloriesTextView);

        mLogOutButton.setOnClickListener(view -> {
            Intent intent = new Intent(HomeActivity.this, LoginActivity.class);
            startActivity(intent);
        });
    }



    // Funktion, um ein neues Lebensmittel zur Liste hinzuzufügen
    public void addFoodItem(View view) {
        String foodName = fmNameEditText.getText().toString();
        double portionSize = Double.parseDouble(mPortionSizeEditText.getText().toString());
        int caloriesPer100g = Integer.parseInt(mCaloriesPer100gEditText.getText().toString());

        FoodItem foodItem = new FoodItem(foodName, portionSize, caloriesPer100g);
        mFoodItemList.add(foodItem);

        // Zurücksetzen der EditText-Felder
        fmNameEditText.setText("");
        mPortionSizeEditText.setText("");
        mCaloriesPer100gEditText.setText("");
    }

    // Funktion, um die Gesamtkalorienzahl aller Lebensmittel in der Liste zu berechnen
    public void calculateTotalCalories(View view) {
        int totalCalories = 0;
        for (FoodItem foodItem : mFoodItemList) {
            double portionSize = foodItem.getPortionSize();
            int caloriesPer100g = foodItem.getCaloriesPer100g();
            double calories = portionSize / 100 * caloriesPer100g;
            totalCalories += calories;
        }

        totalCaloriesTextView.setText(String.valueOf(totalCalories));
    }
}