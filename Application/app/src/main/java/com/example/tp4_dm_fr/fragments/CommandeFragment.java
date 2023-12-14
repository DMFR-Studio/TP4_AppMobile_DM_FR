package com.example.tp4_dm_fr.fragments;

import static com.example.tp4_dm_fr.MainActivity.clientLoggedIn;
import static com.example.tp4_dm_fr.MainActivity.commande;
import static com.example.tp4_dm_fr.MainActivity.listePizzasCommande;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.tp4_dm_fr.CommandeItemAdapter;
import com.example.tp4_dm_fr.R;

public class CommandeFragment extends Fragment {
    private TextView montantCommande;
    private TextView totalAvecPoints;
    private TextView montantEconomise;
    private Button ajouterAuPanier;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.commande_layout, container, false);
        ajouterSamplePizzas(view);
        montantCommande = (TextView) view.findViewById(R.id.montantCommande);
        totalAvecPoints = (TextView) view.findViewById(R.id.totalAvecPoints);
        montantEconomise = (TextView) view.findViewById(R.id.montantEconomise);
        ajouterAuPanier = (Button) view.findViewById(R.id.ajouterAuPanier);

        if(commande != null){
            montantCommande.setText(String.valueOf(commande.getMontant()));
            totalAvecPoints.setText(String.valueOf(montantAvecPoints()));
            montantEconomise.setText(String.valueOf(montantEconomiser()));
        } else {
            montantCommande.setText("0.0");
            totalAvecPoints.setText("0.0");
            montantEconomise.setText("0.0");
        }

        ajouterAuPanier.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder alert = createAlertWindow(view.getContext(), "ADD TO CART", "Passer à la caisse pour paiement par carte");
                alert.create().show();
            }
        });

        return view;
    }

    public void ajouterSamplePizzas(View view) {
        ListView listView = view.findViewById(R.id.commandeListView); // Assurez-vous d'avoir un ListView dans votre layout XML

        CommandeItemAdapter adapter = new CommandeItemAdapter(getContext(), listePizzasCommande, this);
        listView.setAdapter(adapter);
    }

    public void updateMontantText() {
        if (montantCommande != null && commande != null) {
            montantCommande.setText(String.valueOf(commande.getMontant()));
            totalAvecPoints.setText(String.valueOf(montantAvecPoints()));
            montantEconomise.setText(String.valueOf(montantEconomiser()));
        }
    }

    public double montantAvecPoints(){
        double nbPoints = clientLoggedIn.getPoints();

        if(nbPoints > commande.getMontant()){
            return 0.0;
        } else {
            return commande.getMontant() - nbPoints;
        }
    }

    public double montantEconomiser(){
        double nbPoints = clientLoggedIn.getPoints();

        if(nbPoints > commande.getMontant()){
            return commande.getMontant();
        } else {
            return nbPoints;
        }
    }

    private AlertDialog.Builder createAlertWindow(Context context, String title, String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        View dialogView = getLayoutInflater().inflate(R.layout.alert_dialog_layout, null);
        TextView alertMessageTextView = dialogView.findViewById(R.id.alertMessageTextView);

        alertMessageTextView.setText(message);

        builder.setView(dialogView);
        builder.setTitle(title);
        builder.setPositiveButton("OUI", (dialog, which) -> {
            FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
            transaction.replace(R.id.frame, new FragmentVide());
            transaction.commit();
        });
        builder.setNegativeButton("NON", (dialog, which) -> {

        });

        return builder;
    }
}


