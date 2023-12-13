package com.example.tp4_dm_fr;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import java.util.AbstractCollection;
import java.util.List;

public class CommandeFragment extends Fragment {
    private List<Object> listeCommandes;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.pizzas_layout, container, false);
        return view;
    }

    public void ajouterPizzaALaCommande(String nomPizza, String format, String prix) {
        // Ajoutez la pizza à votre liste de commandes ici
        // Par exemple, si vous avez une liste de commandes appelée listeCommandes :
        listeCommandes.add(new Pizza(nomPizza, PizzaItem.Format.valueOf(format), Double.parseDouble("1")));
        // Rafraîchissez votre adaptateur ou mettez à jour l'affichage de la liste
    }
}
