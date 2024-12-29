package org.example.models;

import org.example.observers.Observer;

public class DishReview extends Review {
    public DishReview(String name, Double rating, String comment) {
        super(name, rating, comment);
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

    public String getDishName() {
        return getName();
    }
}
