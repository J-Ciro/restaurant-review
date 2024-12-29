package org.example.services;

import org.example.models.Dish;
import org.example.models.Menu;
import org.example.models.Restaurant;
import org.example.observers.Observer;
import org.example.observers.Subject;
import org.example.observers.SubjectImplementation;
import org.example.repositories.DishRepository;

public class DishService {

    private final DishRepository dishRepository;
    private final RestaurantService restaurantService;
    private final Subject<Dish> dishSubject;


    public DishService() {
        this.dishRepository = DishRepository.getInstance();
        this.restaurantService = new RestaurantService();
        this.dishSubject = new SubjectImplementation<>();

    }

    public void attachObserver(Observer<Dish> observer) {
        dishSubject.attach(observer);
    }

    public void addDishToMenu(String restaurantName, String menuName, Dish dish) {
        Restaurant restaurant = restaurantService.findRestaurantByName(restaurantName);
        if (restaurant == null) {
            System.out.println("Restaurante no encontrado.");
            return;
        }

        Menu menu = restaurant.getMenus().stream()
                .filter(m -> m.getName().equals(menuName))
                .findFirst()
                .orElse(null);

        if (menu == null) {
            System.out.println("Menú no encontrado.");
            return;
        }

        menu.addDish(dish);
        dishSubject.notify(dish);
    }

    public Dish findDish(String restaurantName, String menuName, String dishName) {
        Restaurant restaurant = restaurantService.findRestaurantByName(restaurantName);
        if (restaurant == null) return null;

        return restaurant.getMenus().stream()
                .filter(menu -> menu.getName().equals(menuName))
                .flatMap(menu -> menu.getItems().stream())
                .filter(dish -> dish.getName().equals(dishName))
                .findFirst()
                .orElse(null);
    }



}