package org.example.commands;

import org.example.controllers.DishController;
import org.example.controllers.MenuController;
import org.example.utils.ICommand;
import org.example.utils.IHandler;

public class EditMenuRestaurant implements ICommand {
    private final DishController dishController;
    private final IHandler handler;
    private final MenuController menuController;

    public EditMenuRestaurant(MenuController menuController, DishController dishController, IHandler handler) {
        this.dishController = dishController;
        this.menuController = menuController;
        this.handler = handler;
    }

    @Override
    public void execute() {
        handler.writeLine("+-- Editar Menu de Restaurante --+");

        handler.writeLine("Ingrese el nombre del restaurante:");
        String restaurantName = handler.readLine();

        handler.writeLine("Ingrese el nombre del menu:");
        String menuName = handler.readLine();

        handler.writeLine("¿Qué desea hacer?");
        handler.writeLine("1. Cambiar nombre del menú");
        handler.writeLine("2. Editar un plato");
        handler.writeLine("3. Eliminar un plato");
        String option = handler.readLine();

        switch (option) {
            case "1":
                handler.writeLine("Ingrese el nuevo nombre del menu:");
                String newMenuName = handler.readLine();
                menuController.editMenu(restaurantName, menuName, newMenuName);
                break;

            case "2":
                handler.writeLine("Ingrese el nombre del plato a editar:");
                String dishName = handler.readLine();

                handler.writeLine("Ingrese el nuevo nombre del plato (dejar en blanco para mantener el actual):");
                String newDishName = handler.readLine();

                handler.writeLine("Ingrese el nuevo precio del plato (dejar en blanco para mantener el actual):");
                String priceStr = handler.readLine();
                Double newPrice = null;
                if (!priceStr.trim().isEmpty()) {
                    try {
                        newPrice = Double.parseDouble(priceStr);
                    } catch (NumberFormatException e) {
                        handler.writeLine("Precio inválido. Se mantendrá el precio actual.");
                    }
                }

                dishController.editDishInMenu(restaurantName, menuName, dishName, newDishName, newPrice);
                break;

            case "3":
                handler.writeLine("Ingrese el nombre del plato a eliminar:");
                String dishToRemove = handler.readLine();
                dishController.removeDishFromMenu(restaurantName, menuName, dishToRemove);
                break;

            default:
                handler.writeLine("Opción no válida");
                break;
        }
    }
}