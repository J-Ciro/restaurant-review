package org.example.models;

import java.util.List;

public class Dish {

    private String name;
    private Double price;
    private List<DishReview> dishReview;
    private Double stars;

    public Dish(String name, Double price, List<DishReview> dishReview) {
        this.name = name;
        this.price = price;
        this.stars = 0.0;
        this.dishReview = dishReview;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public List<DishReview> getDishReview() {
        return dishReview;
    }

    public void setDishReview(List<DishReview> dishReview) {
        this.dishReview = dishReview;
    }

    public void addDishReview(DishReview review) {
        this.dishReview.add(review);
    }

    public Double getStars() {
        if (dishReview.isEmpty()) {
            return 0.0;
        }
        double totalStars = 0.0;
        for (DishReview review : dishReview) {
            totalStars += review.getRating();
        }
        return totalStars / dishReview.size();
    }
}