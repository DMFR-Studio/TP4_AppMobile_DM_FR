package com.example.tp4_dm_fr;

public class PizzaItem {
    private int imageResource;
    private String nom;
    private Format format;
    private double prix;

    public PizzaItem(int imageResource, String nom, Format format, double prix) {
        this.imageResource = imageResource;
        this.nom = nom;
        this.format = format;
        this.prix = prix;
    }

    public int getImageResource() {
        return imageResource;
    }

    public void setImageResource(int imageResource) {
        this.imageResource = imageResource;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Format getFormat() {
        return format;
    }

    public void setFormat(Format format) {
        this.format = format;
    }

    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }

    public enum Format{
        PETITE,
        MOYENNE,
        LARGE
    }
}
