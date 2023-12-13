package com.example.tp4_dm_fr;

import android.content.Context;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;

public class ConsommationREST {
    public void addUser(Context context, String nom, String courriel, String mot_de_passe, String adresse, String telephone) {
        String url = "http://192.168.2.134:8081/addClient";

        RequestQueue queue = Volley.newRequestQueue(context);

        JSONObject postData = new JSONObject();
        try {
            postData.put("nom", nom);
            postData.put("courriel", courriel);
            postData.put("mot_de_passe", mot_de_passe);
            postData.put("adresse", adresse);
            postData.put("telephone", telephone);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                response -> {
                    // Handle successful response (status 200)
                    Log.i("api", "user added successfully");
                },
                error -> {
                    // Handle error
                    Log.e("api", String.valueOf(error));
                }) {
                @Override
                protected Map<String, String> getParams() {
                        Map<String, String> params = new HashMap<>();
                        params.put("nom", nom);
                        params.put("courriel", courriel);
                        params.put("mot_de_passe", mot_de_passe);
                        params.put("adresse", adresse);
                        params.put("telephone", telephone);
                    return params;
                }
        };

        queue.add(stringRequest);
    }

    public void logInUser(Context context, String courriel, String mot_de_passe) {
        String url = "http://192.168.2.134:8081/logIn";
        RequestQueue queue = Volley.newRequestQueue(context);

        JSONObject postData = new JSONObject();
        try {
            postData.put("courriel", courriel);
            postData.put("mot_de_passe", mot_de_passe);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                response -> {
                    // Handle successful response (status 200)
                    Log.i("api", "user loggedIn successfully");
                },
                error -> {
                    // Handle error
                    Log.e("api", String.valueOf(error));
                }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("courriel", courriel);
                params.put("mot_de_passe", mot_de_passe);
                return params;
            }
        };

        queue.add(stringRequest);
    }
}
