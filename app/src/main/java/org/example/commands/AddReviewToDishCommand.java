package org.example.commands;

import org.example.controllers.DishController;
import org.example.controllers.RestaurantController;
import org.example.controllers.ReviewController;
import org.example.factories.ReviewFactory;
import org.example.models.Dish;
import org.example.models.DishReview;
import org.example.models.Review;
import org.example.utils.ICommand;
import org.example.utils.IHandler;

public class AddReviewToDishCommand implements ICommand {

    private final RestaurantController restaurantController;
    private final DishController dishController;
    private final ReviewController reviewController;
    private final IHandler handler;
    private final ReviewFactory reviewFactory;

    public AddReviewToDishCommand(RestaurantController restaurantController, DishController dishController, ReviewController reviewController, IHandler handler, ReviewFactory reviewFactory) {
        this.restaurantController = restaurantController;
        this.dishController = dishController;
        this.reviewController = reviewController;
        this.reviewFactory = reviewFactory;
        this.handler = handler;

    }

    @Override
    public void execute() {
        handler.writeLine("Ingrese el nombre del restaurante:");
        String restaurantName = handler.readLine();

        handler.writeLine("Ingrese el nombre del menu:");
        String menuName = handler.readLine();

        handler.writeLine("Ingrese el nombre del plato:");
        String dishName = handler.readLine();

        handler.writeLine("Ingrese su comentario:");
        String comment = handler.readLine();

        handler.writeLine("Ingrese la calificacion (1.0 - 5.0):");
        Double stars;
        try {
            stars = Double.parseDouble(handler.readLine());
            if (stars < 1 || stars > 5) {
                handler.writeLine("Calificacion no válida. Debe ser entre 1 y 5.");
                return;
            }
        } catch (NumberFormatException e) {
            handler.writeLine("Calificacion no válida. Debe ser un número.");
            return;
        }

        Dish dish = dishController.findDish(restaurantName, menuName, dishName);
        if (dish == null) {
            handler.writeLine("Plato no encontrado.");
            return;
        }

        Review review = reviewFactory.createReview("dish", dishName, stars, comment, null);
        reviewController.addReviewToDish(restaurantName, menuName, dishName, (DishReview) review);

    }
}
