package ija.ija2022.homework2.common;

import java.util.HashSet;
import java.util.Set;

public abstract class AbstractObservable implements Field {
    private final Set<Observable.Observer> observers = new HashSet();

    public AbstractObservable() {
    }

    public void addObserver(Observable.Observer o) {
        this.observers.add(o);
    }

    public void removeObserver(Observable.Observer o) {
        this.observers.remove(o);
    }

    public void notifyObservers() {
        this.observers.forEach((o) -> {
            o.update( this);
        });
    }
}