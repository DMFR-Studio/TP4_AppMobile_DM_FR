package com.example.tp4_dm_fr.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.tp4_dm_fr.CommandeItemAdapter;
import com.example.tp4_dm_fr.Pizza;
import com.example.tp4_dm_fr.PizzaItem;
import com.example.tp4_dm_fr.PizzaItemAdapter;
import com.example.tp4_dm_fr.R;

import java.util.AbstractCollection;
import java.util.ArrayList;
import java.util.List;

public class CommandeFragment extends Fragment {
    private List<PizzaItem> listeCommandes = new ArrayList<>();
    private TextView quantiteTextView;
    private Button ajouterBouton;
    private Button enleverBouton;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.commande_layout, container, false);
        ajouterSamplePizzas(view);
        return view;
    }

    public void ajouterSamplePizzas(View view) {
        //TODO: remplacer par les vraies pizzas de la commande
        ListView listView = view.findViewById(R.id.commandeListView); // Assurez-vous d'avoir un ListView dans votre layout XML
        List<PizzaItem> items = new ArrayList<>();
        items.add(new PizzaItem(R.drawable.image_pizza_1, "Texte 1", PizzaItem.Format.MOYENNE, 5.00));
        items.add(new PizzaItem(R.drawable.image_pizza_1, "Texte 2", PizzaItem.Format.LARGE, 7.00));
        items.add(new PizzaItem(R.drawable.image_pizza_1, "Texte 2", PizzaItem.Format.LARGE, 7.00));
        items.add(new PizzaItem(R.drawable.image_pizza_1, "Texte 2", PizzaItem.Format.LARGE, 7.00));

        CommandeItemAdapter adapter = new CommandeItemAdapter(getContext(), items);
        listView.setAdapter(adapter);

        //TODO: faire la somme de toutes les pizzas de la commande
    }

    //TODO: manque le listener au clic du bouton "Add to card" et la logique des points
}
