package org.example.observers;

import org.example.models.Dish;

public class NewDishObserver implements Observer<Dish> {
    @Override
    public void update(Dish dish) {
        System.out.println(String.format("""
                 \033[1;32m[+]\033[0m \033[1;33mNotificación\033[0m: Se ha agregado un nuevo plato: \033[1;34m%s\033[0m con precio: \033[1;32m$%.2f\033[0m""",
                dish.getName(),
                dish.getPrice()
        ));
    }

}