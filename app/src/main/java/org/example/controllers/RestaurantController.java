package org.example.controllers;

import org.example.models.Restaurant;
import org.example.observers.Observer;
import org.example.services.RestaurantService;

public class RestaurantController {

    private final RestaurantService restaurantService;


    public RestaurantController() {
        this.restaurantService = new RestaurantService();

    }

    public void attachNewRestaurantObserver(Observer<Restaurant> observer) {
        restaurantService.attachNewRestaurantObserver(observer);
    }

    public void attachEditRestaurantObserver(Observer<Restaurant> observer) {
        restaurantService.attachEditRestaurantObserver(observer);
    }

    public void attachRemoveRestaurantObserver(Observer<Restaurant> observer) {
        restaurantService.attachRemoveRestaurantObserver(observer);
    }

    public void removeRestaurant(String restaurantName) {
        restaurantService.removeRestaurant(restaurantName);
    }

    public void editRestaurant(String restaurantName,String newRestaurantName, String restaurantAddress) {
        restaurantService.editRestaurant(restaurantName,newRestaurantName ,restaurantAddress);
    }

    public void addRestaurant(String restaurantName, String restaurantAddress) {
        restaurantService.addRestaurant(restaurantName, restaurantAddress);

    }

    public void showRestaurants() {
        restaurantService.showRestaurants();
    }



}