package org.example.observers;

public class ConsoleNotificationObserver<T> implements Observer<T> {
    @Override
    public void update(T event) {
        System.out.println("Notificacion: " + event.toString());
    }
}