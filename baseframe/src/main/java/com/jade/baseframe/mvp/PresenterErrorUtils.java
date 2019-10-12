package com.jade.baseframe.mvp;

class PresenterErrorUtils {
    public static void errorState(int currentState, int targetState) {
        throw new IllegalStateException(String.format("Don't move the current state(%s) to target state(%s)", currentState, targetState));
    }
}
