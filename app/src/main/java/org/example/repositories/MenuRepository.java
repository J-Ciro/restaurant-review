package org.example.repositories;
import org.example.models.Menu;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MenuRepository implements IRepository<Menu> {
    private static MenuRepository instance;
    private Map<String, Menu> menus;

    private MenuRepository(){
        this.menus = new HashMap<>();
    }


    public static synchronized MenuRepository getInstance() {
        if (instance == null) {
            instance = new MenuRepository();
        }
        return instance;
    }

    @Override
    public void save(Menu entity) {
        if (entity != null && entity.getName() != null) {
            menus.put(entity.getName(), entity);
            System.out.println("Menu guardado: " + entity.getName());
        } else {
            System.out.println("No se pudo guardar el menu: entidad o nombre nulo.");
        }
    }

    @Override
    public Menu findByName(String name) {
        return null;
    }

    @Override
    public void delete(String name) {

    }

    @Override
    public List<Menu> findAll() {
        return List.of();
    }
}

