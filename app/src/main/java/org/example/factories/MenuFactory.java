package org.example.factories;

import org.example.models.Dish;
import org.example.models.Menu;

import java.util.List;

public class MenuFactory {


    public Menu createMenu(String name, List<Dish> dishes) {
        Menu menu = new Menu(name);
        for (Dish dish : dishes) {
            menu.addItem(dish);
        }
        return menu;
    }
}
