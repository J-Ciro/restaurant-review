package org.example.services;

import org.example.models.Dish;
import org.example.models.DishReview;
import org.example.models.Restaurant;
import org.example.models.RestaurantReview;
import org.example.observers.Observer;
import org.example.observers.Subject;
import org.example.observers.SubjectImplementation;
import org.example.repositories.RestaurantRepository;

public class ReviewService {
    private final RestaurantRepository restaurantRepository;
    private final RestaurantService restaurantService;
    private final Subject<RestaurantReview> reviewSubject;
    private final Subject<DishReview> dishReviewSubject;

    public ReviewService() {
        this.restaurantRepository = RestaurantRepository.getInstance();
        this.restaurantService = new RestaurantService();
        this.reviewSubject = new SubjectImplementation<>();
        this.dishReviewSubject = new SubjectImplementation<>();

    }

    public void attachObserverRestaurant(Observer<RestaurantReview> observer) {
        reviewSubject.attach(observer);
    }

    public void attachObserverDish(Observer<DishReview> observer) {
        dishReviewSubject.attach(observer);
    }

    public Dish findDish(String restaurantName, String menuName, String dishName) {
        Restaurant restaurant = restaurantService.findRestaurantByName(restaurantName);
        if (restaurant == null) return null;

        return restaurant.getMenus().stream()
                .filter(menu -> menu.getName().equals(menuName))
                .flatMap(menu -> menu.getItems().stream())
                .filter(dish -> dish.getName().equals(dishName))
                .findFirst()
                .orElse(null);
    }

    public void addReviewToDish(String restaurantName, String menuName, String dishName, DishReview review) {
        Dish dish = findDish(restaurantName, menuName, dishName);
        if (dish == null) {
            System.out.println("Plato no encontrado.");
            return;
        }

        dish.addDishReview(review);
        dishReviewSubject.notify(review);
    }

    public void addReviewToRestaurant(String restaurantName, RestaurantReview review) {
        Restaurant restaurant = restaurantRepository.findByName(restaurantName);
        if (restaurant != null) {
            restaurant.addReview(review);
            restaurantRepository.save(restaurant);
            reviewSubject.notify(review);
        } else {
            System.out.println("Restaurante no encontrado.");
        }
    }


}
