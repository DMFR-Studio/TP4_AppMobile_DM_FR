package com.example.tp4_dm_fr.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.tp4_dm_fr.MainActivity;
import com.example.tp4_dm_fr.R;

public class PointsFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.points_layout, container, false);
        TextView points = view.findViewById(R.id.pointsTextView);
        points.setText(R.string.mes_points + "\n" + MainActivity.clientLoggedIn.getPoints());
        return view;
    }
}
