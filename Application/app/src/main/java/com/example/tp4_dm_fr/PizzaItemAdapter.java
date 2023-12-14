package com.example.tp4_dm_fr;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.List;

public class PizzaItemAdapter extends ArrayAdapter {
    private Context context;
    private List<PizzaItem> itemList;

    public PizzaItemAdapter(Context context, List<PizzaItem> itemList) {
        super(context, R.layout.pizza_item_layout, itemList);
        this.context = context;
        this.itemList = itemList;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View listItemView = inflater.inflate(R.layout.pizza_item_layout, parent, false);

        ImageView imageView = listItemView.findViewById(R.id.pizzaImageView);
        TextView nomPizza = listItemView.findViewById(R.id.nomPizzatextView);
        TextView format = listItemView.findViewById(R.id.formatTextView);
        TextView prix = listItemView.findViewById(R.id.prixtextView);
        Spinner spinnerFormat = listItemView.findViewById(R.id.spinnerFormat);

        PizzaItem currentItem = itemList.get(position);

        imageView.setImageResource(currentItem.getImageResource());
        nomPizza.setText(currentItem.getSorte());
        format.setText(currentItem.getType());
        prix.setText(String.valueOf(currentItem.getPrix()));

        double prixPetite = currentItem.getPrix();
        double prixMoyenne = currentItem.getPrix()*1.15;
        double prixGrande = currentItem.getPrix()*1.30;

        String[] options = {"Petite " + prixPetite, "Moyenne " + prixMoyenne, "Grande " + prixGrande};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this.getContext(), android.R.layout.simple_spinner_item, options);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerFormat.setAdapter(adapter);

        spinnerFormat.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedItem = (String) parent.getItemAtPosition(position);
                String[] parts = selectedItem.split(" ");
                double selectedPrice = Double.parseDouble(parts[1]);
                String selectedType = parts[0];

                prix.setText(String.valueOf(selectedPrice));
                format.setText(selectedType);
                Log.i("selectedItem", String.valueOf(selectedPrice));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // À exécuter lorsqu'aucun élément n'est sélectionné
            }
        });

        //TODO: ajouter la pizza dans l'instance statique du client (je suggère d'ajouter un
        // attribut qui représente la commande dans le client)

        return listItemView;
    }
}
