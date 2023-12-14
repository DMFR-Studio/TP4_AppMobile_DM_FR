package com.example.tp4_dm_fr.fragments;

import static com.example.tp4_dm_fr.MainActivity.commande;
import static com.example.tp4_dm_fr.MainActivity.listePizzasCommande;

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
    private TextView montantCommande;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.commande_layout, container, false);
        ajouterSamplePizzas(view);
        montantCommande = (TextView) view.findViewById(R.id.montantCommande);

        if(commande != null){
            montantCommande.setText(String.valueOf(commande.getMontant()));
        } else {
            montantCommande.setText("0");
        }

        return view;
    }

    public void ajouterSamplePizzas(View view) {
        ListView listView = view.findViewById(R.id.commandeListView); // Assurez-vous d'avoir un ListView dans votre layout XML

        CommandeItemAdapter adapter = new CommandeItemAdapter(getContext(), listePizzasCommande);
        listView.setAdapter(adapter);
    }


    //TODO: manque le listener au clic du bouton "Add to card" et la logique des points
}
