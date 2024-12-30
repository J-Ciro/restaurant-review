package org.example.commands;

import org.example.controllers.RestaurantController;
import org.example.utils.ICommand;
import org.example.utils.IHandler;

public class AddRestaurantCommand implements ICommand {
    private final RestaurantController restaurantController;
    private final IHandler handler;

    public AddRestaurantCommand(RestaurantController restaurantController, IHandler handler) {
        this.restaurantController = restaurantController;
        this.handler = handler;
    }

    @Override
    public void execute() {
        handler.writeLine("Ingrese el nombre del restaurante:");
        String name = handler.readLine();

        handler.writeLine("Ingrese la direccion del restaurante:");
        String address = handler.readLine();

        restaurantController.addRestaurant(name, address);

    }
}

