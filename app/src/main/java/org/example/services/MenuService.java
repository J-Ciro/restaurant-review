package org.example.services;

import org.example.models.Menu;
import org.example.models.Restaurant;
import org.example.observers.Observer;
import org.example.observers.Subject;
import org.example.observers.SubjectImplementation;
import org.example.repositories.MenuRepository;
import org.example.repositories.RestaurantRepository;

public class MenuService {
    private final MenuRepository menuRepository;
    private final RestaurantRepository restaurantRepository;
    private final Subject<Menu> menuSubject;

    public MenuService() {

        this.menuRepository = MenuRepository.getInstance();
        this.restaurantRepository = RestaurantRepository.getInstance();
        this.menuSubject = new SubjectImplementation<>();
    }

    public void attachObserver(Observer<Menu> observer) {
        menuSubject.attach(observer);
    }

    public void addMenuToRestaurant(String restaurantName, Menu menu) {
        Restaurant restaurant = restaurantRepository.findByName(restaurantName);
        if (restaurant != null) {
            restaurant.addMenu(menu);
            menuRepository.save(menu);
            menuSubject.notify(menu);

        } else {
            System.out.println("Restaurante no encontrado.");
        }
    }
}
