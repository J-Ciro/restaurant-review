package org.example.commands;

import org.example.controllers.RestaurantController;
import org.example.utils.ICommand;
import org.example.utils.IHandler;

public class RemoveRestuarantCommand implements ICommand {

    private final RestaurantController restaurantController;
    private final IHandler handler;

    public RemoveRestuarantCommand(RestaurantController restaurantController, IHandler handler) {
        this.restaurantController = restaurantController;
        this.handler = handler;
    }

    @Override
    public void execute() {
        handler.writeLine("+-- Eliminar Restaurante --+");

        handler.writeLine("Ingrese el nombre del restaurante a eliminar:");
        String name = handler.readLine();
        restaurantController.removeRestaurant(name);
    }

}
