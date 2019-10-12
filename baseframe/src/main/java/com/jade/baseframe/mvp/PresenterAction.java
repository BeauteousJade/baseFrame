package com.jade.baseframe.mvp;

@FunctionalInterface
public interface PresenterAction {
    void onAction(Presenter presenter);
}
