package org.example.controllers;

import org.example.models.Restaurant;
import org.example.observers.Observer;
import org.example.services.RestaurantService;

public class RestaurantController {

    private final RestaurantService restaurantService;


    public RestaurantController() {
        this.restaurantService = new RestaurantService();

    }

    public void attachObserver(Observer<Restaurant> observer) {
        restaurantService.attachObserver(observer);
    }

    public void addRestaurant(String restaurantName, String restaurantAddress) {
        restaurantService.addRestaurant(restaurantName, restaurantAddress);

    }

    public void showRestaurants() {
        restaurantService.showRestaurants();
    }



}