package org.example.commands;

import org.example.controllers.MenuController;
import org.example.controllers.RestaurantController;
import org.example.factories.MenuFactory;
import org.example.models.Menu;
import org.example.utils.ICommand;
import org.example.utils.IHandler;

import java.util.ArrayList;

public class AddMenuToRestaurantCommand implements ICommand {
    private final RestaurantController restaurantController;
    private final IHandler handler;
    private final MenuFactory menuFactory;
    private final MenuController menuController;

    public AddMenuToRestaurantCommand(RestaurantController restaurantController, MenuController menuController , IHandler handler, MenuFactory menuFactory) {
        this.menuFactory = menuFactory;
        this.menuController = menuController;
        this.restaurantController = restaurantController;
        this.handler = handler;
    }

    @Override
    public void execute() {
        handler.writeLine("Ingrese el nombre del restaurante:");
        String restaurantName = handler.readLine();

        handler.writeLine("Ingrese el nombre del menu:");
        String menuName = handler.readLine();

        Menu menu = menuFactory.createMenu(menuName, new ArrayList<>());
        menuController.addMenuToRestaurant(restaurantName, menu);

    }
}
