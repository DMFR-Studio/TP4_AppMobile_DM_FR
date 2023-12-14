package com.example.tp4_dm_fr.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.tp4_dm_fr.PizzaItem;
import com.example.tp4_dm_fr.PizzaItemAdapter;
import com.example.tp4_dm_fr.R;

import java.util.ArrayList;
import java.util.List;

public class SelectionPizzaFragment extends Fragment {

    private View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.pizzas_layout, container, false);
        this.view = view;
        afficherPizzas(view);
        return view;
    }

    private void afficherPizzas(View view) {
        //TODO: remplacer par toutes les pizzas dispo avec un call d'api
        ListView listView = view.findViewById(R.id.pizzaListView); // Assurez-vous d'avoir un ListView dans votre layout XML
        List<PizzaItem> items = new ArrayList<>();
        items.add(new PizzaItem(R.drawable.image_pizza_1, "Texte 1", PizzaItem.Format.MOYENNE, 5.00));
        items.add(new PizzaItem(R.drawable.image_pizza_1, "Texte 2", PizzaItem.Format.LARGE, 7.00));

        PizzaItemAdapter adapter = new PizzaItemAdapter(getContext(), items);
        listView.setAdapter(adapter);
    }
}
