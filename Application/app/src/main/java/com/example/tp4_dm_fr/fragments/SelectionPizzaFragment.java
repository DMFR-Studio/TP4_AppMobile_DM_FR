package com.example.tp4_dm_fr.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;

import androidx.fragment.app.Fragment;

import com.example.tp4_dm_fr.ConsommationREST;
import com.example.tp4_dm_fr.PizzaItem;
import com.example.tp4_dm_fr.PizzaItemAdapter;
import com.example.tp4_dm_fr.R;

import java.util.ArrayList;
import java.util.List;

public class SelectionPizzaFragment extends Fragment {
    private View view;
    ConsommationREST consoRest = new ConsommationREST();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.pizzas_layout, container, false);
        this.view = view;
        afficherPizzas(view);
        return view;
    }

    private void afficherPizzas(View view) {
        ListView listView = view.findViewById(R.id.pizzaListView);
        List<PizzaItem> items = new ArrayList<>();

        consoRest.getAllPizzas(view.getContext(), new ConsommationREST.OnAllPizzasResponseListener() {
            @Override
            public void onAllPizzasResponse(List<PizzaItem> pizzaItemList) {
                int image = 0;
                //TODO ajouter image pour différentes pizzas
                for (PizzaItem pizzaItem : pizzaItemList) {
                    if(pizzaItem.getSorte().equals("Fromage")){
                        image = R.drawable.fromage;
                    } else if(pizzaItem.getSorte().equals("Pepperoni")){
                        image = R.drawable.pepperoni;
                    } else if(pizzaItem.getSorte().equals("Garnie")){
                        image = R.drawable.garnie;
                    } else if(pizzaItem.getSorte().equals("Hawaienne")){
                        image = R.drawable.hawaienne;
                    } else if(pizzaItem.getSorte().equals("Bacon")){
                        image = R.drawable.bacon;
                    }
                    items.add(new PizzaItem(image, pizzaItem.getSorte(), pizzaItem.getType(), pizzaItem.getPrix()));
                }

                PizzaItemAdapter adapter = new PizzaItemAdapter(view.getContext(), items);
                listView.setAdapter(adapter);
            }

            @Override
            public void onAllPizzasError() {
                Log.e("Pizza", "Erreur lors de la récupération de la liste de pizzas");
            }
        });
    }

}
