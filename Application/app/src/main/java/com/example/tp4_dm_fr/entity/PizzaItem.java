package com.example.tp4_dm_fr.entity;

public class PizzaItem {
    private int imageResource;
    private String sorte;
    private String type;
    private double prix;

    public PizzaItem(int imageResource, String sorte, String type, double prix) {
        this.imageResource = imageResource;
        this.sorte = sorte;
        this.type = type;
        this.prix = prix;
    }

    public int getImageResource() {
        return imageResource;
    }

    public void setImageResource(int imageResource) {
        this.imageResource = imageResource;
    }

    public String getSorte() {
        return sorte;
    }

    public void setSorte(String sorte) {
        this.sorte = sorte;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }

}
