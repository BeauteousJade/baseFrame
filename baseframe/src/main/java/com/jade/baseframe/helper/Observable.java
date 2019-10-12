package com.jade.baseframe.helper;

import java.util.ArrayList;
import java.util.List;

public final class Observable<T> {

    private List<Observer<T>> mObserverList = new ArrayList<>();

    public static <T> Observable<T> create() {
        return new Observable<>();
    }

    public void addObserver(Observer<T> observer) {
        mObserverList.add(observer);
    }

    public void removeObserver(Observer<T> observer) {
        mObserverList.remove(observer);
    }

    public void notifyChanged(T t) {
        for (Observer<T> observer : mObserverList) {
            observer.onChanged(t);
        }
    }
}
