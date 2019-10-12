package com.jade.baseframe.helper;

@FunctionalInterface
public interface Observer<T> {
    void onChanged(T t);
}
