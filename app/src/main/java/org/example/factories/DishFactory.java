package org.example.factories;

import org.example.models.Dish;

import java.util.ArrayList;

public class DishFactory {
    public Dish createDish(String name, Double price) {
        return new Dish(name, price, new ArrayList<>());
    }
}