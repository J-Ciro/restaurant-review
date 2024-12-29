package org.example.models;

import org.example.observers.Observer;

public class RestaurantReview extends Review {
    public RestaurantReview(String name, Double rating, String comment) {
        super(name, rating, comment);
    }

    public double getStars() {
        return getRating();
    }

    @Override
    public void attach(Observer observer) {

    }

    @Override
    public void detach(Observer observer) {

    }

    @Override
    public void notify(Object event) {

    }

    public String getRestaurantName() {
        return getName();
    }
}