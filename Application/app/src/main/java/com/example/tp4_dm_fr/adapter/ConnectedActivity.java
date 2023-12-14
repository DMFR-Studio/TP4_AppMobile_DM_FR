package com.example.tp4_dm_fr.adapter;

import android.os.Bundle;
import android.view.Gravity;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.tp4_dm_fr.R;
import com.example.tp4_dm_fr.fragments.AccueilConnecteFragment;
import com.example.tp4_dm_fr.fragments.AccueilFragment;
import com.google.android.material.navigation.NavigationView;

public class ConnectedActivity extends AppCompatActivity {

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
        afficherAccueilConnecteFragment();
    }

    private void afficherAccueilConnecteFragment() {
        Fragment frag = new AccueilConnecteFragment();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame, frag);
        transaction.commit();
    }

    private void setNavigationDrawer() {
        dLayout = (DrawerLayout) findViewById(R.id.drawer_connected_layout);
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
                transaction.replace(R.id.frameConnected, frag);
                transaction.commit();
                dLayout.closeDrawers();
                return true;
            }
            return false;
        });
    }
}

