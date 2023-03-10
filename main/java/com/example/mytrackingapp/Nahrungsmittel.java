package com.example.mytrackingapp;

public class Nahrungsmittel {
    private String name;
    private double portionGröße;
    private int kalorienPro100g;

    public Nahrungsmittel(String name, double portionGröße, int kalorienPro100g) {
        this.name = name;
        this.portionGröße = portionGröße;
        this.kalorienPro100g = kalorienPro100g;
    }

    public String getNahrungsmittel() {
        return name;
    }

    public double getPortionGröße() {
        return portionGröße;
    }

    public int getKalorienPro100g() {
        return kalorienPro100g;
    }
}
