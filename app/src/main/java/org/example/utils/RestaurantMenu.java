package org.example.utils;

import org.example.controllers.DishController;
import org.example.controllers.MenuController;
import org.example.controllers.RestaurantController;
import org.example.controllers.ReviewController;
import org.example.factories.CommandFactory;
import org.example.factories.DishFactory;
import org.example.factories.MenuFactory;
import org.example.factories.ReviewFactory;
import org.example.models.Restaurant;
import org.example.observers.NewRestaurantObserver;
import org.example.observers.Observer;

public class RestaurantMenu {
    private final IHandler handler;
    private final CommandFactory commandFactory;
    private final RestaurantController restaurantController;
    private final ReviewController reviewController;
    private final MenuController menuController;
    private final MenuFactory menuFactory;
    private final DishFactory dishFactory;
    private final ReviewFactory reviewFactory;
    private final Observer<Restaurant> restaurantObserver;


    public RestaurantMenu(RestaurantController restaurantController, DishController dishController,MenuController menuController, ReviewController reviewController, IHandler handler, MenuFactory menuFactory, DishFactory dishFactory, ReviewFactory reviewFactory, Observer<Restaurant> restaurantObserver) {
        this.handler = handler;
        this.commandFactory = new CommandFactory(restaurantController, dishController ,menuController, reviewController, handler, menuFactory, dishFactory, reviewFactory);
        this.restaurantController = restaurantController;
        this.reviewController = reviewController;
        this.menuController = menuController;
        this.menuFactory = new MenuFactory();
        this.dishFactory = new DishFactory();
        this.reviewFactory = new ReviewFactory();
        this.restaurantObserver = restaurantObserver;
    }

    public void displayMenu() {
        handler.writeLine(" +--- Bienvenido al Review de restaurantes ---+ ");

        while (true) {
            handler.writeLine("\nSelecciona una opcion:");
            handler.writeLine("1. Mostrar Restaurantes");
            handler.writeLine("2. Agregar Restaurante");
            handler.writeLine("3. Agregar Menu a Restaurante");
            handler.writeLine("4. Agregar Plato a Menu");
            handler.writeLine("5. Agregar Review a Restaurante");
            handler.writeLine("6. Agregar Review a Plato");
            handler.writeLine("7. Editar Restaurante");
            handler.writeLine("8. Eliminar Restaurante");
            handler.writeLine("9. Editar Menu");
            handler.writeLine("0. Salir");

            int option;
            try {
                option = Integer.parseInt(handler.readLine());
            } catch (NumberFormatException e) {
                handler.writeLine("Opcion no válida. Intentelo de nuevo.");
                continue;
            }

            if (option == 1) {
                restaurantController.showRestaurants();
            } else if (option == 0) {
                handler.writeLine("Gracias por usar el sistema. ¡Hasta luego!");
                return;
            } else {
                ICommand command = commandFactory.createCommand(option);
                if (command != null) {
                    command.execute();
                } else {
                    handler.writeLine("Opcion no valida. Intentelo de nuevo.");
                }
            }
        }
    }
}