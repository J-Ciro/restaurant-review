package org.example.services;
import org.example.models.Restaurant;
import org.example.observers.Observer;
import org.example.observers.SubjectImplementation;
import org.example.observers.Subject;
import org.example.repositories.RestaurantRepository;
import java.util.List;

public class RestaurantService {
    private final RestaurantRepository restaurantRepository;
    private final Subject<Restaurant> restaurantSubject;


    public RestaurantService() {
        this.restaurantRepository = RestaurantRepository.getInstance();
        this.restaurantSubject = new SubjectImplementation<>();
    }

    public void attachObserver(Observer<Restaurant> observer) {
        restaurantSubject.attach(observer);
    }

    public void addRestaurant(String restaurantName, String restaurantAddress) {
        Restaurant restaurant = new Restaurant(restaurantName, restaurantAddress);
        restaurantRepository.save(restaurant);
        restaurantSubject.notify(restaurant);


    }

    public List<Restaurant> showRestaurants() {
        System.out.println("+--- Restaurantes ---+");
        List<Restaurant> allRestaurants = restaurantRepository.findAll();
        if (allRestaurants.isEmpty()) {
            System.out.println("No hay restaurantes disponibles.");
        } else {
            allRestaurants.forEach(r -> {
                System.out.println("Nombre: " + r.getName());
                System.out.println("Direccion: " + r.getAddress());
                System.out.println("Estrellas: " + r.getStars());
                System.out.println("Menus: ");
                r.getMenus().forEach(menu -> {
                    System.out.println("  " + menu.getName());
                    menu.getItems().forEach(dish -> {
                        System.out.println("    Plato: " + dish.getName() + ", Precio: " + dish.getPrice() + ", Estrellas: " + dish.getStars() );
                        System.out.println("    Reviews: ");
                        dish.getDishReview().forEach(review -> {
                            System.out.println("      " + review.getComment() + " - " + review.getRating() + " estrellas");
                        });
                    });
                });
                System.out.println("Reviews: ");
                r.getReviews().forEach(review -> {
                    System.out.println("  " + review.getComment() + " - " + review.getStars() + " estrellas");
                });
                System.out.println();
            });
        }
        return allRestaurants;
    }


    public Restaurant findRestaurantByName(String name) {
        return restaurantRepository.findByName(name);
    }

}