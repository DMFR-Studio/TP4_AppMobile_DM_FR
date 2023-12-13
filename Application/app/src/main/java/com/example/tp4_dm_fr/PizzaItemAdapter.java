package com.example.tp4_dm_fr;

import android.content.Context;
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
        nomPizza.setText(currentItem.getNom());
        format.setText(currentItem.getFormat().toString());
        prix.setText(String.valueOf(currentItem.getPrix()));

        String[] options = {PizzaItem.Format.PETITE.name() + " 5.00$", PizzaItem.Format.MOYENNE.name() + " 10.00$", PizzaItem.Format.LARGE.name() + " 15.00$"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this.getContext(), android.R.layout.simple_spinner_item, options);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerFormat.setAdapter(adapter);

        spinnerFormat.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedItem = (String) parent.getItemAtPosition(position);
                // Faire quelque chose avec l'élément sélectionné
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // À exécuter lorsqu'aucun élément n'est sélectionné
            }
        });

        return listItemView;
    }
}
