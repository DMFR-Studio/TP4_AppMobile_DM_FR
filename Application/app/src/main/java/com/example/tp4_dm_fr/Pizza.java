package com.example.tp4_dm_fr;

public class Pizza {
    private String nom;
    private PizzaItem.Format format;
    private double prix;

    public Pizza(String nom, PizzaItem.Format format, double prix) {
        this.nom = nom;
        this.format = format;
        this.prix = prix;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public PizzaItem.Format getFormat() {
        return format;
    }

    public void setFormat(PizzaItem.Format format) {
        this.format = format;
    }

    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }
}
