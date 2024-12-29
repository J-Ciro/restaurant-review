package org.example.observers;

import java.util.ArrayList;
import java.util.List;

public class SubjectImplementation<T> implements Subject<T> {
    private final List<Observer<T>> observers = new ArrayList<>();

    @Override
    public void attach(Observer<T> observer) {
        observers.add(observer);
    }

    @Override
    public void detach(Observer<T> observer) {
        observers.remove(observer);
    }

    @Override
    public void notify(T event) {
        for (Observer<T> observer : observers) {
            observer.update(event);
        }
    }
}