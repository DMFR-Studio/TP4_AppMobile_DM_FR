package com.example.tp4_dm_fr;

import static com.example.tp4_dm_fr.MainActivity.commande;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class CommandeItemAdapter extends ArrayAdapter {
    private Context context;
    private List<PizzaItem> itemList;

    public CommandeItemAdapter(Context context, List<PizzaItem> itemList) {
        super(context, R.layout.pizza_item_layout, itemList);
        this.context = context;
        this.itemList = itemList;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View listItemView = inflater.inflate(R.layout.commande_item_layout, parent, false);

        ImageView imageView = listItemView.findViewById(R.id.pizzaImageView);
        TextView nomPizza = listItemView.findViewById(R.id.nom_format_textView);
        TextView prix = listItemView.findViewById(R.id.prixtextView);
        TextView total = listItemView.findViewById(R.id.totalTextView);
        Button ajouterButton = listItemView.findViewById(R.id.ajouterBouton);
        Button enleverButton = listItemView.findViewById(R.id.enleverBouton);
        TextView quantiteTextView = listItemView.findViewById(R.id.quantiteTextView);

        PizzaItem currentItem = itemList.get(position);

        imageView.setImageResource(currentItem.getImageResource());
        nomPizza.setText(currentItem.getSorte() + " - " + currentItem.getType());
        prix.setText("Prix: " + String.valueOf(currentItem.getPrix()));
        quantiteTextView.setText("1");
        String montantString = prix.getText().toString().substring(prix.getText().toString().indexOf(":") + 1).trim();
        total.setText("Total: " + Double.parseDouble(montantString));

        // Définir le listener pour le bouton d'ajout

        ajouterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int quantite = Integer.parseInt(quantiteTextView.getText().toString());
                quantiteTextView.setText(String.valueOf(quantite + 1));
                //extrait le montant du textView ayant le prix unitaire d'une pizza
                String montantString = prix.getText().toString().substring(prix.getText().toString().indexOf(":") + 1).trim();
                total.setText("Total: " + Double.parseDouble(montantString) * (quantite + 1));
                if(commande != null){
                    commande.setMontant(commande.getMontant() + Double.parseDouble(montantString) * (quantite + 1));
                }
            }
        });

        // Définir le listener pour le bouton de soustraction

        enleverButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int quantite = Integer.parseInt(quantiteTextView.getText().toString());
                if (quantite > 0) {
                    quantiteTextView.setText(String.valueOf(quantite - 1));
                    String montantString = prix.getText().toString().substring(prix.getText().toString().indexOf(":") + 1).trim();
                    total.setText("Total: " + Double.parseDouble(montantString) * (quantite - 1));
                    if(commande != null){
                        commande.setMontant(commande.getMontant() + Double.parseDouble(montantString) * (quantite - 1));
                    }
                }
            }
        });

        return listItemView;
    }
}