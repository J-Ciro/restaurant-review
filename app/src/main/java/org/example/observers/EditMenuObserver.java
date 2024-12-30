package org.example.observers;

import org.example.models.Menu;
import org.example.models.Restaurant;

public class EditMenuObserver implements Observer<Menu> {
    @Override
    public void update(Menu menu) {
        System.out.println(String.format("""
                \033[1;32m[+]\033[0m \033[1;33mNotificación\033[0m: Se ha editado el Menu
                """, menu.getName()));
    }


}
