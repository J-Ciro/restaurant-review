package org.example.repositories;
import org.example.models.Review;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ReviewRepository implements IRepository<Review> {

    private static ReviewRepository instance;

    private Map<Integer, Review> reviews;

    private ReviewRepository(){
        this.reviews = new HashMap<>();
    }


    public static synchronized ReviewRepository getInstance() {
        if (instance == null) {
            instance = new ReviewRepository();
        }
        return instance;
    }


    @Override
    public void save(Review entity) {

    }

    @Override
    public Review findByName(String name) {
        return null;
    }

    @Override
    public void delete(String name) {

    }

    @Override
    public List<Review> findAll() {
        return List.of();
    }
}
