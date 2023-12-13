package com.example.tp4_dm_fr;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {
    public static Client clientLoggedIn = new Client();
    DrawerLayout dLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dLayout.openDrawer(Gravity.LEFT);
            }
        });


        setNavigationDrawer();
        afficherAccueilFragment();
    }

    private void afficherAccueilFragment() {
        Fragment frag = new AccueilFragment();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame, frag);
        transaction.commit();
    }

    private void setNavigationDrawer() {
        dLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        NavigationView navView = (NavigationView) findViewById(R.id.navigation);


        navView.setNavigationItemSelectedListener(menuItem -> {
            dLayout.close();

            Fragment frag = null;
            int itemId = menuItem.getItemId();
            if (itemId == R.id.accueilItem) {
                frag = new AccueilFragment();
            }

            if (frag != null) {
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.frame, frag);
                transaction.commit();
                dLayout.closeDrawers();
                return true;
            }
            return false;
        });
    }

    public void onInscription(View view) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame, new InscriptionFragment());
        transaction.commit();
        dLayout.closeDrawers();
    }

    public void onConnexion(View view) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame, new ConnexionFragment());
        transaction.commit();
        dLayout.closeDrawers();
    }

    public void onAjouterPizza(View view) {
        Toast.makeText(getApplicationContext(), "Pizza ajoutée", Toast.LENGTH_SHORT).show();
    }

}