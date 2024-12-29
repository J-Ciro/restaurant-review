package org.example.observers;

import org.example.models.DishReview;

public class NewDishReviewObserver implements Observer<DishReview> {
    @Override
    public void update(DishReview review) {
        System.out.println(String.format("""
                \033[1;32m[+]\033[0m \033[1;33mNotificación\033[0m: Se ha agregado una nueva review al plato  - \033[1;34m%s\033[0m
                """, review.getDishName()));
    }


}