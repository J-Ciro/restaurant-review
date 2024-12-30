package org.example.commands;

import org.example.controllers.DishController;
import org.example.factories.DishFactory;
import org.example.models.Dish;
import org.example.utils.ICommand;
import org.example.utils.IHandler;

public class AddDishToMenuCommand implements ICommand {


    private final DishController dishController;
    private final IHandler handler;
    private final DishFactory dishFactory;

    public AddDishToMenuCommand(DishController dishController, IHandler handler, DishFactory dishFactory) {
        this.dishController = dishController;
        this.handler = handler;
        this.dishFactory = dishFactory;
    }


    @Override
    public void execute(){
        handler.writeLine("Ingrese el nombre del restaurante:");
        String restaurantName = handler.readLine();

        handler.writeLine("Ingrese el nombre del menu:");
        String menuName = handler.readLine();

        handler.writeLine("Ingrese el nombre del plato:");
        String dishName = handler.readLine();

        handler.writeLine("Ingrese el precio del plato:");
        double price;
        try {
            price = Double.parseDouble(handler.readLine());
        } catch (NumberFormatException e) {
            handler.writeLine("Precio no válido. Intentelo de nuevo.");
            return;
        }

        Dish dish = dishFactory.createDish(dishName, price);
        dishController.addDishToMenu(restaurantName, menuName, dish);
    }

}
