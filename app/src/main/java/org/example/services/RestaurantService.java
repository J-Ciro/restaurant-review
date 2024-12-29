package org.example.services;
import org.example.models.Restaurant;
import org.example.observers.Observer;
import org.example.observers.SubjectImplementation;
import org.example.observers.Subject;
import org.example.repositories.RestaurantRepository;
import java.util.List;

public class RestaurantService {
    private final RestaurantRepository restaurantRepository;
    private final Subject<Restaurant> newRestaurantSubject;
    private final Subject<Restaurant> editRestaurantSubject;
    private final Subject<Restaurant> removeRestaurantSubject;


    public RestaurantService() {
        this.restaurantRepository = RestaurantRepository.getInstance();
        this.newRestaurantSubject = new SubjectImplementation<>();
        this.editRestaurantSubject = new SubjectImplementation<>();
        this.removeRestaurantSubject = new SubjectImplementation<>();
    }



    public void attachNewRestaurantObserver(Observer<Restaurant> observer) {
        newRestaurantSubject.attach(observer);
    }

    public void attachEditRestaurantObserver(Observer<Restaurant> observer) {
        editRestaurantSubject.attach(observer);
    }

    public void attachRemoveRestaurantObserver(Observer<Restaurant> observer) {
        removeRestaurantSubject.attach(observer);
    }





    public void addRestaurant(String restaurantName, String restaurantAddress) {
        Restaurant restaurant = new Restaurant(restaurantName, restaurantAddress);
        restaurantRepository.save(restaurant);
        newRestaurantSubject.notify(restaurant);

    }

    public void removeRestaurant(String restaurantName) {
        Restaurant restaurant = restaurantRepository.findByName(restaurantName);
        if (restaurant != null) {
            restaurantRepository.delete(restaurantName);
            removeRestaurantSubject.notify(restaurant);
        }
    }

    public void editRestaurant(String restaurantName, String newRestaurantName, String restaurantAddress) {
        Restaurant restaurant = restaurantRepository.findByName(restaurantName);
        if (restaurant != null) {

            if ("eliminar".equalsIgnoreCase(newRestaurantName)) {
                newRestaurantName = "";
            } else if (newRestaurantName == null || newRestaurantName.trim().isEmpty()) {
                newRestaurantName = restaurant.getName();
            }

            if ("eliminar".equalsIgnoreCase(restaurantAddress)) {
                restaurantAddress = " ";
            } else if (restaurantAddress == null || restaurantAddress.trim().isEmpty()) {
                restaurantAddress = restaurant.getAddress();
            }
            restaurantRepository.delete(restaurantName);
            restaurant.setName(newRestaurantName);
            restaurant.setAddress(restaurantAddress);
            restaurantRepository.save(restaurant);
            editRestaurantSubject.notify(restaurant);
        } else {
            System.out.println("Restaurante no encontrado.");
        }
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