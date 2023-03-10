package com.example.mytrackingapp;

public class FoodItem {
    private String name;
    private double portionSize;
    private int caloriesPer100g;

    public FoodItem(String name, double portionSize, int caloriesPer100g) {
        this.name = name;
        this.portionSize = portionSize;
        this.caloriesPer100g = caloriesPer100g;
    }

    public String getName() {
        return name;
    }

    public double getPortionSize() {
        return portionSize;
    }

    public int getCaloriesPer100g() {
        return caloriesPer100g;
    }
}
