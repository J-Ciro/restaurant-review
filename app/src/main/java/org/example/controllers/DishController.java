package org.example.controllers;

import org.example.models.*;
import org.example.services.DishService;
import org.example.services.RestaurantService;
import org.example.observers.*;

public class DishController {
    private final Subject<Dish> dishSubject;
    private final RestaurantService restaurantService;
    private final DishService dishService;

    public DishController() {
        this.restaurantService = new RestaurantService();
        this.dishSubject = new SubjectImplementation<>();
        this.dishSubject.attach(new ConsoleNotificationObserver<>());
        this.dishService = new DishService();
    }

    public void attachObserver(Observer<Dish> observer) {
        dishService.attachObserver(observer);
    }

    public void addDishToMenu(String restaurantName, String menuName, Dish dish) {
        dishService.addDishToMenu(restaurantName, menuName, dish);
    }

    public Dish findDish(String restaurantName, String menuName, String dishName) {
        return dishService.findDish(restaurantName, menuName, dishName);
    }


}