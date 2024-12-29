package org.example.factories;

import org.example.models.DishReview;
import org.example.models.RestaurantReview;
import org.example.models.Review;

public class ReviewFactory {
    public Review createReview(String type, String nameOrDishName, Double rating, String comment, Object context) {
        return switch (type.toLowerCase()) {
            case "restaurant" -> new RestaurantReview((String) context, rating, comment);
            case "dish" -> new DishReview(nameOrDishName, rating, comment);
            default -> throw new IllegalArgumentException("Tipo de review no válido");
        };
    }
}