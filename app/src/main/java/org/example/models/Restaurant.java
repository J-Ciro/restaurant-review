package org.example.models;

import java.util.ArrayList;
import java.util.List;

public class Restaurant {

    private String name;
    private String address;
    private List<Menu> menus;
    private List<RestaurantReview> reviews;
    private double stars;

    public Restaurant(String name, String address, List<Menu> menus, List<RestaurantReview> reviews) {
        this.name = name;
        this.address = address;
        this.menus = menus;
        this.reviews = reviews;
        updateStars();
    }

    public Restaurant(String name, String address) {
        this(name, address, new ArrayList<>(), new ArrayList<>());
    }

    public void addMenu(Menu menu) {
        if (this.menus == null) {
            this.menus = new ArrayList<>();
        }
        this.menus.add(menu);
    }

    public void addReview(RestaurantReview review) {
        if (this.reviews == null) {
            this.reviews = new ArrayList<>();
        }
        this.reviews.add(review);
        updateStars();
    }

    private void updateStars() {
        if (this.reviews.isEmpty()) {
            this.stars = 0.0;
        } else {
            this.stars = this.reviews.stream().mapToDouble(RestaurantReview::getStars).average().orElse(0.0);
        }
    }

    public double getStars() {
        return stars;
    }

    public List<Menu> getMenus() {
        return menus;
    }

    public List<RestaurantReview> getReviews() {
        return reviews;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setMenus(List<Menu> menus) {
        this.menus = menus;
    }

    public void setReviews(List<RestaurantReview> reviews) {
        this.reviews = reviews;
    }

    public void setStars(double stars) {
        this.stars = stars;
    }

}