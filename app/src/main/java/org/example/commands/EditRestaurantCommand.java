package org.example.commands;

import org.example.controllers.RestaurantController;
import org.example.utils.ICommand;
import org.example.utils.IHandler;

public class EditRestaurantCommand implements ICommand {

        private final RestaurantController restaurantController;
        private final IHandler handler;

    public EditRestaurantCommand(RestaurantController restaurantController, IHandler handler) {
        this.restaurantController = restaurantController;
        this.handler = handler;
    }

    @Override
        public void execute() {
            handler.writeLine("+-- Editar Restaurante --+");

            handler.writeLine("Ingrese el nombre del restaurante a editar:");
            String name = handler.readLine();

            handler.writeLine("Ingrese el nuevo nombre del restaurante (Si no desea cambiar el nombre, dejalo en blanco, si deseas eliminarlo coloca eliminar):");
            String newName = handler.readLine();

            handler.writeLine("Ingrese la nueva direccion del restaurante (Si no desea cambiar el nombre, dejao en blanco, si deseas eliminarlo coloca eliminar):");
            String address = handler.readLine();


            restaurantController.editRestaurant(name,newName,address);
        }

}
