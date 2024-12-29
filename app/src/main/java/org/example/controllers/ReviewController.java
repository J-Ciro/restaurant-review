package org.example.controllers;

import org.example.models.DishReview;
import org.example.models.RestaurantReview;
import org.example.models.Review;
import org.example.observers.Observer;
import org.example.observers.Subject;
import org.example.observers.SubjectImplementation;
import org.example.services.ReviewService;

public class ReviewController {

    private final ReviewService reviewService;
    private final Subject<Review> reviewSubject;

    public ReviewController() {
        this.reviewService = new ReviewService();
        this.reviewSubject = new SubjectImplementation<>();
    }

    public void attachObserverRestaurant(Observer<RestaurantReview> observer) {
        reviewService.attachObserverRestaurant(observer);
    }
    public void attachObserverDish(Observer<DishReview> observer) {
        reviewService.attachObserverDish(observer);
    }


    public void addReviewToRestaurant(String restaurantName, RestaurantReview review) {
        reviewService.addReviewToRestaurant(restaurantName, review);
    }

    public void addReviewToDish(String restaurantName, String menuName, String dishName, DishReview review) {
        reviewService.addReviewToDish(restaurantName, menuName, dishName, review);
    }
}
