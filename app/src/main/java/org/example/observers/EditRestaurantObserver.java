package org.example.observers;

import org.example.models.Restaurant;

public class EditRestaurantObserver implements Observer<Restaurant> {
    @Override
    public void update(Restaurant restaurant) {
        System.out.println(String.format("""
                \033[1;32m[+]\033[0m \033[1;33mNotificación\033[0m: Se ha editado el restaurante - \033[1;34m%s\033[0m
                """, restaurant.getName()));
    }


}
