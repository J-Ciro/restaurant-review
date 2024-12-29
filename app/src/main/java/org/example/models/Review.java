package org.example.models;

import org.example.observers.Observer;
import org.example.observers.Subject;

import java.util.List;

public abstract class Review implements Subject {
    private String name;
    private Double rating;
    private String comment;
    private List<Observer> observers;

    public Review(String name, Double rating, String comment) {
        this.name = name;
        this.rating = rating;
        this.comment = comment;
    }

    public Double getRating() { return rating; }
    public String getComment() { return comment; }
    public String getName() { return name; }

}
