package org.example.observers;

public interface Observer<T> {
    void update(T event);

}