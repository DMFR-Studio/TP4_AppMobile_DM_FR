package com.example.tp4_dm_fr.customListener;

import com.example.tp4_dm_fr.entity.PizzaItem;

import java.util.List;

public interface OnAllPizzasResponseListener {
    void onAllPizzasResponse(List<PizzaItem> pizzaItemList);

    void onAllPizzasError();
}
