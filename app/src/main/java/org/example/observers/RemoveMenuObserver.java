package org.example.observers;
import org.example.models.Menu;


public class RemoveMenuObserver implements Observer<Menu> {
    @Override
    public void update(Menu menu) {
        System.out.println(String.format("""
                \033[1;31m[-]\033[0m \033[1;33mNotificación\033[0m: Se ha eliminado el Menu - \033[1;31m%s\033[0m
                """, menu.getName()));
    }

}