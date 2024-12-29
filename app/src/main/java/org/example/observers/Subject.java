package org.example.observers;

public interface Subject<T> {
    void attach(Observer<T> observer);
    void detach(Observer<T> observer);
    void notify(T event);
}

