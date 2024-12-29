package org.example.observers;
import org.example.models.Dish;


public class RemoveDishObserver implements Observer<Dish> {
    @Override
    public void update(Dish dish) {
        System.out.println(String.format("""
                \033[1;31m[-]\033[0m \033[1;33mNotificación\033[0m: Se ha eliminado el Plato - \033[1;31m%s\033[0m
                """, dish.getName()));
    }
}