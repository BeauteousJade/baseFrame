package com.jade.baseframe.helper.loadmore;

import androidx.annotation.IntDef;

public interface LoadMoreCallback {

    int distanceFromLastItem();

    void onStartMore();

    @LoadMoreState
    int getLoadMoreState();

    @IntDef({LoadMoreState.DEFAULT, LoadMoreState.LOAD_MORE})
    @interface LoadMoreState {
        int DEFAULT = 0;
        int LOAD_MORE = 1;
    }
}
