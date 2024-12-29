package org.example.repositories;

import org.example.models.Dish;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DishRepository implements IRepository<Dish> {

    private static DishRepository instance;
    private final Map<String, Dish> dishes;

    private DishRepository() {
        this.dishes = new HashMap<>();
    }

    public static synchronized DishRepository getInstance() {
        if (instance == null) {
            instance = new DishRepository();
        }
        return instance;
    }

    public void save(Dish dish) {
        dishes.put(dish.getName(), dish);
    }

    public Dish findByName(String name) {
        return dishes.get(name);
    }

    public void delete(String name) {
        dishes.remove(name);
    }

    public List<Dish> findAll() {
        return new ArrayList<>(dishes.values());
    }


}