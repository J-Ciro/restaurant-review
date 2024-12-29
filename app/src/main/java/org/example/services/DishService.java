package org.example.services;

import org.example.models.Dish;
import org.example.models.Menu;
import org.example.models.Restaurant;
import org.example.observers.Observer;
import org.example.observers.Subject;
import org.example.observers.SubjectImplementation;
import org.example.repositories.DishRepository;
import org.example.repositories.MenuRepository;
import org.example.repositories.RestaurantRepository;

public class DishService {

    private final DishRepository dishRepository;
    private final RestaurantRepository restaurantRepository;
    private final MenuRepository menuRepository;
    private final RestaurantService restaurantService;
    private final Subject<Dish> dishSubject;
    private final Subject<Dish> remvoeDishSubject;
    private final Subject<Dish>  editDishInMenu;
    private final Subject<Menu> editMenuSubject;
//    private final Subject<Menu> editDishInMenu;


    public DishService() {
        this.menuRepository = MenuRepository.getInstance();
        this.restaurantRepository = RestaurantRepository.getInstance();
        this.dishRepository = DishRepository.getInstance();
        this.restaurantService = new RestaurantService();
        this.dishSubject = new SubjectImplementation<>();
        this.editMenuSubject = new SubjectImplementation<>();
        this.remvoeDishSubject = new SubjectImplementation<>();
        this.editDishInMenu = new SubjectImplementation<>();

    }

    public void attachObserver(Observer<Dish> observer) {
        dishSubject.attach(observer);
    }

    public void attachORemovebserver(Observer<Dish> observer) {
        remvoeDishSubject.attach(observer);
    }

    public void attachEditObserver(Observer<Dish> observer) {
        editDishInMenu.attach(observer);
    }

    public void editDish(String restaurantName, String menuName, String dishName, String newDishName, Double price) {
        Dish dish = findDish(restaurantName, menuName, dishName);
        if (dish != null) {
            dish.setName(newDishName);
            dish.setPrice(price);
            dishRepository.save(dish);
            dishSubject.notify(dish);
        } else {
            System.out.println("Plato no encontrado.");
        }
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

    public void removeDishFromMenu(String restaurantName, String menuName, String dishName) {
        Restaurant restaurant = validateRestaurant(restaurantName);
        if (restaurant == null) return;

        Menu menu = validateMenu(restaurant, menuName);
        if (menu == null) return;

        Dish dish = validateDish(menu, dishName);
        if (dish == null) return;

        menu.getItems().remove(dish);
        menuRepository.save(menu);
        remvoeDishSubject.notify(dish);
    }

    public void editDishInMenu(String restaurantName, String menuName, String dishName, String newDishName, Double newPrice) {
        Restaurant restaurant = validateRestaurant(restaurantName);
        if (restaurant == null) return;

        Menu menu = validateMenu(restaurant, menuName);
        if (menu == null) return;

        Dish dish = validateDish(menu, dishName);
        if (dish == null) return;

        if (isValidString(newDishName)) {
            dish.setName(newDishName);
        }
        if (newPrice != null) {
            dish.setPrice(newPrice);
        }

        menuRepository.save(menu);
        editDishInMenu.notify(dish);
    }

    private Dish validateDish(Menu menu, String dishName) {
        Dish dish = findDishInMenu(menu, dishName);
        if (dish == null) {
            System.out.println("Plato no encontrado en el menú.");
        }
        return dish;
    }
    private Dish findDishInMenu(Menu menu, String dishName) {
        return menu.getItems().stream()
                .filter(d -> d.getName().equalsIgnoreCase(dishName))
                .findFirst()
                .orElse(null);
    }

    private Menu validateMenu(Restaurant restaurant, String menuName) {
        Menu menu = findMenuInRestaurant(restaurant, menuName);
        if (menu == null) {
            System.out.println("Menú no encontrado.");
        }
        return menu;
    }

    private Menu findMenuInRestaurant(Restaurant restaurant, String menuName) {
        return restaurant.getMenus().stream()
                .filter(m -> m.getName().equalsIgnoreCase(menuName))
                .findFirst()
                .orElse(null);
    }

    private Restaurant validateRestaurant(String restaurantName) {
        Restaurant restaurant = restaurantRepository.findByName(restaurantName);
        if (restaurant == null) {
            System.out.println("Restaurante no encontrado.");
        }
        return restaurant;
    }


    private boolean isValidString(String value) {
        return value != null && !value.trim().isEmpty();
    }



}