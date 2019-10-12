package com.jade.jade_baseframe.network.request;

import io.reactivex.Observable;

public interface Request<T> {
    void enqueue(RequestCallback<T> requestCallback);

    default void enqueue() {
        enqueue(null);
    }

    void enqueue(RequestCallback<T> requestCallback, int start, int end);

    default void enqueue(int start, int end) {
        enqueue(null, start, end);
    }

    void cancel();

    Observable<T> createObservable();

    Observable<T> onCreateObservable(int start, int end);
}