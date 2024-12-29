package org.example.services;

import org.example.models.Dish;
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
    private final Subject<Menu> newMenuSubject;
    private final Subject<Menu> editMenuSubject;

    public MenuService() {
        this.menuRepository = MenuRepository.getInstance();
        this.restaurantRepository = RestaurantRepository.getInstance();
        this.newMenuSubject = new SubjectImplementation<>();
        this.editMenuSubject = new SubjectImplementation<>();
    }

    public void attachObserverNewMenu(Observer<Menu> observer) {
        newMenuSubject.attach(observer);
    }

    public void attachObserverEditMenu(Observer<Menu> observer) {
        editMenuSubject.attach(observer);
    }

    public void addMenuToRestaurant(String restaurantName, Menu menu) {
        Restaurant restaurant = validateRestaurant(restaurantName);
        if (restaurant == null) return;

        restaurant.addMenu(menu);
        menuRepository.save(menu);
        newMenuSubject.notify(menu);
    }

    public void editMenu(String restaurantName, String menuName, String newMenuName) {
        Restaurant restaurant = validateRestaurant(restaurantName);
        if (restaurant == null) return;

        Menu menu = validateMenu(restaurant, menuName);
        if (menu == null) return;

        if (isValidString(newMenuName)) {
            menu.setName(newMenuName);
            menuRepository.save(menu);
            editMenuSubject.notify(menu);
            System.out.println("Menú actualizado correctamente.");
        }
    }



    private Restaurant validateRestaurant(String restaurantName) {
        Restaurant restaurant = restaurantRepository.findByName(restaurantName);
        if (restaurant == null) {
            System.out.println("Restaurante no encontrado.");
        }
        return restaurant;
    }

    private Menu validateMenu(Restaurant restaurant, String menuName) {
        Menu menu = findMenuInRestaurant(restaurant, menuName);
        if (menu == null) {
            System.out.println("Menú no encontrado.");
        }
        return menu;
    }


    private boolean isValidString(String value) {
        return value != null && !value.trim().isEmpty();
    }

    private Menu findMenuInRestaurant(Restaurant restaurant, String menuName) {
        return restaurant.getMenus().stream()
                .filter(m -> m.getName().equalsIgnoreCase(menuName))
                .findFirst()
                .orElse(null);
    }


}
