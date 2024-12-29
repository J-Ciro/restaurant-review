package org.example.controllers;

import org.example.models.Menu;
import org.example.models.Restaurant;
import org.example.observers.ConsoleNotificationObserver;
import org.example.observers.Observer;
import org.example.observers.SubjectImplementation;
import org.example.observers.Subject;
import org.example.services.MenuService;


import java.util.ArrayList;
import java.util.List;

public class MenuController {

    private final Subject<Menu> menuSubject;
    private final List<Restaurant> restaurants;

    private final MenuService menuService;


    public MenuController() {
        this.menuService = new MenuService();
        this.menuSubject = new SubjectImplementation<>();
        this.menuSubject.attach(new ConsoleNotificationObserver<>());
        this.restaurants = new ArrayList<>();
    }


    public void attachObserver(Observer<Menu> observer) {
        menuService.attachObserver(observer);
    }

    public void addMenuToRestaurant(String restaurantName, Menu menu) {
        menuService.addMenuToRestaurant(restaurantName, menu);
    }
}