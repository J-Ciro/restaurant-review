package org.example.repositories;
import org.example.models.Restaurant;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RestaurantRepository implements IRepository<Restaurant> {
    private static RestaurantRepository instance;
    private final Map<String, Restaurant> restaurants;


    private RestaurantRepository() {
        this.restaurants = new HashMap<>();
    }

    public static RestaurantRepository getInstance() {
        if (instance == null) {
            instance = new RestaurantRepository();
        }
        return instance;
    }

    @Override
    public void save(Restaurant entity) {

        if (entity != null && entity.getName() != null) {
            restaurants.put(entity.getName(), entity);

        } else {
            System.out.println("No se pudo guardar el restaurante: entidad o nombre nulo");
        }
    }

    @Override
    public Restaurant findByName(String name) {
        if (name == null || name.isEmpty()) {
            return restaurants.values().stream()
                    .filter(restaurant -> restaurant.getName().isEmpty())
                    .findFirst()
                    .orElse(null);
        }
        return restaurants.get(name);
    }

    @Override
    public void delete(String name) {
        restaurants.remove(name);
    }

    @Override
    public List<Restaurant> findAll() {
        return new ArrayList<>(restaurants.values());
    }
}