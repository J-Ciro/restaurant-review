package org.example.factories;

import org.example.commands.AddDishToMenuCommand;
import org.example.commands.AddMenuToRestaurantCommand;
import org.example.commands.AddRestaurantCommand;
import org.example.commands.AddReviewToDishCommand;
import org.example.commands.AddReviewToRestaurantCommand;
import org.example.commands.EditMenuRestaurant;
import org.example.commands.EditRestaurantCommand;
import org.example.commands.RemoveRestuarantCommand;
import org.example.controllers.DishController;
import org.example.controllers.MenuController;
import org.example.controllers.RestaurantController;
import org.example.controllers.ReviewController;
import org.example.utils.ICommand;
import org.example.utils.IHandler;

public class CommandFactory {
    private final RestaurantController restaurantController;
    private final DishController dishController;
    private final IHandler handler;
    private final MenuFactory menuFactory;
    private final DishFactory dishFactory;
    private final ReviewFactory reviewFactory;
    private final MenuController menuController;
    private final ReviewController reviewController;


    public CommandFactory(RestaurantController restaurantController, DishController dishController, MenuController menuController, ReviewController reviewController, IHandler handler, MenuFactory menuFactory, DishFactory dishFactory, ReviewFactory reviewFactory) {
        this.restaurantController = restaurantController;
        this.menuController = menuController;
        this.dishController = dishController;
        this.reviewController = reviewController;
        this.handler = handler;
        this.menuFactory = menuFactory;
        this.dishFactory = dishFactory;
        this.reviewFactory = reviewFactory;

    }

    public ICommand createCommand(int option) {
        return switch (option) {
            case 2 -> new AddRestaurantCommand(restaurantController, handler);
            case 3 -> new AddMenuToRestaurantCommand(restaurantController,menuController,handler, menuFactory);
            case 4 -> new AddDishToMenuCommand(dishController, handler, dishFactory);
            case 5 -> new AddReviewToRestaurantCommand(restaurantController, reviewController, handler, reviewFactory);
            case 6 -> new AddReviewToDishCommand(restaurantController,dishController, reviewController,handler, reviewFactory);
            case 7 -> new EditRestaurantCommand(restaurantController, handler);
            case 8 -> new RemoveRestuarantCommand(restaurantController, handler);
            case 9 -> new EditMenuRestaurant(menuController, dishController, handler);
            default -> null;
        };
    }
}