package org.example.observers;

import org.example.models.Menu;

public class NewMenuObserver implements Observer<Menu> {
    @Override
    public void update(Menu menu) {
        System.out.println(String.format("""
                \033[1;32m[+]\033[0m \033[1;33mNotificación\033[0m: Se ha agregado un nuevo menu - \033[1;34m%s\033[0m
                """, menu.getName()));
    }
}