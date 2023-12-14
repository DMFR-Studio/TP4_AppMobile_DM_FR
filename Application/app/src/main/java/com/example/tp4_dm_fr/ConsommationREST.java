package com.example.tp4_dm_fr;

import static com.example.tp4_dm_fr.MainActivity.clientLoggedIn;

import android.content.Context;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.tp4_dm_fr.customListener.OnLoginResultListener;
import com.example.tp4_dm_fr.customListener.OnUserAddedListener;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ConsommationREST {
    public void addUser(Context context, String nom, String courriel, String mot_de_passe, String adresse, String telephone, OnUserAddedListener listener) {
        String url = "http://192.168.0.119:8081/addClient";

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
                    try {
                        JSONObject jsonResponse = new JSONObject(response);
                        int idClient = jsonResponse.getInt("id");
                        String adresseClient = jsonResponse.getString("adresse");
                        double pointsClient = jsonResponse.getDouble("points");
                        clientLoggedIn.setId(idClient);
                        clientLoggedIn.setAdresse(adresseClient);
                        clientLoggedIn.setPoints(pointsClient);
                        Log.i("api", "user added successfully with ID: " + idClient);
                        listener.onUserAdded(idClient);
                    } catch (JSONException e) {
                        e.printStackTrace();
                        Log.e("api", "Error parsing JSON");
                    }
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


    public void logInUser(Context context, String courriel, String mot_de_passe, OnLoginResultListener listener) {
        String url = "http://192.168.0.119:8081/logIn";
        RequestQueue queue = Volley.newRequestQueue(context);

        JSONObject postData = new JSONObject();
        try {
            postData.put("courriel", courriel);
            postData.put("mot_de_passe", mot_de_passe);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, postData,
                response -> {
                    // Handle successful response (status 200)
                    try {
                        int idClient = response.getInt("id");
                        String adresseClient = response.getString("adresse");
                        int pointsClient = response.getInt("points");
                        clientLoggedIn.setId(idClient);
                        clientLoggedIn.setAdresse(adresseClient);
                        clientLoggedIn.setPoints(pointsClient);
                        listener.onLoginResult(true, idClient);
                    } catch (JSONException e) {
                        e.printStackTrace();
                        Log.e("api", "Error parsing JSON");
                        listener.onLoginResult(false, 0);
                    }
                },
                error -> {
                    // Handle error
                    Log.e("api", String.valueOf(error));
                    listener.onLoginResult(false, 0);
                });

        queue.add(jsonObjectRequest);
    }

    public void getClient(Context context, String clientId, OnClientResponseListener listener) {
        String url = "http://192.168.0.119:8081/getClient?id=" + clientId;
        Client client = new Client();

        RequestQueue queue = Volley.newRequestQueue(context);

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                response -> {
                    // Traitement de la réponse
                    listener.onClientResponse(response);
                },
                error -> {
                    // Gestion de l'erreur
                    Log.e("api", String.valueOf(error));
                    listener.onClientError();
                });

        queue.add(stringRequest);
    }

    public interface OnClientResponseListener {
        void onClientResponse(String response);

        void onClientError();
    }

    public void getCommande(Context context, String clientId, OnCommandeResponseListener listener) {
        String url = "http://192.168.0.119:8081/getCommandes?id=" + clientId;

        RequestQueue queue = Volley.newRequestQueue(context);

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                response -> {
                    // Traitement de la réponse
                    listener.onCommandeResponse(response);
                },
                error -> {
                    // Gestion de l'erreur
                    Log.e("api", String.valueOf(error));
                    listener.onCommandeError();
                });

        queue.add(stringRequest);
    }

    public interface OnCommandeResponseListener {
        void onCommandeResponse(String response);

        void onCommandeError();
    }

    public void getPizza(Context context, String pizzaId, OnPizzaResponseListener listener) {
        String url = "http://192.168.0.119:8081/getPizza?id=" + pizzaId;

        RequestQueue queue = Volley.newRequestQueue(context);

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                response -> {
                    // Traitement de la réponse
                    listener.onPizzaResponse(response);
                },
                error -> {
                    // Gestion de l'erreur
                    Log.e("api", String.valueOf(error));
                    listener.onPizzaError();
                });

        queue.add(stringRequest);
    }

    public void getAllPizzas(Context context, OnAllPizzasResponseListener listener) {
        String url = "http://192.168.0.119:8081/getAllPizzas";

        RequestQueue queue = Volley.newRequestQueue(context);

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                response -> {
                    // Traitement de la réponse
                    List<PizzaItem> pizzaItemList = parsePizzaList(response);
                    listener.onAllPizzasResponse(pizzaItemList);
                },
                error -> {
                    // Gestion de l'erreur
                    Log.e("api", String.valueOf(error));
                    listener.onAllPizzasError();
                });

        queue.add(stringRequest);
    }

    private List<PizzaItem> parsePizzaList(String response) {
        Gson gson = new Gson();
        Type listType = new TypeToken<List<PizzaItem>>(){}.getType();
        return gson.fromJson(response, listType);
    }

    public interface OnPizzaResponseListener {
        void onPizzaResponse(String response);

        void onPizzaError();
    }

    public interface OnAllPizzasResponseListener {
        void onAllPizzasResponse(List<PizzaItem> pizzaItemList);

        void onAllPizzasError();
    }



}
