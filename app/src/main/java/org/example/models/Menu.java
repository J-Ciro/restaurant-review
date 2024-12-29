package org.example.models;

import java.util.ArrayList;
import java.util.List;

public class Menu {
    private String name;
    private List<Dish> items;

    public Menu(String name) {
        this.name = name;
        this.items = new ArrayList<>();
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public List<Dish> getItems() {
        return items;
    }

    public void addDish(Dish item) {
        items.add(item);
    }

    public void addItem(Dish item) {
        items.add(item);
    }


}