package org.example.observers;

import org.example.models.RestaurantReview;

public class NewRestaurantReviewObserver implements Observer<RestaurantReview> {
    @Override
    public void update(RestaurantReview review) {
        System.out.println(String.format("""
                \033[1;32m[+]\033[0m \033[1;33mNotificación\033[0m: Se ha agregado una nueva review al restaurante  - \033[1;34m%s\033[0m
                """, review.getRestaurantName()));
    }

}