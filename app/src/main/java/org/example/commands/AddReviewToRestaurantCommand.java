package org.example.commands;

import org.example.controllers.RestaurantController;
import org.example.controllers.ReviewController;
import org.example.factories.ReviewFactory;
import org.example.models.RestaurantReview;
import org.example.models.Review;
import org.example.utils.ICommand;
import org.example.utils.IHandler;

public class AddReviewToRestaurantCommand implements ICommand {


    private final RestaurantController restaurantController;
    private final ReviewController reviewController;
    private final IHandler handler;
    private final ReviewFactory reviewFactory;

    public AddReviewToRestaurantCommand(RestaurantController restaurantController, ReviewController reviewController, IHandler handler, ReviewFactory reviewFactory) {
        this.restaurantController = restaurantController;
        this.reviewController = reviewController;
        this.handler = handler;
        this.reviewFactory = reviewFactory;
    }


    @Override
    public void execute() {
        handler.writeLine("Ingrese el nombre del restaurante:");
        String restaurantName = handler.readLine();

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

        Review review = reviewFactory.createReview("restaurant", restaurantName, stars, comment, restaurantName);
        reviewController.addReviewToRestaurant(restaurantName, (RestaurantReview) review);
    }
}
