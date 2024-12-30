package org.example.observers;
import org.example.models.Dish;

public class EditDishObserver implements Observer<Dish> {
    @Override
    public void update(Dish dish) {
        System.out.println(String.format("""
                \033[1;32m[+]\033[0m \033[1;33mNotificación\033[0m: Se ha editado el Plato - \033[1;34m%s\033[0m
                """, dish.getName()));
    }


}
