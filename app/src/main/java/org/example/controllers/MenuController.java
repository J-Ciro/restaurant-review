package org.example.controllers;

import org.example.models.Menu;
import org.example.models.Restaurant;
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
        this.restaurants = new ArrayList<>();
    }


    public void editMenu(String restaurantName, String menuName, String newMenuName) {
        menuService.editMenu(restaurantName, menuName, newMenuName);
    }


    public void attachObserverNewMenu(Observer<Menu> observer) {
        menuService.attachObserverNewMenu(observer);
    }

    public void attachObserverEditMenu(Observer<Menu> observer) {
        menuService.attachObserverEditMenu(observer);
    }

    public void addMenuToRestaurant(String restaurantName, Menu menu) {
        menuService.addMenuToRestaurant(restaurantName, menu);
    }
}