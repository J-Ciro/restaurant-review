package org.example;

import org.example.controllers.DishController;
import org.example.controllers.MenuController;
import org.example.controllers.ReviewController;
import org.example.factories.DishFactory;
import org.example.factories.MenuFactory;
import org.example.factories.ReviewFactory;
import org.example.controllers.RestaurantController;
import org.example.models.Menu;
import org.example.models.Restaurant;
import org.example.observers.EditDishObserver;
import org.example.observers.EditMenuObserver;
import org.example.observers.EditRestaurantObserver;
import org.example.observers.NewDishObserver;
import org.example.observers.NewDishReviewObserver;
import org.example.observers.NewMenuObserver;
import org.example.observers.NewRestaurantObserver;
import org.example.observers.NewRestaurantReviewObserver;
import org.example.observers.Observer;
import org.example.observers.RemoveDishObserver;
import org.example.observers.RemoveRestaurantObserver;
import org.example.utils.ConsoleHandler;
import org.example.utils.IHandler;
import org.example.utils.RestaurantMenu;

public class App {

    public static void main(String[] args) {

        Observer<Restaurant> restaurantObserver = new NewRestaurantObserver();

        Observer<Menu> menuObserver = new NewMenuObserver();

        RestaurantController restaurantController = new RestaurantController();
        restaurantController.attachNewRestaurantObserver(new NewRestaurantObserver());
        restaurantController.attachEditRestaurantObserver(new EditRestaurantObserver());
        restaurantController.attachRemoveRestaurantObserver(new RemoveRestaurantObserver());


        MenuController menuController = new MenuController();
        menuController.attachObserverNewMenu(new NewMenuObserver());
        menuController.attachObserverEditMenu(new EditMenuObserver());


        DishController dishController = new DishController();
        dishController.attachObserver(new NewDishObserver());
        dishController.attachORemovebserver(new RemoveDishObserver());
        dishController.attachEditObserver(new EditDishObserver());

        ReviewController reviewController = new ReviewController();
        reviewController.attachObserverRestaurant(new NewRestaurantReviewObserver());
        reviewController.attachObserverDish(new NewDishReviewObserver());


        MenuFactory menuFactory = new MenuFactory();

        ReviewFactory reviewFactory = new ReviewFactory();
        DishFactory dishFactory = new DishFactory();
        IHandler handler = new ConsoleHandler();

        RestaurantMenu menu = new RestaurantMenu(restaurantController,dishController,menuController ,reviewController,handler, menuFactory, dishFactory, reviewFactory, restaurantObserver);
        menu.displayMenu();
    }
}